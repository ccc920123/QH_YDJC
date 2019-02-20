package com.scxd.service;

import java.util.List;
import java.util.Map;

/**
 *  运行情况监控服务接口
 */
public interface MonitoringService {

    //移动集成指挥接入支队
    List<Map> getIntegratedCommand(int page) throws Exception;

    //查询表空间
   Map getTableSpace()throws Exception;

    //警务通在线监控
    List<Map> getPoliceService()throws Exception;
}
