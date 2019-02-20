package com.scxd.service.common.impl;

import com.scxd.service.common.LibService;
import com.scxd.service.common.LibSysParam;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.Map;

/**
 * @Auther:陈攀
 * @Description:系统参数缓存
 * @Date:Created in 10:27 2018/6/14
 * @Modified By:
 */
@Component
public class LibSysParamImpl  implements LibSysParam{
    public static final String TYPE = "SYS_PARAM";
    public static final String CJBH = "SYS_CJBH";

    public static  final String ZHPT_URL="ZHPT_URL";
    public static  final String JCZHPT_URL="JCZHPT_URL";
    public static  final String JCZHPTZP_URL="JCZHPTZP_URL";
    public static  final String ZHPT_JKXLH="ZHPT_JKXLH";
    public static  final String JCZHPT_JKXLH="JCZHPT_JKXLH";
    public static  final String SERVER_PORT="SERVER_PORT";
    public static  final String PHOTO_PATH="PHOTO_PATH";
    public static  final String LOCALSIMPLENAME="LOCALSIMPLENAME";
    public static  final String USERDEFALTNUM="USERDEFALTNUM";
    public static  final String DEVICEDEFALTNUM="DEVICEDEFALTNUM";
    @Resource
    private LibService libService;
    @Override
    public String get(String key) throws Exception {
        return libService.get(TYPE, key);
    }

    @Override
    public Map getAll() throws Exception {
        return libService.getAll(TYPE);
    }

    public  String  getZHPTUrl(){
        try {
            return   get(ZHPT_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  String  getJCZHPTUrl(){
        try {
            return   get(JCZHPT_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  String  getJCZHPTZPUrl(){
        try {
            return   get(JCZHPTZP_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getZHPTJKXLH() {
        try {
            return   get(ZHPT_JKXLH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getLocal() {
        try {
            return   get(LOCALSIMPLENAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getJCZHPTJKXLH() {
        try {
            return   get(JCZHPT_JKXLH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSER_PORT() {
        try {
            return   get(SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "9995";
    }

    @Override
    public String getPHOTO_PATH() {
        try {
            return   get(PHOTO_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "127.0.0.1:9995";
    }

    @Override
    public Map getCJBHMap() {
        try {
            return libService.getAll(CJBH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int getUserdefaltNum() {
        try {
            return Integer.valueOf(get(USERDEFALTNUM));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int getDeviceDefaltNum() {
        try {
            return Integer.valueOf(get(DEVICEDEFALTNUM));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
