package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.CarLawDetailsReqBean;
import com.scxd.lawqinghai.bean.request.CarPrintBean;
import com.scxd.lawqinghai.bean.request.LedgerCarBean;
import com.scxd.lawqinghai.bean.request.LedgerDirver;
import com.scxd.lawqinghai.bean.request.LedgerDriverBean;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.request.SummaryPunishmentQueryReqBean;
import com.scxd.lawqinghai.bean.request.WSBHReqBean;
import com.scxd.lawqinghai.bean.response.BaseSummaryPunishmentDetailComitJson;
import com.scxd.lawqinghai.bean.response.BaseSummaryPunishmentDetailJson;
import com.scxd.lawqinghai.bean.response.CarLawDetailsRspBean;
import com.scxd.lawqinghai.bean.response.WsbhResJson;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
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
public class CarLawPrintModelImpl implements CarLawPrintModel {

    @Override
    public void queryCar(Context context, String hpzl, String hphm, final MVPCallBack mvpCallBack) {
        LedgerCarBean bean = new LedgerCarBean(hpzl, hphm);
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
                    LedgerQueyCarReqBean jsonObj = new Gson().fromJson(response, LedgerQueyCarReqBean.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());
                    } else {
                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }
                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q09Exception:" + e.getMessage() + "--" + e.toString() + "\n");
                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }
    
    @Override
    public void queryDriver(Context context, String sfzh, final MVPCallBack mvpCallBack) {
        LedgerDirver bean = new LedgerDirver(sfzh);
        NetWorking.requstJsonNetData("Q10", Common.QUERY, bean, new DataCallback() {
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
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q10.json");
                    }
                    LedgerDriverBean jsonObj = new Gson().fromJson(response, LedgerDriverBean.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());
                    } else {
                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }
                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q10Exception:" + e.getMessage() + "--" + e.toString() + "\n");
                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
    }

    /**
     * 查询文书编号
     *
     * @param bean
     * @param mvpCallBack
     */
    @Override
    public void queryWsbh(WSBHReqBean bean, final MVPCallBack mvpCallBack) {
        NetWorking.requstJsonNetData("Q28", Common.QUERY, bean, new DataCallback() {
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
                    LogUtil.open().appendMethodB("返回Q28:" + response + "\n");
                    WsbhResJson jsonObj = new Gson().fromJson(response, WsbhResJson.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData().getWsbh());
                    } else {
                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }
                } catch (Exception e) {
                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    @Override
    public void queryDetail(String jdsbh, final MVPCallBack mvpCallBack) {
        SummaryPunishmentQueryReqBean bean = new SummaryPunishmentQueryReqBean(jdsbh);
        NetWorking.requstJsonNetData("Q20", Common.QUERY, bean, new DataCallback() {
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
                    BaseSummaryPunishmentDetailJson jsonObj = new Gson().fromJson(response, BaseSummaryPunishmentDetailJson.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());
                    } else {
                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }
                } catch (Exception e) {
                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
    }
    
    /**
     * 提交打印机动车违法信息
     * @param context
     * @param mvpCallBack
     */
    @Override
    public void comitData(Context context, CarPrintBean bean, final MVPCallBack mvpCallBack) {

        NetWorking.requstJsonNetData("W12", Common.WRITE, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.open().appendMethodB("请求W12异常:" + e.getMessage()+e.toString()+"\n");
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
                
            }

            @Override
            public void onResponse(String response, int id) {
                try {

                    BaseSummaryPunishmentDetailComitJson jsonObj = new Gson().fromJson(response, BaseSummaryPunishmentDetailComitJson.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }
                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回W12Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
        
    }
}
