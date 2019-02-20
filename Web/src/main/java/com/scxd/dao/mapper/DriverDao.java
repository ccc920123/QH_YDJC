package com.scxd.dao.mapper;

import com.scxd.beans.biz.BizDrvInfo;
import com.scxd.beans.biz.Q10Return;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.Map;

public interface DriverDao {

    public int insertDriver(@Param("map") Map<?, ?> map);
    //通过驾驶证号获取驾驶人信息
    public Q10Return selectDriverByjszh(String jszh);
    //查询有无驾驶人信息
    public int selectDriverByzxbh(String sfzmhm);
    //查询最近一小时有无驾驶人信息
    public Q10Return selectDriverByxrsjAndjszh( String jszh);
    //删除驾驶人信息
    public int deleteDriverByzxbh(String sfzmhm);

    int insertDriverByOBJ(BizDrvInfo bizDrvInfo);
}
