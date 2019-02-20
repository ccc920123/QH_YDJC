package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.WarnSignReqBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.bean.response.WarnDetailsRspBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.WarnDetailsPresenter;
import com.scxd.lawqinghai.mvp.view.WarnDetailsView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.ZoomImageViewGlide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 是否出警有改变，综合平台，1：是，0：否
 *
 * @类名: ${type_name}
 * @功能描述: 预警签收详情
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnDetailsActivity extends BaseActivity implements WarnDetailsView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.picture)
    ImageView picture;
    @BindView(R.id.hpzl)
    TextView hpzl;
    @BindView(R.id.hphm_yjxx)
    TextView hphmYjxx;
    @BindView(R.id.yjlx)
    TextView yjlx;
    @BindView(R.id.yjzlx)
    TextView yjzlx;
    @BindView(R.id.jyqz)
    TextView jyqz;
    @BindView(R.id.jyqztab)
    TableRow jyqztab;
    @BindView(R.id.gcsj)
    TextView gcsj;
    @BindView(R.id.cllxys)
    TextView cllxys;
    @BindView(R.id.yjsj)
    TextView yjsj;
    @BindView(R.id.syxz_yjxx)
    TextView syxzYjxx;
    @BindView(R.id.sxqy)
    TextView sxqy;
    @BindView(R.id.xsdd)
    TextView xsdd;
    @BindView(R.id.xssd)
    TextView xssd;
    @BindView(R.id.sbzl)
    TextView sbzl;
    @BindView(R.id.sbmc)
    TextView sbmc;
    @BindView(R.id.xm_hpzl)
    TextView xmHpzl;
    @BindView(R.id.xm)
    TextView xm;
    @BindView(R.id.hphm)
    TextView hphm;
    @BindView(R.id.clpp)
    TextView clpp;
    @BindView(R.id.sfdqc)
    TextView sfdqc;
    @BindView(R.id.syxz)
    TextView syxz;
    @BindView(R.id.plgl)
    TextView plgl;
    @BindView(R.id.fdjh)
    TextView fdjh;
    @BindView(R.id.fzjg)
    TextView fzjg;
    @BindView(R.id.clsbdh)
    TextView clsbdh;
    @BindView(R.id.zsxzqh)
    TextView zsxzqh;
    @BindView(R.id.clzt)
    TextView clzt;
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
    @BindView(R.id.jszh)
    TextView jszh;
    @BindView(R.id.jszh_tabrow)
    TableRow jszhTabrow;
    @BindView(R.id.jszzt)
    TextView jszzt;
    @BindView(R.id.jszzt_tabrow)
    TableRow jszztTabrow;
    @BindView(R.id.jszdabh)
    TextView jszdabh;
    @BindView(R.id.jszdabh_tabrow)
    TableRow jszdabhTabrow;
    @BindView(R.id.jszzxbh)
    TextView jszzxbh;
    @BindView(R.id.jszzxbh_tabrow)
    TableRow jszzxbhTabrow;
    @BindView(R.id.zjcx)
    TextView zjcx;
    @BindView(R.id.ljjf)
    TextView ljjf;
    @BindView(R.id.zjcx_tabrow)
    TableRow zjcxTabrow;
    @BindView(R.id.cclzrq)
    TextView cclzrq;
    @BindView(R.id.jzqx)
    TextView jzqx;
    @BindView(R.id.cclzrq_tabrow)
    TableRow cclzrqTabrow;
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
    @BindView(R.id.wkcc)
    TextView wkcc;
    @BindView(R.id.youxiao)
    RadioButton youxiao;
    @BindView(R.id.wuxiao)
    RadioButton wuxiao;
    @BindView(R.id.qsjg)
    RadioGroup qsjg;
    @BindView(R.id.shi)
    RadioButton shi;
    @BindView(R.id.fou)
    RadioButton fou;
    @BindView(R.id.sfcj)
    RadioGroup sfcj;
    @BindView(R.id.yjqs_btn)
    Button yjqsBtn;
    @BindView(R.id.main)
    LinearLayout main;
    private String yjxhJPush = "";

    private String qsjgStr = "1";
    private String sfczStr = "1";
    private Map<String, String> yjlxMap = null;
    private Map<String, String> yjzlxMap = null;
    private Map<String, String> hpzlMap = null;
    private Map<String, String> cllxMap = null;
    private Map<String, String> syxzMap = null;
    private Map<String, String> jszztMap = null;
    private Map<String, String> csysMap = null;
    private Map<String, String> clztMap = null;
    private List<DicationResBean> clztList = null;

    private int qsztFlag = 0;
    private String hpzlIntent;
    private WarnDetailsRspBean.DataBean warnBean;


    @Override
    public BasePresenter getPresenter() {
        return new WarnDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_warn_details;
    }

    @Override
    protected void initInjector() {
        title.setText(R.string.yjxq);
        xmHpzl.setText(R.string.hpzl);
        yjxhJPush = getIntent().getStringExtra("YJXH");
        if (null != yjxhJPush && !yjxhJPush.equals("")) {
            Common.YJXH = yjxhJPush;
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        qsjg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.youxiao) {
                    qsjgStr = "1";
                    shi.setChecked(true);//出警
                    shi.setEnabled(true);
                    fou.setEnabled(true);
                } else if (checkedId == R.id.wuxiao) {
                    qsjgStr = "2";
                    fou.setChecked(true);//不出警
                    shi.setEnabled(false);
                    fou.setEnabled(false);
                }
            }
        });
        qsjg.check(R.id.youxiao);
        sfcj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.shi) {
                    sfczStr = "1";
                } else if (checkedId == R.id.fou) {
                    sfczStr = "0";
                }
            }
        });
        sfcj.check(R.id.shi);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(urlStr)) {
                    showPopuPicture(urlStr);
                } else {
                    ToastUtils.showToast(WarnDetailsActivity.this, "暂无该照片信息");
                }
            }
        });
        yjqsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    if (qsztFlag == 1 || qsztFlag == 2) {
                        //跳转到预警反馈界面
                        Intent intent = new Intent(WarnDetailsActivity.this, WarnBackActivity.class);
                        intent.putExtra("HPHM", hphmYjxx.getText().toString());
                        intent.putExtra("HPZL", hpzlIntent);
                        intent.putExtra("QSZT", qsztFlag);
                        intent.putExtra("YJLX", warnBean.getYjlx());
                        intent.putExtra("YJZLX", warnBean.getYjzlx());
                        startActivity(intent);
                        finish();
                    } else {
                        //预警签收
                        if (qsjgStr.equals("") || sfczStr.equals("")) {
                            ToastUtils.showToast(WarnDetailsActivity.this, R.string.SYSTEM_TIPS_SING);
                            return;
                        }
                        //通过签收是否有效，1有效，2无效，当签收结果2无效时，是的出警为0,不出警
                        WarnSignReqBean bean = new WarnSignReqBean(SharedPreferencesUtils.getString
                                (WarnDetailsActivity.this, "USER", ""), Common.YJXH, qsjgStr, "2".equals(qsjgStr) ? 
                                "0" : sfczStr, SharedPreferencesUtils.getString(WarnDetailsActivity.this, "SSBMBH", 
                                ""));

                        ((WarnDetailsPresenter) mPresenter).commitDatas(WarnDetailsActivity.this, bean);
                    }
                }
            }
        });
    }

    @Override
    protected void initEventAndData() {
        yjlxMap = new HashMap<>();
        yjzlxMap = new HashMap<>();
        hpzlMap = new HashMap<>();
        cllxMap = new HashMap<>();
        syxzMap = new HashMap<>();
        jszztMap = new HashMap<>();
        csysMap = new HashMap<>();
        clztMap = new HashMap<>();
        clztList = new ArrayList<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getYjlx()) {
            yjlxMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getYjzlx()) {
            yjzlxMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getHpzl()) {
            hpzlMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getCllx()) {
            cllxMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getSyxz()) {
            syxzMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getJszzt()) {
            jszztMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getCsys()) {
            csysMap.put(bean.getCode(), bean.getName());
        }

        clztList = DictionaryManager.getInstance().getClzt();
        for (DicationResBean bean : clztList) {
            clztMap.put(bean.getCode(), bean.getName());
        }

        ((WarnDetailsPresenter) mPresenter).load(this, Common.YJXH);
    }

    @Override
    public void onBackPressed() {
        if (null != yjxhJPush && !"".equals(yjxhJPush)) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, WarnTabActivity.class);
            startActivity(intent);
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
     * 由于驾驶证状态存在多个值，所有依次解析驾驶证状态
     *
     * @param zt
     * @return
     */
    private String getJszzt(String zt) {

        if (null != zt || !"".equals(zt)) {
            char[] zts = zt.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < zts.length; i++) {

                builder.append(jszztMap.get(String.valueOf(zts[i])));
                if (i != zts.length - 1) {
                    builder.append(",");
                }

            }
            return builder.toString();


        }


        return "";
    }

    private String urlStr = "";

    @Override
    public void showDatas(WarnDetailsRspBean.DataBean bean) {
        warnBean = bean;
        if (null != bean.getZpid() && bean.getZpid().size() > 0) {
            urlStr = bean.getZpid().get(0);
            Glide.with(this).load(urlStr).into(picture);
        }
        hpzl.setText(hpzlMap.get(bean.getHpzl()));
        hphmYjxx.setText(bean.getHphm());
        if ("04".equals(bean.getYjlx())) {
            jyqztab.setVisibility(View.VISIBLE);
            jyqz.setText(bean.getJszyxqz());
        } else {
            jyqztab.setVisibility(View.GONE);
        }
        yjlx.setText(yjlxMap.get(bean.getYjlx()));
        if (bean.getYjlx().equals("34")) {
            cclzrqTabrow.setVisibility(View.VISIBLE);
            jszdabhTabrow.setVisibility(View.VISIBLE);
            jszhTabrow.setVisibility(View.VISIBLE);
            jszzxbhTabrow.setVisibility(View.VISIBLE);
            zjcxTabrow.setVisibility(View.VISIBLE);
            jszh.setText(bean.getJszh());
            jszdabh.setText(bean.getDabh());
            jszzxbh.setText(bean.getJszyxqz());
            zjcx.setText(bean.getZjcx());
            if (null == bean.getLjjf() || "".equals(bean.getLjjf())){
                ljjf.setText("0");
            } else {
                ljjf.setText(bean.getLjjf());
                if (Integer.valueOf(bean.getLjjf()) > 0){
                    ljjf.setTextColor(Color.RED);
                }
            }
            cclzrq.setText(bean.getCclzrq());
            jzqx.setText(bean.getJszyxqz());
            if (null != bean.getJszyxqz() || !"".equals(bean.getJszyxqz())) {
                if (Date_U.compareDate(bean.getJszyxqz(), Date_U.getCurrentTime2())) {
                    jzqx.setTextColor(Color.RED);
                }
            }
        } else {
            cclzrqTabrow.setVisibility(View.GONE);
            jszdabhTabrow.setVisibility(View.GONE);
            jszhTabrow.setVisibility(View.GONE);
            jszzxbhTabrow.setVisibility(View.GONE);
            zjcxTabrow.setVisibility(View.GONE);
        }

        yjzlx.setText(yjzlxMap.get(bean.getYjzlx()));
        yjsj.setText(bean.getYjsj());
        cllxys.setText(cllxMap.get(bean.getCllx()));
        gcsj.setText(bean.getGcsj());
        xssd.setText(bean.getXssd());
        sxqy.setText(bean.getXsqy());
        xsdd.setText(bean.getXssd());
        syxzYjxx.setText(syxzMap.get(bean.getSyxz()));
        sbzl.setText(bean.getSbzl());
        sbmc.setText(bean.getKkmc());//显示的卡口名称
        if (null != bean.getJszzt())
            jszzt.setText(getJszzt(bean.getJszzt()));
        if (bean.getQsjg() != null) {
            switch (bean.getQsjg()) {
                case "1":
                    qsjg.check(R.id.youxiao);
                    break;
                case "2":
                    qsjg.check(R.id.wuxiao);
                    break;
                default:
                    qsjg.clearCheck();
                    break;
            }
        }
        qsztFlag = Integer.parseInt(bean.getQszt());
        if (bean.getSfcj() != null) {
            if (qsztFlag != 0) {//当签收状态是0表示未签收，不判断是否显示按钮
                switch (bean.getSfcj()) {
                    case "1":
                        sfcj.check(R.id.shi);
                        yjqsBtn.setVisibility(View.VISIBLE);
                        break;
                    case "0":
                        sfcj.check(R.id.fou);
                        yjqsBtn.setVisibility(View.GONE);
                        break;
                    default:
                        sfcj.clearCheck();
                        break;
                }
            }
        }

        switch (Integer.parseInt(bean.getQszt())) {
            case 1:
            case 2:
                yjqsBtn.setText("处置情况");
                yjqsBtn.setBackgroundResource(R.drawable.login_btn_gray);
                break;
            default:
                yjqsBtn.setText(R.string.yjqs);
                break;
        }
        hpzlIntent = bean.getHpzl();


        //请求查询车辆基本信息
        ((WarnDetailsPresenter) mPresenter).loadCar(this, bean.getHpzl(), bean.getHphm());
    }

    @Override
    public void showCarDatas(QueryCarBean.DataBean bean) {
        xm.setText(hpzlMap.get(bean.getHpzl()));
        hphm.setText(bean.getHphm());
        clpp.setText(bean.getClpp());
        sfdqc.setText(bean.getSfdqc());
        //        csys.setText(csysMap.get(bean.getCsys()));
        fzjg.setText(bean.getFzjg());
        zsxzqh.setText(bean.getZsxzqh());

        for (DicationResBean clztBean : clztList) {
            if (bean.getClzt().equals(clztBean.getCode())) {
                clzt.setText(clztBean.getName());
                if (bean.getClzt().equals("A")) {
                    clzt.setTextColor(Color.RED);
                } else {
                    clzt.setTextColor(Color.BLACK);
                }
            }
        }
        clzt.setText(getclzt(bean.getClzt()));
        jyyxqz.setText(bean.getJyyxqz());
        jyqz.setText(bean.getJyyxqz());
        yjdcbgyy.setText(bean.getYjdcbgyy());
        hpqyrq.setText(bean.getHpqyrq());
        ccdjrq.setText(bean.getCcdjrq());
        jdcsyr.setText(bean.getJdcsyr());
        syxz.setText(syxzMap.get(bean.getSyxz()));
        clsbdh.setText(bean.getClsbdh());
        gcjk.setText(bean.getGcjk());
        bxzzrq.setText(bean.getBxzzrq());
        hgbzbh.setText(bean.getHgbzbh());
        fdjh.setText(bean.getFdjh());
        xszxph.setText(bean.getXszbh());
        plgl.setText(bean.getPl());
        dybj.setText(bean.getDybj());
        wkcc.setText(bean.getWkc() + " mm(长) " + bean.getWkk() + " mm(宽) " + bean.getWkg() + " mm(高)");
    }

    @Override
    public void commitDatas(String message) {

        if ("0".equals(sfczStr)) {
            onBackPressed();
        } else {
            Intent intent = new Intent(WarnDetailsActivity.this, WarnBackActivity.class);
            intent.putExtra("HPHM", hphmYjxx.getText().toString());
            intent.putExtra("HPZL", hpzlIntent);
            intent.putExtra("QSZT", qsztFlag);
            intent.putExtra("YJLX", warnBean.getYjlx());
            intent.putExtra("YJZLX", warnBean.getYjzlx());
            startActivity(intent);
            finish();
        }
        ToastUtils.showToast(this, message);

    }

    @Override
    public void Failed(String message) {
        ToastUtils.showToast(this, message);
    }

    /**
     * 显示图片弹窗
     */
    PopupWindow window = null;

    private void showPopuPicture(String url) {

        View popupView = this.getLayoutInflater().inflate(R.layout.warn_photo_single, null);
        final ZoomImageViewGlide zoomImage = (ZoomImageViewGlide) popupView.findViewById(R.id.image);
        TextView btn_cancle = (TextView) popupView.findViewById(R.id.btn_cancle);
        Glide.with(this).load(url).into(zoomImage);
        window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.update();
        window.showAtLocation(main, Gravity.CENTER, 0, 0);
        window.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 如果点击了popupwindow的外部，popupwindow也会消失
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    window.dismiss();
                    return true;
                }
                return false;
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                window.dismiss();
            }
        });

    }

}
