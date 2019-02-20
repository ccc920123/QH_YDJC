package com.scxd.lawqinghai.mvp.model;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.LogBeanReq;
import com.scxd.lawqinghai.bean.request.UpdateDownloadBean;
import com.scxd.lawqinghai.bean.response.BaseDicationResponseJson;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.UpDataDownloadBeanRes;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.service.GpsService;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;

import okhttp3.Call;


/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WelcomeModelImpl implements WelcomModel {
    /**
     * 是否有绑定Service
     */
    /**
     * gps服务
     */
    private GpsService gpsService;
    /**
     * 是否获取成功
     */
    private boolean isGetSuccess = false;
    private MVPCallBack mBack;
    private boolean bindServiceFlag = false;

    @Override
    public void getSystemVersion(Context context, MVPCallBack<String> mvpCallBack) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            Common.VERSION = info.versionName;
            mvpCallBack.succeed(Common.VERSION);
        } catch (Exception e) {
            e.printStackTrace();
            mvpCallBack.failed(context.getString(R.string.failed_to_get_version_number));
        }
    }

    @Override
    public void startLocationService(Context context, MVPCallBack mBack) {
        startGPSService(context, mBack);
        return;
    }

    /**
     * 获取服务器版本号
     *
     * @param context
     * @param mBack
     */
    @Override
    public void getSerViceVerDion(Context context, String bmbh, final MVPCallBack mBack) {
        UpdateDownloadBean bean = new UpdateDownloadBean();
        bean.setBmbh(bmbh);
        NetWorking.requstJsonNetData("Q02", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (Common.isDataTest) {
                    onResponse("cyjtest", 1);
                } else {
                    if (e.toString().contains("java.net.SocketTimeoutException")){
                        mBack.failed("请求服务器连接超时");
                    } else {
                        mBack.failed("请求服务器失败,请联系服务人员");
                    }
                }

            }

            @Override
            public void onResponse(String response, int id) {
                try {


                    if (Common.isDataTest) {
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q02.json");
                    }
                    LogUtil.open().appendMethodB("requestQ02:" + response);
                    UpDataDownloadBeanRes jsonObj = new Gson().fromJson(response, UpDataDownloadBeanRes.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mBack.succeed(jsonObj.getData());

                    } else {

                        mBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q02Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });


    }

    @Override
    public void getDictiona(String bmbh, final MVPCallBack mBack) {
        UpdateDownloadBean bean = new UpdateDownloadBean();
        bean.setBmbh(bmbh);
        NetWorking.requstJsonNetData("Q11", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (Common.isDataTest) {
                    onResponse("cyjtest", 1);
                } else {
                    if (e.toString().contains("java.net.SocketTimeoutException")){
                        mBack.failed("请求服务器连接超时");
                    } else {
                        mBack.failed("请求服务器失败,请联系服务人员");
                    }
                }

            }

            @Override
            public void onResponse(String response, int id) {
                try {


                    if (Common.isDataTest) {
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q11.json");
                    }

                    LogUtil.open().appendMethodB("requestQ11:" + response);
                    BaseDicationResponseJson dication = new Gson().fromJson(response, BaseDicationResponseJson.class);

                    if (dication.getMeta().isSuccess()) {
                        mBack.succeed(dication.getData());


                    } else {

                        mBack.failed(dication.getMeta().getMessage());
                    }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q11Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    /**
     * 上传异常日志
     * @param wym
     * @param loginname
     * @param indata
     * @param mvpCallBack
     */
    @Override
    public void comitLog(String wym, String loginname, String indata, final MVPCallBack mvpCallBack) {


        LogBeanReq bean = new LogBeanReq(wym,loginname,indata);
        NetWorking.requstJsonNetData("W00", Common.WRITE, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mBack.failed("请求服务器连接超时");
                } else {
                    mBack.failed("请求服务器失败,请联系服务人员");
                }

            }

            @Override
            public void onResponse(String response, int id) {
                try {


                    LogUtil.open().appendMethodB("返回W00:" + response + "\n");
                    BaseResponseJson jsonObj = new Gson().fromJson(response, BaseResponseJson.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }
                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回W00Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    /**
     * <br/> 方法名称: isOPen
     * <br/> 方法详述: 判断是否开启GPS
     * <br/> 参数:context
     * <br/> 返回值:true(开启),false(未开启)
     */
    public boolean isOPen(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        //        if (gps || network) {
        //            return true;
        //        }
        if (gps) {
            return true;
        }
        return false;
    }

    /**
     * <br/> 方法名称:openGPS
     * <br/> 方法详述:强制开启GPS
     * <br/> 参数:context
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:开启失败
     */
    public void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startGPSService(Context context, MVPCallBack mBack) {
        try {
            Intent gpsIntent = new Intent();
            gpsIntent.setAction("com.scxd.lawqinghai.service.GpsService");
            gpsIntent.putExtra("exit", false);
            context.startService(gpsIntent);
            Log.e("Location", "bindGPSService");
            mBack.succeed("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
