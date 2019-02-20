package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.bean.request.QueryCodeListReqBean;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.bean.response.QueryCodeRspBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.LogUtil;

import org.json.JSONObject;

import okhttp3.Call;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CodeLawListModelImpl implements CodeLawListModel {

    /**
     *  Query Law List Information
     * @param context
     */
    @Override
    public void loadLawDatas(Context context, String wfdm, String wfnr, int page, final MVPCallBack mvpCallBack) {

        QueryCodeListReqBean listRepBean = new QueryCodeListReqBean(wfdm, wfnr, page, 10);
        
        NetWorking.requstJsonNetData("Q23", Common.QUERY, listRepBean, new DataCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.open().appendMethodB("返回Q23异常:" + e.getMessage()+e.toString()+"\n");
                if (e.toString().contains("java.net.SocketTimeoutException")){
                    mvpCallBack.failed("请求服务器连接超时");
                } else {
                    mvpCallBack.failed("请求服务器失败,请联系服务人员");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    QueryCodeRspBean jsonObj = JSON.parseObject(response, QueryCodeRspBean.class);
                    if (jsonObj.getMeta().isSuccess()) {
                        if (jsonObj.getData() != null) {
                            mvpCallBack.succeed(jsonObj.getData());
                        }
                    } else {

                        mvpCallBack.failed(jsonObj.getMeta().getMessage());
                    }
                } catch (Exception e){
                    LogUtil.open().appendMethodB("返回Q23Exception:" + e.getMessage() + "--" + e.toString() + "\n");

                    mvpCallBack.failed("请求数据解析异常，请联系服务人员");
                }


            }
        });
        
        
    }
}
