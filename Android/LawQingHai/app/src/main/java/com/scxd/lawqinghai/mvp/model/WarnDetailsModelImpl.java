package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.QueryCarRepBean;
import com.scxd.lawqinghai.bean.request.WarnRequestBean;
import com.scxd.lawqinghai.bean.response.BaseRespoWarnListJson;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.bean.response.WarnDetailsRspBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;

import okhttp3.Call;


/**
 * @类名: ${type_name}
 * @功能描述: 预警详情内容
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnDetailsModelImpl implements WarnDetailsModel {


    @Override
    public void loadDatas(Context context, String yjxh, final MVPCallBack mvpCallBack) {

        WarnRequestBean bean = new WarnRequestBean(yjxh);
        NetWorking.requstJsonNetData("Q07", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.open().appendMethodB("返回Q07异常:" + e.getMessage() + e.toString() + "\n");
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {


                    LogUtil.open().appendMethodB("返回Q07:" + response + "\n");
                    WarnDetailsRspBean warnDetailsRspBean = JSON.parseObject(response, WarnDetailsRspBean.class);
                    if (warnDetailsRspBean.getMeta().isSuccess()) {
                        mvpCallBack.succeed(warnDetailsRspBean.getData());

                    } else {

                        mvpCallBack.failed(warnDetailsRspBean.getMeta().getMessage());
                    }

                } catch (Exception e) {
                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });

    }

    @Override
    public void loadCarDatas(Context context, String hpzl, String hphm, final MVPCallBack mvpCallBack) {

        QueryCarRepBean bean = new QueryCarRepBean(hpzl, hphm);

        NetWorking.requstJsonNetData("Q09", Common.QUERY, bean, new DataCallback() {

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


                    QueryCarBean queryCarBean = JSON.parseObject(response, QueryCarBean.class);
                    if (queryCarBean.getMeta().isSuccess()) {
                        mvpCallBack.succeed(queryCarBean.getData());

                    } else {

                        mvpCallBack.failed(queryCarBean.getMeta().getMessage());
                    }


                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q09Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
    }

    @Override
    public void committ(Context context, Object object, final MVPCallBack mvpCallBack) {

        NetWorking.requstJsonNetData("W03", Common.WRITE, object, new DataCallback() {
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
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/W03.json");
                    }
                    BaseResponseJson json = new Gson().fromJson(response.toString(), BaseResponseJson.class);
                    if (json.getMeta().isSuccess()) {
                        mvpCallBack.succeed("签收成功");
                    } else {
                        mvpCallBack.failed(json.getMeta().getMessage());
                    }


                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回W03Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
    }
}
