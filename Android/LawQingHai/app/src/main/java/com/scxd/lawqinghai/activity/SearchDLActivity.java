package com.scxd.lawqinghai.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.LeftListAdapter;
import com.scxd.lawqinghai.adapter.MainSectionedAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.bean.response.DLRespBean;
import com.scxd.lawqinghai.bean.response.LDRespBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.SearchDLPresenter;
import com.scxd.lawqinghai.mvp.view.SearchDLView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.PinnedHeaderListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/7
 * 修改人：
 * 修改时间：
 */


public class SearchDLActivity extends BaseActivity implements SearchDLView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.dlmd)
    EditText dlmd;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.left_listview)
    ListView leftListview;
    @BindView(R.id.pinnedListView)
    PinnedHeaderListView pinnedListView;
    @BindView(R.id.no_datas)
    LinearLayout noDatas;

    private LeftListAdapter adapter;
    private boolean isScroll = true;

    private List<DLRespBean> beanDL;
    private List<Boolean> bList;//标记那个选择中
    private MainSectionedAdapter sectionedAdapter;

    @Override
    public BasePresenter getPresenter() {
        return new SearchDLPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_searchdl;
    }

    @Override
    protected void initInjector() {

        title.setText("道路/路段查询");

    }

    @Override
    protected void initEventAndData() {
        sectionedAdapter = new MainSectionedAdapter(this);
        pinnedListView.setAdapter(sectionedAdapter);
        adapter = new LeftListAdapter(this);
        leftListview.setAdapter(adapter);
        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                isScroll = false;

                for (int i = 0; i < beanDL.size(); i++) {
                    if (i == position) {
                        if (null == bList.get(i)) {
                            bList.add(true);

                        } else {
                            bList.remove(i);
                            bList.add(i,true);

                        }
                    } else {
                        if (null == bList.get(i)) {
                            bList.add(false);

                        } else {
                            bList.remove(i);
                            bList.add(i,false);
                        }
                    }
                }
                adapter.setLeftDLList(beanDL, bList);
                adapter.notifyDataSetChanged();

                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                pinnedListView.setSelection(rightSection);

                SearchLDReqBean ldBean = new SearchLDReqBean(
                        SharedPreferencesUtils.getString(SearchDLActivity.this, "SJBM", ""),
                        beanDL.get(position).getDldm());

                ((SearchDLPresenter) mPresenter).searchLD(ldBean, beanDL.get(position));


            }

        });


        pinnedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView arg0, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (pinnedListView.getLastVisiblePosition() == (pinnedListView.getCount() - 1)) {
                            leftListview.setSelection(ListView.FOCUS_DOWN);
                        }

                        // 判断滚动到顶部
                        if (pinnedListView.getFirstVisiblePosition() == 0) {
                            leftListview.setSelection(0);
                        }

                        break;
                }
            }

            int y = 0;
            int x = 0;
            int z = 0;

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (null == bList) {
                    return;
                }
                if (isScroll) {
                    for (int i = 0; i < bList.size(); i++) {
                        if (i == sectionedAdapter.getSectionForPosition(pinnedListView.getFirstVisiblePosition())) {
                            if (null == bList.get(i)) {
                                bList.add(true);

                            } else {
                                bList.remove(i);
                                bList.add(i,true);
                            }


                            x = i;
                        } else {
                            if (null == bList.get(i)) {
                                bList.add(false);

                            } else {
                                bList.remove(i);
                                bList.add(i,false);
                            }
                        }
                    }
                    if (x != y) {
                        adapter.notifyDataSetChanged();
                        y = x;
                        if (y == leftListview.getLastVisiblePosition()) {
//                            z = z + 3;
                            leftListview.setSelection(z);
                        }
                        if (x == leftListview.getFirstVisiblePosition()) {
//                            z = z - 1;
                            leftListview.setSelection(z);
                        }
                        if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                            leftListview.setSelection(ListView.FOCUS_DOWN);
                        }
                    }
                } else {
                    isScroll = true;
                }
            }
        });

    }

    public void getDLData(DLRespBean dl, LDRespBean ld)
    {

        Intent itt = new Intent();
        itt.putExtra("DL",dl);
        itt.putExtra("LD",ld);
        this.setResult(Activity.RESULT_OK, itt);
        finish();




    }


    @OnClick({R.id.back, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.search:

                if (!ButtonTools.isFastDoubleClick(1500)) {
                    if(!"".equals(dlmd.getText().toString())){
                        SearchDLReqBean dlBean = new SearchDLReqBean(
                                SharedPreferencesUtils.getString(SearchDLActivity.this, "SJBM", ""),
                                dlmd.getText().toString());
                        ((SearchDLPresenter) mPresenter).searchDL(dlBean);
                    }else{

                        ToastUtils.showToast(this,"请输入查询条件");
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void showLoadProgressDialog(String str) {
        showLoading(str);
    }

    @Override
    public void disDialog() {
        dissLoadDialog();
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(this, message);
    }

    /**
     * 返回道路
     *
     * @param bean
     */
    @Override
    public void backDL(List<DLRespBean> bean) {

        this.beanDL = bean;
        bList = new ArrayList<>();//组装选择中的状态
//组装选中的数据
        for (int i = 0; i < beanDL.size(); i++) {
            if (i == 0) {
                bList.add(true);

            } else {
                bList.add(false);
            }
        }
        adapter.setLeftDLList(beanDL, bList);
        adapter.notifyDataSetChanged();
        SearchLDReqBean ldBean = new SearchLDReqBean(
                SharedPreferencesUtils.getString(SearchDLActivity.this, "SJBM", ""),
                beanDL.get(0).getDldm());

        ((SearchDLPresenter) mPresenter).searchLD(ldBean, beanDL.get(0));
    }

    /**
     * 返回路段
     *
     * @param bean
     */
    @Override
    public void backLD(List<LDRespBean> bean, DLRespBean dl) {
        List<DLRespBean> dlBean = new ArrayList<>();
        dlBean.add(dl);

        sectionedAdapter.setDataList(dlBean, bean);
        sectionedAdapter.notifyDataSetChanged();


    }
}
