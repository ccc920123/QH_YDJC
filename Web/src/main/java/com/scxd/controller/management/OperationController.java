package com.scxd.controller.management;

import com.alibaba.fastjson.JSONObject;
import com.scxd.common.Response;
import com.scxd.service.OperationService;
import com.scxd.service.common.impl.LoggerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:32 2018/9/3
 * @Modified By:
 */
@RestController
@RequestMapping("/Operation")
public class OperationController {
    int OP_MODULE = 19;
    @Resource
    OperationService operationService;

    @Resource
    LoggerService loggerService;

  /*  @RequestMapping(value = "/queryOperationType", method = RequestMethod.GET, produces = "application/json")
    public Response queryOperationType(String bmbh, HttpServletRequest request) {
        Response response=operationService.queryOperationType();
        String opcontent="bmbh="+bmbh;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return operationService.queryOperationType();
    }*/

    @RequestMapping(value = "/queryOperationList", method = RequestMethod.GET, produces = "application/json")
    public Response queryOperationList(String ksrq, String jsrq, String bmbh, String czlx, String czr,int logType, int pageNo, int pageSize,HttpServletRequest request) {
        Response response = operationService.queryOperationList(ksrq, jsrq, bmbh, czlx, czr, pageNo, pageSize, logType);
        String opcontent="ksrq="+ksrq+"&jsrq="+jsrq+"&bmbh="+bmbh+"&czlx="+czlx+"&czr="+czr+"&logType="+logType+"&pageNo="+pageNo+"&pageSize="+pageSize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }

    @RequestMapping(value = "/queryOperationDetail", method = RequestMethod.GET, produces = "application/json")
    public Response queryOperationDetail(String id,HttpServletRequest request) {
        Response response=operationService.queryOperationDetail(id);
        String opcontent="id="+id;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }

    /**
     *根据id查询ScyLog表中的数据
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryScyLogDetail", method = RequestMethod.GET, produces = "application/json")
    public Response queryScyLogDetail(String id,HttpServletRequest request) {
        Response response=operationService.queryScyLogDetail(id);
        String opcontent="id="+id;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,3,3);
        return response;
    }
    // 统计
    @RequestMapping(value = "/queryLoginStatisticsList", method = RequestMethod.GET, produces = "application/json")
    public Response queryLoginStatisticsList(String kssj,String jssj,HttpServletRequest request) {
        Response response=operationService.queryLoginStatisticsList(kssj,jssj);
        String opcontent="kssj="+kssj+"&jssj="+jssj;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,25,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }
    @RequestMapping(value = "/queryOpStatisticsList", method = RequestMethod.GET, produces = "application/json")
    public Response queryOpStatisticsList(String kssj,String jssj,HttpServletRequest request) {
        Response response=operationService.queryOpStatisticsList(kssj,jssj);
        String opcontent="kssj="+kssj+"&jssj="+jssj;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,26,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }
    @RequestMapping(value = "/querySalfStatisticsList", method = RequestMethod.GET, produces = "application/json")
    public Response querySalfStatisticsList(String kssj,String jssj,HttpServletRequest request) {
        Response response=operationService.querySalfStatisticsList(kssj,jssj);
        String opcontent="kssj="+kssj+"&jssj="+jssj;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,27,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }
    @RequestMapping(value = "/queryAuditStatisticsList", method = RequestMethod.GET, produces = "application/json")
    public Response queryAuditStatisticsList(String kssj,String jssj,HttpServletRequest request) {
        Response response=operationService.queryAuditStatisticsList(kssj,jssj);
        String opcontent="kssj="+kssj+"&jssj="+jssj;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,28,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }
}
