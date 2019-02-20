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
import com.scxd.lawqinghai.adapter.ElectronicMonitoringInputAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.response.ElectronicMonitoringInputResBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.ElectronicMonitoringInputPresenter;
import com.scxd.lawqinghai.mvp.view.ElectronicMonitoringInputView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.CoustomCheckTimePickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述：电子监控录入
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/4 10:44
 * 修改人：
 * 修改时间：
 */


public class ElectronicMonitoringInputActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, 
        ElectronicMonitoringInputView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.timestar)
    TextView timestar;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.timeend)
    TextView timeend;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.no_datas)
    LinearLayout noDatas;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.xxts)
    TextView xxts;
    private LinearLayoutManager layoutManager;
    private ElectronicMonitoringInputAdapter mAdapter;
    private List<ElectronicMonitoringInputResBean.DataBean.ListBean> ListBeans;
    private String user;
    private String bmbh;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_electronic_monitoring_input;
    }

    @Override
    protected void initInjector() {
        more.setVisibility(View.VISIBLE);
        title.setText("电子监控列表");
        user = SharedPreferencesUtils.getString(this, "USER", "");
        bmbh = SharedPreferencesUtils.getString(this, "BMBH", "");

        timestar.setText(Date_U.getCurrentTime());
        timeend.setText(Date_U.getCurrentTime());
    }


    @Override
    protected void initEventAndData() {
        mRefreshLayout.setColorSchemeColors(Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN);
        mRefreshLayout.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ListBeans = new ArrayList<>();

        mAdapter = new ElectronicMonitoringInputAdapter(this);
        mAdapter.setOnItemClickListener(new ElectronicMonitoringInputAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object bean) {
                //tag=1表示详情，tag=2表示打印

                if ("1".equals(view.getTag().toString())) {
                    Intent intent = new Intent(ElectronicMonitoringInputActivity.this, 
                            ElectronicMonitoringInputDetailActivity.class);
                    intent.putExtra("TAG", "Detail");
                    intent.putExtra("XH", ((ElectronicMonitoringInputResBean.DataBean.ListBean) bean).getJdsbh());
                    startActivity(intent);
                    //                    finish();

                } else if ("2".equals(view.getTag().toString())) {

                    Intent intent = new Intent(ElectronicMonitoringInputActivity.this, PrintImageActivity.class);
                    intent.putExtra("TAG", "ListElectronicMonitoringInputActivity");
                    intent.putExtra("XH", ((ElectronicMonitoringInputResBean.DataBean.ListBean) bean).getJdsbh());
                    startActivity(intent);
                    finish();
                }


            }
        });
        recyclerView.setAdapter(mAdapter);
        recycleListener();

        //请求数据
        ((ElectronicMonitoringInputPresenter) mPresenter).querElectronicMonitoringInput(user, bmbh, timestar.getText
                ().toString(), timeend.getText().toString(), 1);


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

    private void getMoreData() {
        mRefreshLayout.setRefreshing(false);
        ((ElectronicMonitoringInputPresenter) mPresenter).querElectronicMonitoringInput(user, bmbh, timestar.getText
                ().toString(), timeend.getText().toString(), 0);
    }

    @Override
    public BasePresenter getPresenter() {
        return new ElectronicMonitoringInputPresenter();
    }


    @OnClick({R.id.back, R.id.timestar, R.id.timeend, R.id.search, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();

                break;
            case R.id.timestar:
                CoustomCheckTimePickerView.getInstance().getRqTime(this, timestar);

                break;
            case R.id.timeend:
                CoustomCheckTimePickerView.getInstance().getRqTime(this, timeend);
                break;
            case R.id.search:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    if (Date_U.datayyyyMMdd(timestar.getText().toString()) > Date_U.datayyyyMMdd(timeend.getText()
                            .toString())) {
                        ToastUtils.showToast(ElectronicMonitoringInputActivity.this, "开始日期不能大于结束日期");
                        break;
                    }
                    isFirst = true;
                    ListBeans.clear();
                    ((ElectronicMonitoringInputPresenter) mPresenter).querElectronicMonitoringInput(user, bmbh, 
                            timestar.getText().toString(), timeend.getText().toString(), 1);
                }
                break;
            case R.id.more:

                Intent intent = new Intent(this, ElectronicMonitoringInputDetailActivity.class);
                intent.putExtra("TAG", "newAdd");
                intent.putExtra("XH", "");
                startActivity(intent);
                //                finish();

                break;

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRefresh() {
        // 启动刷新的控件
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                // 设置是否开始刷新,true为刷新，false为停止刷新
                mRefreshLayout.setRefreshing(true);
                isFirst = true;
                ((ElectronicMonitoringInputPresenter) mPresenter).querElectronicMonitoringInput(user, bmbh, timestar
                        .getText().toString(), timeend.getText().toString(), 1);
            }
        });
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
        if (null == ListBeans || ListBeans.size() == 0) {
            xxts.setText("共有：0条");
            mRefreshLayout.setVisibility(View.GONE);
            noDatas.setVisibility(View.VISIBLE);
        } else {
            mRefreshLayout.setVisibility(View.VISIBLE);
            noDatas.setVisibility(View.GONE);
        }
        //        ToastUtils.showToast(this, message);
    }

    //得到返回的数据
    @Override
    public void backElectronicMonitoringInput(ElectronicMonitoringInputResBean.DataBean obj) {
        
        xxts.setText("共有：" + obj.getTotal() + "条");
        
        mAdapter.setFooterOff();
        mRefreshLayout.setRefreshing(false);
        isLoading = false;
        if (isFirst) {
            ListBeans.clear();
            isFirst = false;
        }
        ListBeans.addAll(obj.getList());
        if (ListBeans.size() == 0) {

            mRefreshLayout.setVisibility(View.GONE);
            noDatas.setVisibility(View.VISIBLE);
        } else {
            mRefreshLayout.setVisibility(View.VISIBLE);
            noDatas.setVisibility(View.GONE);
            mAdapter.setDatas(ListBeans);
        }

    }

}
