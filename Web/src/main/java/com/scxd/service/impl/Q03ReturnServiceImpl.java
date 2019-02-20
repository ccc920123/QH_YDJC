package com.scxd.service.impl;

import com.alibaba.fastjson.JSON;
import com.scxd.beans.biz.BizPhotoInfo;
import com.scxd.beans.common.User;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.pdaBeans.Root;
import com.scxd.beans.pdaBeans.request.PdaFeedBackRequest;
import com.scxd.beans.pdaBeans.response.CodeValueCommon;
import com.scxd.beans.pdaBeans.response.CodeYjBean;
import com.scxd.beans.pdaBeans.response.PdaKKResponse;
import com.scxd.beans.pdaBeans.response.Q07ReturnBean;
import com.scxd.beans.pojo.BizAlarmInfo;
import com.scxd.beans.pojo.BizRmConfig;
import com.scxd.common.*;
import com.scxd.dao.mapper.*;
import com.scxd.service.Q03ReturnService;
import com.scxd.service.VehiclesService;
import com.scxd.service.common.CacheMap;
import com.scxd.service.common.LibSysParam;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.webjczhpt.RmJaxRpcOutAccess;
import com.scxd.webjczhpt.RmJaxRpcOutAccessService;
import com.scxd.webjczhpt.RmJaxRpcOutAccessServiceLocator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.*;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 15:19 2018/6/13
 * @Modified By:
 */
@Service
public class Q03ReturnServiceImpl implements Q03ReturnService {

    @Resource
    private BizRmConfigMapper bizRmConfigMapper;


    @Resource
    private BizRmConfigDetailMapper bizRmConfigDetailMapper;

    @Resource
    private BizRoadBlocksMapper bizRoadBlocksMapper;
    @Resource
    private BizPoliceInfoMapper bizPoliceInfoMapper;

    @Resource
    private BizAlarmInfoMapper bizAlarmInfoMapper;

    @Resource
    private LibSysParam libSysParam;
    @Resource
    private PhotoDao photoDao;
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private VehiclesService vehiclesService;
    @Resource
    private LoggerService loggerService;
    @Resource
    private SysDepartmentParamMapper sysDepartmentParamMapper;


    /**
     * 获取卡口接口
     *
     * @param baseRequest json
     * @return
     */
    @Override
    public Response getKKInterFace(BaseRequest baseRequest) {
        String querydoc = baseRequest.getQuerydoc();
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);

            int type = 0;
            map.put("type", type);
            String ssbmbh = map.get("ssbmbh").toString();
            String sjbmbh = map.get("sjbmbh").toString();
            String user = map.get("user").toString();
            //获取上一次的卡口信息以及获取上一次选中的列表信息
            BizRmConfig bizRmConfig = bizRmConfigMapper.getLastKKMessage(map);
            List<CodeValueCommon> roadBlocks = bizRoadBlocksMapper.getKKMessageByBmbh(type, ssbmbh, user);
            if (roadBlocks == null || roadBlocks.size() < 1) {
                roadBlocks = bizRoadBlocksMapper.getKKMessageByBmbh(type, sjbmbh, user);
            }
            PdaKKResponse pdaKKResponse = new PdaKKResponse();
            pdaKKResponse.setJszt("0");
            if (null != bizRmConfig) {
                //获取对应部门的卡口列表
                pdaKKResponse.setJszt(bizRmConfig.getTszt().toString());
            }
            pdaKKResponse.setSbkk(roadBlocks);
            response = new Response().success(pdaKKResponse);
        } else {
            response = new Response().failure("查询数据失败");
        }
        return response;
    }

    @Override
    public Response getYJInterFace(String querydoc) {
        Response response = null;
        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
            map.put("type", 1);
            //获取上一次的卡口信息以及获取上一次选中的列表信息
            List<CodeYjBean> codeYjBeans = bizRmConfigMapper.getYJMessage(map);
            response = new Response().success(codeYjBeans);
        } else {
            response = new Response().failure("查询数据失败");
        }
        return response;
    }

    @Override
    public Response getPoliceInterFace(String querydoc) {
        Response response = null;

        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(querydoc);
            map.put("type", 2);

            //获取上一次的警员信息以及获取上一次选中的列表信息
            //获取对应部门的警员列表
            List<CodeValueCommon> polices = bizPoliceInfoMapper.getPoliceInterFace(map);
            response = new Response().success(polices);
        } else {
            response = new Response().failure("查询数据失败");
        }
        return response;
    }

    @Override
    public Response getYJDetails(BaseRequest baseRequest, HttpServletRequest request) {
        String querydoc = baseRequest.getQuerydoc();
        Response response = null;

        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, String> map = (Map<String, String>) JSON.parse(querydoc);
            String yjxh = map.get("yjxh");
            loggerService.writeOperaLogger(baseRequest.getUser(), 8, yjxh, baseRequest.getWym());
            Q07ReturnBean alarmInfo = bizAlarmInfoMapper.getYJDetails(yjxh);
            response = new Response().success(alarmInfo);
        } else {
            response = new Response().failure("查询数据失败");
        }
        return response;
    }

    @Override
    public Response writeQSMessage(BaseRequest baseRequest) throws UnsupportedEncodingException, RemoteException, ServiceException {
        Response response = null;
        String writedoc = baseRequest.getWritedoc();
        if (StringUtil.isNotEmpty(writedoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(writedoc);
            String sfcj = map.get("sfcj").toString();
            String yjxh = map.get("yjxh").toString();
            String qsjg = map.get("qsjg").toString();
            String bmbh = map.get("bmbh").toString();
            loggerService.writeOperaLogger(baseRequest.getUser(), 9, yjxh, baseRequest.getWym());
            //当签收结果选择无效
            //1.修改状态
            //2.直接反馈集成指挥平台
            Date czsj = new Date();
            if ("2".equals(qsjg)) {
                map.put("zt", "2");
                String polices = bizPoliceInfoMapper.getPlicesByLoginName(map.get("user").toString());
                User user = sysUserMapper.selectUserByUserName(map.get("user").toString());
                if (StringUtil.isEmpty(polices)) {

                    if (null != user) {
                        polices = user.getRealname();
                    } else {
                        polices = map.get("user").toString();
                    }
                }
                polices = polices.substring(0, polices.length() >= 30 ? 30 : polices.length());
                BizAlarmInfo bizAlarmInfo = bizAlarmInfoMapper.selectByPrimaryKey(yjxh);
                bizAlarmInfo.setQsjg(Short.valueOf(qsjg));
                bizAlarmInfo.setZt("2");
                bizAlarmInfo.setCzdw(map.get("bmbh").toString());
                bizAlarmInfo.setCzsj(czsj);
                bizAlarmInfo.setCzdw(bmbh);
                bizAlarmInfo.setCzr(user.getRealname());
                bizAlarmInfo.setYwlx(Short.valueOf("3"));
                String xml = getFeedBackXml(bizAlarmInfo);
                RmJaxRpcOutAccessService rmJaxRpcOutAccessService = new RmJaxRpcOutAccessServiceLocator();
                RmJaxRpcOutAccess serviceSoap = rmJaxRpcOutAccessService.getRmOutAccess();
                String jkxlh = sysDepartmentParamMapper.getCSZByBmbhAndCsdm(bmbh, "JCZHJKXLH");
                WebResponse webResponse = writeFeedBackMsgToWEB(xml, jkxlh, serviceSoap);
                if (!webResponse.isSuccess()) {
                    return new Response().failure(webResponse.getMessage());
                }
                map.put("czr", polices);
                map.put("czsj", czsj);
                map.put("ywlx", Short.valueOf("3"));

            } else {
                map.put("zt", "1");
            }
            map.put("qssj", czsj);
            int i = bizAlarmInfoMapper.updateQSMessage(map);
            if (i > 0) {
                response = new Response().success();
            } else {
                response = new Response().failure("签收失败");
            }

        } else {
            response = new Response().failure("签收失败");
        }
        return response;
    }

    @Override
    public Response getYJFeedBackDetails(String querydoc, HttpServletRequest request) {
        Response response = null;

        if (StringUtil.isNotEmpty(querydoc)) {
            Map<String, String> map = (Map<String, String>) JSON.parse(querydoc);
            String yjxh = map.get("yjxh");
            String sjbmbh = map.get("sjbmbh");
            PdaFeedBackRequest pdaFeedBackRequest = bizAlarmInfoMapper.getYJFeedBackDetails(yjxh, sjbmbh);
            response = new Response().success(pdaFeedBackRequest);
        } else {
            response = new Response().failure("查询数据失败");
        }
        return response;
    }

    @Override
    public Response writeFeedBackMessage(BaseRequest baseRequest) throws Exception {
//"{\"cfxylx\":\"04\",\"cjry\":\"李一川\",\"czdw\":\"633102000010\",\"czfs\":\"2\",\"fxyclyy\":\"\",\"jyw\":\"4\",\"ljqk\":\"1\",\"ljqkms\":\"已拦截，现场开具处罚文书\",\"lxdh\":\"\",\"lxr\":\"\"," +
//        "\"sfxycl\":\"1\",\"user\":\"000718\",\"wljdyy\":\"\",\"wsbh\":\"633102100056001\",\"wslb\":\"1\",\"yjbm\":\"\",\"yjxh\":\"6300A00062489614\"}";
        Response response = null;
        String writedoc = baseRequest.getWritedoc();
        //writedoc="{\"cfxylx\":\"34\",\"cfxyzlx\":\"J\",\"cjry\":\"张刚\",\"czdw\":\"633102000010\",\"czfs\":\"2\",\"fxyclyy\":\"\",\"jyw\":\"0\",\"ljqk\":\"1\",\"ljqkms\":\"已拦截并开具处罚决定书\",\"lxdh\":\"\",\"lxr\":\"\",\"sfxycl\":\"1\",\"user\":\"000686\",\"wljdyy\":\"\",\"wsbh\":\"633102100056213\",\"wslb\":\"1\",\"yjbm\":\"\",\"yjxh\":\"6300A00064648351\"}";
        if (StringUtil.isNotEmpty(writedoc)) {
            PdaFeedBackRequest feedBackRequest = JSONUtil.parseObject(writedoc, PdaFeedBackRequest.class);
            // 查询整个预警信息，并提交到缉查布控平台
            if (feedBackRequest == null) {
                return new Response().failure("上传的数据不正确");
            }
            String yjxh = feedBackRequest.getYjxh();
            loggerService.writeOperaLogger(baseRequest.getUser(), 10, yjxh, baseRequest.getWym());
            String bmbh = feedBackRequest.getCzdw();
            RmJaxRpcOutAccessService rmJaxRpcOutAccessService = new RmJaxRpcOutAccessServiceLocator();
            RmJaxRpcOutAccess serviceSoap = rmJaxRpcOutAccessService.getRmOutAccess();
            String jkxlh = sysDepartmentParamMapper.getCSZByBmbhAndCsdm(bmbh, "JCZHJKXLH");
            //查询数据库中当前预警状态如果已经反馈了直接上传照片

            BizAlarmInfo bizAlarmInfoin = bizAlarmInfoMapper.selectByPrimaryKey(yjxh);
            if (bizAlarmInfoin != null && "2".equals(bizAlarmInfoin.getZt())) {
                boolean upPhotoSusseed = upLoadPhoto(yjxh, jkxlh, serviceSoap);
                if (!upPhotoSusseed) {
                    photoDao.deleteByGlid(yjxh, "7002");
                    return new Response().failure("上传照片失败");
                } else {
                    return new Response().success();
                }
            }

            String polices = bizPoliceInfoMapper.getPlicesByLoginName(feedBackRequest.getUser());
            User user = sysUserMapper.selectUserByUserName(feedBackRequest.getUser());
            if (StringUtil.isEmpty(polices)) {
                if (null != user) {
                    polices = user.getRealname();
                } else {
                    polices = feedBackRequest.getUser();
                }
            }
            polices = polices.substring(0, polices.length() >= 30 ? 30 : polices.length());
            BizAlarmInfo bizAlarmInfo = bizAlarmInfoMapper.selectByPrimaryKey(yjxh);
            bizAlarmInfo.setZt("2");
            bizAlarmInfo.setYwlx(Short.valueOf("3"));
            if (StringUtil.isNotEmpty(feedBackRequest.getLjqk())) {
                bizAlarmInfo.setLjclqk(Short.valueOf(feedBackRequest.getLjqk()));
            }
            bizAlarmInfo.setWljdyy(feedBackRequest.getWljdyy());
            if (StringUtil.isNotEmpty(feedBackRequest.getSfxycl())) {
                bizAlarmInfo.setChjg(Short.valueOf(feedBackRequest.getSfxycl()));
            }
            bizAlarmInfo.setWchyy(feedBackRequest.getFxyxlyy());
            bizAlarmInfo.setLjqkms(feedBackRequest.getLjqkms());
            //更新反馈信息
            //获取布控配置选择的民警信息
            bizAlarmInfo.setCzr(user.getRealname());
            bizAlarmInfo.setCzdw(feedBackRequest.getCzdw());
            bizAlarmInfo.setCljg(feedBackRequest.getCzfs());
            bizAlarmInfo.setLxr(feedBackRequest.getLxr());
            bizAlarmInfo.setLxdh(feedBackRequest.getLxdh());
            bizAlarmInfo.setWsbh(feedBackRequest.getWsbh());
            bizAlarmInfo.setCfxylx(feedBackRequest.getCfxylx());
            bizAlarmInfo.setCfxyzlx(feedBackRequest.getCfxyzlx());
            if (StringUtil.isNotEmpty(feedBackRequest.getJyw())) {
                bizAlarmInfo.setJyw(Short.valueOf(feedBackRequest.getJyw()));
            }
            bizAlarmInfo.setCzsj(new Date());
            if (StringUtil.isNotEmpty(feedBackRequest.getWslb())) {
                bizAlarmInfo.setWslb(Short.valueOf(feedBackRequest.getWslb()));
            }
            bizAlarmInfo.setYjbm(feedBackRequest.getYjbm());

            try {
                //getFeedBackXmlTest(bizAlarmInfo);
                String xml =
                        getFeedBackXml(bizAlarmInfo);
                //上传数据到集成指挥平台
                WebResponse webResponse = //new WebResponse();
                        writeFeedBackMsgToWEB(xml, jkxlh, serviceSoap);
                bizAlarmInfo.setCzr(polices);
                if (webResponse.isSuccess()) {

                    int i = bizAlarmInfoMapper.updateByPrimaryKey(bizAlarmInfo);
                    if (i > 0) {
                        boolean upPhotoSusseed = upLoadPhoto(yjxh, jkxlh, serviceSoap);
                        if (!upPhotoSusseed) {
                            photoDao.deleteByGlid(yjxh, "7002");
                            response = new Response().failure("上传照片失败");
                        } else {
                            response = new Response().success();
                        }
                    } else {
                        photoDao.deleteByGlid(yjxh, "7002");
                        response = new Response().failure("写入失败");
                    }
                } else {
                    response = new Response().failure(webResponse.getMessage());
                }
            } catch (Exception e) {
                //出现异常删除图片
                photoDao.deleteByGlid(yjxh, "7002");
                throw e;
            }
        } else {
            response = new Response().failure("写入失败,传入的数据不正确");
        }
        return response;


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
    private WebResponse writeFeedBackMsgToWEB(String xml, String jkxlh, RmJaxRpcOutAccess serviceSoap) throws ServiceException, RemoteException, UnsupportedEncodingException {
        WebResponse webResponse = new WebResponse();
        //jkxlh = "7A1E1D0F00070305091502010002090200060904020C179FA02E6D72692E636E";
        //县上传图片
        if (CacheMap.isTest) {
            return webResponse;
        }
        String body = serviceSoap.writeObjectOut("64", jkxlh, "64W02", xml);
        body = URLDecoder.decode(body, "utf-8");

        loggerService.writeWebLogger(xml, body, "64W02");
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
            /*else{
                boolean upPhotoSusseed = upLoadPhoto(yjxh, jkxlh, serviceSoap);
                if (!upPhotoSusseed) {
                    webResponse.setSuccess(false);
                    webResponse.setMessage("照片上传失败");
                    return webResponse;
                }
            }*/
        } else {
            webResponse.setSuccess(false);
            webResponse.setMessage("上传集成指挥平台失败");
        }

        return webResponse;
    }

    private boolean upLoadPhoto(String yjxh, String jkxlh, RmJaxRpcOutAccess serviceSoap) throws RemoteException {

        Root root = null;
        //上传图片
        List<BizPhotoInfo> lists = photoDao.getFeedBackPhotosByYjxh(yjxh);
        if (lists != null && lists.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            stringBuilder.append("<root>\n" +
                    "<feedbackpic>");
            stringBuilder.append("<yjxh>" + yjxh + "</yjxh>");
            String psry = null;
            int size = lists.size() > 3 ? 3 : lists.size();
            for (int i = 0; i < size; i++) {
                psry = lists.get(i).getPsry();
                byte[] photo = lists.get(i).getZp();
                String encoded = Base64.getEncoder().encodeToString(photo);
                try {
                    stringBuilder.append("<tp" + (i + 1) + ">" + URLEncoder.encode(encoded, "utf-8") + "</tp" + (i + 1) + ">");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (size == 1) {
                stringBuilder.append("<tp2></tp2>");
                stringBuilder.append("<tp3></tp3>");
            } else if (size == 2) {
                stringBuilder.append("<tp3></tp3>");
            }
            Map<String, String> map = sysUserMapper.selectBmbhANDRealName(psry);
            stringBuilder.append("<scdw>" + map.get("SSBMBH") + "</scdw>");
            stringBuilder.append("<scr>" + map.get("REALNAME") + "</scr>");
            stringBuilder.append("</feedbackpic>\n" +
                    "</root>");
            if (CacheMap.isTest) {
                return true;
            }
            String photobody = serviceSoap.writeObjectOut("02", jkxlh, "64W03", stringBuilder.toString());
            loggerService.writeWebLogger(stringBuilder.toString(), photobody, "64W03");
            if (StringUtil.isNotEmpty(photobody)) {
                root = XmlUtil.toObj(photobody, Root.class);
                if (root != null && root.getHead() != null && "1".equals(root.getHead().getCode())) {
                    return true;
                }
            }

        } else {
            //没有照片直接相当于成功
            return true;
        }
        return false;
    }

    /**
     * 得到预警反馈的xml
     *
     * @param bizAlarmInfo
     * @return
     */
    private String getFeedBackXml(BizAlarmInfo bizAlarmInfo) {
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        stringBuilder.append("<root>\n" +
                "<feedback>");
        stringBuilder.append("<yjxh>" + StringEx.nullToStr(bizAlarmInfo.getYjxh()) + "</yjxh>");
        stringBuilder.append("<ywlx>" + StringEx.nullToStr(bizAlarmInfo.getYwlx()) + "</ywlx>");
        stringBuilder.append("<qsjg>" + StringEx.nullToStr(bizAlarmInfo.getQsjg()) + "</qsjg>");
        stringBuilder.append("<sfcjlj>" + StringEx.nullToStr(bizAlarmInfo.getSfcjlj()) + "</sfcjlj>");
        stringBuilder.append("<ljclqk>" + StringEx.nullToStr(bizAlarmInfo.getLjclqk()) + "</ljclqk>");
        stringBuilder.append("<wljdyy>" + StringEx.nullToStr(bizAlarmInfo.getWljdyy()) + "</wljdyy>");
        stringBuilder.append("<chjg>" + StringEx.nullToStr(bizAlarmInfo.getChjg()) + "</chjg>");
        stringBuilder.append("<cljg>" + StringEx.nullToStr(bizAlarmInfo.getCljg()) + "</cljg>");
        stringBuilder.append("<wsbh>" + StringEx.nullToStr(bizAlarmInfo.getWsbh()) + "</wsbh>");
        stringBuilder.append("<jyw>" + StringEx.nullToStr(bizAlarmInfo.getJyw()) + "</jyw>");
        stringBuilder.append("<wslb>" + StringEx.nullToStr(bizAlarmInfo.getWslb()) + "</wslb>");
        stringBuilder.append("<cfxylx>" + StringEx.nullToStr(bizAlarmInfo.getCfxylx()) + "</cfxylx>");
        stringBuilder.append("<cfxyzlx>" + StringEx.nullToStr(bizAlarmInfo.getCfxyzlx()) + "</cfxyzlx>");
        stringBuilder.append("<yjbm>" + StringEx.nullToStr(bizAlarmInfo.getYjbm()) + "</yjbm>");
        stringBuilder.append("<lxr>" + StringEx.nullToStr(bizAlarmInfo.getLxr()) + "</lxr>");
        stringBuilder.append("<lxdh>" + StringEx.nullToStr(bizAlarmInfo.getLxdh()) + "</lxdh>");
        stringBuilder.append("<wchyy>" + StringEx.nullToStr(bizAlarmInfo.getWchyy()) + "</wchyy>");
        stringBuilder.append("<czqkms>" + StringEx.nullToStr(bizAlarmInfo.getLjqkms()) + "</czqkms>");
        stringBuilder.append("<czdw>" + StringEx.nullToStr(bizAlarmInfo.getCzdw()) + "</czdw>");
        stringBuilder.append("<czr>" + StringEx.nullToStr(bizAlarmInfo.getCzr()) + "</czr>");
        stringBuilder.append("<czsj>" + StringEx.nullToStr(DateUtils.dateToStrSS(bizAlarmInfo.getCzsj())) + "</czsj>");

        stringBuilder.append("</feedback>\n" +
                "</root>");
        return stringBuilder.toString();
    }

    /**
     * test   xml
     *
     * @param bizAlarmInfo
     * @return
     */
    private String getFeedBackXmlTest(BizAlarmInfo bizAlarmInfo) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root>\n" +
                "<feedback>" +
                "<yjxh>6300A00061649079</yjxh>" +
                "<ywlx>3</ywlx>" +
                "<qsjg>1</qsjg>" +
                "<sfcjlj>1</sfcjlj>" +
                "<ljclqk>1</ljclqk>" +//这里是0,?
                "<wljdyy></wljdyy>" +
                "<chjg>1</chjg>" +//?
                "<cljg>2</cljg>" +
                "<wsbh>633102100053725</wsbh>" +//?
                "<jyw>5</jyw>" +//?
                "<wslb>1</wslb>" +
                "<cfxylx>04</cfxylx>" +//?
                "<cfxyzlx></cfxyzlx>" +
                "<yjbm></yjbm>" +
                "<lxr></lxr>" +
                "<lxdh></lxdh>" +
                "<wchyy></wchyy>" +//?
                "<czqkms>" + StringEx.nullToStr("民警对驾驶人处以罚款200元记3分的现场处罚") + "</czqkms>" +//??
                "<czdw>633102000010</czdw>" +//?
                "<czr>" + StringEx.nullToStr("李一川") + "</czr>" +//??
                "<czsj>2018-08-06 16:25:50</czsj></feedback>\n" +
                "</root>";
        return xml;
    }

}
