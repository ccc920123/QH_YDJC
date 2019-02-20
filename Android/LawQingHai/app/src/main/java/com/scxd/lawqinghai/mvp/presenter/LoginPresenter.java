package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.RSPVersionBean;
import com.scxd.lawqinghai.bean.response.TestBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.LoginModel;
import com.scxd.lawqinghai.mvp.model.LoginModelImpl;
import com.scxd.lawqinghai.mvp.view.LoginView;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;
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
public class LoginPresenter extends BasePresenter<LoginView> {

    LoginModel mLoginModel = new LoginModelImpl();

    public void loginAction(final Context loginActivity, final String mAccount, final String mPassword) {
        if (TextUtils.isEmpty(mAccount) || TextUtils.isEmpty(mPassword)) {
            if (null != mView) {
                mView.showToast(loginActivity.getString(R.string.Please_enter_the_correct_user_name_and_password));
            }
        } else {
           login(loginActivity, mAccount, mPassword);

        }
    }


    /**
     * 调用登录接口
     *
     * @param context
     * @param mAccount
     * @param mPassword
     */
    public  void login(final Context context, final String mAccount, final String mPassword) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在登录...");
        }
        mLoginModel.login(context, mAccount, mPassword, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.gotoMainActivity(bean);

                } else {
                    mView.showToast("获取数据失败");
                }

            }
            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);
            }
        });
    }

    /**
     * test
     * @param context
     * @param mAccount
     * @param mPassword
     */
    public  void testlogin(final Context context, final String mAccount, final String mPassword) {
        if (mView != null) {
//            mView.showLoadProgressDialog("正在登录...");
        }
        mLoginModel.testlogin(context, mAccount, mPassword, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("test",e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
            }

        });
    }


    public void showVersion(final Context context) {

        mLoginModel.getVersion(context, String.valueOf(getLocalVersion(context)), new MVPCallBack() {
            @Override
            public void succeed(Object object) {
                if (object != null){
                    RSPVersionBean.ResultvalueBean bean = (RSPVersionBean.ResultvalueBean) object;
                    mView.gotoUpgradeActivity(bean);
                } else {
                    mView.disDialog();
                    ToastUtils.showToast(context, "获取数据失败");
                }
            }

            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);
            }
        });
    }

    /**
     * 获取本地软件版本号
     */
    public static int getLocalVersion(Context context) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
