package com.scxd.dao.mapper;

import com.scxd.beans.pojo.SCY_LOG;
import com.scxd.beans.pojo.SCY_LOG_C;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SCY_LOGMapper {
    int deleteByPrimaryKey(String id);

    int insert(SCY_LOG record);

    int insertSelective(SCY_LOG record);

    SCY_LOG selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SCY_LOG record);

    int updateByPrimaryKey(SCY_LOG record);

    int selectTotal(@Param("kssj")String kssj,@Param("jssj")String jssj,@Param("bmbh")String bmbh, @Param("czlx")String czlx, @Param("czr")String czr);
    List<SCY_LOG_C> selectList(@Param("kssj")String kssj, @Param("jssj")String jssj, @Param("bmbh")String bmbh, @Param("czlx")String czlx, @Param("czr")String czr, @Param("pageNo")int pageNo, @Param("pageSize")int pageSize);

   SCY_LOG_C selectListToId(@Param("id")String id);
}