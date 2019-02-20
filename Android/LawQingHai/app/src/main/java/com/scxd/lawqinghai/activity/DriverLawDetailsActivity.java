package com.scxd.lawqinghai.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.CarLawDetailsRspBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.CarLawDetailsPresenter;
import com.scxd.lawqinghai.mvp.view.CarLawDetailsView;
import com.scxd.lawqinghai.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述: 驾驶人违法记录列表详情
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class DriverLawDetailsActivity extends BaseActivity implements CarLawDetailsView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.wftp)
    Button wftp;
    @BindView(R.id.wfsj)
    TextView wfsj;
    @BindView(R.id.xzqh)
    TextView xzqh;
    @BindView(R.id.dllx)
    TextView dllx;
    @BindView(R.id.wfdd)
    TextView wfdd;
    @BindView(R.id.wfdz)
    TextView wfdz;
    @BindView(R.id.zqmj)
    TextView zqmj;
    @BindView(R.id.fxjg)
    TextView fxjg;
    @BindView(R.id.jtfs)
    TextView jtfs;
    @BindView(R.id.sglx)
    TextView sglx;
    @BindView(R.id.xxly)
    TextView xxly;
    @BindView(R.id.lrr)
    TextView lrr;
    @BindView(R.id.lrsj)
    TextView lrsj;
    @BindView(R.id.jsjq)
    TextView jsjq;
    @BindView(R.id.sfzh)
    TextView sfzh;
    @BindView(R.id.fzjg)
    TextView fzjg;
    @BindView(R.id.dabh)
    TextView dabh;
    @BindView(R.id.dsr)
    TextView dsr;
    @BindView(R.id.zjcx)
    TextView zjcx;
    @BindView(R.id.lxdh)
    TextView lxdh;
    @BindView(R.id.zzxq)
    TextView zzxq;
    @BindView(R.id.syxz)
    TextView syxz;
    @BindView(R.id.hpzl)
    TextView hpzl;
    @BindView(R.id.hphm)
    TextView hphm;
    @BindView(R.id.syr)
    TextView syr;
    @BindView(R.id.lxfs)
    TextView lxfs;
    @BindView(R.id.zsdz)
    TextView zsdz;
    @BindView(R.id.clfl)
    TextView clfl;
    @BindView(R.id.clyt)
    TextView clyt;
    @BindView(R.id.cfzl)
    TextView cfzl;
    @BindView(R.id.wfdm)
    TextView wfdm;
    @BindView(R.id.wfnr)
    TextView wfnr;
    @BindView(R.id.wfjf)
    TextView wfjf;
    @BindView(R.id.fkje)
    TextView fkje;
    @BindView(R.id.zkys)
    TextView zkys;
    @BindView(R.id.jlts)
    TextView jlts;
    @BindView(R.id.jbr)
    TextView jbr;
    @BindView(R.id.cflb)
    TextView cflb;
    @BindView(R.id.cfbh)
    TextView cfbh;
    @BindView(R.id.clsj)
    TextView clsj;
    @BindView(R.id.cjjg)
    TextView cjjg;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.jkbj)
    TextView jkbj;
    @BindView(R.id.jkbj_text)
    TextView jkbjText;
    @BindView(R.id.fxjgrow)
    TableRow fxjgRow;
    @BindView(R.id.clytrow)
    TableRow clytRow;
    @BindView(R.id.jscf)
    Button jscf;

    private String xh;
    private Map<String, String> syxzMap = null;
    private Map<String, String> hpzlMap = null;
    private Map<String, String> clflMap = null;
    private Map<String, String> cfzlMap = null;
    private Map<String, String> clytMap = null;
    private Map<String, String> jkbjMap = null;
    private Map<String, String> jtfsMap = null;

    @Override
    public BasePresenter getPresenter() {
        return new CarLawDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_law_detail;
    }

    @Override
    protected void initInjector() {
        title.setText("驾驶人查询明细");

        xh = getIntent().getStringExtra("xh");

        wftp.setVisibility(View.INVISIBLE);
        fxjgRow.setVisibility(View.GONE);
        clytRow.setVisibility(View.GONE);
        jscf.setVisibility(View.GONE);
        bindClick();//监听点击事件

        //查询数据
        try {
            ((CarLawDetailsPresenter) mPresenter).loadDatas(this, xh, "0");
        } catch (Exception e) {
            ToastUtils.showToast(this, "系统错误，请重新加载");
            finish();
        }

    }

    @Override
    protected void initEventAndData() {
        //使用性质
        syxzMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getSyxz()) {
            syxzMap.put(bean.getCode(), bean.getName());
        }
        //号牌种类
        hpzlMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getHpzl()) {
            hpzlMap.put(bean.getCode(), bean.getName());
        }
        //车辆分类
        clflMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getClfl()) {
            clflMap.put(bean.getCode(), bean.getName());
        }
        //处罚种类
        cfzlMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getCfzl()) {
            cfzlMap.put(bean.getCode(), bean.getName());
        }
        //车辆用途
        clytMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getClyt()) {
            clytMap.put(bean.getCode(), bean.getName());
        }
        //缴款标记
        jkbjMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getJkbj()) {
            jkbjMap.put(bean.getCode(), bean.getName());
        }
        //交通方式
        jtfsMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getCllx()) {
            jtfsMap.put(bean.getCode(), bean.getName());
        }
    }


    /**
     * 点击事件
     */
    public void bindClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
    public void showDatas(CarLawDetailsRspBean.DataBean bean) {
        try {
            wfsj.setText(bean.getWfsj());
            xzqh.setText(bean.getZsxzqh());
            dllx.setText(bean.getDldm());
            wfdd.setText(bean.getWfdd());
            wfdz.setText(bean.getWfdz());
            zqmj.setText(bean.getZqmj());
            fxjg.setText(bean.getCjjgmc());
            jtfs.setText("".equals(jtfsMap.get(bean.getJtfs())) ? bean.getJtfs() : jtfsMap.get(bean.getJtfs()));
            sglx.setText(bean.getSgly());
            switch (bean.getXxly()) {
                case "1":
                    xxly.setText("现场处罚");
                    break;
                case "2":
                    xxly.setText("非现场处罚");
                    break;
                default:
                    break;
            }
            lrr.setText(bean.getLrr());
            lrsj.setText(bean.getLrsj());
            switch (bean.getJsjqbj()) {
                case "":
                    break;
                default:
                    break;
            }
            sfzh.setText(bean.getJszh());
            fzjg.setText(bean.getFzjg());
            dabh.setText(bean.getDabh());
            dsr.setText(bean.getDsr());
            zjcx.setText(bean.getZjcx());
            lxdh.setText(bean.getDh());
            zzxq.setText(bean.getZsxzqh());
            syxz.setText(syxzMap.get(bean.getSyxz()));
            hpzl.setText(hpzlMap.get(bean.getHpzl()));
            hphm.setText(bean.getHphm());
            lxfs.setText(bean.getLxfs());
            zsdz.setText(bean.getZsxxdz());
            clfl.setText(clflMap.get(bean.getClfl().trim()));
            cfzl.setText(cfzlMap.get(bean.getCfzl().trim()));
            wfjf.setText(bean.getWfjfs());
            fkje.setText(bean.getFkje());
            jbr.setText(bean.getJbr());
            clsj.setText(bean.getClsj());
            cjjg.setText(bean.getCljg());
            dllx.setText(bean.getDldm());
            sglx.setText(bean.getSgly());
            syr.setText(bean.getSyr());
            clyt.setText(clytMap.get(bean.getClyt()));
            wfdm.setText(bean.getWfdm());
            wfnr.setText(bean.getWfmc());
            zkys.setText(bean.getZkys());
            jlts.setText(bean.getJlts());
            cflb.setText(cfzlMap.get(bean.getJdslb()));
            cfbh.setText(bean.getJdsbh());
            jkbj.setText(jkbjMap.get(bean.getJkbj()));

        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast(this, "加载失败，请重新请求");
        }
    }

}
