package com.scxd.dao.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 运行情况监控数据访问接口
 */
public interface MonitoringMapper {

    //移动集成指挥接入支队
    List<Map> selectIntegratedCommand(String bmbh,int start,int end,Date time);
    //通过部门编号获取该部门和下级部门总数
    int selectTotalByBmbh(String bmbh);

   //查询表空间
   Map selectTableSpace();

    //警务通在线监控
    List<Map> selectPoliceService(Date time);
}
