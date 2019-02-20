package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.biz.*;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.webBeans.WebRoot;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.common.WebResponse;
import com.scxd.common.XmlUtil;
import com.scxd.dao.mapper.BizVioInfoMapper;
import com.scxd.service.VioService;
import com.scxd.service.common.CacheMap;
import com.scxd.service.common.LibSysParam;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccess;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccessService;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccessServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 违法信息实现类
 */
@Service
public class VioServiceImpl implements VioService {

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private BizVioInfoMapper vio;
    @Resource
    private LibSysParam libSysParam;
    @Resource
    private LoggerService loggerService;

    //获取机动车违法列表清单
    @Override
    public Response getVioList(BaseRequest baseRequest, HttpServletRequest Hrequest) throws Exception {
        String querydoc = baseRequest.getQuerydoc();
        //判空
        if (StringUtil.isEmpty(querydoc)) return new Response().failure("PDA传入参数为空");
        Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
        String hpzl = (String) map.get("hpzl");
        String hphm = (String) map.get("hphm");
        loggerService.writeOperaLogger(baseRequest.getUser(), 3, hphm, baseRequest.getWym());
        //获取页码和页数大小
        int page = getPage(map);
        int pageSize = getPageSize(map);
        int start = (page - 1) * pageSize;
        int end = page * pageSize;
        //判断最近一天有无存储该车信息,时间为当前时间减去一天的毫秒数，这样与创建时间进行比较
        Date date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        List<Q13Return> result = vio.selectVioByDate(hpzl, hphm, date, start, end);
        if (result != null && result.size() > 0) {
            //信息存在，直接返回给PDA,
            return new Response().success(result);
        } else {//信息不存在，判断是否是查询第一页
            if (page != 1) {//不是第一页，且查询不出数据，则判断第一页有无数据
                result = vio.selectVioByDate(hpzl, hphm, date, 0, pageSize);
                if (result != null && result.size() > 0)//第一页有数据，也就是说数据不满end条
                    return new Response().failure("数据不超过" + end + "条");
            }
        }

        //调取综合平台
        boolean res = getVioInfoFromZHPT(hpzl, hphm, Hrequest);
        if (!res) {
            result = vio.selectVioInfo(hpzl, hphm, start, end);
        } else {
            //数据存在，删除时间超过一天的信息，然后调取综合平台
            vio.deleteZP(date, hphm, hpzl);
            vio.deleteVioInfoByCar(date, hphm, hpzl);
            result = vio.selectVioInfo(hpzl, hphm, start, end);
        }

        return (result != null && result.size() > 0) ? new Response().success(result) : new Response().failure("获取信息为空");
    }

    //获取驾驶人违法信息列表
    @Override
    public Response getVioDrvList(BaseRequest baseRequest, HttpServletRequest hrequest) throws Exception {
        String querydoc = baseRequest.getQuerydoc();
        if (StringUtil.isEmpty(querydoc)) return new Response().failure("查询条件为空");
        Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
        String sfzmhm = (String) map.get("sfzmhm");
        loggerService.writeOperaLogger(baseRequest.getUser(), 5, sfzmhm, baseRequest.getWym());
        //获取页码和页数大小
        int page = getPage(map);
        int pageSize = getPageSize(map);
        int start = (page - 1) * pageSize;
        int end = page * pageSize;
        //判断最近一天内有无存储该驾驶人违法信息
        Date date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        List<Q16Return> result = vio.selectVioDriByDate(sfzmhm, date, start, end);
        if (result != null && result.size() > 0) {
            return new Response().success(result);
        } else {//判断是否查询第一页
            if (page != 1) {//判断第一页有无数据
                result = vio.selectVioDriByDate(sfzmhm, date, 0, pageSize);
                if (result != null && result.size() > 0) {
                    //第一页有数据
                    return new Response().failure("数据不超过" + end + "条");
                }
            }
        }
        //最近一天内未存储该信息，删除过期数据，去综合平台查询
        //调取综合平台
        boolean bool = getVioDriFromZHPT(sfzmhm, hrequest);
        if (!bool) {
            result = vio.selectVioDriByDate(sfzmhm, null, start, end);
        } else {
            vio.deleteVioInfoByJSZH(date, sfzmhm);
            result = vio.selectVioDriByDate(sfzmhm, date, start, end);
        }

        return (result != null) ? new Response().success(result) : new Response().failure("查询结果为空");
    }

    //获取违法详细信息
    @Override
    public Response getVioInfo(BaseRequest baseRequest, HttpServletRequest request) throws Exception {
        String querydoc = baseRequest.getQuerydoc();
        if (StringUtil.isEmpty(querydoc)) return new Response().failure("查询条件为空");
        Map<String, String> map = (Map<String, String>) JSON.parse(querydoc);

        //判断是查询机动车还是驾驶人详情
        String cxly = map.get("cxly");
        String xh=map.get("xh");
        if (cxly.equals("0")) {//查询驾驶人,这里的序号对应着违法表中的id
            Q14Return result = vio.selectVioById(xh);
            return (result != null) ? new Response().success(result) : new Response().failure("查询驾驶人违法信息为空");
        } else if (cxly.equals("1")) {//查询机动车，这里的序号对应违法表中的机动车序号
            Q14Return result = vio.selectVioByXH(xh);
            if (result == null) return new Response().failure("查询信息为空");
            //当查询信息不为空时，先判断本地库中是否存有该照片信息，如果有则直接返回照片路劲，若没有则调综合平台
            List<String> urls = vio.selectZpUrl(result.getId());
            if (urls != null && urls.size() > 0) {
                result.setZps(urls);//将照片信息添加进去
                return new Response().success(result);
            }
            //综合平台调取违法机动车照片信息,并返回
            List<String> zps=null;
            //机动车违停xh是16位，简易处罚书用的是id 32位
            if (xh.length()<20){
                zps= getVioZpFromZHPT(xh, result, request);
            }
            if (zps == null) {
                zps = new ArrayList<>();
            }
            result.setZps(zps);
            return new Response().success(result);
        } else
            return new Response().failure("查询来源输入有误，0为驾驶人，1为机动车");


    }

    //获取违法代码列表
    @Override
    public Response getVioCodeList(String querydoc) throws Exception {

        if (StringUtil.isEmpty(querydoc)) return new Response().failure("查询条件为空");
        Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);

        //查询违法代码总条数
        int xxts = vio.selectXXTS(map);
        if (xxts == 0) return new Response().failure("违法代码信息条数为0");
        //获取页码和页数大小
        int page = getPage(map);
        int pageSize = getPageSize(map);
        int start = (page - 1) * pageSize;
        int end = page * pageSize;

        List<Q23Return> result = vio.selectVioCodeList(map, start, end);
        if (result != null && result.size() > 0) {
            Q23ReturnExtend res = new Q23ReturnExtend();
            res.setList(result);
            res.setXxts(xxts);
            return new Response().success(res);
        } else return new Response().failure("查询信息为空");

        //result为空，最近一小时无信息，则先删除数据库中的违法代码信息，然后重新从综合平台上下载
            /*vio.deleteVioCode();
            //调取综合平台
            boolean bool = getVioCodeFromZHPT();
            if (!bool)return new Response().failure("调取综合平台查询违法代码写入本地库失败");
            result = vio.selectVioCodeList(map.get("wfdm"),map.get("wfnr"),start,end,null);
            return (result != null)?new Response().success(result):new Response().failure("违法代码查询为空");*/
    }


    /**
     * //获取违法代码详情
     *
     * @param baseRequest
     * @return
     * @throws Exception
     */
    @Override
    public Response getVioCodeInfo(BaseRequest baseRequest) throws Exception {
        String querydoc = baseRequest.getQuerydoc();
        if (StringUtil.isEmpty(querydoc)) return new Response().failure("查询条件为空");
        Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
        Q24Return result = vio.selectVioCodeInfo((String) map.get("wfdm"));
        loggerService.writeOperaLogger(baseRequest.getUser(), 18, (String) map.get("wfdm"), baseRequest.getWym());
        return (result != null) ? new Response().success(result) : new Response().failure("获取违法代码详情为空");
    }

    /**
     * 综合平台调取机动车违法信息并写入本地库
     *
     * @param hpzl
     * @param hphm
     * @param hrequest
     * @return
     * @throws Exception
     */

    public boolean getVioInfoFromZHPT(String hpzl, String hphm, HttpServletRequest hrequest) throws Exception {
        if (CacheMap.isTest) {
            return false;
        }

        TmriJaxRpcOutNewAccessService service = new TmriJaxRpcOutNewAccessServiceLocator();
        TmriJaxRpcOutNewAccess soap = service.getTmriOutNewAccess();
        String serverName = hrequest.getServerName();
        //调用04C03接口获取电子监控的车辆违法信息
        WebResponse webResponse_03 = getVioInfoFromZHPT_C03(hphm, hpzl, soap, serverName);
        //调用04C06接口获取简易处罚书的车辆违法信息
        WebResponse webResponse_06 = getVioInfoFromZHPT_C06(hphm, hpzl, soap, serverName);
        return webResponse_03.isSuccess() || webResponse_06.isSuccess();

    }

    /**
     * 调用04C06接口获取简易处罚书的车辆违法信息
     *
     * @param hphm
     * @param hpzl
     * @param soap
     * @param serverName
     */
    private WebResponse getVioInfoFromZHPT_C06(String hphm, String hpzl, TmriJaxRpcOutNewAccess soap, String serverName) {
        //条件查询xmldoc
        String xmlResult = "";
        String xmldoc = "";
        WebResponse webResponse = new WebResponse();
        try {
            xmldoc = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "\n" +
                    "<root> \n" +
                    "  <QueryCondition> \n" +
                    "    <hphm>" + URLEncoder.encode(hphm, "utf-8") + "</hphm>  \n" +
                    "    <hpzl>" + hpzl + "</hpzl> \n" +
                    "  </QueryCondition> \n" +
                    "</root>";
            //获取场景编号
            Map<String, String> m = libSysParam.getCJBHMap();
            String cjsqbh = "";
            if (m != null) {
                boolean b = m.containsKey("04C06");
                cjsqbh = (b == true) ? m.get("04C06").trim() : "";
            }
            String xml = soap.queryObjectOutNew("04", libSysParam.getZHPTJKXLH(), "04C06", cjsqbh, "", "", "", "", serverName, xmldoc);
            xmlResult = URLDecoder.decode(xml, "utf-8");

            //解析xml
            if (StringUtil.isEmpty(xmlResult)) {
                webResponse.setSuccess(false);
                webResponse.setMessage("未能返回数据");
            } else {
                WebRoot result = XmlUtil.toObj(xmlResult, WebRoot.class);
                if (result != null && "1".equals(result.getHead().getCode())) {
                    //循环遍历添加值
                    List<BizVioInfo> vioInfos = result.getBody().getBizVioInfoList();
                    if (vioInfos == null || vioInfos.size() < 1) {
                        webResponse.setSuccess(false);
                        webResponse.setMessage("未能查询到数据");
                    } else {
                        for (int i = 0; i < vioInfos.size(); i++) {
                          String uuid=  UUID.randomUUID().toString();
                            vioInfos.get(i).setId(uuid);
                            vioInfos.get(i).setXh(uuid);
                            vioInfos.get(i).setCjsj(new Date());
                            vioInfos.get(i).setCxly("1");
                            vioInfos.get(i).setClbj("1");
                        }
                        //将信息写入本地库
                        int num = vio.insertVioInfoList(vioInfos);
                    }
                } else {
                    webResponse.setSuccess(false);
                    webResponse.setMessage(result == null ? "返回数据有误" : result.getHead().getMessage());
                }
            }
            if (webResponse.isSuccess()) {
                loggerService.writeWebLogger(xmldoc, xmlResult, "04C06");
            } else {
                loggerService.writeWebLogger(xmldoc, xmlResult, webResponse.getMessage(), "04C06");
            }

        } catch (Exception e) {
            loggerService.writeWebLogger(xmldoc, xmlResult, e.getMessage(), "04C06");
            webResponse.setSuccess(false);
            webResponse.setMessage(e.getMessage());
        }
        return webResponse;
    }

    /**
     * 调用04C03接口获取电子监控的车辆违法信息
     *
     * @param hphm
     * @param hpzl
     * @param soap
     * @param serverName
     */
    private WebResponse getVioInfoFromZHPT_C03(String hphm, String hpzl, TmriJaxRpcOutNewAccess soap, String serverName) {
        //条件查询xmldoc
        String xmlResult = "";
        String xmldoc = "";
        WebResponse webResponse = new WebResponse();
        try {
            xmldoc = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "\n" +
                    "<root> \n" +
                    "  <QueryCondition> \n" +
                    "    <hphm>" + URLEncoder.encode(hphm, "utf-8") + "</hphm>  \n" +
                    "    <hpzl>" + hpzl + "</hpzl> \n" +
                    "<xh></xh>\n<clbj></clbj>\n" +
                    "  </QueryCondition> \n" +
                    "</root>";
            //获取场景编号
            Map<String, String> m = libSysParam.getCJBHMap();
            String cjsqbh = "";
            if (m != null) {
                boolean b = m.containsKey("04C03");
                cjsqbh = (b == true) ? m.get("04C03").trim() : "";
            }
            String xml = soap.queryObjectOutNew("04", libSysParam.getZHPTJKXLH(), "04C03", cjsqbh, "", "", "", "", serverName, xmldoc);
            xmlResult = URLDecoder.decode(xml, "utf-8");

            //解析xml
            if (StringUtil.isEmpty(xmlResult)) {
                webResponse.setSuccess(false);
                webResponse.setMessage("未能返回数据");
            } else {
                WebRoot result = XmlUtil.toObj(xmlResult, WebRoot.class);
                if (result != null && "1".equals(result.getHead().getCode())) {
                    //循环遍历添加值
                    List<BizVioInfo> vioInfos = result.getBody().getVioInfo();
                    if (vioInfos == null || vioInfos.size() < 1) {
                        webResponse.setSuccess(false);
                        webResponse.setMessage("未能查询到数据");
                    } else {
                        for (int i = 0; i < vioInfos.size(); i++) {
                            vioInfos.get(i).setId(UUID.randomUUID().toString());
                            vioInfos.get(i).setCjsj(new Date());
                            vioInfos.get(i).setCxly("1");
                            System.out.println(vioInfos.get(i));
                        }

                        //将信息写入本地库
                        int num = vio.insertVioInfoList(vioInfos);
                    }
                } else {
                    webResponse.setSuccess(false);
                    webResponse.setMessage(result == null ? "返回数据有误" : result.getHead().getMessage());
                }
            }
            if (webResponse.isSuccess()) {
                loggerService.writeWebLogger(xmldoc, xmlResult, "04C03");
            } else {
                loggerService.writeWebLogger(xmldoc, xmlResult, webResponse.getMessage(), "04C03");
            }

        } catch (Exception e) {
            loggerService.writeWebLogger(xmldoc, xmlResult, e.getMessage(), "04C03");
            webResponse.setSuccess(false);
            webResponse.setMessage(e.getMessage());
        }
        return webResponse;
    }

    /**
     * //综合平台查询驾驶人违法信息
     *
     * @param sfzmhm
     * @param hrequest
     * @return
     * @throws Exception
     */
    public boolean getVioDriFromZHPT(String sfzmhm, HttpServletRequest hrequest) throws Exception {
        if (CacheMap.isTest) {
            return false;
        }
        TmriJaxRpcOutNewAccessService service = new TmriJaxRpcOutNewAccessServiceLocator();
        TmriJaxRpcOutNewAccess soap = service.getTmriOutNewAccess();
        //条件查询xmldoc
        String xmldoc = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<root> \n" +
                "  <QueryCondition> \n" +
                "    <jszh>" + sfzmhm + "</jszh>  \n" +
                "<jdsbh></jdsbh>\n<wfbh></wfbh>\n<dabh></dabh>\n<jkbj></jkbj>" +
                "  </QueryCondition> \n" +
                "</root>";
        //获取场景编号
        Map<String, String> m = libSysParam.getCJBHMap();
        String cjsqbh = "";
        if (m != null) {
            boolean b = m.containsKey("04C01");
            cjsqbh = (b == true) ? m.get("04C01").trim() : "";
        }
//        long date1=new Date().getTime();
        String xmlResult = soap.queryObjectOutNew("04", libSysParam.getZHPTJKXLH(), "04C01", cjsqbh, "", "", "", "", hrequest.getServerName(), xmldoc);
        xmlResult = URLDecoder.decode(xmlResult, "utf-8");
        loggerService.writeWebLogger(xmldoc, xmlResult, "04C01");
        //解析xml
        if (StringUtil.isEmpty(xmlResult)) return false;

        WebRoot result = XmlUtil.toObj(xmlResult, WebRoot.class);
        if (result == null || result.getHead() == null || !"1".equals(result.getHead().getCode())) {
            return false;
        }

        //循环遍历添加值
        List<BizVioInfo> vioInfos = result.getBody().getVioDrvInfo();
        if (vioInfos == null || vioInfos.size() < 1) {
            return false;
        }
        for (int i = 0; i < vioInfos.size(); i++) {
            vioInfos.get(i).setId(UUID.randomUUID().toString());
            vioInfos.get(i).setCjsj(new Date());
            vioInfos.get(i).setCxly("0");
        }
        //将信息写入本地库
        int num = vio.insertVioInfoList(vioInfos);
        return (num != 0) ? true : false;
    }

    //调取综合平台获取机动车违法照片信息//这里的id作为将照片插入本地库照片表的关联id
    public List<String> getVioZpFromZHPT(String xh, Q14Return result, HttpServletRequest hrequest) throws Exception {
        if (CacheMap.isTest) {
            return new ArrayList<>();
        }
        //将Base64转换成流
        BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        //做批量插入的照片集合
        List<BizPhotoInfo> zplist = new ArrayList<>();
        //返回照片路径的路径集合
        List<String> urls = new ArrayList<>();
        TmriJaxRpcOutNewAccessService service = new TmriJaxRpcOutNewAccessServiceLocator();
        TmriJaxRpcOutNewAccess soap = service.getTmriOutNewAccess();
        //条件查询xmldoc
        String xmldoc = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<root> \n" +
                "  <QueryCondition> \n" +
                "    <xh>" + xh + "</xh>  \n" +
                "  </QueryCondition> \n" +
                "</root>";
        Map<String, String> m = libSysParam.getCJBHMap();
        String cjsqbh = "";
        if (m != null) {
            boolean b = m.containsKey("04C04");
            cjsqbh = (b == true) ? m.get("04C04").trim() : "";
        }
        String xmlResult = soap.queryObjectOutNew("04", libSysParam.getZHPTJKXLH(), "04C04", cjsqbh, "", "", "", "", hrequest.getServerName(), xmldoc);
        xmlResult = URLDecoder.decode(xmlResult, "utf-8");
        loggerService.writeWebLogger(xmldoc, xmlResult, "04C04");
        //解析xml
        if (StringUtil.isNotEmpty(xmlResult)) {
            WebRoot res = XmlUtil.toObj(xmlResult, WebRoot.class);
            if (res == null || !"1".equals(res.getHead().getCode())) {
                return null;
            } else {
                VioZps zps = res.getBody().getVioZps();
                if (zps != null) {
                    //生成照片的路径
                    String contextPath = hrequest.getContextPath();
                    String basePath = hrequest.getScheme() + "://" + hrequest.getServerName() + ":" +
                            hrequest.getServerPort() + contextPath + "/getPhotos?" + "id=";

                    //照片1
                    if (StringUtil.isNotEmpty(zps.getZpstr1())) {
                        BizPhotoInfo photo1 = new BizPhotoInfo();
                        byte[] zp1 = decoder.decodeBuffer(zps.getZpstr1());
                        photo1.setZp(zp1);
                        photo1.setGlid(result.getId());
                        photo1.setPssj(zps.getLrsj());
                        String uuid = UUID.randomUUID().toString();
                        photo1.setId(uuid);
                        photo1.setSx1(basePath + uuid);
                        photo1.setZpzl("7005");
                        photo1.setPsry("综合平台");
                        zplist.add(photo1);
                        urls.add(basePath + uuid);
                    }

                    //照片2
                    if (StringUtil.isNotEmpty(zps.getZpstr2())) {
                        BizPhotoInfo photo2 = new BizPhotoInfo();
                        byte[] zp2 = decoder.decodeBuffer(zps.getZpstr2());
                        photo2.setZp(zp2);
                        photo2.setGlid(result.getId());
                        photo2.setPssj(zps.getLrsj());
                        String uuid = UUID.randomUUID().toString();
                        photo2.setId(uuid);
                        photo2.setSx1(basePath + uuid);
                        photo2.setZpzl("7005");
                        photo2.setPsry("综合平台");
                        zplist.add(photo2);
                        urls.add(basePath + uuid);
                    }

                    //照片3
                    if (StringUtil.isNotEmpty(zps.getZpstr3())) {
                        BizPhotoInfo photo3 = new BizPhotoInfo();
                        byte[] zp3 = decoder.decodeBuffer(zps.getZpstr3());
                        photo3.setZp(zp3);
                        photo3.setGlid(result.getId());
                        photo3.setPssj(zps.getLrsj());
                        String uuid = UUID.randomUUID().toString();
                        photo3.setId(uuid);
                        photo3.setSx1(basePath + uuid);
                        photo3.setZpzl("7005");
                        photo3.setPsry("综合平台");
                        zplist.add(photo3);
                        urls.add(basePath + uuid);
                    }

                    int num = vio.insertVioZps(zplist);
                    if (num != 0) {//插入照片成功,将照片路径集合写入result中并返回
                        return urls;
                    } else {
                        return null;
                    }
                }
            }
        }

        return null;


    }


    //页码转数据类型
    public int getPage(Map<String, Object> map) throws Exception {
        if (map.get("page") instanceof String) return Integer.parseInt((String) map.get("page"));
        if (map.get("page") instanceof Integer) return (int) map.get("page");
        return 0;
    }

    public int getPageSize(Map<String, Object> map) throws Exception {
        if (map.get("pageSize") instanceof String) return Integer.parseInt((String) map.get("pageSize"));
        if (map.get("pageSize") instanceof Integer) return (int) map.get("pageSize");
        return 0;
    }
}
