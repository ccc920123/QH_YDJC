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
public class MainModelImpl implements MainModel {
  

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
                    LogUtil.open().appendMethodB("返回Q02MainActivity:" + response);
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
    

}
