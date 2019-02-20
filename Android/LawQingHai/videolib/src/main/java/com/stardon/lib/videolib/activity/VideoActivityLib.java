package com.stardon.lib.videolib.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.stardon.lib.videolib.R;
import com.stardon.lib.videolib.activity.fragment.RecordVideoFragment;
import com.stardon.lib.videolib.activity.impl.VideoBase;
import com.stardon.lib.videolib.activity.impl.VideoPreviewImp;

public abstract class VideoActivityLib extends FragmentActivity implements VideoPreviewImp, VideoBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 屏幕长亮
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        //获取系统的设置
        int flag = Settings.System.getInt(getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0);
        //为0，表示系统不是自适应屏幕设置，此时将系统设置为自适应屏幕设置
        if (0 == flag) {
            Settings.System.putInt(getContentResolver(),
                    Settings.System.ACCELEROMETER_ROTATION, 1);
        }
        setContentView(R.layout.activity_video);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_voideocontainer, RecordVideoFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindView();
    }

    /**
     * 方法名称: setCountdown
     * <br/>方法详述: 设置倒计时
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static void setCountdown(long timer) {
        RecordVideoFragment.setCountdown(timer);
    }

    /**
     * 方法名称: setVideoPath
     * <br/>方法详述: 设置录像的存放地址，录像的名字
     * <br/>参数: String Path 地址,String Name名字
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static void setVideoPath(String videoPath, String videoName) {
        RecordVideoFragment.setVideoPath(videoPath, videoName);
    }

    /**
     * 方法名称: setIsVisibleVideoConvertButton
     * <br/>方法详述: 是否显示摄像头转换按钮
     * <br/>参数: boolean true：显示；false：隐藏
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    protected void setIsVisibleVideoConvertButton(boolean isVisiable) {
        RecordVideoFragment.setIsVisibleVideoConvertButton(isVisiable);

    }

    /**
     * 方法名称: setDefaulVideoCamera
     * <br/>方法详述: 设置拍照摄像头的位置，默认为后摄像头。
     * <br/>参数:int   0:后摄像头    1：前摄像头
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    protected void setDefaulVideoCamera(int CameraID) {
        RecordVideoFragment.setDefaultVideoCamera(CameraID);
    }

    /**
     * 方法名称: getCameraVideoBack
     * <br/>方法详述: 得到拍照界面的返回按钮，你可以拿到该按钮设置自己喜欢的颜色，字体等包括事件
     * <br/>参数:
     * <br/>返回值:cameraBack返回按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    protected static TextView getCameraVideoBack() {

        return RecordVideoFragment.getCameraVideoBack();
    }
}
