package com.scxd.service.impl;

import com.scxd.beans.common.ListTotal;
import com.scxd.beans.management.LedgerListBean;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.pdaBeans.Root;
import com.scxd.beans.pdaBeans.response.Q30ReturnBean;
import com.scxd.beans.pojo.BizLedgerInfo;
import com.scxd.common.*;
import com.scxd.dao.mapper.BizLedgerInfoMapper;
import com.scxd.dao.mapper.BizPoliceInfoMapper;
import com.scxd.dao.mapper.SysDepartmentParamMapper;
import com.scxd.service.LedgerService;
import com.scxd.service.common.CacheMap;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.webjczhpt.RmJaxRpcOutAccess;
import com.scxd.webjczhpt.RmJaxRpcOutAccessService;
import com.scxd.webjczhpt.RmJaxRpcOutAccessServiceLocator;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.*;

@Service
public class LedgerServiceImpl implements LedgerService {

    @Resource
    private BizLedgerInfoMapper bizLedgerInfoMapper;
    @Resource
    private LoggerService loggerService;
    @Resource
    private SysDepartmentParamMapper sysDepartmentParamMapper;
    @Resource
    private BizPoliceInfoMapper bizPoliceInfoMapper;


    /**
     * 台账信息写入
     *
     * @param baseRequest
     */
    @Override
    public Response writeLedger(BaseRequest baseRequest) throws ParseException, UnsupportedEncodingException, RemoteException, ServiceException {
        String writedoc = baseRequest.getWritedoc();
        Response response = null;
        if (StringUtil.isNotEmpty(writedoc)) {
            BizLedgerInfo bizLedgerInfo = JSONUtil.parseObject(writedoc, BizLedgerInfo.class);
            if (bizLedgerInfo == null) {
                response = new Response().failure("传入的数据不能解析");
            } else {
                String id = UUID.randomUUID().toString();
                bizLedgerInfo.setId(id);

                bizLedgerInfo.setJszh(bizLedgerInfo.getZjszh());
                int num = bizLedgerInfoMapper.insertSelective(bizLedgerInfo);
                if (num > 0) {
                    String bmbh = bizLedgerInfo.getSsbmbh();
                    String writeXmlDoc = getLedgerXml(bizLedgerInfo);
                    loggerService.writeOperaLogger(baseRequest.getUser(), 17, bizLedgerInfo.getHphm(), baseRequest.getWym());
                    String jkxlh = sysDepartmentParamMapper.getCSZByBmbhAndCsdm(bmbh, "JCZHJKXLH");
                    WebResponse webResponse = writeLegerToWeb(writeXmlDoc, jkxlh);
                    if (webResponse.isSuccess()) {
                        Map map1 = new HashMap<String, String>();
                        map1.put("id", id);
                        response = new Response().success(map1);
                    } else {
                        response = new Response().failure("写入集成平台失败" + webResponse.getMessage());
                    }
                } else {
                    response = new Response().failure("台账信息写入失败");
                }
            }
        } else {
            response = new Response().failure("数据不完善");
        }
        return response;
    }

    @Override
    public Response getLedgerList(String querydoc) throws Exception {
        Response response = null;
        List<Q30ReturnBean> list=null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
            int page = Integer.valueOf(String.valueOf(map.get("page")));
            int pageSize = Integer.valueOf(String.valueOf(map.get("pageSize")));
            map.put("pagefirst", (page - 1) * pageSize + 1);
            map.put("pageend", page * pageSize);
//            map.put("ksrq", DateUtils.strToDateORNULLDAY(map.get("ksrq").toString()));
//            map.put("jsrq", DateUtils.strToDateORNULLDAY2(map.get("jsrq").toString()));
            int total = bizLedgerInfoMapper.getLedgerDocTotal(map);
            if (total > 0) {
                 list = bizLedgerInfoMapper.getLedgerDocument(map);
            }

            if (list != null && list.size() > 0) {
                ListTotal listTotal = new ListTotal(list,total);

                response = new Response().success(listTotal);
            } else {
                response = new Response().failure("未能查询到信息");
            }
        } else {
            response = new Response().failure("查询条件不完善");
        }
        return response;
    }

    @Override
    public Response getLedgerDetail(String querydoc) throws Exception {
        //String querydoc = baseRequest.getQuerydoc();
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
            String tzlsh = map.get("tzlsh").toString();
            BizLedgerInfo bizLedgerInfo = bizLedgerInfoMapper.getLedgerDocumentDetail(tzlsh);
            List<String> photos = bizLedgerInfoMapper.getLedgerPhoto(tzlsh);
            if (bizLedgerInfo != null) {
                bizLedgerInfo.setPhotos(photos);
                response = new Response().success(bizLedgerInfo);
            } else {
                response = new Response().failure("未能查询到信息");
            }
        } else {
            response = new Response().failure("查询条件不完善");
        }
        return response;
    }


    //将台账信息写进综合平台
    public WebResponse writeLegerToWeb(String writeXmlDoc, String jkxlh) throws ServiceException, RemoteException, UnsupportedEncodingException {
        WebResponse webResponse = new WebResponse();
        if (CacheMap.isTest) {
            return webResponse;
        }
        RmJaxRpcOutAccessService rmJaxRpcOutAccessService = new RmJaxRpcOutAccessServiceLocator();
        RmJaxRpcOutAccess serviceSoap = rmJaxRpcOutAccessService.getRmOutAccess();

        String result = serviceSoap.writeObjectOut("64", jkxlh, "64W01", writeXmlDoc);
        result = URLDecoder.decode(result, "utf-8");
        loggerService.writeWebLogger(writeXmlDoc, result, "64W01");
        Root root = null;
        if (StringUtil.isNotEmpty(result)) {
            root = XmlUtil.toObj(result, Root.class);

        }
        if (root == null) {
            webResponse.setSuccess(false);
            webResponse.setMessage("未能连接到集成指挥平台");
            return webResponse;
        }
        if (root.getHead() != null && "1".equals(root.getHead().getCode())) {
            webResponse.setSuccess(true);
        } else {
            webResponse.setSuccess(false);
            webResponse.setMessage("上传集成指挥平台有误");
        }
        return webResponse;
    }

    /**
     * 拼装上传的xml
     *
     * @param bizLedgerInfo
     * @return
     */
    private String getLedgerXml(BizLedgerInfo bizLedgerInfo) {
        String writeXmlDoc = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<root>\n" +
                //根据jcmj查询police的执法站编号
                "<vehcheck>\n" +
                "<fwzbh>" + StringEx.nullToStrUtf(bizLedgerInfo.getFwzbh()) + "</fwzbh>\n" +
                "<clzt>" + StringEx.nullToStrUtf(bizLedgerInfo.getClzt()) + "</clzt>\n" +
                "<hpzl>" + StringEx.nullToStrUtf(bizLedgerInfo.getHpzl()) + "</hpzl>\n" +
                "<hphm>" + StringEx.nullToStrUtf(bizLedgerInfo.getHphm()) + "</hphm>\n" +
                "<jccllx>" + StringEx.nullToStrUtf(bizLedgerInfo.getJccllx()) + "</jccllx>\n" +
                "<jcclzlx>" + StringEx.nullToStrUtf(bizLedgerInfo.getJcclzlx()) + "</jcclzlx>\n" +
                "<sfd>" + StringEx.nullToStrUtf(bizLedgerInfo.getSfd()) + "</sfd>\n" +
                "<mdd>" + StringEx.nullToStrUtf(bizLedgerInfo.getMdd()) + "</mdd>\n" +
                "<zks>" + StringEx.nullToStrUtf(bizLedgerInfo.getZks()) + "</zks>\n" +
                "<sjzzl>" + StringEx.nullToStrUtf(bizLedgerInfo.getSjzzl()) + "</sjzzl>\n" +
                "<gps>" + StringEx.nullToStrUtf(bizLedgerInfo.getGps()) + "</gps>\n" +
                "<aqsb>" + StringEx.nullToStrUtf(bizLedgerInfo.getAqsb()) + "</aqsb>\n" +
                "<cllthw>" + StringEx.nullToStrUtf(bizLedgerInfo.getCllthw()) + "</cllthw>\n" +
                "<wfyy>" + StringEx.nullToStrUtf(bizLedgerInfo.getWfyy()) + "</wfyy>\n" +
                "<jaqd>" + StringEx.nullToStrUtf(bizLedgerInfo.getJaqd()) + "</jaqd>\n" +
                "<pljs>" + StringEx.nullToStrUtf(bizLedgerInfo.getPljs()) + "</pljs>\n" +
                "<ffgz>" + StringEx.nullToStrUtf(bizLedgerInfo.getFfgz()) + "</ffgz>\n" +
                "<ztfgbs>" + StringEx.nullToStrUtf(bizLedgerInfo.getZtfgbs()) + "</ztfgbs>\n" +
                "<azfhzz>" + StringEx.nullToStrUtf(bizLedgerInfo.getAzfhzz()) + "</azfhzz>\n" +
                "<xgjsbz>" + StringEx.nullToStrUtf(bizLedgerInfo.getXgjsbz()) + "</xgjsbz>\n" +
                "<azdsj>" + StringEx.nullToStrUtf(bizLedgerInfo.getAzdsj()) + "</azdsj>\n" +
                "<azdlx>" + StringEx.nullToStrUtf(bizLedgerInfo.getAzdlx()) + "</azdlx>\n" +
                "<sfwzjs>" + StringEx.nullToStrUtf(bizLedgerInfo.getSfwzjs()) + "</sfwzjs>\n" +
                "<sfyfjsy>" + StringEx.nullToStrUtf(bizLedgerInfo.getSfyfjsy()) + "</sfyfjsy>\n" +
                "<qdystxz>" + StringEx.nullToStrUtf(bizLedgerInfo.getQdystxz()) + "</qdystxz>\n" +
                "<sfyqwjy>" + StringEx.nullToStrUtf(bizLedgerInfo.getSfyqwjy()) + "</sfyqwjy>\n" +
                "<sfyqwbf>" + StringEx.nullToStrUtf(bizLedgerInfo.getSfyqwbf()) + "</sfyqwbf>\n" +
                "<sfwfwcl>" + StringEx.nullToStrUtf(bizLedgerInfo.getSfwfwcl()) + "</sfwfwcl>\n" +
                "<jcjg>" + StringEx.nullToStrUtf(bizLedgerInfo.getJcjg()) + "</jcjg>\n" +
                "<jcqkms>" + StringEx.nullToStrUtf(bizLedgerInfo.getJcqkms()) + "</jcqkms>\n" +
                "<jcsj>" + StringEx.nullToStrUtf(DateUtils.dateToStrSS(bizLedgerInfo.getJcsj())) + "</jcsj>\n" +
                "<jcmj>" + StringEx.nullToStrUtf(bizLedgerInfo.getJcmj()) + "</jcmj>\n" +
                "<zjszh>" + StringEx.nullToStrUtf(bizLedgerInfo.getZjszh()) + "</zjszh>\n" +
                "<zlxdh>" + StringEx.nullToStrUtf(bizLedgerInfo.getZlxdh()) + "</zlxdh>\n" +
                "<zjssfcf>" + StringEx.nullToStrUtf(bizLedgerInfo.getZjssfcf()) + "</zjssfcf>\n" +
                "<zjssfyqwsy>" + StringEx.nullToStrUtf(bizLedgerInfo.getZjssfyqwsy()) + "</zjssfyqwsy>\n" +
                "<zjssfyqwhz>" + StringEx.nullToStrUtf(bizLedgerInfo.getZjssfyqwhz()) + "</zjssfyqwhz>\n" +
                "<zjszjbf>" + StringEx.nullToStrUtf(bizLedgerInfo.getZjszjbf()) + "</zjszjbf>\n" +
                "<fjszh>" + StringEx.nullToStrUtf(bizLedgerInfo.getFjszh()) + "</fjszh>\n" +
                "<flxdh>" + StringEx.nullToStrUtf(bizLedgerInfo.getFlxdh()) + "</flxdh>\n" +
                "<fjssfcf>" + StringEx.nullToStrUtf(bizLedgerInfo.getFjssfcf()) + "</fjssfcf>\n" +
                "<fjssfyqwsy>" + StringEx.nullToStrUtf(bizLedgerInfo.getFjssfyqwsy()) + "</fjssfyqwsy>\n" +
                "<fjssfyqwhz>" + StringEx.nullToStrUtf(bizLedgerInfo.getFjssfyqwhz()) + "</fjssfyqwhz>\n" +
                "<fjszjbf>" + StringEx.nullToStrUtf(bizLedgerInfo.getFjszjbf()) + "</fjszjbf>\n" +
                "<dgzc>" + StringEx.nullToStrUtf(bizLedgerInfo.getDgzc()) + "</dgzc>\n" +
                "<zdzc>" + StringEx.nullToStrUtf(bizLedgerInfo.getZdzc()) + "</zdzc>\n" +
                "<zxzc>" + StringEx.nullToStrUtf(bizLedgerInfo.getZxzc()) + "</zxzc>\n" +
                "<sfjbc>" + StringEx.nullToStrUtf(bizLedgerInfo.getSfjbc()) + "</sfjbc>\n" +
                "<bxpc>" + StringEx.nullToStrUtf(bizLedgerInfo.getBxpc()) + "</bxpc>\n" +
                "<sftbjqx>" + StringEx.nullToStrUtf(bizLedgerInfo.getSftbjqx()) + "</sftbjqx>\n" +
                "<srzk>" + StringEx.nullToStrUtf(bizLedgerInfo.getSrzk()) + "</srzk>\n" +
                "<sczk>" + StringEx.nullToStrUtf(bizLedgerInfo.getSczk()) + "</sczk>\n" +
                "<yxqy>" + StringEx.nullToStrUtf(bizLedgerInfo.getYxqy()) + "</yxqy>\n" +
                "<yyxm>" + StringEx.nullToStrUtf(bizLedgerInfo.getYyxm()) + "</yyxm>\n" +
                "<yydh>" + StringEx.nullToStrUtf(bizLedgerInfo.getYydh()) + "</yydh>\n" +
                "<yysfz>" + StringEx.nullToStrUtf(bizLedgerInfo.getYysfz()) + "</yysfz>\n" +
                "<whpzl>" + StringEx.nullToStrUtf(bizLedgerInfo.getWhpzl()) + "</whpzl>\n" +
                "<whpmc>" + StringEx.nullToStrUtf(bizLedgerInfo.getWhpmc()) + "</whpmc>\n" +
                "<chjg>" + StringEx.nullToStrUtf(bizLedgerInfo.getChjg()) + "</chjg>\n" +
                "<cljg>" + StringEx.nullToStrUtf(bizLedgerInfo.getCljg()) + "</cljg>\n" +
                //文书编号自己生成
                "<wsbh>" + StringEx.nullToStrUtf(bizLedgerInfo.getWsbh()) + "</wsbh>\n" +
                "<jyw>" + StringEx.nullToStrUtf(bizLedgerInfo.getJyw()) + "</jyw>\n" +
                "<wslb>" + StringEx.nullToStrUtf(bizLedgerInfo.getWslb()) + "</wslb>\n" +
                "<cfxylx>" + StringEx.nullToStrUtf(bizLedgerInfo.getCfxylx()) + "</cfxylx>\n" +
                "<cfxyzlx>" + StringEx.nullToStrUtf(bizLedgerInfo.getCfxyzlx()) + "</cfxyzlx>\n" +
                "<yjbm>" + StringEx.nullToStrUtf(bizLedgerInfo.getYjbm()) + "</yjbm>\n" +
                "<lxr>" + StringEx.nullToStrUtf(bizLedgerInfo.getLxr()) + "</lxr>\n" +
                "<lxdh>" + StringEx.nullToStrUtf(bizLedgerInfo.getLxdh()) + "</lxdh>\n" +
                "<czqkms>" + StringEx.nullToStrUtf(bizLedgerInfo.getCzqkms()) + "</czqkms>\n" +
                "</vehcheck>\n" +
                "</root>";
        return writeXmlDoc;
    }

    @Override
    public Response queryLeadgerList(String bmbh, String ksrq, String jsrq, String zfzbh, String hphm, String jszh, String pageNo, String pageSize) {
        int total = bizLedgerInfoMapper.queryLeadgerListTotal(bmbh, ksrq, jsrq, zfzbh, hphm, jszh);
        List<LedgerListBean> listBeans = new ArrayList<>();
        if (total > 0) {
            listBeans = bizLedgerInfoMapper.queryLeadgerList(bmbh, ksrq, jsrq, zfzbh, hphm, jszh, pageNo, pageSize);
        }
        ListTotal listTotal = new ListTotal(listBeans, total);
        return new Response().success(listTotal);
    }

    @Override
    public Response queryLeadgerDetail(String id, HttpServletRequest request) {
        if (StringUtil.isEmpty(id)) {
            return new Response().failure("查询数据为空");
        }
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort();
        Map<String, Object> map = bizLedgerInfoMapper.getLedgerPagesDetail(id);
        List<String> photos = bizLedgerInfoMapper.getLedgerPhoto(id);
        if (photos != null && photos.size() > 0) {
            for (int i = 0; i < photos.size(); i++) {
                String url = photos.get(i);
                url = ImgTools.getLocalServiceUrl(url, basePath);
                photos.set(i, url);
            }
        }
        map.put("photos", photos);
        return new Response().success(map);
    }
}
