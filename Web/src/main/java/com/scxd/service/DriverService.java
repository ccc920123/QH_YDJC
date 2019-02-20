package com.scxd.service;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;

public interface DriverService {
//通过驾驶证号获取驾驶人信息
    public Response getDriverByjszh(BaseRequest baseRequest, HttpServletRequest request) throws Exception;
}
