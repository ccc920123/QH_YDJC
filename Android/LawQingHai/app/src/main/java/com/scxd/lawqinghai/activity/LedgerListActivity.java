package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.LedgerListAdapter;
import com.scxd.lawqinghai.adapter.WarnListAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.MessageEvent;
import com.scxd.lawqinghai.bean.response.LedgerListRespBean;
import com.scxd.lawqinghai.bean.response.WarnListBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.LedgerListPresenter;
import com.scxd.lawqinghai.mvp.presenter.WarnListPresenter;
import com.scxd.lawqinghai.mvp.view.LedgerListView;
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

/**
 * @类名: ${type_name}
 * @功能描述: 预警清单列表
 * @作者: 张翔
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LedgerListActivity extends BaseActivity implements LedgerListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.no_datas)
    LinearLayout noDatas;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.timestar)
    TextView timestar;
    @BindView(R.id.timeend)
    TextView timeend;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.xxts)
    TextView xxts;


    private LinearLayoutManager layoutManager;
    private LedgerListAdapter mAdapter;

    @Override
    public BasePresenter getPresenter() {
        return new LedgerListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ledger_refresh;
    }

    @Override
    protected void initInjector() {
        more.setVisibility(View.VISIBLE);
        title.setText("台账");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LedgerListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        timestar.setText(Date_U.getCurrentTime());
        timeend.setText(Date_U.getCurrentTime());
    }

    @Override
    protected void initEventAndData() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mRefreshLayout.setColorSchemeColors(Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN);
        mRefreshLayout.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mListBeans = new LinkedList<>();

        ((LedgerListPresenter) mPresenter).load(this, 
                SharedPreferencesUtils.getString(this, "USER", ""),
                timestar.getText().toString().trim(),
                timeend.getText().toString().trim(), 1);

        mAdapter = new LedgerListAdapter(this);
        mAdapter.setOnItemClickListener(new LedgerListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, LedgerListRespBean.DataBean.ListBean bean) {
                Intent intent = new Intent(LedgerListActivity.this, LedgerActivity.class);
                intent.putExtra("TAG", "Details");
                intent.putExtra("LSH", bean);
                startActivity(intent);
//                finish();
            }
        });

        recyclerView.setAdapter(mAdapter);
        recycleListener();
    }

    private boolean isLoading;

    public void recycleListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiableItemPosition = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisiableItemPosition + 1 == mAdapter
                        .getItemCount()) {
                    if (!isLoading) {
                        isLoading = true;
                        mAdapter.setFooterOn();
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
                ((LedgerListPresenter) mPresenter).load(LedgerListActivity.this, SharedPreferencesUtils.getString
                        (LedgerListActivity.this, "USER", ""),
                        timestar.getText().toString().trim(),
                        timeend.getText().toString().trim(), 1);
            }
        });
    }

    private void getMoreData() {
        mRefreshLayout.setRefreshing(false);
        ((LedgerListPresenter) mPresenter).load(this, SharedPreferencesUtils.getString(this, "USER", ""),
                timestar.getText().toString().trim(),
                timeend.getText().toString().trim(), 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoadProgressDialog(String str) {
        showLoading(str);
    }

    @Override
    public void disDialog() {
        mAdapter.setFooterOff();
        mRefreshLayout.setRefreshing(false);
        dissLoadDialog();
    }

    @Override
    public void showToast(String message) {
        mAdapter.setFooterOff();
        mRefreshLayout.setRefreshing(false);
        //        ToastUtils.showToast(this, message);
        
        if (mListBeans.size() == 0) {
            xxts.setText("共：0条");
            noDatas.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
        } else {
            noDatas.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);

            mAdapter.setDatas(mListBeans);
        }
    }

    private List<LedgerListRespBean.DataBean.ListBean> mListBeans;

    @Override
    public void showDatas(LedgerListRespBean.DataBean bean) {
        
        xxts.setText("共：" + bean.getTotal() + "条");
        
        mAdapter.setFooterOff();
        mRefreshLayout.setRefreshing(false);
        isLoading = false;
        if (isFirst) {
            mListBeans.clear();
            isFirst = false;
        }
        mListBeans.addAll(bean.getList());
        if (mListBeans.size() == 0) {
            noDatas.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
        } else {
            noDatas.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);

            mAdapter.setDatas(mListBeans);
        }
    }

    @Override
    public void showFailed(String message) {

        mAdapter.setFooterOff();
        mRefreshLayout.setRefreshing(false);

        ToastUtils.showToast(this, message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        
    }

    @OnClick({R.id.more, R.id.timestar, R.id.timeend, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more:
                Intent intent = new Intent(this, LedgerActivity.class);
                intent.putExtra("TAG", "ADD");
                startActivity(intent);
                finish();
                break;
            case R.id.timestar:
                CoustomCheckTimePickerView.getInstance().getRqTime(this, timestar);
                break;
            case R.id.timeend:
                CoustomCheckTimePickerView.getInstance().getRqTime(this, timeend);
                break;
            case R.id.search:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    if (Date_U.datayyyyMMdd(timestar.getText().toString()) > Date_U.datayyyyMMdd(timeend.getText().toString())) {
                        ToastUtils.showToast(this, "开始日期不能大于结束日期");
                        break;
                    }
                    isFirst = true;
                    mListBeans.clear();
                    ((LedgerListPresenter) mPresenter).load(this, 
                            SharedPreferencesUtils.getString(this, "USER", ""), 
                            timestar.getText().toString().trim(), 
                            timeend.getText().toString().trim(), 1);
                }
                break;
        }
    }
}
