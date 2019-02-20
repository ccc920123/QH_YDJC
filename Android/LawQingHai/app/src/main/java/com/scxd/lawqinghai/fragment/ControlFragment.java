package com.scxd.lawqinghai.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * 布控界面
 */
public class ControlFragment extends BaseFragment {


    @BindView(R.id.button)
    Button button;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_control;
    }

    @Override
    protected void initInjector() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK&&data!=null) {
            String recognitionResult= data.getStringExtra("result");
            // mShowPicture.setImageBitmap(Utils.getImage(data.getStringExtra("picture")));
        }
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
}
