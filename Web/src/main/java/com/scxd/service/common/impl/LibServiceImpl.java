package com.scxd.service.common.impl;

import com.scxd.service.common.CacheMap;
import com.scxd.service.common.LibDao;
import com.scxd.service.common.LibService;
import com.sun.deploy.appcontext.AppContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component("BeanDefineConfigue")
public class LibServiceImpl implements LibService, ApplicationListener<ApplicationEvent> {


    private static final Logger log = Logger.getLogger(LibServiceImpl.class.getName());

    @Resource
    private LibDao libDao;

    /**
     * 缓存到redis
     *
     * @param type
     * @param map
     * @throws Exception
     */
    @Override
    public void cache(String type, Map<String, String> map) throws Exception {
        Map<String, String> rMap = new HashMap<>();
        rMap.putAll(map);
        CacheMap.map.put(type,rMap);
    }


    /**
     * 根据类别、key取值
     *
     * @param type
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    public String get(String type, String key) throws Exception {
        Map<String, String> rMap = CacheMap.map.get(type);
        return rMap.get(key);
    }

    /**
     * 根据类别取一类缓存
     *
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public Map getAll(String type) throws Exception {
        Map<String, String> rMap = CacheMap.map.get(type);
        return new LinkedHashMap(rMap);
    }


    /**
     * 启动的时候执行
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (!(event instanceof ContextRefreshedEvent)) {
            return;
        }
        libCache();

    }


    /**
     * 保存缓存
     */
    public void libCache() {


        /**
         * dept
         */
        try {
            Map<String, String> map = libDao.sysParam();
            cache("SYS_PARAM", map);
            log.info("****************【缓存sysparam】**************");
            Map<String, String> cjMap = libDao.getCJbhMap();
            cache("SYS_CJBH", cjMap);
            log.info("****************【缓存场景编号】**************");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("加载缓存异常：" + e.getMessage());
        }

    }


}
