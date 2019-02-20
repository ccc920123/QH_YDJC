package com.scxd.dao.mapper;

import com.scxd.beans.management.OperationLogBean;
import com.scxd.beans.pojo.SysOpLogBean;
import com.scxd.beans.pojo.SysOperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SysOpLogBeanMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysOpLogBean record);

    SysOpLogBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysOpLogBean record);

    List<SysOpLogBean> getDeviceDefaltLog(@Param("ip") String ip, @Param("lasttime") Date lasttime, @Param("logType") int logType);

    List<SysOpLogBean> selectSysOperateLog(@Param("loginname") String loginname, @Param("lasttime") Date lasttime, @Param("logType") int logType);

    int queryOperationListTotal(@Param("ksrq") String ksrq, @Param("jsrq") String jsrq, @Param("bmbh") String bmbh,
                                @Param("czlx") String czlx, @Param("czr") String czr, @Param("logType") int logType);

    List<OperationLogBean> queryOperationList(@Param("ksrq") String ksrq, @Param("jsrq") String jsrq, @Param("bmbh") String bmbh,
                                              @Param("czlx") String czlx, @Param("czr") String czr, @Param("logType") int logType,
                                              @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    OperationLogBean queryOperationDetail(@Param("id") String id);

    List<Map> queryLoginStatisticsList(@Param("ksrq") String kssj, @Param("jsrq") String jssj);

    List<Map> queryOpStatisticsList(@Param("ksrq") String kssj, @Param("jsrq") String jssj);

    List<Map> querySalfStatisticsList(@Param("ksrq") String kssj, @Param("jsrq") String jssj);
}