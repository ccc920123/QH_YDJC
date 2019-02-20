package com.scxd.controller.management;

import com.alibaba.fastjson.JSONObject;
import com.scxd.common.Response;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.service.management.ifaces.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:12 2018/9/28
 * @Modified By:
 */
@RestController
@RequestMapping("/black_list")
public class BlackListController {
    int OP_MODULE = 21;
    @Autowired
    BlackListService blackListService;
    @Autowired
    LoggerService loggerService;

    @RequestMapping(value = "/management/blacklist/getBlackList", method = RequestMethod.GET, produces = "application/json")
    public Response getBlackList(String bmbh, String type, String name, Integer pageindex, Integer pagesize, HttpServletRequest request) {
       String opcontent="bmbh="+bmbh+"&type="+type+"&name="+name+"&pageindex="+pageindex+"&pagesize="+pagesize;
        Response response =blackListService.getBlackList(bmbh, type, name, pageindex, pagesize);
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }

    @RequestMapping(value = "/management/blacklist/deleteBlack", method = RequestMethod.DELETE, produces = "application/json")
    public Response deleteBlack(String blackId, HttpServletRequest request) {
        String opcontent="blackId:"+blackId;
        Response response =blackListService.deleteBlack(blackId);
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,4,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        loggerService.writeSecurityOperaLoggerBySession(request,1,
                OP_MODULE,"删除黑名单"+opcontent,response.getMeta().isSuccess()?1:0,str,
                3,1);
        return response;
    }
}
