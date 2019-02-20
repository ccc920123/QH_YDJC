package com.scxd.lawqinghai.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.parkingwang.vehiclekeyboard.PopupKeyboard;
import com.parkingwang.vehiclekeyboard.ScenClick;
import com.parkingwang.vehiclekeyboard.view.InputView;
import com.scxd.idcardlibs.DriverScanActivity;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.CZJGAdpter;
import com.scxd.lawqinghai.adapter.PhotoAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.Md_cartype;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.LedgerDriverBean;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.request.WriteLegerBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.LedgerDetailsRespBean;
import com.scxd.lawqinghai.bean.response.LedgerListRespBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.common.Md_System_Datas;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.LedgerPersenter;
import com.scxd.lawqinghai.mvp.view.LedgerView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.CheckText;
import com.scxd.lawqinghai.widget.MyGrideView;
import com.scxd.lawqinghai.widget.ZoomImageViewGlide;
import com.scxd.lawqinghai.widget.checkpic.PictureSelectorConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @类名: ${type_name}
 * @功能描述: 台账录入
 * 	【是否有副驾驶员】为“1”时，【副驾驶证号】不能为空；scenClick
 * 	【号牌种类】为“41”时，【号牌号码】必须为“-”；
 * 	【车辆状态】为“2”时，【号牌种类】必须为“41”；
 * 	如果【检查车辆类型】=’04’,
 * 则“是否疲劳驾驶/安全设备是否合格/车辆轮胎花纹是否合格/是否系安全带/是否按规定粘贴反光标识/是否安装侧后防护装置/是否非法改装/是否超载/核定载质量/实际载质量”必须填写，如果【检查车辆类型】=’01’or’03’,
 * 则“疲劳驾驶/安全设备不合格/车辆轮胎花纹不合格/未系安全带/违法营运/车辆超员/核定载客数/实际载客数”必须填写，如果【检查车辆类型】=’02
 * ’，则“疲劳驾驶/安全设备是否合格/车辆轮胎花纹是否合格/是否系安全带/是否悬挂或喷涂警示标志/是否按指定时间行驶/是否按指定路线行驶/是否取得道路运输通行证”必须填写，如果【检查车辆类型】=’05’or’06’or’99
 * ’，则“疲劳驾驶/安全设备是否合格/车辆轮胎花纹是否合格/是否系安全带”必须填写；
 * 	【检查车辆类型】=’01’且【检查车辆子类型】=‘0101’时，“是否接驳车辆/班线频次/是否投保交强险/驶入站口/驶出站口/运行区域”必须填写；
 * 	【检查车辆类型】=’02’且【检查车辆子类型】=‘0102’时，“是否投保交强险/驶入站口/驶出站口”必须填写；
 * 	【检查车辆类型】=’02’时，“是否投保交强险/驶入站口/驶出站口/押运员姓名、押运员电话、押运员证件号码/危化品种类/危化品名称”必须填写；
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LedgerActivity extends BaseActivity implements LedgerView, ScenClick {
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
    @BindView(R.id.zdcllx)
    TextView zdcllx;
    @BindView(R.id.cllx)
    TextView cllx;
    @BindView(R.id.syxz)
    TextView syxz;
    @BindView(R.id.clpp)
    TextView clpp;
    @BindView(R.id.ccdjrq)
    TextView ccdjrq;
    @BindView(R.id.clzt)
    TextView clzt;
    @BindView(R.id.wfcs)
    TextView wfcs;
    @BindView(R.id.wfwcl)
    TextView wfwcl;
    @BindView(R.id.yqwjy)
    TextView yqwjy;
    @BindView(R.id.yqwbf)
    TextView yqwbf;
    @BindView(R.id.fzjg_car)
    TextView fzjgCar;
    @BindView(R.id.syr)
    TextView syr;
    @BindView(R.id.lxdh_car)
    TextView lxdhCar;
    @BindView(R.id.lxdz_car)
    TextView lxdzCar;
    @BindView(R.id.sfzh)
    EditText sfzh;
    @BindView(R.id.query_driver)
    Button queryDriver;
    @BindView(R.id.xm)
    TextView xm;
    @BindView(R.id.jszzt_driver)
    TextView jszzt_driver;
    @BindView(R.id.zjcx_driver)
    TextView zjcx_driver;
    @BindView(R.id.lxdh_driver)
    TextView lxdh_driver;
    @BindView(R.id.cclzrq_driver)
    TextView cclzrq_driver;
    @BindView(R.id.lxdz_driver)
    TextView lxdz_driver;
    @BindView(R.id.xb_driver)
    TextView xbDriver;
    @BindView(R.id.ljjf)
    TextView ljjf;
    @BindView(R.id.sfcf)
    TextView sfcf;
    @BindView(R.id.csrq_driver)
    TextView csrq_driver;
    @BindView(R.id.fzjg_driver)
    TextView fzjgDriver;
    //    @BindView(R.id.zjcx)
    //    TextView zjcx;
    @BindView(R.id.sfzjbf)
    TextView sfzjbf;
    @BindView(R.id.sfd)
    EditText sfd;
    @BindView(R.id.mdd)
    EditText mdd;
    @BindView(R.id.ywfjsy)
    CheckText ywfjsy;
    @BindView(R.id.gpszb) //GPS 装备情况
            Spinner gpszb;
    @BindView(R.id.sfjbc)
    CheckText sfjbc;
    @BindView(R.id.bxpc)
    Spinner bxpc;
    @BindView(R.id.sftbjqx)
    CheckText sftbjqx;
    @BindView(R.id.jcqkms)
    EditText jcqkms;
    @BindView(R.id.gridview)
    MyGrideView gridview;
    @BindView(R.id.tj_btn)
    Button tjBtn;

    List<DicationResBean> hpzlList;
    @BindView(R.id.wfyy)
    CheckText wfyy;
    @BindView(R.id.sjzzl)
    EditText sjzzl;
    @BindView(R.id.fjsysfz)
    EditText fjsysfz;
    @BindView(R.id.czqkms)
    EditText czqkms;
    @BindView(R.id.sfwzjs)
    CheckText sfwzjs;
    @BindView(R.id.qdystxz)
    CheckText qdystxz;
    @BindView(R.id.jcjg)
    Spinner jcjg;
    //    @BindView(R.id.sfxycl)
    //    CheckText sfxycl;
    @BindView(R.id.czjg)
    TextView czjg;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.sfzmhm_scan)
    ImageView sfzmhmScan;
    @BindView(R.id.clzt_check)
    Spinner clztCheck;
    @BindView(R.id.jccllx_check)
    Spinner jccllxCheck;
    @BindView(R.id.jcclzlx_check)
    Spinner jcclzlxCheck;
    @BindView(R.id.fourleLayout)
    TableLayout fourleLayout;//检查车型04
    @BindView(R.id.oneorthreeleLayout)
    TableLayout oneorthreeleLayout;//检查车型01\03
    @BindView(R.id.twoLayout)
    TableLayout twoLayout;//检查车型02
    @BindView(R.id.childmainLayout)
    TableLayout childmainLayout;//检查车型子类型主
    @BindView(R.id.twosLayout)
    TableLayout twosLayout;//检查车型02s
    @BindView(R.id.childotherLayout)
    TableLayout childotherLayout;//检查车型副
    @BindView(R.id.tabrowfjsysfz)
    TableRow tabrowfjsysfz;//副驾驶员身份证号
    @BindView(R.id.pljs)
    CheckText pljs;
    @BindView(R.id.aqsb)
    CheckText aqsb;
    @BindView(R.id.cllthw)
    CheckText cllthw;
    @BindView(R.id.jaqd)
    CheckText jaqd;
    @BindView(R.id.ztfgbs)
    CheckText ztfgbs;
    @BindView(R.id.azfhzz)
    CheckText azfhzz;
    @BindView(R.id.ffgz)
    CheckText ffgz;
    //    @BindView(R.id.sfcz)
    //    CheckText sfcz;
    @BindView(R.id.zks)
    EditText zks;
    @BindView(R.id.jclxzlbtab)
    TableRow jclxzlbtab;
    @BindView(R.id.xgjsbz)
    CheckText xgjsbz;
    @BindView(R.id.azdsj)
    CheckText azdsj;
    @BindView(R.id.azdlx)
    CheckText azdlx;
    @BindView(R.id.srzk)
    EditText srzk;
    @BindView(R.id.sczk)
    EditText sczk;
    @BindView(R.id.yydh)
    EditText yydh;
    @BindView(R.id.yysfz)
    EditText yysfz;
    @BindView(R.id.whpzl)
    Spinner whpzl;
    @BindView(R.id.whpmc)
    EditText whpmc;
    @BindView(R.id.yyxm)
    EditText yyxm;
    @BindView(R.id.yxqy)
    EditText yxqy;
    @BindView(R.id.imageview)
    ImageView imageview;


    private String user;
    private String userXm;
    private PhotoAdapter mPhotoAdapter;
    private String picPath;
    private static int REQUEST_ORIGINAL = 2;
    private static int REQUEST_MANIFEST = 3;
    private List<String> mList;
    private PopupKeyboard mPopupKeyboard;
    private final int HPHM_SCAN_REQUEST_CODE = 200;
    private int MY_SCAN_REQUEST_CODE = 300;
    private int count = 0;
    List<String> photos;

    private List<DicationResBean> clztMap = null;
    private List<Md_cartype> clztList = null;
    private Map<String, String> cllxMap = null;
    private Map<String, String> syxzMap = null;
    private Map<String, String> jszztMap = null;
    List<DicationResBean> jccllxList;
    List<DicationResBean> jcclzlxList;
    List<DicationResBean> czjgList;
    List<DicationResBean> GPSList;
    List<DicationResBean> bxpcList;
    List<DicationResBean> whpzlList;
    List<DicationResBean> jcclzlxList01;
    List<DicationResBean> jcclzlxList03;
    List<Md_cartype> zdaqList;
    private PopupWindow popView;
    CZJGAdpter czjg_Adapter;
    //储存处置结果的数据
    StringBuilder str;
    private String sfzhStr;
    private String fwzbhStr;

    private String tag = "";

    private LedgerListRespBean.DataBean.ListBean mListBean;

    @Override
    public BasePresenter getPresenter() {
        return new LedgerPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_ledger;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initInjector() {
        title.setText("台账录入");

        tag = getIntent().getStringExtra("TAG");
        
        if (tag.equals("Details")) {
            queryCar.setClickable(false);
            queryCar.setBackground(getResources().getDrawable(R.drawable.login_btn_gray));
            queryDriver.setClickable(false);
            queryDriver.setBackground(getResources().getDrawable(R.drawable.login_btn_gray));
            tjBtn.setClickable(false);
            tjBtn.setBackground(getResources().getDrawable(R.drawable.login_btn_gray));
            sfzmhmScan.setClickable(false);
            hphm.setClickable(false);
            jccllxCheck.setEnabled(false);
            jcclzlxCheck.setEnabled(false);
            jcjg.setEnabled(false);
            bxpc.setEnabled(false);
            clztCheck.setEnabled(false);
            mListBean = (LedgerListRespBean.DataBean.ListBean) getIntent().getSerializableExtra("LSH");
        }

        hpzlList = DictionaryManager.getInstance().getHpzl();
        jccllxList = DictionaryManager.getInstance().getJccllx();
        jcclzlxList = DictionaryManager.getInstance().getJcclzlx();
        czjgList = DictionaryManager.getInstance().getCzjg();
        GPSList = DictionaryManager.getInstance().getGpszbqk();
        zdaqList = Md_System_Datas.getZdaq();
        bxpcList = DictionaryManager.getInstance().getBxpc();
        whpzlList = DictionaryManager.getInstance().getWhpzl();
        user = SharedPreferencesUtils.getString(this, "USER", "");
        userXm = SharedPreferencesUtils.getString(this, "xm", "");
        fwzbhStr = SharedPreferencesUtils.getString(this, "FWZBH", "");
        ywfjsy.setValue(0, true);
        qdystxz.setValue(1);
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(this);
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(hphm, this);

    }

    @Override
    protected void initEventAndData() {
        try {
            File mFile = new File(Common.PHONE_PATH + "photo/");
            if (!mFile.exists()) {
                mFile.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cllxMap = new HashMap<>();
        syxzMap = new HashMap<>();
        jszztMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getSyxz()) {
            syxzMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getCllx()) {
            cllxMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getJszzt()) {
            jszztMap.put(bean.getCode(), bean.getName());
        }

        //        clztMap = new ArrayList<>();
        //        clztList = new ArrayList<>();
        clztList = Md_System_Datas.getClzt();

        clztMap = DictionaryManager.getInstance().getClzt();

        ArrayAdapter<Md_cartype> clztAdapter = new ArrayAdapter<Md_cartype>(this, android.R.layout
                .simple_spinner_item, clztList);
        clztAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clztCheck.setAdapter(clztAdapter);
        clztCheck.setSelection(0);

        ArrayAdapter<DicationResBean> adapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, hpzlList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hpzl.setAdapter(adapter);
        hpzl.setSelection(1);
        hpzl.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //41时表示无号牌
                String code = ((DicationResBean) adapterView.getSelectedItem()).getCode();
                if ("41".equals(code)) {
                    hphm.setEnabled(false);

                } else {
                    hphm.setEnabled(true);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //检测子类别


        //检验类型
        ArrayAdapter<DicationResBean> jccllxadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, jccllxList);
        jccllxadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jccllxCheck.setAdapter(jccllxadapter);
        jccllxCheck.setSelection(1);
        String code = ((DicationResBean) jccllxCheck.getSelectedItem()).getCode();
        if ("01".equals(code) || "03".equals(code)) {

            //显示检查子类别
            jclxzlbtab.setVisibility(View.VISIBLE);
            oneorthreeleLayout.setVisibility(View.VISIBLE);//显示01与03类型隐藏其他类型数据
            childmainLayout.setVisibility(View.GONE);
            childotherLayout.setVisibility(View.GONE);
            twosLayout.setVisibility(View.GONE);
            twoLayout.setVisibility(View.GONE);
            fourleLayout.setVisibility(View.GONE);

        } else {

            jclxzlbtab.setVisibility(View.GONE);

            if ("02".equals(code)) {

                oneorthreeleLayout.setVisibility(View.GONE);
                childmainLayout.setVisibility(View.VISIBLE);
                childotherLayout.setVisibility(View.GONE);
                twosLayout.setVisibility(View.VISIBLE);
                twoLayout.setVisibility(View.VISIBLE);
                fourleLayout.setVisibility(View.GONE);

            } else if ("04".equals(code)) {

                oneorthreeleLayout.setVisibility(View.GONE);
                childmainLayout.setVisibility(View.GONE);
                childotherLayout.setVisibility(View.GONE);
                twosLayout.setVisibility(View.GONE);
                twoLayout.setVisibility(View.GONE);
                fourleLayout.setVisibility(View.VISIBLE);

            } else {

                oneorthreeleLayout.setVisibility(View.GONE);//全部隐藏
                childmainLayout.setVisibility(View.GONE);
                childotherLayout.setVisibility(View.GONE);
                twosLayout.setVisibility(View.GONE);
                twoLayout.setVisibility(View.GONE);
                fourleLayout.setVisibility(View.GONE);
            }
        }

        //GPS装备
        ArrayAdapter<DicationResBean> gpsadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, GPSList);
        gpsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gpszb.setAdapter(gpsadapter);
        gpszb.setSelection(0);
        //重大案情
        ArrayAdapter<Md_cartype> zdaqAdapter = new ArrayAdapter<Md_cartype>(this, android.R.layout
                .simple_spinner_item, zdaqList);
        zdaqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jcjg.setAdapter(zdaqAdapter);
        jcjg.setSelection(0);
        //班线频次
        ArrayAdapter<DicationResBean> bxpcAdapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, bxpcList);
        bxpcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bxpc.setAdapter(bxpcAdapter);
        bxpc.setSelection(0);
        //危化品种类
        ArrayAdapter<DicationResBean> whpzlAdapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, whpzlList);
        whpzlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whpzl.setAdapter(whpzlAdapter);
        whpzl.setSelection(0);


        mList = new ArrayList<>();
        if (tag.equals("Details")) {
            ((LedgerPersenter) mPresenter).queryDatas(this, mListBean.getTzlsh());
            imageview.setVisibility(View.GONE);
        } else {
            imageview.setVisibility(View.VISIBLE);
            //处理图片
            mList = LogUtil.getAllFiles(Common.PHONE_PATH + "photo/", ".jpg");
            mPhotoAdapter = new PhotoAdapter(this, 0);
            gridview.setAdapter(mPhotoAdapter);
            if (mList != null && mList.size() > 0) {
                mPhotoAdapter.setList(mList);
            }
            mPhotoAdapter.setOnItemClickListener(new PhotoAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position, Object testBean) {
                    showPopu(mList, position, 0);
                }
            });

            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mList.size() >= 3) {
                        ToastUtils.showToast(LedgerActivity.this, "只能拍三张");
                    } else {
                        picPath = Common.PHONE_PATH + "photo/" + System.currentTimeMillis() + ".jpg";
                        checkPremission();
                    }
                }
            });
        }
        
        jccllxCheck.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String code = ((DicationResBean) adapterView.getSelectedItem()).getCode();
                if ("01".equals(code) || "03".equals(code)) {

                    //显示检查子类别
                    jclxzlbtab.setVisibility(View.VISIBLE);
                    oneorthreeleLayout.setVisibility(View.VISIBLE);//显示01与03类型隐藏其他类型数据
                    childmainLayout.setVisibility(View.GONE);
                    childotherLayout.setVisibility(View.GONE);
                    twosLayout.setVisibility(View.GONE);
                    twoLayout.setVisibility(View.GONE);
                    fourleLayout.setVisibility(View.GONE);

                    if (!tag.equals("Details")) {
                        final ArrayAdapter<DicationResBean> jcclzlxadapter = new ArrayAdapter<DicationResBean>
                                (LedgerActivity.this, android.R.layout.simple_spinner_item, "01".equals(code) ? 
                                        jcclzlxList01 : jcclzlxList03);
                        jcclzlxadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        jcclzlxCheck.setAdapter(jcclzlxadapter);
                        jcclzlxCheck.setSelection(0);
                    }
                    if (null == jcclzlxCheck.getSelectedItem()){
                        return;
                    }
                    //判断子类型是否是0101，此时需要选择
                    String jcclzlxcode = ((DicationResBean) jcclzlxCheck.getSelectedItem()).getCode();
                    //当子类型为0101需要重新组装一下检查项目

                    if ("0101".equals(jcclzlxcode) && jclxzlbtab.getVisibility() == View.VISIBLE) {
                        oneorthreeleLayout.setVisibility(View.VISIBLE);
                        childmainLayout.setVisibility(View.VISIBLE);
                        childotherLayout.setVisibility(View.VISIBLE);
                        twosLayout.setVisibility(View.GONE);
                        twoLayout.setVisibility(View.GONE);
                        fourleLayout.setVisibility(View.GONE);
                    } else {

                        jclxzlbtab.setVisibility(View.VISIBLE);
                        oneorthreeleLayout.setVisibility(View.VISIBLE);//显示01与03类型隐藏其他类型数据
                        childmainLayout.setVisibility(View.GONE);
                        childotherLayout.setVisibility(View.GONE);
                        twosLayout.setVisibility(View.GONE);
                        twoLayout.setVisibility(View.GONE);
                        fourleLayout.setVisibility(View.GONE);


                    }

                } else {

                    jclxzlbtab.setVisibility(View.GONE);

                    if ("02".equals(code)) {

                        oneorthreeleLayout.setVisibility(View.GONE);
                        childmainLayout.setVisibility(View.VISIBLE);
                        childotherLayout.setVisibility(View.GONE);
                        twosLayout.setVisibility(View.VISIBLE);
                        twoLayout.setVisibility(View.VISIBLE);
                        fourleLayout.setVisibility(View.GONE);

                    } else if ("04".equals(code)) {

                        oneorthreeleLayout.setVisibility(View.GONE);
                        childmainLayout.setVisibility(View.GONE);
                        childotherLayout.setVisibility(View.GONE);
                        twosLayout.setVisibility(View.GONE);
                        twoLayout.setVisibility(View.GONE);
                        fourleLayout.setVisibility(View.VISIBLE);

                    } else {

                        oneorthreeleLayout.setVisibility(View.GONE);//全部隐藏
                        childmainLayout.setVisibility(View.GONE);
                        childotherLayout.setVisibility(View.GONE);
                        twosLayout.setVisibility(View.GONE);
                        twoLayout.setVisibility(View.GONE);
                        fourleLayout.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //重新组装检查子类别
        String jccllxCode = ((DicationResBean) jccllxCheck.getSelectedItem()).getCode();
        jcclzlxList01 = new ArrayList<>();
        jcclzlxList03 = new ArrayList<>();
        for (int i = 0; i < jcclzlxList.size(); i++) {

            if ("0101".equals(jcclzlxList.get(i).getCode()) || "0102".equals(jcclzlxList.get(i).getCode()) || "0103"
                    .equals(jcclzlxList.get(i).getCode())) {


                jcclzlxList01.add(jcclzlxList.get(i));
            } else if ("0301".equals(jcclzlxList.get(i).getCode()) || "0302".equals(jcclzlxList.get(i).getCode()) || 
                    "0303".equals(jcclzlxList.get(i).getCode()) || "0304".equals(jcclzlxList.get(i).getCode())) {


                jcclzlxList03.add(jcclzlxList.get(i));
            }

        }
        if (!tag.equals("Details")) {
            if ("01".equals(jccllxCode)) {

                final ArrayAdapter<DicationResBean> jcclzlxadapter = new ArrayAdapter<DicationResBean>(this, android
                        .R.layout.simple_spinner_item, jcclzlxList01);

                jcclzlxadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                jcclzlxCheck.setAdapter(jcclzlxadapter);
                jcclzlxCheck.setSelection(0);
            } else if ("03".equals(jccllxCode)) {

                final ArrayAdapter<DicationResBean> jcclzlxadapter = new ArrayAdapter<DicationResBean>(this, android
                        .R.layout.simple_spinner_item, jcclzlxList03);
                jcclzlxadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                jcclzlxCheck.setAdapter(jcclzlxadapter);
                jcclzlxCheck.setSelection(0);

            } else {
                final ArrayAdapter<DicationResBean> jcclzlxadapter = new ArrayAdapter<DicationResBean>(this, android
                        .R.layout.simple_spinner_item, jcclzlxList);
                jcclzlxadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                jcclzlxCheck.setAdapter(jcclzlxadapter);
                jcclzlxCheck.setSelection(0);
            }
            //判断子类型是否是0101，此时需要选择
            String jcclzlxcode = ((DicationResBean) jcclzlxCheck.getSelectedItem()).getCode();
            //当子类型为0101需要重新组装一下检查项目
            if ("0101".equals(jcclzlxcode) && jclxzlbtab.getVisibility() == View.VISIBLE) {
                oneorthreeleLayout.setVisibility(View.VISIBLE);
                childmainLayout.setVisibility(View.VISIBLE);
                childotherLayout.setVisibility(View.VISIBLE);
                twosLayout.setVisibility(View.GONE);
                twoLayout.setVisibility(View.GONE);
                fourleLayout.setVisibility(View.GONE);
            }
        }
        jcclzlxCheck.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String jcclzlxcode = ((DicationResBean) adapterView.getSelectedItem()).getCode();
                if ("0101".equals(jcclzlxcode)) {

                    oneorthreeleLayout.setVisibility(View.VISIBLE);
                    childmainLayout.setVisibility(View.VISIBLE);
                    childotherLayout.setVisibility(View.VISIBLE);
                    twosLayout.setVisibility(View.GONE);
                    twoLayout.setVisibility(View.GONE);
                    fourleLayout.setVisibility(View.GONE);
                } else {
                    String code = ((DicationResBean) jccllxCheck.getSelectedItem()).getCode();
                    if ("01".equals(code) || "03".equals(code)) {

                        oneorthreeleLayout.setVisibility(View.VISIBLE);//显示01与03类型隐藏其他类型数据
                        childmainLayout.setVisibility(View.GONE);
                        childotherLayout.setVisibility(View.GONE);
                        twosLayout.setVisibility(View.GONE);
                        twoLayout.setVisibility(View.GONE);
                        fourleLayout.setVisibility(View.GONE);

                    } else {
                        if ("02".equals(code)) {

                            oneorthreeleLayout.setVisibility(View.GONE);
                            childmainLayout.setVisibility(View.VISIBLE);
                            childotherLayout.setVisibility(View.GONE);
                            twosLayout.setVisibility(View.VISIBLE);
                            twoLayout.setVisibility(View.VISIBLE);
                            fourleLayout.setVisibility(View.GONE);

                        } else if ("04".equals(code)) {

                            oneorthreeleLayout.setVisibility(View.GONE);
                            childmainLayout.setVisibility(View.GONE);
                            childotherLayout.setVisibility(View.GONE);
                            twosLayout.setVisibility(View.GONE);
                            twoLayout.setVisibility(View.GONE);
                            fourleLayout.setVisibility(View.VISIBLE);

                        } else {

                            oneorthreeleLayout.setVisibility(View.GONE);//全部隐藏
                            childmainLayout.setVisibility(View.GONE);
                            childotherLayout.setVisibility(View.GONE);
                            twosLayout.setVisibility(View.GONE);
                            twoLayout.setVisibility(View.GONE);
                            fourleLayout.setVisibility(View.GONE);
                        }
                    }


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       

    }

    //判断权限
    private void checkPremission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_MANIFEST);
                return;
            } else {
                //                openCamera();//调用具体方法
                PictureSelectorConfig.initMultiConfig(this, 3 - (mList.size()));
            }
        } else {
            PictureSelectorConfig.initMultiConfig(this, 3 - (mList.size()));
            //            openCamera();//调用具体方法
        }
    }

    private void openCamera() {  //调用相机拍照
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= 24) {
            intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent2.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, "com.scxd.lawqinghai" +
                    ".fileprovider", new File(picPath)));
        } else {
            File file = new File(picPath);
            Uri uri = Uri.fromFile(file);
            //为拍摄的图片指定一个存储的路径
            intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        startActivityForResult(intent2, REQUEST_ORIGINAL);
    }

    @OnClick({R.id.back, R.id.query_car, R.id.query_driver, R.id.tj_btn, R.id.sfzmhm_scan, R.id.czjg, R.id.ywfjsy})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.back:

                onBackPressed();
                break;
            case R.id.query_car:
                if (mPopupKeyboard.isShown()) {
                    mPopupKeyboard.dismiss(LedgerActivity.this);
                }

                if (!ButtonTools.isFastDoubleClick(1500)) {
                    String hphmStr = hphm.getNumber();
                    String hpzlStr = ((DicationResBean) hpzl.getSelectedItem()).getCode().toString();
                    ((LedgerPersenter) mPresenter).loadCarDatas(this, hpzlStr, hphmStr);
                }
                break;
            case R.id.query_driver:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    sfzhStr = sfzh.getText().toString().trim();
                    ((LedgerPersenter) mPresenter).loadDriverDatas(this, sfzhStr);
                }
                break;


            case R.id.ywfjsy:
                //有无副驾驶人员
                if (ywfjsy.getValue() == 1) {
                    tabrowfjsysfz.setVisibility(View.VISIBLE);


                } else {

                    tabrowfjsysfz.setVisibility(View.GONE);
                }

                break;
            case R.id.czjg:

                if (popView != null) {
                    popView.dismiss();
                    popView = null;
                }
                ListView listView = new ListView(this);
                popView = new PopupWindow(listView, czjg.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT, false);
                Button ltggButton = new Button(this);
                ltggButton.setText("确定选择");
                ltggButton.setOnClickListener(click);
                hideSoftInputPanel(czjg);
                czjg_Adapter = new CZJGAdpter(czjgList, this, popView, czjg);
                // dropDownAdapter = new MyAdapter(context,
                // MD_ListItem.Get_colors()); // 车身颜色pop弹出

                listView.setOnItemClickListener(item_click);
                listView.addHeaderView(ltggButton);
                listView.setAdapter(czjg_Adapter);
                listView.setCacheColorHint(Color.parseColor("#EBEBEB"));
                listView.setBackgroundColor(Color.parseColor("#EBEBEB"));

                popView.setFocusable(true);
                popView.setOutsideTouchable(true);
                popView.showAsDropDown(czjg);


                break;
            case R.id.tj_btn:

                if (!ButtonTools.isFastDoubleClick(1500)) {
                    WriteLegerBean object = new WriteLegerBean();
                    if ("".equals(lxdh_driver.getText().toString())) {
                        ToastUtils.showToast(LedgerActivity.this, "请录入联系电话");
                        break;
                    }
                    object.setZlxdh(lxdh_driver.getText().toString().trim());
                    if ("41".equals(((DicationResBean) hpzl.getSelectedItem()).getCode().toString())) {
                        object.setHphm("-");
                    } else {
                        if ("".equals(hphm.getNumber())) {
                            ToastUtils.showToast(LedgerActivity.this, "请填写号牌号码");
                            break;
                        }
                        object.setHphm(hphm.getNumber());
                    }
                    String jccllxCode = ((DicationResBean) jccllxCheck.getSelectedItem()).getCode();
                    object.setJccllx(jccllxCode);
                    String clzt = ((Md_cartype) clztCheck.getSelectedItem()).getName().toString();
                    if ("2".equals(clzt)) {
                        object.setHphm("-");

                    }
                    object.setClzt(clzt);
                    if ("2".equals(clzt)) {
                        object.setHpzl("41");
                    } else {
                        object.setHpzl(((DicationResBean) hpzl.getSelectedItem()).getCode().toString());
                    }
                    //                    object.setJszh(sfzh.getText().toString());
                    //                    object.setSfzjbf(sfzjbf.getText().toString());
                    if ("".equals(sfd.getText().toString())) {
                        ToastUtils.showToast(LedgerActivity.this, "请录入始发地");
                        break;
                    }
                    object.setSfd(sfd.getText().toString());
                    if ("".equals(mdd.getText().toString())) {
                        ToastUtils.showToast(LedgerActivity.this, "请录入目的地");
                        break;
                    }
                    object.setMdd(mdd.getText().toString());
                    object.setFwzbh(fwzbhStr);//执法站编号我这边传“”后台去自动匹配
                    if ("01".equals(jccllxCode) || "03".equals(jccllxCode)) {//只有当检验类型为01和03才有检验子类型
                        object.setJcclzlx(((DicationResBean) jcclzlxCheck.getSelectedItem()).getCode());
                    } else {
                        object.setJcclzlx("");
                    }
                    if ("".equals(sfzh.getText().toString())) {
                        ToastUtils.showToast(LedgerActivity.this, "请在驾驶员信息中录入身份证号");
                        break;
                    }
                    object.setZjszh(sfzh.getText().toString());//主驾驶证号

                    object.setJcsj(Date_U.getCurrentTime2());//检测时间
                    object.setJcmj(userXm);//检查民警
                    object.setUser(user);//登录账号
                    object.setJcjg(((Md_cartype) jcjg.getSelectedItem()).getName());//是否重大按情
                    object.setPljs(pljs.getValue() + "");//疲劳驾驶
                    object.setAqsb(aqsb.getValue() + "");//安全设备情况
                    object.setCllthw(cllthw.getValue() + "");//轮胎花纹
                    object.setJaqd(jaqd.getValue() + "");//驾驶人安全带
                    if ("04".equals(jccllxCode)) {
                        object.setZtfgbs(ztfgbs.getValue() + "");
                        object.setAzfhzz(azfhzz.getValue() + "");
                        object.setFfgz(ffgz.getValue() + "");
                        if ("".equals(sjzzl.getText().toString())) {
                            ToastUtils.showToast(LedgerActivity.this, "请录入实际载质量");
                            break;
                        }
                        object.setSjzzl(sjzzl.getText().toString());
                    } else if ("01".equals(jccllxCode) || "03".equals(jccllxCode)) {

                        object.setWfyy(wfyy.getValue() + "");
                        if ("".equals(zks.getText().toString())) {
                            ToastUtils.showToast(LedgerActivity.this, "请录入实际载客数");
                            break;
                        }
                        object.setZks(zks.getText().toString());
                        if ("01".equals(jccllxCode) && ("0101".equals(((DicationResBean) jcclzlxCheck.getSelectedItem
                                ()).getCode()))) {
                            object.setSfjbc(sfjbc.getValue() + "");
                            object.setBxpc(((DicationResBean) bxpc.getSelectedItem()).getCode());

                            if ("".equals(yxqy.getText().toString())) {
                                ToastUtils.showToast(LedgerActivity.this, "请录入运行区域");
                                break;
                            }

                            object.setYxqy(yxqy.getText().toString());

                            object.setSftbjqx(sftbjqx.getValue() + "");
                            if ("".equals(srzk.getText().toString())) {
                                ToastUtils.showToast(LedgerActivity.this, "请录入驶入站口");
                                break;
                            }
                            object.setSrzk(srzk.getText().toString());
                            if ("".equals(sczk.getText().toString())) {
                                ToastUtils.showToast(LedgerActivity.this, "请录入驶出站口");
                                break;
                            }
                            object.setSczk(sczk.getText().toString());

                        }

                    } else if ("02".equals(jccllxCode)) {
                        object.setSftbjqx(sftbjqx.getValue() + "");
                        if ("".equals(srzk.getText().toString())) {
                            ToastUtils.showToast(LedgerActivity.this, "请录入驶入站口");
                            break;
                        }
                        object.setSrzk(srzk.getText().toString());
                        if ("".equals(sczk.getText().toString())) {
                            ToastUtils.showToast(LedgerActivity.this, "请录入驶出站口");
                            break;
                        }
                        object.setSczk(sczk.getText().toString());

                        object.setXgjsbz(xgjsbz.getValue() + "");
                        object.setAzdsj(azdsj.getValue() + "");
                        object.setAzdlx(azdlx.getValue() + "");
                        object.setQdystxz(qdystxz.getValue() + "");
                        if ("".equals(yyxm.getText().toString())) {
                            ToastUtils.showToast(LedgerActivity.this, "请录入押运员姓名");
                            break;
                        }
                        object.setYyxm(yyxm.getText().toString());
                        if ("".equals(yydh.getText().toString())) {
                            ToastUtils.showToast(LedgerActivity.this, "请录入押运员电话");
                            break;
                        }
                        object.setYydh(yydh.getText().toString());
                        if ("".equals(yysfz.getText().toString())) {
                            ToastUtils.showToast(LedgerActivity.this, "请录入押运员身份证");
                            break;
                        }
                        object.setYysfz(yysfz.getText().toString());
                        object.setWhpzl(((DicationResBean) whpzl.getSelectedItem()).getCode());
                        if ("".equals(whpmc.getText().toString())) {
                            ToastUtils.showToast(LedgerActivity.this, "请录入危化品名称");
                            break;
                        }
                        object.setWhpmc(whpmc.getText().toString());


                    }
                    object.setJcqkms(jcqkms.getText().toString());
                    object.setSsbmbh(SharedPreferencesUtils.getString(this, "SSBMBH", ""));

                    //                    object.setSfyfjsy(ywfjsy.getValue() + "");
                    //                    object.setSfjbc(sfjbc.getValue() + "");
                    //
                    //                    object.setSftbjqx(sftbjqx.getValue() + "");
                    //                    object.setWfyy(wfyy.getValue() + "");//是否违法运营
                    //                    object.setSjzzl(sjzzl.getText().toString());//实际载质量
                    //                    object.setSfwzjs(sfwzjs.getValue() + "");//是否无证驾驶
                    //                    object.setQdystxz(qdystxz.getValue() + "");//是否取得道路运输通行证
                    ////                    object.setSfxycl(sfxycl.getValue() + "");//是否嫌疑车辆
                    //                    object.setCljg(str.toString());//处置结果
                    //                    object.setCzqkms(czqkms.getText().toString());//处置描述
                    //                    object.setFjszh(fjsysfz.getText().toString());//副驾驶证号
                    //                    object.setGps(((DicationResBean) gpszb.getSelectedItem()).getCode());//gps 
                    // 装备情况
                    //
                    //
                    //                    object.setZtfgbs(ztfgbs.getValue() + "");//反关标识
                    //                    object.setAzfhzz(azfhzz.getValue() + "");//侧后制
                    //                    object.setFfgz(ffgz.getValue() + "");//非法改装
                    //                    // sfcz; 是否超载
                    //                    object.setZks(zks.getText().toString());//载客数


                    ((LedgerPersenter) mPresenter).commitDatas(this, object);


                }
                break;
            case R.id.sfzmhm_scan:

                if (!ButtonTools.isFastDoubleClick(1500)) {
                    intent = new Intent(this, DriverScanActivity.class);
                    startActivityForResult(intent, MY_SCAN_REQUEST_CODE);
                }
                break;
        }
    }

    private View.OnClickListener click = new View.OnClickListener() {

        @SuppressWarnings("static-access")
        @Override
        public void onClick(View v) {
            Map<Integer, String> dataMap = czjg_Adapter.getCheckDate();

            if (str == null) {
                str = new StringBuilder();
            } else {
                str = null;
                str = new StringBuilder();
            }
            if (czjg_Adapter.getCheckDate().size() > 0) {
                Set<Integer> keyset = dataMap.keySet();

                for (int i : keyset) {
                    if ("".equals(str.toString())) {
                        str.append(dataMap.get(i));
                    } else {
                        str.append(",");
                        str.append(dataMap.get(i));
                    }
                }

                czjg.setText(str.toString());
            }

            popView.dismiss();

        }
    };
    private AdapterView.OnItemClickListener item_click = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            czjg.setText(((DicationResBean) czjg_Adapter.getItem(position)).getName());
            popView.dismiss();
        }
    };

    public void hideSoftInputPanel(TextView editText) {
        if (editText == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) this.getApplicationContext().getSystemService(Activity
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        if (mPopupKeyboard.isShown()) {
            mPopupKeyboard.dismiss(this);
            return;
        }
        Intent intent = new Intent(this, LedgerListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        for (int i = 0; i < mList.size(); i++) {
            File file = new File(mList.get(i));
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                file.delete();
            }

        }

        super.onDestroy();
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
    public void queryCar(LedgerQueyCarReqBean.DataBean carBean) {

        zdcllx.setText(cllxMap.get(carBean.getZdcllx()));
        cllx.setText(cllxMap.get(carBean.getCllx()));
        syxz.setText(syxzMap.get(carBean.getSyxz()));
        clpp.setText(carBean.getClpp());
        ccdjrq.setText(carBean.getCcdjrq());

        for (DicationResBean clztBean : clztMap) {
            if (carBean.getClzt().equals(clztBean.getCode())) {
                clzt.setText(clztBean.getName());
                if (carBean.getClzt().equals("A")) {
                    clzt.setTextColor(Color.BLACK);
                } else {
                    clzt.setTextColor(Color.RED);
                }
            }
        }

        wfcs.setText(carBean.getWfcs());
        //        wfwcl.setText(carBean.getwf); //违法未处理

        yqwjy.setText(carBean.getYqwjy());
        yqwbf.setText(carBean.getYqwbf());
        fzjgCar.setText(carBean.getFzjg());
        syr.setText(carBean.getJdcsyr());
        lxdhCar.setText(carBean.getLxdh());
        lxdzCar.setText(carBean.getLxdz());


    }

    @Override
    public void queryDriver(Object bean) {
        LedgerDriverBean.DataBean ledgerBean = (LedgerDriverBean.DataBean) bean;
        xm.setText(ledgerBean.getXm());
        csrq_driver.setText(ledgerBean.getCsrq());
        sfcf.setText(ledgerBean.getSfcf());
        ljjf.setText(ledgerBean.getLjjf());
        fzjgDriver.setText(ledgerBean.getFzjg());
        sfzhStr = ledgerBean.getSfzmhm();
        if (null == sfzhStr || "".equals(sfzhStr)) {
            xbDriver.setText(""); //性别
        } else {

            xbDriver.setText(getxb(sfzhStr)); //性别
        }
        zjcx_driver.setText(ledgerBean.getZjcx());
        jszzt_driver.setText(getJszzt(ledgerBean.getJszzt()));
        lxdz_driver.setText(ledgerBean.getLxzsxxdz());
        lxdh_driver.setText(ledgerBean.getLxdh());
        cclzrq_driver.setText(ledgerBean.getCclzrq());

        if (tag.equals("Details")) {
            ((LedgerPersenter) mPresenter).loadCarDatas(this, mListBean.getHpzl(), mListBean.getHphm());
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
     * 判断信息
     */
    private String getxb(String sfzh) {

        String xbstr = sfzh.substring(sfzh.length() - 2, sfzh.length() - 1);
        int xb = Integer.parseInt(xbstr);

        //        LogUtil.open().appendMethodB("性别:" + xb + "\n");
        return xb % 2 == 0 ? "女" : "男";
    }

    @Override
    public void loadCommit(String id) {
        photos = LogUtil.getAllFiles(Common.PHONE_PATH + "photo/", ".jpg");
        if (null != photos && photos.size() > 0) {
            count = 0;
            String user = SharedPreferencesUtils.getString(LedgerActivity.this, "USER", "");
            ((LedgerPersenter) mPresenter).commitPhotos(LedgerActivity.this, "uploadPhoto", user, id, "7003", photos);
        } else {

            if (count == photos.size()) {
                Intent intent = new Intent(this, LedgerListActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }

    @Override
    public void commitPhotos(String message) {
        count++;

        ToastUtils.showToast(this, message);
        if (count == photos.size()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    //照片过后上传

    @Override
    public void showFailed(String message) {
        ToastUtils.showToast(this, R.string.NET_WORK_FAILED);
    }

    @Override
    public void queryDatas(final LedgerDetailsRespBean.DataBean bean) {
        if (null != bean.getHpzl()) {
            for (int i = 0; i < hpzlList.size(); i++) {
                if (bean.getHpzl().equals(hpzlList.get(i).getCode())) {
                    hpzl.setSelection(i);
                    break;
                }
            }
        }
        hphm.updateNumber(bean.getHphm());
        sfzh.setText(bean.getZjszh());
        
        sfd.setText(bean.getSfd());
        mdd.setText(bean.getMdd());
        if (null != bean.getJcjg()) {
            for (int i = 0; i < zdaqList.size(); i++) {
                if (bean.getJcjg().equals(zdaqList.get(i).getName())) {
                    jcjg.setSelection(i);
                    break;
                }
            }
        }
        pljs.setValue(Integer.parseInt(bean.getPljs()));
        aqsb.setValue(Integer.parseInt(bean.getAqsb()));
        cllthw.setValue(Integer.parseInt(bean.getCllthw()));
        jaqd.setValue(Integer.parseInt(bean.getJaqd()));
        if (null != bean.getClzt()) {
            for (int i = 0; i < clztList.size(); i++) {
                if (bean.getClzt().equals(clztList.get(i).getName())) {
                    clztCheck.setSelection(i);
                    break;
                }
            }
        }
        jcqkms.setText(bean.getJcqkms());
        
        String code = bean.getJccllx();

        if (null != code) {
            if ("01".equals(code) || "03".equals(code)){

                final ArrayAdapter<DicationResBean> jcclzlxadapter = new ArrayAdapter<DicationResBean>(LedgerActivity
                        .this, android.R.layout.simple_spinner_item, "01".equals(code) ? jcclzlxList01 : jcclzlxList03);
                jcclzlxadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                jcclzlxCheck.setAdapter(jcclzlxadapter);
                
                if (null != bean.getJcclzlx()) {
                    if ("01".equals(code)) {
                        for (int i = 0; i < jcclzlxList01.size(); i++) {
                            if (bean.getJcclzlx().equals(jcclzlxList01.get(i).getCode())) {
                                jcclzlxCheck.setSelection(i);
                                break;
                            }
                        }
                    } else {
                        for (int i = 0; i < jcclzlxList03.size(); i++) {
                            if (bean.getJcclzlx().equals(jcclzlxList03.get(i).getCode())) {
                                jcclzlxCheck.setSelection(i);
                                break;
                            }
                        }
                    }
                }
            }
            
            for (int i = 0; i < jccllxList.size(); i++) {
                if (bean.getJccllx().equals(jccllxList.get(i).getCode())) {
                    jccllxCheck.setSelection(i);
                    break;
                }
            }
        }
        
        if ("01".equals(code) || "03".equals(code)) {

            //显示检查子类别
            jclxzlbtab.setVisibility(View.VISIBLE);
            oneorthreeleLayout.setVisibility(View.VISIBLE);//显示01与03类型隐藏其他类型数据
            childmainLayout.setVisibility(View.GONE);
            childotherLayout.setVisibility(View.GONE);
            twosLayout.setVisibility(View.GONE);
            twoLayout.setVisibility(View.GONE);
            fourleLayout.setVisibility(View.GONE);

            
            //判断子类型是否是0101，此时需要选择
            String jcclzlxcode = bean.getJcclzlx();
            //当子类型为0101需要重新组装一下检查项目
            oneorthreeleLayout.setVisibility(View.VISIBLE);//显示01与03类型隐藏其他类型数据
            wfyy.setValue(Integer.parseInt(bean.getWfyy()));
            zks.setText(bean.getZks());

            twosLayout.setVisibility(View.GONE);
            twoLayout.setVisibility(View.GONE);
            fourleLayout.setVisibility(View.GONE);
            if ("0101".equals(jcclzlxcode) && jclxzlbtab.getVisibility() == View.VISIBLE) {
                childmainLayout.setVisibility(View.VISIBLE);
                childotherLayout.setVisibility(View.VISIBLE);
                sftbjqx.setValue(Integer.parseInt(bean.getSftbjqx()));
                srzk.setText(bean.getSrzk());
                sczk.setText(bean.getSczk());
                sfjbc.setValue(Integer.parseInt(bean.getSfjbc()));
                if (null != bean.getBxpc()) {
                    for (int i = 0; i < bxpcList.size(); i++) {
                        if (bean.getBxpc().equals(bxpcList.get(i).getCode())) {
                            bxpc.setSelection(i);
                            break;
                        }
                    }
                }
                yxqy.setText(bean.getYxqy());
            } else {
                childmainLayout.setVisibility(View.GONE);
                childotherLayout.setVisibility(View.GONE);
            }

        } else {

            jclxzlbtab.setVisibility(View.GONE);
            oneorthreeleLayout.setVisibility(View.GONE);
            childotherLayout.setVisibility(View.GONE);

            if ("02".equals(code)) {

                childmainLayout.setVisibility(View.VISIBLE);
                sftbjqx.setValue(Integer.parseInt(bean.getSftbjqx()));
                srzk.setText(bean.getSrzk());
                sczk.setText(bean.getSczk());
                twosLayout.setVisibility(View.VISIBLE);
                yydh.setText(bean.getYydh());
                yysfz.setText(bean.getYysfz());
                if (null != bean.getWhpzl()) {
                    for (int i = 0; i < whpzlList.size(); i++) {
                        if (bean.getWhpzl().equals(whpzlList.get(i).getCode())) {
                            whpzl.setSelection(i);
                            break;
                        }
                    }
                }
                whpmc.setText(bean.getWhpmc());
                yyxm.setText(bean.getYyxm());
                twoLayout.setVisibility(View.VISIBLE);
                xgjsbz.setValue(Integer.parseInt(bean.getXgjsbz()));
                azdsj.setValue(Integer.parseInt(bean.getAzdsj()));
                azdlx.setValue(Integer.parseInt(bean.getAzdlx()));
                qdystxz.setValue(Integer.parseInt(bean.getQdystxz()));
                fourleLayout.setVisibility(View.GONE);
            } else if ("04".equals(code)) {
                childmainLayout.setVisibility(View.GONE);
                twosLayout.setVisibility(View.GONE);
                twoLayout.setVisibility(View.GONE);
                fourleLayout.setVisibility(View.VISIBLE);
                ztfgbs.setValue(Integer.parseInt(bean.getZtfgbs()));
                azfhzz.setValue(Integer.parseInt(bean.getAzfhzz()));
                ffgz.setValue(Integer.parseInt(bean.getFfgz()));
                sjzzl.setText(bean.getSjzzl());
            } else {

                childmainLayout.setVisibility(View.GONE);
                twosLayout.setVisibility(View.GONE);
                twoLayout.setVisibility(View.GONE);
                fourleLayout.setVisibility(View.GONE);
            }
        }
        if (null != bean.getPhotos() && bean.getPhotos().size() > 0) {
            mPhotoAdapter = new PhotoAdapter(this, 2);
            gridview.setAdapter(mPhotoAdapter);
            mPhotoAdapter.setList(bean.getPhotos());
            mPhotoAdapter.setOnItemClickListener(new PhotoAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position, Object testBean) {
                    showPopu(bean.getPhotos(), position, 2);
                }
            });
        }
        ((LedgerPersenter) mPresenter).loadDriverDatas(this, bean.getZjszh());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_MANIFEST) {
            //就像onActivityResult一样这个地方就是判断你是从哪来的。
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                //                openCamera();
                PictureSelectorConfig.initMultiConfig(this, 3 - (mList.size()));
            } else {
                // Permission Denied
                ToastUtils.showToast(LedgerActivity.this, R.string.ERROR_TIPS_02);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //        if (requestCode == REQUEST_ORIGINAL) {
        //            File file = new File(picPath);
        //            if (file.isFile()) {
        //                FileInputStream fis = null;
        //                try {
        //                    // 把图片转化为字节流
        //                    fis = new FileInputStream(picPath);
        //                    BitmapFactory.Options options = new BitmapFactory.Options();
        //                    // 当inJustDecodeBounds设为true时,不会加载图片仅获取图片尺寸信息
        //                    options.inJustDecodeBounds = true;
        //                    options.inSampleSize = 4;   // 找到合适的倍率
        //                    options.inJustDecodeBounds = false;
        //                    Bitmap bitmap = BitmapFactory.decodeStream(fis, null, options);
        //                    BitmapUtil.saveBitmap(bitmap, picPath);
        //
        //                    mList.add(picPath);
        //                    mPhotoAdapter.setList(mList);
        //
        //                } catch (FileNotFoundException e) {
        //                    e.printStackTrace();
        //                } finally {
        //                    try {
        //                        fis.close();//关闭流
        //                    } catch (IOException e) {
        //                        e.printStackTrace();
        //                    }
        //                }
        //            }
        if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    refreshAdapter(PictureSelector.obtainMultipleResult(data));
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        } else if (requestCode == HPHM_SCAN_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String hphmStr = data.getCharSequenceExtra("number").toString();
            hphm.updateNumber(hphmStr);

        } else if (requestCode == MY_SCAN_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String id = data.getStringExtra("id");
            if (id != null && id.length() == 18 || id.length() == 13) {
                sfzh.setText(id);
            }
            if ("".equals(sfzh.getText().toString())) {
                ToastUtils.showToast(this, "证件号码不能为空！");
            } else {
                ((LedgerPersenter) mPresenter).loadDriverDatas(this, sfzh.getText().toString());
            }
        }

    }

    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径  /data/data/com.scxd.lawqinghai/cache/luban_disk_cache/Luban_66450758.jpg
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                //把图片添加到将要上传的图片数组中
                mList.add(compressPath);
                mPhotoAdapter.setList(mList);

            }
        }
    }

    PopupWindow window = null;
    private int flag = 0;

    public void showPopu(final List<String> datas, final int position, int zt) {
        flag = position;
        View popupView = this.getLayoutInflater().inflate(R.layout.warn_back_photo, null);
        final ZoomImageViewGlide zoomImage = (ZoomImageViewGlide) popupView.findViewById(R.id.image);
        final TextView btn_syz = (TextView) popupView.findViewById(R.id.btn_syz);
        final TextView btn_cancle = (TextView) popupView.findViewById(R.id.btn_cancle);
        final TextView btn_xyz = (TextView) popupView.findViewById(R.id.btn_xyz);
        final TextView btn_deleted = (TextView) popupView.findViewById(R.id.btn_deleted);
        if (zt == 2) {
            btn_deleted.setVisibility(View.GONE);
        } else {
            btn_deleted.setVisibility(View.VISIBLE);
        }
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
        btn_deleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                File file = new File(datas.get(flag));
                // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
                if (file.exists() && file.isFile()) {
                    if (file.delete()) {
                        datas.remove(flag);
                        ToastUtils.showToast(LedgerActivity.this, R.string.SYSTEM_TIPS_DELETED);
                        if (datas.size() == 0) {
                            window.dismiss();
                        } else {
                            if (flag != 0) {
                                flag = flag - 1;
                            }
                            Glide.with(LedgerActivity.this).load(datas.get(flag)).into(zoomImage);
                        }
                        mPhotoAdapter.setList(datas);
                    } else {
                        ToastUtils.showToast(LedgerActivity.this, "删除失败");
                    }
                }
            }
        });
        btn_syz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (flag == 0) {
                    ToastUtils.showToast(LedgerActivity.this, "当前已经是第一张");
                } else {
                    flag--;
                    Glide.with(LedgerActivity.this).load(datas.get(flag)).into(zoomImage);
                }
            }
        });
        btn_xyz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (flag == datas.size() - 1) {
                    ToastUtils.showToast(LedgerActivity.this, "当前已经是最后一张");
                } else {
                    flag++;
                    Glide.with(LedgerActivity.this).load(datas.get(flag)).into(zoomImage);
                }
            }

        });
    }

    @Override
    public void scenClick(Context mContext) {
        Intent intent = new Intent(LedgerActivity.this, ScenCameraActivity.class);

        startActivityForResult(intent, HPHM_SCAN_REQUEST_CODE);
    }

}
