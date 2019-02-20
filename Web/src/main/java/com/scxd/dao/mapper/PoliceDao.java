package com.scxd.dao.mapper;

import com.scxd.beans.common.PoliceBean;
import com.scxd.beans.management.FwzbhAndFwzmc;

import java.util.List;
import java.util.Map;

/**
 * 平台操作警员信息数据库接口
 */
public interface PoliceDao {

    //平台查询警员信息总数
    int selectPoliceTotal (Map map);

    //平台分页查询警员信息
    List<PoliceBean> selectPoliceList(Map map);

    //根据部门编号查询服务站名称和编号
    List<FwzbhAndFwzmc> selectFwz(String glbm);
}
