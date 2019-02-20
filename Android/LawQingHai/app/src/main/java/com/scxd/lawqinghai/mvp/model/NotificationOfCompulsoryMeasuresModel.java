package com.scxd.lawqinghai.mvp.model;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.NotificationOfCompulsoryMeasuresReqBean;
import com.scxd.lawqinghai.bean.request.SummaryPunishmentDecisionReqBean;
import com.scxd.lawqinghai.bean.response.BaseNotifcationOfCompulsoryMeasuresJson;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresResBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;

import okhttp3.Call;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/3
 * 修改人：
 * 修改时间：
 */


public class NotificationOfCompulsoryMeasuresModel implements NotificationOfCompulsoryMeasuresModelImp{


    /**
     * 开始请求服务器数据
     *
     * @param mvpCallBack
     */
    @Override
    public void backNotificationOfCompulsoryMeasures(String user,String bmbh,String starTime, String endTime, int page, final MVPCallBack mvpCallBack) {

        NotificationOfCompulsoryMeasuresReqBean object=new NotificationOfCompulsoryMeasuresReqBean(user,bmbh,
                starTime,endTime,page,10);


        NetWorking.requstJsonNetData("Q18", Common.QUERY, object, new DataCallback() {
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
                    response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q01.json");
                }
                    NotificationOfCompulsoryMeasuresResBean jsonObj = JSON.parseObject(response,
                            NotificationOfCompulsoryMeasuresResBean.class);


                if (jsonObj.getMeta().isSuccess()) {
                    mvpCallBack.succeed(jsonObj.getData());

                } else {

                    mvpCallBack.failed(jsonObj.getMeta().getMessage());
                }


                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q18Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });


    }
}
