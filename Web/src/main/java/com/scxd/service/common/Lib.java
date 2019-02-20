package com.scxd.service.common;

import java.util.Map;

/**
 * 标题：常用参数缓存
 * 说明:
 * 作者：陈攀
 * 日期：2018/6/14
 */
public interface Lib {
    /**
     * 根据代码取值
     * @param key
     * @return
     */
    public String get(String key) throws Exception;

    /**
     * 取全部值的map
     * @return
     */
    public Map getAll() throws Exception;
}