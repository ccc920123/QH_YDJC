package com.scxd.lawqinghai.mvp.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.bean.request.PhotoReqBean;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.bean.request.WarnRequestBean;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.WarnbackRspBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.widget.dialog.CustomPrograss;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringDialogCallback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnBackModelImpl implements WarnBackModel {
    @Override
    public void loadDatas(Context context, String yjxh,String sjbmbh, final MVPCallBack mvpCallBack) {
        
        WarnRequestBean bean = new WarnRequestBean(yjxh, sjbmbh);

        NetWorking.requstJsonNetData("Q08", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                
                WarnbackRspBean bean = JSON.parseObject(response, WarnbackRspBean.class);
                if (bean.getMeta().isSuccess()) {
                    mvpCallBack.succeed(bean.getData());
                } else {
                    mvpCallBack.failed(bean.getMeta().getMessage());
                }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q08Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
    }

    @Override
    public void commit(Context context, WarnBackReqBean object, final MVPCallBack mvpCallBack) {

        NetWorking.requstJsonNetData("W04", Common.WRITE, object, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    
                
                BaseResponseJson json = new Gson().fromJson(response.toString(), BaseResponseJson.class);
                if (json.getMeta().isSuccess()) {
                    mvpCallBack.succeed("反馈成功");
                } else {
                    mvpCallBack.failed(json.getMeta().getMessage());
                }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回W04Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    @Override
    public void commitPhoto(final Context context, String url, String user, String yjxh, String zpzl, List<String> 
            beans, final MVPCallBack mvpCallBack) {
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", yjxh);
        map.put("psr", user);
        map.put("zpzl", zpzl);
        
        NetWorking.requstJsonFileNetData(context, url, map, beans, 
                new StringDialogCallback((Activity) context, "正在上传照片......"){
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                try {
                    
                        
                BaseResponseJson json = new Gson().fromJson(response.toString(), BaseResponseJson.class);
                if (json.getMeta().isSuccess()) {
                    mvpCallBack.succeed("照片提交成功");
                } else {
                    mvpCallBack.failed(json.getMeta().getMessage());
                }
                } catch (Exception e){
                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
        
    }


}
