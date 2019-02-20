package com.scxd.dao.mapper;

import com.scxd.beans.management.SafeSJBean;
import com.scxd.beans.pojo.SysUserSafeauditstrategylog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserSafeauditstrategylogMapper {

    int insertSelective(SysUserSafeauditstrategylog record);

    int salfAuditLogListTotal(@Param("safetype") String safetype);

    List<SafeSJBean> salfAuditLogList(@Param("safetype")String safetype, @Param("pageNo")int pageNo, @Param("pageSize")int pageSize);

    List<Map> queryAuditStatisticsList(@Param("ksrq") String kssj, @Param("jsrq") String jssj);
}