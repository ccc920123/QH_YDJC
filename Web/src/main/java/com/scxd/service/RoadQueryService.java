package com.scxd.service;

import com.scxd.common.Response;

/**
 * 查询道路,路段代码
 */

public interface RoadQueryService {
    Response getRoadItem(String querydoc) throws Exception;

    Response getRoadSegItem(String querydoc) throws Exception;
}
