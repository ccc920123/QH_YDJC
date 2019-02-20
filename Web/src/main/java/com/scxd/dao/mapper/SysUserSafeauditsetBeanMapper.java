package com.scxd.dao.mapper;

import com.scxd.beans.pojo.SysUserSafeauditsetBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserSafeauditsetBeanMapper {

    int updateByPrimaryKeySelective(SysUserSafeauditsetBean record);

    int getsalfAuditTotal(@Param("salfType") String salfType);

    List<Map> getsalfAuditList(@Param("salfType")String salfType, @Param("pageNo")int pageNo, @Param("pageSize")int pageSize);

    SysUserSafeauditsetBean getSalfAuditDetail(@Param("id") String id);
}