package com.scxd.lawqinghai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.parkingwang.vehiclekeyboard.PopupKeyboard;
import com.parkingwang.vehiclekeyboard.ScenClick;
import com.parkingwang.vehiclekeyboard.view.InputView;
import com.scxd.idcardlibs.DriverScanActivity;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.QzcslxAdapter;
import com.scxd.lawqinghai.adapter.SjxmAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.WarnBackCommon;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.LedgerDriverBean;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.request.NotificationOfCompulsoryMeasuresDetailReqBan;
import com.scxd.lawqinghai.bean.request.NotificationOfCompulsoryMeasuresListReqBean;
import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.bean.request.WSBHReqBean;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.bean.response.DLRespBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.LDRespBean;
import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresDetailResBean;
import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresListResBean;
import com.scxd.lawqinghai.bean.response.PrintImageResBean;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.manager.AppManager;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.NotificationOfCompulsoryMeasuresDetailPersenter;
import com.scxd.lawqinghai.mvp.view.NotificationOfCompulsoryMeasuresDetailView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.DrawableTextView;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.SpinnerValue;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.CoustomCheckTimePickerView;
import com.scxd.lawqinghai.widget.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述：强制决定书详细界面
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class NotificationOfCompulsoryMeasuresDetailActivity extends BaseActivity implements
        NotificationOfCompulsoryMeasuresDetailView, ScenClick {
    @BindView(R.id.wsbh)
    EditText wsbh;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.hpzl)
    Spinner hpzl;
    @BindView(R.id.hphm)
    InputView hphm;
    @BindView(R.id.query_car)
    Button queryCar;
    @BindView(R.id.clfl)
    Spinner clfl;
    @BindView(R.id.ryfl)
    Spinner ryfl;
    @BindView(R.id.cllx)
    TextView cllx;
    @BindView(R.id.syxz)
    TextView syxz;
    @BindView(R.id.csys)
    TextView csys;
    @BindView(R.id.zjhm)
    EditText zjhm;
    @BindView(R.id.sfzmhm_scan)
    ImageView sfzmhm_scan;
    @BindView(R.id.query_driver)
    Button queryDriver;
    @BindView(R.id.dsr)
    TextView dsr;
    @BindView(R.id.fzjg)
    TextView fzjg;
    @BindView(R.id.zjcx)
    TextView zjcx;
    @BindView(R.id.dabh)
    TextView dabh;
    @BindView(R.id.ljjf)
    TextView ljjf;
    @BindView(R.id.fdjh)
    TextView fdjh;
    @BindView(R.id.clsbdh)
    TextView clsbdh;
    @BindView(R.id.jszzt)
    EditText jszzt;
    @BindView(R.id.lxdh)
    EditText lxdh;
    @BindView(R.id.lxdz)
    EditText lxdz;
    @BindView(R.id.lxfs)
    EditText lxfs;
    @BindView(R.id.xzqh)
    Spinner xzqh;
    @BindView(R.id.zsxzqh)
    EditText zsxzqh;
    @BindView(R.id.zsxxdz)
    EditText zsxxdz;
    @BindView(R.id.jdcsyr)
    EditText jdcsyr;
    @BindView(R.id.dldm)
    TextView dldm;
    @BindView(R.id.gls)
    EditText gls;
    @BindView(R.id.ms)
    EditText ms;
    @BindView(R.id.wfdd)
    TextView wfdd;
    @BindView(R.id.wfdz)
    EditText wfdz;
    @BindView(R.id.wfxw1)
    EditText wfxw1;
    @BindView(R.id.scz1)
    EditText scz1;
    @BindView(R.id.bzz1)
    EditText bzz1;
    @BindView(R.id.wfdm)
    EditText wfdm;
    @BindView(R.id.wfsj)
    TextView wfsj;
    @BindView(R.id.wfnr)
    EditText wfnr;
    @BindView(R.id.flyj)
    TextView flyj;
    @BindView(R.id.zqmj)
    EditText zqmj;
    //    @BindView(R.id.fxjg)
    //    EditText fxjg;
    @BindView(R.id.jsjq)
    Spinner jsjq;
    @BindView(R.id.dsryy)
    EditText dsryy;
    @BindView(R.id.bz)
    EditText bz;
    @BindView(R.id.mjyj)
    EditText mjyj;
    @BindView(R.id.qzcslx)
    MyListView qzcslx;
    @BindView(R.id.klwpcfd)
    EditText klwpcfd;
    @BindView(R.id.sjxm)
    MyListView sjxm;
    @BindView(R.id.sjwpcfd)
    EditText sjwpcfd;
    @BindView(R.id.tj_btn)
    Button tjBtn;
    @BindView(R.id.query_wfdm)
    Button queryWfdm;
    @BindView(R.id.sjwpcfdrow)
    TableRow sjwpcfdRow;
    @BindView(R.id.sjxmrow)
    TableRow sjxmRow;
    @BindView(R.id.klwpcfdrow)
    TableRow klwpcfdRow;
    @BindView(R.id.addwfxw)
    TextView addWfxw;
    @BindView(R.id.tablewfxw)
    TableLayout tablewfxw;
    @BindView(R.id.xzssjg)
    EditText xzssjg;

    private final int HPHM_SCAN_REQUEST_CODE = 200;
    @BindView(R.id.driver)
    TableLayout driver;
    @BindView(R.id.dsr_edit)
    EditText dsrEdit;
    @BindView(R.id.driver_edit)
    TableLayout driverEdit;
    @BindView(R.id.jszzt_tablerow)
    TableRow jszztTablerow;
    @BindView(R.id.hpzl_linear)
    LinearLayout hpzlLinear;
    @BindView(R.id.cllx_tabrow)
    TableRow cllxTabrow;
    @BindView(R.id.syxz_ys_tabrow)
    TableRow syxzYsTabrow;
    @BindView(R.id.fdjh_tabrow)
    TableRow fdjhTabrow;
    @BindView(R.id.clsbdh_tabrow)
    TableRow clsbdhTabrow;
    @BindView(R.id.jdcsyr_tabrow)
    TableRow jdcsyrTabrow;

    private int MY_SCAN_REQUEST_CODE = 300;
    private int MY_SEARCH_CODE = 400;
    private Map<String, String> cllxMap;
    private Map<String, String> syxzMap;
    private Map<String, String> jszztMap;
    private Map<String, String> csysMap;
    private Map<String, String> clflMap;


    private String tag;
    private String xh;
    private PopupKeyboard mPopupKeyboard;
    private String user;
    private String bmbh;
    private String cllxStr;
    private String syxzStr;
    private String csysStr;
    private List<DicationResBean> hpzlList;
    private List<DicationResBean> clflList;//车辆分类
    private List<DicationResBean> ryflList;//人员分类
    private List<DicationResBean> jszztList;//驾驶状太
    private List<DicationResBean> wslbList;//文书类别
    private List<DicationResBean> qzlxList;//强制类型
    private List<DicationResBean> sjxmList;//收缴项目
    List<DicationResBean> jsjqbjList;
    private SjxmAdapter sjxmAdapter;
    private QzcslxAdapter qzcslxAdapter;
    NotificationOfCompulsoryMeasuresDetailReqBan bean;

    private WarnBackCommon mWarnBackCommon;
    List<DicationResBean> xzqhList;
    /**
     * 道路代码/路段代码/行政区划
     */
    private String dldmstr;
    private String lddmstr;

    private String jszztStr = "A";

    @Override
    public BasePresenter getPresenter() {
        return new NotificationOfCompulsoryMeasuresDetailPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qzcstzs_details;
    }

    @Override
    protected void initInjector() {
        title.setText("强制措施凭证");
        tag = getIntent().getStringExtra("TAG");
        if ("WarnBackDetails".equals(tag)) {
            mWarnBackCommon = new WarnBackCommon();
            mWarnBackCommon = (WarnBackCommon) getIntent().getSerializableExtra("BACK");
            if (null != mWarnBackCommon) {
                xh = mWarnBackCommon.getYjxh();
            }
        } else {
            xh = getIntent().getStringExtra("XH");
        }
        user = SharedPreferencesUtils.getString(this, "USER", "");
        bmbh = SharedPreferencesUtils.getString(this, "SSBMBH", "");

        clflList = DictionaryManager.getInstance().getClfl();
        ryflList = DictionaryManager.getInstance().getRyfl();
        hpzlList = DictionaryManager.getInstance().getHpzl();
        jszztList = DictionaryManager.getInstance().getJszzt();
        jsjqbjList = DictionaryManager.getInstance().getJsjq();
        wslbList = new ArrayList<>();
        wslbList.addAll(DictionaryManager.getInstance().getWslb());

        //将简易处罚决定书这个选项移除
        for (int i = 0; i < wslbList.size(); i++) {
            if ("1".equals(wslbList.get(i).getCode())) {
                wslbList.remove(i);
            }
        }
        qzlxList = DictionaryManager.getInstance().getQzcslx();
        for (int i = 0; i < qzlxList.size(); i++) {

            qzlxList.get(i).setFlg(false);

        }

        sjxmList = DictionaryManager.getInstance().getSjxm();
        for (int i = 0; i < sjxmList.size(); i++) {

            sjxmList.get(i).setFlg(false);

        }
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(this);
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(hphm, this);

        gls.setText(SharedPreferencesUtils.getString(this, "NOTIFICATION_GLS", ""));
        xzssjg.setText(SharedPreferencesUtils.getString(this, "XZSSJG_NOTIFI", ""));

        String xzqhStr = "";
        if ("WarnBackDetails".equals(tag)) {
            dldmstr = mWarnBackCommon.getDldm();
            lddmstr = "".equals(mWarnBackCommon.getLddm()) ? "0000" : mWarnBackCommon.getDldm();
            //            wfdd.setText(mWarnBackCommon.getDlmc());
            //            dldm.setText("".equals(mWarnBackCommon.getLdmc()) ? "国道/高速" : mWarnBackCommon.getDldm());
            //路段==dldm
            SearchLDReqBean ldBean = new SearchLDReqBean(SharedPreferencesUtils.getString(this, "SJBM", ""),
                    mWarnBackCommon.getLddm());
            ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).searchLD(ldBean);
            //            //道路==wfdd
            //            SearchDLReqBean dlBean = new SearchDLReqBean(SharedPreferencesUtils.getString(this, "SJBM",
            // ""), 
            //                    mWarnBackCommon.getDldm());
            //            ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).searchDL(dlBean);
            wfdz.setText(mWarnBackCommon.getDlmc());
            wfsj.setText(mWarnBackCommon.getYjsj());

            xzqhStr = mWarnBackCommon.getXzqh();
        } else {
            dldmstr = SharedPreferencesUtils.getString(this, "DLDM_NOTIFICATION", "");
            lddmstr = SharedPreferencesUtils.getString(this, "LDDM_NOTIFICATION", "");
            wfdd.setText(SharedPreferencesUtils.getString(this, "DLMC_NOTIFICATION", ""));
            dldm.setText(SharedPreferencesUtils.getString(this, "LDMC_NOTIFICATION", ""));
            wfdz.setText(SharedPreferencesUtils.getString(this, "NOTIFICATION_WFDZ", ""));
            xzqhStr = SharedPreferencesUtils.getString(this, "XZQH_NOTIFICATION", "");

            wfsj.setText(Date_U.getCurrentTime2());
        }
        if (!"".equals(xzqhStr)) {
            String[] xzqhLeng = xzqhStr.split(",");
            xzqhList = new ArrayList<>();
            for (int i = 0; i < xzqhLeng.length; i++) {
                DicationResBean bean = new DicationResBean();
                bean.setName(xzqhLeng[i]);
                bean.setCode(xzqhLeng[i]);
                xzqhList.add(bean);
            }
            // 行政区划
            ArrayAdapter<DicationResBean> xzqhadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                    .simple_spinner_item, xzqhList);
            xzqhadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            xzqh.setAdapter(xzqhadapter);
            xzqh.setSelection(0);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initEventAndData() {

        cllxMap = new HashMap<>();
        syxzMap = new HashMap<>();
        jszztMap = new HashMap<>();
        csysMap = new HashMap<>();
        clflMap = new HashMap<>();

        for (DicationResBean bean : DictionaryManager.getInstance().getSyxz()) {
            syxzMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getCllx()) {
            cllxMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getJszzt()) {
            jszztMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getCsys()) {
            csysMap.put(bean.getCode(), bean.getName());
        }

        sjxmAdapter = new SjxmAdapter(this);
        sjxm.setAdapter(sjxmAdapter);
        sjxmAdapter.setSjxmList(sjxmList);
        //强制措施类型
        qzcslxAdapter = new QzcslxAdapter(this);
        qzcslx.setAdapter(qzcslxAdapter);
        qzcslxAdapter.setQzcslxList(qzlxList);

        if ("Detail".equals(tag)) {

            addWfxw.setEnabled(false);
            addWfxw.setClickable(false);

            ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).LoadDetail(xh);

            queryCar.setEnabled(false);
            queryCar.setBackground(getResources().getDrawable(R.drawable.gray_btn));
            queryDriver.setEnabled(false);
            queryDriver.setBackground(getResources().getDrawable(R.drawable.gray_btn));
            tjBtn.setEnabled(false);
            tjBtn.setBackground(getResources().getDrawable(R.drawable.gray_btn));
            queryWfdm.setEnabled(false);
            queryWfdm.setBackground(getResources().getDrawable(R.drawable.gray_btn));
        } else {

            zqmj.setText(SharedPreferencesUtils.getString(this, "xm", ""));

            //再次调用文书编号接口得到返回的文书编号
            WSBHReqBean bean = new WSBHReqBean();
            bean.setBmbh(bmbh);
            bean.setUser(user);
            bean.setWslb("3");
            ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).queryWsbh(bean);//查询文书编号

        }

        //号牌种类
        ArrayAdapter<DicationResBean> adapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, hpzlList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hpzl.setAdapter(adapter);
        hpzl.setSelection(1);
        //驾驶证状态
        //        ArrayAdapter<DicationResBean> jszztadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
        //                .simple_spinner_item, jszztList);
        //        jszztadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //        jszzt.setAdapter(jszztadapter);
        //        jszzt.setSelection(0);
        /**
         * 车辆分类
         */
        ArrayAdapter<DicationResBean> clfladapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, clflList);
        clfladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clfl.setAdapter(clfladapter);
        clfl.setSelection(0);
        clfl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (clflList.get(position).getCode().equals("2")) {
                    hpzlLinear.setVisibility(View.GONE);
                    cllxTabrow.setVisibility(View.GONE);
                    syxzYsTabrow.setVisibility(View.GONE);
                    fdjhTabrow.setVisibility(View.GONE);
                    clsbdhTabrow.setVisibility(View.GONE);
                    jdcsyrTabrow.setVisibility(View.GONE);
                } else {
                    hpzlLinear.setVisibility(View.VISIBLE);
                    cllxTabrow.setVisibility(View.VISIBLE);
                    syxzYsTabrow.setVisibility(View.VISIBLE);
                    fdjhTabrow.setVisibility(View.VISIBLE);
                    clsbdhTabrow.setVisibility(View.VISIBLE);
                    jdcsyrTabrow.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * 人员分类
         */
        ArrayAdapter<DicationResBean> ryfladapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, ryflList);
        ryfladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ryfl.setAdapter(ryfladapter);
        ryfl.setSelection(0);
        ryfl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (ryflList.get(position).getCode().equals("3")) {
                    driver.setVisibility(View.GONE);
                    jszztTablerow.setVisibility(View.GONE);
                    driverEdit.setVisibility(View.VISIBLE);
                    queryDriver.setEnabled(false);
                    queryDriver.setBackground(getResources().getDrawable(R.drawable.gray_btn));
                } else {
                    driver.setVisibility(View.VISIBLE);
                    jszztTablerow.setVisibility(View.VISIBLE);
                    driverEdit.setVisibility(View.GONE);
                    queryDriver.setEnabled(true);
                    queryDriver.setBackground(getResources().getDrawable(R.drawable.login_btn));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //拒收拒签
        ArrayAdapter<DicationResBean> jsjqbjadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, jsjqbjList);
        jsjqbjadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jsjq.setAdapter(jsjqbjadapter);
        jsjq.setSelection(0);


        //强制措施类型
        //        ArrayAdapter<DicationResBean> qzcslxadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
        //                .simple_spinner_item, qzlxList);
        //        qzcslxadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //        qzcslx.setAdapter(qzcslxadapter);
        //        qzcslx.setSelection(0);

        //点击右边进行页面跳转到搜索界面
        ((DrawableTextView) wfdd).setDrawableRightClick(new DrawableTextView.DrawableRightClickListener() {
            @Override
            public void onDrawableRightClickListener(View view) {
                Intent intent = new Intent(NotificationOfCompulsoryMeasuresDetailActivity.this, SearchDLActivity.class);
                startActivityForResult(intent, MY_SEARCH_CODE);
            }
        });
        //   预警反馈跳转，自动请求
        if ("WarnBackDetails".equals(tag)) {
            hpzl.setSelection(SpinnerValue.getSpinnerValueSelected(mWarnBackCommon.getHpzl(), hpzlList));//号牌种类
            hphm.updateNumber(mWarnBackCommon.getHphm());
            hpzl.setEnabled(false);
            hphm.setEnabled(false);
            ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).loadCarDatas(this, mWarnBackCommon.getHpzl
                    (), mWarnBackCommon.getHphm());
        }
        wfdz.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b) {
                    if ("".equals(wfdz.getText().toString().trim())) {
                        wfdz.setText(wfdd.getText().toString() + dldm.getText().toString() + ("".equals(gls.getText()
                                .toString()) ? "" : gls.getText().toString() + "公里") + ("".equals(ms.getText()
                                .toString()) ? "" : ms.getText().toString() + "米"));

                    }
                }

            }
        });
        wfdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(wfdz.getText().toString().trim())) {
                    wfdz.setText(wfdd.getText().toString() + dldm.getText().toString() + ("".equals(gls.getText()
                            .toString()) ? "" : gls.getText().toString() + "公里") + ("".equals(ms.getText().toString()
                    ) ? "" : ms.getText().toString() + "米"));

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

    /**
     * 返回车辆信息
     *
     * @param carBean
     */
    @Override
    public void backQueryCar(LedgerQueyCarReqBean.DataBean carBean) {


        cllxStr = carBean.getCllx();
        syxzStr = carBean.getSyxz();
        csysStr = carBean.getCsys();

        cllx.setText("".equals(cllxStr) ? "" : cllxMap.get(cllxStr));
        syxz.setText("".equals(syxzStr) ? "" : syxzMap.get(syxzStr));
        csys.setText("".equals(csysStr) ? "" : csysMap.get(csysStr));

        jdcsyr.setText(carBean.getJdcsyr());//机动车所有人

        fdjh.setText(carBean.getFdjh());
        clsbdh.setText(carBean.getClsbdh());

        //        jszzl


    }

    /**
     * 返回驾驶人信息
     *
     * @param bean
     */
    @Override
    public void backQueryDriver(Object bean) {
        LedgerDriverBean.DataBean ledgerBean = (LedgerDriverBean.DataBean) bean;
        dsr.setText(ledgerBean.getXm());
        fzjg.setText(ledgerBean.getFzjg());
        zjcx.setText(ledgerBean.getZjcx());
        dabh.setText(ledgerBean.getDabh());
        ljjf.setText(ledgerBean.getLjjf());
        jszztStr = ledgerBean.getJszzt();
        //        jszzt.setSelection(SpinnerValue.getSpinnerValueSelected(ledgerBean.getJszzt(), jszztList));
        jszzt.setText(getJszzt(ledgerBean.getJszzt()));
        lxdh.setText(ledgerBean.getLxdh());
        lxdz.setText(ledgerBean.getLxzsxxdz());
        zsxzqh.setText(ledgerBean.getZsxzqy());
        zsxxdz.setText(ledgerBean.getDjzsxxdz());//登记住所详细地址

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void backQueryDetail(NotificationOfCompulsoryMeasuresDetailResBean bean) {
        //TODO 显示查询Q21的数据
        sjxmRow.setVisibility(View.VISIBLE);
        sjwpcfdRow.setVisibility(View.VISIBLE);
        klwpcfdRow.setVisibility(View.VISIBLE);
        hpzl.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getHpzl(), hpzlList));//号牌种类

        hphm.updateNumber(bean.getHphm());
        cllxStr = bean.getCllx();
        syxzStr = bean.getSyxz();
        csysStr = bean.getCsys();

        clfl.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getClfl(), clflList));//车辆分类
        ryfl.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getRyfl(), ryflList));
        if (bean.getRyfl().equals("3")) {
            driver.setVisibility(View.GONE);
            driverEdit.setVisibility(View.VISIBLE);
            dsrEdit.setText(bean.getDsr());
        } else {
            driver.setVisibility(View.VISIBLE);
            driverEdit.setVisibility(View.GONE);
            dsr.setText(bean.getDsr());
            fzjg.setText(bean.getFzjg());
            zjcx.setText(bean.getZjcx());
            dabh.setText(bean.getDabh());
            ljjf.setText(bean.getLjjf());
        }

        cllx.setText("".equals(cllxStr) ? "" : cllxMap.get(cllxStr));
        syxz.setText("".equals(syxzStr) ? "" : syxzMap.get(syxzStr));
        csys.setText("".equals(csysStr) ? "" : csysMap.get(csysStr));
        wsbh.setText(bean.getJdsbh());
        jdcsyr.setText(bean.getJdcsyr());
        fdjh.setText(bean.getFdjh());
        clsbdh.setText(bean.getClsbdh());
        zjhm.setText(bean.getJszh());
        //        jszzt.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getJszzt(), jszztList));
        jszzt.setText(getJszzt(bean.getJszzt()));

        lxdh.setText(bean.getDh());
        lxfs.setText(bean.getLxfs());
        lxdz.setText(bean.getLxdz());
        DicationResBean xzqhbean = new DicationResBean();
        xzqhList = new ArrayList<>();
        xzqhbean.setCode(bean.getXzqh());
        xzqhbean.setName(bean.getXzqh());
        xzqhList.add(xzqhbean);
        xzqh.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getXzqh(), xzqhList));
        zsxzqh.setText(bean.getZsxzqh());
        zsxxdz.setText(bean.getZsxxdz());

        gls.setText(bean.getGls());
        ms.setText(bean.getDdms());
        wfdz.setText(bean.getWfdz());
        wfsj.setText(bean.getWfsj());
        zqmj.setText(bean.getZqmj());

        xzssjg.setText(bean.getXzssjg());

        //        fxjg.setText(bean.getFxjg());
        //        qzcslx.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getQzcslx(), qzlxList));
        //强制措施类型显示
        String qzcslxStr = bean.getQzcslx();
        if (!"".equals(qzcslxStr)) {

            //            String[] qzcslxLeg = qzcslxStr.split(",");
            char[] qzcslxLeg = qzcslxStr.toCharArray();
            //            StringBuilder builder = new StringBuilder();
            //            for (int i = 0; i < qzcslxChar.length; i++) {
            //
            //                builder.append(qzlxMap.get(String.valueOf(qzcslxChar[i])));
            //                if (i != qzcslxChar.length - 1) {
            //                    builder.append(",");
            //                }
            //
            //            }


            for (int j = 0; j < qzcslxLeg.length; j++) {
                for (int i = 0; i < qzlxList.size(); i++) {


                    if (String.valueOf(qzcslxLeg[j]).equals(qzlxList.get(i).getCode())) {

                        qzlxList.get(i).setFlg(true);


                    }

                }
            }


        }
        qzcslxAdapter.setQzcslxList(qzlxList);


        dsryy.setText(bean.getDsryy());
        bz.setText(bean.getBz());
        jsjq.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getJsjqbj(), jsjqbjList));
        mjyj.setText(bean.getMjyj());
        klwpcfd.setText(bean.getKlwpcfd());
        sjwpcfd.setText(bean.getSjwpcfd());

        List<NotificationOfCompulsoryMeasuresListResBean> wfxwList = bean.getWfxwlist();
        if (wfxwList != null && wfxwList.size() > 1) {

            for (int i = 0; i < wfxwList.size(); i++) {

                if (i == 0) {

                    wfxw1.setText(wfxwList.get(i).getWfxw());
                    scz1.setText(wfxwList.get(i).getScz());
                    bzz1.setText(wfxwList.get(i).getBzz());
                    wfdm.setText(wfxwList.get(i).getWfxw());
                    wfnr.setText(wfxwList.get(i).getWfnr());
                    flyj.setText(wfxwList.get(i).getFlyj());
                } else {
                    final View views = LayoutInflater.from(NotificationOfCompulsoryMeasuresDetailActivity.this)
                            .inflate(R.layout.addwfxw_itemview, null);
                    ViewHolder holder = new ViewHolder(views);
                    holder.wfxw.setText(wfxwList.get(i).getWfxw());
                    holder.scz.setText(wfxwList.get(i).getScz());
                    holder.bzz.setText(wfxwList.get(i).getBzz());
                    holder.wfdm.setText(wfxwList.get(i).getWfxw());
                    holder.wfnr.setText(wfxwList.get(i).getWfnr());
                    holder.flyj.setText(wfxwList.get(i).getFlyj());
                    tablewfxw.addView(views);
                }


            }
        } else {

            wfxw1.setText(wfxwList.get(0).getWfxw());
            scz1.setText(wfxwList.get(0).getScz());
            bzz1.setText(wfxwList.get(0).getBzz());
            wfdm.setText(wfxwList.get(0).getWfxw());
            wfnr.setText(wfxwList.get(0).getWfnr());
            flyj.setText(wfxwList.get(0).getFlyj());


        }
        String sjxmStr = bean.getSjxm();
        if (!"".equals(sjxmStr)) {

            String[] sjxmLeg = sjxmStr.split(",");
            for (int j = 0; j < sjxmLeg.length; j++) {
                for (int i = 0; i < sjxmList.size(); i++) {


                    if (sjxmLeg[j].equals(sjxmList.get(i).getCode())) {

                        sjxmList.get(i).setFlg(true);


                    }

                }
            }


        }
        sjxmAdapter.setSjxmList(sjxmList);
        //路段==dldm

        SearchLDReqBean ldBean = new SearchLDReqBean(SharedPreferencesUtils.getString(this, "SJBM", ""), bean.getLddm
                ());

        ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).searchLD(ldBean);


        //        //道路==wfdd
        //        SearchDLReqBean dlBean = new SearchDLReqBean(SharedPreferencesUtils.getString(this, "SJBM", ""), 
        // bean.getWfdd
        //                ());
        //        ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).searchDL(dlBean);

    }

    private PrintImageResBean mPrintImageResBean;
    private boolean isPhoto = false;   //判断文书照片是否上传完成

    @Override
    public void backComitData(PrintImageResBean bean) {
        mPrintImageResBean = bean;
        if ("WarnBackDetails".equals(tag)) {
            WarnBackReqBean warnBackBean = mWarnBackCommon.getWarnBackReqBean();
            warnBackBean.setWsbh(bean.getXh());//将文书编号设置进去
            warnBackBean.setJyw(bean.getWsjyw());//将校验位设置进去
            if (isPhoto) {
                ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).commitBackData(this, warnBackBean);
            } else {
                if (mWarnBackCommon.getPhotos().size() > 0) {
                    ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).commitPhotos(this, "uploadPhoto",
                            user, xh, "7002", mWarnBackCommon.getPhotos());

                } else {
                    ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).commitBackData(this, warnBackBean);
                }
            }
        } else {
            Intent intent = new Intent(this, PrintImageActivity.class);
            intent.putExtra("BEAN", this.bean);
            intent.putExtra("XH", mPrintImageResBean.getXh());//打印照片的地址

            intent.putExtra("TAG", "NotificationOfCompulsoryMeasuresDetail");
            startActivity(intent);
            finish();

        }

    }

    /**
     * 返回违法内容
     *
     * @param dataBean
     */
    @Override
    public void showCodeDatas(QueryCodeListBean dataBean) {
        wfdm.setText(dataBean.getWfdm());
        wfnr.setText(dataBean.getWfnr());
        flyj.setText(dataBean.getFlyj());
        wfxw1.setText(dataBean.getWfdm());//违法代码就是违法行为

    }

    @Override
    public void showCodeItemDatas(QueryCodeListBean dataBean, ViewHolder holder) {
        holder.wfdm.setText(dataBean.getWfdm());
        holder.wfnr.setText(dataBean.getWfnr());
        holder.flyj.setText(dataBean.getFlyj());
        holder.wfxw.setText(dataBean.getWfdm());//违法代码就是违法行为
    }

    /**
     * 提交反馈信息
     *
     * @param message
     */
    @Override
    public void commitBackDatas(String message) {

        AppManager.getAppManager().finishActivity(WarnBackActivity.class);

        Intent intent = new Intent(this, PrintImageActivity.class);
        intent.putExtra("BEAN", this.bean);
        intent.putExtra("XH", mPrintImageResBean.getXh());//打印照片的地址

        intent.putExtra("TAG", "NotificationOfCompulsoryMeasuresDetail");
        startActivity(intent);
        finish();

    }

    private int count = 0;

    /**
     * 提交反馈照片
     *
     * @param message
     */
    @Override
    public void commitBackPhotos(String message) {

        count++;
        if (mWarnBackCommon.getPhotos().size() == count) {
            count = 0;
            isPhoto = true;
            ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).commitBackData(this, mWarnBackCommon
                    .getWarnBackReqBean());

        }
    }

    @Override
    public void backDL(List<DLRespBean> bean) {

        wfdd.setText(bean.get(0).getDlmc());

        //        if ("WarnBackDetails".equals(tag)) {
        //            if ("".equals(wfdz.getText().toString().trim())) {
        //                wfdz.setText(wfdd.getText().toString() + dldm.getText().toString() + ("".equals(gls.getText()
        //                        .toString()) ? "" : gls.getText().toString() + "公里") + ("".equals(ms.getText().toString()) ? 
        //                        "" : ms.getText().toString() + "米"));
        //
        //            }
        //        }
    }

    @Override
    public void backLD(List<LDRespBean> bean) {
        dldm.setText(bean.get(0).getLdmc());

        if ("WarnBackDetails".equals(tag)) {
            //道路==wfdd
            SearchDLReqBean dlBean = new SearchDLReqBean(SharedPreferencesUtils.getString(this, "SJBM", ""),
                    mWarnBackCommon.getDldm());

            ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).searchDL(dlBean);
        }
    }

    @Override
    public void getWsbh(String wsbhs) {
        wsbh.setText(wsbhs);
    }

    @OnClick({R.id.back, R.id.query_car, R.id.query_driver, R.id.tj_btn, R.id.sfzmhm_scan, R.id.query_wfdm, R.id
            .addwfxw, R.id.wfsj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.query_car:
                if (mPopupKeyboard.isShown()) {
                    mPopupKeyboard.dismiss(NotificationOfCompulsoryMeasuresDetailActivity.this);
                }
                ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).loadCarDatas
                        (NotificationOfCompulsoryMeasuresDetailActivity.this, ((DicationResBean) hpzl.getSelectedItem
                                ()).getCode(), hphm.getNumber());

                break;
            case R.id.query_driver:

                ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).loadDriverDatas
                        (NotificationOfCompulsoryMeasuresDetailActivity.this, zjhm.getText().toString());

                break;
            case R.id.tj_btn:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    if (getBean() != null) {
                        if (!"".equals(gls.getText().toString())) {
                            SharedPreferencesUtils.saveString(this, "NOTIFICATION_GLS", gls.getText().toString());
                        }
                        if (!"".equals(wfdz.getText().toString())) {
                            SharedPreferencesUtils.saveString(this, "NOTIFICATION_WFDZ", wfdz.getText().toString());
                        }
                        ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).comitData
                                (NotificationOfCompulsoryMeasuresDetailActivity.this, getBean());
                    } else {
                        //                        ToastUtils.showToast(this, "请将数据补充完整");
                    }
                }
                break;
            case R.id.sfzmhm_scan:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    Intent intent = new Intent(this, DriverScanActivity.class);
                    startActivityForResult(intent, MY_SCAN_REQUEST_CODE);
                }

                break;
            case R.id.query_wfdm:

                if ("".equals(wfdm.getText().toString()))

                {
                    ToastUtils.showToast(NotificationOfCompulsoryMeasuresDetailActivity.this, "请录入违法代码！");
                    return;
                }

                ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).loadCodeLaw
                        (NotificationOfCompulsoryMeasuresDetailActivity.this, wfdm.getText().toString().trim());


                break;
            case R.id.addwfxw:

                //                View views =getLayoutInflater().inflate(R.layout.addwfxw_itemview,null);

                int rowCount = tablewfxw.getChildCount();
                if (rowCount < 4) {
                    final View views = LayoutInflater.from(NotificationOfCompulsoryMeasuresDetailActivity.this)
                            .inflate(R.layout.addwfxw_itemview, null);

                    //

                    final ViewHolder holder = new ViewHolder(views);
                    holder.deletewfxw.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tablewfxw.removeView(views);
                        }
                    });
                    holder.queryWfdm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if ("".equals(holder.wfdm.getText().toString()))

                            {
                                ToastUtils.showToast(NotificationOfCompulsoryMeasuresDetailActivity.this, "请录入违法代码！");

                            } else {

                                ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).loadItemCodeLaw
                                        (NotificationOfCompulsoryMeasuresDetailActivity.this, holder.wfdm.getText()
                                                .toString().trim(), holder);

                            }
                        }
                    });

                    tablewfxw.addView(views);

                } else {

                    ToastUtils.showToast(NotificationOfCompulsoryMeasuresDetailActivity.this, "只能添加4次！");
                }
                break;
            case R.id.wfsj:
                CoustomCheckTimePickerView.getInstance().getTime(this, wfsj);

                break;
        }
    }

    /**
     * 构建所需要的bean
     *
     * @return
     */
    private NotificationOfCompulsoryMeasuresDetailReqBan getBean() {

        bean = new NotificationOfCompulsoryMeasuresDetailReqBan();
        bean.setLrr(user);
        bean.setBmbh(bmbh);

        String clflCode = ((DicationResBean) clfl.getSelectedItem()).getCode().toString();
        bean.setClfl(clflCode);
        if (clflCode.equals("2")){
            bean.setHpzl("");
            bean.setHphm("");
            bean.setCllx("");
            bean.setSyxz("");
            bean.setCsys("");
        } else {
            bean.setHpzl(((DicationResBean) hpzl.getSelectedItem()).getCode().toString());
            if ("".equals(hphm.getNumber())) {
                ToastUtils.showToast(this, "号牌号码不能为空！");
                return null;
            }
            bean.setHphm(hphm.getNumber());
            bean.setCllx(cllxStr);
            bean.setSyxz(syxzStr);
            bean.setCsys(csysStr);
        }
        bean.setJdcsyr(jdcsyr.getText().toString());//
        bean.setFdjh(fdjh.getText().toString());//
        bean.setClsbdh(clsbdh.getText().toString());//
        if ("".equals(zjhm.getText().toString())) {
            ToastUtils.showToast(this, "证件号码不能为空！");
            return null;

        }
        bean.setJszh(zjhm.getText().toString());
        String code = ((DicationResBean) ryfl.getSelectedItem()).getCode().toString();
        bean.setRyfl(code);
        if (code.equals("3")) {
            bean.setDsr(dsrEdit.getText().toString().trim());
        } else {
            bean.setDsr(dsr.getText().toString());
        }
        bean.setFzjg(fzjg.getText().toString());
        bean.setZjcx(zjcx.getText().toString());
        bean.setDabh(dabh.getText().toString());
        //        bean.setJszzt(((DicationResBean) jszzt.getSelectedItem()).getCode().toString());
        bean.setJszzt(jszztStr);
        bean.setLjjf(ljjf.getText().toString());
        bean.setDh(lxdh.getText().toString());
        bean.setLxfs(lxfs.getText().toString());
        bean.setLxdz(lxdz.getText().toString());
        bean.setZsxzqh(zsxzqh.getText().toString());
        bean.setZsxxdz(zsxxdz.getText().toString());
        if ("".equals(dldm.getText().toString())) {
            ToastUtils.showToast(this, "请填写路段号码！");
            return null;
        }
        bean.setLddm(lddmstr);
        bean.setGls(gls.getText().toString());
        bean.setDdms(ms.getText().toString());
        if ("".equals(wfdd.getText().toString())) {
            ToastUtils.showToast(this, "请填写道路代码！");
            return null;
        }
        bean.setWfdd(dldmstr);
        if (null == xzqh.getSelectedItem()) {
            ToastUtils.showToast(this, "请通过道路代码查询行政区划！");
            return null;
        }
        bean.setXzqh(((DicationResBean) xzqh.getSelectedItem()).getCode().toString());
        if ("".equals(wfdz.getText().toString())) {
            ToastUtils.showToast(this, "请填写违法地址！");
            return null;
        }
        bean.setWfdz(wfdz.getText().toString());
        if ("".equals(wfdm.getText().toString())) {
            ToastUtils.showToast(this, "请填写违法代码！");
            return null;
        }
        // 违法行为为list
        bean.setWfxwlist(getListWfxw());


        bean.setWfsj(wfsj.getText().toString());
        if ("".equals(wfnr.getText().toString())) {
            ToastUtils.showToast(this, "请填写违法内容！");
            return null;
        }
        bean.setWfnr(wfnr.getText().toString());
        bean.setJtfs(cllxStr);//交通方式传cllx
        if ("".equals(zqmj.getText().toString())) {
            zqmj.setEnabled(true);
            zqmj.setHint("录入执勤民警警号");
            ToastUtils.showToast(this, "请填录入执勤民警警号！");
            return null;
        }
        bean.setZqmj(SharedPreferencesUtils.getString(this, "USER", ""));
        bean.setFxjg(bmbh);
        bean.setDsryy(dsryy.getText().toString());
        bean.setBz(bz.getText().toString());
        bean.setSgdj(SharedPreferencesUtils.getString(this, "SGDJ", ""));//事故等级
        bean.setJsjqbj(((DicationResBean) jsjq.getSelectedItem()).getCode().toString());//拒收拒签
        bean.setMjyj(mjyj.getText().toString());
        bean.setJd("");
        bean.setWd("");
        bean.setWslb("3");//3 文书类别为“3”时，必须符合代码表中xtlb=04和dmlb=0080的代码；为“6”时必须为空。
        bean.setQzcslx(getMianQzcslx());


        if (klwpcfdRow.getVisibility() == View.VISIBLE) {
            if ("".equals(klwpcfd.getText().toString())) {
                ToastUtils.showToast(this, "必须录入扣留物品存放地！");

                return null;
            }

        }
        bean.setKlwpcfd(klwpcfdRow.getVisibility() == View.GONE ? "" : klwpcfd.getText().toString());
        if (sjxmRow.getVisibility() == View.VISIBLE) {
            if ("".equals(getMianSjxm())) {
                ToastUtils.showToast(this, "必须录入收缴项目！");

                return null;
            }

        }
        bean.setSjxm(getMianSjxm());
        bean.setSjwpmc(getMianSjwpmc());
        if (sjwpcfdRow.getVisibility() == View.VISIBLE) {
            if ("".equals(sjwpcfd.getText().toString())) {
                ToastUtils.showToast(this, "必须录入收缴物品存放地！");

                return null;
            }

        }
        bean.setSjwpcfd(sjwpcfdRow.getVisibility() == View.GONE ? "" : sjwpcfd.getText().toString());

        if ("".equals(wsbh.getText().toString())) {
            ToastUtils.showToast(this, "必须录入文书编号！");

            return null;
        }
        bean.setPzbh(wsbh.getText().toString());
        String xzssjgStr = xzssjg.getText().toString().trim();
        SharedPreferencesUtils.saveString(this, "XZSSJG_NOTIFI", xzssjgStr);
        bean.setXzssjg(xzssjgStr);

        return bean;

    }

    private List<NotificationOfCompulsoryMeasuresListReqBean> getListWfxw() {
        List<NotificationOfCompulsoryMeasuresListReqBean> wfxwlist = null;
        wfxwlist = new ArrayList<>();
        NotificationOfCompulsoryMeasuresListReqBean reqBean1 = new NotificationOfCompulsoryMeasuresListReqBean();
        reqBean1.setWfxw(wfxw1.getText().toString());
        reqBean1.setBzz(bzz1.getText().toString());
        reqBean1.setScz(scz1.getText().toString());
        wfxwlist.add(reqBean1);
        int count = tablewfxw.getChildCount();
        if (count != 0) {

            for (int i = 0; i < count; i++) {

                NotificationOfCompulsoryMeasuresListReqBean reqBean = new NotificationOfCompulsoryMeasuresListReqBean();
                LinearLayout layout = (LinearLayout) tablewfxw.getChildAt(i);

                //                TableRow tab0 = (TableRow) layout.getChildAt(0);//第1个TableRow
                //                LinearLayout layout01 = (LinearLayout) tab0.getChildAt(1);
                //                EditText wfdmEdit = (EditText) layout01.getChildAt(0);
                //                reqBean.setWfdm(wfdmEdit.getText().toString());//违法代码

                //                TableRow tab1 = (TableRow) layout.getChildAt(1);//第2个TableRow
                //                LinearLayout layout11 = (LinearLayout) tab1.getChildAt(1);
                //                EditText wfnrEdit = (EditText) layout11.getChildAt(0);
                //                reqBean.setWfnr(wfnrEdit.getText().toString());//违法内容

                //                TableRow tab2 = (TableRow) layout.getChildAt(2);//第3个TableRow
                //                LinearLayout layout21 = (LinearLayout) tab2.getChildAt(1);
                //                TextView flyjText = (TextView) layout21.getChildAt(0);
                //                reqBean.setFlyj(flyjText.getText().toString());//违法依据


                TableRow tab3 = (TableRow) layout.getChildAt(3);//第4个TableRow
                LinearLayout layout31 = (LinearLayout) tab3.getChildAt(1);
                EditText wfxwEdit = (EditText) layout31.getChildAt(0);
                reqBean.setWfxw(wfxwEdit.getText().toString());//违法行为
                TableRow tab4 = (TableRow) layout.getChildAt(4);//第5个TableRow
                LinearLayout layout41 = (LinearLayout) tab4.getChildAt(1);
                EditText sczEdit = (EditText) layout41.getChildAt(0);
                reqBean.setScz(sczEdit.getText().toString());//撕扯值

                LinearLayout layout43 = (LinearLayout) tab4.getChildAt(3);
                EditText bzzEdit = (EditText) layout43.getChildAt(0);
                reqBean.setBzz(bzzEdit.getText().toString());//撕扯值
                wfxwlist.add(reqBean);


            }

        }
        return wfxwlist;
    }

    /**
     * 获取组装的搜缴项目
     *
     * @return
     */
    private String getMianQzcslx() {
        StringBuilder qzcslxStr = new StringBuilder();

        //拿到选择的项目
        List<DicationResBean> list = qzcslxAdapter.getQzcslxList();
        for (DicationResBean bean : list) {
            if (bean.isFlg()) {
                qzcslxStr.append(bean.getCode());
                //                sjxmStr.append(",");

            }

        }
        if ("".equals(qzcslxStr.toString())) {
            return qzcslxStr.toString();
        }
        //        String str = sjxmStr.toString().substring(0, sjxmStr.toString().length() - 1);
        String str = qzcslxStr.toString();

        return str;


    }

    /**
     * 获取组装的搜缴项目
     *
     * @return
     */
    private String getMianSjxm() {
        StringBuilder sjxmStr = new StringBuilder();

        if (sjxmRow.getVisibility() == View.GONE) {

            return "";
        }
        //拿到选择的项目
        List<DicationResBean> list = sjxmAdapter.getSjxmList();
        for (DicationResBean bean : list) {
            if (bean.isFlg()) {
                sjxmStr.append(bean.getCode());
                //                sjxmStr.append(",");

            }

        }
        if ("".equals(sjxmStr.toString())) {
            return sjxmStr.toString();
        }
        //        String str = sjxmStr.toString().substring(0, sjxmStr.toString().length() - 1);
        String str = sjxmStr.toString();

        return str;


    }

    /**
     * 获取组装的搜缴物品名称
     *
     * @return
     */
    private String getMianSjwpmc() {
        StringBuilder SjwpmcStr = new StringBuilder();

        //拿到选择的项目
        List<DicationResBean> list = sjxmAdapter.getSjxmList();
        for (DicationResBean bean : list) {
            if (bean.isFlg()) {
                SjwpmcStr.append(bean.getName());
                SjwpmcStr.append("#");

            }

        }
        if ("".equals(SjwpmcStr.toString())) {
            return SjwpmcStr.toString();
        }
        String str = SjwpmcStr.toString().substring(0, SjwpmcStr.toString().length() - 1);

        return str;


    }

    public void Visibility(List<DicationResBean> mList) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isFlg()) {
                stringBuilder.append(mList.get(i).getCode());


            }

        }

        if (stringBuilder.toString().contains("5")) {
            //是否包含5.收缴物品,包含显示收缴项目，收缴物品存放地
            sjxmRow.setVisibility(View.VISIBLE);
            sjwpcfdRow.setVisibility(View.VISIBLE);
        } else {

            sjxmRow.setVisibility(View.GONE);
            sjwpcfdRow.setVisibility(View.GONE);
        }
        if (stringBuilder.toString().contains("1") || stringBuilder.toString().contains("2") || stringBuilder
                .toString().contains("3") || stringBuilder.toString().contains("4") || stringBuilder.toString()
                .contains("6") || stringBuilder.toString().contains("8")) {
            klwpcfdRow.setVisibility(View.VISIBLE);

        } else {

            klwpcfdRow.setVisibility(View.GONE);
        }


    }

    @Override
    public void onBackPressed() {
        if (mPopupKeyboard.isShown()) {
            mPopupKeyboard.dismiss(this);
            return;
        }
        //        if (!"WarnBackDetails".equals(tag)) {
        //            Intent intent = new Intent(this, NotificationOfCompulsoryMeasuresActivity.class);
        //            startActivity(intent);
        //        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == HPHM_SCAN_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String hphmStr = data.getCharSequenceExtra("number").toString();
            hphm.updateNumber(hphmStr);

        } else if (requestCode == MY_SCAN_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String id = data.getStringExtra("id");
            if (id != null && id.length() == 18 || id.length() == 13) {
                zjhm.setText(id); //识别结果
            }
            if ("".equals(zjhm.getText().toString())) {
                ToastUtils.showToast(this, "证件号码不能为空！");
            } else {
                ((NotificationOfCompulsoryMeasuresDetailPersenter) mPresenter).loadDriverDatas(NotificationOfCompulsoryMeasuresDetailActivity.this, zjhm.getText().toString());
            }
        } else if (requestCode == MY_SEARCH_CODE && resultCode == RESULT_OK && data != null) {
            //TODODLRespBean dl, LDRespBean ld

            DLRespBean dl = (DLRespBean) data.getSerializableExtra("DL");
            LDRespBean ld = (LDRespBean) data.getSerializableExtra("LD");
            wfdd.setText(dl.getDlmc());
            dldm.setText(ld.getLdmc());

            String xzqhstr = dl.getXzqh();// 行政区划

            dldmstr = dl.getDldm();
            lddmstr = ld.getLddm();

            SharedPreferencesUtils.saveString(this, "DLDM_NOTIFICATION", dl.getDldm());
            SharedPreferencesUtils.saveString(this, "DLMC_NOTIFICATION", dl.getDlmc());
            SharedPreferencesUtils.saveString(this, "XZQH_NOTIFICATION", xzqhstr);
            SharedPreferencesUtils.saveString(this, "LDDM_NOTIFICATION", ld.getLddm());
            SharedPreferencesUtils.saveString(this, "LDMC_NOTIFICATION", ld.getLdmc());
            String[] xzqhLeng = xzqhstr.split(",");
            xzqhList = new ArrayList<>();
            for (int i = 0; i < xzqhLeng.length; i++) {
                DicationResBean bean = new DicationResBean();
                bean.setName(xzqhLeng[i]);
                bean.setCode(xzqhLeng[i]);
                xzqhList.add(bean);

            }
            // 行政区划
            ArrayAdapter<DicationResBean> xzqhadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                    .simple_spinner_item, xzqhList);
            xzqhadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            xzqh.setAdapter(xzqhadapter);
            xzqh.setSelection(0);


        }

    }

    @Override
    public void scenClick(Context mContext) {
        Intent intent = new Intent(NotificationOfCompulsoryMeasuresDetailActivity.this, ScenCameraActivity.class);

        startActivityForResult(intent, HPHM_SCAN_REQUEST_CODE);
    }

    public static class ViewHolder {
        @BindView(R.id.wfxw)
        EditText wfxw;
        @BindView(R.id.scz)
        EditText scz;
        @BindView(R.id.bzz)
        EditText bzz;
        @BindView(R.id.deletewfxw)
        TextView deletewfxw;
        @BindView(R.id.wfdm)
        EditText wfdm;
        @BindView(R.id.query_wfdm)
        Button queryWfdm;
        @BindView(R.id.wfnr)
        EditText wfnr;
        @BindView(R.id.flyj)
        TextView flyj;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
