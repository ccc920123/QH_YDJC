package com.scxd.controller;

import com.alibaba.fastjson.JSONObject;
import com.scxd.beans.management.FwzbhAndFwzmc;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.service.PoliceService;
import com.scxd.service.common.impl.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 警员信息平台显示控制器
 */
@RestController
@RequestMapping("/police")
public class PoliceController {
    int OP_MODULE=8;
       @Autowired
       private PoliceService ps;
    @Resource
    LoggerService loggerService;
    /**
     * 获取警员信息列表
     * 查询条件 人员编号 人员姓名 （通过模糊查询 ）所属执法站
     */
    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
       public Response getPoliceInfo(@RequestBody Map map, HttpServletRequest request){
           try{

               Response response=ps.getPoliceInfo(map);
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

    /**
     * 通过部门编号获取执法站名称和编号，前端生成执法站下拉列表
     */
    @RequestMapping(value = "/getFwz",method = RequestMethod.POST)
    public List<FwzbhAndFwzmc> getFwz(@RequestBody String glbm){
       try{
            if (StringUtil.isEmpty(glbm))return null;
            return ps.getFwz(glbm);
       }catch(Exception e){
           e.printStackTrace();
           return null;
       }
    }

}
