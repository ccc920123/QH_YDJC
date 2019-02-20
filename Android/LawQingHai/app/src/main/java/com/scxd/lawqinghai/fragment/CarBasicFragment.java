package com.scxd.lawqinghai.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.CarInformationActivity;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.QueryCarRepBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.CarBasicPresenter;
import com.scxd.lawqinghai.mvp.view.CarBasicView;
import com.scxd.lawqinghai.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述: 机动车查询基本信息
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarBasicFragment extends BaseFragment implements CarBasicView {


    @BindView(R.id.hpzl)
    TextView hpzl;
    @BindView(R.id.xm)
    TextView xm;
    @BindView(R.id.hphm)
    TextView hphm;
    @BindView(R.id.clpp)
    TextView clpp;
    @BindView(R.id.sfdqc)
    TextView sfdqc;
    @BindView(R.id.csys)
    TextView csys;
    @BindView(R.id.fzjg)
    TextView fzjg;
    @BindView(R.id.zsxzqh)
    TextView zsxzqh;
    @BindView(R.id.clzt)
    TextView clzt;
    @BindView(R.id.zdcllx)
    TextView zdcllx;
    @BindView(R.id.cllx)
    TextView cllx;
    @BindView(R.id.lxdh)
    TextView lxdh;
    @BindView(R.id.lxdz)
    TextView lxdz;
    @BindView(R.id.jyyxqz)
    TextView jyyxqz;
    @BindView(R.id.yjdcbgyy)
    TextView yjdcbgyy;
    @BindView(R.id.hpqyrq)
    TextView hpqyrq;
    @BindView(R.id.ccdjrq)
    TextView ccdjrq;
    @BindView(R.id.jdcsyr)
    TextView jdcsyr;
    @BindView(R.id.syxz)
    TextView syxz;
    @BindView(R.id.plgl)
    TextView plgl;
    @BindView(R.id.fdjh)
    TextView fdjh;
    @BindView(R.id.clsbdm)
    TextView clsbdm;
    @BindView(R.id.gcjk)
    TextView gcjk;
    @BindView(R.id.bxzzrq)
    TextView bxzzrq;
    @BindView(R.id.xszxph)
    TextView xszxph;
    @BindView(R.id.hgbzbh)
    TextView hgbzbh;
    @BindView(R.id.dybj)
    TextView dybj;
    @BindView(R.id.wfcs)
    TextView wfcs;
    @BindView(R.id.wkcc)
    TextView wkcc;
    @BindView(R.id.zbzl)
    TextView zbzl;
    @BindView(R.id.yqwjy)
    TextView yqwjy;
    @BindView(R.id.yqwbf)
    TextView yqwbf;
    @BindView(R.id.clxh)
    TextView clxh;
    @BindView(R.id.sfzh)
    TextView sfzh;
    @BindView(R.id.dhhm)
    TextView dhhm;
    @BindView(R.id.xszxbh)
    TextView xszxbh;

    private String hphmIntent, hpzlIntent;

    private Map<String, String> hpzlMap = null;
    private Map<String, String> csysMap = null;
    private Map<String, String> clztMap = null;
    private Map<String, String> syxzMap = null;
    private Map<String, String> cllxMap = null;

    @Override
    public BasePresenter getPresenter() {
        return new CarBasicPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_car_basic;
    }

    /**
     * 获取从机动车查询界面传递过来的查询条件内容
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hphmIntent = ((CarInformationActivity) context).getHphm();
        hpzlIntent = ((CarInformationActivity) context).getHpzl();
    }

    @Override
    protected void initInjector() {
        try {
            //号牌种类
            hpzlMap = new HashMap<>();
            for (DicationResBean bean : DictionaryManager.getInstance().getHpzl()) {
                hpzlMap.put(bean.getCode(), bean.getName());
            }
            //车身颜色
            csysMap = new HashMap<>();
            for (DicationResBean bean : DictionaryManager.getInstance().getCsys()) {
                csysMap.put(bean.getCode(), bean.getName());
            }
            //车辆状态
            clztMap = new HashMap<>();
            for (DicationResBean bean : DictionaryManager.getInstance().getClzt()) {
                clztMap.put(bean.getCode(), bean.getName());
            }
            //车辆状态
            syxzMap = new HashMap<>();
            for (DicationResBean bean : DictionaryManager.getInstance().getSyxz()) {
                syxzMap.put(bean.getCode(), bean.getName());
            }
            //车辆类型
            cllxMap = new HashMap<>();
            for (DicationResBean bean : DictionaryManager.getInstance().getCllx()) {
                cllxMap.put(bean.getCode(), bean.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void lazyLoadData() {

        //查询数据  请求加载
        ((CarBasicPresenter) mPresenter).loadCarBasic(MyApplication.appContext, new QueryCarRepBean(hpzlIntent, 
                hphmIntent));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 由于驾驶证状态存在多个值，所有依次解析驾驶证状态
     *
     * @param zt
     * @return
     */
    private String getclzt(String zt) {

        if (null != zt || !"".equals(zt)) {
            char[] zts = zt.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < zts.length; i++) {

                builder.append(clztMap.get(String.valueOf(zts[i])));
                if (i != zts.length - 1) {
                    builder.append(",");
                }

            }
            return builder.toString();


        }


        return "";
    }

    /**
     * 获取机动车信息
     *
     * @param dataBean
     */
    @Override
    public void showCarDatas(QueryCarBean.DataBean dataBean) {
        try {
            xm.setText(hpzlMap.get(dataBean.getHpzl()));
            hphm.setText(dataBean.getHphm());
            clpp.setText(dataBean.getClpp());
            switch (dataBean.getSfdqc()) {
                case "0":
                    sfdqc.setText("否");
                    break;
                case "1":
                    sfdqc.setText("是");
                    break;
                default:
                    break;
            }
            csys.setText(csysMap.get(dataBean.getCsys()));
            fzjg.setText(dataBean.getFzjg());
            zsxzqh.setText(dataBean.getZsxzqh());
            if (!"A".equals(dataBean.getClzt())) {
                clzt.setTextColor(Color.RED);
                clzt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            }
            clzt.setText(getclzt(dataBean.getClzt()));
            jyyxqz.setText(dataBean.getJyyxqz());
            yjdcbgyy.setText(dataBean.getYjdcbgyy());
            hpqyrq.setText(dataBean.getHpqyrq());
            ccdjrq.setText(dataBean.getCcdjrq());
            jdcsyr.setText(dataBean.getJdcsyr());
            syxz.setText(syxzMap.get(dataBean.getSyxz()));
            clsbdm.setText(dataBean.getClsbdh());
            gcjk.setText(dataBean.getGcjk());
            bxzzrq.setText(dataBean.getBxzzrq());
            hgbzbh.setText(dataBean.getHgbzbh());
            fdjh.setText(dataBean.getFdjh());
            xszxph.setText(dataBean.getXszbh());
            plgl.setText(dataBean.getPl() + "/" + dataBean.getGl());
            switch (dataBean.getDybj()) {
                case "0":
                    dybj.setText("未抵押");
                    break;
                case "1":
                    dybj.setText("已抵押");
                    break;
                default:
                    dybj.setText("");
                    break;
            }
            wkcc.setText("长：" + dataBean.getWkc() + "mm 宽：" + dataBean.getWkk() + "mm 高：" + dataBean.getWkg() + "mm");
            zbzl.setText(dataBean.getZbzl());
            zdcllx.setText(cllxMap.get(dataBean.getZdcllx()));
            cllx.setText(cllxMap.get(dataBean.getCllx()));
            wfcs.setText(dataBean.getWfcs());
            yqwjy.setText(dataBean.getYqwjy());
            yqwbf.setText(dataBean.getYqwbf());
            lxdh.setText(dataBean.getLxdh());
            lxdz.setText(dataBean.getLxdz());
            clxh.setText(dataBean.getClxh());
            sfzh.setText(dataBean.getSfzmhm());
            dhhm.setText(dataBean.getLxdh());
            xszxbh.setText(dataBean.getXszbh());
        } catch (Exception e) {
            e.printStackTrace();
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
        ToastUtils.showToast(getActivity(), message);
    }

}
