package com.scxd.lawqinghai.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.MessageEvent;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.JpushRepBean;
import com.scxd.lawqinghai.bean.request.WarnJpushBean;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.WarnJpushRspBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.network.NetWorking;
import com.scxd.lawqinghai.utils.AssestToString;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.IntentUtils;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.TTSUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;

/**
 * @类名: ${type_name}
 * @功能描述: 推送
 * @作者: ${user}     Lee
 * @时间: ${date}     2017 - 9 - 21
 * @最后修改者:
 * @最后修改内容:
 */
public class NotificationService extends Service {

    private Timer timer;

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final boolean isExit = intent.getBooleanExtra("exit", false);
        if (isExit) {
            if (timer != null) {
                timer.cancel();
            }
            stopSelf(1);
            stopSelf();

        } else {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Common.ISJPUSH = SharedPreferencesUtils.getString(getApplication(), Common.user, "0");
                    if (Common.ISLOGIN && "1".equals(Common.ISJPUSH))
                        QueryJPush();


                }
            }, 0, 10 * 1000);

        }
        return Service.START_REDELIVER_INTENT;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static Context context;

    public static void startService(Context mContext, boolean isExit) {
        context = mContext;
        Intent gpsIntent = new Intent();
        gpsIntent.setAction("com.scxd.lawqinghai.service.NotificationService");
        gpsIntent.putExtra("exit", isExit);
        Intent eintent = new Intent(IntentUtils.getExplicitIntent(mContext, gpsIntent));
        mContext.startService(eintent);
    }


    public synchronized void notifationInit() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication());
        builder.setSmallIcon(R.drawable.applogo);
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText(getString(R.string.app_notifaction));
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.applogo));
        //        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setLights(Color.GREEN, 1500, 1000);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setTicker("有新任务");
        //        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri path = Uri.parse("android.resource://com.scxd.lawqinghai/" + R.raw.tishi);
        builder.setSound(path);

        Intent intent = new Intent(getApplication(), NotificationBroadcastReceiver.class);
        intent.setAction("notification_clicked");
        intent.putExtra("YJXH", warnListBean.getYjxh());
        int id = (int) (System.currentTimeMillis() / 1000);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplication(), id, intent, PendingIntent
                .FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        //        builder.setFullScreenIntent(pendingIntent,false);
        //        builder.setAutoCancel(true);
        Notification nf = builder.build();
        nf.flags = Notification.FLAG_SHOW_LIGHTS;
        nf.flags |= Notification.FLAG_AUTO_CANCEL;
        manager = (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(50, nf);
        MyApplication.notifyManager = manager;
        Log.e("Notification", "Notification");

        yjlxMap = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getYjlx()) {
            yjlxMap.put(bean.getCode(), bean.getName());
        }
        try {
            Thread.sleep(1000);
            if (null == warnListBean.getHphm() || "-".equals(warnListBean.getHphm())
                    || "".equals(warnListBean.getHphm())){
                TTSUtils.getInstance().speak("无号牌。");
                Thread.sleep(1500);
            } else {
                TTSUtils.getInstance().speak(warnListBean.getHphm().replace("", "，"));
                Thread.sleep(3500);
            }
            TTSUtils.getInstance().speak(yjlxMap.get(warnListBean.getYjlx()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Handler hand = new Handler();
    private NotificationManager manager;

    class RefreshTask extends AsyncTask<Integer,Integer,String> {


        //        @Override
        //        public void run() {
        //            hand.post(new Runnable() {
        //                @Override
        //                public void run() {
        //
        //                    notifationInit();
        //                }
        //            });
        //        }

        @Override
        protected String doInBackground(Integer... integers) {
            notifationInit();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }

    }

    private WarnJpushRspBean.DataBean warnListBean = null;
    private Map<String, String> yjlxMap = null;
    public void QueryJPush() {
        WarnJpushBean bean = new WarnJpushBean(
                SharedPreferencesUtils.getString(getApplication(), "USER", ""),
                SharedPreferencesUtils.getString(this, "SSBMBH", ""),
                MyApplication.getJd() == null ? "" : MyApplication.getJd(),
                MyApplication.getWd() == null ? "" : MyApplication.getWd());
        NetWorking.requstJsonNetData("Q12", Common.QUERY, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                //                if (Common.isDataTest) {
                //                    onResponse("cyjtest", 1);
                //                } else {
                e.printStackTrace();
                //                }
            }

            @Override
            public void onResponse(String response, int id) {
                if (Common.isDataTest) {
                    response = AssestToString.xmltoString(MyApplication.appContext, "dataTest/Q12.json");
                }
                LogUtil.open().appendMethodB("requestQ12:" + response);
                WarnJpushRspBean jsonObj = JSON.parseObject(response, WarnJpushRspBean.class);
                if (jsonObj.getMeta().isSuccess()) {
                    warnListBean = jsonObj.getData();
                    if (!warnListBean.getYjxh().equals("")) {
                        WriteJPush(warnListBean.getYjxh());
                        EventBus.getDefault().post(new MessageEvent(true,warnListBean.getYjxh(),warnListBean.getHphm(),
                                warnListBean.getCllx(),warnListBean.getYjsj(),warnListBean.getYjlx(),warnListBean.getStatus()));
                        RefreshTask task = new RefreshTask();
                        task.execute();
                    }
                }
            }
        });
    }

    public void WriteJPush(final String yjxh) {
        JpushRepBean bean = new JpushRepBean(SharedPreferencesUtils.getString(getApplication(), "USER",
                ""), yjxh);
        NetWorking.requstJsonNetData("W07", Common.WRITE, bean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.open2().appendMethod(Date_U.getCurrentTime2() + yjxh + "回执失败，网络错误", "/Jpush.txt");
            }

            @Override
            public void onResponse(String response, int id) {
                BaseResponseJson jsonObj = new Gson().fromJson(response.toString(), BaseResponseJson.class);
                if (jsonObj.getMeta().isSuccess()) {
                    //LogUtil.open2().appendMethod(Date_U.getCurrentTime2() + yjxh + "回执成功", "/Jpush.txt");
                }
            }
        });
    }
}
//jhfghfh