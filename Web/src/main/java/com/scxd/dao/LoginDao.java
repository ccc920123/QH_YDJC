package com.scxd.dao;

import javax.servlet.http.HttpSession;

/**
 * Created by hdfs on 2018/5/15.
 */
public interface LoginDao {

    public boolean checkUser(String username,String password, HttpSession session) throws Exception;

}
