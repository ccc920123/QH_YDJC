package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.response.LoginBeanRes;
import com.scxd.lawqinghai.bean.response.RSPVersionBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.LoginPresenter;
import com.scxd.lawqinghai.mvp.view.LoginView;
import com.scxd.lawqinghai.service.NotificationService;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.TTSUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.ExpandableLayout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LoginActivity extends BaseActivity implements LoginView {
    @BindView(R.id.login_image)
    ImageView loginImage;
    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_psd)
    EditText loginPsd;
    @BindView(R.id.login_login)
    Button loginLogin;
    @BindView(R.id.login_set)
    TextView loginSet;
    @BindView(R.id.login_all)
    LinearLayout loginAll;
    @BindView(R.id.expandableLayout)
    ExpandableLayout mExpandableLayout;
    
    @Override
    public BasePresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {
        loginName.setText(SharedPreferencesUtils.getString(this, "USER", ""));
//        loginPsd.setText("88888888");
        try {
            File mFile = new File(Common.PHONE_PATH + "photo/");
            if (!mFile.exists()) {
                mFile.mkdirs();
            }
            File mFiles = new File( Common.PIC_PATH + "cache/");
            if (!mFiles.exists()) {
                mFiles.mkdirs();
            }
            
           
        } catch (Exception e) {
            ToastUtils.showToast(this, "文件管理器出异常，部分功能将无法使用！");
        }
        
        

    }

    @Override
    protected void initEventAndData() {

        loginAll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                // 获取root在窗体的可视区域
                loginAll.getWindowVisibleDisplayFrame(rect);
                // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                int rootInvisibleHeight = loginAll.getRootView().getHeight() - rect.bottom;

                if (rootInvisibleHeight > 120) { // 说明键盘是弹出状态
                    mExpandableLayout.collapse();
                } else {
                    mExpandableLayout.expand();
                }

            }
        });
    }

    @Override
    public void showLoadProgressDialog(String str) {
        showLoading(str);
    }

    @Override
    public void disDialog() {
        dissLoadDialog();
    }

    @Override
    public void showToast(String message) {

        ToastUtils.showToast(this, message);
    }

    @Override
    public void gotoUpgradeActivity(final RSPVersionBean.ResultvalueBean bean) {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("有新版本 " + bean.getVersion() + ",是否更新？");
//        builder.setCancelable(false);
//        builder.setPositiveButton("去更新", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                UpgradeActivity.statAction(LoginActivity.this, bean.getVersion(), bean.getDescription(), bean.getUrl());
//            }
//        });
//        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                System.exit(0);
//            }
//        });
//        builder.show();
    }

    @Override
    public void gotoMainActivity(Object obj) {
        LoginBeanRes login = new Gson().fromJson(obj.toString(), LoginBeanRes.class);
        String menuids = "";
        for (int i = 0; i < login.getMenuIds().size(); i++) {
            menuids += login.getMenuIds().get(i) + ",";
        }
         Common.user= login.getLoginname();
        //将登录账号存在shared里面
        SharedPreferencesUtils.saveString(this, "USER", login.getLoginname());
        SharedPreferencesUtils.saveString(this, "xm", login.getXm());
        SharedPreferencesUtils.saveString(this, "bmmc", login.getBmmc());
        SharedPreferencesUtils.saveString(this, "SSBMBH", login.getSsbmbh());
        SharedPreferencesUtils.saveString(this, "SJBM", login.getSjbm());
        SharedPreferencesUtils.saveString(this, "SGDJ", login.getSgdj());
        SharedPreferencesUtils.saveString(this, "FWZBH", login.getFwzbh());
        SharedPreferencesUtils.saveString(this, "MENUIDS", menuids);
        SharedPreferencesUtils.saveString(this, Common.user, login.getJszt());
        Common.ISJPUSH = login.getJszt();
        Common.ISLOGIN = true;
        /**
         * 登录成功后开启推送的调用 修改在卡口设置中去开启推送服务
         */
//        NotificationService.startService(this, false);  //开启推送服务
        Intent itt = new Intent(this, MainActivity.class);
        startActivity(itt);
        finish();

    }
    @OnClick({R.id.login_login, R.id.login_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    String name = loginName.getText().toString();
                    String pwd = loginPsd.getText().toString();
                    ((LoginPresenter) mPresenter).loginAction(this, name, pwd);
                }
                break;
            case R.id.login_set:
                Intent itt = new Intent(this, InitActivity.class);
                itt.putExtra("TAG", "L1");
                startActivity(itt);
                break;
        }
    }

    public boolean isLoginEmpty(String name, String psd) {


        return false;
    }

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(this, "再按一次返回将退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                NotificationService.startService(this, true);//关闭服务

                TTSUtils.getInstance().release();
                // 释放内存资源
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
