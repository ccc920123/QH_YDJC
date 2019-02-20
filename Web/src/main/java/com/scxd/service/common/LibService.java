package com.scxd.service.common;

import java.util.Map;

/**
 * 标题：
 * 说明:
 * 作者：陈攀
 * 日期：2017/10/19
 */
public interface LibService {
    /**
     * 根据类别、key取值
     * @param type
     * @param key
     * @return
     * @throws Exception
     */
    public String get(String type,String key) throws Exception;

    /**
     * 根据类别取一类缓存
     * @param type
     * @return
     * @throws Exception
     */
    public Map getAll(String type) throws Exception;

    /**
     *
     *
     * @param type
     * @throws Exception
     */
    public void cache(String type, Map<String, String> map) throws Exception;

}