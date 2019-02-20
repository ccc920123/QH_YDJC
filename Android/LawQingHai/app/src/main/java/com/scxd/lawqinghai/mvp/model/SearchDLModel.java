package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.LoginBean;
import com.scxd.lawqinghai.bean.request.RQVersionBean;
import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.DLRespJson;
import com.scxd.lawqinghai.bean.response.LDRespJson;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * @类名: ${type_name}
 * @功能描述: 预警清单列表请求
 * @作者: ${user}
 * @时间: ${date}activity_login.xml
 * @最后修改者:
 * @最后修改内容:
 */
public class SearchDLModel implements SearchDLModelImp {


    @Override
    public void searchDL(SearchDLReqBean bean, final MVPCallBack mvpCallBack) {
        NetWorking.requstJsonNetData("Q26", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (Common.isDataTest) {
                    onResponse("cyjtest", 1);
                } else {
                    if (e.toString().contains("java.net.SocketTimeoutException")){
                        mvpCallBack.failed("请求服务器连接超时");
                    } else {
                        mvpCallBack.failed("请求服务器失败,请联系服务人员");
                    }
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {

                    if (Common.isDataTest) {
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q26.json");
                    }
                    DLRespJson jsonObj = new Gson().fromJson(response, DLRespJson.class);


                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q26Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    @Override
    public void searchLD(SearchLDReqBean bean, final MVPCallBack mvpCallBack) {
        NetWorking.requstJsonNetData("Q27", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (Common.isDataTest) {
                    onResponse("cyjtest", 1);
                } else {
                    if (e.toString().contains("java.net.SocketTimeoutException")){
                        mvpCallBack.failed("请求服务器连接超时");
                    } else {
                        mvpCallBack.failed("请求服务器失败,请联系服务人员");
                    }
                }

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    if (Common.isDataTest) {
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q27.json");
                    }

                    LDRespJson jsonObj = new Gson().fromJson(response, LDRespJson.class);


                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.succeed(null);
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q27Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }
}
