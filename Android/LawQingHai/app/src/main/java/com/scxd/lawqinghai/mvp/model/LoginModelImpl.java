package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.BaseResponseBodyVO;
import com.scxd.lawqinghai.bean.BaseResponseHeadVO;
import com.scxd.lawqinghai.bean.ResponseRootVo;
import com.scxd.lawqinghai.bean.request.LoginBean;
import com.scxd.lawqinghai.bean.request.TestRequestBean;
import com.scxd.lawqinghai.bean.request.RQVersionBean;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.RSPVersionBean;
import com.scxd.lawqinghai.bean.response.TestBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.StringUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @类名: ${type_name}
 * @功能描述: 预警清单列表请求
 * @作者: ${user}
 * @时间: ${date}activity_login.xml
 * @最后修改者:
 * @最后修改内容:
 */
public class LoginModelImpl implements LoginModel {

    @Override
    public void login(Context loginActivity, String name, String password, final MVPCallBack mvpCallBack) {
        LoginBean bean = new LoginBean(name, password, Common.JGBH);
        NetWorking.requstJsonNetData("Q01", Common.QUERY, bean, new DataCallback() {
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
                BaseResponseJson jsonObj = new Gson().fromJson(response, BaseResponseJson.class);


                if (jsonObj.getMeta().isSuccess()) {
                    mvpCallBack.succeed(jsonObj.getData());

                } else {

                    mvpCallBack.failed(jsonObj.getMeta().getMessage());
                }

                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q01Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }

            }
        });

    }

    @Override
    public void getVersion(Context loginActivty, final String version, final MVPCallBack mvpCallBack) {
        RQVersionBean bean = new RQVersionBean(version);
        NetWorking.requstJsonNetData("Q02", Common.QUERY, bean, new DataCallback() {
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
                    
                
                BaseResponseJson jsonObj = new Gson().fromJson(response, BaseResponseJson.class);
                if (jsonObj.getMeta().isSuccess()) {
                    mvpCallBack.succeed(jsonObj.getData());

                } else {

                    mvpCallBack.failed(jsonObj.getMeta().getMessage());
                }


//                if (StringUtils.isJson(response)){
//                    RSPVersionBean bean = JSONObject.parseObject(response, RSPVersionBean.class);
//                    if (bean.getResult().equals("1")) {
//                        mvpCallBack.succeed(bean.getResultvalue());
//                    } else {
//                        mvpCallBack.failed("暂无数据");
//                    }
//                } else {
//                    XStream xStream = new XStream(new DomDriver());
//                    xStream.ignoreUnknownElements();
//                    xStream.alias("root", ResponseRootVo.class);
//                    xStream.alias("head", BaseResponseHeadVO.class);
//                    xStream.alias("body", BaseResponseBodyVO.class);
//                    xStream.aliasField("vioSurveil", BaseResponseBodyVO.class, "testBean");
//                    xStream.alias("vioSurveil", TestRequestBean.class);
//                    ResponseRootVo responseRootVo = (ResponseRootVo) xStream.fromXML(response);
//                    if (responseRootVo.getHead().getCode() == 1) {
//                        mvpCallBack.succeed(responseRootVo.getBody());
//                    }
//                }

                } catch (Exception e){
                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
            
        });
    }

    @Override
    public void testlogin(Context loginActivity, String mAccount, String mPassword, DataCallback myCallBack) {
        String url = "http://" + Common.IP + "/Web/login/pdalogin";
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("user", "kkkkk");
        stringMap.put("password", "kkkkk");
        OkHttpUtils
                .post()
                .url(url)
                .params(stringMap)//这里要json
                .build()
                .execute(myCallBack);
    }

}
