package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.scxd.lawqinghai.fragment.WaiteFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    
    private List<String> tags;//标示fragment的tag
    private static final int PAGE_COUNT = 3;
    private Context mContext;
    private FragmentManager fragmentManager;

    public FragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        this.fragmentManager = fm;
        tags = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = WaiteFragment.getInstence(String.valueOf(position));
                break;
            case 1:
                fragment = WaiteFragment.getInstence(String.valueOf(position));
                break;
            case 2:
                fragment = WaiteFragment.getInstence(String.valueOf(position));
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    private String[] titles = new String[]{"待签收", "已签收", "已反馈"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    //这个就不说了
    private String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
    //必须重写此方法，添加tag一一做记录
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        tags.add(makeFragmentName(container.getId(), getItemId(position)));
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.fragmentManager.beginTransaction().show(fragment).commit();
        return fragment;
    }
    //根据tag查找缓存的fragment，移除缓存的fragment，替换成新的
    public void setNewFragments() {
        if (this.tags != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (int i = 0; i < tags.size(); i++) {
                fragmentTransaction.remove(fragmentManager.findFragmentByTag(tags.get(i)));
            }
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
            tags.clear();
        }
        notifyDataSetChanged();
    }

}
//jhfghfh