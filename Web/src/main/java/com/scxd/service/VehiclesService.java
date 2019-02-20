package com.scxd.service;

import com.scxd.beans.biz.Q09Return;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;

public interface VehiclesService {

    public Response getVehiclesInfo(BaseRequest baseRequest, HttpServletRequest request) throws Exception;
    Q09Return getVehMessage(String hphm, String hpzl, HttpServletRequest request) throws Exception;
}
