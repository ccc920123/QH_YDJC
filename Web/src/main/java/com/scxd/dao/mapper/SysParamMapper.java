package com.scxd.dao.mapper;

import com.scxd.beans.management.ParamsBean;
import com.scxd.beans.pojo.SysParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysParamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_PARAM
     *
     * @mbggenerated
     */
    int insert(SysParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_PARAM
     *
     * @mbggenerated
     */
    int insertSelective(SysParam record);

  Map<String ,String> getCacheBySysParam();

    List<ParamsBean> getParamMessage(@Param("bmbh") String bmbh,@Param("csmc") String csmc,@Param("page") String page, @Param("pageSize")String pageSize) ;

    int getParamCount(@Param("bmbh")String bmbh,@Param("csmc") String csmc);

    int updateParams(@Param("id")String id, @Param("csz")String csz);

   }