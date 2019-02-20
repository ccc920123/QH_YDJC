package com.scxd.service.common;

import java.util.Map;

/**
 * 标题：
 * 说明:
 * 作者：武伟
 * 日期：2017/10/19
 */
public interface LibDao {
    /**
     * 代码表SYS_PARAM
     *
     * @return
     * @throws Exception
     */
    public Map<String, String> sysParam() throws Exception;


    Map<String,String> getCJbhMap() throws Exception;
}
