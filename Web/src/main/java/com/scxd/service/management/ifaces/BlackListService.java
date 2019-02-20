package com.scxd.service.management.ifaces;

import com.scxd.common.Response;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 14:19 2018/9/28
 * @Modified By:
 */
public interface BlackListService {
    Response getBlackList(String bmbh, String type, String name, Integer pageindex, Integer pagesize);

    Response deleteBlack(String blackId);
}
