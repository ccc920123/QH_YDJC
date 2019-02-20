package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.scxd.lawqinghai.bean.request.CarLawDetailsReqBean;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.CarLawDetailsRspBean;
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
public class CarLawDetailsModelImpl implements CarLawDetailsModel {

    /**
     * 机动车违法详细信息查询
     * @param context
     * @param xh
     * @param mvpCallBack
     */
    @Override
    public void load(Context context, String xh, String cxly, final MVPCallBack mvpCallBack) {

        CarLawDetailsReqBean reqBean = new CarLawDetailsReqBean(xh, cxly);
        NetWorking.requstJsonNetData("Q14", Common.QUERY, reqBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.open().appendMethodB("返回Q14异常:" + e.getMessage()+e.toString()+"\n");
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
                
            }

            @Override
            public void onResponse(String response, int id) {
                try {

                    CarLawDetailsRspBean carBean = JSON.parseObject(response, CarLawDetailsRspBean.class);
                    if (carBean.getMeta().isSuccess()) {
                        mvpCallBack.succeed(carBean.getData());

                    } else {

                        mvpCallBack.failed(carBean.getMeta().getMessage());
                    }
                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q14Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
        
    }
}
