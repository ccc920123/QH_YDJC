package com.scxd.lawqinghai.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.InitActivity;
import com.scxd.lawqinghai.activity.LoginActivity;
import com.scxd.lawqinghai.activity.MainActivity;
import com.scxd.lawqinghai.activity.UpgradeActivity;
import com.scxd.lawqinghai.activity.WelcomeActivity;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.manager.AppManager;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.view.BaseView;
import com.scxd.lawqinghai.nfcexputils.NfcPrinter;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.StatusBarUtil;
import com.scxd.lawqinghai.utils.TTSUtils;
import com.scxd.lawqinghai.utils.WaterMarkUtil;
import com.scxd.lawqinghai.widget.dialog.AlertDialog;
import com.scxd.lawqinghai.widget.dialog.CustomPrograss;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import me.naturs.library.statusbar.StatusBarHelper;

/**
 * @类名: ${type_name}
 * @功能描述: 预警清单列表
 * @作者: 张翔
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public abstract class BaseActivity<T extends BasePresenter<BaseView>> extends AppCompatActivity implements IBase {
    public BasePresenter mPresenter;
    // public RxManager mRxManager;
    protected StatusBarHelper mStatusBarHelper;
    public Context mContext;
    /**
     * 传入标示符
     */
    public String fromTag;

    public NfcPrinter nfcPrinter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // mRxManager = new RxManager();
        setBaseConfig();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        onTintStatusBar();
        onSaveState(savedInstanceState);
        mPresenter = getPresenter();
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.attach((BaseView) this);
        }

        nfcPrinter = new NfcPrinter(this);
        boolean result = nfcPrinter.check_loacl_NFC_device();//判断是否支持Nfc
        if (!result)
            return;
        nfcPrinter.setNfcForeground();


        initInjector();
        //注册一个监听连接状态的listener
        initEventAndData();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(BaseActivity.this);
        }
        String xmstr = SharedPreferencesUtils.getString(this, "xm", "");
        String bmmcstr = SharedPreferencesUtils.getString(this, "bmmc", "");
        String loginname = SharedPreferencesUtils.getString(this, "USER", "");
        if (this instanceof WelcomeActivity ||
                this instanceof InitActivity ||
                this instanceof MainActivity ||
                this instanceof LoginActivity) {

        } else {
            WaterMarkUtil.showWatermarkView(this, bmmcstr + "\n\n" + loginname + "  " + xmstr);

        }

    }

    public void onSaveState(Bundle savedInstanceState) {
    }


    private void setBaseConfig() {
        initTheme();
        AppManager.getAppManager().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //  SetStatusBarColor();
    }

    public void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT, StatusBarHelper
                    .LEVEL_21_VIEW);
        }
        mStatusBarHelper.setColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    //****处理NFC标签
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        if (nfcPrinter != null) {
            nfcPrinter.regist();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        super.onPause();
        if (nfcPrinter != null) {
            nfcPrinter.disableForegroundDispatch();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initInjector();

    /**
     * 设置监听
     */
    protected abstract void initEventAndData();

    private void initTheme() {
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.black));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    public void SetStatusBarColor(int color) {
        StatusBarUtil.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarUtil.translucentStatusBar(this, false);
    }


    private void restartApplication() {
        final Intent intent = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.detachView();
            mPresenter = null;
        }
        //mRxManager.clear();
        EventBus.getDefault().unregister(this);
        // AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    private void initToolBarConfig() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上  
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0  
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 开启消息等待框
     *
     * @param string
     */
    public void showLoading(String string) {
        CustomPrograss.show(this, string, false, null);
    }

    /**
     * 关闭消息等待框
     */
    public void dissLoadDialog() {
        CustomPrograss.disMiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    // 执行在一个新的子线程
    @Subscribe
    public void onEventMainThread(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if ("0".equals(msg)) {
                    showOpenGPSDialog();
                } else if ("1".equals(msg)) {
                    gotoSetTime(Common.serviceTime);
                } else if ("2".equals(msg)) { //单点登录
//                    showSingleLogin();
                }
            }
        });

    }

    /**
     * 弹出设置时间登录框
     *
     * @param time
     */
    private AlertDialog timeDialog;

    private void gotoSetTime(final String time) {
        if (timeDialog == null) {
            timeDialog = new AlertDialog(BaseActivity.this).builder();
            if (TextUtils.isEmpty(time)) {
                timeDialog.setTitle("时间同步").setMsg("获取服务器时间失败,退出程序!").setPositiveButton("确定", new View
                        .OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppManager.getAppManager().finishAllActivity();
                        timeDialog.dismiss();
                        timeDialog = null;
                        System.exit(0);
                    }
                }).setCancelable(false).show();
            } else {
                timeDialog.setTitle("时间同步").setMsg("服务器时间为:\n" + time + "\n与服务器时间不同步,请设置").setPositiveButton("去设置",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timeDialog.dismiss();
                                timeDialog = null;
                                Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timeDialog.dismiss();
                        timeDialog = null;
                    }
                }).setCancelable(false).show();
            }
        }
    }

    /**
     * 弹出GPS登录框
     */
    private AlertDialog gpsDialog;

    private void showOpenGPSDialog() {
        if (gpsDialog == null) {
            gpsDialog = new AlertDialog(BaseActivity.this).builder();
            gpsDialog.setTitle("位置信息").setMsg("系统GPS未打开,请先打开").setPositiveButton("去设置", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gpsDialog.dismiss();
                    gpsDialog = null;
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gpsDialog.dismiss();
                    gpsDialog = null;
                }
            }).setCancelable(false).show();
        }
    }

}
