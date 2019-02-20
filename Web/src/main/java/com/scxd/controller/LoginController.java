package com.scxd.controller;

import com.scxd.aspect.IgnoreSecurity;
import com.scxd.beans.common.User;
import com.scxd.common.Response;
import com.scxd.service.DriverService;
import com.scxd.service.LedgerService;
import com.scxd.service.LoginService;
import com.scxd.service.VehiclesService;
import com.scxd.service.common.impl.LoggerService;
import com.scxd.service.impl.DriverServiceImpl;
import com.scxd.service.impl.VioServiceImpl;
import com.scxd.service.impl.WebJCBKServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.MalformedURLException;

/**
 * Created by hdfs on 2018/5/15.
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    int OP_MODULE=1;
    int OP_MODULE_OUT=2;
    @Resource
    private LoginService loginService;
    @Resource
    LedgerService ledgerService;
    @Resource
    LoggerService loggerService;


    @IgnoreSecurity
    @RequestMapping(value = "/loginIn", produces = "application/json")
    public Response login(@RequestBody @Valid User user, HttpServletRequest request, HttpSession session) {


        return loginService.checkUser(user.getLoginname(), user.getPassword(),request, session);
    }

    @RequestMapping(value = "/pdalogin", produces = "application/json")
    public Response loginpda(String user, String password, HttpServletRequest request, HttpSession session) {

        return new Response().success();
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Response loginOut(HttpServletRequest request, HttpSession session) {
        try {

            session.invalidate();
            loggerService.writeSecurityOperaLoggerBySession(request,2,
                    1,"退出",1,"退出成功",
                    1,1);
            loggerService.writeSecurityOperaLoggerBySession(request,2,
                    1,"退出",1,"退出成功",
                    2,1);
            return new Response().success("退出成功!");
        } catch (Exception e) {
            return new Response().failure("退出失败!");
        }
    }
    //修改用户密码
    @RequestMapping(value = "/getUserMessage",method = RequestMethod.GET)
    public Response getUserMessage(HttpSession session,HttpServletRequest request){
        try{
            return loginService.getUserMessage(session,request);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure("获取登录用户失败");
        }

    }

}
