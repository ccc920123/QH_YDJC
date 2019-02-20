package com.scxd.service.impl;

import com.scxd.beans.common.ListTotal;
import com.scxd.beans.management.SafeSJBean;
import com.scxd.beans.pojo.SysUserSafeauditsetBean;
import com.scxd.common.Response;
import com.scxd.dao.mapper.SysUserSafeauditsetBeanMapper;
import com.scxd.dao.mapper.SysUserSafeauditstrategylogMapper;
import com.scxd.service.SalfAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 13:59 2018/10/18
 * @Modified By:
 */
@Service
public class SalfAuditServiceImpl implements SalfAuditService {
    @Autowired
    SysUserSafeauditsetBeanMapper sysUserSafeauditsetBeanMapper;
    @Autowired
    SysUserSafeauditstrategylogMapper sysUserSafeauditstrategylogMapper;

    @Override
    public Response salfAuditService(String salfType, int pageNo, int pageSize) {
        int total=sysUserSafeauditsetBeanMapper.getsalfAuditTotal(salfType);
        List<Map> list=null;
        if (total>0){
            try {
            list=sysUserSafeauditsetBeanMapper.getsalfAuditList(salfType,pageNo,pageSize);
        } catch (Exception e){
                e.printStackTrace();
            }
        }
        ListTotal listTotal=new ListTotal(list,total);
        return new Response().success(listTotal) ;
    }

    @Override
    public Response getSalfAuditDetail(String id) {
      SysUserSafeauditsetBean sysUserSafeauditsetBean= sysUserSafeauditsetBeanMapper.getSalfAuditDetail(id);
      if (sysUserSafeauditsetBean!=null){
          return new Response().success(sysUserSafeauditsetBean);
      }
        return new Response().failure("查询失败");
    }

    @Override
    public Response saveSalfAudit(SysUserSafeauditsetBean sysUserSafeauditsetBean) {
       int i= sysUserSafeauditsetBeanMapper.updateByPrimaryKeySelective(sysUserSafeauditsetBean);
       if (i>0){
           return new Response().success("更新成功");
       }
        return new Response().failure("更新失败");
    }

    @Override
    public Response salfAuditLogList(String safetype, int pageNo, int pageSize) {
        int total=sysUserSafeauditstrategylogMapper.salfAuditLogListTotal(safetype);
        List<SafeSJBean> list=null;
        if (total>0){
            list=sysUserSafeauditstrategylogMapper.salfAuditLogList(safetype,pageNo,pageSize);
        }
        ListTotal listTotal=new ListTotal(list,total);
        return new Response().success(listTotal) ;
    }
}
