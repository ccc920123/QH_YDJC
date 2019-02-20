package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.LoginBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface LoginModel {

    void login(Context loginActivity, String mAccount, String mPassword,
               MVPCallBack mvpCallBack);

    void getVersion(Context context, String version, MVPCallBack mvpCallBack);


    void testlogin(Context loginActivity, String mAccount, String mPassword,
                   DataCallback mvpCallBack);
}
