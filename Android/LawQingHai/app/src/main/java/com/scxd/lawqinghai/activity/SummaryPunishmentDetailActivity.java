package com.scxd.lawqinghai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.parkingwang.vehiclekeyboard.PopupKeyboard;
import com.parkingwang.vehiclekeyboard.ScenClick;
import com.parkingwang.vehiclekeyboard.view.InputView;
import com.scxd.idcardlibs.DriverScanActivity;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.WarnBackCommon;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.CarPrintBean;
import com.scxd.lawqinghai.bean.request.LedgerDriverBean;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.bean.request.SummaryPunishmentDetailReqBan;
import com.scxd.lawqinghai.bean.request.WSBHReqBean;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.bean.response.CarLawDetailsRspBean;
import com.scxd.lawqinghai.bean.response.DLRespBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.LDRespBean;
import com.scxd.lawqinghai.bean.response.PrintImageResBean;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.bean.response.SummaryPunishmentDetailResBean;
import com.scxd.lawqinghai.manager.AppManager;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.SummaryPunishmentDetailPersenter;
import com.scxd.lawqinghai.mvp.view.SummaryPunishmentDetailView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.DrawableTextView;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.SpinnerValue;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.CoustomCheckTimePickerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述：简易处罚决定书
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class SummaryPunishmentDetailActivity extends BaseActivity implements SummaryPunishmentDetailView, ScenClick {
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
    @BindView(R.id.jszzt)
    EditText jszzt;
    @BindView(R.id.lxdh)
    EditText lxdh;
    @BindView(R.id.lxdz)
    EditText lxdz;
    @BindView(R.id.xzqh)
    Spinner xzqh;
    @BindView(R.id.zsxzqh)
    EditText zsxzqh;
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
    @BindView(R.id.wfxw)
    EditText wfxw;
    @BindView(R.id.scz)
    EditText scz;
    @BindView(R.id.bzz)
    EditText bzz;
    @BindView(R.id.wfdm)
    EditText wfdm;
    @BindView(R.id.wfsj)
    TextView wfsj;
    @BindView(R.id.wfnr)
    EditText wfnr;
    @BindView(R.id.wfjf)
    EditText wfjf;
    @BindView(R.id.fkje)
    EditText fkje;
    @BindView(R.id.cfjg)
    RadioGroup cfjg;
    @BindView(R.id.jg)
    RadioButton jg;
    @BindView(R.id.fk)
    RadioButton fk;
    @BindView(R.id.jkfs)
    Spinner jkfs;
    @BindView(R.id.zqmj)
    EditText zqmj;
    @BindView(R.id.jkrq)
    TextView jkrq;
    @BindView(R.id.jsjq)
    Spinner jsjq;
    @BindView(R.id.jkbj)
    Spinner jkbj;
    @BindView(R.id.tj_btn)
    Button tjBtn;
    @BindView(R.id.fkjerow)
    TableRow fkjerow;
    @BindView(R.id.jkfsrow)
    TableRow jkfsrow;
    @BindView(R.id.jkrqrow)
    TableRow jkrqrow;
    @BindView(R.id.jkbjrow)
    TableRow jkbjrow;
    @BindView(R.id.query_wfdm)
    Button queryWfdm;
    private final int HPHM_SCAN_REQUEST_CODE = 200;
    @BindView(R.id.xzssjg)
    EditText xzssjg;
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
    @BindView(R.id.jdcsyr_tabrow)
    TableRow jdcsyrTabrow;
    private int MY_SCAN_REQUEST_CODE = 300;
    private int MY_SEARCH_CODE = 400;
    private Map<String, String> cllxMap;
    private Map<String, String> syxzMap;
    private Map<String, String> jszztMap;
    private Map<String, String> csysMap;
    List<DicationResBean> hpzlList;
    List<DicationResBean> jszztList;
    List<DicationResBean> jkfsList;
    List<DicationResBean> jkbjList;
    List<DicationResBean> clflList;
    List<DicationResBean> ryflList;
    List<DicationResBean> jsjqbjList;

    List<DicationResBean> xzqhList;

    private String tag;
    private String xh;
    private PopupKeyboard mPopupKeyboard;
    private String user;
    private String bmbh;
    private String ssbmbh;
    private String cfjgStr = "2";

    private String cllxStr;
    private String syxzStr;
    private String csysStr;
    private WarnBackCommon mWarnBackCommon;
    //将简易的对象传到打印界面
    SummaryPunishmentDetailReqBan bean;

    /**
     * 道路代码/路段代码/行政区划
     */
    private String dldmstr;
    private String lddmstr;
    /**
     * 驾驶证状态
     */
    private String jszztStr = "A";

    private CarLawDetailsRspBean.DataBean carbean;
    
    @Override
    public BasePresenter getPresenter() {
        return new SummaryPunishmentDetailPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jycfjds_details;
    }

    @Override
    protected void initInjector() {
        title.setText("简易程序处罚决定书");

        tag = getIntent().getStringExtra("TAG");
        if ("WarnBackDetails".equals(tag)) {
            mWarnBackCommon = new WarnBackCommon();
            mWarnBackCommon = (WarnBackCommon) getIntent().getSerializableExtra("BACK");
            if (null != mWarnBackCommon) {
                xh = mWarnBackCommon.getYjxh();
            }
        } else if ("CarLawDetailsActivity".equals(tag)){
            hphm.setCilck(true); //禁止号牌号码点击
            carbean = (CarLawDetailsRspBean.DataBean) getIntent().getSerializableExtra("CarLawBean");
            if (carbean == null) {
                ToastUtils.showToast(this, "暂无任何相关信息！！！");
                return;
            }
        } else {
            xh = getIntent().getStringExtra("XH");
        }
        user = SharedPreferencesUtils.getString(this, "USER", "");
        bmbh = SharedPreferencesUtils.getString(this, "SSBMBH", "");
        ssbmbh = SharedPreferencesUtils.getString(this, "SSBMBH", "");
        hpzlList = DictionaryManager.getInstance().getHpzl();
        jszztList = DictionaryManager.getInstance().getJszzt();
        jkfsList = DictionaryManager.getInstance().getJkfs();
        jkbjList = DictionaryManager.getInstance().getJkbj();
        clflList = DictionaryManager.getInstance().getClfl();
        jsjqbjList = DictionaryManager.getInstance().getJsjq();
        ryflList = DictionaryManager.getInstance().getRyfl();
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(this);
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(hphm, this);

        gls.setText(SharedPreferencesUtils.getString(this, "SUMMARY_GLS", ""));
        wfdm.setText(SharedPreferencesUtils.getString(this, "SUMMARY_WFDM", ""));
        wfnr.setText(SharedPreferencesUtils.getString(this, "SUMMARY_WFNR", ""));
        wfjf.setText(SharedPreferencesUtils.getString(this, "SUMMARY_WFJF", ""));
        wfxw.setText(SharedPreferencesUtils.getString(this, "SUMMARY_WFXW", ""));
        xzssjg.setText(SharedPreferencesUtils.getString(this, "XZSSJG_SUMMARY", ""));

        String xzqhStr = "";
        if ("WarnBackDetails".equals(tag)) {
            dldmstr = mWarnBackCommon.getDldm();
            lddmstr = "".equals(mWarnBackCommon.getLddm()) ? "0000" : mWarnBackCommon.getDldm();
            //            wfdd.setText(mWarnBackCommon.getDlmc());
            dldm.setText("".equals(mWarnBackCommon.getLdmc()) ? "国道/高速" : mWarnBackCommon.getDldm());
            //路段==dldm
            SearchLDReqBean ldBean = new SearchLDReqBean(SharedPreferencesUtils.getString(this, "SJBM", ""),
                    mWarnBackCommon.getLddm());
            ((SummaryPunishmentDetailPersenter) mPresenter).searchLD(ldBean);
            wfdz.setText(mWarnBackCommon.getDlmc());
            wfsj.setText(mWarnBackCommon.getYjsj());

            xzqhStr = mWarnBackCommon.getXzqh();
        } else if ("CarLawDetailsActivity".equals(tag)) {
            dldmstr = carbean.getWfdd();
            lddmstr = "".equals(carbean.getWfdd()) ? "0000" : carbean.getWfdd();
            wfdd.setText(carbean.getWfdz());
            dldm.setText("".equals(carbean.getWfdd()) ? "国道/高速" : carbean.getWfdd());
            //路段==dldm
            SearchLDReqBean ldBean = new SearchLDReqBean(SharedPreferencesUtils.getString(this, "SJBM", ""),
                    carbean.getWfdd());
            ((SummaryPunishmentDetailPersenter) mPresenter).searchLD(ldBean);
            wfdz.setText(wfdd.getText().toString()+ ("".equals(gls.getText()
                    .toString()) ? "" : gls.getText().toString() + "公里") + ("".equals(ms.getText()
                    .toString()) ? "" : ms.getText().toString() + "米"));
            wfsj.setText(carbean.getWfsj());
            xzqhStr = carbean.getZsxzqh();
        } else {
            dldmstr = SharedPreferencesUtils.getString(this, "DLDM_SUMMARY", "");
            lddmstr = SharedPreferencesUtils.getString(this, "LDDM_SUMMARY", "");
            wfdd.setText(SharedPreferencesUtils.getString(this, "DLMC_SUMMARY", ""));
            dldm.setText(SharedPreferencesUtils.getString(this, "LDMC_SUMMARY", ""));
            wfdz.setText(SharedPreferencesUtils.getString(this, "SUMMARY_WFDZ", ""));
            xzqhStr = SharedPreferencesUtils.getString(this, "XZQH_SUMMARY", "");

            wfsj.setText(Date_U.getCurrentTime2());
        }
        setXzqhxx(xzqhStr);
    }

    /**
     * 设置行政区划
     * @param xzqhStr
     */
    public void setXzqhxx(String xzqhStr){
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

        //点击右边进行页面跳转到搜索界面
        ((DrawableTextView) wfdd).setDrawableRightClick(new DrawableTextView.DrawableRightClickListener() {
            @Override
            public void onDrawableRightClickListener(View view) {

                Intent intent = new Intent(SummaryPunishmentDetailActivity.this, SearchDLActivity.class);
                startActivityForResult(intent, MY_SEARCH_CODE);


            }
        });
        jkrq.setText(Date_U.getCurrentTime());
        //罚款

        fkjerow.setVisibility(View.VISIBLE);
        jkfsrow.setVisibility(View.VISIBLE);
        jkbjrow.setVisibility(View.VISIBLE);


        cfjg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {

                    case R.id.jg:
                        cfjgStr = "1";
                        fkjerow.setVisibility(View.GONE);
                        jkfsrow.setVisibility(View.GONE);
                        jkrqrow.setVisibility(View.GONE);
                        jkbjrow.setVisibility(View.GONE);
                        break;

                    case R.id.fk:
                        cfjgStr = "2";
                        fkjerow.setVisibility(View.VISIBLE);
                        jkfsrow.setVisibility(View.VISIBLE);
                        jkbjrow.setVisibility(View.VISIBLE);
                        if ("1".equals(((DicationResBean) jkbj.getSelectedItem()).getCode().toString())) {
                            jkrqrow.setVisibility(View.VISIBLE);
                        } else {
                            jkrqrow.setVisibility(View.GONE);
                        }


                        break;


                }


            }
        });
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
        //交款方式
        ArrayAdapter<DicationResBean> jkfsadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, jkfsList);
        jkfsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jkfs.setAdapter(jkfsadapter);
        jkfs.setSelection(0);
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
                    jdcsyrTabrow.setVisibility(View.GONE);
                } else {
                    hpzlLinear.setVisibility(View.VISIBLE);
                    cllxTabrow.setVisibility(View.VISIBLE);
                    syxzYsTabrow.setVisibility(View.VISIBLE);
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
        //交款标记
        ArrayAdapter<DicationResBean> jkbjadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, jkbjList);
        jkbjadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jkbj.setAdapter(jkbjadapter);
        jkbj.setSelection(0);
        jkbj.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String code = ((DicationResBean) adapterView.getSelectedItem()).getCode();
                if ("1".equals(code)) {
                    jkrqrow.setVisibility(View.VISIBLE);
                } else {

                    jkrqrow.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //拒收拒签
        ArrayAdapter<DicationResBean> jsjqbjadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, jsjqbjList);
        jsjqbjadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jsjq.setAdapter(jsjqbjadapter);
        jsjq.setSelection(0);


        fkje.addTextChangedListener(new TextWatcher() {
            int l = 0;////////记录字符串被删除字符之前，字符串的长度
            int location = 0;//记录光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                l = s.length();
                location = fkje.getSelectionStart();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern p = Pattern.compile("^(200|[1-9]\\d|\\d)$");

                Matcher m = p.matcher(s.toString());
                if (m.find() || ("").equals(s.toString())) {

                } else {
                    int temp = Integer.valueOf(s.toString());
                    if (temp >= 200) {
                        fkje.setText("200");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //   预警反馈跳转，自动请求
        if ("WarnBackDetails".equals(tag)) {
            hpzl.setSelection(SpinnerValue.getSpinnerValueSelected(mWarnBackCommon.getHpzl(), hpzlList));//号牌种类
            hphm.updateNumber(mWarnBackCommon.getHphm());
            hpzl.setEnabled(false);
            hphm.setEnabled(false);
            ((SummaryPunishmentDetailPersenter) mPresenter).loadCarDatas(SummaryPunishmentDetailActivity.this,
                    mWarnBackCommon.getHpzl(), mWarnBackCommon.getHphm());
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


        if ("Detail".equals(tag)) {  //   查询简易详情

            ((SummaryPunishmentDetailPersenter) mPresenter).LoadDetail(xh);

            queryCar.setEnabled(false);
            queryCar.setBackground(getResources().getDrawable(R.drawable.gray_btn));
            queryDriver.setEnabled(false);
            queryDriver.setBackground(getResources().getDrawable(R.drawable.gray_btn));
            tjBtn.setEnabled(false);
            tjBtn.setBackground(getResources().getDrawable(R.drawable.gray_btn));
            queryWfdm.setEnabled(false);
            queryWfdm.setBackground(getResources().getDrawable(R.drawable.gray_btn));
        } else if ("CarLawDetailsActivity".equals(tag)){ 

            hpzl.setSelection(SpinnerValue.getSpinnerValueSelected(carbean.getHpzl(), hpzlList));//号牌种类
            hphm.updateNumber(carbean.getHphm());
            ((SummaryPunishmentDetailPersenter) mPresenter).loadCarDatas(SummaryPunishmentDetailActivity.this,
                    carbean.getHpzl(), carbean.getHphm());
            queryCar.setEnabled(false);
            queryCar.setBackground(getResources().getDrawable(R.drawable.gray_btn));
            clfl.setSelection(SpinnerValue.getSpinnerValueSelected(carbean.getClfl(), clflList));
            clfl.setEnabled(false);
            hpzl.setEnabled(false);
            hphm.setClickable(false);
            dsr.setText(carbean.getDsr());
            fzjg.setText(carbean.getFzjg());
            zjcx.setText(carbean.getZjcx());
            dabh.setText(carbean.getDabh());
            lxdh.setText(carbean.getDh());
            lxdz.setText(carbean.getLxfs());
            dldm.setText(carbean.getWfdd());
            wfdd.setText(carbean.getWfdz());
            wfdm.setText(carbean.getWfdm());
            wfsj.setText(carbean.getWfsj());
            wfjf.setText(carbean.getWfjfs());
            fkje.setText(carbean.getFkje());
            try {
                if (Integer.valueOf(carbean.getFkje()).intValue() > 0) {
                    cfjgStr = "2";
                    fk.setChecked(true);
                    fkjerow.setVisibility(View.VISIBLE);
                    jkfsrow.setVisibility(View.VISIBLE);
                    jkbjrow.setVisibility(View.VISIBLE);
                } else {
                    cfjgStr = "1";
                    jg.setChecked(true);
                    fkjerow.setVisibility(View.GONE);
                    jkfsrow.setVisibility(View.GONE);
                    jkbjrow.setVisibility(View.GONE);
                }
            } catch (Exception e){
                cfjgStr = "1";
                jg.setChecked(true);
                fkjerow.setVisibility(View.GONE);
                jkfsrow.setVisibility(View.GONE);
                jkbjrow.setVisibility(View.GONE);
            }
            lxdh.setText(carbean.getZsxxdz());
            zjhm.setText(carbean.getJszh());
//            zqmj.setText(carbean.getZqmj());
            zqmj.setText(SharedPreferencesUtils.getString(this, "xm", ""));
            if (null != carbean.getWfdm() && !"".equals(carbean.getWfdm())){
                queryWfdm.setClickable(false);
                queryWfdm.setBackground(getResources().getDrawable(R.drawable.gray_btn));
                ((SummaryPunishmentDetailPersenter) mPresenter).loadCodeLaw(SummaryPunishmentDetailActivity.this,
                        wfdm.getText().toString().trim());
            }
        } else {
            zqmj.setText(SharedPreferencesUtils.getString(this, "xm", ""));
            //再次调用文书编号接口得到返回的文书编号
            WSBHReqBean bean = new WSBHReqBean();
            bean.setBmbh(bmbh);
            bean.setUser(user);
            bean.setWslb("1");
            ((SummaryPunishmentDetailPersenter) mPresenter).queryWsbh(bean);//查询文书编号


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
     * 返回查询的车辆信息
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
        //        jszzl
        //机动车所有人
        jdcsyr.setText(carBean.getJdcsyr());

        if ("CarLawDetailsActivity".equals(tag)) {
            //再次调用文书编号接口得到返回的文书编号
            WSBHReqBean bean = new WSBHReqBean();
            bean.setBmbh(bmbh);
            bean.setUser(user);
            bean.setWslb("1");
            ((SummaryPunishmentDetailPersenter) mPresenter).queryWsbh(bean);//查询文书编号
        }
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
    }

    /**
     * //显示查询Q20的数据
     *
     * @param bean
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void backQueryDetail(SummaryPunishmentDetailResBean bean) {


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
            queryDriver.setClickable(false);
        } else {
            driver.setVisibility(View.VISIBLE);
            driverEdit.setVisibility(View.GONE);
            dsr.setText(bean.getDsr());
            fzjg.setText(bean.getFzjg());
            zjcx.setText(bean.getZjcx());
            dabh.setText(bean.getDabh());
            ljjf.setText(bean.getLjjf());
        }

        wsbh.setText(bean.getJdsbh());
        cllx.setText("".equals(cllxStr) ? "" : cllxMap.get(cllxStr));
        syxz.setText("".equals(syxzStr) ? "" : syxzMap.get(syxzStr));
        csys.setText("".equals(csysStr) ? "" : csysMap.get(csysStr));
        zjhm.setText(bean.getJszh().toString());
        dsr.setText(bean.getDsr());
        fzjg.setText(bean.getFzjg());
        zjcx.setText(bean.getZjcx());
        dabh.setText(bean.getDabh());
        //        jszzt.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getJszzt(), jszztList));//驾驶证状态
        jszzt.setText(getJszzt(bean.getJszzt()));
        ljjf.setText(bean.getLjjf());
        lxdh.setText(bean.getDh());
        lxdz.setText(bean.getZsxxdz());
        DicationResBean xzqhbean = new DicationResBean();
        xzqhList = new ArrayList<>();
        xzqhbean.setCode(bean.getJszzt());
        xzqhbean.setName(bean.getJszzt());
        xzqhList.add(xzqhbean);
        xzqh.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getXzqh(), xzqhList));
        zsxzqh.setText(bean.getZsxzqh());
        jdcsyr.setText(bean.getJdcsyr());
        scz.setText(bean.getScz());
        bzz.setText(bean.getBzz());
        gls.setText(bean.getGls());
        ms.setText(bean.getDdms());
        wfdz.setText(bean.getWfdz());
        wfxw.setText(bean.getWfxw());
        wfdm.setText(bean.getWfxw());
        wfsj.setText(bean.getWfsj());
        wfnr.setText(bean.getWfnr());
        wfjf.setText(bean.getWfjf());
        fkje.setText(bean.getFkje());
        cfjgStr = bean.getCfzl();
        if ("2".equals(cfjgStr)) {
            fk.setChecked(true);
            fkjerow.setVisibility(View.VISIBLE);
            jkfsrow.setVisibility(View.VISIBLE);
            jkbjrow.setVisibility(View.VISIBLE);
            if ("1".equals(((DicationResBean) jkbj.getSelectedItem()).getCode().toString())) {
                jkrqrow.setVisibility(View.VISIBLE);
            } else {
                jkrqrow.setVisibility(View.GONE);
            }
        } else {//if ("1".equals(cfjgStr))
            jg.setChecked(true);
        }
        zqmj.setText(bean.getZqmj());
        jkbj.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getJkbj(), jkbjList));//交款标记
        jkrq.setText(bean.getJkrq());
        jkfs.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getJkfs(), jkfsList));//交款方式
        jsjq.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getJsjqbj(), jsjqbjList));//拒收拒签

        xzssjg.setText(bean.getXzssjg());

        //路段==dldm

        SearchLDReqBean ldBean = new SearchLDReqBean(SharedPreferencesUtils.getString(this, "SJBM", ""), bean.getLddm
                ());

        ((SummaryPunishmentDetailPersenter) mPresenter).searchLD(ldBean);


        //道路==wfdd
        SearchDLReqBean dlBean = new SearchDLReqBean(SharedPreferencesUtils.getString(this, "SJBM", ""), bean.getWfdd
                ());
        ((SummaryPunishmentDetailPersenter) mPresenter).searchDL(dlBean);


    }

    private PrintImageResBean mPrintImageResBean;
    private boolean isPhoto = false;   //判断文书照片是否上传完成

    /**
     * 提交数据完成后跳转到打印界面
     *
     * @param bean
     */
    @Override
    public void backComitData(PrintImageResBean bean) {
        mPrintImageResBean = bean;
        if ("WarnBackDetails".equals(tag)) {
            WarnBackReqBean warnBackBean = mWarnBackCommon.getWarnBackReqBean();
            warnBackBean.setWsbh(bean.getXh());//将文书编号设置进去
            warnBackBean.setJyw(bean.getWsjyw());//将校验位设置进去
            if (isPhoto) {
                ((SummaryPunishmentDetailPersenter) mPresenter).commitBackData(this, warnBackBean);

            } else {
                if (mWarnBackCommon.getPhotos().size() > 0) {
                    ((SummaryPunishmentDetailPersenter) mPresenter).commitPhotos(SummaryPunishmentDetailActivity
                            .this, "uploadPhoto", user, xh, "7002", mWarnBackCommon.getPhotos());

                } else {
                    ((SummaryPunishmentDetailPersenter) mPresenter).commitBackData(this, warnBackBean);
                }
            }
        } else {
            Intent intent = new Intent(this, PrintImageActivity.class);
            intent.putExtra("BEAN", this.bean);//打印照片的地址
            intent.putExtra("XH", mPrintImageResBean.getXh());//打印照片的地址

            intent.putExtra("TAG", "SummaryPunishmentDetail");
            startActivity(intent);
            finish();
        }
    }

    /**
     * 通过违法代码查询数据
     *
     * @param dataBean
     */
    @Override
    public void showCodeDatas(QueryCodeListBean dataBean) {

        wfdm.setText(dataBean.getWfdm());
        wfnr.setText(dataBean.getWfnr());
        wfjf.setText(dataBean.getWfjf());
        wfxw.setText(dataBean.getWfdm());


    }

    /**
     * 提交反馈信息
     *
     * @param message
     */
    @Override
    public void commitBackDatas(String message) {
        //关闭预警反馈界面
        AppManager.getAppManager().finishActivity(WarnBackActivity.class);

        Intent intent = new Intent(this, PrintImageActivity.class);
        intent.putExtra("BEAN", this.bean);
        intent.putExtra("XH", mPrintImageResBean.getXh());//打印照片的地址

        intent.putExtra("TAG", "SummaryPunishmentDetail");
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
            ((SummaryPunishmentDetailPersenter) mPresenter).commitBackData(this, mWarnBackCommon.getWarnBackReqBean());

        }
    }

    @Override
    public void backDL(List<DLRespBean> bean) {
        wfdd.setText(bean.get(0).getDlmc());

        //        if ("WarnBackDetails".equals(tag)) {
        //            if ("".equals(wfdz.getText().toString().trim())) {
        //                wfdz.setText(wfdd.getText().toString() + dldm.getText().toString() + ("".equals(gls.getText
        // ().toString()) ? "" : gls.getText().toString() + "公里") + ("".equals(ms.getText().toString()) ? "" : ms
        // .getText().toString() + "米"));
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

            ((SummaryPunishmentDetailPersenter) mPresenter).searchDL(dlBean);
        }
    }

    @Override
    public void getWsbh(String wsbhs) {

        wsbh.setText(wsbhs);


    }
    /**
     * 处理提交返回数据
     * @param bean
     */
    @Override
    public void showDatas(PrintImageResBean bean) {
        Intent intent = new Intent(this, PrintImageActivity.class);
        intent.putExtra("BEAN", this.bean);
        intent.putExtra("XH", bean.getXh());//打印照片的地址
        intent.putExtra("TAG", "SummaryPunishmentDetail");
        intent.putExtra("TAG2", "CarLaw");
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.back, R.id.query_car, R.id.query_driver, R.id.tj_btn, R.id.sfzmhm_scan, R.id.query_wfdm, R.id
            .wfsj, R.id.jkrq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.query_car:
                if (mPopupKeyboard.isShown()) {
                    mPopupKeyboard.dismiss(SummaryPunishmentDetailActivity.this);
                }
                if ("".equals(hphm.getNumber())) {
                    ToastUtils.showToast(SummaryPunishmentDetailActivity.this, "号牌号码不能为空！");
                    break;
                }
                ((SummaryPunishmentDetailPersenter) mPresenter).loadCarDatas(SummaryPunishmentDetailActivity.this, (
                        (DicationResBean) hpzl.getSelectedItem()).getCode(), hphm.getNumber());

                break;
            case R.id.query_driver:
                if ("".equals(zjhm.getText().toString())) {
                    ToastUtils.showToast(SummaryPunishmentDetailActivity.this, "证件号码不能为空！");
                    break;
                }
                ((SummaryPunishmentDetailPersenter) mPresenter).loadDriverDatas(SummaryPunishmentDetailActivity.this,
                        zjhm.getText().toString());

                break;
            case R.id.tj_btn:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    if (getBean() != null) {
                        if (!"".equals(gls.getText().toString())) {
                            SharedPreferencesUtils.saveString(this, "SUMMARY_GLS", gls.getText().toString());
                        }
                        if (!"".equals(wfdz.getText().toString())) {
                            SharedPreferencesUtils.saveString(this, "SUMMARY_WFDZ", wfdz.getText().toString());
                        }
                        if (!"".equals(wfdm.getText().toString())) {
                            SharedPreferencesUtils.saveString(this, "SUMMARY_WFDM", wfdm.getText().toString());
                        }
                        if (!"".equals(wfnr.getText().toString())) {
                            SharedPreferencesUtils.saveString(this, "SUMMARY_WFNR", wfnr.getText().toString());
                        }
                        if (!"".equals(wfjf.getText().toString())) {
                            SharedPreferencesUtils.saveString(this, "SUMMARY_WFJF", wfjf.getText().toString());
                        }
                        if (!"".equals(wfxw.getText().toString())) {
                            SharedPreferencesUtils.saveString(this, "SUMMARY_WFXW", wfxw.getText().toString());
                        }
                        if ("CarLawDetailsActivity".equals(tag)) {
                            ((SummaryPunishmentDetailPersenter) mPresenter).comitCarLawData(SummaryPunishmentDetailActivity
                                    .this, getPrintBean());
                        } else {
                            ((SummaryPunishmentDetailPersenter) mPresenter).comitData(SummaryPunishmentDetailActivity
                                    .this, getBean());
                        }
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
                    ToastUtils.showToast(SummaryPunishmentDetailActivity.this, "请录入违法代码！");
                    break;
                }

                ((SummaryPunishmentDetailPersenter) mPresenter).loadCodeLaw(SummaryPunishmentDetailActivity.this,
                        wfdm.getText().toString().trim());


                break;
            case R.id.wfsj:
                CoustomCheckTimePickerView.getInstance().getTime(this, wfsj);
                break;
            case R.id.jkrq:
                CoustomCheckTimePickerView.getInstance().getRqTime(this, jkrq);
                break;
        }
    }
    /**
     * 设置提交内容
     */
    private CarPrintBean getPrintBean() {
        CarPrintBean bean = new CarPrintBean();

        bean.setXh(getIntent().getStringExtra("xh"));
        bean.setJdsbh(wsbh.getText().toString());
        bean.setCljg(ssbmbh);
        bean.setCjjg(carbean.getCjjg());
        bean.setJbr1(carbean.getJbr());
        String code = ((DicationResBean) ryfl.getSelectedItem()).getCode().toString();
        bean.setRyfl(code);
        if (code.equals("3")) {
            bean.setDsr(dsrEdit.getText().toString().trim());
        } else {
            bean.setDsr(dsr.getText().toString());
        }
        bean.setJszh(zjhm.getText().toString().trim());
        bean.setDabh(dabh.getText().toString().trim());
        bean.setFzjg(carbean.getFzjg());
        bean.setZjcx(carbean.getZjcx());
        bean.setZsxzqh(zsxzqh.getText().toString().trim());
        bean.setZsxxdz(lxdz.getText().toString().trim());
        bean.setDh(lxdh.getText().toString().trim());
        bean.setLxfs(carbean.getLxfs());
        bean.setCfzl(cfjgStr);
        bean.setFkje(fkjerow.getVisibility() == View.GONE ? "" : fkje.getText().toString());
        bean.setXm(dsr.getText().toString().trim());
        bean.setHphm(carbean.getHphm());
        bean.setHpzl(carbean.getHpzl());
        bean.setJdcsyr(jdcsyr.getText().toString().trim());
        bean.setClfl(((DicationResBean) clfl.getSelectedItem()).getCode().toString());
        bean.setSyxz(syxzStr);
        bean.setJtfs(carbean.getJtfs());
        bean.setWfsj(wfsj.getText().toString().trim());
        bean.setWfdd(dldmstr);
        bean.setWfdz(wfdz.getText().toString().trim());
        bean.setWfxw(wfxw.getText().toString().trim());
        bean.setWfjf(wfjf.getText().toString().trim());
        bean.setLrr(user);
        bean.setXzssjg(xzssjg.getText().toString().trim());

        return bean;
    }
    /**
     * 构建所需要的bean
     *
     * @return
     */
    private SummaryPunishmentDetailReqBan getBean() {
        bean = new SummaryPunishmentDetailReqBan();
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

        if ("".equals(zjhm.getText().toString())) {
            ToastUtils.showToast(this, "证件号码不能为空！");
            return null;

        }
        bean.setJszh(zjhm.getText().toString());
        bean.setZjcx(zjcx.getText().toString());
        bean.setJtfs(cllxStr);//交通方式传车辆类型
        String code = ((DicationResBean) ryfl.getSelectedItem()).getCode().toString();
        bean.setRyfl(code);
        if (code.equals("3")) {
            bean.setDsr(dsrEdit.getText().toString().trim());
        } else {
            bean.setDsr(dsr.getText().toString());
        }
        bean.setFzjg(fzjg.getText().toString());
        bean.setDabh(dabh.getText().toString());
        //        bean.setJszzt(((DicationResBean) jszzt.getSelectedItem()).getCode().toString());
        bean.setJszzt(jszztStr);
        bean.setLjjf(ljjf.getText().toString());
        bean.setDh(lxdh.getText().toString());
        bean.setZsxxdz(lxdz.getText().toString());

        bean.setZsxzqh(zsxzqh.getText().toString());
        if ("".equals(dldm.getText().toString())) {
            ToastUtils.showToast(this, "请填写路段号码！");
            return null;
        }
        bean.setLddm(lddmstr);//路段代码
        bean.setJdcsyr(jdcsyr.getText().toString());
        bean.setScz(scz.getText().toString());
        bean.setBzz(bzz.getText().toString());
        bean.setGls(gls.getText().toString());
        bean.setDdms(ms.getText().toString());
        if ("".equals(wfdd.getText().toString())) {
            ToastUtils.showToast(this, "请填写道路代码！");
            return null;
        }
        bean.setWfdd(dldmstr);

        if ("".equals(wfdz.getText().toString())) {
            ToastUtils.showToast(this, "请填写违法地址！");
            return null;
        }
        bean.setWfdz(wfdz.getText().toString());
        if (null == xzqh.getSelectedItem()) {
            ToastUtils.showToast(this, "请通过道路代码查询行政区划！");
            return null;
        }
        bean.setXzqh(((DicationResBean) xzqh.getSelectedItem()).getCode().toString());
        if ("".equals(wfxw.getText().toString())) {
            ToastUtils.showToast(this, "请填写违法行为！");
            return null;
        }
        bean.setWfxw(wfxw.getText().toString());
        if ("".equals(wfdm.getText().toString())) {
            ToastUtils.showToast(this, "请填写违法代码！");
            return null;
        }
        bean.setWfsj(wfsj.getText().toString());
        if ("".equals(wfnr.getText().toString())) {
            ToastUtils.showToast(this, "请填写违法内容！");
            return null;
        }
        if ("".equals(wfjf.getText().toString())) {
            ToastUtils.showToast(this, "请填录入违法记分！");
            return null;
        }
        bean.setWfjf(wfjf.getText().toString());
        bean.setFkje(fkjerow.getVisibility() == View.GONE ? "" : fkje.getText().toString());
        bean.setCfzl(cfjgStr);//处罚结果
        if ("".equals(zqmj.getText().toString())) {
            zqmj.setEnabled(true);
            zqmj.setHint("录入执勤民警警号");
            ToastUtils.showToast(this, "请填录入执勤民警警号！");
            return null;
        }
        bean.setZqmj(SharedPreferencesUtils.getString(this, "USER", ""));
        bean.setFxjg(ssbmbh);
        bean.setJkrq(jkrqrow.getVisibility() == View.GONE ? "" : jkrq.getText().toString());
        bean.setJkfs(jkfsrow.getVisibility() == View.GONE ? "" : ((DicationResBean) jkfs.getSelectedItem()).getCode()
                .toString());
        bean.setClsj("");//处理时间
        bean.setJkbj(((DicationResBean) jkbj.getSelectedItem()).getCode().toString());
        bean.setJsjqbj(((DicationResBean) jsjq.getSelectedItem()).getCode().toString());//拒收拒签
        bean.setSgdj(SharedPreferencesUtils.getString(this, "SGDJ", ""));//事故等级
        bean.setJd("");
        bean.setWd("");
        if ("".equals(wsbh.getText().toString())) {
            ToastUtils.showToast(this, "请填写文书编号！");
            return null;
        }
        bean.setJdsbh(wsbh.getText().toString());
        String xzssjgStr = xzssjg.getText().toString().trim();
        SharedPreferencesUtils.saveString(this, "XZSSJG_SUMMARY", xzssjgStr);
        bean.setXzssjg(xzssjgStr);
        return bean;

    }

    @Override
    public void onBackPressed() {
        if (mPopupKeyboard.isShown()) {
            mPopupKeyboard.dismiss(this);
            return;
        }
        //        if (!"WarnBackDetails".equals(tag)) {
        //            Intent intent = new Intent(this, SummaryPunishmentDecisionActivity.class);
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
            if (mPopupKeyboard.isShown()) {
                mPopupKeyboard.dismiss(SummaryPunishmentDetailActivity.this);
            }
            ((SummaryPunishmentDetailPersenter) mPresenter).loadCarDatas(SummaryPunishmentDetailActivity.this, (
                    (DicationResBean) hpzl.getSelectedItem()).getCode(), hphm.getNumber());
        } else if (requestCode == MY_SCAN_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String id = data.getStringExtra("id");
            if (id != null && id.length() == 18 || id.length() == 13) {
                zjhm.setText(id);
            }
            if ("".equals(zjhm.getText().toString())) {
                ToastUtils.showToast(SummaryPunishmentDetailActivity.this, "证件号码不能为空！");
            } else {
                ((SummaryPunishmentDetailPersenter) mPresenter).loadDriverDatas(SummaryPunishmentDetailActivity.this, zjhm.getText().toString());
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

            SharedPreferencesUtils.saveString(this, "DLDM_SUMMARY", dl.getDldm());
            SharedPreferencesUtils.saveString(this, "DLMC_SUMMARY", dl.getDlmc());
            SharedPreferencesUtils.saveString(this, "XZQH_SUMMARY", xzqhstr);
            SharedPreferencesUtils.saveString(this, "LDDM_SUMMARY", ld.getLddm());
            SharedPreferencesUtils.saveString(this, "LDMC_SUMMARY", ld.getLdmc());


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
        Intent intent = new Intent(SummaryPunishmentDetailActivity.this, ScenCameraActivity.class);

        startActivityForResult(intent, HPHM_SCAN_REQUEST_CODE);
    }

}
