package com.scxd.service;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 15:18 2018/6/13
 * @Modified By:
 */
public interface Q03ReturnService {
    Response getKKInterFace(BaseRequest querydoc);
    Response getYJInterFace(String querydoc);
    Response getPoliceInterFace(String querydoc);

    Response getYJDetails(BaseRequest baseRequest, HttpServletRequest request);

    Response writeQSMessage(BaseRequest baseRequest) throws UnsupportedEncodingException, RemoteException, ServiceException;

    Response getYJFeedBackDetails(String querydoc, HttpServletRequest request);

    Response writeFeedBackMessage(BaseRequest baseRequest) throws ServiceException, RemoteException, Exception;
}
