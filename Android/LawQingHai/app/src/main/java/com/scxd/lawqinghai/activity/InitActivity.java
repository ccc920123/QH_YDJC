package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.service.NotificationService;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.TTSUtils;
import com.scxd.lawqinghai.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class InitActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.address_ip)
    EditText addressIp;
    /* @BindView(R.id.address_jk)
     EditText addressJk;*/
    @BindView(R.id.apartment_num)
    EditText apartmentNum;
    @BindView(R.id.pda_num)
    TextView pdaNum;
    @BindView(R.id.save_para)
    Button savePara;
    private String tag;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activty_setpara;
    }

    @Override
    protected void initInjector() {
        tag = getIntent().getStringExtra("TAG");
        Common.IP = SharedPreferencesUtils.getString(this, "IP", "127.0.0.1:59995");
        Common.JGBH = SharedPreferencesUtils.getString(this, "BMBH", "");
        Common.PDA_IMEI = SharedPreferencesUtils.getString(this, "PDAIMEI", "");
        addressIp.setText(Common.IP);
        apartmentNum.setText(Common.JGBH);
        pdaNum.setText(Common.PDA_IMEI);
    }

    @Override
    protected void initEventAndData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("W1".equals(tag)) {
                    System.exit(0);
                } else {
                    onBackPressed();
                }
            }
        });
        savePara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDatas();
            }
        });
    }

    public void saveDatas() {
        Common.IP = addressIp.getText().toString().trim();
        Common.JGBH = apartmentNum.getText().toString().trim();
        if (checkNetIsLegal(Common.IP) && MatcherProt(Common.IP)) {
            if ("".equals(Common.JKXLH) && "".equals(Common.JGBH)) {
                ToastUtils.showToast(this, R.string.init_tip1);
            } else {
                Common.IP = addressIp.getText().toString().trim();
                Common.JGBH = apartmentNum.getText().toString().trim();
                SharedPreferencesUtils.saveString(this, "IP", Common.IP);
                SharedPreferencesUtils.saveString(this, "BMBH", Common.JGBH);
                //                SharedPreferencesUtils.saveString(this, "JKXLH", Common.JGBH);
                gotoLogin();
            }

        } else {
            ToastUtils.showToast(this, R.string.init_tip1);
        }

    }

    private void gotoLogin() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }

    private boolean checkNetIsLegal(String ipAddress) {
        if (ipAddress != null && !ipAddress.isEmpty()) {
            if (ipAddress.contains(":")) {

                ipAddress = ipAddress.split(":")[0];


            }
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // 判断ip地址是否与正则表达式匹配
            if (ipAddress.matches(regex)) {
                // 返回判断信息
                return true;
            } else {
                // 返回判断信息
                return false;
            }
        }
        return false;
    }

    /**
     * @param prot
     * @return 返回端口是否可用
     */
    private boolean MatcherProt(String prot) {
        if (prot.contains(":")) {

            prot = prot.split(":")[1];
            if (prot.length() <= 100) {

                //                    Pattern pattern = Pattern.compile("^((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])"
                //                            + "\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])"
                //                            + "\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])"
                //                            + "\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])"
                //                            + ":((102[4-9])|(10[3-9]\\d)|([1-5]\\d{3,4})|(6[0-4]\\d{3})|(65[0-4]\\d{2})|(655[0-2]\\d)|(6553[0-5]))$");
                //
                //                    Matcher matcher = pattern.matcher(prot);
                //                    return matcher.matches();
                return true;
            } else {
                return false;
            }

        } else {
            return true;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            NotificationService.startService(this, true);//关闭服务

            TTSUtils.getInstance().release();
            // 释放内存资源
            System.exit(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
