package com.scxd.lawqinghai.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.CzjgAdapter;
import com.scxd.lawqinghai.adapter.PhotoAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.WarnBackCommon;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.WarnbackRspBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.common.Md_System_Datas;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.WarnBackPresenter;
import com.scxd.lawqinghai.mvp.view.WarnBackView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.SpinnerValue;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.MyGrideView;
import com.scxd.lawqinghai.widget.MyListView;
import com.scxd.lawqinghai.widget.ZoomImageViewGlide;
import com.scxd.lawqinghai.widget.checkpic.PictureSelectorConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述: 预警反馈
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnBackActivity extends BaseActivity implements WarnBackView {

    private static int REQUEST_ORIGINAL = 2;
    private static int REQUEST_MANIFEST = 3;


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.ylj)
    RadioButton ylj;
    @BindView(R.id.wlj)
    RadioButton wlj;
    @BindView(R.id.ljqk)
    RadioGroup ljqk;
    @BindView(R.id.shi)
    RadioButton shi;
    @BindView(R.id.fou)
    RadioButton fou;
    @BindView(R.id.sfxycl)
    RadioGroup sfxycl;
    @BindView(R.id.fxyclyy)
    Spinner fxyclyy;
    @BindView(R.id.ljqkms)
    EditText ljqkms;
    @BindView(R.id.jybm)
    EditText jybm;
    @BindView(R.id.cjry)
    TextView cjry;
    @BindView(R.id.czfs)
    MyListView czfs;
    @BindView(R.id.wslb)
    Spinner wslb;
    @BindView(R.id.gridview)
    MyGrideView gridview;
    @BindView(R.id.yjfk_btn)
    Button yjfkBtn;
    @BindView(R.id.wljdyy)
    Spinner wljdyy;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.wljdyyrow)
    TableRow wljdyyrow;
    @BindView(R.id.fxyclyyrow)
    TableRow fxyclyyrow;
    @BindView(R.id.czjgrow)
    TableRow czjgrow;
    @BindView(R.id.cjryrow)
    TableRow cjryrow;
    @BindView(R.id.ljqkrow)
    TableRow ljqkrow;
    @BindView(R.id.sfxlcrow)
    TableRow sfxlcrow;
    private static TableRow yjbmrow;
    private static TableRow wszlxrow;
    private static TableRow lxrrow;
    private static TableRow lxdhrow;
    @BindView(R.id.lxr)
    EditText lxr;
    @BindView(R.id.lxdh)
    EditText lxdh;
    @BindView(R.id.wsbh)
    TextView wsbh;
    @BindView(R.id.wsbhrow)
    TableRow wsbhrow;
    @BindView(R.id.imageview)
    ImageView imageview;


    private List<CheckBox> checkBoxList;
    private String ljqkStr = "1";
    private String wljdyyStr;
    private String sfxyclStr = "1";
    private String fxyclyyStr = "";
    private String ljqkmsStr = "";
    private String cjryStr = "";
    private String czfsStr = null;

    private PhotoAdapter mPhotoAdapter;
    private CzjgAdapter mCzjgAdapter;
    private List<String> mList;
    private List<DicationResBean> wljdyyList;
    private String picPath;
    private Map<String, Boolean> isSelected;

    private int qsztFlag = 0;
    private String hphm;
    private String hpzl;
    private String yjlx;
    private String yjzlx;
    private static List<DicationResBean> wsList;
    private static List<DicationResBean> wslbList;

    private boolean isPhoto = false;   //判断照片是否上传完成

    private WarnbackRspBean.DataBean mDataBean;
    private WarnBackCommon warnBackCommon;

    @Override
    public BasePresenter getPresenter() {
        return new WarnBackPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_warn_back;
    }

    public static void setYjbm(boolean is) {
        if (is) {
            yjbmrow.setVisibility(View.VISIBLE);
            lxrrow.setVisibility(View.VISIBLE);
            lxdhrow.setVisibility(View.VISIBLE);
        } else {

            yjbmrow.setVisibility(View.GONE);
            lxrrow.setVisibility(View.GONE);
            lxdhrow.setVisibility(View.GONE);
        }

    }

    public void setwslb(boolean is) {
        if (is) {
            //            list.addAll(wsList);
            //            mCzjgAdapter.notifyDataSetChanged();

            wszlxrow.setVisibility(View.VISIBLE);
        } else {
            //            list.removeAll(wsList);
            //            mCzjgAdapter.notifyDataSetChanged();
            wszlxrow.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initInjector() {
        wsList = DictionaryManager.getInstance().getWslb();
        wslbList = new LinkedList<>();
        for (DicationResBean bean : wsList) {
            if ("1".equals(bean.getCode()) || "3".equals(bean.getCode())) {
                wslbList.add(bean);
                LogUtil.open().appendMethodB(bean.getName());//
            }
        }
        wljdyyList = DictionaryManager.getInstance().getWljdyy();
        InputMethodManager imm = (InputMethodManager) WarnBackActivity.this.getSystemService(this.INPUT_METHOD_SERVICE);
        yjbmrow = (TableRow) findViewById(R.id.yjbmrow);
        wszlxrow = (TableRow) findViewById(R.id.wszlxrow);
        lxrrow = (TableRow) findViewById(R.id.lxrrow);
        lxdhrow = (TableRow) findViewById(R.id.lxdhrow);
        title.setText(R.string.yjfk);

        qsztFlag = getIntent().getIntExtra("QSZT", 0);
        hphm = getIntent().getStringExtra("HPHM");
        hpzl = getIntent().getStringExtra("HPZL");
        yjlx = getIntent().getStringExtra("YJLX");
        yjzlx = getIntent().getStringExtra("YJZLX");
        switch (qsztFlag) {
            case 2:
                imageview.setVisibility(View.GONE);
                yjfkBtn.setVisibility(View.GONE);
                break;
            default:
                imageview.setVisibility(View.VISIBLE);
                yjfkBtn.setVisibility(View.VISIBLE);
                break;
        }

        warnBackCommon = new WarnBackCommon();

        isSelected = new HashMap<>();
        for (int i = 0; i < Md_System_Datas.getCzfs().size(); i++) {
            isSelected.put(Md_System_Datas.getCzfs().get(i).getName(), false);
        }
        mCzjgAdapter = new CzjgAdapter(this, qsztFlag);
        czfs.setAdapter(mCzjgAdapter);
        mCzjgAdapter.setIsSelected(isSelected);
        mList = new ArrayList<>();
        mList = LogUtil.getAllFiles(Common.PHONE_PATH + "photo/", ".jpg");
        mPhotoAdapter = new PhotoAdapter(this, qsztFlag);
        gridview.setAdapter(mPhotoAdapter);
        if (null != mList) {
            mPhotoAdapter.setList(mList);
        } else {
            mList = new ArrayList<>();
        }
        
        bindView();
        
    }

    @Override
    protected void initEventAndData() {
        String sjbmbh = SharedPreferencesUtils.getString(this, "SJBM", "");
        ((WarnBackPresenter) mPresenter).load(this, Common.YJXH, sjbmbh);
    }

    public void getCommitDatas() {
        wljdyyStr = ((DicationResBean) wljdyy.getSelectedItem()).getCode().toString();
        fxyclyyStr = ((DicationResBean) fxyclyy.getSelectedItem()).getCode().toString();
        SharedPreferencesUtils.saveString(this, "FXYCLYY", fxyclyyStr);
        ljqkmsStr = ljqkms.getText().toString().trim();
        //        if (!"".equals(ljqkmsStr)) {
        //            SharedPreferencesUtils.saveString(WarnBackActivity.this, "LJQKMS", ljqkms.getText().toString()
        // .trim());
        //        }
        cjryStr = cjry.getText().toString().trim();
        StringBuffer sb = new StringBuffer();
        //遍历集合中的checkBox,判断是否选择，获取选中的文本
        isSelected = new HashMap<>();
        isSelected = mCzjgAdapter.getIsSelected();//此处需要修改
        Iterator<Map.Entry<String, Boolean>> it = isSelected.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Boolean> entry = it.next();
            if (entry.getValue()) {
                sb.append(entry.getKey() + ",");
            }
        }
        if (null != sb && sb.length() > 0)
            czfsStr = sb.substring(0, sb.length() - 1);

    }

    public void bindView() {

        ArrayAdapter<DicationResBean> wljdyyAdapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, wljdyyList);
        wljdyyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wljdyy.setAdapter(wljdyyAdapter);
        wljdyy.setSelection(0);

        ArrayAdapter<DicationResBean> fxyclyyAdapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, DictionaryManager.getInstance().getFxyclyy());
        fxyclyyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fxyclyy.setAdapter(fxyclyyAdapter);
        fxyclyy.setSelection(0);
        //文书类别
        ArrayAdapter<DicationResBean> wslbAdapter = new ArrayAdapter<DicationResBean>(this, android.R.layout
                .simple_spinner_item, wslbList);
        wslbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wslb.setAdapter(wslbAdapter);
        wslb.setSelection(0);

        ljqkms.setText(SharedPreferencesUtils.getString(WarnBackActivity.this, "LJQKMS", ""));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ljqk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.ylj) {
                    ljqkStr = "1";
                    wljdyyrow.setVisibility(View.GONE);
                    czjgrow.setVisibility(View.VISIBLE);
                    cjryrow.setVisibility(View.VISIBLE);
                    ljqkrow.setVisibility(View.VISIBLE);
                    sfxlcrow.setVisibility(View.VISIBLE);
                    if ("0".equals(sfxyclStr)) {
                        fxyclyyrow.setVisibility(View.VISIBLE);
                        String fxy = SharedPreferencesUtils.getString(WarnBackActivity.this, "FXYCLYY", "");
                        for (int i = 0; i < DictionaryManager.getInstance().getFxyclyy().size(); i++) {
                            if (DictionaryManager.getInstance().getFxyclyy().get(i).getCode().equals(fxy)) {
                                fxyclyy.setSelection(i);
                                break;
                            }
                        }
                    }

                } else if (checkedId == R.id.wlj) {
                    ljqkStr = "0";
                    wljdyyrow.setVisibility(View.VISIBLE);
                    //表示未拦截到，那么是否嫌你车，拦截情况，处置结果都不显示
                    czjgrow.setVisibility(View.GONE);
                    cjryrow.setVisibility(View.GONE);
                    ljqkrow.setVisibility(View.GONE);
                    sfxlcrow.setVisibility(View.GONE);
                    fxyclyyrow.setVisibility(View.GONE);


                }
            }
        });
        ljqk.check(R.id.ylj);
        sfxycl.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.shi) {
                    sfxyclStr = "1";
                    fxyclyyrow.setVisibility(View.GONE);
                    czjgrow.setVisibility(View.VISIBLE);
                    if (flagWslb){
                        wszlxrow.setVisibility(View.VISIBLE);
                    }
                } else if (checkedId == R.id.fou) {
                    sfxyclStr = "0";
                    czjgrow.setVisibility(View.GONE);
                    if (wszlxrow.getVisibility() == View.GONE) {
                        flagWslb = false;
                    } else {
                        flagWslb = true;
                        wszlxrow.setVisibility(View.GONE);
                    }
                    fxyclyyrow.setVisibility(View.VISIBLE);
                    String fxy = SharedPreferencesUtils.getString(WarnBackActivity.this, "FXYCLYY", "");
                    for (int i = 0; i < DictionaryManager.getInstance().getFxyclyy().size(); i++) {
                        if (DictionaryManager.getInstance().getFxyclyy().get(i).getCode().equals(fxy)) {
                            fxyclyy.setSelection(i);
                            break;
                        }
                    }
                }
            }
        });
        sfxycl.check(R.id.shi);
        wsbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String isEnd = wszlxrow.getVisibility() == View.GONE ? "" : ((DicationResBean) wslb.getSelectedItem()
                ).getCode();
                if ("1".equals(isEnd)) {
                    intent.setClass(WarnBackActivity.this, SummaryPunishmentDetailActivity.class);
                } else if ("3".equals(isEnd)) {
                    intent.setClass(WarnBackActivity.this, NotificationOfCompulsoryMeasuresDetailActivity.class);
                } else if ("6".equals(isEnd)) {
                    intent.setClass(WarnBackActivity.this, ElectronicMonitoringInputDetailActivity.class);
                }
                intent.putExtra("XH", wsbh.getText().toString().trim());
                intent.putExtra("TAG", "Detail");
                startActivity(intent);
            }
        });

        checkBoxList = new ArrayList<>();

        yjfkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    czfsStr = "";
                    if ("1".equals(ljqkStr)) {//1为拦截

                        getCommitDatas();
                        if ("0".equals(sfxyclStr)) {
                            czfsStr = "";
                        } else {

                            if (null == czfsStr || "".equals(czfsStr.toString())) {
                                ToastUtils.showToast(WarnBackActivity.this, R.string.ERROR_TIPS_01);
                                return;
                            }
                        }
                    } else {
                        czfsStr = "";
                    }

                    WarnBackReqBean bean = new WarnBackReqBean(SharedPreferencesUtils.getString(WarnBackActivity
                            .this, "USER", "admin"), Common.YJXH, ljqkStr, "1".equals(ljqkStr) ? "" : wljdyyStr, 
                            sfxlcrow.getVisibility() == View.GONE ? "" : sfxyclStr, fxyclyyrow.getVisibility() == 
                            View.GONE ? "" : fxyclyyStr, ljqkrow.getVisibility() == View.GONE ? "" : ljqkmsStr, 
                            cjryrow.getVisibility() == View.GONE ? "" : cjryStr, czjgrow.getVisibility() == View.GONE
                            ? "" : czfsStr.toString(), wszlxrow.getVisibility() == View.GONE ? "" : (
                                    (DicationResBean) wslb.getSelectedItem()).getCode(), yjbmrow.getVisibility() == 
                            View.GONE ? "" : jybm.getText().toString(), "", "", yjlx, yjzlx, SharedPreferencesUtils
                            .getString(WarnBackActivity.this, "SSBMBH", ""), lxrrow.getVisibility() == View.GONE ? ""
                            : lxr.getText().toString(), lxdhrow.getVisibility() == View.GONE ? "" : lxdh.getText()
                            .toString()

                    );
                    ArrayList<String> photos = new ArrayList<>();
                    if (mList.size() > 0) {
                        count = 0;
                        photos = LogUtil.getAllFiles(Common.PHONE_PATH + "photo/", ".jpg");
                    }
                    Intent intent = new Intent();
                    String isEnd = wszlxrow.getVisibility() == View.GONE ? "" : ((DicationResBean) wslb
                            .getSelectedItem()).getCode();
                    if (!"".equals(isEnd)) {
                        if ("1".equals(isEnd)) {
                            intent.setClass(WarnBackActivity.this, SummaryPunishmentDetailActivity.class);
                        } else if ("3".equals(isEnd)) {
                            intent.setClass(WarnBackActivity.this, NotificationOfCompulsoryMeasuresDetailActivity
                                    .class);
                        } else if ("6".equals(isEnd)) {
                            intent.setClass(WarnBackActivity.this, ElectronicMonitoringInputDetailActivity.class);
                        }

                        warnBackCommon.setWarnBackReqBean(bean);
                        warnBackCommon.setPhotos(photos);
                        warnBackCommon.setYjxh(Common.YJXH);
                        warnBackCommon.setHphm(hphm);
                        warnBackCommon.setHpzl(hpzl);

                        intent.putExtra("BACK", warnBackCommon);
                        intent.putExtra("TAG", "WarnBackDetails");
                        startActivity(intent);

                    } else {
                        if (isPhoto) {
                            loadBackDatas();
                        } else {
                            if (mList.size() > 0) {
                                count = 0;
                                String user = SharedPreferencesUtils.getString(WarnBackActivity.this, "USER", "");
                                photos = LogUtil.getAllFiles(Common.PHONE_PATH + "photo/", ".jpg");
                                ((WarnBackPresenter) mPresenter).commitPhotos(WarnBackActivity.this, "uploadPhoto", 
                                        user, Common.YJXH, "7002", photos);

                            } else {
                                loadBackDatas();
                            }
                        }
                    }

                }
            }
        });

        // gridview滑动时触发的事件
        gridview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        // 当ListView处于滑动状态时，停止加载图片，保证操作界面流畅
                        Glide.with(WarnBackActivity.this).pauseRequests();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 当ListView处于静止状态时，继续加载图片
                        Glide.with(WarnBackActivity.this).resumeRequests();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        mPhotoAdapter.setOnItemClickListener(new PhotoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object testBean) {
//                if (qsztFlag == 2) {
                    showPopu(mList, position);
//                } else {
//                    if (position == mList.size()) {
//                        
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
                    ToastUtils.showToast(WarnBackActivity.this, "只能拍三张");
                } else {
                    picPath = Common.PHONE_PATH + "photo/" + System.currentTimeMillis() + ".jpg";
                    checkPremission();
                }
            }
        });
    }
    
    private boolean flagWslb = false;
    
    public void loadBackDatas() {
        getCommitDatas();
        if ("1".equals(ljqkStr)) {//1为拦截
            if ("0".equals(sfxyclStr)) {
                czfsStr = "";
            } else {

                if (null == czfsStr || "".equals(czfsStr.toString())) {
                    ToastUtils.showToast(WarnBackActivity.this, R.string.ERROR_TIPS_01);
                    return;
                }
            }
        } else {
            czfsStr = "";
        }

        WarnBackReqBean bean = new WarnBackReqBean(SharedPreferencesUtils.getString(WarnBackActivity.this, "USER", 
                "admin"), Common.YJXH, ljqkStr, "1".equals(ljqkStr) ? "" : wljdyyStr,

                sfxlcrow.getVisibility() == View.GONE ? "" : sfxyclStr, fxyclyyrow.getVisibility() == View.GONE ? "" 
                : fxyclyyStr, ljqkrow.getVisibility() == View.GONE ? "" : ljqkmsStr, cjryrow.getVisibility() == View
                .GONE ? "" : cjryStr, czjgrow.getVisibility() == View.GONE ? "" : czfsStr.toString(), wszlxrow
                .getVisibility() == View.GONE ? "" : ((DicationResBean) wslb.getSelectedItem()).getCode(), yjbmrow
                .getVisibility() == View.GONE ? "" : jybm.getText().toString(), "", "", yjlx, yjzlx, 
                SharedPreferencesUtils.getString(WarnBackActivity.this, "SSBMBH", ""), lxrrow.getVisibility() == View
                .GONE ? "" : lxr.getText().toString(), lxdhrow.getVisibility() == View.GONE ? "" : lxdh.getText()
                .toString()

        );

        ((WarnBackPresenter) mPresenter).commitDatas(WarnBackActivity.this, bean);
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

            //            openCamera();//调用具体方法


            PictureSelectorConfig.initMultiConfig(this, 3 - (mList.size()));

        }
    }

    private void openCamera() {  //调用相机拍照
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= 24) {
            intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent2.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, "com.scxd.lawqinghai" + "" +
                    ".fileprovider", new File(picPath)));
        } else {
            Uri uri = Uri.fromFile(new File(picPath));
            //为拍摄的图片指定一个存储的路径 
            intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        startActivityForResult(intent2, REQUEST_ORIGINAL);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, WarnDetailsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        if (!"".equals(ljqkms.getText().toString().trim())) {
            SharedPreferencesUtils.saveString(WarnBackActivity.this, "LJQKMS", ljqkms.getText().toString().trim());
        }

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
        if (null != wslbList) {
            wslbList.clear();
            wslbList = null;
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
    public void showDatas(WarnbackRspBean.DataBean bean) {
        warnBackCommon.setDldm(bean.getDldm());
        warnBackCommon.setDlmc(bean.getDlmc());
        warnBackCommon.setXzqh(bean.getXzqh());
        warnBackCommon.setLddm(bean.getLddm());
        warnBackCommon.setLdmc(bean.getLdmc());
        warnBackCommon.setYjsj(bean.getYjsj());

        if (null != bean.getCzfs() && !"".equals(bean.getCzfs())) {
            String[] czfsArr = bean.getCzfs().split(",");
            for (int i = 0; i < czfsArr.length; i++) {
                isSelected.put(czfsArr[i], true);
            }
            mCzjgAdapter.setIsSelected(isSelected);
        }

        if (null != bean.getLjqk()) {
            switch (bean.getLjqk()) {
                case "1":
                    ljqk.check(R.id.ylj);
                    break;
                case "0":
                    ljqk.check(R.id.wlj);
                    break;
            }
        }
        if (null != bean.getWljdyy()) {
            for (int i = 0; i < Md_System_Datas.getWljyy().size(); i++) {
                if (bean.getWljdyy().equals(Md_System_Datas.getWljyy().get(i).getName())) {
                    wljdyy.setSelection(i);
                    break;
                }
            }
        }

        if (null != bean.getFxyxlyy()) {
            for (int i = 0; i < DictionaryManager.getInstance().getFxyclyy().size(); i++) {
                if (bean.getFxyxlyy().equals(DictionaryManager.getInstance().getFxyclyy().get(i).getCode())) {
                    fxyclyy.setSelection(i);
                    break;
                }
            }
        }
        if (null != bean.getSfxycl()) {
            switch (bean.getSfxycl()) {
                case "1":
                    sfxycl.check(R.id.shi);
                    break;
                case "0":
                    sfxycl.check(R.id.fou);
                    break;
            }
        }
        if (null != bean.getLjqkms() || "".equals(bean.getLjqkms())) {
            ljqkms.setText(bean.getLjqkms());
        } else {
            ljqkms.setText(SharedPreferencesUtils.getString(WarnBackActivity.this, "LJQKMS", ""));
        }
        if (null != bean.getWsbh() && !"".equals(bean.getWsbh())) {
            wsbhrow.setVisibility(View.VISIBLE);
            wsbh.setText(bean.getWsbh());
        } else {
            wsbhrow.setVisibility(View.GONE);
        }
        if (null != bean.getCjry())
            cjry.setText(bean.getCjry());
        if (null != bean.getZpid() && bean.getZpid().size() > 0) {
            mList.addAll(bean.getZpid());
            mPhotoAdapter.setList(mList);
        }
        jybm.setText(bean.getYjbm());
        if (null != bean.getWslb() && !"".equals(bean.getWslb())) {
            for (int i = 0; i < DictionaryManager.getInstance().getWslb().size(); i++) {
                if (bean.getWslb().equals(DictionaryManager.getInstance().getWslb().get(i).getCode())) {

                    wslb.setSelection(SpinnerValue.getSpinnerValueSelected(bean.getWslb(), wslbList));//文书类别wsList
                    break;
                }
            }
        }


    }

    @Override
    public void commitDatas(String message) {
        ToastUtils.showToast(this, message);
        //        Intent intent = new Intent(this, WarnListActivity.class);
        Intent intent = new Intent(this, WarnTabActivity.class);
        startActivity(intent);
        finish();
    }

    private int count = 0;

    @Override
    public void commitPhotos(String message) {
        //        ToastUtils.showToast(this, message);
        count++;
        if (mList.size() == count) {
            count = 0;
            isPhoto = true;
            loadBackDatas();

        } else {
            isPhoto = false;
        }
    }

    @Override
    public void showFailed(String message) {
        ToastUtils.showToast(this, message);
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
                ToastUtils.showToast(WarnBackActivity.this, R.string.ERROR_TIPS_02);
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


        if (resultCode == RESULT_OK) {
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
        }

    }

    PopupWindow window = null;
    private int flag = 0;

    public void showPopu(final List<String> datas, final int position) {
        flag = position;
        View popupView = this.getLayoutInflater().inflate(R.layout.warn_back_photo, null);
        final ZoomImageViewGlide zoomImage = (ZoomImageViewGlide) popupView.findViewById(R.id.image);
        final TextView btn_syz = (TextView) popupView.findViewById(R.id.btn_syz);
        final TextView btn_cancle = (TextView) popupView.findViewById(R.id.btn_cancle);
        final TextView btn_xyz = (TextView) popupView.findViewById(R.id.btn_xyz);
        final TextView btn_deleted = (TextView) popupView.findViewById(R.id.btn_deleted);
        if (qsztFlag == 2) {
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
                        ToastUtils.showToast(WarnBackActivity.this, R.string.SYSTEM_TIPS_DELETED);
                        if (datas.size() == 0) {
                            window.dismiss();
                        } else {
                            if (flag != 0) {
                                flag = flag - 1;
                            }
                            Glide.with(WarnBackActivity.this).load(datas.get(flag)).into(zoomImage);
                        }
                        mPhotoAdapter.setList(datas);
                    } else {
                        ToastUtils.showToast(WarnBackActivity.this, "删除失败");
                    }
                }
            }
        });
        btn_syz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (flag == 0) {
                    ToastUtils.showToast(WarnBackActivity.this, "当前已经是第一张");
                } else {
                    flag--;
                    Glide.with(WarnBackActivity.this).load(datas.get(flag)).into(zoomImage);
                }
            }
        });
        btn_xyz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (flag == datas.size() - 1) {
                    ToastUtils.showToast(WarnBackActivity.this, "当前已经是最后一张");
                } else {
                    flag++;
                    Glide.with(WarnBackActivity.this).load(datas.get(flag)).into(zoomImage);
                }
            }

        });
    }

}
