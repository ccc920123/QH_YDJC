package com.scxd.service;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * 违法电子监控业务接口
 */
public interface VioSurveService {
    //获取电子监控列表信息
    Response getVioSurveList(String querydoc) throws Exception;
    //获取电子监控详情
    Response getVioSure(String querydoc)throws Exception;
    //电子监控信息录入
    Response writeVioSure(BaseRequest baseRequest, HttpServletRequest request)throws Exception;
}
