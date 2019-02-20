package com.scxd.dao.mapper;

import com.scxd.beans.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer rId);


    int insertSelective(SysRole record);

    SysRole getSysRole(@Param("roleName") String roleName);

    List<Map<String,String>> menuRoleList(@Param("roleId")int roleId,@Param("type")int type);

    int updateByBean(SysRole role);
}