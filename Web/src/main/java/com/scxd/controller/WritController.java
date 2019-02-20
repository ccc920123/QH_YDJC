package com.scxd.controller;

import com.alibaba.fastjson.JSONObject;
import com.scxd.beans.biz.ForceExtend;
import com.scxd.beans.biz.SurveExtend;
import com.scxd.beans.biz.ViolationExtend;
import com.scxd.common.Response;
import com.scxd.service.WritService;
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
 * 文书管理控制器
 */
@RestController
@RequestMapping("/writ")
public class WritController {
    int OP_MODULE=6;
    @Autowired
    private WritService ws;
    @Resource
    LoggerService loggerService;
    //查询文书信息
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Map selectWrite(@RequestBody Map map,HttpServletRequest request){
       try{
           if(map.size() == 0)return null;
           Map resmap=ws.getWritByWslbWsbhScsj(map);
           String opcontent=((JSONObject) JSONObject.toJSON(map)).toJSONString();
           JSONObject json = (JSONObject) JSONObject.toJSON(resmap);//将java对象转换为json对象
           String str = json.toJSONString();//将json对象转换为字符串
           loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                   opcontent,resmap!=null?1:0,str,2,3);
          return resmap;
       }catch (Exception e){
           e.printStackTrace();
           return  null;
       }
    }

    //删除文书信息
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public boolean removeWrit(@RequestBody Map map){
        try{
            if (map.size() == 0)return false;
           return ws.removeWrit(map);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     *  电子监控决定书详情查询
     */
    @RequestMapping(value = "/surve",method = RequestMethod.POST)
    public SurveExtend getSurveInfo(@RequestBody String wsbh, HttpServletRequest request){
        try{
             if (wsbh == null || wsbh.equals("")) return   null;

            SurveExtend resmap=ws.getSurveInfo(wsbh,request);
            String opcontent="wsbh="+wsbh+"&  电子监控决定书";
            JSONObject json = (JSONObject) JSONObject.toJSON(resmap);//将java对象转换为json对象
            String str = json.toJSONString();//将json对象转换为字符串
            loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                    opcontent,resmap!=null?1:0,str,2,3);
            return resmap;
        }catch(Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    /**
     *  强制措施决定书详情查询
     */
    @RequestMapping(value = "/force",method = RequestMethod.POST)
    public ForceExtend getForceInfo(@RequestBody String wsbh,HttpServletRequest request){
        try{
            if (wsbh == null || wsbh.equals("")) return   null;

            ForceExtend resmap=ws.getForceInfo(wsbh);
            String opcontent="wsbh="+wsbh+"&  强制措施决定书";;
            JSONObject json = (JSONObject) JSONObject.toJSON(resmap);//将java对象转换为json对象
            String str = json.toJSONString();//将json对象转换为字符串
            loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                    opcontent,resmap!=null?1:0,str,2,3);
            return resmap;
        }catch(Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    /**
     * 简易处罚决定书详情
     */
    @RequestMapping(value = "/violation",method = RequestMethod.POST)
    public ViolationExtend getViolationInfo(@RequestBody String wsbh,HttpServletRequest request){
        try{
            if (wsbh == null || wsbh.equals("")) return   null;

            ViolationExtend resmap=ws.getViolationInfo(wsbh);
            String opcontent="wsbh="+wsbh+"&  简易处罚书";
            JSONObject json = (JSONObject) JSONObject.toJSON(resmap);//将java对象转换为json对象
            String str = json.toJSONString();//将json对象转换为字符串
            loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                    opcontent,resmap!=null?1:0,str,2,3);
            return resmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
