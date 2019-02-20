package com.scxd.service.impl;

import com.scxd.beans.common.User;
import com.scxd.beans.management.LoginShowMessage;
import com.scxd.beans.pdaBeans.BaseRequest;
import com.scxd.beans.pdaBeans.request.PdaUser;
import com.scxd.beans.pdaBeans.response.PdaResponse;
import com.scxd.beans.pojo.*;
import com.scxd.common.*;
import com.scxd.dao.UserDao;
import com.scxd.dao.mapper.*;
import com.scxd.dao.util.Md5Util;
import com.scxd.service.LoginService;
import com.scxd.service.common.LibSysParam;
import com.scxd.service.common.impl.LoggerService;
import com.szdt.security.des.DESUtil;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hdfs on 2018/5/15.
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    private UserDao userDao;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    BizRmConfigMapper bizRmConfigMapper;
    @Resource
    SysPdaInfoMapper sysPdaInfoMapper;

    @Resource
    LoggerService loggerService;
    @Resource
    LibSysParam libSysParam;
   /* @Resource
    SCY_LOGMapper scy_logMapper;*/
    @Resource
    SysOpLogBeanMapper sysOpLogBeanMapper;
    @Resource
    SYS_BLACK_LISTMapper sys_black_listMapper;

    @Autowired
    SysUserSafeauditsetBeanMapper sysUserSafeauditsetBeanMapper;

    /**
     * 平台登录调用接口
     *
     * @param username 用户名
     * @param password 密码
     * @param ip
     * @param session  当前session  @return
     */
    private static final String SECERT_KEY = "ics";
    @Override
    public Response checkUser(String username, String password, HttpServletRequest request, HttpSession session) {
        boolean flag = false;
        String ip=request.getRemoteAddr();
        try {
            DESUtil desUtil = new DESUtil(SECERT_KEY);
            username = desUtil.decrypt(username);
            password = desUtil.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SysUser user = sysUserMapper.selectByUserName(username);
        if (user == null) {
            String msg="用户名输入不正确";
//            loggerService.writeSecurityOperaLogger(0,"60000000000",1,
//                    1,"账号："+username+" 密码："+password,0,msg,ip
//            ,username,1,1);
            return new Response().failure(msg);
        }
        //查询黑名单表
        List<SYS_BLACK_LIST> lists = sys_black_listMapper.getBlackListByValue(username);
        if (lists != null && lists.size() > 0) {
            String msg="用户已被锁定，请联系管理员解锁";
            loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
            1,"账号："+username+" 密码："+password,0,msg,ip,user.getLoginname(),user.getRealname(),
                    2,1);
            return new Response().failure(msg);
        }
        lists = sys_black_listMapper.getBlackListByValue(ip);
        if (lists != null && lists.size() > 0) {
            String msg="终端已被锁定，请联系管理员解锁";
            loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                    1,"账号："+username+" 密码："+password,0,msg,ip,user.getLoginname(),user.getRealname(),
                    2,1);
            return new Response().failure(msg);
        }
        try {
            String content = "";
            List<Map<String, Object>> list = userDao.getUserSj();
            for (Map map : list) {
                content = "";
                String state = map.get("state").toString();
                if (map.get("safetype").equals("2")) {
                    if (map.get("id").equals("13") && state.equals("1")) {
                        String[] timeSj = map.get("safevalue").toString().split("—");
                        if (timeSj.length < 2) {
                            timeSj = map.get("safevalue").toString().split("-");
                        }
                        String format = "HH:mm";
                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置格式
                        String time = df.format(new Date());
                        Date nowTime = new SimpleDateFormat(format).parse(time);
                        Date startTime = new SimpleDateFormat(format).parse(timeSj[0]);
                        Date endTime = new SimpleDateFormat(format).parse(timeSj[1]);
                        if (isEffectiveDate(nowTime, startTime, endTime)) {
                            content = "用户" + username + "于" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                                    + "触发安全策略：" + map.get("safechildtype").toString() + "（策略值："
                                    + map.get("safevalue").toString() + "），访问被拒绝";
                            loggerService.writeSecurityAuditLoggerByRequest(username, map.get("safetype").toString(),
                                    map.get("safechildtype").toString(), request, content);
                            return new Response().failure("登录失败，拒绝" + map.get("safechildtype").toString() +
                                    "（特殊时间段：" + map.get("safevalue").toString() + "）");
                        }
                    } else if (map.get("id").equals("15") && state.equals("1")){
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置格式
                        if (map.get("safevalue").toString().equals(df.format(new Date()))){
                            content = "用户" + username + "于" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                                    + "触发安全策略：" + map.get("safechildtype").toString() + "（策略值："
                                    + map.get("safevalue").toString() + "），访问被拒绝";
                            loggerService.writeSecurityAuditLoggerByRequest(username, map.get("safetype").toString(),
                                    map.get("safechildtype").toString(), request, content);
                            return new Response().failure("登录失败，拒绝" + map.get("safechildtype").toString() +
                                    "（特殊日期：" + map.get("safevalue").toString() + "）");
                        }
                    } else if (map.get("id").equals("16") && state.equals("1")){
                        String[] timeSj = map.get("safevalue").toString().split("—");
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置格式
                        String format = "yyyy-MM-dd HH:mm";
                        String time = df.format(new Date());
                        Date nowTime = new SimpleDateFormat(format).parse(time);
                        Date startTime = new SimpleDateFormat(format).parse(timeSj[0]);
                        Date endTime = new SimpleDateFormat(format).parse(timeSj[1]);
                        if (isEffectiveDate(nowTime, startTime, endTime)){
                            content = "用户" + username + "于" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                                    + "触发安全策略：" + map.get("safechildtype").toString() + "（策略值："
                                    + map.get("safevalue").toString() + "），访问被拒绝";
                            loggerService.writeSecurityAuditLoggerByRequest(username, map.get("safetype").toString(),
                                    map.get("safechildtype").toString(), request, content);
                            return new Response().failure("登录失败，拒绝" + map.get("safechildtype").toString() +
                                    "（特殊时间段：" + map.get("safevalue").toString() + "）");
                        }
                    }
                } else if(map.get("safetype").equals("3") && state.equals("1")){
                    String ctime = userDao.getUserSj2(username).get("ctime");
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(ctime);
                    Date d2 = new Date();
                    long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
                    int days = (int) (diff / (1000 * 60 * 60 * 24));
                    if (days > Integer.valueOf(map.get("safevalue").toString())){
                        content = "用户" + username + "于" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                                + "触发安全策略：" + map.get("safechildtype").toString() + "（策略值："
                                + map.get("safevalue").toString() + "），访问被拒绝";
                        loggerService.writeSecurityAuditLoggerByRequest(username, map.get("safetype").toString(),
                                map.get("safechildtype").toString(), request, content);
                        return new Response().failure("登录失败，" + map.get("safechildtype").toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //end

        String pkey = user.getUserId() + password + user.getRealname();
        String pwd = Md5Util.md5(pkey);
        //验证密码
        Date date = new Date();
        if (user.getPassword().equals(pwd)) {
//3、用户登录成功后，该用户的连续失败次数清零，该终端的连续失败次数清零；
            //sysOperateLogMapper.deleteOperateLogByUsernameAndIp(username, ip);
            sysUserMapper.updateLoginDefaltNum(username, 0);
            //验证密码有效期，账号有效期，登录时间

            if (date.compareTo(user.getYhyxq()) > 0) {
                String msg="账号已过期";
                loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                        1,"账号："+username+" 密码："+password,0,msg,ip,user.getLoginname(),user.getRealname(),
                        2,1);
                return new Response().failure(msg);
            }
            if (date.compareTo(user.getMmyxq()) > 0) {
                String msg="密码已过期";
                loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                        1,"账号："+username+" 密码："+password,0,msg,ip,user.getLoginname(),user.getRealname(),
                        2,1);
                return new Response().failure(msg);
            }
            if (StringUtil.isNotEmpty(user.getDljssj()) && StringUtil.isNotEmpty(user.getDlkssj())) {
                DateFormat df = new SimpleDateFormat("HH:mm");
                String dd = df.format(date);
                try {
                    Date d1 = df.parse(user.getDlkssj());
                    Date d2 = df.parse(user.getDljssj());
                    Date d3 = df.parse(dd);
                    if (!DateUtils.belongCalendar(d3, d1, d2)) {
                        String msg="当前时间段不允许该账号登录";
                        loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                                1,"账号："+username+" 密码："+password,0,msg,ip,user.getLoginname(),user.getRealname(),
                                2,1);
                        return new Response().failure(msg);
                    }

                } catch (Exception e) {

                }

            }
            //开启IP限制
            if (user.getKqipxz()==1){
                if (!ip.equals(user.getIpaddress1())&&!ip.equals(user.getIpaddress2())
                        &&!ip.equals(user.getIpaddress3())){
                    String msg="当前用户不允许在当前设备登录";
                    loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                            1,"账号："+username+" 密码："+password,0,msg,ip,user.getLoginname(),user.getRealname(),
                            2,1);
                    return new Response().failure(msg);
                }
            }

            session.setAttribute("SYSUSER", user);
            User Uuser = null;
            try {
                Uuser = userDao.getUserByLoginname(username);
                session.setAttribute("userInfo", Uuser);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //用户登录成功后，更新session Map，如重复登录，强制之前session过期
            String sessionid = SessionListener.userMap.get(username);
            if(sessionid != null&&!sessionid.equals("")){
                //注销在线用户,如果session id 相同，不销毁。
                if(!sessionid.equals(session.getId())){
                    SessionListener.sessionMap.get(sessionid).invalidate();
                    SessionListener.userMap.put(username,session.getId());
                    SessionListener.sessionMap.put(session.getId(),session);
                }
            }else{
                if(SessionListener.sessionMap.containsKey(session.getId())){
                    SessionListener.sessionMap.remove(session.getId());
                    for(String skey : SessionListener.userMap.keySet()){
                        if(SessionListener.userMap.get(skey).equals(session.getId())){
                            SessionListener.userMap.remove(skey);
                        }
                    }
                }
                SessionListener.userMap.put(username,session.getId());
                SessionListener.sessionMap.put(session.getId(),session);
            }

            sysUserMapper.updateLoginIPAndDate(date, ip,username);
            loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                    1,"账号："+username+" 密码："+password,1,"成功",ip,user.getLoginname(),user.getRealname(),
                    1,1);
            loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                    1,"账号："+username+" 密码："+password,1,"成功",ip,user.getLoginname(),user.getRealname(),
                    2,1);
            return new Response().success(true);
        } else {
            //2、用户登录失败一次，该用户的连续失败次数+1，该终端的连续失败次数+1；
            loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                    1,"账号："+username+" 密码："+password,0,"密码错误",ip,user.getLoginname(),user.getRealname(),
                    1,1);
            loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                    1,"账号："+username+" 密码："+password,0,"密码错误",ip,user.getLoginname(),user.getRealname(),
                    2,1);
            sysUserMapper.updateLoginDefaltNum(username, 1);
            int zhsbcs = user.getSbcs() + 1;
            if (zhsbcs == libSysParam.getUserdefaltNum()) {
                //4、当用户的连续失败次数达到【系统参数】配置的值后，把用户名写入黑名单表，
                // 黑名单类型为0：用户黑名单，记录用户名、写入时间；
                SYS_BLACK_LIST sys_black_list = new SYS_BLACK_LIST();
                sys_black_list.setId(UUID.randomUUID().toString());
                sys_black_list.setType(0);
                sys_black_list.setMs("连续"+zhsbcs+"次登录失败");
                sys_black_list.setValue(username);
                sys_black_listMapper.insertSelective(sys_black_list);
                //当用户或终端被锁定时，写入安全日志表：用户名/终端IP、事件（0：登录）
                // 、来源:1：平台）、类型（4：查）、模块（1：登录）
                // 、是否核心功能（1：是）、是否非常规业务（1：是）、
                // 结果（0：失败）和描述（超过失败次数阈值）
                loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                        1,"账号："+username+" 密码："+password,0,"用户登录失败超过次数阈值",ip,user.getLoginname(),user.getRealname(),
                        3,1);
                loggerService.writeSafetyLogger(user.getLoginname(),"1","1","4","1","1","1","0","用户登录失败超过次数阈值");

            }
            //查询上次成功后失败次数
            List<SysOpLogBean> logList = sysOpLogBeanMapper.getDeviceDefaltLog(ip,user.getLasttime(),1);
            if (logList.size() == libSysParam.getDeviceDefaltNum()) {
                //5、当终端的连续失败次数达到【系统参数】配置的值后，
                // 把终端的IP地址写入黑名单表，黑名单类型为1：终端黑名单，记录IP地址、写入时间；
                SYS_BLACK_LIST sys_black_list = new SYS_BLACK_LIST();
                sys_black_list.setId(UUID.randomUUID().toString());
                sys_black_list.setType(1);
                sys_black_list.setMs("连续"+logList.size()+"次登录失败");
                sys_black_list.setValue(ip);
                sys_black_listMapper.insertSelective(sys_black_list);
                loggerService.writeSecurityOperaLogger(user.getUserId(),user.getSsbmbh(),1,
                        1,"账号："+username+" 密码："+password,0,"终端失败超过次数阈值",ip,user.getLoginname(),user.getRealname(),
                        3,1);
                loggerService.writeSafetyLogger(ip,"1","1","4","1","1","1","0","终端失败超过次数阈值");
            }

            return new Response().failure("登录失败！密码错误");
        }

    }

    /**
     * 查询pda用户账号密码
     *
     * @param baseRequest 传入的当前账号密码
     * @return
     */
    @Override
    public Response checkPdaUser(BaseRequest baseRequest) {
        String querydoc = baseRequest.getQuerydoc();
        PdaUser pdaUser = JSONUtil.parseObject(querydoc, PdaUser.class);
        if (pdaUser == null) {
            return new Response().failure("数据有误");
        }
        String wym = baseRequest.getWym();
        loggerService.writeOperaLogger(pdaUser.getUser(), 0, pdaUser.getUser(), wym);
        Response response = null;
        List<SysPdaInfo> pdaInfos = sysPdaInfoMapper.getPdaMessageByWym(wym);
        if (pdaInfos == null || pdaInfos.size() < 1) {
            response = new Response().failure("当前PDA未备案");
        } else {
            PdaResponse pdaResponse = sysUserMapper.selectPdaUser(pdaUser);
            if (null != pdaResponse) {
                if (pdaUser.getPassword().equals(pdaResponse.getPassword())) {
                    //密码匹配，判断该用户是否允许使用PDA
                    if (pdaResponse.getSfsypda() == 1) {
                        if (pdaResponse.getZt() == 1) {
                            int i = bizRmConfigMapper.updateBKDateByUser(pdaUser.getUser(), new Date());
                            response = new Response().success(pdaResponse);
                        } else if (pdaResponse.getZt() == 0) {
                            response = new Response().failure("该用户已被停用");
                        } else {
                            response = new Response().failure("该用户已被锁定");
                        }

                    } else {
                        response = new Response().failure("该用户不被允许使用PDA");
                    }
                } else {
                    response = new Response().failure("密码输入不正确");
                }

            } else {
                response = new Response().failure("用户名输入不正确");
            }
        }
        return response;
    }

    /**
     * 修改密码调用接口
     *
     * @param baseRequest
     * @return
     * @throws Exception
     */
    @Override
    public Response changePassword(BaseRequest baseRequest) throws Exception {
        String writedoc = baseRequest.getWritedoc();
        Response response = null;
        if (StringUtil.isNotEmpty(writedoc)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(writedoc);
            String user = map.get("user").toString();
            loggerService.writeOperaLogger(user, 1, user, baseRequest.getWym());
            PdaUser pdaUser = new PdaUser();
            pdaUser.setUser(user);
            pdaUser.setBmbh(map.get("bmbh").toString());
            pdaUser.setPassword(map.get("oldpwd").toString());
            PdaResponse pdaResponse = sysUserMapper.selectPdaUser(pdaUser);
            if (null != pdaResponse) {
                if (pdaUser.getPassword().equals(pdaResponse.getPassword())) {
                    int i = sysUserMapper.updatePassword(map);
                    if (i > 0) {
                        response = new Response().success("密码修改成功");
                    } else {
                        response = new Response().failure("密码修改失败");
                    }
                } else {
                    response = new Response().failure("密码不正确");
                }
            } else {
                response = new Response().failure("用户名不正确");
            }
        } else {
            response = new Response().failure("传入数据错误");
        }
        return response;
    }

    @Override
    public Response getUserMessage(HttpSession session, HttpServletRequest request) {
        SysUser sysUser= (SysUser) session.getAttribute("SYSUSER");
        if (sysUser!=null){
            LoginShowMessage loginShowMessage=new LoginShowMessage();
            loginShowMessage.setYhyxq(sysUser.getYhyxq());
            loginShowMessage.setMmyxq(sysUser.getMmyxq());
            loginShowMessage.setOnline(SessionListener.sessionMap.size());
            loginShowMessage.setDqdlip(request.getRemoteAddr());
            loginShowMessage.setScdlcgip(sysUser.getPreipaddress());
            loginShowMessage.setScdlcgsj(sysUser.getLasttime());
            loginShowMessage.setDqdlsj(new Date());
            List<SysOpLogBean> logList=sysOpLogBeanMapper.selectSysOperateLog(sysUser.getLoginname(),sysUser.getLasttime(),2);
            loginShowMessage.setCzrz(logList);
            return new Response().success(loginShowMessage);
        }else{
            return new Response().failure("登录信息不存在");
        }

    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
