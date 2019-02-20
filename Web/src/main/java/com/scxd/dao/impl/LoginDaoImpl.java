package com.scxd.dao.impl;

import com.scxd.beans.common.User;
import com.scxd.dao.LoginDao;
import com.scxd.dao.mapper.SysUserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by hdfs on 2018/5/15.
 */
@Component
public class LoginDaoImpl implements LoginDao {

    @Override
    public boolean checkUser(String username, String password, HttpSession session) throws Exception {

        boolean flag = false;


        return flag;
    }

    /**
     * 将用户信息放入session
     *
     * @param session
     * @throws Exception
     */
    public void generateSession(HttpSession session) throws Exception {

    }
}
