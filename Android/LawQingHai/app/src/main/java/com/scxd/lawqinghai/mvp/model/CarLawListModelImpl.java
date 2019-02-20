package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.bean.request.QueryCarLawListRepBean;
import com.scxd.lawqinghai.bean.request.QueryCarRepBean;
import com.scxd.lawqinghai.bean.response.BaseRespoCarLawListJson;
import com.scxd.lawqinghai.bean.response.BaseRespoWarnListJson;
import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
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
public class CarLawListModelImpl implements CarLawListModel {

    /**
     *  Query Car Law List Information
     * @param context
     */
    @Override
    public void loadCarDatas(Context context, String hpzl, String hphm, int page, final MVPCallBack mvpCallBack) {

        QueryCarLawListRepBean listRepBean = new QueryCarLawListRepBean(hpzl, hphm, page, 10);

        NetWorking.requstJsonNetData("Q13", Common.QUERY, listRepBean, new DataCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.open().appendMethodB("返回Q13异常:" + e.getMessage()+e.toString()+"\n");
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {

                    BaseRespoCarLawListJson jsonObj = new Gson().fromJson(response.toString(), BaseRespoCarLawListJson.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        if (jsonObj.getData() != null) {
                            mvpCallBack.succeed(jsonObj.getData());
                        }
                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q13Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });


    }
}
