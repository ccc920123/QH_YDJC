package com.scxd.service;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by hdfs on 2018/5/15.
 */
public interface LoginService {

    public Response checkUser(String username, String password, HttpServletRequest ip, HttpSession session);

    Response checkPdaUser( BaseRequest baseRequest);

    Response changePassword(BaseRequest baseRequest) throws Exception;

    Response getUserMessage(HttpSession session, HttpServletRequest request);

}
