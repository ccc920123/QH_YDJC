package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.LoginBean;
import com.scxd.lawqinghai.bean.request.WarnListRequestBean;
import com.scxd.lawqinghai.bean.request.WarnRequestBean;
import com.scxd.lawqinghai.bean.response.BaseRespoWarnListJson;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.WarnListBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;

import java.util.List;

import okhttp3.Call;

/**
 * @类名: ${type_name}
 * @功能描述: 预警清单列表请求MOdel
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnListModelImpl implements WarnListModel {


    @Override
    public void loadDatas(Context context, String user, String ksrq, String jsrq, String zt, 
                          int page, final MVPCallBack mvpCallBack) {
        WarnListRequestBean bean = new WarnListRequestBean(user, ksrq, jsrq, zt, page, 10);
        NetWorking.requstJsonNetData("Q06", Common.QUERY, bean, new DataCallback() {
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

                    LogUtil.open().appendMethodB("返回" + "Q06" + ":" + response);
                    if (Common.isDataTest) {
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q06.json");
                    }
                    WarnListBean jsonObj = JSON.parseObject(response.toString(), WarnListBean.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        if (jsonObj.getData() != null) {
                            mvpCallBack.succeed(jsonObj.getData());
                        }
                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q06Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
    }


}
