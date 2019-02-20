package com.scxd.service.impl;

import com.scxd.beans.common.User;
import com.scxd.beans.pojo.SysUser;
import com.scxd.dao.CommonDao;
import com.scxd.service.CommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by hdfs on 2018/5/15.
 */
@Service
public class CommonServiceImpl implements CommonService {



    @Override
    public User getUserInfoFromSession(HttpSession session) throws Exception {

        User user = (User) session.getAttribute("userInfo");
        return user;
    }
}
