package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxd.idcardlibs.DriverScanActivity;
import com.scxd.lawqinghai.BuildConfig;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @类名: ${type_name}
 * @功能描述: 驾驶人信息查询
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class DriverQueryActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.sfzmhm)
    EditText sfzmhm;
    @BindView(R.id.sfzmhm_scan)
    ImageView sfzmhmScan;
    @BindView(R.id.zxbh)
    EditText zxbh;
    @BindView(R.id.zxbh_scan)
    ImageView zxbhScan;
    @BindView(R.id.btn_query)
    Button btnQuery;

    private int MY_SCAN_REQUEST_CODE = 100;
    
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_driver_query;
    }

    @Override
    protected void initInjector() {
        title.setText(R.string.jsrxxcx);
    }

    @Override
    protected void initEventAndData() {
        try {
            copyAssetFile("eng.traineddata");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.sfzmhm_scan, R.id.zxbh_scan, R.id.btn_query})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.sfzmhm_scan:
                intent = new Intent(this, DriverScanActivity.class);
                startActivityForResult(intent, MY_SCAN_REQUEST_CODE);
                break;
            case R.id.zxbh_scan:
                break;
            case R.id.btn_query:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    String sfzmhmStr = sfzmhm.getText().toString().trim();
                    if ("".equals(sfzmhmStr)) {
                        ToastUtils.showToast(this, R.string.SYSTEM_TIPS_SFZMHM);
                        return;
                    }
                    if (sfzmhmStr.length() != 18 && sfzmhmStr.length() != 13){
                        ToastUtils.showToast(this, R.string.SYSTEM_TIPS_SFZMHM);
                        return;
                    }
                    intent = new Intent(this, DriverInformationActivity.class);
                    intent.putExtra("sfzmhm", sfzmhmStr);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_SCAN_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String id = data.getStringExtra("id");
            String tag = data.getStringExtra("tag");
            if (id != null) {
                if (tag.equals("sfz") && id.length() == 18) {
                    sfzmhm.setText(id); //识别结果
                } else if (tag.equals("zxbh") && id.length() == 13){
                    sfzmhm.setText(id); //识别结果
                }
            }
        }
    }

    /**
     * 复制识别数据库到本地内存中
     * @param pathName
     * @return
     * @throws Exception
     */
    private boolean copyAssetFile(String pathName) throws Exception {

        String dir = Common.PHONE_PATH + "ayy/" + "/tessdata";
        String filePath = Common.PHONE_PATH + "ayy/"  + "/tessdata/" + pathName;
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
}
