package com.scxd.dao.mapper;

import com.scxd.beans.management.ParamsBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysDepartmentParamMapper {
    String getCSZByBmbhAndCsdm(@Param("bmbh") String bmbh, @Param("csz") String csz);

    List<String> getZDBMBHs();


    ParamsBean getDepartParamMessage(@Param("bmbh") String bmbh,@Param("csz") String csz);

    List<String> getAllParamDM(@Param("csmc") String csmc, @Param("page") String page, @Param("pageSize") String pageSize);

    int updateDepartmentParams(Map map);

    int deleteByID(String id);

    int insertBean(ParamsBean paramsBean);
}
