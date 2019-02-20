package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.biz.Q12Return;
import com.scxd.beans.pdaBeans.Root;
import com.scxd.beans.pojo.BizPoliceGps;
import com.scxd.common.*;
import com.scxd.dao.mapper.BizPoliceGpsMapper;
import com.scxd.dao.mapper.SysDepartmentParamMapper;
import com.scxd.dao.mapper.WarningPushDao;
import com.scxd.service.PushService;
import com.scxd.service.common.CacheMap;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.webjczhpt.RmJaxRpcOutAccess;
import com.scxd.webjczhpt.RmJaxRpcOutAccessService;
import com.scxd.webjczhpt.RmJaxRpcOutAccessServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PushServiceImpl implements PushService {

    @Autowired
    private WarningPushDao wpd;
    @Autowired
    private SysDepartmentParamMapper sysDepartmentParamMapper;
    @Resource
    private LoggerService loggerService;
    @Resource
    private BizPoliceGpsMapper bizPoliceGpsMapper;
    @Override
    public Response getWarningPush(String querydoc) {
        Map<String, String> map = (Map<String, String>) JSON.parse(querydoc);
        String user = map.get("user");
        String bmbh=map.get("bmbh");
        String jd = map.get("jd");
        String wd = map.get("wd");
        Q12Return result = wpd.selectWarningPushByUser(user);
        if (StringUtil.isNotEmpty(bmbh)&&StringUtil.isNotEmpty(jd) && StringUtil.isNotEmpty(wd)) {
            try {
                upLoadPolicesLocal(user,bmbh,jd,wd);
            } catch (ServiceException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return (result != null) ? new Response().success(result) : new Response().failure("获取预警推送消息失败");

    }

    private void upLoadPolicesLocal(String user,String bmbh, String jd, String wd) throws ServiceException, UnsupportedEncodingException, RemoteException {
        String jkxlh = sysDepartmentParamMapper.getCSZByBmbhAndCsdm(bmbh, "JCZHJKXLH");
        RmJaxRpcOutAccessService rmJaxRpcOutAccessService = new RmJaxRpcOutAccessServiceLocator();
        RmJaxRpcOutAccess serviceSoap = rmJaxRpcOutAccessService.getRmOutAccess();
        Date date=new Date();
        String xml = getPolicesLocalXml(user,jd,wd,DateUtils.dateToStrSS2(date));
        //上传数据到集成指挥平台
        WebResponse webResponse = writePolicesLocalToWeb(xml, jkxlh, serviceSoap);
        BizPoliceGps bizPoliceGps=new BizPoliceGps();
        bizPoliceGps.setId(UUID.randomUUID().toString());
        bizPoliceGps.setSbsj(date);
        bizPoliceGps.setJd(jd);
        bizPoliceGps.setWd(wd);
        bizPoliceGps.setRybh(user);
        bizPoliceGps.setScbj((short) (webResponse.isSuccess()?1:0));
        bizPoliceGps.setSbyy(webResponse.getMessage());
        bizPoliceGpsMapper.insertSelective(bizPoliceGps);
    }

    private String getPolicesLocalXml( String user, String jd, String wd,String date) {
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        stringBuilder.append("<root>\n" +
                "<police>");
        stringBuilder.append("<gpsbh></gpsbh>");
        stringBuilder.append("<rylx></rylx>");
        stringBuilder.append("<rybh>" + user + "</rybh>");
        stringBuilder.append("<sbsj>" + date + "</sbsj>");
        stringBuilder.append("<jd>" + jd + "</jd>");
        stringBuilder.append("<wd>" + wd + "</wd>");
        stringBuilder.append("<sd></sd>");
        stringBuilder.append("<fx></fx>");

        stringBuilder.append("</police>\n" +
                "</root>");
        return stringBuilder.toString();
    }

    @Override
    public Response pushBack(String writedoc) {
        Map<String, Object> map = (Map<String, Object>) JSON.parse(writedoc);
        map.put("id", UUID.randomUUID().toString());
        map.put("hzsj", new Date());
        int i = wpd.pushBack(map);
        if (i > 0) {
            return new Response().success();
        } else {
            return new Response().failure();
        }

    }

    /**
     * '
     * 上传数据到集成指挥平台
     *
     * @param jkxlh
     * @param xml
     * @param serviceSoap
     * @return
     * @throws ServiceException
     * @throws RemoteException
     * @throws UnsupportedEncodingException
     */
    private WebResponse writePolicesLocalToWeb(String xml, String jkxlh, RmJaxRpcOutAccess serviceSoap) throws ServiceException, RemoteException, UnsupportedEncodingException {
        WebResponse webResponse = new WebResponse();
        if (CacheMap.isTest) {
            return webResponse;
        }
        String body = serviceSoap.writeObjectOut("68", jkxlh, "68W06", xml);
        body = URLDecoder.decode(body, "utf-8");
        loggerService.writeWebLogger(xml, body, "68W06");
        Root root = null;

        if (StringUtil.isNotEmpty(body)) {
            root = XmlUtil.toObj(body, Root.class);
            if (root == null || root.getHead() == null || !"1".equals(root.getHead().getCode())) {
                webResponse.setSuccess(false);
                if (root != null && root.getHead() != null && StringUtil.isNotEmpty(root.getHead().getMessage())) {
                    webResponse.setMessage(root.getHead().getMessage());
                } else {
                    webResponse.setMessage("数据上传集成指挥平台失败");
                }
            }

        } else {
            webResponse.setSuccess(false);
            webResponse.setMessage("上传集成指挥平台失败");
        }

        return webResponse;
    }
}
