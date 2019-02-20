package com.scxd.service.management.impl;

import com.scxd.beans.common.KeyValueBean;
import com.scxd.beans.common.ListTotal;
import com.scxd.beans.management.OperationLogBean;
import com.scxd.beans.pojo.SCY_LOG;
import com.scxd.beans.pojo.SCY_LOG_C;
import com.scxd.common.IpAddress;
import com.scxd.common.Response;
import com.scxd.common.StringUtil;
import com.scxd.dao.mapper.SCY_LOGMapper;
import com.scxd.dao.mapper.SysOpLogBeanMapper;
import com.scxd.dao.mapper.SysOperateLogMapper;
import com.scxd.dao.mapper.SysUserSafeauditstrategylogMapper;
import com.scxd.service.OperationService;
import com.szdt.security.des.DESUtil;
import org.springframework.stereotype.Service;
import sun.security.krb5.EncryptedData;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:36 2018/9/3
 * @Modified By:
 */
@Service
public class OperationServiceImpl implements OperationService {
    @Resource
    SysOpLogBeanMapper sysOpLogBeanMapper;
    @Resource
    SysUserSafeauditstrategylogMapper sysUserSafeauditstrategylogMapper;
    @Resource
    SCY_LOGMapper scyLogMapper;

    /**
     * @param ksrq
     * @param jsrq
     * @param bmbh
     * @param czlx
     * @param czr
     * @param pageNo
     * @param pageSize
     * @param logType
     * @return
     */
    @Override
    public Response queryOperationList(String ksrq, String jsrq, String bmbh, String czlx, String czr, int pageNo, int pageSize, int logType) {
        ListTotal listTotal=null;
        try {
        DESUtil desUtil=new DESUtil("ics");
            if(logType==3)
            {
                int total=0;
                if(!"".equals(czlx))
                {

                    czlx= desUtil.encrypt(czlx);
                }
                total= scyLogMapper.selectTotal(ksrq, jsrq, bmbh, czlx, czr);
                List<SCY_LOG_C> logBeanList = null;
                List<Map<String,String>> logList=new LinkedList<>();
                if (total > 0) {
                    logBeanList = scyLogMapper.selectList(ksrq, jsrq, bmbh, czlx, czr, pageNo, pageSize);
                    if (logBeanList.size() > 0) {
                        for (SCY_LOG_C log_c : logBeanList) {
                            Map<String, String> op = new HashMap<>();
                            op.put("RN", log_c.getRn());
                            op.put("BMMC", log_c.getName());
                            op.put("OP_TIME", log_c.getXrsj());
                            op.put("OPTYPE", log_c.getEvent());
                            op.put("REALNAME", log_c.getValue());
                            op.put("id", log_c.getId());
                            op.put("OPMODULE", log_c.getModule());
                            op.put("OPRESULT", log_c.getJg());
                            logList.add(op);
                        }
                    }
                }
                listTotal = new ListTotal(logList, total);
            }else {

                int total = sysOpLogBeanMapper.queryOperationListTotal(ksrq, jsrq, bmbh, czlx, czr, logType);
                List<OperationLogBean> logBeanList = null;
                if (total > 0) {
                    try {
                        logBeanList = sysOpLogBeanMapper.queryOperationList(ksrq, jsrq, bmbh, czlx, czr, logType, pageNo, pageSize);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                listTotal = new ListTotal(logBeanList, total);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Response().success(listTotal);
    }

    @Override
    public Response queryOperationDetail(String id) {
        if (StringUtil.isEmpty(id)){
            return new Response().failure("查询数据不正确");
        }
        try {
            OperationLogBean operationLogBean = sysOpLogBeanMapper.queryOperationDetail(id);
            return new Response().success(operationLogBean);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure("查询失败");
        }
    }

    @Override
    public Response queryLoginStatisticsList(String kssj, String jssj) {
      List<Map> list=  sysOpLogBeanMapper.queryLoginStatisticsList(kssj,jssj);
        return new Response().success(list);
    }

    @Override
    public Response queryOpStatisticsList(String kssj, String jssj) {
        List<Map> list=  sysOpLogBeanMapper.queryOpStatisticsList(kssj,jssj);
        return new Response().success(list);
    }

    @Override
    public Response querySalfStatisticsList(String kssj, String jssj) {
        try {
            List<Map> list = sysOpLogBeanMapper.querySalfStatisticsList(kssj, jssj);
            return new Response().success(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response queryAuditStatisticsList(String kssj, String jssj) {
     List<Map> list=   sysUserSafeauditstrategylogMapper.queryAuditStatisticsList(kssj,jssj);
        return new Response().success(list);
    }

    /**
     * 查询ScyLog表
     * @param id
     * @return
     */
    @Override
    public Response queryScyLogDetail(String id) {
        if (StringUtil.isEmpty(id)){
            return new Response().failure("查询数据不正确");
        }
        try {
            SCY_LOG_C scyLog = scyLogMapper.selectListToId(id);
            Map<String, String> op = new HashMap<>();
            op.put("bmmc", scyLog.getName());
            op.put("czsj", scyLog.getXrsj());
            op.put("czlx", scyLog.getEvent());
           if(IpAddress.isIP(scyLog.getValue())) {  //判断是否是ip
               op.put("ip",scyLog.getValue());
           }else{

               op.put("czr", scyLog.getValue());
           }
            op.put("mk", scyLog.getModule());
            op.put("czjg", scyLog.getJg());
            op.put("ms",scyLog.getMs());


            return new Response().success(op);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure("查询失败");
        }
    }
}
