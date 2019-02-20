package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.fragment.CarBasicFragment;
import com.scxd.lawqinghai.fragment.CarLawFragment;
import com.stardon.lib.mainframeuilib.activity.UIMianFrame;
import com.stardon.lib.mainframeuilib.activity.impl.IBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  机动车信息
 * 
 * Created by 64550 on 2018/5/17.
 */

public class CarInformationActivity extends UIMianFrame {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.vpager)
    ViewPager vpager;
    @BindView(R.id.rb_studio_main)
    RadioButton rbStudioMain;
    @BindView(R.id.rb_studio_loan)
    RadioButton rbStudioLoan;
    @BindView(R.id.rg_tab_bar)
    RadioGroup rgTabBar;

    @Override
    public void bindView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        
        title.setText(R.string.jdccxmx);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        

        //添加Fragment list
        List<Fragment> mf = new ArrayList<Fragment>();
        mf.add(new CarBasicFragment());
        mf.add(new CarLawFragment());
        processLogic(vpager, mf);
        //添加RadioButton list
        List<RadioButton> radList = new ArrayList<RadioButton>();
        radList.add(rbStudioMain);
        radList.add(rbStudioLoan);
        //设置标题，如果你不需要改标题可以不用调用该方法，
        // 如果你是在Fragment设置标题也可以不用改标题
        //        setUiFrameTitle(mTitle);
        //设置viewpager的PagerOnPageChangeListener事件
        setViewPagerOnPageChangeListener(radList);
        //设置RadioGroup 的CheckedChangeListener事件
        setRadioGroupOnCheckedChangeListener(rgTabBar);
        //设置iewpager首次展示的页面
        setCurrentItem(0);

    }

    /**
     * 设置号牌号码  号牌种类
     * @return
     */
    public String getHphm(){
        return getIntent().getStringExtra("hphm");
    }
    
    public String getHpzl(){
        return getIntent().getStringExtra("hpzl");
    }
    
    
    @Override
    public int getContentLayout() {
        return R.layout.activity_ui;
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CarQueryActivity.class);
        startActivity(intent);
        finish();
    }
}
