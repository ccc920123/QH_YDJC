package com.scxd.service;

import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.ParseException;

public interface LedgerService {
    public Response writeLedger(BaseRequest baseRequest) throws ParseException, UnsupportedEncodingException, RemoteException, ServiceException;

    Response getLedgerList(String querydoc)throws Exception;

    Response getLedgerDetail(String querydoc)throws Exception;

    Response queryLeadgerList(String bmbh, String ksrq, String jsrq, String zfzbh, String hphm, String jszh, String pageNo, String pageSize);

    Response queryLeadgerDetail(String id, HttpServletRequest request);
}
