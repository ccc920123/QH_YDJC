package com.scxd.service;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

public interface UpdateBaseTableService {
    void updateBaseTable() throws ServiceException, RemoteException, UnsupportedEncodingException;
}
