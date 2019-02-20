package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.biz.BizPhotoInfo;
import com.scxd.beans.biz.Q19Return;
import com.scxd.beans.biz.Q22Return;
import com.scxd.beans.common.ListTotal;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.pdaBeans.Root;
import com.scxd.beans.pojo.BizVioSurveBean;
import com.scxd.common.*;
import com.scxd.dao.mapper.BizVioSurveMapper;
import com.scxd.dao.mapper.PhotoDao;
import com.scxd.service.VioSurveService;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VioSureServiceImpl implements VioSurveService {

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private BizVioSurveMapper vio;
    @Resource
    private LibSysParam libSysParam;
    @Autowired
    private PhotoDao photo;
    @Resource
    private LoggerService loggerService;

    /**
     * 获取违法电子监控列表
     *
     * @param querydoc
     * @return
     */
    @Override
    public Response getVioSurveList(String querydoc) throws Exception {

        Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
        //将字符串的日期数据转换为Date数据类型
        String ksrqStr = (String) map.get("ksrq");
        String jsrqStr = (String) map.get("jsrq");
        String zqmj = (String) map.get("user");
        Date ksrq = format.parse(ksrqStr);
        Date jsrq = format.parse(jsrqStr);
        jsrq = new Date(jsrq.getTime() + (24 * 60 * 60 * 1000));
        //分页处理根据每页大小和页码计算出查询内容在ORACLE数据库中的位置
        int page = getPage(map);
        int pageSize = getPageSize(map);
        int start = (page - 1) * pageSize;
        int end = page * pageSize;
        //查询列表清单
        int total=vio.getSurveTotal(ksrq, jsrq, start, end, zqmj);
        if (total<1){
            return new Response().failure("未能查到更多数据");
        }

        List<Q19Return> result = vio.selectVioSurveList(ksrq, jsrq, start, end, zqmj);
        ListTotal listTotal=new ListTotal();
        listTotal.setList(result);
        listTotal.setTotal(total);
        return (result != null && result.size() > 0) ? new Response().success(listTotal) : new Response().failure("获取电子监控信息为空");
    }

    /**
     * 获取违法电子监控详情
     *
     * @param querydoc
     * @return
     */
    @Override
    public Response getVioSure(String querydoc) throws Exception {

        Map<String, String> map = (Map<String, String>) JSON.parse(querydoc);
        Q22Return result = vio.selectVioSurveByJdsbh(map.get("jdsbh"));
        if (result == null) return new Response().failure("查询信息为空");
        //查询照片
        List<String> zps = vio.selectZPS(map.get("jdsbh"));
        if (zps != null && zps.size() > 0) result.setZps(zps);
        return new Response().success(result);

    }

    /**
     * 电子监控信息录入
     */
    @Override
    public Response writeVioSure(BaseRequest baseRequest, HttpServletRequest request) throws Exception {
        String writedoc=baseRequest.getWritedoc();
        Response response = null;
        if (StringUtil.isNotEmpty(writedoc)) {
            BizVioSurveBean bizVioSurveBean = JSONUtil.parseObject(writedoc, BizVioSurveBean.class);
            if (bizVioSurveBean != null) {
                String id = bizVioSurveBean.getId();
                String tzsh = getIdForSure(bizVioSurveBean.getBmbh());
                loggerService.writeOperaLogger(baseRequest.getUser(),15,tzsh,baseRequest.getWym());
                bizVioSurveBean.setTzsh(tzsh);
                if (bizVioSurveBean.getTzrq()==null){
                    bizVioSurveBean.setTzrq(new Date());
                }
                //查询照片
                List<BizPhotoInfo> bizPhotoInfoList = photo.selectZPByglidAndZpzl(id,"7006");
                if (bizPhotoInfoList == null || bizPhotoInfoList.size() == 0) {
                    return new Response().failure("请至少上传一张照片");
                } else {
                    int num = vio.insertSelective(bizVioSurveBean);
                    if (num > 0) {
                        WebResponse webResponse = writeVioSureToWEB(bizVioSurveBean, bizPhotoInfoList, request);
                        if (webResponse.isSuccess()) {
                            Map map1 = new HashMap();
                            map1.put("zp", "");
                            map1.put("jdsbh", tzsh);
                            return new Response().success(map1);
                        } else {
                            vio.deleteVioSure(id);
                            return new Response().failure(webResponse.getMessage());
                        }
                    } else {
                        response = new Response().failure("写入失败");
                    }
                }
            } else {
                response = new Response().failure("写入数据不正确");
            }
        } else {
            response = new Response().failure("写入数据为空");
        }
        return response;

    }

    /**
     * 上传电子监控信息到综合平台
     *
     * @param bizVioSurveBean
     * @param bizPhotoInfoList
     * @return
     */
    private WebResponse writeVioSureToWEB(BizVioSurveBean bizVioSurveBean, List<BizPhotoInfo> bizPhotoInfoList, HttpServletRequest request) throws Exception {
        WebResponse webResponse = new WebResponse();
        /**
         * 写入综合平台
         */
        TmriJaxRpcOutNewAccessService service = new TmriJaxRpcOutNewAccessServiceLocator();
        TmriJaxRpcOutNewAccess soap = service.getTmriOutNewAccess();
        //组装xmldoc
        String xmldoc = getXmldoc(bizVioSurveBean, bizPhotoInfoList);
        //接口序列号
        String jkxlh = libSysParam.getZHPTJKXLH();
        if (CacheMap.isTest){
            return  webResponse;
        }
        //场景编号
        String cjsqbh = "";
        Map<String, String> mab = libSysParam.getCJBHMap();
        if (mab != null) {
            boolean b = mab.containsKey("04C53");
            cjsqbh = (b == true) ? mab.get("04C53").trim() : "";
        }
        //调用综合平台写入接口
        String result = soap.writeObjectOutNew("04", jkxlh, "04C53", cjsqbh, "", "", "", "", request.getServerName(), xmldoc);
        result = URLDecoder.decode(result, "utf-8");
        loggerService.writeWebLogger(xmldoc, result, "04C53");
        Root root = null;
        if (StringUtil.isNotEmpty(result)) {
            root = XmlUtil.toObj(result, Root.class);
            if (root == null || root.getHead() == null) {
                webResponse.setSuccess(false);
                webResponse.setMessage("写入综合平台失败");
            } else if (!"1".equals(root.getHead().getCode())) {
                webResponse.setSuccess(false);
                webResponse.setMessage(root.getHead().getMessage());
            }
        } else {
            webResponse.setSuccess(false);
            webResponse.setMessage("写入综合平台失败");
        }

        return webResponse;
    }

    /**
     * 电子监控信息写入本地库id生成方法
     * map是PDA传入的JSON数据转换的，这里取出生成ID需要的参数（此处默认需要的参数是部门编号）
     * 先查询表中最后一条的数据编号，在此基础上+1
     */
    public synchronized String getIdForSure(String bmbh) throws Exception {
        String numtext = String.format("%08d", Integer.valueOf(vio.getSurveDocumentBH()));
        String tzsh = bmbh.substring(0, 6) + "7" + numtext;

        return tzsh;
    }


    /**
     * 写入电子监控xml组装
     */
    public String getXmldoc(BizVioSurveBean bizVioSurveBean, List<BizPhotoInfo> bizPhotoInfoList) throws Exception {
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        stringBuilder.append("<root>\n" +
                "<viosurveil>");
        stringBuilder.append("<xh></xh>");//*
        stringBuilder.append("<cjjg>" + StringEx.nullToStrUtf(bizVioSurveBean.getCjjg()) + "</cjjg>");//*
        stringBuilder.append("<clfl>" + StringEx.nullToStrUtf(bizVioSurveBean.getClfl()) + "</clfl>");//*
        stringBuilder.append("<hpzl>" + StringEx.nullToStrUtf(bizVioSurveBean.getHpzl()) + "</hpzl>");//*
        stringBuilder.append("<hphm>" + StringEx.nullToStrUtf(bizVioSurveBean.getHphm()) + "</hphm>");//*
        stringBuilder.append("<jdcsyr>" + StringEx.nullToStrUtf(bizVioSurveBean.getJdcsyr()) + "</jdcsyr>");//*
        stringBuilder.append("<syxz>" + StringEx.nullToStrUtf(bizVioSurveBean.getSyxz()) + "</syxz>");//*
        stringBuilder.append("<fdjh>" + StringEx.nullToStrUtf(bizVioSurveBean.getFdjh()) + "</fdjh>");
        stringBuilder.append("<clsbdh>" + StringEx.nullToStrUtf(bizVioSurveBean.getClsbdh()) + "</clsbdh>");
        stringBuilder.append("<csys>" + StringEx.nullToStrUtf(bizVioSurveBean.getCsys()) + "</csys>");
        stringBuilder.append("<clpp>" + StringEx.nullToStrUtf(bizVioSurveBean.getClpp()) + "</clpp>");
        stringBuilder.append("<jtfs>" + StringEx.nullToStrUtf(bizVioSurveBean.getJtfs()) + "</jtfs>");//*
        stringBuilder.append("<fzjg>" + StringEx.nullToStrUtf(bizVioSurveBean.getFzjg()) + "</fzjg>");//*
        stringBuilder.append("<zsxzqh>" + StringEx.nullToStrUtf(bizVioSurveBean.getZsxzqh()) + "</zsxzqh>");//*
        stringBuilder.append("<zsxxdz>" + StringEx.nullToStrUtf(bizVioSurveBean.getZsxxdz()) + "</zsxxdz>");
        stringBuilder.append("<dh>" + StringEx.nullToStrUtf(bizVioSurveBean.getDh()) + "</dh>");
        stringBuilder.append("<tzsh>" + StringEx.nullToStrUtf(bizVioSurveBean.getTzsh()) + "</tzsh>");//*
        stringBuilder.append("<tzrq>" + StringEx.nullToStrUtf(DateUtils.dateToStrDay(bizVioSurveBean.getTzrq())) + "</tzrq>");//*
        stringBuilder.append("<cjfs>" + StringEx.nullToStrUtf(bizVioSurveBean.getCjfs()) + "</cjfs>");//*
        stringBuilder.append("<wfsj>" + StringEx.nullToStrUtf(DateUtils.dateToStrMI(bizVioSurveBean.getWfsj())) + "</wfsj>");//*
        stringBuilder.append("<xzqh>" + StringEx.nullToStrUtf(bizVioSurveBean.getXzqh()) + "</xzqh>");//*
        stringBuilder.append("<wfdd>" + StringEx.nullToStrUtf(bizVioSurveBean.getWfdd()) + "</wfdd>");
        stringBuilder.append("<lddm>" + StringEx.nullToStrUtf(bizVioSurveBean.getLddm()) + "</lddm>");//*
        stringBuilder.append("<ddms>" + StringEx.nullToStrUtf(bizVioSurveBean.getDdms()) + "</ddms>");//*
        stringBuilder.append("<wfxw>" + StringEx.nullToStrUtf(bizVioSurveBean.getWfxw()) + "</wfxw>");//*
        stringBuilder.append("<scz>" + StringEx.nullToStrUtf(bizVioSurveBean.getScz()) + "</scz>");
        stringBuilder.append("<bzz>" + StringEx.nullToStrUtf(bizVioSurveBean.getBzz()) + "</bzz>");
        stringBuilder.append("<zqmj>" + StringEx.nullToStrUtf(bizVioSurveBean.getZqmj()) + "</zqmj>");//*
        stringBuilder.append("<spdz></spdz>");//*
        stringBuilder.append("<sbbh>" + StringEx.nullToStrUtf(bizVioSurveBean.getSbbh()) + "</sbbh>");//*
        int size = bizPhotoInfoList.size();
        for (int i = 0; i < size; i++) {
            byte[] photo=bizPhotoInfoList.get(i).getZp();
            String encoded = Base64.getEncoder().encodeToString(photo);
            try {
                stringBuilder.append("<zpstr1>" + URLEncoder.encode(encoded, "utf-8") + "</zpstr1>");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (size == 1) {
            stringBuilder.append("<zpstr1></zpstr1>");
            stringBuilder.append("<zpstr1></zpstr1>");
        } else if (size == 2) {
            stringBuilder.append("<zpstr1></zpstr1>");
        }
        stringBuilder.append("</viosurveil>\n" +
                "</root>");
        return stringBuilder.toString();

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

    //判断map中是否存在key和key所对应的值
    public boolean checkMapkey(Map map, String key) throws Exception {
        boolean b = map.containsKey(key);
        if (!b) return false;
        Object o = map.get(key);
        if (o.equals("")) return false;
        return true;
    }

}
