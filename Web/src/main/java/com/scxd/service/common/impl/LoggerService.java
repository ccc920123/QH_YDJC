package com.scxd.service.common.impl;

import com.scxd.beans.common.User;
import com.scxd.beans.pdaBeans.request.PdaFeedBackRequest;
import com.scxd.beans.pojo.*;
import com.scxd.common.DateUtils;
import com.scxd.common.JSONUtil;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.*;
import com.scxd.dao.util.Md5Util;
import com.scxd.service.CommonService;
import com.szdt.security.des.DESUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:30 2018/6/12
 * @Modified By:
 */
@Service
public class LoggerService {
    @Resource
    private SysIfLogMapper sysIfLogMapper;
    @Resource
    private PdaCashBeanMapper pdaCashBeanMapper;
    @Resource
    SysOperateLogMapper sysOperateLogMapper;
    @Resource
    SysOpLogBeanMapper sysOpLogBeanMapper;
    @Resource
    SysUserSafeauditstrategylogMapper sysUserSafeauditstrategylogMapper;
    @Resource
    CommonService commonService;
    @Resource
    SCY_LOGMapper scyLogMapper;

    public void writePdaInterfaceLog(SysIfLogWithBLOBs sysPdaLog) {
        sysIfLogMapper.insert(sysPdaLog);
    }

    public void updatePdaInterfacelog(SysIfLogWithBLOBs sysPdaLogWithBLOBs) {
        sysIfLogMapper.updateByPrimaryKeyWithBLOBs(sysPdaLogWithBLOBs);
    }

    /**
     * 记录PDA崩溃日志
     *
     * @param writedoc
     * @return
     */
    public Response writePDACashLog(String writedoc) {
        Response response = null;
        if (StringUtil.isNotEmpty(writedoc)) {
            PdaCashBean pdaCashBean = JSONUtil.parseObject(writedoc, PdaCashBean.class);
            pdaCashBean.setId(UUID.randomUUID().toString());
            pdaCashBean.setCjsj(new Date());
            int i = pdaCashBeanMapper.insert(pdaCashBean);
            if (i > 0) {
                response = new Response().success("数据上传成功");
            } else {
                response = new Response().failure("数据上传失败");
            }
        } else {
            response = new Response().failure("崩溃日志数据无效");
        }
        return response;
    }

    /**
     * 记录写入综合平台的xml和返回xml
     *
     * @param requestXML
     * @param responseXML
     */
    public void writeWebLogger(String requestXML, String responseXML, String jkid) {
        String id = UUID.randomUUID().toString();
        SysIfLogWithBLOBs sysIfLogWithBLOBs = new SysIfLogWithBLOBs();
        sysIfLogWithBLOBs.setCjsj(new Date());
        sysIfLogWithBLOBs.setId(id);
        sysIfLogWithBLOBs.setJkid(jkid);
        sysIfLogWithBLOBs.setWym("8888888888");
        sysIfLogWithBLOBs.setIndata(requestXML);
        sysIfLogWithBLOBs.setOutdata(responseXML);
        writePdaInterfaceLog(sysIfLogWithBLOBs);
    }

    public void writeWebLogger(String requestXML, String responseXML, String errorMsg, String jkid) {
        String id = UUID.randomUUID().toString();
        SysIfLogWithBLOBs sysIfLogWithBLOBs = new SysIfLogWithBLOBs();
        sysIfLogWithBLOBs.setCjsj(new Date());
        sysIfLogWithBLOBs.setErrormsg(errorMsg);
        sysIfLogWithBLOBs.setId(id);
        sysIfLogWithBLOBs.setJkid(jkid);
        sysIfLogWithBLOBs.setWym("8888888888");
        sysIfLogWithBLOBs.setIndata(requestXML);
        sysIfLogWithBLOBs.setOutdata(responseXML);
        writePdaInterfaceLog(sysIfLogWithBLOBs);
    }

    /**
     * 记录操作日志
     *
     * @param opuser
     * @param optype
     */
    public void writeOperaLogger(String opuser, int optype, String opvalues, String opdevice) {
        try {
            String id = UUID.randomUUID().toString();
            SysOperateLog sysOperateLog = new SysOperateLog();
            sysOperateLog.setId(id);
            sysOperateLog.setOpuser(opuser);
            sysOperateLog.setOptype(optype);
            sysOperateLog.setOptime(new Date());
            sysOperateLog.setOpvalues(opvalues);
            sysOperateLog.setOpdevice(opdevice);
            sysOperateLog.setOpsource(1);
            sysOperateLogMapper.insertSelective(sysOperateLog);
        } catch (Exception e) {

        }
    }

    public void writeSecurityOperaLoggerBySession(HttpServletRequest request,
                                                  int opType,
                                                  int opModule,
                                                  String opContent,
                                                  int opResult,
                                                  String opResultInfo,
                                                  int logType,
                                                  int funcType) {
        HttpSession session = request.getSession();
        String opIp = request.getRemoteAddr();
        String uname = "admin";
        String realname = "超级管理员";
        String userid = "0";
        String bmbh = "630000000000";
        try {
            User user = commonService.getUserInfoFromSession(session);
            if (user != null) {
                uname = user.getLoginname();
                userid = user.getUser_id();
                bmbh = user.getSsbmbh();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        writeSecurityOperaLogger(userid, bmbh, opType, opModule, opContent
                , opResult, opResultInfo, opIp, uname,realname, logType, funcType);
    }

    /**
     * @param userid       用户id
     * @param bmbh         所属部门编号
     * @param opType       操作类型 1 登录 2 退出(登出)  3 读取(查询) 4 写入(新增) 5 其他  dmlb=1041
     * @param opModule     操作模块  dmlb=1042
     * @param opContent    操作内容
     * @param opResult     操作结果  1 成功 0 失败
     * @param opResultInfo 操作结果信息
     * @param opIp         操作IP
     * @param uname        用户名
     * @param realname     用户名
     * @param logType      日志类型 1 登录日志 2 操作日志 3 安全日志
     * @param funcType     功能类型 1 普通功能 2 核心功能 3 非常规业务功能
     */
    public void writeSecurityOperaLogger(String userid,
                                         String bmbh,
                                         int opType,
                                         int opModule,
                                         String opContent,
                                         int opResult,
                                         String opResultInfo,
                                         String opIp,
                                         String uname,
                                         String realname,
                                         int logType,
                                         int funcType) {

        try {
            String id = UUID.randomUUID().toString();
            SysOpLogBean sysOpLogBean = new SysOpLogBean();
            sysOpLogBean.setId(id);
            sysOpLogBean.setUserid(userid);
            sysOpLogBean.setOrgid(bmbh);
            sysOpLogBean.setOpTime(new Date());
            sysOpLogBean.setOpType(opType);
            sysOpLogBean.setOpModule(opModule);
            sysOpLogBean.setOpContent(opContent);
            sysOpLogBean.setOpResult(opResult);
            sysOpLogBean.setOpResultInfo(opResultInfo);
            sysOpLogBean.setOpIp(opIp);
            sysOpLogBean.setCtime(new Date());
            sysOpLogBean.setUname(uname);
            sysOpLogBean.setRealname(realname);
            sysOpLogBean.setLogType(logType);
            sysOpLogBean.setFuncType(funcType);
            String checkdigit = Md5Util.md5(sysOpLogBean.toString());
            sysOpLogBean.setCheckdigit(checkdigit);
            sysOpLogBeanMapper.insertSelective(sysOpLogBean);
        } catch (Exception e) {

        }
    }
    /**
     * 记录审计日志
     * @param safetype 审计策略类型
     * @param safechildtype  审计策略子类型
     * @param request
     * @param safecontent 审计内容
     */
    public void  writeSecurityAuditLoggerByRequest(String username, String safetype,String safechildtype,HttpServletRequest request,String safecontent){
        HttpSession session = request.getSession();
        String opIp = request.getRemoteAddr();
        String uname = username;
        try {
            User user = commonService.getUserInfoFromSession(session);
            if (user != null) {
                uname = user.getLoginname();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        writeSecurityAuditLogger(safetype,safechildtype,uname,opIp,safecontent);
    }
    /**
     * 记录审计日志
     * @param safetype 审计策略类型
     * @param safechildtype  审计策略子类型
     * @param userid  用户名
     * @param cip ip
     * @param safecontent 审计内容
     */
    public void  writeSecurityAuditLogger(String safetype,String safechildtype,String userid,String cip,String safecontent){

        try {
            String id = UUID.randomUUID().toString();
            DESUtil desUtil=new DESUtil("ics");
            SysUserSafeauditstrategylog sysUserSafeauditstrategylog=new SysUserSafeauditstrategylog();
            sysUserSafeauditstrategylog.setId(id);
            sysUserSafeauditstrategylog.setSafechildtype(safechildtype);
            sysUserSafeauditstrategylog.setSafetype(safetype);
            sysUserSafeauditstrategylog.setUserid(userid);
            sysUserSafeauditstrategylog.setCip(cip);
            sysUserSafeauditstrategylog.setSafecontent(desUtil.encrypt(safecontent));
            sysUserSafeauditstrategylog.setCheckdigit(sysUserSafeauditstrategylog.toString());
            sysUserSafeauditstrategylog.setCtime(new Date());
            sysUserSafeauditstrategylogMapper.insertSelective(sysUserSafeauditstrategylog);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 安全日志模块  scy_log
     * @param value 用户名
     * @param event 事件；1：登录；2：；3：移除黑名单
     * @param ly 来源；1：PDA；2：平台
     * @param type 类型；1234：增删改查
     * @param module 模块；1：登录；2：黑名单；
     * @param sfhxgn 是否核心功能；0：否；1：是；
     * @param sffcgyw 是否非常规业务；0：否；1：是；
     * @param jg 结果；0：失败；1：成功
     * @param ms 描述
     */
    public void writeSafetyLogger(String value,String event,String ly,String type,String module,String sfhxgn,String sffcgyw,String jg,String ms)
    {

        try {
            String id = UUID.randomUUID().toString();
            DESUtil desUtil=new DESUtil("ics");
            SCY_LOG sycLogBean=new SCY_LOG();
            sycLogBean.setId(id);
            sycLogBean.setValue(value);
            sycLogBean.setEvent(desUtil.encrypt(event));
            sycLogBean.setLy(desUtil.encrypt(ly));
            sycLogBean.setType(desUtil.encrypt(type));
            sycLogBean.setModule(desUtil.encrypt(module));
            sycLogBean.setSfhxgn(desUtil.encrypt(sfhxgn));
            sycLogBean.setSffcgyw(desUtil.encrypt(sffcgyw));
            sycLogBean.setJg(desUtil.encrypt(jg));
            sycLogBean.setMs(desUtil.encrypt(ms));
            sycLogBean.setXrsj(new java.util.Date());
           sycLogBean.setJyw(Md5Util.md5(sycLogBean.toString()));
            scyLogMapper.insert(sycLogBean);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
