package com.scxd.lawqinghai.mvp.model;

import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.DispatchBean;
import com.scxd.lawqinghai.bean.request.DispatchKKBean;
import com.scxd.lawqinghai.bean.request.LoginBean;
import com.scxd.lawqinghai.bean.request.WriteDispatchBean;
import com.scxd.lawqinghai.bean.response.BaseDispatchResponseJson;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.DispatchRespYjxxBean;
import com.scxd.lawqinghai.bean.response.DispatchRespcjryBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;

import okhttp3.Call;

/**
 * 描述：请求卡控的数据
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */

public class DispatchModelImpl implements DispatchModel {
    
    @Override
    public void getDispatchsbkk(String user,String sjbmbh,String ssbmbh, final MVPCallBack mvpCallBack) {
        DispatchKKBean bean = new DispatchKKBean(user,sjbmbh,ssbmbh);
        NetWorking.requstJsonNetData("Q03", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (Common.isDataTest) {
                    onResponse("cyjtest", 1);
                } else {
                    if (e.toString().contains("java.net.SocketTimeoutException")){
                        mvpCallBack.failed("请求服务器连接超时");
                    } else {
                        mvpCallBack.failed("获取设备卡口失败,请联系服务人员");
                    }
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {


                    if (Common.isDataTest) {
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q03.json");
                    }
                    BaseDispatchResponseJson jsonObj = new Gson().fromJson(response, BaseDispatchResponseJson.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q03Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    @Override
    public void getDispatchyjlx(String user, final MVPCallBack mvpCallBack) {
        DispatchBean bean = new DispatchBean(user);
        NetWorking.requstJsonNetData("Q04", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (Common.isDataTest) {
                    onResponse("cyjtest", 1);
                } else {
                    if (e.toString().contains("java.net.SocketTimeoutException")){
                        mvpCallBack.failed("请求服务器连接超时");
                    } else {
                        mvpCallBack.failed("获取预警类型失败,请联系服务人员");
                    }
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {


                    if (Common.isDataTest) {
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q04.json");
                    }
                    DispatchRespYjxxBean jsonObj = new Gson().fromJson(response, DispatchRespYjxxBean.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q04Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    @Override
    public void getDispatchcjry(String user, final MVPCallBack mvpCallBack) {
        DispatchBean bean = new DispatchBean(user);
        NetWorking.requstJsonNetData("Q05", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (Common.isDataTest) {
                    onResponse("cyjtest", 1);
                } else {
                    if (e.toString().contains("java.net.SocketTimeoutException")){
                        mvpCallBack.failed("请求服务器连接超时");
                    } else {
                        mvpCallBack.failed("获取出警失败,请联系服务人员");
                    }
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    
                
                if (Common.isDataTest) {
                    response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q05.json");
                }
                DispatchRespcjryBean jsonObj = new Gson().fromJson(response, DispatchRespcjryBean.class);
                if (jsonObj.getMeta().isSuccess()) {
                    mvpCallBack.succeed(jsonObj.getData());

                } else {

                    mvpCallBack.failed(jsonObj.getMeta().getMessage());
                }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q05Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    @Override
    public void getWirteDispatch(WriteDispatchBean bean, final MVPCallBack mvpCallBack) {
        NetWorking.requstJsonNetData("W02", Common.WRITE, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                mvpCallBack.failed("布控设置失败,请联系服务人员");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    
                BaseResponseJson jsonObj = new Gson().fromJson(response, BaseResponseJson.class);
                if (jsonObj.getMeta().isSuccess()) {
                    mvpCallBack.succeed(jsonObj.getData());

                } else {

                    mvpCallBack.failed(jsonObj.getMeta().getMessage());
                }


                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回W02Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
    }
}
