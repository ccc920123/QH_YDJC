package com.scxd.lawqinghai.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.fragment.ControlFragment;
import com.scxd.lawqinghai.fragment.MyFragment;
import com.scxd.lawqinghai.fragment.QueryFragment;
import com.stardon.lib.mainframeuilib.activity.UIMianFrame;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 64550 on 2018/5/17.
 */

public class LawMainActivity extends UIMianFrame {


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


        findId();

        //添加Fragment list
        List<Fragment> mf = new ArrayList<Fragment>();
        mf.add(new QueryFragment());
        mf.add(new ControlFragment());
        mf.add(new MyFragment());
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


    private void findId() {

        vpager = (ViewPager) findViewById(R.id.vpager);
        rbStudioMain = (RadioButton) findViewById(R.id.rb_studio_main);
        rbStudioLoan = (RadioButton) findViewById(R.id.rb_studio_loan);
//        rbStudioMessage = (RadioButton) findViewById(R.id.rb_studio_message);
        rgTabBar = (RadioGroup) findViewById(R.id.rg_tab_bar);
//        mTitle = (TextView) findViewById(R.id.titletext);

    }
    
    @Override
    public int getContentLayout() {
        return R.layout.activity_ui;
    }


    @Override
    protected void onResume() {
        super.onResume();

//        if (!rid.equals("")) {
//            setStyleCustom();
//            TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
//            // 查询alias : ACTION_GET, 设置 ： ACTION_SET， 删除 ：ACTION_DELETE
//            tagAliasBean.action = ACTION_SET;
//            sequence++;
//            //设置alias
//            //tagAliasBean.tags = getInPutTags("test");
//            //tagAliasBean.isAliasAction = true;
//            //设置tag
//            tagAliasBean.tags = getInPutTags(Common.USERNAME);
//            TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(), sequence, tagAliasBean);
//        }
    }

}
