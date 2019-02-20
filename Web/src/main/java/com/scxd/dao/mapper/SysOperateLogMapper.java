package com.scxd.dao.mapper;

import com.scxd.beans.common.KeyValueBean;
import com.scxd.beans.management.OperationLogBean;
import com.scxd.beans.pojo.SysOperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysOperateLogMapper {

    int insertSelective(SysOperateLog record);

    List<KeyValueBean> queryOperationType();

    int queryOperationListTotal(@Param("ksrq") String ksrq,@Param("jsrq") String jsrq,
                                @Param("bmbh")String bmbh, @Param("czlx")String czlx, @Param("czr")String czr);

    List<OperationLogBean> queryOperationList(@Param("ksrq")String ksrq, @Param("jsrq")String jsrq, @Param("bmbh")String bmbh,
                                              @Param("czlx")String czlx, @Param("czr")String czr, @Param("pageNo")String pageNo, @Param("pageSize")String pageSize);

    OperationLogBean queryOperationDetail(@Param("id")String id);

    void deleteOperateLogByUsernameAndIp(@Param("username")String username, @Param("ip")String ip);

    List<SysOperateLog> getDeviceDefaltLog(@Param("ip") String ip, @Param("lasttime") Date lasttime);

    List<SysOperateLog> selectSysOperateLog(@Param("opsource")int opsurce, @Param("optype")int optype, @Param("loginname")String loginname,@Param("lasttime") Date lasttime);
}