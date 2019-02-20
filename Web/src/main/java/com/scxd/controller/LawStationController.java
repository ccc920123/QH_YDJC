package com.scxd.controller;

import com.alibaba.fastjson.JSONObject;
import com.scxd.common.Response;
import com.scxd.service.LawStationService;
import com.scxd.service.common.impl.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 执法站备案管理
 */
@RestController
@RequestMapping("/lawStation")
public class LawStationController {
    int OP_MODULE=7;
    @Autowired
    private LawStationService law;
    @Resource
    LoggerService loggerService;
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public Map queryLawStation(@RequestBody Map map){
     try{
         if (map.size() == 0)return null;
         return law.queryLawStation(map);
     }catch (Exception e){
         e.printStackTrace();
         return null;
     }
    }


    @RequestMapping(value = "/queryByBmbh",method = RequestMethod.GET)
    public Response queryByBmbh(String  bmbh, HttpServletRequest request){
        Response response=law.queryByBmbh(bmbh);
        String opcontent="bmbh="+bmbh;
        JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
        return response;
    }
}
