package com.scxd.lawqinghai.mvp.model;

import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.ElectronicMonitoringInputQueryReqBean;
import com.scxd.lawqinghai.bean.request.NotificationOfCompulsoryMeasuresQueryReqBean;
import com.scxd.lawqinghai.bean.request.PrintCallBackReqBean;
import com.scxd.lawqinghai.bean.request.SummaryPunishmentQueryReqBean;
import com.scxd.lawqinghai.bean.response.BaseElectronicMonitoringInputDetailJson;
import com.scxd.lawqinghai.bean.response.BaseNotifcationOfCompulsoryMeasuresDetailJson;
import com.scxd.lawqinghai.bean.response.BaseSummaryPunishmentDecisionJson;
import com.scxd.lawqinghai.bean.response.BaseSummaryPunishmentDetailComitJson;
import com.scxd.lawqinghai.bean.response.BaseSummaryPunishmentDetailJson;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;

import okhttp3.Call;

/**
 * 描述： 查询打印的图片
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class PrintImageModel implements PrintImageModelImpl {


    @Override
    public void queryPrintImage(String xh, final MVPCallBack mvpCallBack) {

        PrintCallBackReqBean bean = new PrintCallBackReqBean(xh);
        NetWorking.requstJsonNetData("Q25", Common.QUERY, bean, new DataCallback() {
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
                    BaseSummaryPunishmentDetailComitJson jsonObj = new Gson().fromJson(response, BaseSummaryPunishmentDetailComitJson.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }


                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q25Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });


    }

    @Override
    public void callBackPrintImage(String xh, String type, final MVPCallBack mvpCallBack) {
        PrintCallBackReqBean bean = new PrintCallBackReqBean(xh, type);

        NetWorking.requstJsonNetData("W11", Common.WRITE, bean, new DataCallback() {
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
                if (Common.isDataTest) {
                    response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/W11.json");
                }
                LogUtil.open().appendMethodB("返回W11:" + response + "\n");
                BaseSummaryPunishmentDecisionJson jsonObj = new Gson().fromJson(response, BaseSummaryPunishmentDecisionJson.class);


                if (jsonObj.getMeta().isSuccess()) {
                    mvpCallBack.succeed("回执成功");

                } else {

                    mvpCallBack.failed(jsonObj.getMeta().getMessage());
                }


            }
        });
    }

    @Override
    public void queryDetail(final String jdsbh, final String jkid, final MVPCallBack mvpCallBack) {

        Object bean = null;
        if ("Q20".equals(jkid)) {
            bean = new SummaryPunishmentQueryReqBean(jdsbh);
        } else if ("Q21".equals(jkid)) {
            bean = new NotificationOfCompulsoryMeasuresQueryReqBean(jdsbh);

        } else if ("Q22".equals(jkid)) {
            bean = new ElectronicMonitoringInputQueryReqBean(jdsbh);
        }
        try {


            NetWorking.requstJsonNetData(jkid, Common.QUERY, bean, new DataCallback() {
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

                    if ("Q20".equals(jkid)) {

                        if (Common.isDataTest) {//测试
                            response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q20.json");
                        }
                        LogUtil.open().appendMethodB("返回Q20:" + response + "\n");
                        BaseSummaryPunishmentDetailJson jsonObj = new Gson().fromJson(response, BaseSummaryPunishmentDetailJson.class);

                        if (jsonObj.getMeta().isSuccess()) {
                            mvpCallBack.succeed(jsonObj.getData());

                        } else {

                            mvpCallBack.failed(jsonObj.getMeta().getMessage());
                        }
                    } else if ("Q21".equals(jkid)) {

                        BaseNotifcationOfCompulsoryMeasuresDetailJson jsonObj = new Gson().fromJson(response, BaseNotifcationOfCompulsoryMeasuresDetailJson.class);

                        if (jsonObj.getMeta().isSuccess()) {
                            mvpCallBack.succeed(jsonObj.getData());

                        } else {

                            mvpCallBack.failed(jsonObj.getMeta().getMessage());
                        }


                    } else if ("Q22".equals(jkid)) {

                        BaseElectronicMonitoringInputDetailJson jsonObj = new Gson().fromJson(response, BaseElectronicMonitoringInputDetailJson.class);

                        if (jsonObj.getMeta().isSuccess()) {
                            mvpCallBack.succeed(jsonObj.getData());

                        } else {

                            mvpCallBack.failed(jsonObj.getMeta().getMessage());
                        }
                    }


                }
            });
        } catch (Exception e) {
            mvpCallBack.failed("请求出现异常");
        }


    }

}
