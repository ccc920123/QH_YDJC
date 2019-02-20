package com.scxd.lawqinghai.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.parkingwang.vehiclekeyboard.PopupKeyboard;
import com.parkingwang.vehiclekeyboard.ScenClick;
import com.parkingwang.vehiclekeyboard.view.InputView;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.PhotoAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.WarnBackCommon;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.ElectronicMonitoringInputDetailReqBan;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.bean.request.WSBHReqBean;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.bean.response.DLRespBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.ElectronicMonitoringInputDetailResBean;
import com.scxd.lawqinghai.bean.response.LDRespBean;
import com.scxd.lawqinghai.bean.response.PrintImageResBean;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.manager.AppManager;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.ElectronicMonitoringInputDetailPersenter;
import com.scxd.lawqinghai.mvp.presenter.NotificationOfCompulsoryMeasuresDetailPersenter;
import com.scxd.lawqinghai.mvp.presenter.SummaryPunishmentDetailPersenter;
import com.scxd.lawqinghai.mvp.view.ElectronicMonitoringInputDetailView;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.BitmapUtil;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.DrawableTextView;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.SpinnerValue;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.CoustomCheckTimePickerView;
import com.scxd.lawqinghai.widget.MyGrideView;
import com.scxd.lawqinghai.widget.ZoomImageViewGlide;
import com.scxd.lawqinghai.widget.checkpic.PictureSelectorConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述：电子录入详细界面
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class ElectronicMonitoringInputDetailActivity extends BaseActivity implements ElectronicMonitoringInputDetailView, ScenClick {


    private static int REQUEST_ORIGINAL = 2;
    private static int REQUEST_MANIFEST = 3;
    private final int HPHM_SCAN_REQUEST_CODE = 200;
    private int MY_SEARCH_CODE = 400;
    //    @BindView(R.id.wsbh)
    //    EditText wsbh;
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
    //    @BindView(R.id.clfl)
    //    TextView clfl;
    @BindView(R.id.cllx)
    TextView cllx;
    @BindView(R.id.syxz)
    TextView syxz;
    @BindView(R.id.csys)
    TextView csys;
    @BindView(R.id.fzjg)
    TextView fzjg;
    @BindView(R.id.cjfs)
    Spinner cjfs;
    @BindView(R.id.wfsj)
    TextView wfsj;
    @BindView(R.id.xzqh)
    Spinner xzqh;
    @BindView(R.id.fdjh)
    EditText fdjh;
    @BindView(R.id.clsbdh)
    EditText clsbdh;
    @BindView(R.id.clpp)
    EditText clpp;
    @BindView(R.id.jdcsyr)
    EditText jdcsyr;
    @BindView(R.id.zsxzqh)
    EditText zsxzqh;
    @BindView(R.id.zsxxdz)
    EditText zsxxdz;
    @BindView(R.id.lxdh)
    EditText lxdh;
    @BindView(R.id.lxfs)
    EditText lxfs;
    @BindView(R.id.wfdz)
    EditText wfdz;
    @BindView(R.id.wfdd)
    TextView wfdd;
    @BindView(R.id.dldm)
    TextView dldm;
    @BindView(R.id.gls)
    EditText gls;
    @BindView(R.id.ddms)
    EditText ddms;
    @BindView(R.id.wfxw)
    EditText wfxw;
    @BindView(R.id.scz1)
    EditText scz;
    @BindView(R.id.bzz1)
    EditText bzz;
    @BindView(R.id.zqmj)
    EditText zqmj;
    @BindView(R.id.wfnr)
    EditText wfnr;
    @BindView(R.id.clfl)
    Spinner clfl;
    @BindView(R.id.gridview)
    MyGrideView gridview;
    @BindView(R.id.yjfk_btn)
    Button yjfkBtn;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.query_wfdm)
    Button queryWfdm;
    @BindView(R.id.imageview)
    ImageView imageview;

    private PopupKeyboard mPopupKeyboard;
    private Map<String, String> cllxMap;
    private Map<String, String> syxzMap;
    private Map<String, String> csysMap;
    private List<String> mList;
    private PhotoAdapter mPhotoAdapter;
    private String picPath;
    private int count = 0;
    List<String> photos;

    private String tag;
    private String xh;
    private String id;
    //    private String clflStr;
    private String cllxStr;
    private String syxzStr;
    private String csysStr;
    private List<DicationResBean> hpzlList;
    private List<DicationResBean> clflList;//车辆分类
    private List<DicationResBean> cjfsList;
    //    private List<DicationResBean> ryflList;//人员分类
    List<DicationResBean> xzqhList;
    private Map<String, String> clflMap;
    private String user;
    private String bmbh;
    private int flagPhoto = 0;

    ElectronicMonitoringInputDetailReqBan bean;

    private WarnBackCommon mWarnBackCommon;

    private boolean isPhoto1 = false;   //判断文书照片是否上传完成
    private boolean isPhoto2 = false;   //判断反馈照片是否上传完成

    /**
     * 道路代码/路段代码/行政区划
     */
    private String dldmstr;
    private String lddmstr;

    @Override
    public BasePresenter getPresenter() {
        return new ElectronicMonitoringInputDetailPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dzjklr_details;
    }

    @Override
    protected void initInjector() {
        title.setText("电子监控");
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
        id = UUID.randomUUID().toString();
        user = SharedPreferencesUtils.getString(this, "USER", "");
        bmbh = SharedPreferencesUtils.getString(this, "SSBMBH", "");

        clflList = DictionaryManager.getInstance().getClfl();
        hpzlList = DictionaryManager.getInstance().getHpzl();
        cjfsList = DictionaryManager.getInstance().getCjfs();
        //        ryflList = DictionaryManager.getInstance().getRyfl();
        wfsj.setText(Date_U.getCurrentTime2());
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(this);
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(hphm, this);

        dldmstr = SharedPreferencesUtils.getString(this, "DLDM_ELE", "");
        lddmstr = SharedPreferencesUtils.getString(this, "LDDM_ELE", "");
        lddmstr = SharedPreferencesUtils.getString(this, "LDDM_ELE", "");
        String xzqhStr = SharedPreferencesUtils.getString(this, "XZQH_ELE", "");
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
        wfdd.setText(SharedPreferencesUtils.getString(this, "DLMC_ELE", ""));
        dldm.setText(SharedPreferencesUtils.getString(this, "LDMC_ELE", ""));
        ddms.setText(SharedPreferencesUtils.getString(this, "MS_ELE", ""));
        gls.setText(SharedPreferencesUtils.getString(this, "GLS_ELE", ""));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initEventAndData() {

        cllxMap = new HashMap<>();
        syxzMap = new HashMap<>();
        csysMap = new HashMap<>();
        clflMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getSyxz()) {
            syxzMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getCllx()) {
            cllxMap.put(bean.getCode(), bean.getName());
        }

        for (DicationResBean bean : DictionaryManager.getInstance().getCsys()) {
            csysMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : clflList) {
            clflMap.put(bean.getCode(), bean.getName());
        }
        if ("Detail".equals(tag)) {
            imageview.setVisibility(View.GONE);
            flagPhoto = 2;
            ((ElectronicMonitoringInputDetailPersenter) mPresenter).LoadDetail(xh);

            queryCar.setEnabled(false);
            queryCar.setBackground(getResources().getDrawable(R.drawable.gray_btn));
            yjfkBtn.setEnabled(false);
            yjfkBtn.setBackground(getResources().getDrawable(R.drawable.gray_btn));

        } else {
            imageview.setVisibility(View.VISIBLE);
            flagPhoto = 1;
            zqmj.setText(SharedPreferencesUtils.getString(this, "xm", ""));

            //再次调用文书编号接口得到返回的文书编号(电子监控不用调)
            //            WSBHReqBean bean = new WSBHReqBean();
            //            bean.setBmbh(bmbh);
            //            bean.setUser(user);
            //            bean.setWslb("7");
            //            ((ElectronicMonitoringInputDetailPersenter) mPresenter).queryWsbh(bean);//查询文书编号

        }
        //号牌种类
        ArrayAdapter<DicationResBean> adapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, hpzlList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hpzl.setAdapter(adapter);
        hpzl.setSelection(1);

        /**
         * 人员分类
         */
        ArrayAdapter<DicationResBean> clfladapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, clflList);
        clfladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clfl.setAdapter(clfladapter);
        clfl.setSelection(0);

        //采集方式
        ArrayAdapter<DicationResBean> cjfsadapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, cjfsList);
        cjfsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cjfs.setAdapter(cjfsadapter);
        cjfs.setSelection(0);
        String cjfsselete = SharedPreferencesUtils.getString(this, "CJFSLIST", "");
        for (int i = 0; i < cjfsList.size(); i++) {
            if (cjfsList.get(i).getCode().equals(cjfsselete)) {
                cjfs.setSelection(i);
                break;
            }
        }

        //处理图片
        mList = new ArrayList<>();
        mList = LogUtil.getAllFiles(Common.PHONE_PATH + "photo/", ".jpg");
        mPhotoAdapter = new PhotoAdapter(this, flagPhoto);
        gridview.setAdapter(mPhotoAdapter);
        if (mList != null && mList.size() > 0) {
            mPhotoAdapter.setList(mList);
        }

        //点击右边进行页面跳转到搜索界面
        ((DrawableTextView) wfdd).setDrawableRightClick(new DrawableTextView.DrawableRightClickListener() {
            @Override
            public void onDrawableRightClickListener(View view) {


                Intent intent = new Intent(ElectronicMonitoringInputDetailActivity.this, SearchDLActivity.class);
                startActivityForResult(intent, MY_SEARCH_CODE);


            }
        });
        mPhotoAdapter.setOnItemClickListener(new PhotoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object testBean) {
                //                if ("Detail".equals(tag)) {
                showPopu(mList, position);
                //                } else {
                //                    if (position == mList.size()) {
                //                        if (mList.size() >= 3) {
                //                            ToastUtils.showToast(ElectronicMonitoringInputDetailActivity.this, "只能拍三张");
                //                        } else {
                //                            picPath = Common.PHONE_PATH + "photo/" + System.currentTimeMillis() + ".jpg";
                //                            checkPremission();
                //                        }
                //                    } else {
                //                        showPopu(mList, position);
                //                    }
                //                }
            }
        });

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.size() >= 3) {
                    ToastUtils.showToast(ElectronicMonitoringInputDetailActivity.this, "只能拍三张");
                } else {
                    picPath = Common.PHONE_PATH + "photo/" + System.currentTimeMillis() + ".jpg";
                    checkPremission();
                }
            }
        });

        //   预警反馈跳转，自动请求
        if ("WarnBackDetails".equals(tag)) {
            hpzl.setSelection(SpinnerValue.getSpinnerValueSelected(mWarnBackCommon.getHpzl(), hpzlList));//号牌种类
            hphm.updateNumber(mWarnBackCommon.getHphm());
            hpzl.setEnabled(false);
            hphm.setEnabled(false);
            ((ElectronicMonitoringInputDetailPersenter) mPresenter).loadCarDatas(this,
                    mWarnBackCommon.getHpzl(), mWarnBackCommon.getHphm());
        }

        wfdz.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b) {
                    if ("".equals(wfdz.getText().toString().trim())) {
                        wfdz.setText(wfdd.getText().toString() + dldm.getText().toString()
                                + ("".equals(gls.getText().toString()) ? "" : gls.getText().toString() + "公里")
                                + ("".equals(ddms.getText().toString()) ? "" : ddms.getText().toString() + "米"));

                    }
                }

            }
        });
        wfdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(wfdz.getText().toString().trim())) {
                    wfdz.setText(wfdd.getText().toString() + dldm.getText().toString()
                            + ("".equals(gls.getText().toString()) ? "" : gls.getText().toString() + "公里")
                            + ("".equals(ddms.getText().toString()) ? "" : ddms.getText().toString() + "米"));

                }
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

                PictureSelectorConfig.initMultiConfig(this, 3-(mList.size()));

                //                Common.PHONE_PATH + "photo/" + System.currentTimeMillis() + ".jpg";
            }
        } else {

            //            openCamera();//调用具体方法
            PictureSelectorConfig.initMultiConfig(this, 3-(mList.size()));
        }
    }

    private void openCamera() {  //调用相机拍照
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= 24) {
            intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(this,
                            "com.scxd.lawqinghai.fileprovider", new File(picPath)));
        } else {
            File file = new File(picPath);
            Uri uri = Uri.fromFile(file);
            //为拍摄的图片指定一个存储的路径
            intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        startActivityForResult(intent2, REQUEST_ORIGINAL);
    }

    PopupWindow window = null;
    private int flag = 0;

    /**
     * 提交照片数据的消息等待框
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
        Glide.with(this).load(datas.get(position)).into(zoomImage);
        window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.update();
        window.showAtLocation(main, Gravity.CENTER, 0, 0);
        if ("Detail".equals(tag)) {
            btn_deleted.setVisibility(View.GONE);
        } else {
            btn_deleted.setVisibility(View.VISIBLE);
        }
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
                        ToastUtils.showToast(ElectronicMonitoringInputDetailActivity.this, R.string.SYSTEM_TIPS_DELETED);
                        if (datas.size() == 0) {
                            window.dismiss();
                        } else {
                            if (flag != 0) {
                                flag = flag - 1;
                            }
                            Glide.with(ElectronicMonitoringInputDetailActivity.this)
                                    .load(datas.get(flag))
                                    .into(zoomImage);
                        }
                        mPhotoAdapter.setList(datas);
                    } else {
                        ToastUtils.showToast(ElectronicMonitoringInputDetailActivity.this, "删除失败");
                    }
                }
            }
        });
        btn_syz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (flag == 0) {
                    ToastUtils.showToast(ElectronicMonitoringInputDetailActivity.this, "当前已经是第一张");
                } else {
                    flag--;
                    Glide.with(ElectronicMonitoringInputDetailActivity.this).load(datas.get(flag))
                            .into(zoomImage);
                }
            }
        });
        btn_xyz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (flag == datas.size() - 1) {
                    ToastUtils.showToast(ElectronicMonitoringInputDetailActivity.this, "当前已经是最后一张");
                } else {
                    flag++;
                    Glide.with(ElectronicMonitoringInputDetailActivity.this).load(datas.get(flag))
                            .into(zoomImage);
                }
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_MANIFEST) {
            //就像onActivityResult一样这个地方就是判断你是从哪来的。
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                //                openCamera();


                PictureSelectorConfig.initMultiConfig(this, 3-(mList.size()));
            } else {

                // Permission Denied
                ToastUtils.showToast(ElectronicMonitoringInputDetailActivity.this, R.string.ERROR_TIPS_02);
            }
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
    public void backQueryCar(LedgerQueyCarReqBean.DataBean carBean) {

        //        clflStr = carBean.getZdcllx();
        cllxStr = carBean.getCllx();
        syxzStr = carBean.getSyxz();
        csysStr = carBean.getCsys();

        //        clfl.setText("".equals(clflStr) ? "" : clflMap.get(clflStr));//车辆分类
        cllx.setText("".equals(cllxStr) ? "" : cllxMap.get(cllxStr));
        syxz.setText("".equals(syxzStr) ? "" : syxzMap.get(syxzStr));
        csys.setText("".equals(csysStr) ? "" : csysMap.get(csysStr));
        jdcsyr.setText(carBean.getJdcsyr());//机动车所有人
        fzjg.setText(carBean.getFzjg());

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void backQueryDetail(ElectronicMonitoringInputDetailResBean bean) {
        //显示查询Q22的数据
        queryWfdm.setEnabled(false);
        queryWfdm.setBackground(getResources().getDrawable(R.drawable.gray_btn));

        hpzl.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getHpzl(), hpzlList));//号牌种类

        hphm.updateNumber(bean.getHphm());
        //        clflStr = bean.getClfl();
        cllxStr = bean.getCllx();
        syxzStr = bean.getSyxz();
        csysStr = bean.getCsys();
        //        wsbh.setText(bean.getJdsbh());
        //        clfl.setText("".equals(clflStr) ? "" : clflMap.get(clflStr));//车辆分类
        cllx.setText("".equals(cllxStr) ? "" : cllxMap.get(cllxStr));
        syxz.setText("".equals(syxzStr) ? "" : syxzMap.get(syxzStr));
        csys.setText("".equals(csysStr) ? "" : csysMap.get(csysStr));
        clfl.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getRyfl(), clflList));
        fzjg.setText(bean.getFzjg());
        jdcsyr.setText(bean.getJdcsyr());
        fdjh.setText(bean.getFdjh());
        clsbdh.setText(bean.getClsbdh());
        clpp.setText(bean.getClpp());
        zsxzqh.setText(bean.getZsxzqh());
        zsxxdz.setText(bean.getZsxxdz());
        lxdh.setText(bean.getDh());
        lxfs.setText(bean.getLxfs());
        cjfs.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getCjfs(), cjfsList));
        wfsj.setText(bean.getWfsj());
        DicationResBean xzqhbean = new DicationResBean();
        xzqhList = new ArrayList<>();
        xzqhbean.setCode(bean.getXzqh());
        xzqhbean.setName(bean.getXzqh());
        xzqhList.add(xzqhbean);
        xzqh.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getXzqh(), xzqhList));
        ddms.setText(bean.getDdms());
        gls.setText(bean.getDdgls());
        wfdz.setText(bean.getWfdz());
        wfxw.setText(bean.getWfxw());
        wfnr.setText(bean.getWfnr());
        scz.setText(bean.getScz());
        bzz.setText(bean.getBzz());
        zqmj.setText(bean.getZqmj());

        if (null != bean.getZps() && bean.getZps().size() > 0) {
            mPhotoAdapter.setList(bean.getZps());
            mList = bean.getZps();
        }


        //路段==dldm

        SearchLDReqBean ldBean = new SearchLDReqBean(
                SharedPreferencesUtils.getString(this, "SJBM", ""),
                bean.getLddm());

        ((ElectronicMonitoringInputDetailPersenter) mPresenter).searchLD(ldBean);


        //道路==wfdd
        SearchDLReqBean dlBean = new SearchDLReqBean(
                SharedPreferencesUtils.getString(this, "SJBM", ""),
                bean.getWfdd());
        ((ElectronicMonitoringInputDetailPersenter) mPresenter).searchDL(dlBean);

    }


    private PrintImageResBean mPrintImageResBean;

    @Override
    public void backComitData(PrintImageResBean bean) {
        mPrintImageResBean = bean;
        if ("WarnBackDetails".equals(tag)) {
            WarnBackReqBean warnBackBean = mWarnBackCommon.getWarnBackReqBean();
            warnBackBean.setWsbh(bean.getXh());//将文书编号设置进去
            warnBackBean.setJyw(bean.getWsjyw());//将校验位设置进去
            if (isPhoto2) {
                ((ElectronicMonitoringInputDetailPersenter) mPresenter).commitBackData(this, warnBackBean);
            } else {
                if (mWarnBackCommon.getPhotos().size() > 0) {
                    ((ElectronicMonitoringInputDetailPersenter) mPresenter).commitBackPhotos(this, "uploadPhoto", user, xh, "7002", mWarnBackCommon.getPhotos());

                } else {
                    ((ElectronicMonitoringInputDetailPersenter) mPresenter).commitBackData(this, warnBackBean);
                }
            }
        } else {
            Intent intent = new Intent(this, PrintImageActivity.class);
            intent.putExtra("BEAN", this.bean);
            intent.putExtra("XH", bean.getXh());//打印照片的地址
            intent.putExtra("TAG", "ElectronicMonitoringInputDetail");
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void backCommitPhotos(String message) {

        count++;
        ToastUtils.showToast(this, message);
        if (count == photos.size()) {//开始提交数据
            count = 0;
            isPhoto1 = true;
            ((ElectronicMonitoringInputDetailPersenter) mPresenter).comitData(ElectronicMonitoringInputDetailActivity.this, getBean());

        }


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
        intent.putExtra("TAG", "ElectronicMonitoringInputDetail");
        startActivity(intent);
        finish();

    }

    private int count2 = 0;

    /**
     * 提交反馈照片
     *
     * @param message
     */
    @Override
    public void commitBackPhotos(String message) {

        count2++;
        if (mWarnBackCommon.getPhotos().size() == count2) {
            count2 = 0;
            isPhoto2 = true;
            ((ElectronicMonitoringInputDetailPersenter) mPresenter).commitBackData(this, mWarnBackCommon
                    .getWarnBackReqBean());

        }
    }

    /**
     * 违法代码查询到数据
     *
     * @param dataBean
     */
    @Override
    public void showCodeDatas(QueryCodeListBean dataBean) {


        wfnr.setText(dataBean.getWfnr());
        wfxw.setText(dataBean.getWfdm());

    }

    @Override
    public void backDL(List<DLRespBean> bean) {
        wfdd.setText(bean.get(0).getDlmc());

    }

    @Override
    public void backLD(List<LDRespBean> bean) {
        dldm.setText(bean.get(0).getLdmc());
    }

    @Override
    public void getWsbh(String wsbhs) {
        //        wsbh.setText(wsbhs);
    }


    @OnClick({R.id.back, R.id.query_car, R.id.yjfk_btn, R.id.query_wfdm, R.id.wfsj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;


            case R.id.query_wfdm:

                if ("".equals(wfxw.getText().toString()))

                {
                    ToastUtils.showToast(ElectronicMonitoringInputDetailActivity.this, "请录入违法行为代码查询违法内容！");
                    break;
                }

                ((ElectronicMonitoringInputDetailPersenter) mPresenter).loadCodeLaw(ElectronicMonitoringInputDetailActivity.this, wfxw.getText().toString().trim());


                break;
            case R.id.query_car:
                if (mPopupKeyboard.isShown()) {
                    mPopupKeyboard.dismiss(ElectronicMonitoringInputDetailActivity.this);
                }
                ((ElectronicMonitoringInputDetailPersenter) mPresenter).loadCarDatas(ElectronicMonitoringInputDetailActivity.this,
                        ((DicationResBean) hpzl.getSelectedItem()).getCode(), hphm.getNumber());

                break;

            case R.id.yjfk_btn:
                if (!ButtonTools.isFastDoubleClick(1500)) {

                    if ("".equals(hphm.getNumber())) {
                        ToastUtils.showToast(this, "号牌号码不能为空！");
                        break;

                    }
                    if ("".equals(wfsj.getText().toString())) {
                        ToastUtils.showToast(this, "违法时间不能为空！");
                        break;

                    }
                    if ("".equals(wfdd.getText().toString())) {
                        ToastUtils.showToast(this, "道路代码不能为空！");
                        break;

                    }
                    if ("".equals(dldm.getText().toString())) {
                        ToastUtils.showToast(this, "路段号码不能为空！");
                        break;

                    }
                    if ("".equals(wfdz.getText().toString())) {
                        ToastUtils.showToast(this, "违法地址不能为空！");
                        break;

                    }

                    if ("".equals(wfxw.getText().toString())) {
                        ToastUtils.showToast(this, "违法代码不能为空！");
                        break;

                    }
                    if ("".equals(wfnr.getText().toString())) {
                        ToastUtils.showToast(this, "请通过违法代码查出违法内容");
                        break;
                    }
                    photos = LogUtil.getAllFiles(Common.PHONE_PATH + "photo/", ".jpg");

                    if (null != photos && photos.size() > 0) {
                        if (isPhoto1) {
                            ((ElectronicMonitoringInputDetailPersenter) mPresenter).comitData(ElectronicMonitoringInputDetailActivity.this, getBean());

                        } else {
                            count = 0;
                            String user = SharedPreferencesUtils.getString(ElectronicMonitoringInputDetailActivity.this, "USER", "");

                            ((ElectronicMonitoringInputDetailPersenter) mPresenter).commitPhotos(ElectronicMonitoringInputDetailActivity.this, "uploadPhoto", user, id, "7006", photos);
                        }
                    } else {

                        ToastUtils.showToast(this, "必须至少拍摄一张照片！");


                    }
                }


                break;
            case R.id.wfsj:

                CoustomCheckTimePickerView.getInstance().getTime(this, wfsj);
                break;
        }
    }

    /**
     * 组装数据W10 bean
     *
     * @return
     */
    private ElectronicMonitoringInputDetailReqBan getBean() {
        bean = new ElectronicMonitoringInputDetailReqBan();
        bean.setId(id);
        bean.setLrr(user);
        bean.setBmbh(bmbh);
        bean.setCjjg(bmbh);
        bean.setHpzl(((DicationResBean) hpzl.getSelectedItem()).getCode().toString());

        bean.setHphm(hphm.getNumber());

        //        bean.setClfl(clflStr);
        //        bean.setCllx(cllxStr);
        bean.setSyxz(syxzStr);
        bean.setCsys(csysStr);
        bean.setJdcsyr(jdcsyr.getText().toString());
        bean.setFdjh(fdjh.getText().toString());
        bean.setClsbdh(clsbdh.getText().toString().toUpperCase());
        bean.setClpp(clpp.getText().toString());
        bean.setJtfs(cllxStr);
        bean.setClfl(((DicationResBean) clfl.getSelectedItem()).getCode().toString());
        bean.setFzjg(fzjg.getText().toString());
        bean.setZsxzqh(zsxzqh.getText().toString());
        bean.setZsxxdz(zsxxdz.getText().toString());
        bean.setDh(lxdh.getText().toString());
        bean.setLxfs(lxfs.getText().toString());
        bean.setTzrq("");
        bean.setCjfs(((DicationResBean) cjfs.getSelectedItem()).getCode().toString());
        SharedPreferencesUtils.saveString(this, "CJFSLIST",
                ((DicationResBean) cjfs.getSelectedItem()).getCode().toString());
        bean.setWfsj(wfsj.getText().toString());
        bean.setXzqh(((DicationResBean) xzqh.getSelectedItem()).getCode().toString());
        bean.setWfdd(dldmstr);
        bean.setLddm(lddmstr);
        bean.setDdms(ddms.getText().toString());
        bean.setDdgls(gls.getText().toString());
        SharedPreferencesUtils.saveString(this, "MS_ELE", ddms.getText().toString());
        SharedPreferencesUtils.saveString(this, "GLS_ELE", gls.getText().toString());
        bean.setWfdz(wfdz.getText().toString());
        bean.setWfxw(wfxw.getText().toString());
        bean.setScz(scz.getText().toString());
        bean.setBzz(bzz.getText().toString());
        if ("".equals(zqmj.getText().toString())) {
            zqmj.setEnabled(true);
            zqmj.setHint("录入执勤民警警号");
            ToastUtils.showToast(this, "请填录入执勤民警警号！");
            return null;
        }
        bean.setZqmj(SharedPreferencesUtils.getString(this, "USER", ""));
        //        bean.setSpdz("");
        //        bean.setSbbh("");
        //        bean.setZpstr1("");
        //        bean.setZpstr2("");
        //        bean.setZpstr3("");


        return bean;
    }


    @Override
    public void onBackPressed() {
        if (mPopupKeyboard.isShown()) {
            mPopupKeyboard.dismiss(this);
            return;
        }
        //        if (!"WarnBackDetails".equals(tag)) {
        //            Intent intent = new Intent(this, ElectronicMonitoringInputActivity.class);
        //            startActivity(intent);
        //        }
        finish();
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
    /**
     * 调用系统相机，号牌识别框架识别号牌，返回数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
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
        //        }

        if (requestCode==PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) {
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

        } else if (requestCode == MY_SEARCH_CODE && resultCode == RESULT_OK && data != null) {
            //TODODLRespBean dl, LDRespBean ld

            DLRespBean dl = (DLRespBean) data.getSerializableExtra("DL");
            LDRespBean ld = (LDRespBean) data.getSerializableExtra("LD");
            wfdd.setText(dl.getDlmc());
            dldm.setText(ld.getLdmc());

            String xzqhstr = dl.getXzqh();// 行政区划

            dldmstr = dl.getDldm();
            lddmstr = ld.getLddm();

            SharedPreferencesUtils.saveString(this, "DLDM_ELE", dl.getDldm());
            SharedPreferencesUtils.saveString(this, "DLMC_ELE", dl.getDlmc());
            SharedPreferencesUtils.saveString(this, "XZQH_ELE", xzqhstr);
            SharedPreferencesUtils.saveString(this, "LDDM_ELE", ld.getLddm());
            SharedPreferencesUtils.saveString(this, "LDMC_ELE", ld.getLdmc());
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

    /**
     * 跳转到号牌识别界面进行识别
     *
     * @param mContext
     */
    @Override
    public void scenClick(Context mContext) {
        Intent intent = new Intent(ElectronicMonitoringInputDetailActivity.this, ScenCameraActivity.class);

        startActivityForResult(intent, HPHM_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onDestroy() {
        if (null != mList) {
            //            for (int i = 0; i < mList.size(); i++) {
            //                File file = new File(mList.get(i));
            //                // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            //                if (file.exists() && file.isFile()) {
            //                    file.delete();
            //                }
            //
            //            }

            PictureSelectorConfig.deleteCacheDirFile(this);
            PictureSelectorConfig.deleteFile(Common.PHONE_PATH + "photo/");
            PictureSelectorConfig.deleteFile(Common.PIC_PATH + "cache/");


        }

        super.onDestroy();

    }
}
