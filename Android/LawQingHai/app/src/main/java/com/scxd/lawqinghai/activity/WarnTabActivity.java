package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.FragmentAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.WarnListPresenter;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.widget.CoustomCheckTimePickerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @类名: ${type_name}
 * @功能描述: 预警Tab
 * @作者: 张翔
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnTabActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.main_fragment)
    FrameLayout mainFragment;
    @BindView(R.id.text_dqs)
    TextView textDqs;
    @BindView(R.id.text_yqs)
    TextView textYqs;
    @BindView(R.id.text_yfk)
    TextView textYfk;

    private FragmentAdapter adapter;

    @Override
    public BasePresenter getPresenter() {
        return new WarnListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_warn_tab;
    }

    @Override
    protected void initInjector() {
        title.setText(R.string.yjxx);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WarnTabActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initEventAndData() {
        if (adapter == null) {
            adapter = new FragmentAdapter(this, this.getSupportFragmentManager());//继承FragmentPagerAdapter
        }
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewpager);
        if (true) {//是否强制刷新，此处做判断
            adapter.setNewFragments();//这是我在FragmentPagerAdapter中定义的刷新方法，
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
