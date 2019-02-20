package com.scxd.controller.management;

import com.alibaba.fastjson.JSONObject;
import com.scxd.beans.common.User;
import com.scxd.beans.pojo.SysPdaInfo;
import com.scxd.beans.pojo.SysPdaVersion;
import com.scxd.common.Response;
import com.scxd.service.CommonService;
import com.scxd.service.PdaService;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.service.management.ifaces.PDAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 10:40 2018/6/6
 * @Modified By:
 */
@RestController
@RequestMapping("/pdas")
public class PDAController {
    int OP_MODULE=14;
    int VER_OP_MODULE=15;
    @Autowired
    private PdaService pdaS;

    @Resource
    private PDAService pdaService;
    @Resource
    private CommonService commonService;

    @Resource
    LoggerService loggerService;
    @RequestMapping(value = "/management/pda/getpdas", method = RequestMethod.GET, produces = "application/json")
    public Response getPdaMessage(String pdamc,String pdawym, String bmbh, String bhxj, String pageindex, String pagesize,HttpServletRequest request) {
        Response response=pdaService.getPdaMessage(pdamc,pdawym,bmbh,bhxj,pageindex,pagesize);
        String opcontent="bmbh="+bmbh+"&pdamc="+pdamc+"&pdawym="+pdawym+"&bhxj="+bhxj+"&pageindex="+pageindex+"&pagesize="+pagesize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

        return response;


    }
    @RequestMapping(value = "/management/pda/addpda", method = RequestMethod.POST, produces = "application/json")
    public Response addPdaInfo(@RequestBody @Valid SysPdaInfo pdaInfo, HttpSession session,HttpServletRequest request) {
        String  name =null;
        try {
            User user=commonService.getUserInfoFromSession(session);
            name=user.getRealname();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response response= pdaService.addPdaInfo(pdaInfo,name);
        String opcontent=((JSONObject) JSONObject.toJSON(pdaInfo)).toJSONString();
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,3,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }

    @RequestMapping(value = "/management/pda/deletepda", method = RequestMethod.DELETE, produces = "application/json")
    public Response deletePda(String  pdaid, HttpServletRequest request) {
        Response response= pdaService.deletePda(pdaid);
        String opcontent="pdaid="+pdaid;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,4,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return  response;
    }
    @RequestMapping(value = "/management/pda/updatepda", method = RequestMethod.POST, produces = "application/json")
    public Response updatePda(@RequestBody @Valid SysPdaInfo pdaInfo, HttpSession session,HttpServletRequest request) {
        String  name =null;
        try {
            User user=commonService.getUserInfoFromSession(session);
            name=user.getRealname();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response response= pdaService.updatePda(pdaInfo,name);
        String opcontent=((JSONObject) JSONObject.toJSON(pdaInfo)).toJSONString();
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,5,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return  response;
    }

    @RequestMapping(value = "/management/pdaVersion/addpda", method = RequestMethod.POST, produces = "application/json")
    public Response addPdaVersion(@RequestBody @Valid SysPdaVersion pdaInfo, HttpSession session,HttpServletRequest request) {
        String  name =null;
        try {
            User user=commonService.getUserInfoFromSession(session);
            name=user.getLoginname();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response response= pdaService.addPdaVersion(pdaInfo,name);
        String opcontent=((JSONObject) JSONObject.toJSON(pdaInfo)).toJSONString();
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,3,VER_OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return  response;
    }
    @RequestMapping(value = "/management/pdaVersion/getPdaVersion", method = RequestMethod.GET, produces = "application/json")
    public Response getPdaVersion( String  bmbh,String version ,String des,Integer pageindex,Integer pagesize,HttpServletRequest request) {
        Response response= pdaService.getPdaVersion(bmbh,version,des,pageindex,pagesize);
        String opcontent="bmbh="+bmbh+"&version="+version+"&des="+des+"&pageindex="+pageindex+"&pagesize="+pagesize;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,VER_OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return  response;
    }

    @RequestMapping(value = "/management/pdaVersion/deletepda", method = RequestMethod.DELETE, produces = "application/json")
    public Response deletePdaVersion(String  pdaversionid, HttpServletRequest request) {
        Response response= pdaService.deletePdaVersion(pdaversionid);
        String opcontent="pdaversionid="+pdaversionid;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,4,VER_OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return  response;
    }

}
