package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.biz.BizDrvInfo;
import com.scxd.beans.biz.BizPhotoInfo;
import com.scxd.beans.biz.Q10Return;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.webBeans.DriverPhotoBean;
import com.scxd.beans.webBeans.WebRoot;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.common.XmlUtil;
import com.scxd.dao.mapper.DriverDao;
import com.scxd.dao.mapper.PhotoDao;
import com.scxd.service.DriverService;
import com.scxd.service.common.CacheMap;
import com.scxd.service.common.LibSysParam;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccess;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccessService;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccessServiceLocator;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

/**
 * 功能描述：查询驾驶人信息
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Resource
    private DriverDao dd;
    @Resource
    private LibSysParam libSysParam;
    @Resource
    private PhotoDao photoDao;

    @Resource
    private LoggerService loggerService;
    //通过驾驶证号获取驾驶人信息

    /**
     * @param baseRequest 查询的json数据
     * @param request     生成图片链接需要的数据
     * @return 返回一个封装bean
     * @throws Exception
     */
    @Override
    public Response getDriverByjszh(BaseRequest baseRequest, HttpServletRequest request) throws Exception {
        String querydoc = baseRequest.getQuerydoc();
        if (StringUtil.isEmpty(querydoc)) return new Response().failure("查询参数为空");
        Map<String, String> map = (Map<String, String>) JSON.parse(querydoc);
        String sfzmhm = map.get("jszh");
        loggerService.writeOperaLogger(baseRequest.getUser(), 4, sfzmhm, baseRequest.getWym());
        Q10Return driver= getDriverBysFzmhm(sfzmhm, request);
        return (driver == null) ? new Response().failure("没有该驾驶人信息") : new Response().success(driver);
    }

    /**
     * 从综合平台获取驾驶证信息
     *
     * @param jkxlh
     * @param zxbh  证信编号
     * @param soap
     */
    private BizDrvInfo getDriverBysZxbh(String zxbh, String jkxlh, TmriJaxRpcOutNewAccess soap, HttpServletRequest request) throws UnsupportedEncodingException, ServiceException, RemoteException {
        BizDrvInfo bizDrvInfo = null;
        //查询条件XML文档
        String queryXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<root> \n" +
                "  <QueryCondition> \n" +
                "    <zxbh>" +
                zxbh +
                "</zxbh> \n" +
                "  </QueryCondition> \n" +
                "</root>";
        //获取综合平台接口序列号
        String cjbh = "";
        Map<String, String> map = libSysParam.getCJBHMap();
        if (map != null) {
            boolean b = map.containsKey("02CB4");
            cjbh = (b == true) ? map.get("02CB4").trim() : "";
        }
        //调取查询方法
        String platResult = soap.queryObjectOutNew("02", jkxlh, "02CB4", cjbh, "", "", "", "", request.getServerName(), queryXML);
        platResult = URLDecoder.decode(platResult, "utf-8");
        loggerService.writeWebLogger(queryXML, platResult, "02CB4");
        WebRoot root = null;
        if (StringUtil.isNotEmpty(platResult)) {
            root = XmlUtil.toObj(platResult, WebRoot.class);
        }
        if (root == null || root.getHead() == null || !"1".equals(root.getHead().getCode())) {

        } else {

            bizDrvInfo = root.getBody().getBizDrvInfo();
        }
        return bizDrvInfo;
    }

    /**
     * 从综合平台获取驾驶证信息
     *
     * @param sfzmhm  身份证明号码
     * @param request
     * @return
     * @throws Exception
     */
    public Q10Return getDriverBysFzmhm(String sfzmhm, HttpServletRequest request) throws Exception {

        Q10Return driver;
        //获取当前时间前一小时的时间。
        //查询本地库有无最近一小时的驾驶人信息
        driver = dd.selectDriverByjszh(sfzmhm);
        if (CacheMap.isTest){
            return driver;
        }
        if (driver == null) {
            TmriJaxRpcOutNewAccessService service1 = new TmriJaxRpcOutNewAccessServiceLocator();
            TmriJaxRpcOutNewAccess soap = service1.getTmriOutNewAccess();
            //获取综合平台接口序列号
            String jkxlh = libSysParam.getZHPTJKXLH();
            BizDrvInfo bizDrvInfo = null;
            if (StringUtil.isNotEmpty(sfzmhm) && sfzmhm.length() > 13) {
                //查询条件XML文档
                String queryXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<root> \n" +
                        "  <QueryCondition> \n" +
                        "    <sfzmhm>" +
                        sfzmhm +
                        "</sfzmhm> \n" +
                        "  </QueryCondition> \n" +
                        "</root>";

                //获取综合平台接口序列号
                String cjbh = "";
                Map<String, String> map = libSysParam.getCJBHMap();
                if (map != null) {
                    boolean b = map.containsKey("02C26");
                    cjbh = (b == true) ? map.get("02C26").trim() : "";
                }
                //调取查询方法
                String platResult = soap.queryObjectOutNew("02", jkxlh, "02C26", cjbh, "", "", "", "", request.getServerName(), queryXML);
                platResult = URLDecoder.decode(platResult, "utf-8");
                loggerService.writeWebLogger(queryXML, platResult, "02C26");
                WebRoot root = null;
                if (StringUtil.isNotEmpty(platResult)) {
                    root = XmlUtil.toObj(platResult, WebRoot.class);
                }
                if (root == null || root.getHead() == null || !"1".equals(root.getHead().getCode())) {

                } else {

                    bizDrvInfo = root.getBody().getDrvInfo();
                }
            } else {
                bizDrvInfo = getDriverBysZxbh(sfzmhm, jkxlh, soap, request);
            }
            if (bizDrvInfo != null) {
                bizDrvInfo.setId(UUID.randomUUID().toString());
                bizDrvInfo.setXrsj(new java.util.Date());
                String queryXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<root> \n" +
                        "  <QueryCondition> \n" +
                        "    <sfzmhm>" +
                        bizDrvInfo.getSfzmhm() +
                        "</sfzmhm> \n" +
                        "  </QueryCondition> \n" +
                        "</root>";
                int i = dd.insertDriverByOBJ(bizDrvInfo);
                if (i > 0) {
                    //获取驾驶人照片
                    getDriverPhoto(soap, jkxlh, bizDrvInfo.getId(), queryXML, request);
                }
            }
            driver = dd.selectDriverByjszh(sfzmhm);
        }


        return driver;

    }

    /**
     * 获取驾驶人照片,插入数据库
     *
     * @param soap     调用综合平台的接口对象
     * @param jkxlh    接口序列号
     * @param glid     关联id
     * @param queryXML 查询的xml
     */
    private void getDriverPhoto(TmriJaxRpcOutNewAccess soap, String jkxlh, String glid, String queryXML, HttpServletRequest request) throws IOException, ServiceException {

        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + contextPath + "/getPhotos?" + "id=";

        String platResult = soap.queryObjectOutNew("02", jkxlh, "02C05", "", "", "", "", "", request.getServerName(), queryXML);
        platResult = URLDecoder.decode(platResult, "utf-8");
        loggerService.writeWebLogger(queryXML, platResult, "02C05");
        WebRoot root = null;
        if (StringUtil.isNotEmpty(platResult)) {
            root = XmlUtil.toObj(platResult, WebRoot.class);
        }
        if (root == null || root.getHead() == null || !"1".equals(root.getHead().getCode())) {

        } else {
            DriverPhotoBean driverPhotoBean = root.getBody().getDrvZp();
            if (driverPhotoBean != null && StringUtil.isNotEmpty(driverPhotoBean.getZpStr())) {
                BizPhotoInfo photoInfo = new BizPhotoInfo();
                String uuid = UUID.randomUUID().toString();
                photoInfo.setId(uuid);
                photoInfo.setGlid(glid);
                photoInfo.setPsry("jcbkpt");
                photoInfo.setZpzl("7004");
                photoInfo.setPssj(new java.util.Date());
                photoInfo.setSx1(basePath + uuid);
                BASE64Decoder decoder = new sun.misc.BASE64Decoder();
                byte[] buffer = decoder.decodeBuffer(driverPhotoBean.getZpStr());
                photoInfo.setZp(buffer);
                photoDao.insertPhotoByObj(photoInfo);
            }


        }

    }


}

