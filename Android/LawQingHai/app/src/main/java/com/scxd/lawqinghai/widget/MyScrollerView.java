package com.scxd.lawqinghai.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class MyScrollerView extends ScrollView {
    
    private OnScrollChangeListener mOnSeekBarChangeListener = null;
    
    public MyScrollerView(Context context) {
        super(context);
    }

    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnSeekBarChangeListener != null){
            mOnSeekBarChangeListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
    
    public interface OnScrollChangeListener{
        void onScrollChanged(MyScrollerView scrollerView, int x, int y, int oldx, int oldy);
    }
}
//jhfghfh