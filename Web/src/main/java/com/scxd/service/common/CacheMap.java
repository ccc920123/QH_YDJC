package com.scxd.service.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 15:23 2018/6/14
 * @Modified By:
 */
public class CacheMap {
    public static final Map<String,Map<String,String>> map=new HashMap<>();
    /**
     * 是否开启测试
     * true:开启测试不调用上传综合平台接口
     * false:不开启测试
     */
    public static  final  boolean isTest=false;


}
