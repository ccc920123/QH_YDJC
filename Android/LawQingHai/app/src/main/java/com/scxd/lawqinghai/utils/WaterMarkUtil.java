package com.scxd.lawqinghai.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.InitActivity;
import com.scxd.lawqinghai.activity.LoginActivity;
import com.scxd.lawqinghai.activity.MainActivity;
import com.scxd.lawqinghai.activity.WelcomeActivity;

/**
 * 描述":水印工具
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/13
 * 修改人：
 * 修改时间：
 */


public class WaterMarkUtil {

    private static TextView waterView, waterView2, waterView3;

    public static boolean showWatermarkView(final Activity activity, String text) {
        final ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        View framView = LayoutInflater.from(activity).inflate(R.layout.layout_watermark, null);
        waterView = (TextView) framView.findViewById(R.id.water_marktext);
        waterView2 = (TextView) framView.findViewById(R.id.water_marktext2);
        waterView3 = (TextView) framView.findViewById(R.id.water_marktext3);
        waterView.setText(text);
        waterView2.setText(text);
        waterView3.setText(text);
        //可对水印布局进行初始化操作
        rootView.addView(framView);
        return true;
    }

}
