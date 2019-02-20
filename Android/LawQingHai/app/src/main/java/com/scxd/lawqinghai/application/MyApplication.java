package com.scxd.lawqinghai.application;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;

import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.event.CrashHandler;
import com.scxd.lawqinghai.service.GpsService;
import com.scxd.lawqinghai.service.NotificationService;
import com.scxd.lawqinghai.utils.TTSUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class MyApplication extends Application {
    
    public static MyApplication myApp;
    public static Context appContext;
    public static String jd;

    public static String getJd() {
        return jd;
    }

    public static void setJd(String jd) {
        MyApplication.jd = jd;
    }

    public static String getWd() {
        return wd;
    }

    public static void setWd(String wd) {
        MyApplication.wd = wd;
    }

    public static String wd
    
;


    public static NotificationManager notifyManager = null;
    
    public static MyApplication getApplication() {
        return myApp;
    }

    public static File getFileLocation() {
        return myApp.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
    }
    //
    public static File getPicFileLocation() {
        return myApp.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        appContext=this;

        init();
        
        GpsService.startService(this, false);
        NotificationService.startService(this, false);  //开启推送服务
        TTSUtils.getInstance().initTts();
        
        CrashHandler.getInstance().init(this);
        DictionaryManager.getInstance().init(this);
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance());
    }

    /**
     * 初始化
     */
    private void init() {
        //进行okhttp初始化配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(30000L, TimeUnit.MILLISECONDS).readTimeout(30000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    @Override
    public void onTerminate() {
        if (this.notifyManager != null) {
            notifyManager.cancelAll();
        }
        super.onTerminate();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
