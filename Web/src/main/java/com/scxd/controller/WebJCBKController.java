package com.scxd.controller;

import com.scxd.aspect.IgnoreSecurity;
import com.scxd.beans.pojo.BizAlarmInfo;
import com.scxd.common.DateUtils;
import com.scxd.common.Response;
import com.scxd.service.WebJCBKService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

/**
 * @Auther:陈攀
 * @Description:缉查布控的调用接口
 * @Date:Created in 19:25 2018/6/14
 * @Modified By:
 */
@RestController
@Api(value = "缉查布控调用接口", description = "缉查布控调用接口")
public class WebJCBKController {
    @Resource
    private WebJCBKService webJCBKService;
    @IgnoreSecurity
    @ApiOperation(value = "缉查布控预警写入调用接口", notes = "缉查布控预警写入调用接口")
    @RequestMapping(value = "/alarm.tfc", method = RequestMethod.GET, produces = "application/json")
    public Response sendAlarm(String method, String yjxh, String yjsj,
                              String ywlb, String bklx,
                              String bkzlx, String gcxh,
                              String kkbh, String kkmc,
                              String fxlx, String cdbh,
                              String hpzl, String hphm,
                              String gcsj, String qsbm,
                              HttpServletRequest request, HttpSession session) {
        BizAlarmInfo bizAlarmInfo = new BizAlarmInfo();
        bizAlarmInfo.setYjxh(yjxh);
        bizAlarmInfo.setYjsj(DateUtils.strToDateLong(yjsj));
        bizAlarmInfo.setYwlb(ywlb);
        bizAlarmInfo.setBklx(bklx);
        bizAlarmInfo.setBkzlx(bkzlx);
        bizAlarmInfo.setGcxh(gcxh);
        bizAlarmInfo.setKkbh(kkbh);
        bizAlarmInfo.setKkmc(kkmc);
        bizAlarmInfo.setFxlx(fxlx);
        bizAlarmInfo.setCdbh(cdbh);
        bizAlarmInfo.setHpzl(hpzl);
        bizAlarmInfo.setHphm(hphm);
        bizAlarmInfo.setGcsj(DateUtils.strToDateLong(gcsj));
        bizAlarmInfo.setQsbm(qsbm);
        bizAlarmInfo.setZt("0");
        return webJCBKService.writeYJMessage(bizAlarmInfo, request);
    }

    /**
     * 测试综合平台调用接口没
     * @param request
     * @return
     */
//    @RequestMapping(value = "/alarm.tfc", method = RequestMethod.GET, produces = "application/json")
//    public Response sendAlarm(HttpServletRequest request) {
//        String param = request.getQueryString();
//        if (Base64.isBase64(param)) {
//            param = new String(Base64.decodeBase64(param), StandardCharsets.UTF_8);
//        }
//        return new Response().success(param);
//    }

}
