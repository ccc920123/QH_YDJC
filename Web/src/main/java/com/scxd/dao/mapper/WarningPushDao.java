package com.scxd.dao.mapper;

import com.scxd.beans.biz.Q12Return;

import java.util.List;
import java.util.Map;

public interface WarningPushDao {

    //获取预警推送消息
    public Q12Return selectWarningPushByUser(String user);

    int pushBack(Map<String, Object> map);
}
