package com.scxd.controller.management;

import com.alibaba.fastjson.JSONObject;
import com.scxd.common.Response;
import com.scxd.service.LedgerService;
import com.scxd.service.common.impl.LoggerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:25 2018/8/30
 * @Modified By: 台账
 */
@RestController
@RequestMapping("/Ledger")
public class LedgerController {
    int OP_MODULE = 5;
    @Resource
    LedgerService ledgerService;
    @Resource
    LoggerService loggerService;

    @RequestMapping(value = "/queryLeadgerList", method = RequestMethod.GET)
    public Response queryLeadgerList(String bmbh, String ksrq, String jsrq, String zfzbh,
                                     String hphm, String jszh, String pageNo, String pageSize
    ,HttpServletRequest request) {
        Response response=ledgerService.queryLeadgerList(bmbh, ksrq, jsrq, zfzbh, hphm, jszh, pageNo, pageSize);
        String opcontent="bmbh="+bmbh+"&ksrq="+ksrq+"&jsrq="+jsrq+"&zfzbh="+zfzbh+"&hphm="+hphm+"&jszh="+jszh+"&pageNo="+pageNo+"&pageSize="+pageSize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }

    @RequestMapping(value = "/queryLeadgerDetail", method = RequestMethod.GET)
    public Response queryLeadgerDetail(String id, HttpServletRequest request) {
        Response response=ledgerService.queryLeadgerDetail(id, request);
        String opcontent="id="+id;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }
}
