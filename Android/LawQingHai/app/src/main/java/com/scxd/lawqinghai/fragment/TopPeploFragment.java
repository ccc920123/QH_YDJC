package com.scxd.lawqinghai.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.scxd.idcardlibs.DriverScanActivity;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * 使用人查询
 */
public class TopPeploFragment extends BaseFragment {


    private int MY_SCAN_REQUEST_CODE = 100;
    
    @BindView(R.id.query_content)
    EditText queryContent;
    @BindView(R.id.scan_img)
    ImageView scanImg;
    @BindView(R.id.btn_query)
    Button btnQuery;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_top_peplo;
    }

    @Override
    protected void initInjector() {

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
    protected void lazyLoadData() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.scan_img, R.id.btn_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan_img:
                Intent scanIntent = new Intent(getActivity(), DriverScanActivity.class);
                startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
                break;
            case R.id.btn_query:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_SCAN_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String id = data.getStringExtra("id");
            if (id != null && id.length() == 18 || id.length() == 13) {
                queryContent.setText(id);
            }
        }
    }

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

            InputStream in = getActivity().getAssets().open(pathName);

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
