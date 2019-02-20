package com.scxd.lawqinghai.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.view.BaseView;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.WaterMarkUtil;
import com.scxd.lawqinghai.widget.dialog.CustomPrograss;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zwl on 16/9/30.
 */

public abstract class BaseFragment<T extends BasePresenter<BaseView>> extends Fragment implements IBase {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    public Context mActivity;
    //是否可见状态
    private boolean isVisible;
    //View已经初始化完成
    private boolean isPrepared;
    //是否第一次加载完
    private boolean isFirstLoad;

    protected BasePresenter mPresenter;
    private View view = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isFirstLoad = true;
        try {
            if (view == null) {
                view = inflater.inflate(getlayoutId(), container, false);
            }
            ButterKnife.bind(this, view);
            isPrepared = true;
            initInjector();//dagger2注解,子类实现initInjector()方法 进行inject()
            if (savedInstanceState != null) {
                initStateData(savedInstanceState);
            }
            mPresenter = getPresenter();
            if (mPresenter != null && this instanceof BaseView) {
                mPresenter.attach((BaseView) this);
            }
            //初始化事件和获取数据, 在此方法中获取数据不是懒加载模式
            initEventAndData();
            //在此方法中获取数据为懒加载模式,如不需要懒加载,请在initEventAndData获取数据,GankFragment有使用实例
            lazyLoad();
            String xmstr = SharedPreferencesUtils.getString(getActivity(), "xm", "");
            String bmmcstr = SharedPreferencesUtils.getString(getActivity(), "bmmc", "");
            WaterMarkUtil.showWatermarkView(getActivity(), bmmcstr + "\n\n" + xmstr);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return view;
    }

    public void initStateData(Bundle savedInstanceState) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onAttach(Context context) {
        this.mActivity = context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) 
            return;
        isFirstLoad = false;
        lazyLoadData();
    }

    protected abstract int getlayoutId();

    protected abstract void initInjector();

    protected abstract void initEventAndData();

    protected abstract void lazyLoadData();

    public void showLoading(String string) {
        CustomPrograss.show(getActivity(), string, true, null);
    }

    public void dissLoadDialog() {
        CustomPrograss.disMiss();
    }
}
