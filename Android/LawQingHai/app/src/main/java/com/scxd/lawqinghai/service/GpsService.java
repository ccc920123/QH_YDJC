package com.scxd.lawqinghai.service;

import android.Manifest;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.utils.IntentUtils;
import com.scxd.lawqinghai.utils.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author chenyujin
 * @ClassName GpsService
 * @Description GPS定位
 * @date 2016-9-6 上午9:53:26
 * 不断获取Gps
 */
public class GpsService extends Service {

    private static Context context;
    private LocationManager locationManager;
    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            stopSelf();
            return Service.START_NOT_STICKY;
        }
        final boolean isExit = intent.getBooleanExtra("exit", false);
        if (isExit) {
            if (timer != null) {
                timer.cancel();
            }
            stopSelf();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                isOPen(getApplication());
            }
        },0, 5 * 1000);


        openGPSlocation();
        getLocation(3000);
        return Service.START_STICKY;

    }


    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public static final boolean isOPen(final Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）  
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）  
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps) {
            return true;
        }
        return false;
    }

    /**
     * 强制帮用户打开GPS
     *
     * @param context
     */
    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 开启GPS定位
     */
    public void openGPSlocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        }
        Criteria criteria = new Criteria();
        // 设置定位精确度
        // Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // 设置是否要求速度
        criteria.setSpeedRequired(false);
        // 设置是否允许运营商收费
        criteria.setCostAllowed(false);
        // 设置是否需要方位信息
        criteria.setBearingRequired(false);
        // 设置是否需要海拔信息
        criteria.setAltitudeRequired(false);
        // 设置对电源的需求
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        // 为获取地理位置信息时设置查询条件
        String bestProvider = locationManager.getBestProvider(criteria, true);

        // 获取位置信息
        // 如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER
        // Location location =
        // locationManager.getLastKnownLocation(bestProvider);

    }

    public void getLocation(long timeMills) {
        // 监听状态
        // locationManager.addGpsStatusListener(new
        // myGpsStatusListener());
        // 绑定监听，有4个参数
        // 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种
        // 参数2，位置信息更新周期，单位毫秒
        // 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
        // 参数4，监听
        // 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新
        // 1秒更新一次，或最小位移变化超过1米更新一次；
        if (checkGPSPermission()) {
            Log.e("Location", "getLocation is return");
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, timeMills, 0, myLocationListener);
        Log.e("Location", "开启监听");
    }

    /**
     * GPS定位类
     *
     * @author wuwei
     */
    private LocationListener myLocationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                MyApplication.setJd(String.valueOf(location.getLongitude()));
                MyApplication.setWd(String.valueOf(location.getLatitude()));
                LogUtil.open2().appendMethod(
                        "{'LATITUDE':'" + location.getLatitude() + "','LONGITUDE':'" + location.getLongitude() +
                                "'},", "/location.txt");
              //TODO GPS

            }
            Log.e("Location", "getLocation is onLocationChanged");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e("Location", "getLocation is onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e("Location", "getLocation is onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e("Location", "getLocation is onProviderDisabled");
        }

    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (checkGPSPermission()) {
            Log.e("Location", "onDestroy is return");
            return;
        }
        try {
            locationManager.removeUpdates(myLocationListener);
        } catch (Exception e) {

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * <br/> 方法名称: checkGPSPermission
     * <br/> 方法详述: 检查当前GPS权限是否开启
     * <br/> 参数:
     * <br/> 返回值: true(开启),false(未开启)
     */
    private boolean checkGPSPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.e("Location", "checkGPSPermission is return");
            return true;
        }
        return false;
    }

    /**
     * <br/> 方法名称:startService
     * <br/> 方法详述:启动时间服务
     * <br/> 参数:
     * <br/>&nbsp;&nbsp;&nbsp;&nbsp;mContext:上下文依赖
     * <br/>&nbsp;&nbsp;&nbsp;&nbsp;isExit:
     * <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;true:停止当前服务
     * <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;false:启动当前服务
     */
    public static void startService(Context mContext, boolean isExit) {
        context=mContext;
        openGPS(mContext);
        Intent gpsIntent = new Intent();
        gpsIntent.setAction("com.scxd.lawqinghai.service.GpsService");
        gpsIntent.putExtra("exit", isExit);
        //        gpsIntent.setPackage(mContext.getPackageName());
        Intent eintent = new Intent(IntentUtils.getExplicitIntent(mContext, gpsIntent));
        mContext.startService(eintent);


        Log.e("Location", "bindGPSService");
    }
}
