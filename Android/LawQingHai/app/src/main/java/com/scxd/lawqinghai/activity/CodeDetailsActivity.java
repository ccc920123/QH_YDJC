package com.scxd.lawqinghai.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.CodeDetailsPresenter;
import com.scxd.lawqinghai.mvp.view.CodeDetailsView;
import com.scxd.lawqinghai.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述: 违法代码详情
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CodeDetailsActivity extends BaseActivity implements CodeDetailsView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.wfdm)
    TextView wfdm;
    @BindView(R.id.wfjf)
    TextView wfjf;
    @BindView(R.id.wfnr)
    TextView wfnr;
    @BindView(R.id.wfms)
    TextView wfms;
    @BindView(R.id.fkje)
    TextView fkje;
    @BindView(R.id.zkys)
    TextView zkys;
    @BindView(R.id.jlsj)
    TextView jlsj;
    @BindView(R.id.jgbj)
    TextView jgbj;
    @BindView(R.id.fkbj)
    TextView fkbj;
    @BindView(R.id.zkbj)
    TextView zkbj;
    @BindView(R.id.dxbj)
    TextView dxbj;
    @BindView(R.id.jlbj)
    TextView jlbj;
    @BindView(R.id.wfgd)
    TextView wfgd;
    @BindView(R.id.flyj)
    TextView flyj;

    private String wfdmIntent;

    @Override
    public BasePresenter getPresenter() {
        return new CodeDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_code_details;
    }

    @Override
    protected void initInjector() {
        wfdmIntent = getIntent().getStringExtra("WFDM");
        title.setText("违法代码详情");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initEventAndData() {
        try {
            ((CodeDetailsPresenter) mPresenter).loadCodeLaw(this, wfdmIntent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast(this, "加载失败，请重新加载");
            finish();
        }

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

    @Override
    public void showCodeDatas(QueryCodeListBean dataBean) {
        try {
            wfms.setText(dataBean.getWfms());
            wfdm.setText(dataBean.getWfdm());
            wfnr.setText("违法内容：" + dataBean.getWfnr());
            wfjf.setText("违法记分："+dataBean.getWfjf() + " 分 ");
            fkje.setText("罚款金额："+dataBean.getFkjemin()+"~"+dataBean.getFkjemax()+" 元 ");
            zkys.setText("暂扣月数："+dataBean.getZkysmin()+"~"+dataBean.getZkysmax());
            jlsj.setText("拘留天数："+dataBean.getJlsjmin()+"~"+dataBean.getJlsjmax());
            jgbj.setText("是否警告："+("0".equals(dataBean.getJgbj())?"否":"是"));
            fkbj.setText("是否罚款："+("0".equals(dataBean.getFkbj())?"否":"是"));
            zkbj.setText("是否暂扣："+("0".equals(dataBean.getZkbj())?"否":"是"));
            dxbj.setText("是否吊销："+("0".equals(dataBean.getDxbj())?"否":"是"));
            jlbj.setText("是否拘留："+("0".equals(dataBean.getJlbj())?"否":"是"));
            wfgd.setText("违法规定："+dataBean.getWfgd());
            flyj.setText("法律依据："+dataBean.getFlyj());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
