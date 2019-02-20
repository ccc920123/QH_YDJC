package com.scxd.lawqinghai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.widget.CustomViewPager;
import com.stardon.lib.mainframeuilib.activity.adapter.UIFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 查询界面
 */
public class QueryFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.rb_jsrtop_main)
    RadioButton rbJsrtopMain;
    @BindView(R.id.rb_jdctop_loan)
    RadioButton rbJdctopLoan;
    @BindView(R.id.rg_tabtop_bar)
    RadioGroup rgTabtopBar;
    @BindView(R.id.vtoppager)

    CustomViewPager mViewPager;
    //viewpage的适配器
    private UIFragmentPagerAdapter mAdapter;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_query;
    }

    @Override
    protected void initInjector() {
        mViewPager.setScanScroll(false);
        //添加Fragment list
        List<Fragment> mf = new ArrayList<Fragment>();
        mf.add(new TopPeploFragment());
        mf.add(new TopCarFragment());
        mAdapter = new UIFragmentPagerAdapter(this.getChildFragmentManager(), mf);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        rbJsrtopMain.setChecked(true);

    }

    @Override
    protected void initEventAndData() {

        rgTabtopBar.setOnCheckedChangeListener(this);


    }

    @Override
    protected void lazyLoadData() {

    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }



    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_jsrtop_main:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_jdctop_loan:
                mViewPager.setCurrentItem(1);
                break;
        }

    }



}
