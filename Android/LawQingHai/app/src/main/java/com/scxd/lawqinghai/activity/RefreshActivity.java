package com.scxd.lawqinghai.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.Test02Adapter;
import com.scxd.lawqinghai.adapter.TestAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.response.TestBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.TestPresenter;
import com.scxd.lawqinghai.mvp.view.TestView;
import com.scxd.lawqinghai.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RefreshActivity extends BaseActivity implements TestView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.text)
    TextView text;

    private LinearLayoutManager layoutManager;
    private Test02Adapter adapter02;

    @Override
    protected int getLayoutId() {
        return R.layout.refresh_test;
    }

    @Override
    protected void initInjector() {
        
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mRefreshLayout.setColorSchemeColors(Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN);
        mRefreshLayout.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<TestBean> datas = new ArrayList<>();
        TestAdapter adapter = new TestAdapter(this, datas);
        adapter02 = new Test02Adapter(this);
        adapter02.setOnItemClickListener(new Test02Adapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, TestBean.DataBean testBean) {

            }
        });

        recyclerView.setAdapter(adapter02);
        recycleListener();
    }


    private boolean isLoading;
    public void recycleListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiableItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisiableItemPosition + 1 == adapter02.getItemCount()) {
                    if (!isLoading) {
                        isLoading = true;
                        adapter02.setFooterOn();
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

    @Override
    public void onRefresh() {
        // 启动刷新的控件
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                // 设置是否开始刷新,true为刷新，false为停止刷新
                getMoreData();
            }
        });
    }

    private void getMoreData() {
        mRefreshLayout.setRefreshing(true);
        ((TestPresenter) mPresenter).getDetailsDatas(this, "test");
    }

    @Override
    protected void initEventAndData() {
        onRefresh();
    }

    @Override
    public BasePresenter getPresenter() {
        return new TestPresenter();
    }


    @Override
    public void showLoadProgressDialog(String str) {
        showLoading(str);
    }

    @Override
    public void disDialog() {
        isLoading = false;
        adapter02.setFooterOff();
        dissLoadDialog();
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(this, message);
    }

    @Override
    public void showView(List<TestBean.DataBean> test) {
        isLoading = false;
        adapter02.setFooterOff();
        mRefreshLayout.setRefreshing(false);
        adapter02.setDatas(test);
        ToastUtils.showToast(this, test.get(0).getContent());
    }
    
}
