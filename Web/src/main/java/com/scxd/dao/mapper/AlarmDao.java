package com.scxd.dao.mapper;

import com.scxd.beans.common.AlarmBean;
import com.scxd.beans.management.AlarmTableBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 平台预警查询Dao接口
 */
public interface AlarmDao {

    //平台预警查询
    List<AlarmTableBean> selectAlarm(Map map);
    //查询预警信息条数
    int selectAlarmNum(Map map);

    AlarmBean getAlarmDetail(String yjxh);

    List<Map> queryStatistics(@Param("ssbmbh") String ssbmbh);

    List<Map> queryStatistics_Piechart(@Param("ssbmbh")String ssbmbh);
    List<Map> getStatistic_docment(@Param("ssbmbh")String ssbmbh);
    List<Map> getOperationLastTime(@Param("ssbmbh")String ssbmbh);
    List<Map> getTenDayData(@Param("ssbmbh")String ssbmbh);
}
