package com.scxd.lawqinghai.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 描述：字体旋转
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/13
 * 修改人：
 * 修改时间：
 */


@SuppressLint("AppCompatCustomView")
public class RotateTextView extends TextView {


    public RotateTextView(Context context) {
        super(context);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //倾斜度45,上下左右居中
        canvas.rotate(-45, getMeasuredWidth()/2, getMeasuredHeight()/2);
        super.onDraw(canvas);
    }


}
