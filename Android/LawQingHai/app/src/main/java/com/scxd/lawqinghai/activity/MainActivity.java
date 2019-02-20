package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.response.UpDataDownloadBeanRes;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.manager.AppManager;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.MainPresenter;
import com.scxd.lawqinghai.mvp.presenter.WelcomePresenter;
import com.scxd.lawqinghai.mvp.view.MainView;
import com.scxd.lawqinghai.mvp.view.WelcomeView;
import com.scxd.lawqinghai.service.NotificationService;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.TTSUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.utils.bottompopfragmentmenu.BottomMenuFragment;
import com.scxd.lawqinghai.utils.bottompopfragmentmenu.MenuItem;
import com.scxd.lawqinghai.utils.bottompopfragmentmenu.MenuItemOnClickListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
public class MainActivity extends BaseActivity implements MainView {
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_info)
    TextView userInfo;
    @BindView(R.id.jdcjbxxcx)
    TextView jdcjbxxcx;
    @BindView(R.id.jsrjbxxcx)
    TextView jsrjbxxcx;
    @BindView(R.id.wfdmcx)
    TextView wfdmcx;
    @BindView(R.id.bksz)
    TextView bksz;
    @BindView(R.id.yjxx)
    TextView yjxx;
    @BindView(R.id.tzlr)
    TextView tzlr;
    @BindView(R.id.jycfjds)
    TextView jycfjds;
    @BindView(R.id.qzcstzs)
    TextView qzcstzs;
    @BindView(R.id.dzjklr)
    TextView dzjklr;
    @BindView(R.id.flyd)
    TextView flyd;
    @BindView(R.id.main)
    LinearLayout main;

    String xm;
    String bmmc;

    @Override
    public BasePresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        xm = SharedPreferencesUtils.getString(this, "xm", "");
        bmmc = SharedPreferencesUtils.getString(this, "bmmc", "");

//        flyd.setVisibility(View.INVISIBLE);
        //        if (SharedPreferencesUtils.getBoolean(this, "ISLOGIN", false)) {
        //            NotificationService.startService(this, false);
        //        } else {
        //            NotificationService.startService(this, true);
        //        }
    }

    @Override
    protected void initEventAndData() {
        userInfo.setText(bmmc);
        userName.setText("您好，" + xm);
        
    }

    @Override
    public void onResume() {
        super.onResume();

        ((MainPresenter) mPresenter).getServiceVersion(this, Common.JGBH);
    }

    @OnClick({R.id.jdcjbxxcx, R.id.jsrjbxxcx, R.id.wfdmcx, R.id.bksz, R.id.yjxx, R.id.tzlr, R.id.jycfjds, R.id
            .qzcstzs, R.id.dzjklr, R.id.user_name, R.id.flyd})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.jdcjbxxcx:   //机动车查询
                intent = new Intent(this, CarQueryActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.jsrjbxxcx:    //驾驶人查询
                intent = new Intent(this, DriverQueryActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.wfdmcx:   //违法代码查询
                intent = new Intent(this, CodeQueryActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.flyd:   //法律园地
                intent = new Intent(this, LawActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bksz:     //布控设置
                intent = new Intent(this, DispatchActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.yjxx:     //预警信息
                intent = new Intent(this, WarnTabActivity.class);
                //                intent = new Intent(this, WarnListActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tzlr:
                //台账录入
                if ("".equals(SharedPreferencesUtils.getString(this, "FWZBH", ""))) {

                    ToastUtils.showToast(this, "你没有执法站检查权限");
                } else {

                    intent = new Intent(this, LedgerListActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.jycfjds:  //简易惩罚
                intent = new Intent(this, SummaryPunishmentDecisionActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.qzcstzs:  //强制措施
                intent = new Intent(this, NotificationOfCompulsoryMeasuresActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.dzjklr:   //电子监控
                intent = new Intent(this, ElectronicMonitoringInputActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.user_name:
                BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();

                List<MenuItem> menuItemList = new ArrayList<MenuItem>();
                MenuItem menuItem1 = new MenuItem();
                menuItem1.setText("密码修改");
                menuItem1.setStyle(MenuItem.MenuItemStyle.COMMON);
                MenuItem menuItem2 = new MenuItem();
                menuItem2.setText("注销登录");
                menuItem2.setStyle(MenuItem.MenuItemStyle.STRESS);
                menuItem1.setMenuItemOnClickListener(new MenuItemOnClickListener(bottomMenuFragment, menuItem1) {
                    @Override
                    public void onClickMenuItem(View v, MenuItem menuItem) {
                        Intent intent = new Intent(MainActivity.this, PassWordSetActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                menuItem2.setMenuItemOnClickListener(new MenuItemOnClickListener(bottomMenuFragment, menuItem1) {
                    @Override
                    public void onClickMenuItem(View v, MenuItem menuItem) {
                        Common.ISLOGIN = false;
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                menuItemList.add(menuItem1);
                menuItemList.add(menuItem2);

                bottomMenuFragment.setMenuItems(menuItemList);

                bottomMenuFragment.show(getFragmentManager(), "BottomMenuFragment");
                break;
            default:
                break;
        }

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
                // 释放内存资源
                //在卡口中去关闭服务，
                Common.ISLOGIN = false;
                NotificationService.startService(this, true);//关闭服务

                TTSUtils.getInstance().release();
                
                AppManager.getAppManager().AppExit(this);
                System.exit(1);


                //                    }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    public void upDataDownload(Object obj) {
        UpDataDownloadBeanRes.DataBean updata= (UpDataDownloadBeanRes.DataBean) obj;
        //判断版本号是否相同
        if (!Common.VERSION.equals(updata.getVersion())) {
            //跳转到升级界面
            showPopuPicture(updata);
        }
    }


    /**
     * 显示图片弹窗
     */
    PopupWindow window = null;
    private TextView content, title;
    private void showPopuPicture(final UpDataDownloadBeanRes.DataBean updata) {
        final View popupView = this.getLayoutInflater().inflate(R.layout.up_app, null);
        title = (TextView) popupView.findViewById(R.id.title);
        content = (TextView) popupView.findViewById(R.id.content);
        title.setText("发现新版本：" + updata.getVersion());
        content.setText(Html.fromHtml(updata.getDescription()));
        final Button cancle = (Button) popupView.findViewById(R.id.cancle);
        final Button upBtn = (Button) popupView.findViewById(R.id.upbtn);
        window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(false);
        window.setOutsideTouchable(false);
        window.update();
        window.showAtLocation(main, Gravity.CENTER, 0, 0);
        popupView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    window.dismiss();
                    return true;
                }
                return false;
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpgradeActivity.statAction(MainActivity.this, updata.getVersion(), updata.getDescription(), updata.getUrl());
                finish();
            }
        });
    }
    
}
