package com.scxd.service;

import com.scxd.common.Response;

import java.util.Map;

/**
 *  卡口备案服务接口
 */
public interface RoadblockService {

    Response getRoadblockInfo(Map map)throws Exception;
}
