package com.scxd.service;

import com.scxd.beans.common.User;
import com.scxd.beans.pojo.SysUser;

import javax.servlet.http.HttpSession;

/**
 * Created by hdfs on 2018/5/15.
 */
public interface CommonService {

    public User getUserInfoFromSession(HttpSession session) throws Exception;
}
