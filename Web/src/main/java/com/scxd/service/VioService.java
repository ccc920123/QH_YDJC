package com.scxd.service;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * 违法信息接口
 */
public interface VioService {
    //获取机动车违法信息列表
    Response getVioList(BaseRequest baseRequest, HttpServletRequest request) throws Exception;
    //获取违法基本信息
    Response getVioInfo(BaseRequest baseRequest, HttpServletRequest request) throws Exception;
    //获取违法代码列表
    Response getVioCodeList(String querydoc) throws Exception;
    //获取违法代码详情
    Response getVioCodeInfo(BaseRequest baseRequest) throws Exception;
    //获取驾驶人违法信息列表
    Response getVioDrvList(BaseRequest baseRequest, HttpServletRequest request) throws Exception;
}
