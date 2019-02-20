package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.CodeQueryAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.bean.response.QueryCodeRspBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.CodeLawListPresenter;
import com.scxd.lawqinghai.mvp.view.CodeLawListView;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @类名: ${type_name}
 * @功能描述: 违法代码查询
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CodeQueryActivity extends BaseActivity implements CodeLawListView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.wfdm)
    EditText wfdm;
    @BindView(R.id.wfnr)
    EditText wfnr;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.no_datas)
    LinearLayout noDatas;
    @BindView(R.id.xxts)
    TextView xxts;

    private CodeQueryAdapter mCodeQueryAdapter;
    private LinearLayoutManager layoutManager;
    private List<QueryCodeRspBean.DataBean.ListBean> mListBeans;

    private String wfdmStr = "", wfnrStr = "";

    @Override
    public BasePresenter getPresenter() {
        return new CodeLawListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_law_code;
    }

    @Override
    protected void initInjector() {
        title.setText(R.string.wfdmcx);
    }

    @Override
    protected void initEventAndData() {
        refresh.setColorSchemeColors(Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN);
        refresh.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mCodeQueryAdapter = new CodeQueryAdapter(this);
        mCodeQueryAdapter.setOnItemClickListener(new CodeQueryAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, QueryCodeRspBean.DataBean.ListBean testBean) {
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    Intent intent = new Intent(CodeQueryActivity.this, CodeDetailsActivity.class);
                    intent.putExtra("WFDM", testBean.getWfdm());
                    startActivity(intent);
                }
            }
        });

        recyclerView.setAdapter(mCodeQueryAdapter);
        recycleListener();


        mListBeans = new ArrayList<>();
        //默认查询数据
        ((CodeLawListPresenter) mPresenter).loadCodeLawList(this, wfdmStr, wfnrStr, 1);

    }

    @OnClick({R.id.back, R.id.query})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.query:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    wfdmStr = wfdm.getText().toString().trim();
                    wfnrStr = wfnr.getText().toString().trim();
//                    if ("".equals(wfnrStr) && "".equals(wfnrStr)) {
//                        ToastUtils.showToast(this, "请输入查询条件");
//                        return;
//                    }
                    isFirst = true;
                    mListBeans.clear();
                    ((CodeLawListPresenter) mPresenter).loadCodeLawList(this, wfdmStr, wfnrStr, 1);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isLoading;// 判断是否正在加载

    public void recycleListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiableItemPosition = layoutManager.findLastVisibleItemPosition();
                //判断是否已经滑到最后一列
                if (lastVisiableItemPosition + 1 == mCodeQueryAdapter.getItemCount()) {
                    if (!isLoading) {
                        isLoading = true;
                        mCodeQueryAdapter.setFooterOn();
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


    @Override
    public void onRefresh() {
        // 启动刷新的控件
        refresh.post(new Runnable() {
            @Override
            public void run() {
                // 设置是否开始刷新,true为刷新，false为停止刷新
                refresh.setRefreshing(true);// 开启下来刷新状态
                isFirst = true;
                ((CodeLawListPresenter) mPresenter).loadCodeLawList(CodeQueryActivity.this, wfdmStr, wfnrStr, 1);
            }
        });
    }

    private void getMoreData() {
        refresh.setRefreshing(false); // 关闭下来刷新状态
        ((CodeLawListPresenter) mPresenter).loadCodeLawList(CodeQueryActivity.this, wfdmStr, wfnrStr, 0);
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
        if (null == mListBeans || mListBeans.size() == 0){
            xxts.setText("共有：0条");
            refresh.setVisibility(View.GONE);
            noDatas.setVisibility(View.VISIBLE);
        } else {

            refresh.setVisibility(View.VISIBLE);
            noDatas.setVisibility(View.GONE);

        }
        ToastUtils.showToast(this, message);
    }

    @Override
    public void showCodeDatas(QueryCodeRspBean.DataBean dataBean) {
        closeRefresh();

        xxts.setText("共有：" + dataBean.getXxts() + "条");
        
        isLoading = false;
        if (isFirst) {
            mListBeans.clear();
            isFirst = false;
        }

        mListBeans.addAll(dataBean.getList());
        
        if (mListBeans.size() == 0){

            refresh.setVisibility(View.GONE);
            noDatas.setVisibility(View.VISIBLE);
        } else {

            refresh.setVisibility(View.VISIBLE);
            noDatas.setVisibility(View.GONE);

            mCodeQueryAdapter.setDatas(mListBeans);
        }
        
    }

    /**
     * 关闭刷新状态
     */
    public void closeRefresh() {

        mCodeQueryAdapter.setFooterOff();// 关闭上拉刷新状态
        refresh.setRefreshing(false);// 关闭下来刷新状态

    }
}
