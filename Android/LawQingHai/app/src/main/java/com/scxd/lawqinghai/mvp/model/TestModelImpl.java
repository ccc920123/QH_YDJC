package com.scxd.lawqinghai.mvp.model;

import android.content.Context;
import android.widget.Toast;


import com.alibaba.fastjson.JSONObject;
import com.scxd.lawqinghai.bean.BaseResponseBodyVO;
import com.scxd.lawqinghai.bean.BaseResponseHeadVO;
import com.scxd.lawqinghai.bean.ResponseRootVo;
import com.scxd.lawqinghai.bean.request.TestRequestBean;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.TestBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.StringUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.util.List;

import okhttp3.Call;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class TestModelImpl implements TestModel {

    public static String JK_ID = "03G03";

    @Override
    public void loadDatas(Context context, String param, final MVPCallBack mvpCallBack) {
        TestRequestBean bean = new TestRequestBean(param);
        NetWorking.requstNetData(JK_ID, Common.QUERY, bean, TestRequestBean.class, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mvpCallBack.failed("网络请求错误，请检查网络");
            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }
}
