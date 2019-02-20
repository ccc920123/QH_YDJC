package com.scxd.controller;

import com.scxd.beans.common.User;
import com.scxd.beans.pojo.SysUser;
import com.scxd.common.Response;
import com.scxd.service.CommonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/9/23.
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @RequestMapping(value = "getUserQx")
    public Response getUserQx(HttpSession session) {

        User user = (User) session.getAttribute("userInfo");

        if (null != user) {
            return new Response().success(user);
        } else {
            return new Response().failure("获取登录用户失败");
        }

    }
}
