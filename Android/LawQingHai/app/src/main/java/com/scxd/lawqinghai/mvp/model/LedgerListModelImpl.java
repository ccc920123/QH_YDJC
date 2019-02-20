package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.LedgerListRequestBean;
import com.scxd.lawqinghai.bean.request.WarnListRequestBean;
import com.scxd.lawqinghai.bean.response.BaseRespoWarnListJson;
import com.scxd.lawqinghai.bean.response.LedgerListRespBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;

import org.json.JSONObject;

import okhttp3.Call;

/**
 * @类名: ${type_name}
 * @功能描述: 台账清单列表请求MOdel
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LedgerListModelImpl implements LedgerListModel {


    @Override
    public void loadDatas(Context context, String user, String ksrq, String jsrq, int page, final MVPCallBack 
            mvpCallBack) {
        LedgerListRequestBean bean = new LedgerListRequestBean(user, ksrq, jsrq, page, 10);
        NetWorking.requstJsonNetData("Q29", Common.QUERY, bean, new DataCallback() {
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

                    LedgerListRespBean jsonObj = JSON.parseObject(response, LedgerListRespBean.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        if (jsonObj.getData() != null) {
                            mvpCallBack.succeed(jsonObj.getData());
                        }
                    } else {
                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q30Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
    }


}
