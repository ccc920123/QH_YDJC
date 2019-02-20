package com.scxd.controller;

import com.alibaba.fastjson.JSONObject;
import com.scxd.aspect.IgnoreSecurity;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.pdaBeans.request.PdaUser;
import com.scxd.beans.pojo.SysIfLogWithBLOBs;
import com.scxd.common.JSONUtil;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.util.Md5Util;
import com.scxd.service.*;
import com.scxd.service.common.impl.LoggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;


/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 13:53 2018/5/25
 * @Modified By:
 */
@RestController
@Api(value = "PDA接口调用", description = "PDA接口调用")
public class ICS_Service {
    private static String PRI_KEY = "scxdics";
    @Resource
    private LoggerService loggerService;

    @Resource
    private LoginService loginService;

    @Resource
    private Q03ReturnService q03ReturnService;

    @Autowired
    private DriverService driver;
    @Autowired
    private PdaService ps;
    @Autowired
    private CodeService cs;
    @Autowired
    private LedgerService ledgerService;
    @Resource
    private W02ReturnService w02ReturnService;
    @Resource
    private Q06ReturnService q06ReturnService;
    @Autowired
    private PushService push;
    @Autowired
    private VehiclesService vs;
    @Autowired
    private VioService vioInfo;
    @Autowired
    private VioSurveService viosurve;
    @Resource
    private DocumentService vioDocument;
    @Autowired
    private RoadQueryService roadQueryService;
    @Autowired
    private LawService lawService;

    @IgnoreSecurity
    @ApiOperation(value = "PDA通用接口", notes = "接口通用入口")
    @RequestMapping(value = "/ICS_Service")
    public Response ICS_Service(@RequestBody @Valid BaseRequest baseRequest, HttpServletRequest request, HttpSession session) {
        //写日志
        SysIfLogWithBLOBs pdaLogWithBLOBs = writeLogger(baseRequest);
        // String path=  request.getServletContext().getRealPath("screenshot.js");

        try {
            String jkid = null;
            String wym = null;
            Response response = null;
            if (null != baseRequest) {
                jkid = baseRequest.getJkid();
                wym = baseRequest.getWym();
            }
            //验证、、、
            boolean verify = Verify(baseRequest);
            if (!verify) {
                writeLogger(baseRequest);
                return new Response().failure("验证失败！");
            }
            String querydoc = baseRequest.getQuerydoc();
            if (StringUtil.isNotEmpty(jkid)) {
                switch (jkid) {
                    case "Q01"://登录接口

                        response = loginService.checkPdaUser(baseRequest);
                        break;
                    case "Q02"://获取PDA版本
                        response = ps.getPdaVersion(baseRequest.querydoc);
                        break;
                    case "Q03"://卡口获取
                        response = q03ReturnService.getKKInterFace(baseRequest);
                        break;
                    case "Q04"://预警类型
                        response = q03ReturnService.getYJInterFace(querydoc);
                        break;
                    case "Q05"://警员获取
                        response = q03ReturnService.getPoliceInterFace(querydoc);
                        break;
                    case "Q06"://预警清单
                        response = q06ReturnService.getYJList(querydoc);
                        break;
                    case "Q07"://预警详情
                        response = q03ReturnService.getYJDetails(baseRequest, request);
                        break;
                    case "Q08"://预警反馈查询
                        response = q03ReturnService.getYJFeedBackDetails(querydoc, request);
                        break;
                    case "Q09"://获取机动车信息
                        response = vs.getVehiclesInfo(baseRequest, request);
                        break;
                    case "Q10"://获取驾驶人信息
                        response = driver.getDriverByjszh(baseRequest, request);
                        break;
                    case "Q11"://获取字典
                        response = cs.getCode(baseRequest.querydoc);
                        break;
                    case "Q12"://获取预警推送消息
                        response = push.getWarningPush(baseRequest.querydoc);
                        break;
                    case "Q13"://获取机动车违法记录列表
                        response = vioInfo.getVioList(baseRequest, request);
                        break;
                    case "Q14"://获取违法记录详细信息
                        response = vioInfo.getVioInfo(baseRequest, request);
                        break;
                    case "Q15"://获取违法驾驶人基本信息
                        break;
                    case "Q16"://获取驾驶人违法记录
                        response = vioInfo.getVioDrvList(baseRequest, request);
                        break;
                    case "Q17"://简易处罚决定书列表清单
                        response = vioDocument.getSimDocument(baseRequest.querydoc);
                        break;
                    case "Q18"://强制措施决定书列表清单
                        response = vioDocument.getForceDocument(baseRequest.querydoc);
                        break;
                    case "Q19"://电子监控决定书列表清单
                        response = viosurve.getVioSurveList(baseRequest.querydoc);
                        break;
                    case "Q20"://简易决定书详情
                        response = vioDocument.getSimDocumentDetail(baseRequest);
                        break;
                    case "Q21"://强制决定书详情
                        response = vioDocument.getForceDocumentDetail(baseRequest.querydoc);
                        break;
                    case "Q22"://电子监控决定书详情
                        response = viosurve.getVioSure(baseRequest.querydoc);
                        break;
                    case "Q23"://违法代码查询
                        response = vioInfo.getVioCodeList(baseRequest.querydoc);
                        break;
                    case "Q24"://违法代码详情查询
                        response = vioInfo.getVioCodeInfo(baseRequest);
                        break;
                    case "Q25"://查询图片
                        break;
                    case "Q26"://道路查询
                        response = roadQueryService.getRoadItem(baseRequest.querydoc);
                        break;
                    case "Q27"://路段查询
                        response = roadQueryService.getRoadSegItem(baseRequest.querydoc);
                        break;
                    case "Q28"://文书编号查询
                        response = vioDocument.getDocumentNumber(baseRequest.querydoc);
                        break;
                    case "Q29"://台账清单查询
                        response = ledgerService.getLedgerList(baseRequest.querydoc);
                        break;
                    case "Q30"://台账详情查询
                        response = ledgerService.getLedgerDetail(baseRequest.querydoc);
                        break;
                    case "Q31"://法律法规列表查询
                        response = lawService.getLawList(baseRequest.querydoc);
                        break;
                    case "Q32"://法律法规详情查询
                        response = lawService.getLawDetail(baseRequest);
                        break;
                    case "W01"://修改密码
                        response = loginService.changePassword(baseRequest);
                        break;
                    case "W02"://写入布控设置
                        response = w02ReturnService.writeBKMessage(baseRequest);
                        break;
                    case "W03"://预警签收
                        response = q03ReturnService.writeQSMessage(baseRequest);
                        break;
                    case "W04"://写入预警反馈
                        response = q03ReturnService.writeFeedBackMessage(baseRequest);
                        break;
                    case "W05"://台账写入
                        response = ledgerService.writeLedger(baseRequest);
                        break;
                    case "W07"://推送回执
                        response = push.pushBack(baseRequest.writedoc);
                        break;
                    case "W08"://写入简易处罚书
                        response = vioDocument.writeSimDocument(baseRequest, request);
                        break;
                    case "W09"://写入强制措施
                        response = vioDocument.writeForceDocument(baseRequest, request);
                        break;
                    case "W10"://电子监控录入
                        response = viosurve.writeVioSure(baseRequest, request);
                        break;
                    case "W11"://打印图片回执接口
                        response = vioDocument.writePrintPhoto(baseRequest);
                        break;
                    case "W00"://记录PDA崩溃错误日志
                        response = loggerService.writePDACashLog(baseRequest.writedoc);
                        break;
//                    default:
//                        response=new Response().failure("未开放的接口");
//                        break;

                }
            }
            //更新输出日志
            JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
            String str = json.toString();//将json对象转换为字符串
            pdaLogWithBLOBs.setOutdata(str);
            loggerService.updatePdaInterfacelog(pdaLogWithBLOBs);
            return response;
        } catch (Exception e) {
            pdaLogWithBLOBs.setErrormsg(e.getMessage());
            loggerService.updatePdaInterfacelog(pdaLogWithBLOBs);
            return new Response().failure("接口处理错误");
        }
    }

    /**
     * 写日志
     */
    private SysIfLogWithBLOBs writeLogger(BaseRequest baseRequest) {
        String wym = baseRequest.getWym();
        String jkid = baseRequest.getJkid();
        String indata = baseRequest.getQuerydoc() == null ? baseRequest.getWritedoc() : baseRequest.getQuerydoc();
        String id = UUID.randomUUID().toString();
        SysIfLogWithBLOBs sysIfLogWithBLOBs = new SysIfLogWithBLOBs();
        sysIfLogWithBLOBs.setCjsj(new Date());
        sysIfLogWithBLOBs.setId(id);
        sysIfLogWithBLOBs.setJkid(jkid);
        sysIfLogWithBLOBs.setWym(wym);
        sysIfLogWithBLOBs.setIndata(indata);
        loggerService.writePdaInterfaceLog(sysIfLogWithBLOBs);
        return sysIfLogWithBLOBs;

    }

    /**
     * 验证
     */
    private boolean Verify(BaseRequest baseRequest) {
        boolean verify = false;
        String wym = baseRequest.getWym();
        String sjc = baseRequest.getSjc();
        String jkxlh = baseRequest.getJkxlh();
        String key = wym + PRI_KEY + sjc;
        String keyMD5 = null;
        try {
            keyMD5 = EncoderByMd5(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (StringUtil.isNotEmpty(keyMD5) && keyMD5.equals(jkxlh)) {
            verify = true;
        } else {
            verify = false;
        }
        return verify;
    }

    /**
     * 利用MD5进行加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String newstr = Md5Util.md5(str);
        return newstr;
    }
}
