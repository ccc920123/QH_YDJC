package com.scxd.lawqinghai.mvp.model;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.LedgerCarBean;
import com.scxd.lawqinghai.bean.request.LedgerDetailsBean;
import com.scxd.lawqinghai.bean.request.LedgerDirver;
import com.scxd.lawqinghai.bean.request.LedgerDriverBean;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.LedgerDetailsRespBean;
import com.scxd.lawqinghai.bean.response.WirteLegerResBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;
import com.zhy.http.okhttp.callback.StringDialogCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * @类名: ${type_name}
 * @功能描述: 台账
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LedgerModelImpl implements LedgerModel {
    /**
     * 车辆查询
     *
     * @param context
     * @param hpzl
     * @param hphm
     * @param mvpCallBack
     */
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

    /**
     * 驾驶人查询
     *
     * @param context
     * @param sfzh
     * @param mvpCallBack
     */
    @Override
    public void queryDriver(Context context, String sfzh, final MVPCallBack mvpCallBack) {
        LedgerDirver bean = new LedgerDirver(sfzh);
        NetWorking.requstJsonNetData("Q10", Common.QUERY, bean, new DataCallback() {
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
//                response="{\n" +
//                        "  \"meta\": {\n" +
//                        "    \"success\": true,\n" +
//                        "    \"message\": \"ok\"\n" +
//                        "  },\n" +
//                        "  \"data\": {\n" +
//                        "    \"xm\": \"胡漪鶨\",\n" +
//                        "    \"lxdh\": \"18000000000\",\n" +
//                        "    \"jszzt\": \"1\",\n" +
//                        "    \"cclzrq\": 1514736000000,\n" +
//                        "    \"sfcf\": null,\n" +
//                        "    \"yqwsy\": null,\n" +
//                        "    \"yqwhz\": null,\n" +
//                        "    \"fzjg\": \"四川省成都市交管局\",\n" +
//                        "    \"zjcx\": \"c1\",\n" +
//                        "    \"lxzsxxdz\": \"无\",\n" +
//                        "    \"sfzmc\": \"A\",\n" +
//                        "    \"csrq\": 1514736000000,\n" +
//                        "    \"dabh\": \"5100000     \",\n" +
//                        "    \"sfzmhm\": \"11111\",\n" +
//                        "    \"zsxzqy\": \"无\",\n" +
//                        "    \"jzqx\": \"2019-01-01 00:00:00\",\n" +
//                        "    \"zhqfrq\": null,\n" +
//                        "    \"ljjf\": 12,\n" +
//                        "    \"zhmfqfrq\": null,\n" +
//                        "    \"xyqfrq\": null,\n" +
//                        "    \"xytjrq\": 1527782400000,\n" +
//                        "    \"djzsxxdz\": \"无\",\n" +
//                        "    \"zxbh\": \"999999999\"\n" +
//                        "  }\n" +
//                        "}";
                try {


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
     * 提交数据
     *
     * @param context
     * @param bean
     * @param mvpCallBack
     */
    @Override
    public void loadCommit(Context context, Object bean, final MVPCallBack mvpCallBack) {

        NetWorking.requstJsonNetData("W05", Common.WRITE, bean, new DataCallback() {
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
                        response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/W05.json");
                    }
                    WirteLegerResBean jsonObj = new Gson().fromJson(response, WirteLegerResBean.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        mvpCallBack.succeed(jsonObj.getData().getId());

                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }

                } catch (Exception e) {
                    LogUtil.open().appendMethodB("返回W05Exception:" + e.getMessage() + "--" + e.toString() + "\n");

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
                                mvpCallBack.succeed("提交成功");
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
    public void queryDetails(Context context, String lsh, final MVPCallBack mvpCallBack) {
        LedgerDetailsBean bean = new LedgerDetailsBean(lsh);
        NetWorking.requstJsonNetData("Q30", Common.QUERY, bean, new DataCallback() {
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
                    LedgerDetailsRespBean jsonObj = JSON.parseObject(response, LedgerDetailsRespBean.class);
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
}
