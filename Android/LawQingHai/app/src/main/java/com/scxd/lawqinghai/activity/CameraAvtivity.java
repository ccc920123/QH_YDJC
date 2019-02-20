package com.scxd.lawqinghai.activity;

import android.Manifest;
import android.view.View;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.fragment.CameraFragment;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.widget.dialog.AlertDialog;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

/**
 * Created by Administrator on 2017/3/16.
 */
public class CameraAvtivity extends BaseActivity {
    
//    private PhotoMessageRqBean mPhotoMessageRqBean;
    private String form;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    public void onTintStatusBar() {
    }

    @Override
    protected void initInjector() {
//        mPhotoMessageRqBean = (PhotoMessageRqBean) getIntent().getExtras().getSerializable("BEAN");
//        form = getIntent().getExtras().getString(Common.FROM);
        MPermissions.requestPermissions(this, Common.PREMISSIONS_CAMERA, Manifest.permission.CAMERA);


    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

//    public static void starCameraActivity(Context context, PhotoMessageRqBean bean, String form) {
//        Intent intent = new Intent(context, CameraAvtivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("BEAN", bean);
//        bundle.putString(Common.FROM, form);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
//    }

    @Override
    public void onBackPressed() {

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment instanceof CameraFragment) {
            finish();
        } else {
            super.onBackPressed();
        }

    }

//    public PhotoMessageRqBean getmPhotoMessageRqBean() {
//        return mPhotoMessageRqBean;
//    }
    
    @PermissionGrant(Common.PREMISSIONS_CAMERA)
    public void requestCameraSucceed() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new CameraFragment())
                .addToBackStack(null)
                .commit();
    }

    @PermissionDenied(Common.PREMISSIONS_CAMERA)
    public void requestCameraFailed() {
        new AlertDialog(this).builder().setTitle("提示")
                .setMsg("拍照会用到相机权限，请确认权限以及相机是否可用！")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).show();
    }

    public String getForm() {
        return form;
    }

}
