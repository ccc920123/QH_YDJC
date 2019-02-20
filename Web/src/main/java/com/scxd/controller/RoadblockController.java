package com.scxd.controller;

import com.alibaba.fastjson.JSONObject;
import com.scxd.common.Response;
import com.scxd.service.RoadblockService;
import com.scxd.service.common.impl.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 卡口备案控制器
 */
@RestController
@RequestMapping("/roadblock")
public class RoadblockController {
    int OP_MODULE=9;
    @Autowired
    private RoadblockService roadblock;
    @Resource
    LoggerService loggerService;
    /**
     * 获取卡口备案信息
     */
    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public Response getRoadblockInfo(@RequestBody Map map, HttpServletRequest request){
        try{
            Response response=roadblock.getRoadblockInfo(map);
            String opcontent=((JSONObject) JSONObject.toJSON(map)).toJSONString();
            JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
            String str = json.toString();//将json对象转换为字符串
            loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                    opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
