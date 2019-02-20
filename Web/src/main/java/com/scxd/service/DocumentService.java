package com.scxd.service;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther:陈攀
 * @Description:文书处理接口
 * @Date:Created in 11:26 2018/7/5
 * @Modified By:
 */
public interface DocumentService {
    Response getSimDocument(String querydoc) throws  Exception;

    Response getForceDocument(String querydoc)throws  Exception;

    Response getSimDocumentDetail(BaseRequest baseRequest)throws  Exception;

    Response getForceDocumentDetail(String querydoc) throws Exception;

    Response writeSimDocument(BaseRequest baseRequest, HttpServletRequest request) throws  Exception;
    Response writeForceDocument(BaseRequest baseRequest, HttpServletRequest request) throws  Exception;

    Response writePrintPhoto(BaseRequest baseRequest)throws  Exception;

    Response getDocumentNumber(String querydoc)throws  Exception;
}
