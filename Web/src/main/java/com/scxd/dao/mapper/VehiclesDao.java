package com.scxd.dao.mapper;

import com.scxd.beans.biz.BizVehInfo;
import com.scxd.beans.biz.Q09Return;
import com.scxd.beans.pojo.test.BizVehInfoBean;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.Map;

public interface VehiclesDao {
    //根据号牌号码和号牌种类查询最近一小时的机动车信息
    public Q09Return selectOneHourVehByHphmHpzl(String hphm,String hpzl);
    //根据号牌号码和号牌种类查询机动车信息
    public int selectVehByHphmHpzl(String hphm,String hpzl);
    //根据号牌号码和号牌种类删除机动车信息
    public int deleteVehByHphmHpzl(String hphm,String hpzl);
    //插入机动车信息
    public int insertVehicles(@Param("map") Map map);
    //查询机动车信息
    public Q09Return selectVehicles(String hphm,String hpzl);
    //查询号牌种类
    public String selectHpzl(String hphm);

    void insertVehMessage(BizVehInfoBean bizVehInfo);
    void insertVehMessageC21(BizVehInfoBean bizVehInfo);
}
