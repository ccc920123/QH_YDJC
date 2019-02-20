package com.stardon.lib.mainframeuilib.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**类名: UIFragmentPagerAdapter
 * <br/>功能描述: Fragment适配器
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/7
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */



public class UIFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private FragmentManager fm;

    public UIFragmentPagerAdapter(FragmentManager  fm,List<Fragment> mFragments) {
        super(fm);
        this.fm=fm;
        this.mFragments=mFragments;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
