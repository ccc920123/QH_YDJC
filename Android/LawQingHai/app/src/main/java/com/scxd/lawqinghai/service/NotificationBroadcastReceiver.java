package com.scxd.lawqinghai.service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.scxd.lawqinghai.activity.WarnDetailsActivity;
import com.scxd.lawqinghai.activity.WelcomeActivity;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;

/**
 * @类名: ${type_name}
 * @功能描述: 推送
 * @作者: ${user}     Lee
 * @时间: ${date}     2017 - 9 - 21
 * @最后修改者:
 * @最后修改内容:
 */
public class NotificationBroadcastReceiver extends BroadcastReceiver {

    public static final String TYPE = "type"; //这个type是为了Notification更新信息的

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String yjxh = intent.getStringExtra("YJXH");
        int type = intent.getIntExtra(TYPE, -1);

        if (type != -1) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(type);
        }

        if (action.equals("notification_clicked")) {
            //处理点击事件
            Intent newIntent = new Intent();
            if (Common.ISLOGIN) {
                newIntent.setClass(context, WarnDetailsActivity.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                newIntent.putExtra("YJXH", yjxh);
            } else {
                newIntent.setClass(context, WelcomeActivity.class);
            }
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newIntent);


        }
    }
}
//jhfghfh