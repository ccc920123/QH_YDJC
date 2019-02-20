package com.scxd.lawqinghai.mvp.model;

import com.google.gson.Gson;
import com.scxd.lawqinghai.bean.request.LoginBean;
import com.scxd.lawqinghai.bean.request.PassWordSetBean;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.LogUtil;

import okhttp3.Call;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public class PassWordSetModelImpl implements  PassWordSetModel {


    @Override
    public void setPassWordSet(String oldPwd, String newPwd, String user, String bmbh,final MVPCallBack mvpCallBack) {
        PassWordSetBean bean = new PassWordSetBean(oldPwd, newPwd , user, bmbh);
        NetWorking.requstJsonNetData("W01", Common.WRITE, bean, new DataCallback() {
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


                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回W01Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常,请联系服务人员");
                }
            }
        });
    }
}
