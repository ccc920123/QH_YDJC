package com.scxd.lawqinghai.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.UpdateDownloadBean;
import com.scxd.lawqinghai.bean.response.LoginBeanRes;
import com.scxd.lawqinghai.bean.response.UpDataDownloadBeanRes;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.WelcomePresenter;
import com.scxd.lawqinghai.mvp.view.WelcomeView;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WelcomeActivity extends BaseActivity implements WelcomeView {

    public static final String Tag = "WelcomeActivity";
    @BindView(R.id.version_id)
    TextView versionId;
    @BindView(R.id.stat)
    TextView welcomStat;
    @BindView(R.id.wel_all)
    RelativeLayout welAll;
    String version;
    String path = Common.PHONE_PATH + "crash/";

    @Override
    public BasePresenter getPresenter() {
        return new WelcomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initInjector() {
        MPermissions.requestPermissions(this,
                Common.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);


    }

    @Override
    protected void initEventAndData() {

    }

    private void gotoSetPara() {
        Intent itt = new Intent(this, InitActivity.class);
        itt.putExtra("TAG", "W1");
        startActivity(itt);
        finish();
    }

    private void gotoLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void showLoadProgressDialog(String str) {

        welcomStat.setText(str);

    }

    @Override
    public void disDialog() {

    }

    @Override
    public void showToast(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("初始化失败，请检查配置");
        builder.setCancelable(false);
        builder.setNegativeButton("退出程序", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        builder.setPositiveButton("系统配置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gotoSetPara();
            }
        });
        builder.show();


    }

    /**
     * 更新字典
     *
     * @param obj
     */
    @Override
    public void upDataDownload(Object obj) {

//        UpDataDownloadBeanRes updata = new Gson().fromJson(obj.toString(), UpDataDownloadBeanRes.class);
        UpDataDownloadBeanRes.DataBean updata= (UpDataDownloadBeanRes.DataBean) obj;
        //判断版本号是否相同，如果相同就去判断字典版本是否相同，否则升级pda
        if (version.equals(updata.getVersion())) {
            String zdversion = SharedPreferencesUtils.getString(this, "ZDVERSION", "");
            //判断字典是否相同如果相同，读取字典后  直接到登录界面，如果不相同重更新字典
            LogUtil.open().appendMethodB("字典:pda:" + zdversion+"----服务器："+updata.getDictionary() + "\n");//写日志看看字典号
            if (zdversion.equals(updata.getDictionary())) {//字典如果相等，就直接上传崩机日志
                //  判断是否有崩机日志
                File dir = new File(path);
                if (!dir.exists()) {
                    gotoLogin();
                } else {

                    if ("".equals(searchFile(path))) {
                        gotoLogin();
                    } else {
                        //有崩机日志就上传到服务器
                        String strLogFile = path + searchFile(path);

                        try {
//                            FileInputStream is =new FileInputStream(strLogFile.replace("file://",""));
                            FileInputStream is = new FileInputStream(new File(strLogFile));

                            ((WelcomePresenter) mPresenter).comitLog(Common.PDA_IMEI, SharedPreferencesUtils.getString(this, "USER", ""), readStreamToString(is),strLogFile);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }


                    }
                }

            } else {
                //下载字典
                ((WelcomePresenter) mPresenter).getDictiona(updata.getBmbh(), updata.getDictionary());

            }


        } else {
            //跳转到升级界面
            UpgradeActivity.statAction(WelcomeActivity.this, updata.getVersion(), updata.getDescription(), updata.getUrl());
            finish();

        }


    }

    @Override
    public void setVersionNumToView(String version) {
        this.version = version;
        versionId.setText("版本号：v" + Common.VERSION);
        //开始检查版本，包括字典版本
        ((WelcomePresenter) mPresenter).getServiceVersion(this, Common.JGBH);

    }

    @Override
    public void showSuccessMessage(String Message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void openSystemGPS() {

    }

    @Override
    public void startGPSServiceSuccess(String message) {

    }

    @Override
    public void downLoadDictionaSuccess(String zdbb) {

        //保存当前版本--然后进入登录界面
        SharedPreferencesUtils.saveString(this, "ZDVERSION", zdbb);
        //跳转到登录界面

        File dir = new File(path);
        if (!dir.exists()) {
            gotoLogin();
        } else {
            if ("".equals(searchFile(path))) {
                gotoLogin();
            } else {
                //有崩机日志就上传到服务器
                String strLogFile = path + searchFile(path);

                try {
                    FileInputStream is = new FileInputStream(new File(strLogFile));

                    ((WelcomePresenter) mPresenter).comitLog(Common.PDA_IMEI, SharedPreferencesUtils.getString(this, "USER", ""), readStreamToString(is), strLogFile);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }


    }
// ((WelcomePresenter) mPresenter).getDictiona(updata.getBmbh(), updata.getDictionary());
    @Override
    public void comitLogSueed(String pathFile) {

        File file = new File(pathFile);
        if (file.isFile()) {
            file.delete();
        }

        gotoLogin();
    }

    @Override
    public void comitLogError() {

        gotoLogin();
    }

    /**
     * 获取PDA唯一码
     */
    public void getPDA() {
        try {
            LogUtil.deleteFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            TelephonyManager TelephonyMgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager
                    .PERMISSION_GRANTED) {
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            if (TelephonyMgr.getDeviceId() != null) {
                Common.PDA_IMEI = TelephonyMgr.getDeviceId();
            } else {
                Common.PDA_IMEI = Settings.Secure.getString(this.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            SharedPreferencesUtils.saveString(this, "PDAIMEI", Common.PDA_IMEI);
        } catch (Exception e) {
            ToastUtils.showToast(this, R.string.login_pda);
            System.exit(0);//获取手机唯一码不成功将不能继续进行流程
        }
    }

    private boolean isFirstRun() {
        Common.IP = SharedPreferencesUtils.getString(this, "IP", "");
        Common.JGBH = SharedPreferencesUtils.getString(this, "BMBH", "");
        if (Common.IP.equals("") || Common.JGBH.equals("")) {
            return false;
        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {

        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(Common.ACCESS_COARSE_LOCATION)
    public void requestSdcardSuccess() {
        getPDA();
        try {
            copyAssetFile("ayy/tessdata", "eng.traineddata");
//            copyAssetFile("tts", "OfflineTTSModels/backend_lzl");
//            copyAssetFile("tts", "OfflineTTSModels/frontend_model");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isFirstRun()) {
            gotoSetPara();
        } else {

            ((WelcomePresenter) mPresenter).getSystemVersion(this);
        }
    }

    @PermissionDenied(Common.ACCESS_COARSE_LOCATION)
    public void requestSdcardFailed() {
        finish();
    }
    
    private boolean copyAssetFile(String path, String pathName) throws Exception {

        String dir = Common.PHONE_PATH + path;
        String filePath = Common.PHONE_PATH + path + pathName;
        File f = new File(dir);
        if (f.exists()) {
        } else {
            f.mkdirs();
        }
        File dataFile = new File(filePath);
        if (dataFile.exists()) {
            return true;// 文件存在
        } else {

            InputStream in = this.getAssets().open(pathName);

            File outFile = new File(filePath);
            if (outFile.exists()) {
                outFile.delete();
            }
            OutputStream out = new FileOutputStream(outFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 得到文件下的文件
     *
     * @param keyword
     * @return
     */
    private String searchFile(String keyword) {
        String result = "";
        File[] files = new File(keyword).listFiles();
        for (File file : files) {
            result += file.getName();
        }
        if (result.equals("")) {
            result = "";
        }
        return result;
    }

    /**
     * 读取文件
     *
     * @param inputStream
     * @return
     */
    public static String readStreamToString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public void onBackPressed() {
        
        return;
    }
}
