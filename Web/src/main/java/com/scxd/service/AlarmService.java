package com.scxd.service;

import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 平台预警查询
 */
public interface AlarmService {
    Response queryAlarm(Map map)throws Exception;

    Response queryDetail(String yjxh, HttpServletRequest request);

    Response queryStatistics(String ssbmbh);

    Response queryStatistics_Piechart(String ssbmbh);

    Response getStatistic_docment(String ssbmbh);

    Response getOperationLastTime(String ssbmbh);

    Response getTenDayData(String ssbmbh);
}
