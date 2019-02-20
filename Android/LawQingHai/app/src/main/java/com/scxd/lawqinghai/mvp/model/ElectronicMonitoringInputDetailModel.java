package com.scxd.lawqinghai.mvp.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.ElectronicMonitoringInputDetailReqBan;
import com.scxd.lawqinghai.bean.request.ElectronicMonitoringInputQueryReqBean;
import com.scxd.lawqinghai.bean.request.ElectronicMonitoringInputReqBean;
import com.scxd.lawqinghai.bean.request.LedgerCarBean;
import com.scxd.lawqinghai.bean.request.LedgerDirver;
import com.scxd.lawqinghai.bean.request.LedgerDriverBean;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.request.NotificationOfCompulsoryMeasuresDetailReqBan;
import com.scxd.lawqinghai.bean.request.NotificationOfCompulsoryMeasuresQueryReqBean;
import com.scxd.lawqinghai.bean.request.QueryCodeReqBean;
import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.bean.request.WSBHReqBean;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.bean.response.BaseCodeDetailsResponseJson;
import com.scxd.lawqinghai.bean.response.BaseElectronicMonitoringInputDetailJson;
import com.scxd.lawqinghai.bean.response.BaseNotifcationOfCompulsoryMeasuresDetailJson;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.BaseSummaryPunishmentDetailComitJson;
import com.scxd.lawqinghai.bean.response.DLRespJson;
import com.scxd.lawqinghai.bean.response.LDRespJson;
import com.scxd.lawqinghai.bean.response.WsbhResJson;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.BitmapUtil;
import com.scxd.lawqinghai.utils.LogUtil;
import com.zhy.http.okhttp.callback.StringDialogCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * 描述：开始请求数据
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class ElectronicMonitoringInputDetailModel implements ElectronicMonitoringInputDetailModelImp {


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
                    //                response="{\"meta\":{\"success\":true,\"message\":\"ok\"},\"data\":{\"zdcllx\":null,\"cllx\":\"未知\",\"wfcs\":0,\"yqwjy\":null,\"yqwbf\":null,\"lxdh\":null,\"lxdz\":null,\"hphm\":\"川A00012\",\"hpzl\":\"2\",\"clpp\":\"梅赛德斯\",\"sfdqc\":null,\"csys\":\"黑色\",\"zsxzqh\":null,\"yjdcbgyy\":null,\"hpqyrq\":null,\"ccdjrq\":\"2018-01-01\",\"jdcsyr\":\"王思聪\",\"syxz\":\"非运营\",\"clsbdh\":\"MSDGDKASK21938\",\"gcjk\":null,\"bxzzrq\":null,\"hgbzbh\":null,\"fdjh\":\"MGKDAK2403929\",\"xszbh\":null,\"clzt\":\"1\",\"pl\":\"8.0\",\"gl\":\"大\",\"dybj\":null,\"wkc\":0.0,\"jyyxqz\":null,\"wkk\":0.0,\"wkg\":0.0,\"fzjg\":null}}";
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
    public void loadCommit(Context context, ElectronicMonitoringInputDetailReqBan bean, final MVPCallBack mvpCallBack) {
        NetWorking.requstJsonNetData("W10", Common.WRITE, bean, new DataCallback() {
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


                    BaseSummaryPunishmentDetailComitJson jsonObj = new Gson().fromJson(response, BaseSummaryPunishmentDetailComitJson.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }


                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回W10Exception:" + e.getMessage() + "--" + e.toString() + "\n");
                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });

    }

    @Override
    public void queryDetail(String jdsbh, final MVPCallBack mvpCallBack) {

        ElectronicMonitoringInputQueryReqBean bean = new ElectronicMonitoringInputQueryReqBean(jdsbh);
        NetWorking.requstJsonNetData("Q22", Common.QUERY, bean, new DataCallback() {
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

                    //                response="{\"meta\":{\"success\":true,\"message\":\"ok\"},\"data\":{\"zdcllx\":null,\"cllx\":\"未知\",\"wfcs\":0,\"yqwjy\":null,\"yqwbf\":null,\"lxdh\":null,\"lxdz\":null,\"hphm\":\"川A00012\",\"hpzl\":\"2\",\"clpp\":\"梅赛德斯\",\"sfdqc\":null,\"csys\":\"黑色\",\"zsxzqh\":null,\"yjdcbgyy\":null,\"hpqyrq\":null,\"ccdjrq\":\"2018-01-01\",\"jdcsyr\":\"王思聪\",\"syxz\":\"非运营\",\"clsbdh\":\"MSDGDKASK21938\",\"gcjk\":null,\"bxzzrq\":null,\"hgbzbh\":null,\"fdjh\":\"MGKDAK2403929\",\"xszbh\":null,\"clzt\":\"1\",\"pl\":\"8.0\",\"gl\":\"大\",\"dybj\":null,\"wkc\":0.0,\"jyyxqz\":null,\"wkk\":0.0,\"wkg\":0.0,\"fzjg\":null}}";
                    BaseElectronicMonitoringInputDetailJson jsonObj = new Gson().fromJson(response, BaseElectronicMonitoringInputDetailJson.class);

                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q22Exception:" + e.getMessage() + "--" + e.toString() + "\n");
                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });


    }

    @Override
    public void commitPhoto(Context context, String url, String user, String yjxh, String zpzl, List<String> beans, final MVPCallBack mvpCallBack) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", yjxh);
        map.put("psr", user);
        map.put("zpzl", zpzl);

        NetWorking.requstJsonFileNetData(context, url, map, beans,
                new StringDialogCallback((Activity) context, "正在上传照片......") {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        super.onError(call, e, id);
                        if (e.toString().contains("java.net.SocketTimeoutException")){
                            mvpCallBack.failed("请求服务器连接超时");
                        } else {
                            mvpCallBack.failed("请求服务器失败,请联系服务人员");
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        super.onResponse(response, id);
                        try {

                            BaseResponseJson json = new Gson().fromJson(response.toString(), BaseResponseJson.class);
                            if (json.getMeta().isSuccess()) {
                                mvpCallBack.succeed("照片提交成功");
                            } else {
                                mvpCallBack.failed(json.getMeta().getMessage());
                            }
                        } catch (Exception e) {
                            mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                        }
                    }
                });


    }

    /**
     * 写入反馈信息
     *
     * @param context
     * @param object
     * @param mvpCallBack
     */
    @Override
    public void commitBackDetails(Context context, WarnBackReqBean object, final MVPCallBack mvpCallBack) {
        NetWorking.requstJsonNetData("W04", Common.WRITE, object, new DataCallback() {
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

                    BaseResponseJson json = new Gson().fromJson(response.toString(), BaseResponseJson.class);
                    if (json.getMeta().isSuccess()) {
                        mvpCallBack.succeed("反馈成功");
                    } else {
                        mvpCallBack.failed(json.getMeta().getMessage());
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回W04Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    @Override
    public void commitBackPhoto(Context context, String url, String user, String yjxh, String zpzl, List<String> beans, final MVPCallBack mvpCallBack) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", yjxh);
        map.put("psr", user);
        map.put("zpzl", zpzl);

        NetWorking.requstJsonFileNetData(context, url, map, beans,
                new StringDialogCallback((Activity) context, "正在上传照片......") {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        super.onError(call, e, id);
                        if (e.toString().contains("java.net.SocketTimeoutException")){
                            mvpCallBack.failed("请求服务器连接超时");
                        } else {
                            mvpCallBack.failed("请求服务器失败,请联系服务人员");
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        super.onResponse(response, id);
                        try {


                            BaseResponseJson json = new Gson().fromJson(response.toString(), BaseResponseJson.class);
                            if (json.getMeta().isSuccess()) {
                                mvpCallBack.succeed("照片提交成功");
                            } else {
                                mvpCallBack.failed(json.getMeta().getMessage());
                            }
                        } catch (Exception e) {
                            mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                        }
                    }
                });

    }

    @Override
    public void loadCodeDatas(Context context, String wfdm, final MVPCallBack mvpCallBack) {


        QueryCodeReqBean listRepBean = new QueryCodeReqBean(wfdm);

        NetWorking.requstJsonNetData("Q24", Common.QUERY, listRepBean, new DataCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.open().appendMethodB("返回Q24异常:" + e.getMessage() + e.toString() + "\n");
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {


                    BaseCodeDetailsResponseJson jsonObj = new Gson().fromJson(response.toString(), BaseCodeDetailsResponseJson.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        if (jsonObj.getData() != null) {
                            mvpCallBack.succeed(jsonObj.getData());
                        }
                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }


                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q24Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });


    }

    @Override
    public void searchDL(SearchDLReqBean bean, final MVPCallBack mvpCallBack) {
        NetWorking.requstJsonNetData("Q26", Common.QUERY, bean, new DataCallback() {
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
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q26.json");
                    }
                    DLRespJson jsonObj = new Gson().fromJson(response, DLRespJson.class);


                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q26Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });
    }

    @Override
    public void searchLD(SearchLDReqBean bean, final MVPCallBack mvpCallBack) {
        NetWorking.requstJsonNetData("Q27", Common.QUERY, bean, new DataCallback() {
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
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q27.json");
                    }


                    LDRespJson jsonObj = new Gson().fromJson(response, LDRespJson.class);


                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData());

                    } else {

                        mvpCallBack.succeed(null);
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q27Exception:" + e.getMessage() + "--" + e.toString() + "\n");

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
//                if (Common.isDataTest) {
//                    onResponse("cyjtest", 1);
//                } else {
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
//                }

            }

            @Override
            public void onResponse(String response, int id) {
                try {
//                    if (Common.isDataTest) {
//                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q28.json");
//                    }


                    WsbhResJson jsonObj = new Gson().fromJson(response, WsbhResJson.class);


                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData().getWsbh());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回Q28Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });


    }


}
