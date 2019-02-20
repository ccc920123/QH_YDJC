package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.biz.Q09Return;
import com.scxd.beans.biz.Q10Return;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.pojo.test.BizVehInfoBean;
import com.scxd.beans.webBeans.WebRoot;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.common.XmlUtil;
import com.scxd.dao.mapper.VehiclesDao;
import com.scxd.service.DriverService;
import com.scxd.service.VehiclesService;
import com.scxd.service.common.CacheMap;
import com.scxd.service.common.LibSysParam;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccess;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccessService;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccessServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：查询机动车信息
 */
@Service
public class VehiclesServiceImpl implements VehiclesService {

    @Autowired
    private VehiclesDao vd;
    @Resource
    private LibSysParam libSysParam;
    @Resource
    private LoggerService loggerService;
    @Autowired
    private DriverServiceImpl driverService;

    @Override
    public Response getVehiclesInfo(BaseRequest baseRequest, HttpServletRequest request) throws Exception {
        String querydoc = baseRequest.getQuerydoc();
        //获取查询条件
        Map<String, String> map = (Map<String, String>) JSON.parse(querydoc);
        String hpzl = map.get("hpzl");
        String hphm = map.get("hphm");
        loggerService.writeOperaLogger(baseRequest.getUser(), 2, hphm, baseRequest.getWym());
        Q09Return result = getVehMessage(hphm, hpzl, request);

        return (result != null) ? new Response().success(result) : new Response().failure("机动车获取信息失败");

    }

    /**
     * 获取机动车信息
     *
     * @param hphm
     * @param hpzl
     * @param request
     * @return
     */
    public Q09Return getVehMessage(String hphm, String hpzl, HttpServletRequest request) throws Exception {
        Q09Return result = null;

        if (StringUtil.isEmpty(hphm) || hphm.length() < 4 || StringUtil.isEmpty(hpzl)) {
            return null;
        }
        String sf = hphm.substring(0, 1);
        String hphm_last = hphm.substring(1, hphm.length());
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(hphm_last);
        if (m.find()) {
            hphm_last = m.replaceAll("");
        }
        //判断最近一小时有无机动车信息
//           result = vd.selectVehicles(hphm, hpzl);
//            if (result != null) {
//                return result;
//            } else {//最近一小时没有信息，调综合平台并存入数据库再调。
        TmriJaxRpcOutNewAccessService service1 = new TmriJaxRpcOutNewAccessServiceLocator();
        TmriJaxRpcOutNewAccess soap = service1.getTmriOutNewAccess();
        String jkxlh = libSysParam.getZHPTJKXLH();
        String local = libSysParam.getLocal();
        String ServerName = request.getServerName();
        BizVehInfoBean bizVehInfoBean = null;
        if (sf.equals(local)) {
            bizVehInfoBean = getVehMessageO1C21(soap, hpzl, hphm_last, jkxlh, ServerName);
        }
        if (bizVehInfoBean == null) {
            bizVehInfoBean = getVehMessageO1C49(soap, sf, hpzl, hphm_last, jkxlh, ServerName);
            if (bizVehInfoBean != null) {
                bizVehInfoBean.setHphm(hphm);
                bizVehInfoBean.setFzjg(hphm.substring(0, 2));
            }
        } else {
            String lxdh = bizVehInfoBean.getLxdh();
            String sjhm = bizVehInfoBean.getSjhm();
            String sfzmhm = bizVehInfoBean.getSfzmhm();
            lxdh = StringUtil.isNotEmpty(sjhm) ? sjhm : lxdh;
            bizVehInfoBean.setLxdh(lxdh);
            if ((StringUtil.isEmpty(lxdh) || (StringUtil.isNotEmpty(lxdh) && lxdh.contains("*"))) && StringUtil.isNotEmpty(sfzmhm)) {
                Q10Return driver = driverService.getDriverBysFzmhm(sfzmhm, request);
                if (driver != null) {
                    bizVehInfoBean.setLxdh(driver.getLxdh());
                }
            }
        }
        if (bizVehInfoBean != null) {
          int n=  vd.deleteVehByHphmHpzl(hphm, hpzl);
            String uuid = UUID.randomUUID().toString();
            bizVehInfoBean.setHphm(hphm);
            bizVehInfoBean.setXrsj(new Date());
            bizVehInfoBean.setId(uuid);
            vd.insertVehMessageC21(bizVehInfoBean);
        }
        result = vd.selectVehicles(hphm, hpzl);
//            }

        return result;
    }

    private BizVehInfoBean getVehMessageO1C49(TmriJaxRpcOutNewAccess soap, String sf, String hpzl, String hphm_last, String jkxlh, String serverName) throws UnsupportedEncodingException, RemoteException {
        BizVehInfoBean bizVehInfo = null;
        if (CacheMap.isTest){
            return bizVehInfo;
        }
        String queryXmlDoc = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<root>\n" +
                "<QueryCondition>\n" +
                "<hpzl>" + hpzl +
                "</hpzl>\n" +
                "<hphm>" + URLEncoder.encode(hphm_last, "utf-8") +
                "</hphm>\n" +
                "<sf>" + URLEncoder.encode(sf, "utf-8") +
                "</sf>\n" +
                "</QueryCondition>\n" +
                "</root>";
        String cjbh = "";
        //获取综合平台接口序列号
        Map<String, String> map = libSysParam.getCJBHMap();
        if (map != null) {
            cjbh = map.get("01C49").trim();
        }
        String platResult = soap.queryObjectOutNew("01", jkxlh, "01C49", cjbh, "", "", "", "", serverName, queryXmlDoc);
        platResult = URLDecoder.decode(platResult, "utf-8");
        loggerService.writeWebLogger(queryXmlDoc, platResult, "01C49");
        WebRoot root = null;
        if (StringUtil.isNotEmpty(platResult)) {
            root = XmlUtil.toObj(platResult, WebRoot.class);

        }
        if (root == null || root.getHead() == null || !"1".equals(root.getHead().getCode())) {

        } else {
            bizVehInfo = root.getBody().getVeh();

        }
        return bizVehInfo;

    }

    private BizVehInfoBean getVehMessageO1C21(TmriJaxRpcOutNewAccess soap, String hpzl, String hphm_last, String jkxlh, String ServerName) throws UnsupportedEncodingException, RemoteException {
        BizVehInfoBean bizVehInfo = null;
        if (CacheMap.isTest){
            return bizVehInfo;
        }
        String queryXmlDoc = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<root>\n" +
                "<QueryCondition>\n" +
                "<hpzl>" + hpzl +
                "</hpzl>\n" +
                "<hphm>" + URLEncoder.encode(hphm_last, "utf-8") +
                "</hphm>\n" +
                "<dabh>" + "" +
                "</dabh>\n" +
                "</QueryCondition>\n" +
                "</root>";
        String cjbh = "";
        //获取综合平台接口序列号
        Map<String, String> map = libSysParam.getCJBHMap();
        if (map != null) {
            cjbh = map.get("01C21").trim();
        }
        String platResult = soap.queryObjectOutNew("01", jkxlh, "01C21", cjbh, "", "", "", "", ServerName, queryXmlDoc);
        platResult = URLDecoder.decode(platResult, "utf-8");
        loggerService.writeWebLogger(queryXmlDoc, platResult, "01C21");
        WebRoot root = null;
        if (StringUtil.isNotEmpty(platResult)) {
            root = XmlUtil.toObj(platResult, WebRoot.class);

        }
        if (root == null || root.getHead() == null || !"1".equals(root.getHead().getCode())) {

        } else {
            bizVehInfo = root.getBody().getVeh();

        }
        return bizVehInfo;
    }


}
