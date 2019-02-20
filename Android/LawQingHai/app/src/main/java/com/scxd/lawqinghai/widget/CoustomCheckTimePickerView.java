package com.scxd.lawqinghai.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.bigkoo.pickerview.TimePickerView;
import com.scxd.lawqinghai.utils.Date_U;

import java.util.Calendar;
import java.util.Date;

/**类名: CoustomCheckTimePickerView
 * <br/>功能描述:时间选择控件
 */



public class CoustomCheckTimePickerView {

   private static CoustomCheckTimePickerView pickerView;

    public static CoustomCheckTimePickerView getInstance() {
      
        if (pickerView == null) {
           pickerView = new CoustomCheckTimePickerView();
        }
        return pickerView;

    }

    /**
     * 获取时间   "yyyy-MM-dd HH:mm:ss"
     * @param mContext
     * @param view
     * @return
     */
    public long getTime(Context mContext, final TextView view) {
        final long[] timess = {0};
        //时间选择器
        final TimePickerView endTimes = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(Date_U.getDateTimeFromMillisecond2(date));
                timess[0] = date.getTime();
            }
        }).setType(TimePickerView.Type.ALL).isCyclic(true).build();
        endTimes.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        endTimes.show();
        return timess[0];
    }

    /**
     * 获取时间   "yyyy-MM-dd"
     * @param mContext
     * @param view
     * @return
     */
    public long getRqTime(Context mContext, final TextView view) {
        final long[] timess = {0};
        //时间选择器
        final TimePickerView endTimes = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(Date_U.getDateTimeFromMillisecond(date));
                timess[0] = date.getTime();
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY).isCyclic(true).build();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, 1950);
//        calendar.set(Calendar.MONTH, 1);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
        endTimes.setDate(Calendar.getInstance());
        //注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        endTimes.show();
        return timess[0];
    }
    
}




