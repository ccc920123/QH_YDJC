package com.scxd.service.impl;

import com.scxd.beans.biz.BizVioInfo;
import com.scxd.beans.biz.Wfxwlist;
import com.scxd.beans.common.ListTotal;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.pdaBeans.Root;
import com.scxd.beans.pdaBeans.response.Q17ReturnBean;
import com.scxd.beans.pdaBeans.response.Q18ReturnBean;
import com.scxd.beans.pdaBeans.response.Q20ReturnBean;
import com.scxd.beans.pdaBeans.response.Q21ReturnBean;
import com.scxd.beans.pojo.BizVioForce;
import com.scxd.beans.pojo.BizVioViolation;
import com.scxd.beans.webBeans.WebForceResponseBean;
import com.scxd.beans.webBeans.WebRoot;
import com.scxd.common.*;
import com.scxd.dao.mapper.*;
import com.scxd.service.DocumentService;
import com.scxd.service.common.CacheMap;
import com.scxd.service.common.LibSysParam;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccess;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccessService;
import com.scxd.webzhpt.TmriJaxRpcOutNewAccessServiceLocator;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.util.*;

/**
 * @Auther:陈攀
 * @Description:文书处理接口
 * @Date:Created in 11:27 2018/7/5
 * @Modified By:
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Resource
    BizVioViolationMapper bizVioViolationMapper;
    @Resource
    BizVioForceMapper bizVioForceMapper;
    @Resource
    LibSysParam libSysParam;
    @Resource
    PhotoDao photoDao;
    @Resource
    BizVioSurveMapper bizVioSurveMapper;
    @Resource
    LoggerService loggerService;
    @Autowired
    BizVioInfoMapper vioInfoMapper;
    @Autowired
    SysDepartmentParamMapper sysDepartmentParamMapper;

    /**
     * 获取简易文书列表
     *
     * @param querydoc
     * @return
     * @throws Exception
     */
    @Override
    public Response getSimDocument(String querydoc) throws Exception {
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
            int page = Integer.valueOf(String.valueOf(map.get("page")));
            int pageSize = Integer.valueOf(String.valueOf(map.get("pageSize")));
            map.put("pagefirst", (page - 1) * pageSize + 1);
            map.put("pageend", page * pageSize);
            map.put("ksrq", DateUtils.strToDateORNULLDAY(map.get("ksrq").toString()));
            map.put("jsrq", DateUtils.strToDateORNULLDAY2(map.get("jsrq").toString()));
            int total = bizVioViolationMapper.getSimDocTotal(map);
            if (total < 1) {
                return new Response().failure("未能查询到信息");
            }
            List<Q17ReturnBean> list = bizVioViolationMapper.getSimDocument(map);
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

    /**
     * 获取强制措施列表
     *
     * @param querydoc
     * @return
     * @throws Exception
     */
    @Override
    public Response getForceDocument(String querydoc) throws Exception {
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
            int page = Integer.valueOf(String.valueOf(map.get("page")));
            int pageSize = Integer.valueOf(String.valueOf(map.get("pageSize")));
            map.put("pagefirst", (page - 1) * pageSize + 1);
            map.put("pageend", page * pageSize);
            map.put("ksrq", DateUtils.strToDateORNULLDAY(map.get("ksrq").toString()));
            map.put("jsrq", DateUtils.strToDateORNULLDAY2(map.get("jsrq").toString()));
            int total = bizVioForceMapper.getForceDocTotal(map);
            if (total < 1) {
                return new Response().failure("未能查询到信息");
            }
            List<Q18ReturnBean> list = bizVioForceMapper.getForceDocument(map);
            if (list != null && list.size() > 0) {
                ListTotal listTotal = new ListTotal();
                listTotal.setList(list);
                listTotal.setTotal(total);
                response = new Response().success(listTotal);
            } else {
                response = new Response().failure("未能查询到信息");
            }
        } else {
            response = new Response().failure("查询条件不完善");
        }
        return response;
    }

    /**
     * 获取简易处罚书详情
     *
     * @param baseRequest
     * @return
     * @throws Exception
     */
    @Override
    public Response getSimDocumentDetail(BaseRequest baseRequest) throws Exception {
        String querydoc = baseRequest.getQuerydoc();
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
            String jdsbh = map.get("jdsbh").toString();
            Q20ReturnBean q20ReturnBean = bizVioViolationMapper.getSimDocumentDetail(jdsbh);

            if (q20ReturnBean != null) {
                q20ReturnBean.setYh(sysDepartmentParamMapper.getCSZByBmbhAndCsdm(q20ReturnBean.getFxjg(), "JKYH"));
                q20ReturnBean.setXzss(sysDepartmentParamMapper.getCSZByBmbhAndCsdm(q20ReturnBean.getFxjg(), "XZSSDW"));
                response = new Response().success(q20ReturnBean);
            } else {
                response = new Response().failure("未能查询到信息");
            }
        } else {
            response = new Response().failure("查询条件不完善");
        }
        return response;
    }

    /**
     * 获取强制措施详情
     *
     * @param querydoc
     * @return
     * @throws Exception
     */
    @Override
    public Response getForceDocumentDetail(String querydoc) throws Exception {
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
            String pzbh = map.get("cfjdsbh").toString();
            Q21ReturnBean q21ReturnBean = bizVioForceMapper.getForceDocumentDetail(pzbh);

            if (q21ReturnBean != null) {
                q21ReturnBean.setXzss(sysDepartmentParamMapper.getCSZByBmbhAndCsdm(q21ReturnBean.getFxjg(), "XZSSDW"));
                q21ReturnBean = getWfxwList(q21ReturnBean);
                response = new Response().success(q21ReturnBean);
            } else {
                response = new Response().failure("未能查询到信息");
            }
        } else {
            response = new Response().failure("查询条件不完善");
        }
        return response;
    }

    /**
     * 为前端拼装wfxwlist
     *
     * @param q21ReturnBean
     * @return
     */
    private Q21ReturnBean getWfxwList(Q21ReturnBean q21ReturnBean) {
        List<Wfxwlist> list = new LinkedList<>();
        if (StringUtil.isNotEmpty(q21ReturnBean.getWfxw1())) {
            Wfxwlist wfxw = new Wfxwlist();
            wfxw.setWfxw(q21ReturnBean.getWfxw1());
            wfxw.setBzz(q21ReturnBean.getBzz1());
            wfxw.setScz(q21ReturnBean.getScz1());
            wfxw.setWfnr(q21ReturnBean.getWfnr1());
            wfxw.setFlyj(q21ReturnBean.getFlyj1());
            list.add(wfxw);
        }
        if (StringUtil.isNotEmpty(q21ReturnBean.getWfxw2())) {
            Wfxwlist wfxw = new Wfxwlist();
            wfxw.setWfxw(q21ReturnBean.getWfxw2());
            wfxw.setBzz(q21ReturnBean.getBzz2());
            wfxw.setScz(q21ReturnBean.getScz2());
            wfxw.setWfnr(q21ReturnBean.getWfnr2());
            wfxw.setFlyj(q21ReturnBean.getFlyj2());
            list.add(wfxw);
        }
        if (StringUtil.isNotEmpty(q21ReturnBean.getWfxw3())) {
            Wfxwlist wfxw = new Wfxwlist();
            wfxw.setWfxw(q21ReturnBean.getWfxw3());
            wfxw.setBzz(q21ReturnBean.getBzz3());
            wfxw.setScz(q21ReturnBean.getScz3());
            wfxw.setWfnr(q21ReturnBean.getWfnr3());
            wfxw.setFlyj(q21ReturnBean.getFlyj3());
            list.add(wfxw);
        }
        if (StringUtil.isNotEmpty(q21ReturnBean.getWfxw4())) {
            Wfxwlist wfxw = new Wfxwlist();
            wfxw.setWfxw(q21ReturnBean.getWfxw4());
            wfxw.setBzz(q21ReturnBean.getBzz4());
            wfxw.setScz(q21ReturnBean.getScz4());
            wfxw.setWfnr(q21ReturnBean.getWfnr4());
            wfxw.setFlyj(q21ReturnBean.getFlyj4());
            list.add(wfxw);
        }
        if (StringUtil.isNotEmpty(q21ReturnBean.getWfxw5())) {
            Wfxwlist wfxw = new Wfxwlist();
            wfxw.setWfxw(q21ReturnBean.getWfxw5());
            wfxw.setBzz(q21ReturnBean.getBzz5());
            wfxw.setScz(q21ReturnBean.getScz5());
            wfxw.setWfnr(q21ReturnBean.getWfnr5());
            wfxw.setFlyj(q21ReturnBean.getFlyj5());
            list.add(wfxw);
        }
        q21ReturnBean.setWfxwlist(list);
        return q21ReturnBean;
    }

    /**
     * 写入简易处罚书
     *
     * @param baseRequest
     * @return
     * @throws Exception 这里上传成功后，写入本地不成功会重复提交
     *                   <p>
     *                   可以先写入本地数据库，生成照片，在提交到综合平台，成功返回，失败回滚
     */
    @Transactional
    @Override
    public Response writeSimDocument(BaseRequest baseRequest, HttpServletRequest request) throws Exception {
        String writedoc = baseRequest.getWritedoc();
        Response response = null;
        if (StringUtil.isNotEmpty(writedoc)) {
            BizVioViolation bizVioViolation = JSONUtil.parseObject(writedoc, BizVioViolation.class);

            if (bizVioViolation != null) {
                //查询本地数据以及校验位
                String jdsbh = bizVioViolation.getJdsbh();
                loggerService.writeOperaLogger(baseRequest.getUser(), 11, jdsbh, baseRequest.getWym());
                String id = UUID.randomUUID().toString();

                bizVioViolation.setJdsbh(jdsbh);
                bizVioViolation.setId(id);
                //设置当前时间为处理时间
                bizVioViolation.setClsj(new Date());
                if (!"1".equals(bizVioViolation.getCfzl()) && !"2".equals(bizVioViolation.getCfzl())) {
                    return new Response().failure("违法类型必须为警告或罚款");
                }
                if ("0".equals(bizVioViolation.getJkbj()) || "9".equals(bizVioViolation.getJkbj())) {
                    bizVioViolation.setJkrq(null);
                }
                if ("1".equals(bizVioViolation.getCfzl())) {
                    bizVioViolation.setJkfs("0");
                }

                TmriJaxRpcOutNewAccessService service = new TmriJaxRpcOutNewAccessServiceLocator();
                TmriJaxRpcOutNewAccess serviceSoap = service.getTmriOutNewAccess();
                Q20ReturnBean q20ReturnBean = bizVioViolationMapper.getSimDocumentDetail(jdsbh);
                if (q20ReturnBean != null) {
                    if (StringUtil.isNotEmpty(q20ReturnBean.getWsjyw())) {
                        Map map = new HashMap<String, String>();
                        map.put("wsjyw", q20ReturnBean.getWsjyw());
                        map.put("jdsbh", jdsbh);
                        return new Response().success(map);
                    } else {
                        String wsjyw = getWsjywAndUpdateTable(jdsbh, serviceSoap, request);
                        if (StringUtil.isNotEmpty(wsjyw)) {
                            Map map = new HashMap<String, String>();
                            map.put("wsjyw", wsjyw);
                            map.put("jdsbh", jdsbh);
                            return new Response().success(map);
                        } else {
                            return new Response().failure("获取文书校验位失败,请重试");
                        }
                    }
                }
                //先写入数据库
                int num = bizVioViolationMapper.insertSelective(bizVioViolation);
                if (num > 0) {
                    //写入成功生成打印照片
                    //  String url = getSimDocumentPhoto(bizVioViolation, request);
                    //返回一个照片url
                    //上传到综合平台
                    String xmlDoc = getSimDocumentXMLDoc(bizVioViolation);
                    WebResponse webResponse =
                            uploadSimDocumentToWEB(xmlDoc, serviceSoap, request);
                    boolean uploadSucceed = webResponse.isSuccess();
                    //  uploadSucceed = true;
                    if (uploadSucceed) {
                        //删除当前驾驶人和机动车违法
                        if (!CacheMap.isTest) {
                            Date date=new Date();
                            vioInfoMapper.deleteVioInfoByCar(date,bizVioViolation.getHphm(), bizVioViolation.getHpzl());
                            vioInfoMapper.deleteVioInfoByJSZH(date,bizVioViolation.getJszh());
                        }
                        String wsjyw = getWsjywAndUpdateTable(jdsbh, serviceSoap, request);
                        if (StringUtil.isNotEmpty(wsjyw)) {
                            Map map = new HashMap<String, String>();
                            map.put("wsjyw", wsjyw);
                            map.put("jdsbh", jdsbh);
                            response = new Response().success(map);
                        } else {
                            response = new Response().failure("获取文书校验位失败,请重试!");
                        }
                    } else {
                        bizVioViolationMapper.deleteByBizVioValation(id);
                        // photoDao.deleteByUrl(url);
                        response = new Response().failure(webResponse.getMessage());
                    }

                } else {
                    response = new Response().failure("写入失败");
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
     * 调综合平台获取检验位并更新表中的jyw
     *
     * @param jdsbh
     * @param serviceSoap
     * @param request
     * @throws UnsupportedEncodingException
     * @throws RemoteException
     */
    private String getWsjywAndUpdateTable(String jdsbh, TmriJaxRpcOutNewAccess serviceSoap, HttpServletRequest request) throws UnsupportedEncodingException, RemoteException {

        String wsjyw = "";
        if (CacheMap.isTest) {
            wsjyw = "5";
            bizVioViolationMapper.updateWsjywByJdsbh(jdsbh, wsjyw);
            return wsjyw;
        }
        BizVioInfo bizVioInfo = getSimWfxxFromWEB(jdsbh, serviceSoap, request);

        if (bizVioInfo != null) {
            wsjyw = bizVioInfo.getWsjyw();
            //跟新表中的校验位
            bizVioViolationMapper.updateWsjywByJdsbh(jdsbh, wsjyw);
        }
        return wsjyw;
    }

    /**
     * 根据当前文书编号获取上传的建议处罚书信息,最重要的是获取文书检验位
     *
     * @param jdsbh
     * @param serviceSoap
     * @param hrequest
     */
    private BizVioInfo getSimWfxxFromWEB(String jdsbh, TmriJaxRpcOutNewAccess serviceSoap, HttpServletRequest hrequest) throws UnsupportedEncodingException, RemoteException {

        BizVioInfo bizVioInfo = null;
        //条件查询xmldoc
        String xmldoc = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<root> \n" +
                "  <QueryCondition> \n" +
                "    <jszh></jszh>  \n" +
                "<jdsbh>" + jdsbh + "</jdsbh>\n<wfbh></wfbh>\n<dabh></dabh>\n<jkbj></jkbj>" +
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
        String xmlResult = serviceSoap.queryObjectOutNew("04", libSysParam.getZHPTJKXLH(), "04C01", cjsqbh, "", "", "", "", hrequest.getServerName(), xmldoc);
        xmlResult = URLDecoder.decode(xmlResult, "utf-8");
        loggerService.writeWebLogger(xmldoc, xmlResult, "04C01");
        //解析xml
        if (StringUtil.isEmpty(xmlResult)) {
            return null;
        }

        WebRoot result = XmlUtil.toObj(xmlResult, WebRoot.class);
        if (result == null || result.getHead() == null || !"1".equals(result.getHead().getCode())) {
            return null;
        }
        List<BizVioInfo> vioInfos = result.getBody().getVioDrvInfo();
        if (vioInfos == null || vioInfos.size() < 1) {
            return null;
        } else {
            return vioInfos.get(0);
        }

    }


    private String getSimDocumentXMLDoTestc(BizVioViolation bizVioViolation) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root>\n" +
                "<violation>" +
                "<jdsbh>633102100053725</jdsbh>" +
                "<ryfl>4</ryfl>" +//?4
                "<jszh>630102198712280015</jszh>" +
                "<dabh>632100166751</dabh>" +
                "<fzjg>%E9%9D%92B</fzjg>" +
                "<zjcx>C1</zjcx>" +
                "<dsr>%E9%A9%AC%E6%99%93%E4%BA%91</dsr>" +
                "<zsxzqh>632122</zsxzqh>" +
                "<zsxxdz>" + StringEx.nullToStrUtf("青海省海东地区民和回族土族自治县中川乡青一村") + "</zsxxdz>" +
                "<dh>13099764244</dh>" +
                "<lxfs></lxfs>" +
                "<clfl>3</clfl>" +//?3
                "<hpzl>02</hpzl>" +
                "<hphm>%E9%9D%92AHE424</hphm>" +
                "<jdcsyr>%E9%A9%AC%E6%99%93%E4%BA%91</jdcsyr>" +
                "<syxz>A</syxz>" +
                "<jtfs>C01</jtfs>" +
                "<wfsj>2018-08-06 16:17</wfsj>" +
                "<xzqh>633102</xzqh>" +
                "<wfdd>20612</wfdd>" +//
                "<lddm>0000</lddm>" +//道路代码
                "<ddms></ddms>" +
                "<wfdz>%E8%A5%BF%E5%92%8C%E9%AB%98%E9%80%9F35%E5%85%AC%E9%87%8C</wfdz>" +
                "<wfxw>1340</wfxw>" +
                "<scz></scz>" +
                "<bzz></bzz>" +
                "<cfzl>2</cfzl>" +
                "<fkje>200</fkje>" +
                "<zqmj>000718</zqmj>" +//jybh
                "<jkfs>2</jkfs>" +
                "<jkbj>0</jkbj>" +
                "<jkrq></jkrq>" +
                "<fxjg>633102000010</fxjg>" +//这个要改成zqmj当前部门
                "<clsj>2018-08-06 16:25</clsj>" +
                "<jsjqbj>00</jsjqbj>" +//?
                "<sgdj>4</sgdj><jd></jd><wd></wd></violation>\n" +
                "</root>";
        return xml;
    }

    /**
     * 通过传入的数据生成xml
     *
     * @param bizVioViolation
     * @return xml
     */
    private String getSimDocumentXMLDoc(BizVioViolation bizVioViolation) {
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        stringBuilder.append("<root>\n" +
                "<violation>");
        stringBuilder.append("<jdsbh>" + StringEx.nullToStrUtf(bizVioViolation.getJdsbh()) + "</jdsbh>");//*
        stringBuilder.append("<ryfl>" + StringEx.nullToStrUtf(bizVioViolation.getRyfl()) + "</ryfl>");//*
        stringBuilder.append("<jszh>" + StringEx.nullToStrUtf(bizVioViolation.getJszh()) + "</jszh>");//*
        stringBuilder.append("<dabh>" + StringEx.nullToStrUtf(bizVioViolation.getDabh()) + "</dabh>");//*
        stringBuilder.append("<fzjg>" + StringEx.nullToStrUtf(bizVioViolation.getFzjg()) + "</fzjg>");//*
        stringBuilder.append("<zjcx>" + StringEx.nullToStrUtf(bizVioViolation.getZjcx()) + "</zjcx>");//*
        stringBuilder.append("<dsr>" + StringEx.nullToStrUtf(bizVioViolation.getDsr()) + "</dsr>");//*
        stringBuilder.append("<zsxzqh>" + StringEx.nullToStrUtf(bizVioViolation.getZsxzqh()) + "</zsxzqh>");
        stringBuilder.append("<zsxxdz>" + StringEx.nullToStrUtf(bizVioViolation.getZsxxdz()) + "</zsxxdz>");
        stringBuilder.append("<dh>" + StringEx.nullToStrUtf(bizVioViolation.getDh()) + "</dh>");
        stringBuilder.append("<lxfs>" + StringEx.nullToStrUtf(bizVioViolation.getLxfs()) + "</lxfs>");
        stringBuilder.append("<clfl>" + StringEx.nullToStrUtf(bizVioViolation.getClfl()) + "</clfl>");//*
        stringBuilder.append("<hpzl>" + StringEx.nullToStrUtf(bizVioViolation.getHpzl()) + "</hpzl>");//*
        stringBuilder.append("<hphm>" + StringEx.nullToStrUtf(bizVioViolation.getHphm()) + "</hphm>");//*
        stringBuilder.append("<jdcsyr>" + StringEx.nullToStrUtf(bizVioViolation.getJdcsyr()) + "</jdcsyr>");
        stringBuilder.append("<syxz>" + StringEx.nullToStrUtf(bizVioViolation.getSyxz()) + "</syxz>");
        stringBuilder.append("<jtfs>" + StringEx.nullToStrUtf(bizVioViolation.getJtfs()) + "</jtfs>");//*
        stringBuilder.append("<wfsj>" + StringEx.nullToStrUtf(DateUtils.dateToStrMI(bizVioViolation.getWfsj())) + "</wfsj>");//*
        stringBuilder.append("<xzqh>" + StringEx.nullToStrUtf(bizVioViolation.getXzqh()) + "</xzqh>");//*
        stringBuilder.append("<wfdd>" + StringEx.nullToStrUtf(bizVioViolation.getWfdd()) + "</wfdd>");//*
        stringBuilder.append("<lddm>" + StringEx.nullToStrUtf(bizVioViolation.getLddm()) + "</lddm>");//*
        stringBuilder.append("<ddms>" + StringEx.nullToStrUtf(bizVioViolation.getDdms()) + "</ddms>");
        stringBuilder.append("<wfdz>" + StringEx.nullToStrUtf(bizVioViolation.getWfdz()) + "</wfdz>");//*
        stringBuilder.append("<wfxw>" + StringEx.nullToStrUtf(bizVioViolation.getWfxw()) + "</wfxw>");//*
        stringBuilder.append("<scz>" + StringEx.nullToStrUtf(bizVioViolation.getScz()) + "</scz>");
        stringBuilder.append("<bzz>" + StringEx.nullToStrUtf(bizVioViolation.getBzz()) + "</bzz>");
        stringBuilder.append("<cfzl>" + StringEx.nullToStrUtf(bizVioViolation.getCfzl()) + "</cfzl>");//* 必须为1或者2
        stringBuilder.append("<fkje>" + StringEx.nullToStrUtf(bizVioViolation.getFkje()) + "</fkje>");//*
        stringBuilder.append("<zqmj>" + StringEx.nullToStrUtf(bizVioViolation.getZqmj()) + "</zqmj>");//*
        stringBuilder.append("<jkfs>" + StringEx.nullToStrUtf(bizVioViolation.getJkfs()) + "</jkfs>");//*
        stringBuilder.append("<jkbj>" + StringEx.nullToStrUtf(bizVioViolation.getJkbj()) + "</jkbj>");//*
        stringBuilder.append("<jkrq>" + StringEx.nullToStrUtf(DateUtils.dateToStrDay(bizVioViolation.getJkrq())) + "</jkrq>");
        stringBuilder.append("<fxjg>" + StringEx.nullToStrUtf(bizVioViolation.getFxjg()) + "</fxjg>");//*
        stringBuilder.append("<clsj>" + StringEx.nullToStrUtf(DateUtils.dateToStrMI(bizVioViolation.getClsj())) + "</clsj>");
        stringBuilder.append("<jsjqbj>" + StringEx.nullToStrUtf(bizVioViolation.getJsjqbj()) + "</jsjqbj>");//*
        stringBuilder.append("<sgdj>" + StringEx.nullToStrUtf(bizVioViolation.getSgdj()) + "</sgdj>");//*
        stringBuilder.append("<jd>" + StringEx.nullToStrUtf(bizVioViolation.getJd()) + "</jd>");
        stringBuilder.append("<wd>" + StringEx.nullToStrUtf(bizVioViolation.getWd()) + "</wd>");
        stringBuilder.append("</violation>\n" +
                "</root>");
        return stringBuilder.toString();
    }

    /**
     * 上传综合平台简易处罚书
     *
     * @param xmlDoc
     * @param serviceSoap
     * @return
     */
    private WebResponse uploadSimDocumentToWEB(String xmlDoc, TmriJaxRpcOutNewAccess serviceSoap, HttpServletRequest request) throws ServiceException, RemoteException, UnsupportedEncodingException {
        WebResponse webResponse = new WebResponse();
        if (CacheMap.isTest) {
            return webResponse;
        }
        //场景编号
        String cjsqbh = "";
        Map<String, String> mab = libSysParam.getCJBHMap();
        if (mab != null) {
            boolean b = mab.containsKey("04C54");
            cjsqbh = (b == true) ? mab.get("04C54").trim() : "";
        }
        String body = serviceSoap.writeObjectOutNew("04", libSysParam.getZHPTJKXLH(), "04C54", cjsqbh, "", "", "", "", request.getServerName(), xmlDoc);
        body = URLDecoder.decode(body, "utf-8");
        loggerService.writeWebLogger(xmlDoc, body, "04C54");
        Root root = null;
        if (StringUtil.isNotEmpty(body)) {
            root = XmlUtil.toObj(body, Root.class);
            if (root == null || root.getHead() == null || !"1".equals(root.getHead().getCode())) {
                webResponse.setSuccess(false);
                if (root != null && root.getHead() != null && StringUtil.isNotEmpty(root.getHead().getMessage())) {
                    webResponse.setMessage(root.getHead().getMessage());
                } else {
                    webResponse.setMessage("数据上传综合平台失败");
                }
            }
        } else {
            webResponse.setSuccess(false);
        }
        return webResponse;
    }

    /**
     * 写入强制措施
     *
     * @param baseRequest
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public Response writeForceDocument(BaseRequest baseRequest, HttpServletRequest request) throws Exception {
        String writedoc = baseRequest.getWritedoc();

        Response response = null;
        if (StringUtil.isNotEmpty(writedoc)) {
            BizVioForce bizVioForce = JSONUtil.parseObject(writedoc, BizVioForce.class);
            Q21ReturnBean q21ReturnBean = JSONUtil.parseObject(writedoc, Q21ReturnBean.class);
            if (bizVioForce != null && q21ReturnBean != null) {
                String id = UUID.randomUUID().toString();
                String pzbh = bizVioForce.getPzbh();
                loggerService.writeOperaLogger(baseRequest.getUser(), 13, pzbh, baseRequest.getWym());
                bizVioForce.setId(id);

                List<Wfxwlist> list = q21ReturnBean.getWfxwlist();
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        Wfxwlist wfxw = list.get(i);
                        if (i == 0) {
                            bizVioForce.setWfxw1(wfxw.getWfxw());
                            bizVioForce.setScz1(wfxw.getScz());
                            bizVioForce.setBzz1(wfxw.getBzz());
                            continue;
                        }
                        if (i == 1) {
                            bizVioForce.setWfxw2(wfxw.getWfxw());
                            bizVioForce.setScz2(wfxw.getScz());
                            bizVioForce.setBzz2(wfxw.getBzz());
                            continue;
                        }
                        if (i == 2) {
                            bizVioForce.setWfxw3(wfxw.getWfxw());
                            bizVioForce.setScz3(wfxw.getScz());
                            bizVioForce.setBzz3(wfxw.getBzz());
                            continue;
                        }
                        if (i == 3) {
                            bizVioForce.setWfxw4(wfxw.getWfxw());
                            bizVioForce.setScz4(wfxw.getScz());
                            bizVioForce.setBzz4(wfxw.getBzz());
                            continue;
                        }
                        if (i == 4) {
                            bizVioForce.setWfxw5(wfxw.getWfxw());
                            bizVioForce.setScz5(wfxw.getScz());
                            bizVioForce.setBzz5(wfxw.getBzz());
                            continue;
                        }
                    }
                }
                TmriJaxRpcOutNewAccessService service = new TmriJaxRpcOutNewAccessServiceLocator();
                TmriJaxRpcOutNewAccess serviceSoap = service.getTmriOutNewAccess();
                Q21ReturnBean bean = bizVioForceMapper.getForceDocumentDetail(pzbh);
                if (bean != null) {
                    String wsjyw = "";
                    if (StringUtil.isNotEmpty(bean.getWsjyw())) {
                        wsjyw = bean.getWsjyw();
                    } else {
                        wsjyw = getWsjywAndUpdateForceTable(pzbh, serviceSoap, request);
                        if (StringUtil.isEmpty(wsjyw)) {
                            return new Response().failure("获取文书校验位失败,请重新提交");
                        }

                    }
                    Map map = new HashMap<String, String>();
                    map.put("wsjyw", wsjyw);
                    map.put("jdsbh", pzbh);
                    return new Response().success(map);
                }
                if ("3".equals(bizVioForce.getRyfl())){
                    //当人员分类为无证驾驶时，驾驶证号，档案编号，发证机关传无
                    bizVioForce.setDabh("无");
                    bizVioForce.setFzjg("无");

                }
                if ("2".equals(bizVioForce.getClfl())){
                    //当车辆分类为无牌无证机动车时，号牌号码，号牌种类传无
                    bizVioForce.setHphm("无");
                    bizVioForce.setHpzl("无");

                }
                int num = bizVioForceMapper.insertSelective(bizVioForce);
                if (num > 0) {
                    //写入成功生成打印照片
                    //   String url = getForceDocumentPhoto(q21ReturnBean, request);
                    //上传到综合平台
                    String xmlDoc = getForceDocumentXMLDoc(bizVioForce);
                    WebResponse webResponse =
                            uploadForceDocumentToWEB(xmlDoc, serviceSoap, request);
                    // isUploadSucceed = true;
                    if (webResponse.isSuccess()) {
                        String wsjyw = getWsjywAndUpdateForceTable(pzbh, serviceSoap, request);
                        if (StringUtil.isEmpty(wsjyw)) {
                            response = new Response().failure("获取文书校验位失败,请重新提交");
                        } else {
                            Map map = new HashMap<String, String>();
                            map.put("wsjyw", wsjyw);
                            map.put("jdsbh", pzbh);
                            response = new Response().success(map);
                        }
                    } else {
                        //删除刚才插入的数据
                        bizVioForceMapper.deleteVioForceById(id);
                        response = new Response().failure("写入综合平台失败");
                    }

                } else {
                    response = new Response().failure("写入失败");
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
     * 调用综合平台获取当前凭证编号的违法信息以及校验位
     *
     * @param pzbh
     * @param serviceSoap
     * @param request
     * @return
     */
    private String getWsjywAndUpdateForceTable(String pzbh, TmriJaxRpcOutNewAccess serviceSoap, HttpServletRequest request) throws UnsupportedEncodingException, RemoteException {
        String wsjyw = "";
        if (CacheMap.isTest) {
            wsjyw = "5";
            bizVioForceMapper.updateWsjywByPzbh(wsjyw, pzbh);
            return wsjyw;
        }
        WebForceResponseBean webForceResponseBean = getForceFromWebByPZBH(pzbh, serviceSoap, request);
        if (webForceResponseBean != null) {
            wsjyw = webForceResponseBean.getWsjyw();
            bizVioForceMapper.updateWsjywByPzbh(wsjyw, pzbh);
        }
        return wsjyw;
    }

    /**
     * 通过综合平台获取当前凭证编号的信息,主要获取凭证编号较验位
     *
     * @param pzbh
     * @param serviceSoap
     * @param hrequest
     */
    private WebForceResponseBean getForceFromWebByPZBH(String pzbh, TmriJaxRpcOutNewAccess serviceSoap, HttpServletRequest hrequest) throws UnsupportedEncodingException, RemoteException {
        //条件查询xmldoc
        String xmldoc = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<root> \n" +
                "  <QueryCondition> \n" +
                "    <pzbh>" + pzbh + "</pzbh>  \n" +
                "<xh></xh>\n<jszh></jszh>\n<dabh></dabh>\n<cjbj></cjbj>" +
                "  </QueryCondition> \n" +
                "</root>";
        //获取场景编号
        Map<String, String> m = libSysParam.getCJBHMap();
        String cjsqbh = "";
        if (m != null) {
            boolean b = m.containsKey("04C02");
            cjsqbh = (b == true) ? m.get("04C02").trim() : "";
        }
//        long date1=new Date().getTime();
        String xmlResult = serviceSoap.queryObjectOutNew("04", libSysParam.getZHPTJKXLH(), "04C02", cjsqbh, "", "", "", "", hrequest.getServerName(), xmldoc);
        xmlResult = URLDecoder.decode(xmlResult, "utf-8");
        loggerService.writeWebLogger(xmldoc, xmlResult, "04C02");
        //解析xml
        if (StringUtil.isEmpty(xmlResult)) {
            return null;
        }

        WebRoot result = XmlUtil.toObj(xmlResult, WebRoot.class);
        if (result == null || result.getHead() == null || !"1".equals(result.getHead().getCode())) {
            return null;
        }
        List<WebForceResponseBean> forceResponseBeanList = result.getBody().getWebForceResponseBeans();
        if (forceResponseBeanList == null || forceResponseBeanList.size() < 1) {
            return null;
        } else {
            return forceResponseBeanList.get(0);
        }
    }

    /**
     * 跟新打印次数
     *
     * @param baseRequest
     * @return
     * @throws Exception
     */
    @Override
    public Response writePrintPhoto(BaseRequest baseRequest) throws Exception {
        String writedoc = baseRequest.getWritedoc();
        Response response = null;
        if (StringUtil.isNotEmpty(writedoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(writedoc);
            if (map != null) {
                int i = 0;
                String xh = map.get("xh").toString();
                if ("0".equals(map.get("type").toString())) {//简易打印次数
                    loggerService.writeOperaLogger(baseRequest.getUser(), 12, xh, baseRequest.getWym());
                    i = bizVioViolationMapper.updatePrint(xh);
                } else if ("1".equals(map.get("type").toString())) {//强制打印次数
                    loggerService.writeOperaLogger(baseRequest.getUser(), 14, xh, baseRequest.getWym());
                    i = bizVioForceMapper.updatePrint(xh);
                } else {//电子打印次数
                    loggerService.writeOperaLogger(baseRequest.getUser(), 16, xh, baseRequest.getWym());
                    i = bizVioSurveMapper.updatePrint(xh);
                }
                if (i > 0) {
                    response = new Response().success();
                } else {
                    response = new Response().failure("更新打印次数失败");
                }
            } else {
                response = new Response().failure("打印回执数据错误");
            }
        } else {
            response = new Response().failure("打印回执数据错误");
        }
        return response;
    }


    /**
     * 上传强制措施到综合平台
     *
     * @param xmlDoc
     * @param serviceSoap
     * @return
     */
    private WebResponse uploadForceDocumentToWEB(String xmlDoc, TmriJaxRpcOutNewAccess serviceSoap, HttpServletRequest request) throws ServiceException, RemoteException, UnsupportedEncodingException {
        WebResponse webResponse = new WebResponse();
        if (CacheMap.isTest) {
            return webResponse;
        }

        //场景编号
        String cjsqbh = "";
        Map<String, String> mab = libSysParam.getCJBHMap();
        if (mab != null) {
            boolean b = mab.containsKey("04C55");
            cjsqbh = (b == true) ? mab.get("04C55").trim() : "";
        }
        String body = serviceSoap.writeObjectOutNew("04", libSysParam.getZHPTJKXLH(), "04C55", cjsqbh, "", "", "", "", request.getServerName(), xmlDoc);
        body = URLDecoder.decode(body, "utf-8");
        loggerService.writeWebLogger(xmlDoc, body, "04C55");
        Root root = null;
        if (StringUtil.isNotEmpty(body)) {
            root = XmlUtil.toObj(body, Root.class);
            if (root == null || root.getHead() == null) {
                webResponse.setSuccess(false);
                webResponse.setMessage("上传综合平台失败");
            } else if (!"1".equals(root.getHead().getCode())) {
                webResponse.setSuccess(false);
                webResponse.setMessage(root.getHead().getMessage());
            }
        } else {
            webResponse.setSuccess(false);
            webResponse.setMessage("上传综合平台失败");
        }
        return webResponse;
    }

    /**
     * 得到上传到综合平台的xml文档
     *
     * @param bizVioForce
     * @return
     */
    private String getForceDocumentXMLDoc(BizVioForce bizVioForce) {
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        stringBuilder.append("<root>\n" +
                "<vioforce>");
        stringBuilder.append("<pzbh>" + StringEx.nullToStrUtf(bizVioForce.getPzbh()) + "</pzbh>");
        stringBuilder.append("<wslb>" + "3" + "</wslb>");
        stringBuilder.append("<qzcslx>" + StringEx.nullToStrUtf(bizVioForce.getQzcslx()) + "</qzcslx>");
        stringBuilder.append("<klwpcfd>" + StringEx.nullToStrUtf(bizVioForce.getKlwpcfd()) + "</klwpcfd>");
        stringBuilder.append("<sjxm>" + StringEx.nullToStrUtf(bizVioForce.getSjxm()) + "</sjxm>");
        stringBuilder.append("<sjwpmc>" + StringEx.nullToStrUtf(bizVioForce.getSjwpmc()) + "</sjwpmc>");
        stringBuilder.append("<sjwpcfd>" + StringEx.nullToStrUtf(bizVioForce.getSjwpcfd()) + "</sjwpcfd>");
        stringBuilder.append("<ryfl>" + StringEx.nullToStrUtf(bizVioForce.getRyfl()) + "</ryfl>");
        stringBuilder.append("<jszh>" + StringEx.nullToStrUtf(bizVioForce.getJszh()) + "</jszh>");
        stringBuilder.append("<dabh>" + StringEx.nullToStrUtf(bizVioForce.getDabh()) + "</dabh>");
        stringBuilder.append("<fzjg>" + StringEx.nullToStrUtf(bizVioForce.getFzjg()) + "</fzjg>");

        stringBuilder.append("<zjcx>" + StringEx.nullToStrUtf(bizVioForce.getZjcx()) + "</zjcx>");
        stringBuilder.append("<dsr>" + StringEx.nullToStrUtf(bizVioForce.getDsr()) + "</dsr>");
        stringBuilder.append("<zsxzqh>" + StringEx.nullToStrUtf(bizVioForce.getZsxzqh()) + "</zsxzqh>");
        stringBuilder.append("<zsxxdz>" + StringEx.nullToStrUtf(bizVioForce.getZsxxdz()) + "</zsxxdz>");
        stringBuilder.append("<dh>" + StringEx.nullToStrUtf(bizVioForce.getDh()) + "</dh>");
        stringBuilder.append("<lxfs>" + StringEx.nullToStrUtf(bizVioForce.getLxfs()) + "</lxfs>");
        stringBuilder.append("<clfl>" + StringEx.nullToStrUtf(bizVioForce.getClfl()) + "</clfl>");
        stringBuilder.append("<hpzl>" + StringEx.nullToStrUtf(bizVioForce.getHpzl()) + "</hpzl>");
        stringBuilder.append("<hphm>" + StringEx.nullToStrUtf(bizVioForce.getHphm()) + "</hphm>");

        stringBuilder.append("<jdcsyr>" + StringEx.nullToStrUtf(bizVioForce.getJdcsyr()) + "</jdcsyr>");
        stringBuilder.append("<fdjh>" + StringEx.nullToStrUtf(bizVioForce.getFdjh()) + "</fdjh>");
        stringBuilder.append("<clsbdh>" + StringEx.nullToStrUtf(bizVioForce.getClsbdh()) + "</clsbdh>");
        stringBuilder.append("<syxz>" + StringEx.nullToStrUtf(bizVioForce.getSyxz()) + "</syxz>");
        stringBuilder.append("<jtfs>" + StringEx.nullToStrUtf(bizVioForce.getJtfs()) + "</jtfs>");
        stringBuilder.append("<wfsj>" + StringEx.nullToStrUtf(DateUtils.dateToStrMI(bizVioForce.getWfsj())) + "</wfsj>");
        stringBuilder.append("<xzqh>" + StringEx.nullToStrUtf(bizVioForce.getXzqh()) + "</xzqh>");
        stringBuilder.append("<wfdd>" + StringEx.nullToStrUtf(bizVioForce.getWfdd()) + "</wfdd>");
        stringBuilder.append("<lddm>" + StringEx.nullToStrUtf(bizVioForce.getLddm()) + "</lddm>");
        stringBuilder.append("<ddms>" + StringEx.nullToStrUtf(bizVioForce.getDdms()) + "</ddms>");
        stringBuilder.append("<wfdz>" + StringEx.nullToStrUtf(bizVioForce.getWfdz()) + "</wfdz>");
        stringBuilder.append("<wfxw1>" + StringEx.nullToStrUtf(bizVioForce.getWfxw1()) + "</wfxw1>");
        stringBuilder.append("<scz1>" + StringEx.nullToStrUtf(bizVioForce.getScz1()) + "</scz1>");
        stringBuilder.append("<bzz1>" + StringEx.nullToStrUtf(bizVioForce.getBzz1()) + "</bzz1>");

        stringBuilder.append("<wfxw2>" + StringEx.nullToStrUtf(bizVioForce.getWfxw2()) + "</wfxw2>");
        stringBuilder.append("<scz2>" + StringEx.nullToStrUtf(bizVioForce.getScz2()) + "</scz2>");
        stringBuilder.append("<bzz2>" + StringEx.nullToStrUtf(bizVioForce.getBzz2()) + "</bzz2>");

        stringBuilder.append("<wfxw3>" + StringEx.nullToStrUtf(bizVioForce.getWfxw3()) + "</wfxw3>");
        stringBuilder.append("<scz3>" + StringEx.nullToStrUtf(bizVioForce.getScz3()) + "</scz3>");
        stringBuilder.append("<bzz3>" + StringEx.nullToStrUtf(bizVioForce.getBzz3()) + "</bzz3>");
        stringBuilder.append("<wfxw4>" + StringEx.nullToStrUtf(bizVioForce.getWfxw4()) + "</wfxw4>");
        stringBuilder.append("<scz4>" + StringEx.nullToStrUtf(bizVioForce.getScz4()) + "</scz4>");
        stringBuilder.append("<bzz4>" + StringEx.nullToStrUtf(bizVioForce.getBzz4()) + "</bzz4>");
        stringBuilder.append("<wfxw5>" + StringEx.nullToStrUtf(bizVioForce.getWfxw5()) + "</wfxw5>");
        stringBuilder.append("<scz5>" + StringEx.nullToStrUtf(bizVioForce.getScz5()) + "</scz5>");
        stringBuilder.append("<bzz5>" + StringEx.nullToStrUtf(bizVioForce.getBzz5()) + "</bzz5>");


        stringBuilder.append("<zqmj>" + StringEx.nullToStrUtf(bizVioForce.getZqmj()) + "</zqmj>");
        stringBuilder.append("<fxjg>" + StringEx.nullToStrUtf(bizVioForce.getFxjg()) + "</fxjg>");
        stringBuilder.append("<jsjqbj>" + StringEx.nullToStrUtf(bizVioForce.getJsjqbj()) + "</jsjqbj>");
        stringBuilder.append("<sgdj>" + StringEx.nullToStrUtf(bizVioForce.getSgdj()) + "</sgdj>");
        stringBuilder.append("<mjyj>" + StringEx.nullToStrUtf(bizVioForce.getMjyj()) + "</mjyj>");

        stringBuilder.append("<jd>" + StringEx.nullToStrUtf(bizVioForce.getJd()) + "</jd>");
        stringBuilder.append("<wd>" + StringEx.nullToStrUtf(bizVioForce.getWd()) + "</wd>");

        stringBuilder.append("</vioforce>\n" +
                "</root>");
        return stringBuilder.toString();
    }


    @Override
    public Response getDocumentNumber(String querydoc) throws Exception {
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
            String wslb = map.get("wslb").toString();
            String bmbh = map.get("bmbh").toString();
            String user = map.get("user").toString();
            if (StringUtil.isEmpty(bmbh) || StringUtil.isEmpty(user)) {
                response = new Response().failure("无法确定当前警员信息");
            } else {
                String wsbh = bizVioForceMapper.getDocumentNumberByBmbhAndUser(bmbh, wslb, user);
                if (StringUtil.isEmpty(wsbh)) {
                    response = new Response().failure("无法获取当前警员的文书编号");
                } else {
                    Map<String, String> maps = new HashMap();
                    if (wsbh.length() > 8) {
                        String w = wsbh.substring(7, wsbh.length());
                        String q = wsbh.substring(0, 7);
                        int wf = Integer.valueOf(w);
                        wsbh = q + String.format("%08d", wf + 1);
                    }else{

                        wsbh=wsbh+String.format("%08d",  1);
                    }
                    maps.put("wsbh", wsbh);
                    response = new Response().success(maps);
                }
            }
        } else {
            response = new Response().failure("无法获取文书编号");
        }
        return response;
    }
}
