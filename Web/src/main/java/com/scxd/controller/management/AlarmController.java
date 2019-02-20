package com.scxd.controller.management;

import com.alibaba.fastjson.JSONObject;
import com.scxd.beans.common.User;
import com.scxd.common.Response;
import com.scxd.service.AlarmService;
import com.scxd.service.CommonService;
import com.scxd.service.common.impl.LoggerService;
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
 * 平台预警查询控制器
 */
@RestController
@RequestMapping(value = "alarm")
public class AlarmController {
    int OP_MODULE=4;
    @Autowired
    private AlarmService alarm;
    @Resource
    private CommonService commonService;
    @Resource
    LoggerService loggerService;
    @RequestMapping(value = "/queryTable",method = RequestMethod.POST)
    public Response queryAlarm(@RequestBody Map map,HttpServletRequest request){
        try{
            Response response=alarm.queryAlarm(map);
            String opcontent=((JSONObject) JSONObject.toJSON(map)).toJSONString();
            JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
            String str = json.toString();//将json对象转换为字符串
            loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                    opcontent,response.getMeta().isSuccess()?1:0,str,2,3);
          return response;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/queryDetail",method = RequestMethod.GET)
    public Response queryDetail(String yjxh , HttpServletRequest request){
        try{
            Response response=alarm.queryDetail(yjxh,request);
            String opcontent="yjxh="+yjxh;
            JSONObject json = (JSONObject) JSONObject.toJSON(response);//将java对象转换为json对象
            String str = json.toString();//将json对象转换为字符串
            loggerService.writeSecurityOperaLoggerBySession(request,6,OP_MODULE,
                    opcontent,response.getMeta().isSuccess()?1:0,str,2,3);

            return response;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取首页的柱状图
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryStatistics/Bargraph",method = RequestMethod.GET)
    public Response queryStatistics( HttpSession session){

        String ssbmbh=getSsbmbh(session);

        return alarm.queryStatistics(ssbmbh);
    }

    /**
     * 获取首页的饼状图
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryStatistics/Piechart",method = RequestMethod.GET)
    public Response queryStatistics_Piechart( HttpSession session){
        String ssbmbh=getSsbmbh(session);
        return alarm.queryStatistics_Piechart(ssbmbh);
    }
    /**
     * 获取文书统计
     * @param session
     * @return
     */
    @RequestMapping(value = "queryStatistics/Docment",method = RequestMethod.GET)
    public Response getStatistic_docment( HttpSession session){
        String ssbmbh=getSsbmbh(session);
        return alarm.getStatistic_docment(ssbmbh);
    }
    /**
     * 获取最近5条记录
     * @param session
     * @return
     */
    @RequestMapping(value = "queryStatistics/LastTime",method = RequestMethod.GET)
    public Response getOperationLastTime( HttpSession session){
        String ssbmbh=getSsbmbh(session);
        return alarm.getOperationLastTime(ssbmbh);
    }
    /**
     * 获取最近十天的
     * @param session
     * @return
     */
    @RequestMapping(value = "queryStatistics/TenDayData",method = RequestMethod.GET)
    public Response getTenDayData( HttpSession session){
        String ssbmbh=getSsbmbh(session);
        return alarm.getTenDayData(ssbmbh);
    }

    private String  getSsbmbh(HttpSession session){
        User cachedUser = null;
        String ssbmbh=null;
        try {
            cachedUser = commonService.getUserInfoFromSession(session);
            ssbmbh=cachedUser.getSsbmbh();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return ssbmbh;
    }
}
