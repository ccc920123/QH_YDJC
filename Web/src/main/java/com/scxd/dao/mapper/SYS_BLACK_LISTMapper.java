package com.scxd.dao.mapper;

import com.scxd.beans.pojo.SYS_BLACK_LIST;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SYS_BLACK_LISTMapper {
    int insert(SYS_BLACK_LIST record);

    int insertSelective(SYS_BLACK_LIST record);

    List<SYS_BLACK_LIST> getBlackListByValue(String value);

    int getBlackListCount(@Param("type") String type,@Param("name") String name);

    List<SYS_BLACK_LIST> getBlackListTable(@Param("type")String type, @Param("name")String name,
                                           @Param("page")Integer pageindex, @Param("pageSize")Integer pageSize);

    int deleteByID(@Param("blackId")String blackId);

    SYS_BLACK_LIST selectBeanByID(@Param("blackId")String blackId);
}