package com.scxd.service;

import com.scxd.beans.pojo.BizAlarmInfo;
import com.scxd.common.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 11:22 2018/6/15
 * @Modified By:
 */
public interface WebJCBKService {
    Response writeYJMessage(BizAlarmInfo bizAlarmInfo, HttpServletRequest request);
}
