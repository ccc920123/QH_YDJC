package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.scxd.lawqinghai.bean.request.QueryCarRepBean;
import com.scxd.lawqinghai.bean.request.QueryDriverRepBean;
import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.bean.response.QueryDriverBean;
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
public class DriverBasicModelImpl implements DriverBasicModel {

    /**
     *  Query Car Basic Information
     * @param context
     * @param bean
     */
    @Override
    public void loadDriverDatas(Context context, QueryDriverRepBean bean, final MVPCallBack mvpCallBack) {

        NetWorking.requstJsonNetData("Q10", Common.QUERY, bean, new DataCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.open().appendMethodB("返回Q10异常:" + e.getMessage()+e.toString()+"\n");
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {


                    QueryDriverBean driverBean = JSON.parseObject(response, QueryDriverBean.class);
                    if (driverBean.getMeta().isSuccess()) {
                        mvpCallBack.succeed(driverBean.getData());

                    } else {

                        mvpCallBack.failed(driverBean.getMeta().getMessage());
                    }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q10Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });


    }
}
