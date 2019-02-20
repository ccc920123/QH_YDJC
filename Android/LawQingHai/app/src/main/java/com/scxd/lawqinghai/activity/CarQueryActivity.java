package com.scxd.lawqinghai.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parkingwang.vehiclekeyboard.PopupKeyboard;
import com.parkingwang.vehiclekeyboard.ScenClick;
import com.parkingwang.vehiclekeyboard.core.KeyboardType;
import com.parkingwang.vehiclekeyboard.view.InputView;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.HorizontalListView;
import com.scxd.lawqinghai.widget.ZoomImageViewGlide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @类名: ${type_name}
 * @功能描述:  机动车查询条件
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarQueryActivity extends BaseActivity implements ScenClick {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.carzl)
    Spinner carzl;
/*    @BindView(R.id.hpzl)
    Spinner hpzl;*/
/*    @BindView(R.id.hphm)
    EditText hphm;*/
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.carnuber)
    InputView carnuber;
    private PopupKeyboard mPopupKeyboard;
    private final  int HPHM_SCAN_REQUEST_CODE=200;

    private List<DicationResBean> hpzlMap = null;
    
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_query;
    }

    @Override
    protected void initInjector() {
        title.setText(R.string.jdcxxcx);
    }

    @Override
    protected void initEventAndData() {
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(this);
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(carnuber, this);
        
        //获取号牌种类   适配数据
        hpzlMap = DictionaryManager.getInstance().getHpzl();
        ArrayAdapter<DicationResBean> hpzlAdapter = new ArrayAdapter<DicationResBean>(this, android.R.layout.simple_spinner_item,
                hpzlMap);
        hpzlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carzl.setAdapter(hpzlAdapter);
        carzl.setSelection(0); //默认为第0个
    }

    @OnClick({R.id.back, R.id.btn_query})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.btn_query:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    String hphm = carnuber.getNumber();
                    String hpzl = hpzlMap.get(carzl.getSelectedItemPosition()).getCode();
                    if ("".equals(hphm) || "".equals(hpzl) || hphm.length() < 7) {    //判断内容是否为空 号码长度
                        ToastUtils.showToast(this, R.string.SYSTEM_TIPS_HPHM);
                        return;
                    }
                    intent = new Intent(this, CarInformationActivity.class);
                    intent.putExtra("hphm", hphm);
                    intent.putExtra("hpzl", hpzl);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    /**
     * 点击的方法
     * @param mContext
     */
    @Override
    public void scenClick(Context mContext) {

        Intent intent = new Intent(CarQueryActivity.this,
                ScenCameraActivity.class);

        startActivityForResult(intent, HPHM_SCAN_REQUEST_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HPHM_SCAN_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String hphm = data.getCharSequenceExtra("number").toString();
            carnuber.updateNumber(hphm);    //号码返回结果
//            String hpzl = hpzlMap.get(carzl.getSelectedItemPosition()).getCode();
//            if ("".equals(hphm) || "".equals(hpzl) || hphm.length() < 7) {    //判断内容是否为空 号码长度
//                ToastUtils.showToast(this, R.string.SYSTEM_TIPS_HPHM);
//                return;
//            }
//            Intent intent = new Intent(this, CarInformationActivity.class);
//            intent.putExtra("hphm", hphm);
//            intent.putExtra("hpzl", hpzl);
//            startActivity(intent);
//            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (mPopupKeyboard.isShown()){
            mPopupKeyboard.dismiss(this);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    PopupWindow window = null;
    private int mScreenH, mScreenW, popupHeight, popupWidth;
    private void showPopu() {
        if (window != null){
            return;
        }
        final View popupView = this.getLayoutInflater().inflate(R.layout.keyborad_popu, null);
        HorizontalListView listView = (HorizontalListView) popupView.findViewById(R.id.listview);
        window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewTreeObserver vto1 = popupView.getViewTreeObserver();
        vto1.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                popupHeight = popupView.getMeasuredHeight();
                popupWidth = popupView.getMeasuredWidth();
                return true;
            }
        });
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(false);
        window.setOutsideTouchable(false);
        window.update();
        mPopupKeyboard.getKeyboardView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int[] location = new int[2];
        mPopupKeyboard.getKeyboardView().getLocationOnScreen(location);
        window.showAtLocation(mPopupKeyboard.getKeyboardView(), Gravity.NO_GRAVITY,
                0,
                location[1] - popupHeight);
        popupView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    //如果PopupWindow处于显示状态，则关闭PopupWindow
                    return true;
                }
                return false;
            }
        });
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
                window = null;
            }
        });

    }
}
