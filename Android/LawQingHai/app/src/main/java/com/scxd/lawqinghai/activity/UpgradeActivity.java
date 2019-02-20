package com.scxd.lawqinghai.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.WelcomePresenter;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.PromptUtils;
import com.scxd.lawqinghai.widget.dialog.BaseDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 升级界面
 *
 * @类名 :UpgradeActivity.java
 * @author Administrator
 * @功能描述:
 * @创建时间:2015-10-9
 */
public class UpgradeActivity extends Activity {
    private TextView mVersion;
    private TextView mSysProTv;
    private EditText mSysExplain;
    private Button mSysupgradeBtn;
    private ProgressBar mSysPro;
    private LinearLayout msyLayout;
    /**
     * 下载线程是否正在执行
     */
    boolean isAsycn = false;
    private String mUrl;
    private String from;
    int length = 0;

    private BaseDialog mDialog;
    private String version;
    private String msg;
    
    
    public static void statAction(Context ct, String version, String msg, String url) {
        Intent it = new Intent(ct, UpgradeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("version", version);
        bundle.putString("msg", msg);
        bundle.putString("url", url);
        it.putExtras(bundle);
        ct.startActivity(it);

    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        getView();
        init();
    }
    
    
    
    /**
     * @功能描述：初始化，获取数据来源
     */
    private void init() {
        Bundle bundle = getIntent().getExtras();
        version = bundle.getString("version");
        msg = bundle.getString("msg");
        mUrl = bundle.getString("url");
        mVersion.setText(version);
        mSysExplain.setText(Html.fromHtml(msg));
        mSysupgradeBtn.setOnClickListener(clicklistener);
    }

    private OnClickListener clicklistener = new OnClickListener() {

        @Override
        public void onClick(View arg0) {
            mSysupgradeBtn.setBackgroundColor(getResources().getColor(R.color.gray01));
            new OnLoadData_Updata().execute();
            mSysupgradeBtn.setVisibility(View.GONE);
            mSysPro.setVisibility(View.VISIBLE);
            mSysProTv.setVisibility(View.VISIBLE);
            msyLayout.setVisibility(View.VISIBLE);
        }

    };

    /**
     * @功能描述：得到控件
     * @param
     * @return
     * @throws IOException
     * @throws NullPointerException
     */
    private void getView() {
        mVersion = (TextView) findViewById(R.id.version);
        mSysProTv = (TextView) findViewById(R.id.sys_pro_tv);
        mSysExplain = (EditText) findViewById(R.id.sys_explain);
        mSysupgradeBtn = (Button) findViewById(R.id.sys_upgradebtn);
        mSysPro = (ProgressBar) findViewById(R.id.sys_pro);
        msyLayout=(LinearLayout) findViewById(R.id.sys_pro_lay);
    }

    class OnLoadData_Updata extends AsyncTask<String, Integer, Integer> {

        @Override
        protected void onPostExecute(Integer msu) {
            // TODO Auto-generated method stub
            super.onPostExecute(msu);
            isAsycn=false;
            switch (msu) {
                case 1:

                    dialog(getResources().getString(R.string.upgrade_success)
                            + GetVersion(Common.PHONE_PATH
                            + "APK/QHZF.apk") +getResources().getString(R.string.upgrade_install), 1);
                    break;
                case 0:
                    dialog(getResources().getString(R.string.upgrade_defult), 0);
                    break;
                case 2:
                    dialog( getResources().getString(R.string.upgrade_exception), 0);
                    break;

            }

        }

        /*
         * 更新界面
         */
        @SuppressWarnings("unused")
        @Override
        protected void onProgressUpdate(Integer... values) {
            float fileLength = ((float) length / 1024 / 1024);



            mSysPro.setProgress(values[0]);
            mSysProTv.setText("(" + values[0] + "%" + ")");
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(String... arg0) {
            isAsycn = true;

            try {
//                mUrl = "http://imtt.dd.qq.com/16891/D60D4D5B9B7756F51BC42E06657FAFB0.apk?fsname=com.tencent" +
//                        ".mobileqq_7.7.0_882.apk";
                System.out.println("下载地址:" + mUrl);
                URL url = new URL(mUrl);

                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();

                conn.setDoInput(true);

                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)

                {

                    InputStream is = conn.getInputStream();
                    File file = new File(Common.PHONE_PATH + "APK");
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    FileOutputStream fos = new FileOutputStream(new File(
                            Common.PHONE_PATH + "APK/QHZF.apk"));

                    length = conn.getContentLength();

                    byte[] bt = new byte[1024];

                    int count = 0;
                    int updataJD = 0;
                    int i = 0;

                    while ((i = is.read(bt)) > 0) {
                        count += i;
                        updataJD = (int) (((float) count / length) * 100);

                        // 更新进度
                        publishProgress(updataJD);

                        fos.write(bt, 0, i);

                    }

                    fos.flush();

                    fos.close();

                    is.close();
                    // 下载完成
                    publishProgress(100);

                    System.err.println("下载文件成功");
                    return 1;
                } else {

                    System.err.println("下载文件失败");
                    return 0;
                }

            } catch (Exception e) {
                System.err.println("下载文件失败:" + e.getMessage());

                return 2;
            }
        }

    }

    /**
     * 获取升级包版本信息
     *
     * @param upFile
     *            升级包地址
     * @return 返回版本信息
     */
    @SuppressWarnings("unused")
    private String GetVersion(String upFile) {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(upFile,
                    PackageManager.GET_ACTIVITIES);
            if (info != null) {
                ApplicationInfo appInfo = info.applicationInfo;
                String appName = pm.getApplicationLabel(appInfo).toString();
                String packageName = appInfo.packageName; // 得到安装包名称
                return info.versionName; // 得到版本信息
                // 未开启代码
                // Drawable icon = pm.getApplicationIcon(appInfo);//得到图标信息
                // TextView tv = (TextView)findViewById(R.id.tv); //显示图标
                // tv.setBackgroundDrawable(icon);
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }



    /**
     * 弹出对话框
     *
     *            信息头
     * @param msg
     *            信息
     * @param type
     *            类型 0普通 1定制对话框
     */
    protected void dialog( String msg, int type) {
        switch (type) {
            case 0:
                mDialog = BaseDialog.getDialog(this, R.string.dialog_tips, msg,
                        R.string.confirm, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                if (!isAsycn) {
                                    System.gc();
                                    mDialog.dismiss();
                                    finish();
                                }
                            }
                        }, R.string.cancle, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                mDialog.dismiss();
                                Intent it = new Intent(UpgradeActivity.this, WelcomeActivity.class);
                                UpgradeActivity.this.startActivity(it);
                                finish();
                            }
                        });
                mDialog.show();
                break;

            case 1:

                mDialog = BaseDialog.getDialog(this, R.string.dialog_tips, msg,
                        R.string.confirm, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setDataAndType(Uri.fromFile(new File(
                                                Common.PHONE_PATH
                                                        + "APK/QHZF.apk")),
                                        "application/vnd.android.package-archive");
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                System.gc();
                                mDialog.dismiss();
                                finish();
                            }
                        }, R.string.cancle, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                mDialog.dismiss();
                                Intent it = new Intent(UpgradeActivity.this, WelcomeActivity.class);
                                UpgradeActivity.this.startActivity(it);
                                finish();
                            }
                        });
                mDialog.show();
                break;
        }

    }
    @Override
    public void onBackPressed() {
        if(isAsycn){
            PromptUtils.showAlertDialog(this, R.string.now_upgrade,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            PromptUtils.closeAlertDialog();
                            finish();

                        }
                    });
        }else{
            System.exit(0);
        }
    }
}
