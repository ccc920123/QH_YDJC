package com.scxd.controller.management;

import com.alibaba.fastjson.JSONObject;
import com.scxd.beans.management.ParamsBean;
import com.scxd.common.Response;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.service.management.ifaces.SystemParamService;
import com.scxd.service.management.impl.SystemParamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 作者: 张翔
 * 公司：四川星盾科技股份有限公司
 * 部门：技术部 PDA
 * 创建时间: 2018/6/21 下午 02:41
 * 描述：
 * 修改人：
 * 修改时间：
 */

@RestController
@RequestMapping("/params")
public class ParamsController {
    int OP_MODULE=16;
    int Det_OP_MODULE=17;
    @Resource
    LoggerService loggerService;
    @Autowired
    private SystemParamService paramService;

    @RequestMapping(value = "/management/system/params", method = RequestMethod.GET, produces = "application/json")
    public Response getPdaMessage(String bmbh, String csmc, String page, String pagesize, HttpServletRequest request) {
        Response response=paramService.queryParams(bmbh, csmc, page, pagesize);
        String opcontent="bmbh="+bmbh+"&csmc="+csmc+"&page="+page+"&pagesize="+pagesize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }

    @RequestMapping(value = "/management/system/updateParams", method = RequestMethod.GET, produces = "application/json")
    public Response updateParams(String bmbh, String id, String csz,HttpServletRequest request) {
        Response response=paramService.updateParams(bmbh, id, csz);
        String opcontent="bmbh="+bmbh+"&id="+id+"&csz="+csz;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }

    @RequestMapping(value = "/management/department/params", method = RequestMethod.GET, produces = "application/json")
    public Response getDepartMentParamsMessage(String bmbh, String csmc, String page, String pagesize,HttpServletRequest request) {
        Response response=paramService.getDepartMentParamsMessage(bmbh, csmc, page, pagesize);
        String opcontent="bmbh="+bmbh+"&csmc="+csmc+"&page="+page+"&pagesize="+pagesize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,Det_OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }

    @RequestMapping(value = "/management/department/addParams", method = RequestMethod.POST, produces = "application/json")
    public Response addParams(@RequestBody ParamsBean paramsBean,HttpServletRequest request) {
        Response response=paramService.addParams(paramsBean);
        String opcontent=((JSONObject) JSONObject.toJSON(paramsBean)).toJSONString();
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,3,Det_OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }

    @RequestMapping(value = "/management/department/deleteParams", method = RequestMethod.POST, produces = "application/json")
    public Response deleteParams( @RequestBody Map map,HttpServletRequest request) {
        Response response=paramService.deleteParams((String) map.get("id"));
        String opcontent=(String) map.get("id");
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,4,Det_OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;
    }

    @RequestMapping(value = "/management/department/updateParams", method = RequestMethod.POST, produces = "application/json")
    public Response updateDepartmentParams(@RequestBody Map map,HttpServletRequest request) {
        Response response=paramService.updateDepartmentParams(map);
        String opcontent=((JSONObject) JSONObject.toJSON(map)).toJSONString();
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,3,Det_OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }
}
