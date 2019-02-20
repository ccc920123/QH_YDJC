package com.scxd.dao.mapper;

import com.scxd.beans.biz.Q02Return;
import com.scxd.beans.pojo.SysPdaVersion;

import java.util.List;
import java.util.Map;

public interface SysPdaVersionMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysPdaVersion record);

    int insertSelective(SysPdaVersion record);

    SysPdaVersion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPdaVersion record);

    int updateByPrimaryKey(SysPdaVersion record);

    List<SysPdaVersion> selectPdaVersion(Map<String ,Object> map);
    int selectPdaVersionTotal();

    //查询PDA最新版本
    public Q02Return selectPdaVersionBybmbh(String bmbh);

}