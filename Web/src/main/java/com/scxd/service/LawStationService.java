package com.scxd.service;

import com.scxd.common.Response;

import java.util.Map;

/**
 * 平台执法站信息查询接口
 */
public interface LawStationService {

    Map queryLawStation(Map map)throws Exception;

    Response queryByBmbh(String bmbh);
}
