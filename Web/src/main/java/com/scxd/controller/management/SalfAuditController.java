package com.scxd.controller.management;

import com.alibaba.fastjson.JSONObject;
import com.scxd.beans.pojo.SysUserSafeauditsetBean;
import com.scxd.common.Response;
import com.scxd.service.SalfAuditService;
import com.scxd.service.common.impl.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 13:49 2018/10/18
 * @Modified By:
 */
@RestController
@RequestMapping("/salfaudit")
public class SalfAuditController {
   int  OP_MODULE=22;
   int LOG_OP_MODULE=24;
    @Autowired
    SalfAuditService salfAuditService;
    @Resource
    LoggerService loggerService;
    @RequestMapping(value = "/getSalfAuditList", produces = "application/json")
    public Response getSalfAuditList(String salfType,int pageNo,int pageSize,  HttpServletRequest request, HttpSession session) {
       Response response= salfAuditService.salfAuditService(salfType,pageNo,pageSize);
        String opcontent="salfType="+salfType+"&pageNo="+pageNo+"&pageSize="+pageSize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }
    @RequestMapping(value = "/getSalfAuditDetail", produces = "application/json")
    public Response getSalfAuditDetail(String id,  HttpServletRequest request, HttpSession session) {
        Response response= salfAuditService.getSalfAuditDetail(id);
        String opcontent="id="+id;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }
    @RequestMapping(value = "/saveSalfAudit",method = RequestMethod.POST ,produces = "application/json")
    public Response saveSalfAudit(@RequestBody SysUserSafeauditsetBean sysUserSafeauditsetBean, HttpServletRequest request, HttpSession session) {
        Response response= salfAuditService.saveSalfAudit(sysUserSafeauditsetBean);
        String opcontent=((JSONObject) JSONObject.toJSON(sysUserSafeauditsetBean)).toJSONString();
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,5,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }
    @RequestMapping(value = "/salfAuditLogList",method = RequestMethod.GET ,produces = "application/json")
    public Response salfAuditLogList(String  safetype,int pageNo,int pageSize, HttpServletRequest request, HttpSession session) {
        Response response= salfAuditService.salfAuditLogList(safetype,pageNo,pageSize);
        String opcontent="safetype="+safetype+"&pageNo="+pageNo+"&pageSize="+pageSize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,LOG_OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }
}
