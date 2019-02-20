package com.scxd.lawqinghai.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.ElectronicMonitoringInputDetailResBean;
import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresDetailResBean;
import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresListResBean;
import com.scxd.lawqinghai.bean.response.SummaryPunishmentDetailResBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.PrintImagePersenter;
import com.scxd.lawqinghai.mvp.view.PrintImageView;
import com.scxd.lawqinghai.nfcexputils.Esc;
import com.scxd.lawqinghai.nfcexputils.EscDefine;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.ZXingUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.scxd.lawqinghai.nfcexputils.Nfc_const.KEY_DATA;

public class PrintImageActivity extends BaseActivity implements PrintImageView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.dy_button)
    Button dyButton;
    @BindView(R.id.message)
    TextView message;

    @BindView(R.id.titlemodenotification)
    TextView titlemodenotification;
    @BindView(R.id.title2modenotification)
    TextView title2modenotification;
    @BindView(R.id.nomodenotification)
    TextView nomodenotification;
    @BindView(R.id.dsrmodenotification)
    TextView dsrmodenotification;
    @BindView(R.id.lxfsmodenotification)
    TextView lxfsmodenotification;
    @BindView(R.id.sfzhmmodenotification)
    TextView sfzhmmodenotification;
    @BindView(R.id.dabhmodenotification)
    TextView dabhmodenotification;
    @BindView(R.id.fzjgmodenotification)
    TextView fzjgmodenotification;
    @BindView(R.id.zjcxmodenotification)
    TextView zjcxmodenotification;
    @BindView(R.id.hphmmodenotification)
    TextView hphmmodenotification;
    @BindView(R.id.cllxmodenotification)
    TextView cllxmodenotification;
    @BindView(R.id.wfxwflyjmodenotification)
    TextView wfxwflyjmodenotification;
    @BindView(R.id.wpmcmodenotification)
    TextView wpmcmodenotification;
    @BindView(R.id.smmodenotification)
    TextView smmodenotification;
    @BindView(R.id.jtjcmodenotification)
    TextView jtjcmodenotification;
    @BindView(R.id.sjmodenotification)
    TextView sjmodenotification;
    @BindView(R.id.dsryymodenotification)
    TextView dsryymodenotification;
    @BindView(R.id.bclrqmmodenotification)
    TextView bclrqmmodenotification;
    @BindView(R.id.bzmodenotification)
    TextView bzmodenotification;
    @BindView(R.id.qmmsjmodenotification)
    TextView qmmsjmodenotification;
    @BindView(R.id.titlemodesummary)
    TextView titlemodesummary;
    @BindView(R.id.title2modesummary)
    TextView title2modesummary;
    @BindView(R.id.nomodesummary)
    TextView nomodesummary;
    @BindView(R.id.bcfrmodesummary)
    TextView bcfrmodesummary;
    @BindView(R.id.dabhmodesummary)
    TextView dabhmodesummary;
    @BindView(R.id.sfzhmodesummary)
    TextView sfzhmodesummary;
    @BindView(R.id.lxfsmodesummary)
    TextView lxfsmodesummary;
    @BindView(R.id.clhpmodesummary)
    TextView clhpmodesummary;
    @BindView(R.id.cllxmodesummary)
    TextView cllxmodesummary;
    @BindView(R.id.fzjgmodesummary)
    TextView fzjgmodesummary;
    @BindView(R.id.zjcxmodesummary)
    TextView zjcxmodesummary;
    @BindView(R.id.wfxwflyjmodesummary)
    TextView wfxwflyjmodesummary;
    @BindView(R.id.cfddmodesummary)
    TextView cfddmodesummary;
    @BindView(R.id.jtjcmodesummary)
    TextView jtjcmodesummary;
    @BindView(R.id.bclrqmmodesummary)
    TextView bclrqmmodesummary;
    @BindView(R.id.bzmodesummary)
    TextView bzmodesummary;
    @BindView(R.id.sjmodesummary)
    TextView sjmodesummary;
    @BindView(R.id.bottommodesummary)
    TextView bottommodesummary;
    @BindView(R.id.modelnotification)
    LinearLayout modelnotification;
    @BindView(R.id.modelsummary)
    LinearLayout modelsummary;
    @BindView(R.id.titlemodeelectorn)
    TextView titlemodeelectorn;
    @BindView(R.id.title2modeelectorn)
    TextView title2modeelectorn;
    @BindView(R.id.nomodeelectorn)
    TextView nomodeelectorn;
    @BindView(R.id.clhpmodeelectorn)
    TextView clhpmodeelectorn;
    @BindView(R.id.clysmodeelectorn)
    TextView clysmodeelectorn;
    @BindView(R.id.cllxmodeelectorn)
    TextView cllxmodeelectorn;
    @BindView(R.id.wftcsjmodeelectorn)
    TextView wftcsjmodeelectorn;
    @BindView(R.id.wftcddmodeelectorn)
    TextView wftcddmodeelectorn;
    @BindView(R.id.wfxwflyjmodeelectorn)
    TextView wfxwflyjmodeelectorn;
    @BindView(R.id.sjmodeelectorn)
    TextView sjmodeelectorn;
    @BindView(R.id.bottommodeelectorn)
    TextView bottommodeelectorn;
    @BindView(R.id.modelelectorn)
    LinearLayout modelelectorn;
    @BindView(R.id.bottommodesummaryimg)
    ImageView bottommodesummaryimg;
    @BindView(R.id.imgewm)
    LinearLayout imgewm;
    @BindView(R.id.bottommodesummarytips)
    TextView bottommodesummarytips;

    private String xh;
    private String tag;
    private String tag2;
    private String url;


    //    NfcPrinter nfcPrinter;
    public static Handler UImHandler;

    private Map<String, String> cllxMap;
    private Map<String, String> csysMap;
    private Map<String, String> qzlxMap;
    //    private Bitmap mbitmap;


    SummaryPunishmentDetailResBean summaryBean;
    NotificationOfCompulsoryMeasuresDetailResBean notificationBean;
    ElectronicMonitoringInputDetailResBean electronicBean;

    private CountDownTimer timer = new CountDownTimer(30000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            String string = "请贴近打印机实现打印<font color='#ff0000'>" + (millisUntilFinished / 1000) + "</font>秒后将失效";

            message.setText(Html.fromHtml(string));
        }

        @Override
        public void onFinish() {
            message.setVisibility(View.GONE);
            dyButton.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_print_image;
    }

    @Override
    protected void initInjector() {
        title.setText("决定书打印");
        try {
            xh = getIntent().getStringExtra("XH");
            tag = getIntent().getStringExtra("TAG");//SummaryPunishmentDetail   详情界面过来
            tag2 = getIntent().getStringExtra("TAG2");//   车辆查询界面
        } catch (Exception e) {
            tag2 = "";
        }
        //处理exp 342 NFC打印机需要的初始化数据
        //        nfcPrinter = new NfcPrinter(this);
        //        boolean result = nfcPrinter.check_loacl_NFC_device();//判断是否支持Nfc
        //        if (!result)
        //            return;
        //        nfcPrinter.setNfcForeground();
    }

    /**
     * 取消倒计时
     */
    public void oncancel() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void restart() {
        timer.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initEventAndData() {
        dyButton.setEnabled(false);
        dyButton.setText("正在准备打印内容");
        dyButton.setBackground(getResources().getDrawable(R.drawable.gray_btn));
        cllxMap = new HashMap<>();
        csysMap = new HashMap<>();
        qzlxMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getCllx()) {
            cllxMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getCsys()) {
            csysMap.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getQzcslx()) {

            qzlxMap.put(bean.getCode(), bean.getName());
        }
        if ("ListSummaryPunishmentDecisionActivity".equals(tag) || "SummaryPunishmentDetail".equals(tag)) {//简易处罚决定书

            ((PrintImagePersenter) mPresenter).LoadDetail(xh, "Q20");

        } else if ("ListNotificationOfCompulsoryMeasuresActivity".equals(tag) || 
                "NotificationOfCompulsoryMeasuresDetail".equals(tag))//强制措施通知书
        {
            ((PrintImagePersenter) mPresenter).LoadDetail(xh, "Q21");
        } else if ("ListElectronicMonitoringInputActivity".equals(tag) || "ElectronicMonitoringInputDetail".equals
                (tag))//电子录入
        {
            ((PrintImagePersenter) mPresenter).LoadDetail(xh, "Q22");

        }

        //        ((PrintImagePersenter) mPresenter).queryPrintImage(xh);


    }

    public static boolean hasNfc(Context context) {
        boolean bRet = false;
        if (context == null)
            return bRet;
        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled()) {
            // adapter存在，能启用
            bRet = true;
        }
        return bRet;
    }

    //    @SuppressLint("NewApi")
    //    @Override
    //    public void onPause() {
    //        super.onPause();
    //        if (nfcPrinter != null) {
    //            nfcPrinter.disableForegroundDispatch();
    //        }
    //
    //    }

    //    @TargetApi(Build.VERSION_CODES.KITKAT)
    //    @Override
    //    public void onResume() {
    //        super.onResume();
    //        if (nfcPrinter != null) {
    //            nfcPrinter.regist();
    //        }
    //    }

    @Override
    public void onStart() {
        super.onStart();


        UImHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) { //接收工作线程返回的消息
                Bundle bundle = msg.getData();
                byte[] data = bundle.getByteArray(KEY_DATA);
                String str = new String(data);
                if ("1".equals(str)) {
                    //1表示成功，成功后上传打印标识
                    if ("ListSummaryPunishmentDecisionActivity".equals(tag) || "SummaryPunishmentDetail".equals(tag))
                    {//简易处罚决定书

                        ((PrintImagePersenter) mPresenter).callBackPrintImage(xh, "0");

                    } else if ("ListNotificationOfCompulsoryMeasuresActivity".equals(tag) || 
                            "NotificationOfCompulsoryMeasuresDetail".equals(tag))//强制措施通知书
                    {
                        ((PrintImagePersenter) mPresenter).callBackPrintImage(xh, "1");
                    } else if ("ListElectronicMonitoringInputActivity".equals(tag) || 
                            "ElectronicMonitoringInputDetail".equals(tag))//电子录入
                    {
                        ((PrintImagePersenter) mPresenter).callBackPrintImage(xh, "2");

                    }


                } else {
                    Toast.makeText(PrintImageActivity.this, str, Toast.LENGTH_LONG).show();
                }
                dyButton.setVisibility(View.VISIBLE);
                message.setVisibility(View.GONE);
                oncancel();
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public BasePresenter getPresenter() {
        return new PrintImagePersenter();
    }


    @OnClick({R.id.back, R.id.dy_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.dy_button:

                DyButton();
                break;
        }
    }

    public void DyButton() {

        if (!hasNfc(this)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("请确定手机是否有NFC功能，或者NFC功能是否开启");
            builder.setCancelable(false);
            builder.setNegativeButton("返  回", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();
                }
            });
            builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));

                }
            });
            builder.show();
        } else {

            //开始倒计时
            restart();
            message.setVisibility(View.VISIBLE);
            dyButton.setVisibility(Button.GONE);
            byte[] data = makeModel();//获取需要发送的数据
            //                byte[] data = Image.printOutimage(mbitmap);
            this.nfcPrinter.startPrintTask(data, 1000 * 30);//开启线程发送数据

            //30秒的相应，如果30后没有操作，请重新打印

            //
        }
    }

    @Override
    public void showLoadProgressDialog(String str) {

    }

    @Override
    public void disDialog() {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void backPrintImageSucced() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("系统提示：").setMessage("打印已结束，是否需要再次打印？").setNegativeButton("取消", new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("CarLaw".equals(tag2)) {

                } else {
                    Intent intent = new Intent(PrintImageActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }).setPositiveButton("打印", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DyButton();
            }
        }).show();
    }

    @Override
    public void queryPrintImageUrl(String url) {


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void backQueryDetail(Object obj, String jkid) {


        if ("Q20".equals(jkid)) {
            summaryBean = (SummaryPunishmentDetailResBean) obj;
            url = summaryBean.getQrCode();
            modelsummary.setVisibility(View.VISIBLE);
            modelnotification.setVisibility(View.GONE);
            modelelectorn.setVisibility(View.GONE);
            activityMouldSummary();

        } else if ("Q21".equals(jkid)) {
            notificationBean = (NotificationOfCompulsoryMeasuresDetailResBean) obj;
            modelsummary.setVisibility(View.GONE);
            modelnotification.setVisibility(View.VISIBLE);
            modelelectorn.setVisibility(View.GONE);
            activityMouldNotification();

        } else if ("Q22".equals(jkid)) {

            electronicBean = (ElectronicMonitoringInputDetailResBean) obj;
            modelsummary.setVisibility(View.GONE);
            modelnotification.setVisibility(View.GONE);
            modelelectorn.setVisibility(View.VISIBLE);
            activityMouldElectronic();


        }

        dyButton.setEnabled(true);
        dyButton.setText("打印");
        dyButton.setBackground(getResources().getDrawable(R.drawable.login_btn));


    }


    @Override
    public void onBackPressed() {
        oncancel();

        Intent intent;
        if ("ListSummaryPunishmentDecisionActivity".equals(tag) || "SummaryPunishmentDetail".equals(tag)) {//简易处罚决定书

            if ("CarLaw".equals(tag2)) {
                
            } else {
                intent = new Intent(this, SummaryPunishmentDecisionActivity.class);
                startActivity(intent);
            }
        } else if ("ListNotificationOfCompulsoryMeasuresActivity".equals(tag) || 
                "NotificationOfCompulsoryMeasuresDetail".equals(tag))//强制措施通知书
        {
            intent = new Intent(this, NotificationOfCompulsoryMeasuresActivity.class);
            startActivity(intent);

        } else if ("ListElectronicMonitoringInputActivity".equals(tag) || "ElectronicMonitoringInputDetail".equals
                (tag))//电子录入
        {
            intent = new Intent(this, ElectronicMonitoringInputActivity.class);
            startActivity(intent);

        }

        finish();
    }


    /****************NFC打印处理*******************/

    public static void setMessageCallback(String datarate) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(KEY_DATA, datarate.getBytes());
        Message msg = new Message();
        msg.setData(bundle);
        UImHandler.sendMessage(msg);
    }

    /**
     * 获取需要发送的数据
     *
     * @return
     */
    private byte[] makeModel() {
        Esc esc = new Esc(2048);
        esc.reset();
        //        esc.feedRightMark();//走纸走到右黑边
        //        esc.feedLeftMark();
        esc.feedLines(2);
        if ("ListSummaryPunishmentDecisionActivity".equals(tag) || "SummaryPunishmentDetail".equals(tag)) {//简易处罚决定书

            createMouldSummary(esc);//创建简易

        } else if ("ListNotificationOfCompulsoryMeasuresActivity".equals(tag) || 
                "NotificationOfCompulsoryMeasuresDetail".equals(tag))//强制措施通知书
        {
            createMouldNotification(esc);
        } else if ("ListElectronicMonitoringInputActivity".equals(tag) || "ElectronicMonitoringInputDetail".equals
                (tag))//电子录入
        {
            createMouldElectronic(esc);

        }
        esc.feedLines(3);

        byte[] mouldData = esc.getESCData();

        esc.mouldDefine(EscDefine.MOULD_ID.m0, mouldData, mouldData.length);

        //        esc.variableDefineString(EscDefine.VAR_ID.v0, "第一联(处罚人留档)\r\n");
        esc.mouldRun(EscDefine.MOULD_ID.m0, 1);

        //        esc.variableDefineString(EscDefine.VAR_ID.v0, "第二联(处罚人留档)\r\n");
        //        esc.mouldRun(EscDefine.MOULD_ID.m0, 1);

        return esc.getESCData();
    }

    /**
     * 创建简易打印模版
     */
    public boolean createMouldSummary(Esc esc) {

        //走到黑标
        //titile

        esc.setAlign(EscDefine.ALIGN.CENTER);//居左对齐
        esc.text("\r\r" + summaryBean.getBmqc() + "\r\n");
        esc.setTextSize(EscDefine.BAR_TEXT_SIZE.ASCII_12x24);
        esc.text("公安交通管理简易程序处罚决定书\r\n");
        esc.setAlign(EscDefine.ALIGN.RIGHT);
        esc.text("编号：" + summaryBean.getJdsbh() + "\r\n");
        esc.setAlign(EscDefine.ALIGN.LEFT);
        esc.text("被处罚人:" + summaryBean.getDsr() + "\r\n");
        esc.text("机动车驾驶证档案编号：" + summaryBean.getDabh() + "\r\n");
        esc.text("机动车驾驶证/居民身份证号码：" + "\r\n");
        esc.text(summaryBean.getJszh() + "\r\n");
        esc.text("联系方式：" + summaryBean.getDh() + "\n车辆牌号：" + summaryBean.getHphm() + "\r\n");
        esc.text("车辆类型：" + (null == cllxMap.get(summaryBean.getCllx()) ? "" : cllxMap.get(summaryBean.getCllx())) + 
                "\n发证机关：" + summaryBean.getFzjg() + "\r\n");
        esc.text("准驾车型：" + summaryBean.getZjcx() + "\r\n");
        esc.text("    被处罚人于" + Date_U.TimestampToTimestamp2(summaryBean.getWfsj()) + ",在" + summaryBean.getWfdz() + 
                "实施" 
                + (null == summaryBean.getWfnr() ? "" : summaryBean.getWfnr()) + "违法行为(代码" + summaryBean.getWfxw() + 
                ")违反了" + "\r\n");
        esc.text((null == summaryBean.getFlyj() ? "" : summaryBean.getFlyj()) + "\r\n");
        esc.text("    决定处以:  记" + ("".equals(summaryBean.getWfjf()) ? "0" : summaryBean.getWfjf()) + "分,罚款" + (""
                .equals
                (summaryBean.getFkje()) ? "0" : summaryBean.getFkje()) + "元\r\n");
        esc.text("    持本决定书在15日内到" + summaryBean.getYh() + "缴纳罚款。逾期不缴纳的，每日按罚款数额的的3%加处罚款。\r\n");

        esc.text("    如不服本决定，可以在收到本决定书之日起60日内向" + summaryBean.getSjbm() + "申请行政复议；");
        if (null != summaryBean.getXzssjg() && !"".equals(summaryBean.getXzssjg())) {
            esc.text("或者在3个月内向" + summaryBean.getXzssjg() + "起行政诉讼。\n");
        } else {
            esc.text("或者在3个月内向" + summaryBean.getXzss() + "起行政诉讼。\n");
        }

        esc.text("交通警察：" + summaryBean.getZqmj() + "\r\n");
        esc.text("被处理人签名：_________" + "\r\n");
        esc.text("备注：_________\r\n");
        esc.setAlign(EscDefine.ALIGN.RIGHT);
        esc.text(Date_U.getDateTimeByMillisecond() + "\r\n");
        esc.setAlign(EscDefine.ALIGN.LEFT);
        esc.text("根据《机动车驾驶证申请和使用规定》记" + summaryBean.getWfjf() + "分\r\n");
        esc.text("注：未作罚款处罚或者当场收缴罚款的,银行联不交被处罚人\r\n");
        //        esc.variablePrintOut(EscDefine.VAR_ID.v0);
        //条码
        //        esc.barcodeSet1DHeight(80);
        //        esc.barcodeSetTextPosition(EscDefine.BAR_TEXT_POS.BOTTOM);
        //        esc.barcodeCode39Auto("12456789");
        //        esc.text("\r\r");
        if (null != url && !"".equals(url)) {
            esc.setAlign(EscDefine.ALIGN.CENTER);
            if (url.length() > 200){
                esc.barcode2D_QRCode(url, 0, 2, EscDefine.ESC_BAR_UNIT.x4);
            } else {
                esc.barcode2D_QRCode(url, 12, 2, EscDefine.ESC_BAR_UNIT.x4);
            }
            esc.text("\r\n");
            esc.text("使用12123平台APP可以在线扫码缴款");
        }
        //        esc.text(100, 25, "交款");
        //        esc.text(100, 50, "请下载APP");
        //        esc.setXY(210, 0);
        //        esc.barcode2D_QRCode("www.jqsh.com", 0, 2, EscDefine.ESC_BAR_UNIT.x3);
        esc.text("\r\n");
        return true;
    }

    /**
     * 显示简易界面
     *
     * @return
     */
    public void activityMouldSummary() {
        titlemodesummary.setText(summaryBean.getBmqc());
        title2modesummary.setText("公安交通管理简易程序处罚决定书");
        nomodesummary.setText("编号：" + summaryBean.getJdsbh());
        bcfrmodesummary.setText("被处罚人:" + summaryBean.getDsr());
        dabhmodesummary.setText("机动车驾驶证档案编号：" + summaryBean.getDabh());
        sfzhmodesummary.setText("机动车驾驶证/居民身份证号码：" + "\r\n" + summaryBean.getJszh());
        lxfsmodesummary.setText("联系方式：" + summaryBean.getDh());
        clhpmodesummary.setText("车辆牌号：" + summaryBean.getHphm());
        cllxmodesummary.setText("车辆类型：" + (null == cllxMap.get(summaryBean.getCllx()) ? "" : cllxMap.get(summaryBean
                .getCllx())));
        fzjgmodesummary.setText("发证机关：" + summaryBean.getFzjg());
        zjcxmodesummary.setText("准驾车型：" + summaryBean.getZjcx());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t\t" + "被处罚人于" + Date_U.TimestampToTimestamp2(summaryBean.getWfsj()) + ",在" + summaryBean
                .getWfdz() + "实施" + (null == summaryBean.getWfnr() ? "" : summaryBean.getWfnr()) + "违法行为(代码" + 
                summaryBean.getWfxw() + ")违反了" + "\r\n");
        stringBuilder.append((null == summaryBean.getFlyj() ? "" : summaryBean.getFlyj()) + "\r\n");
        stringBuilder.append("\t\t" + "决定处以:  记" + ("".equals(summaryBean.getWfjf()) ? "0" : summaryBean.getWfjf()) + "分,罚款" +
                (""
                .equals(summaryBean.getFkje()) ? "0" : summaryBean.getFkje()) + "元\r\n");
        stringBuilder.append("\t\t" + "持本决定书在15日内到" + summaryBean.getYh() + "缴纳罚款。逾期不缴纳的，每日按罚款数额的的3%加处罚款。\r\n");
        stringBuilder.append("\t\t" + "如不服本决定，可以在收到本决定书之日起60日内向" + summaryBean.getSjbm() + "申请行政复议；");
        if (null != summaryBean.getXzssjg() && !"".equals(summaryBean.getXzssjg())) {
            stringBuilder.append("或者在3个月内向" + summaryBean.getXzssjg() + "提起行政诉讼。");
        } else {
            stringBuilder.append("或者在3个月内向" + summaryBean.getXzss() + "提起行政诉讼。");
        }

        wfxwflyjmodesummary.setText(stringBuilder.toString());
        jtjcmodesummary.setText("交通警察：" + summaryBean.getZqmj());
        bclrqmmodesummary.setText("被处理人签名：_________");
        bzmodesummary.setText("备注：_________");
        sjmodesummary.setText(Date_U.getDateTimeByMillisecond() + "\r\n");

        bottommodesummary.setText("根据《机动车驾驶证申请和使用规定》记" + summaryBean.getWfjf() + "分\r\n注：未作罚款处罚或者当场收缴罚款的," +
                "银行联不交被处罚人\r\n");

        if (null != url && !"".equals(url)) {
            imgewm.setVisibility(View.VISIBLE);
            bottommodesummarytips.setVisibility(View.VISIBLE);
            bottommodesummaryimg.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    bottommodesummaryimg.getViewTreeObserver().removeOnPreDrawListener(this);
                    bottommodesummaryimg.setImageBitmap(ZXingUtils.createQRImage(url,
                            bottommodesummaryimg.getWidth(),  bottommodesummaryimg.getHeight()));
                    return false;
                }
            });

        }


    }

    /**
     * 创建强制打印模版
     */
    public boolean createMouldNotification(Esc esc) {
        //        esc.reset();

        esc.setAlign(EscDefine.ALIGN.CENTER);//居左对齐
        esc.text("\r\r" + notificationBean.getBmqc() + "\r\n");
        esc.setTextSize(EscDefine.BAR_TEXT_SIZE.ASCII_12x24);
        esc.text("公安交通管理行政强制措施凭证\r\n");
        esc.setAlign(EscDefine.ALIGN.RIGHT);
        esc.text("编号：" + notificationBean.getJdsbh() + "\r\n");
        esc.setAlign(EscDefine.ALIGN.LEFT);
        esc.text("当事人:" + notificationBean.getDsr() + " 联系方式：" + notificationBean.getDh() + "\r\n");
        esc.text("机动车驾驶证/居民身份证号码：" + "\r\n");
        esc.text(notificationBean.getJszh() + "\r\n");
        esc.text("机动车驾驶证档案编号：\n" + notificationBean.getDabh() + "\r\n");
        esc.text("发证机关：" + notificationBean.getFzjg() + " 准驾车型：" + notificationBean.getZjcx() + "\r\n");
        esc.text("号牌号码：" + notificationBean.getHphm() + " 车辆类型：" + (null == cllxMap.get(notificationBean.getCllx()) ?
                "" : cllxMap.get(notificationBean.getCllx())) + "\r\n");
        esc.text("当事人于" + Date_U.TimestampToTimestamp2(notificationBean.getWfsj()) + ",在" + notificationBean.getWfdz
                () + "实施" + "\r\n");
        List<NotificationOfCompulsoryMeasuresListResBean> wfxwlist = notificationBean.getWfxwlist();
        for (int i = 0; i < wfxwlist.size(); i++) {

            esc.text((null == wfxwlist.get(i).getWfnr() ? "" : wfxwlist.get(i).getWfnr()) + "违法行为(代码" + wfxwlist.get
                    (i).getWfxw() + ")\r\n");

        }
        esc.text("根据：\r\n");
        for (int i = 0; i < wfxwlist.size(); i++) {
            esc.text(wfxwlist.get(i).getFlyj() + "\r\n");
        }
        if (null != notificationBean.getQzcslx() && !"".equals(notificationBean.getQzcslx())) {

            char[] qzcslxChar = notificationBean.getQzcslx().toCharArray();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < qzcslxChar.length; i++) {

                builder.append(qzlxMap.get(String.valueOf(qzcslxChar[i])));
                if (i != qzcslxChar.length - 1) {
                    builder.append(",");
                }

            }


            esc.text("采取行政强制措施：" + builder.toString() + "\r\n");
        }

        String sjwpmc = notificationBean.getSjwpmc();
        sjwpmc = sjwpmc.replaceAll("#", "、");
        if (null != sjwpmc && !"".equals(sjwpmc)) {
            esc.text("收缴" + sjwpmc + "\r\n");
        }
        esc.text("   请持本凭证在15日内到" + notificationBean.getBmqc() + "接受处理。逾期不处理的，依法承担法律" + "责任。如不服本决定，可以在收到本凭证之日起60日内向" 
                + notificationBean.getSjbm() + "申请行政复议；" + "或者在6个月内向");
        if (null != notificationBean.getXzssjg() && !"".equals(notificationBean.getXzssjg())) {
            esc.text(notificationBean.getXzssjg() + "提起行政诉讼。\r\n");
        } else {
            esc.text(notificationBean.getXzss() + "提起行政诉讼。\r\n");
        }
        esc.text("交通警察：" + notificationBean.getZqmj() + "\r\n");
        esc.setAlign(EscDefine.ALIGN.RIGHT);
        esc.text(Date_U.getDateTimeByMillisecond() + "\r\n");
        esc.setAlign(EscDefine.ALIGN.LEFT);
        esc.text("当事人对本凭证记载内容有无异议：" + notificationBean.getDsryy() + "\r\n");
        esc.text("被处理人签名：_________" + "\r\n");
        esc.text("备注：" + notificationBean.getBz() + "\n");
        esc.setAlign(EscDefine.ALIGN.RIGHT);
        esc.text(Date_U.getDateTimeByMillisecond() + "\r\n");
        esc.setAlign(EscDefine.ALIGN.LEFT);
        //        esc.text("本凭证同时作为现场笔录\r\n");

        //        esc.setAlign(EscDefine.ALIGN.CENTER);
        //
        //        esc.barcode2D_QRCode("www.jqsh.com", 12, 2, EscDefine.ESC_BAR_UNIT.x4);
        //        esc.text("\r\n");
        //        esc.text("使用12123平台APP或青海支付平台APP可以在线扫码缴款\r\n");
        esc.text("\r\n");
        return true;
    }

    /**
     * 界面显示数据
     *
     * @return
     */
    public void activityMouldNotification() {

        titlemodenotification.setText(notificationBean.getBmqc());
        title2modenotification.setText("公安交通管理行政强制措施凭证");
        nomodenotification.setText("编号：" + notificationBean.getJdsbh());
        dsrmodenotification.setText("当事人:" + notificationBean.getDsr());
        lxfsmodenotification.setText("联系方式：" + notificationBean.getDh());
        sfzhmmodenotification.setText("机动车驾驶证/居民身份证号码：" + "\r\n" + notificationBean.getJszh());
        dabhmodenotification.setText("机动车驾驶证档案编号：" + notificationBean.getDabh());
        fzjgmodenotification.setText("发证机关：" + notificationBean.getFzjg());
        zjcxmodenotification.setText("准驾车型：" + notificationBean.getZjcx());
        hphmmodenotification.setText("号牌号码：" + notificationBean.getHphm());
        cllxmodenotification.setText("车辆类型：" + (null == cllxMap.get(notificationBean.getCllx()) ? "" : cllxMap.get
                (notificationBean.getCllx())));
        StringBuilder builder = new StringBuilder();
        builder.append("\t\t" + "当事人于" + Date_U.TimestampToTimestamp2(notificationBean.getWfsj()) + ",在" + notificationBean
                .getWfdz() + "实施" + "\r\n");
        List<NotificationOfCompulsoryMeasuresListResBean> wfxwlist = notificationBean.getWfxwlist();
        for (int i = 0; i < wfxwlist.size(); i++) {

            builder.append((null == wfxwlist.get(i).getWfnr() ? "" : wfxwlist.get(i).getWfnr()) + "违法行为(代码" + 
                    wfxwlist.get(i).getWfxw() + ")\r\n");

        }
        builder.append("\t\t" + "根据：\r\n");
        for (int i = 0; i < wfxwlist.size(); i++) {
            builder.append(wfxwlist.get(i).getFlyj() + "\r\n");
        }
        if (null != notificationBean.getQzcslx() && !"".equals(notificationBean.getQzcslx())) {

            char[] qzcslxChar = notificationBean.getQzcslx().toCharArray();
            StringBuilder builderqzcs = new StringBuilder();
            for (int i = 0; i < qzcslxChar.length; i++) {

                builderqzcs.append(qzlxMap.get(String.valueOf(qzcslxChar[i])));
                if (i != qzcslxChar.length - 1) {
                    builderqzcs.append(",");
                }

            }

            builder.append("采取行政强制措施：" + builderqzcs.toString());
        }
        wfxwflyjmodenotification.setText(builder.toString());
        String sjwpmc = notificationBean.getSjwpmc();
        sjwpmc = sjwpmc.replaceAll("#", "、");
        if (null != sjwpmc && !"".equals(sjwpmc)) {
            wpmcmodenotification.setText("收缴" + sjwpmc);
        }

        if (null != notificationBean.getXzssjg() && !"".equals(notificationBean.getXzssjg())) {
            smmodenotification.setText("   请持本凭证在15日内到" + notificationBean.getBmqc() + "接受处理。逾期不处理的，依法承担法律" + 
                    "责任。如不服本决定，可以在收到本凭证之日起60日内向" + notificationBean.getSjbm() + "申请行政复议；" + "或者在6个月内向" + 
                    notificationBean.getXzssjg() + "提起行政诉讼。\r\n");
        } else {
            smmodenotification.setText("   请持本凭证在15日内到" + notificationBean.getBmqc() + "接受处理。逾期不处理的，依法承担法律" + 
                    "责任。如不服本决定，可以在收到本凭证之日起60日内向" + notificationBean.getSjbm() + "申请行政复议；" + "或者在6个月内向" + 
                    notificationBean.getXzss() + "提起行政诉讼。\r\n");
        }

        jtjcmodenotification.setText("交通警察：" + notificationBean.getZqmj());
        sjmodenotification.setText(Date_U.getDateTimeByMillisecond() + "\r\n");
        dsryymodenotification.setText("当事人对本凭证记载内容有无异议：" + notificationBean.getDsryy());
        bclrqmmodenotification.setText("被处理人签名：_________");
        bzmodenotification.setText("备注：" + notificationBean.getBz());
        qmmsjmodenotification.setText(Date_U.getDateTimeByMillisecond() + "\r\n");

    }

    /**
     * 创建电子打印模版
     */
    public boolean createMouldElectronic(Esc esc) {
        //        esc.reset();

        //titile

        esc.setAlign(EscDefine.ALIGN.CENTER);//居左对齐
        esc.text("\r\r" + electronicBean.getBmqc() + "\r\n");
        esc.setTextSize(EscDefine.BAR_TEXT_SIZE.ASCII_12x24);
        esc.text("违法停车告知单\r\n");
        esc.setAlign(EscDefine.ALIGN.LEFT);
        esc.text("编号：" + (null == electronicBean.getJdsbh() ? "" : electronicBean.getJdsbh()) + "\r\n");
        esc.text("车辆号牌:" + electronicBean.getHphm() + "\r\n");
        esc.text("车身颜色：" + (null == csysMap.get(electronicBean.getCsys()) ? "" : csysMap.get(electronicBean.getCsys()
        )) + "\r\n");
        esc.text("车辆类型：" + (null == cllxMap.get(electronicBean.getCllx()) ? "" : cllxMap.get(electronicBean.getCllx()
        )) + "\r\n");
        esc.text("违法停车时间：" + electronicBean.getWfsj() + "\r\n");
        esc.text("违法停车地点：" + electronicBean.getWfdz() + "\r\n");
        esc.text("\n\n该机动车在上述时间、地点停放，违反了" + electronicBean.getFlyj() + electronicBean.getWfnr() + "。请车主于3日后15日内到" + 
                electronicBean.getBmqc() + "接受处理(外地车辆所有人也可以到车辆登记地公安机关交通管理部门接受处理)。" + "\r\n");
        esc.text("\r\n");
        esc.text("\r\n");
        esc.setAlign(EscDefine.ALIGN.RIGHT);
        esc.text(Date_U.getDateTimeByMillisecond() + "\r\n");
        esc.text("\r\n");
        esc.text("\r\n");
        esc.setAlign(EscDefine.ALIGN.LEFT);
        esc.text("备注：机动车所有人登记的住址或者联系电话发生变化的,请及时向登记车辆管理所申请变更备案\r\n");
        //        esc.text("\r\n");

        //        esc.setAlign(EscDefine.ALIGN.CENTER);
        //
        //        esc.barcode2D_QRCode("www.jqsh.com", 12, 2, EscDefine.ESC_BAR_UNIT.x4);
        //        esc.text("\r\n");
        //        esc.text("使用12123平台APP或青海支付平台APP可以在线扫码缴款\r\n");
        esc.text("\r\n");
        return true;
    }

    /**
     * 显示电子数据界面
     *
     * @return
     */
    public void activityMouldElectronic() {


        titlemodeelectorn.setText(electronicBean.getBmqc());
        title2modeelectorn.setText("违法停车告知单");
        nomodeelectorn.setText("编号：" + (null == electronicBean.getJdsbh() ? "" : electronicBean.getJdsbh()));
        clhpmodeelectorn.setText("车辆号牌:" + electronicBean.getHphm());
        clysmodeelectorn.setText("车身颜色：" + (null == csysMap.get(electronicBean.getCsys()) ? "" : csysMap.get
                (electronicBean.getCsys())));
        cllxmodeelectorn.setText("车辆类型：" + (null == cllxMap.get(electronicBean.getCllx()) ? "" : cllxMap.get
                (electronicBean.getCllx())));
        wftcsjmodeelectorn.setText("违法停车时间：" + electronicBean.getWfsj());
        wftcddmodeelectorn.setText("违法停车地点：" + electronicBean.getWfdz());
        wfxwflyjmodeelectorn.setText("\n该机动车在上述时间、地点停放，违反了" + electronicBean.getFlyj() + (null == electronicBean
                .getWfnr() ? "" : electronicBean.getWfnr()) + "。请车主于3日后15日内到" + electronicBean.getBmqc() + "接受处理" +
                "(外地车辆所有人也可以到车辆登记地公安机关交通管理部门接受处理)。" + "\r\n\r\n\r\n");

        sjmodeelectorn.setText(Date_U.getDateTimeByMillisecond() + "\r\n\r\n");

        bottommodeelectorn.setText("备注：机动车所有人登记的住址或者联系电话发生变化的,请及时向登记车辆管理所申请变更备案\r\n\r\n\r\n");

    }


    //    SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
    //        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    //        @Override
    //        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
    //
    //
    //            dyPicture.setImageBitmap(resource);
    //            dyButton.setEnabled(true);
    //            dyButton.setText("打印");
    //            dyButton.setBackground(getResources().getDrawable(R.drawable.login_btn));
    //
    //        }
    //    };


    /***
     * 功能：用线程保存图片
     *
     * @author wangyp
     *
     */
   /* private class SaveImage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String sdcard = MyApplication.getFileLocation() + "/PrintImage";
                File file = new File(sdcard);
                if (!file.exists()) {
                    file.mkdirs();
                }

                file = new File(sdcard + new Date().getTime() + "print.jpg");
                InputStream inputStream = null;
                URL url = new URL(imageUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(20000);
                if (conn.getResponseCode() == 200) {
                    inputStream = conn.getInputStream();
                }
                byte[] buffer = new byte[4096];
                int len = 0;
                FileOutputStream outStream = new FileOutputStream(file);
                while ((len = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                outStream.close();
                result = "1";
            } catch (Exception e) {
                result = "准备失败！" + e.getLocalizedMessage();
            }
            return result;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(String result) {
            if ("1".equals(result)) {
                dyButton.setEnabled(true);
                dyButton.setText("打印");
                dyButton.setBackground(getResources().getDrawable(R.drawable.login_btn));
            } else {

                Toast.makeText(PrintImageActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }*/
   
}
