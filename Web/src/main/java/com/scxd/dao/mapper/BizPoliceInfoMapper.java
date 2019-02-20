package com.scxd.dao.mapper;

import com.scxd.beans.common.PoliceBean;
import com.scxd.beans.management.FwzbhAndFwzmc;
import com.scxd.beans.pdaBeans.response.CodeValueCommon;
import com.scxd.beans.pojo.BizPoliceInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public interface BizPoliceInfoMapper {

    int deleteByPrimaryKey(String id);

    int insert(BizPoliceInfo record);

    int insertSelective(BizPoliceInfo record);

    BizPoliceInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BizPoliceInfo record);

    int updateByPrimaryKey(BizPoliceInfo record);

    List<CodeValueCommon> getPoliceInterFace(Map<String ,Object> map);

    String getPlicesByLoginName(String user);

    //删除来源ly = 1 的数据
    int deletePoliceInfoByLy(@Param("bmbh")String bmbh);
    //批量插入
    int insertPoliceInfo(@Param("policeInfos") List<BizPoliceInfo> listmap);

    String getFWZBHByPoliceBh(@Param("user") String user);

}