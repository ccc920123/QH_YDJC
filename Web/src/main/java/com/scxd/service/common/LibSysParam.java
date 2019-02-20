package com.scxd.service.common;

import java.util.Map;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 10:26 2018/6/14
 * @Modified By:
 */
public interface LibSysParam extends Lib {
    String getZHPTUrl();

    String getJCZHPTUrl();

    String getJCZHPTZPUrl();

    String getZHPTJKXLH();

    /**
     * 获取本省简称
     *
     * @return
     */
    String getLocal();

    String getJCZHPTJKXLH();

    String getSER_PORT();

    String getPHOTO_PATH();

    int getUserdefaltNum();

    int getDeviceDefaltNum();

    Map getCJBHMap();
}
