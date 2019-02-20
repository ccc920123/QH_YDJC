package com.scxd.controller;


import com.scxd.service.CodeService;
import com.scxd.service.MonitoringService;
import com.scxd.service.WritService;
import com.scxd.service.impl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  运行情况监控
 */
@RestController
@RequestMapping("/monitoring")
public class MonitoringController {
    int OP_MODULE=23;
    @Autowired
    private MonitoringService ms;
    @Autowired
    private DriverServiceImpl driver;
    @Autowired
    private WritService ws;
    @Autowired
    private CodeService cs;

    /**
     * 移动集成指挥接入支队
     * 由于部门过多所以需要分页展示，每页显示五条数据
     */
    @RequestMapping(value = "/IC",method = RequestMethod.POST)
    public List<Map> getIntegratedCommand(@RequestBody String page){
      try{
          return ms.getIntegratedCommand(Integer.parseInt(page));
      }catch (Exception e){
          e.printStackTrace();
          return null;
      }
    }

    /**
     * 获取表空间信息
     * @return
     */
    @RequestMapping(value = "/space",method = RequestMethod.POST)
    public Map getTableSpace(){
        try{
            return ms.getTableSpace();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取警务通信息
     */
    @RequestMapping(value = "/ps",method = RequestMethod.POST)
    public List<Map> getPoliceService(){
        try{
            return ms.getPoliceService();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试综合平台接口，以查询驾驶人信息为参考
     * 报异常则接口错误
     */
    @RequestMapping(value = "/zhpt",method = RequestMethod.POST)
    public boolean testZHPT(HttpServletRequest request){
        try{
            driver.getDriverBysFzmhm("630122199808167973",request);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 测试集成指挥平台，以文书查询为参考
     *  异常则接口异常
     */
    @RequestMapping(value = "/jczhpt",method = RequestMethod.POST)
    public boolean testJCZHPT(){
        try{
            Map map = new HashMap();
            map.put("wsbh","");
            map.put("wslb","force");
            map.put("page",1);
            ws.getWritByWslbWsbhScsj(map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 测试PDA接口
     */
    @RequestMapping(value = "/pdajk",method = RequestMethod.POST)
    public boolean testPDAJK(){
        try{
            String querydoc = "{\"bmbh\":\"630000000000\"}";
            cs.getCode(querydoc);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
