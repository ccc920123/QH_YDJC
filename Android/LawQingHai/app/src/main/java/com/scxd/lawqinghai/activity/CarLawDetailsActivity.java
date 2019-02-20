package com.scxd.lawqinghai.activity;

import android.content.Intent;
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
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.CarLawDetailsRspBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.CarLawDetailsPresenter;
import com.scxd.lawqinghai.mvp.view.CarLawDetailsView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.ZoomImageViewGlide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述: 机动车违法记录列表详情
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarLawDetailsActivity extends BaseActivity implements CarLawDetailsView {


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
    @BindView(R.id.xxlytable)
    TableRow xxlyTable;
    @BindView(R.id.sfzhtablerow)
    TableRow sfzhTablerow;
    @BindView(R.id.dabhandzjcxtable)
    TableRow dabhandzjcxTableRow;
    @BindView(R.id.lxdhtablerow)
    TableRow lxdhtablerow;
    @BindView(R.id.lxfstablerow)
    TableRow lxfsTableRow;
    @BindView(R.id.jscf)
    Button jscf;
    private String xh;
    private Map<String, String> syxzMap = null;
    private Map<String, String> hpzlMap = null;
    private Map<String, String> clflMap = null;
    private Map<String, String> cfzlMap = null;
    private Map<String, String> clytMap = null;
    private Map<String, String> jtfsMap = null;
    private List<String> zpList;

    private CarLawDetailsRspBean.DataBean bean;
    private String state;
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
        title.setText("机动车查询明细");

        xh = getIntent().getStringExtra("xh");
        state = getIntent().getStringExtra("state");
        jkbjText.setVisibility(View.INVISIBLE);
        zpList = new ArrayList<>();

        bindClick();//监听点击事件
        xxlyTable.setVisibility(View.GONE);//隐藏信息来源
        sfzhTablerow.setVisibility(View.GONE);
        dabhandzjcxTableRow.setVisibility(View.GONE);//隐藏档案编号和准驾车型
        //        lxdhtablerow.setVisibility(View.GONE);//隐藏联系电话
        lxfsTableRow.setVisibility(View.GONE);//隐藏联系方式
        //查询数据
        try {
            ((CarLawDetailsPresenter) mPresenter).loadDatas(this, xh, "1");
        } catch (Exception e) {
            ToastUtils.showToast(this, "系统错误，请重新加载");
            finish();
        }

    }

    @Override
    protected void initEventAndData() {
        try {
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
            for (DicationResBean bean : DictionaryManager.getInstance().getWslb()) {
                cfzlMap.put(bean.getCode(), bean.getName());
            }
            //车辆用途
            clytMap = new HashMap<>();
            for (DicationResBean bean : DictionaryManager.getInstance().getClyt()) {
                clytMap.put(bean.getCode(), bean.getName());
            }
            //交通方式
            jtfsMap = new HashMap<>();
            for (DicationResBean bean : DictionaryManager.getInstance().getCllx()) {
                jtfsMap.put(bean.getCode(), bean.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        wftp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    if (null == zpList || zpList.size() == 0) {
                        ToastUtils.showToast(CarLawDetailsActivity.this, "暂无违法照片");
                    } else {
                        showPopu(zpList, 0);
                    }
                }
            }
        });
        jscf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ButtonTools.isFastDoubleClick(1500)){
                    Intent intent = new Intent();
                    intent.setClass(CarLawDetailsActivity.this, SummaryPunishmentDetailActivity.class);
                    intent.putExtra("TAG", "CarLawDetailsActivity");
                    intent.putExtra("xh", xh);
                    intent.putExtra("CarLawBean", bean);
                    startActivity(intent);
                }
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
            this.bean = bean;
            wfsj.setText(bean.getWfsj());
            xzqh.setText(bean.getZsxzqh());
            dllx.setText(bean.getDldm());
            wfdd.setText(bean.getWfdd());
            wfdz.setText(bean.getWfdz());
            zqmj.setText(bean.getZqmj());
            fxjg.setText(bean.getCjjgmc());
            jtfs.setText("".equals(jtfsMap.get(bean.getJtfs())) ? bean.getJtfs() : jtfsMap.get(bean.getJtfs()));
            sglx.setText(bean.getSgly());
            if (null != bean.getXxly()) {
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
            }
            lrr.setText(bean.getLrr());
            lrsj.setText(bean.getLrsj());
            if (null != bean.getJsjqbj()) {
                switch (bean.getJsjqbj()) {
                    case "":
                        break;
                    default:
                        break;
                }
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
            boolean isJscf = false;
            String[] menuids = SharedPreferencesUtils.getString(this, "MENUIDS", "").split(",");
            for (String menu : menuids){
                if (menu.equals("102")){
                    isJscf = true;
                    break;
                }
            }
            if (state.equals("0") && isJscf){
                jscf.setVisibility(View.VISIBLE);
            } else {
                jscf.setVisibility(View.GONE);
            }
            cflb.setText(cfzlMap.get(bean.getJdslb()));
            cfbh.setText(bean.getJdsbh());
            if (null != bean.getZps()) {
                zpList.clear();
                zpList.addAll(bean.getZps());
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast(this, "加载失败，请重新请求");
        }
    }


    PopupWindow window = null;
    private int flag = 0;

    /**
     * 违法图片显示窗口
     *
     * @param datas
     * @param position
     */
    public void showPopu(final List<String> datas, final int position) {
        flag = position;
        View popupView = this.getLayoutInflater().inflate(R.layout.warn_back_photo, null);
        final ZoomImageViewGlide zoomImage = (ZoomImageViewGlide) popupView.findViewById(R.id.image);
        final TextView btn_syz = (TextView) popupView.findViewById(R.id.btn_syz);
        final TextView btn_cancle = (TextView) popupView.findViewById(R.id.btn_cancle);
        final TextView btn_xyz = (TextView) popupView.findViewById(R.id.btn_xyz);
        final TextView btn_deleted = (TextView) popupView.findViewById(R.id.btn_deleted);
        btn_deleted.setVisibility(View.GONE);
        //  加载图片
        Glide.with(this).load(datas.get(position)).into(zoomImage);
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
        // 上一张
        btn_syz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (flag == 0) {
                    ToastUtils.showToast(CarLawDetailsActivity.this, "当前已经是第一张");
                } else {
                    flag--;
                    Glide.with(CarLawDetailsActivity.this).load(datas.get(flag)).into(zoomImage);
                }
            }
        });
        //  下一张
        btn_xyz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (flag == datas.size() - 1) {
                    ToastUtils.showToast(CarLawDetailsActivity.this, "当前已经是最后一张");
                } else {
                    flag++;
                    Glide.with(CarLawDetailsActivity.this).load(datas.get(flag)).into(zoomImage);
                    //                    zoomImage.setImageBitmap(BitmapFactory.decodeFile(datas.get(flag)));
                }
            }

        });
    }
}
