package com.scxd.lawqinghai.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.PassWordSetPresenter;
import com.scxd.lawqinghai.mvp.view.PassWordSetView;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.PassWordJudgeUtils;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 描述：修改密码
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */
public class PassWordSetActivity extends BaseActivity implements PassWordSetView {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.old_password)
    EditText oldPwd;
    /* @BindView(R.id.address_jk)
     EditText addressJk;*/
    @BindView(R.id.new_password)
    EditText newPwd;
    @BindView(R.id.confirm_new)
    EditText conFirmPwd;
    @BindView(R.id.save_pwd)
    Button savaPwd;

    String user;
    String bmbh;
    
    @Override
    protected int getLayoutId() {
        return R.layout.activity_passwordset;
    }

    @Override
    protected void initInjector() {

        user = SharedPreferencesUtils.getString(this, "USER", "");
        bmbh = SharedPreferencesUtils.getString(this, "BMBH", "");

    }

    @Override
    protected void initEventAndData() {


    }

    @OnClick({R.id.back, R.id.save_pwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.save_pwd:
                String oldP = oldPwd.getText().toString().trim();
                String newP = newPwd.getText().toString().trim();
                String confirmP = conFirmPwd.getText().toString().trim();
                if (isCheckPwd(oldP, newP, confirmP)) {

                    ((PassWordSetPresenter) mPresenter).setPassWord(oldP, newP, user, bmbh);
                }

                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isCheckPwd(String oldP, String newP, String confirmP) {

        if (TextUtils.isEmpty(oldP)) {
            ToastUtils.showToast(this, "旧密码不可为空");
            return false;
        }
        if (TextUtils.isEmpty(newP)) {
            ToastUtils.showToast(this, "新密码不可为空");
            return false;
        }
        if (TextUtils.isEmpty(confirmP)) {
            ToastUtils.showToast(this, "请再次输入新密码");
            return false;
        }
        if (!newP.equals(confirmP)) {
            ToastUtils.showToast(this, "两次新密码不对");
            return false;
        }
        switch (PassWordJudgeUtils.judgePassWord(this, newP)) {
            case 0:

                return true;
            case 1:
                ToastUtils.showToast(this, "密码不能直接包含用户名");
                break;
            case 2:
                ToastUtils.showToast(this, "密码长度不能小于6");
                break;
            case 3:
                ToastUtils.showToast(this, "密码强度应满足大写字母、小写字母、数字和特殊字符四者中两者以上组合要求");
                break;
        }
        return false;


    }

    @Override
    public BasePresenter getPresenter() {
        return new PassWordSetPresenter();
    }

    @Override
    public void changeSuccess(String message) {
        ToastUtils.showToast(this, message);
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
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
}
