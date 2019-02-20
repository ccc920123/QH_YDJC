package com.scxd.lawqinghai.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.CarInformationActivity;
import com.scxd.lawqinghai.activity.CarLawDetailsActivity;
import com.scxd.lawqinghai.adapter.CarLawAdapter;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.CarLawListBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.TestBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.CarLawListPresenter;
import com.scxd.lawqinghai.mvp.presenter.DriverLawListPresenter;
import com.scxd.lawqinghai.mvp.view.BaseView;
import com.scxd.lawqinghai.mvp.view.CarLawListView;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述: 机动车违法清单
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarLawFragment extends BaseFragment implements CarLawListView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.no_datas)
    LinearLayout noDatas;

    private CarLawAdapter mCarLawAdapter;
    private LinearLayoutManager layoutManager;
    private String hphmIntent, hpzlIntent;
    private List<CarLawListBean> mCarLawListBeans;
    private Context fragmentContext;
    Map<String, String> wfztMap;

    @Override
    public BasePresenter getPresenter() {
        return new CarLawListPresenter();
    }

    /**
     * 获取从机动车查询界面传递过来的查询条件内容
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.fragmentContext = context;
        hphmIntent = ((CarInformationActivity) context).getHphm();
        hpzlIntent = ((CarInformationActivity) context).getHpzl();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_refresh_no_title;
    }

    @Override
    protected void initInjector() {

        refresh.setColorSchemeColors(Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN);//设置刷新动态颜色
        refresh.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void initEventAndData() {


    }

    @Override
    protected void lazyLoadData() {
        try {

            wfztMap = new HashMap<>();
            for (DicationResBean bean : DictionaryManager.getInstance().getWfzt()) {
                wfztMap.put(bean.getCode(), bean.getName());
            }
            mCarLawListBeans = new ArrayList<>();
            // 监听item
            mCarLawAdapter = new CarLawAdapter(getActivity(), wfztMap);
            mCarLawAdapter.setOnItemClickListener(new CarLawAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position, CarLawListBean bean) {
                    if (!ButtonTools.isFastDoubleClick(1500)) {
                        Intent intent = new Intent(getActivity(), CarLawDetailsActivity.class);
                        intent.putExtra("xh", bean.getXh());
                        intent.putExtra("state", bean.getWfzt());
                        getActivity().startActivity(intent);
                    }
                }
            });

            recyclerView.setAdapter(mCarLawAdapter);
            recycleListener();
            //查询数据
            if (null == mPresenter) {
                mPresenter = new DriverLawListPresenter();
                if (mPresenter != null && this instanceof BaseView) {
                    mPresenter.attach((BaseView) this);
                }
            }
            ((CarLawListPresenter) mPresenter).loadCarLawList(fragmentContext, hpzlIntent, hphmIntent, 1);
        } catch (Exception e) {
            ToastUtils.showToast(fragmentContext, "请确定数据是否更新");
        }
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

    private boolean isLoading;// 判断是否正在加载
    private int lastVisiableItemPosition;

    public void recycleListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiableItemPosition = layoutManager.findLastVisibleItemPosition();
                //判断是否已经滑到最后一列
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisiableItemPosition + 1 == mCarLawAdapter.getItemCount()) {

                    if (!isLoading) {
                        isLoading = true;
                        mCarLawAdapter.setFooterOn();
                        getMoreData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private boolean isFirst = true; // 判断是否为第一次加载

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        // 启动刷新的控件
        refresh.post(new Runnable() {
            @Override
            public void run() {
                // 设置是否开始刷新,true为刷新，false为停止刷新
                refresh.setRefreshing(true);// 开启下来刷新状态
                isFirst = true;
                ((CarLawListPresenter) mPresenter).loadCarLawList(getActivity(), hpzlIntent, hphmIntent, 1);
            }
        });
    }

    /**
     * 上拉加载更多
     */
    private void getMoreData() {
        refresh.setRefreshing(false); // 关闭下来刷新状态
        ((CarLawListPresenter) mPresenter).loadCarLawList(getActivity(), hpzlIntent, hphmIntent, 0);
    }

    @Override
    public void showLoadProgressDialog(String str) {
        showLoading(str);
    }

    @Override
    public void disDialog() {
        closeRefresh();
        dissLoadDialog();
    }

    @Override
    public void showToast(String message) {
        closeRefresh();

        if (null == mCarLawListBeans || mCarLawListBeans.size() == 0) {

            refresh.setVisibility(View.GONE);
            noDatas.setVisibility(View.VISIBLE);
        } else {

            refresh.setVisibility(View.VISIBLE);
            noDatas.setVisibility(View.GONE);

        }
//        ToastUtils.showToast(getActivity(), message);
    }

    /**
     * 数据展示
     *
     * @param dataBean
     */

    @Override
    public void showCarDatas(List<CarLawListBean> dataBean) {

        closeRefresh();

        isLoading = false;
        if (isFirst) {
            mCarLawListBeans.clear();
            isFirst = false;
        }
        mCarLawListBeans.addAll(dataBean);
        if (mCarLawListBeans.size() == 0) {
            noDatas.setVisibility(View.VISIBLE);
            refresh.setVisibility(View.GONE);
        } else {
            noDatas.setVisibility(View.GONE);
            refresh.setVisibility(View.VISIBLE);
            mCarLawAdapter.setDatas(mCarLawListBeans);
        }
    }

    /**
     * 关闭刷新状态
     */
    public void closeRefresh() {
        if (null != mCarLawAdapter) {
            mCarLawAdapter.setFooterOff();// 关闭上拉刷新状态
            refresh.setRefreshing(false);// 关闭下来刷新状态
        }

    }

}
