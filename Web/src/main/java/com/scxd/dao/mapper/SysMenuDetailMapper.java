package com.scxd.dao.mapper;

import com.scxd.beans.pojo.SysMenu;
import com.scxd.beans.pojo.SysMenuDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysMenuDetailMapper {

    int insertSelective(SysMenuDetail record);
   int  updateByPrimaryKeySelective(int id);
    SysMenu selectMenuDetailByMenuIDAndQXID(@Param("parentid") int parentid, @Param("qxid") int qxid);
    List<SysMenuDetail> selectMenuDetail(@Param("menuid") int menuId);

    List<Map> selectMenuPression(@Param("userId") String userId);

    int deleteBYQXID(String id);

    int updateSfxzjy(SysMenu sysMenu);
}