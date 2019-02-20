package com.scxd.lawqinghai.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ScrollView;

/**
 * @类名: ${type_name}
 * @功能描述:   设置最大高度
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class MyScrollerView2 extends ScrollView {
    
    private Context mContext;

    public MyScrollerView2(Context context) {
        this(context, null);
    }

    public MyScrollerView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollerView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
            DisplayMetrics d = new DisplayMetrics();
            display.getMetrics(d);
            // 设置控件最大高度不能超过屏幕高度的一半
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(d.heightPixels / 2, MeasureSpec.AT_MOST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 重新计算控件的宽高
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
//jhfghfh