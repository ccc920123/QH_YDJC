package com.scxd.aspect;

import com.scxd.beans.common.User;
import com.scxd.common.Response;
import com.scxd.dao.util.QueryUtil;
import com.scxd.db.JdbcUtil;
import com.scxd.service.CommonService;
import com.scxd.service.common.impl.LoggerService;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Title:安全检查切面(是否登录检查)
 * Description: 通过验证Token维持登录状态
 */
@Component
@Aspect
public class SecurityAspect {


    /**
     * Log4j日志处理
     */
    private static final Logger log = Logger.getLogger(SecurityAspect.class);

    private String requestFlag = "";
    private String requestStr = "";
    private int count = 0;
    private long time = 0;
    @Resource
    LoggerService loggerService;
    @Resource
    CommonService commonService;

    long lasttime;
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        // 从切点上获取目标方法
        Object result = null;
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        log.debug("methodSignature : " + methodSignature);
        Method method = methodSignature.getMethod();
        log.debug("Method : " + method.getName() + " : " + method.isAnnotationPresent(IgnoreSecurity.class));

        // 若目标方法忽略了安全性检查,则直接调用目标方法
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return pjp.proceed();
        }
        HttpServletResponse response = null;
        for (Object param : pjp.getArgs()) {
            if (param instanceof HttpServletResponse) {
                response = (HttpServletResponse) param;
            }
        }
        if (response == null) {
            response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();//获取response
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        requestStr = request.getRequestURI() + "||" + request.getSession().getId();
        Map<String, String> mapSJ = typeList();
        if (mapSJ.get("state").equals("1")) {
            try {
                if (requestStr.equals(requestFlag)) {
                    if ((System.currentTimeMillis() - time) <= Integer.valueOf(mapSJ.get("safechildvalue")) * 1000) {
                        count++;
                    } else {
                        count = 1;
                        time = System.currentTimeMillis();
                    }
                } else {
                    requestFlag = request.getRequestURI() + "||" + request.getSession().getId();
                    count = 1;
                    time = System.currentTimeMillis();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (count > Integer.valueOf(mapSJ.get("safevalue"))) {
                User user = commonService.getUserInfoFromSession(request.getSession());
                String content = "用户" + user.getLoginname() + "于" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                        + "请求地址" + request.getRequestURI() + "触发安全策略：" + mapSJ.get("safechildtype") + "（策略值："
                        + mapSJ.get("safevalue") + "）";
                loggerService.writeSecurityAuditLoggerByRequest("", mapSJ.get("safetype"), mapSJ.get("safechildtype"),
                        request, content);
                requestFlag = "";
                requestStr = "";
                return new Response().failure("操作请求太频繁，请稍后再试。");
            }
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("userInfo") != null) {
            try {
                result = pjp.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return result;
        } else {
            log.debug("Session已超时，正在跳转回登录页面");
            //  response.sendRedirect(request.getContextPath());
//            result=response;
            throw new TokenException("Session已超时，正在跳转回登录页面");
        }
        // return result;

    }

    public Map<String, String> typeList() throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from sys_user_safeauditset where safetype = 1\n");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sqlBuilder.toString());
            rs = ps.executeQuery();

            return QueryUtil.MapFieldValue(rs);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


}


