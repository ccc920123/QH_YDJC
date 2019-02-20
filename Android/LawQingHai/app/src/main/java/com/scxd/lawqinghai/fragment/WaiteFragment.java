package com.scxd.lawqinghai.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.WarnDetailsActivity;
import com.scxd.lawqinghai.activity.WarnTabActivity;
import com.scxd.lawqinghai.adapter.WarnListAdapter;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.bean.MessageEvent;
import com.scxd.lawqinghai.bean.response.WarnListBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.WarnListPresenter;
import com.scxd.lawqinghai.mvp.view.WarnListView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.CoustomCheckTimePickerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @类名: ${type_name}
 * @功能描述: 预警列表
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WaiteFragment extends BaseFragment implements WarnListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.no_datas)
    LinearLayout noDatas;
    Unbinder unbinder;
    @BindView(R.id.xxts)
    TextView xxts;
    @BindView(R.id.timestar)
    TextView timestar;
    @BindView(R.id.timeend)
    TextView timeend;
    @BindView(R.id.search)
    Button search;

    private Button sreach;
    private String type;
    private LinearLayoutManager layoutManager;
    private WarnListAdapter mWarnListAdapter;
    private List<WarnListBean.DataBean.ListBean> mWarnListBeans;
    
    
    public static WaiteFragment getInstence(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        WaiteFragment fragment = new WaiteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public BasePresenter getPresenter() {
        return new WarnListPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_refresh;
    }

    @Override
    protected void initInjector() {
        type = getArguments().getString("type");
        timestar.setText(Date_U.getCurrentTime());
        timeend.setText(Date_U.getCurrentTime());
    }

    @Override
    protected void initEventAndData() {
        mRefreshLayout.setColorSchemeColors(Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN);
        mRefreshLayout.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mWarnListBeans = new LinkedList<>();

        mWarnListAdapter = new WarnListAdapter(getActivity());
        mWarnListAdapter.setOnItemClickListener(new WarnListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, WarnListBean.DataBean.ListBean bean) {
                Common.YJXH = bean.getYjxh();
                Intent intent = new Intent(getActivity(), WarnDetailsActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        recyclerView.setAdapter(mWarnListAdapter);
        recycleListener();


        EventBus.getDefault().register(this);
    }

    /**
     * 通过接受到推送，刷新当前的数据列表
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if (messageEvent.isWarn()) {
            //将数据添加到 mWarnListBeans

            WarnListBean.DataBean.ListBean warnBean = new WarnListBean.DataBean.ListBean();

            warnBean.setYjxh(messageEvent.getYjxh());
            warnBean.setHphm(messageEvent.getHphm());
            warnBean.setCllx(messageEvent.getCllx());
            warnBean.setYjsj(messageEvent.getYjsj());
            warnBean.setYjlx(messageEvent.getYjlx());
            warnBean.setStatus(messageEvent.getStatus());
            mWarnListBeans.add(0, warnBean);
            mWarnListAdapter.setDatas(mWarnListBeans);

        }


    }

    @Override
    protected void lazyLoadData() {
        isFirst = true;
        ((WarnListPresenter) mPresenter).load(getActivity(), SharedPreferencesUtils.getString(getActivity(), "USER", 
                ""), timestar.getText().toString().trim(), timeend.getText().toString().trim()
                , type, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private boolean isLoading;

    public void recycleListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiableItemPosition = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisiableItemPosition + 1 == mWarnListAdapter
                        .getItemCount()) {
                    if (!isLoading) {
                        isLoading = true;
                        mWarnListAdapter.setFooterOn();
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

    private boolean isFirst = true;

    @Override
    public void onRefresh() {
        // 启动刷新的控件
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                // 设置是否开始刷新,true为刷新，false为停止刷新
                mRefreshLayout.setRefreshing(true);
                isFirst = true;
                ((WarnListPresenter) mPresenter).load(getActivity(), SharedPreferencesUtils.getString(getActivity(), 
                        "USER", ""), timestar.getText().toString().trim(), timeend.getText().toString().trim(), 
                        type, 1);
            }
        });
    }

    private void getMoreData() {
        mRefreshLayout.setRefreshing(false);
        ((WarnListPresenter) mPresenter).load(getActivity(), SharedPreferencesUtils.getString(getActivity(), "USER", 
                ""), timestar.getText().toString().trim(), timeend.getText().toString().trim()
                , type, 0);
    }

    @Override
    public void showLoadProgressDialog(String str) {
        showLoading(str);
    }

    @Override
    public void disDialog() {
        mWarnListAdapter.setFooterOff();
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshing(false);
        }
        dissLoadDialog();
    }

    @Override
    public void showToast(String message) {
        mWarnListAdapter.setFooterOff();
        mRefreshLayout.setRefreshing(false);

        if (mWarnListBeans.size() == 0) {
            xxts.setText("共有：0条");
            noDatas.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
        } else {
            noDatas.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);

            mWarnListAdapter.setDatas(mWarnListBeans);
        }
    }

    @Override
    public void showDatas(WarnListBean.DataBean bean) {
        
        xxts.setText("共有：" + bean.getTotal() + "条");
        mWarnListAdapter.setFooterOff();
        mRefreshLayout.setRefreshing(false);
        isLoading = false;
        if (isFirst) {
            mWarnListBeans.clear();
            isFirst = false;
        }
        mWarnListBeans.addAll(bean.getList());
        if (mWarnListBeans.size() == 0) {
            noDatas.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
        } else {
            noDatas.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);

            mWarnListAdapter.setDatas(mWarnListBeans);
        }
    }

    @Override
    public void showFailed(String message) {

        mWarnListAdapter.setFooterOff();
        mRefreshLayout.setRefreshing(false);

        ToastUtils.showToast(getActivity(), message);
    }
    
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({R.id.timestar, R.id.timeend, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.timestar:
                CoustomCheckTimePickerView.getInstance().getRqTime(getActivity(), timestar);
                break;
            case R.id.timeend:
                CoustomCheckTimePickerView.getInstance().getRqTime(getActivity(), timeend);
                break;
            case R.id.search:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    if (Date_U.datayyyyMMdd(timestar.getText().toString()) > Date_U.datayyyyMMdd(timeend.getText().toString())) {
                        ToastUtils.showToast(getActivity(), "开始日期不能大于结束日期");
                        break;
                    }
                    isFirst = true;
                    mWarnListBeans.clear();
                    ((WarnListPresenter) mPresenter).load(getActivity(), SharedPreferencesUtils.getString(getActivity(), "USER", ""), timestar.getText().toString().trim(), timeend.getText().toString().trim(), type, 1);
                }
                break;
        }
    }
}
