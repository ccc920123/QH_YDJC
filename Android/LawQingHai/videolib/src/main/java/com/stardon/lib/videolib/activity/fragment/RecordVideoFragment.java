package com.stardon.lib.videolib.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stardon.lib.videolib.R;
import com.stardon.lib.videolib.activity.VideoActivityLib;
import com.stardon.lib.videolib.activity.utils.ButtonTools;
import com.stardon.lib.videolib.activity.utils.DeleteVideoFile;
import com.stardon.lib.videolib.activity.utils.ScreenUtils;
import com.stardon.lib.videolib.activity.utils.SquareCameraPreview;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.hardware.Camera.open;

/**
 * 类名: RecordVideoFragment
 * <br/>功能描述: 录制视频界面
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/15
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class RecordVideoFragment extends Fragment implements SurfaceHolder.Callback {
    /**
     * tag
     */
    public static final String TAG = RecordVideoFragment.class.getSimpleName();
    /**
     * 常量，相机标识
     */
    public static final String CAMERA_ID_KEY = "camera_id";
    /**
     * 常量，模式标识
     */
    public static final String CAMERA_FLASH_KEY = "flash_mode";
    /**
     * 切换摄像头Relativelayut
     */
    private static RelativeLayout relativeLayoutVideoConvert;
    /**
     * 灯Relativelayut
     */
    private RelativeLayout relativLight;
    /**
     * 切换摄像头
     */
    private TextView videoConvert;
    /**
     * 灯光
     */
    private CheckBox chenckLight;
    /**
     * 聚焦
     */
    private CheckBox checkAutoFocus;

    /**
     * 预览框
     */
    private SquareCameraPreview voidSurfaceView;
    /**
     * 倒计时器
     */
    private TextView mTimer;
    /**
     * 录像界面返回
     */
    private static TextView videoBack;
    /**
     * 录像按钮
     */
    private CheckBox videoButton;
    /**
     * 相机id
     */
    private static int mCameraID;
    /**
     * 相机模式
     */
    private String mFlashMode;
    /**
     * 相机
     */
    private static Camera mCamera;
    /**
     * holder
     */
    private SurfaceHolder mSurfaceHolder;
    /**
     * 预览缩放事件
     */

    private CameraOrientationListener mOrientationListener;
    /**
     * 预览界面最大宽度
     */
    private int PREVIEW_SIZE_MAX_WIDTH;
    /**
     * 视频最大宽度
     */

    private int ViDEO_SIZE_MAX_WIDTH;


//    private static boolean isCamareBack = true;
    /**
     * 录像地址
     */
    private static String videoPath = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/Camera/video/";
    /**
     * 录像名称
     */
    private static String videoName = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ".mp4";
    /**
     * 录制的Media
     */
    private MediaRecorder mMediaRecorder;

    private Camera.Size bestPreviewSize;
    /**
     * 默认倒计时数
     */
    private static long millisInFuture = 0;
    /**
     * 倒计时
     */
    protected CountDownTimer countDownTimer;
    /**
     * 录制视频file
     */
    private File mRecVedioPath;

    private boolean isNormal = false;

    private static VideoActivityLib CameraActivity;
    
    private RelativeLayout select;
    /**
     * 储存摄像头
     */
    SharedPreferences sharedpr;
    /**
     * 方法名称: newInstance
     * <br/>方法详述: 录制界面入口，通过fragment进入
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static Fragment newInstance() {
        return new RecordVideoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recordvideo, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        CameraActivity=(VideoActivityLib)activity;
        mOrientationListener = new CameraOrientationListener(getActivity());//激活多点触控事件
        PREVIEW_SIZE_MAX_WIDTH = ScreenUtils.getScreenWidth(getActivity());//以屏幕宽度来设置预览宽度
        ViDEO_SIZE_MAX_WIDTH = ScreenUtils.getScreenWidth(getActivity());//以屏幕宽度来设置照片宽度
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedpr = getActivity().getSharedPreferences("VideoShp", 0);
        if (savedInstanceState == null) {
            mCameraID=sharedpr.getInt(CAMERA_ID_KEY, getBackCameraID());
//            mCameraID = getBackCameraID();
            mFlashMode = Camera.Parameters.FLASH_MODE_OFF;
        } else {
            mCameraID = savedInstanceState.getInt(CAMERA_ID_KEY);
            mFlashMode = savedInstanceState.getString(CAMERA_FLASH_KEY);
            millisInFuture = savedInstanceState.getLong("TIMER");
        }
        mOrientationListener.enable();
        findViewID(view);
        init();
        CameraActivity.bindView();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences.Editor editor = sharedpr.edit();
        editor.putInt(CAMERA_ID_KEY, mCameraID);
        editor.commit();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CAMERA_ID_KEY, mCameraID);
        outState.putString(CAMERA_FLASH_KEY, mFlashMode);
        outState.putLong("TIMER", millisInFuture);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        if (!isNormal) {
            DeleteVideoFile.deleteFile(mRecVedioPath);
        }
        videoButton.setChecked(false);
        mOrientationListener.disable();
        mTimer.setVisibility(View.GONE);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        relativeLayoutVideoConvert.setVisibility(View.VISIBLE);//显示摄像头切换
        relativLight.setVisibility(View.VISIBLE);//显示灯光
        try {
            if (mMediaRecorder != null) {
                mMediaRecorder.setOnErrorListener(null);
                mMediaRecorder.setOnInfoListener(null);
                mMediaRecorder.setPreviewDisplay(null);
                mMediaRecorder.stop();
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        } catch (Exception e) {

        }
        mOrientationListener.rememberOrientation();
        Camera.ShutterCallback shutterCallback = null;
        Camera.PictureCallback raw = null;
        Camera.PictureCallback postView = null;
        // stop the preview
        if (mCamera != null) {
            stopCameraPreview();
            mCamera.release();
            mCamera = null;
        }
        super.onStop();
    }

    /**
     * 方法名称: findViewID
     * <br/>方法详述iew
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void findViewID(View view) {
        relativeLayoutVideoConvert = (RelativeLayout) view.findViewById(R.id.relativelayoutvideoconvert);
        videoConvert = (TextView) view.findViewById(R.id.videoconvert);
        relativLight = (RelativeLayout) view.findViewById(R.id.relativlight);
        chenckLight = (CheckBox) view.findViewById(R.id.btnlight);
        checkAutoFocus = (CheckBox) view.findViewById(R.id.btnautofocus);
        voidSurfaceView = (SquareCameraPreview) view.findViewById(R.id.voidsurfaceview);
        mTimer = (TextView) view.findViewById(R.id.timer);
        videoBack = (TextView) view.findViewById(R.id.videoback);
        videoButton = (CheckBox) view.findViewById(R.id.videobutton);
        select = (RelativeLayout) view.findViewById(R.id.select);
    }

    /**
     * 方法名称: init
     * <br/>方法详述: 处理逻辑
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void init() {
        //开灯
        chenckLight.setOnCheckedChangeListener(chenckListener);
        //摄像头切换
        videoConvert.setOnClickListener(click);
        //聚焦
        checkAutoFocus.setOnCheckedChangeListener(chenckListener);
        voidSurfaceView.getHolder().addCallback(RecordVideoFragment.this);
        videoButton.setOnClickListener(chenClick);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }

    private View.OnClickListener chenClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
//            if (!ButtonTools.isFastDoubleClick()) {
            CheckBox box = (CheckBox) v;
            isNormal = true;
            if (box.isChecked()) {
                if (millisInFuture == 0) {
                    mTimer.setVisibility(View.GONE);
                } else {
                    mTimer.setVisibility(View.VISIBLE);
                    if (!ButtonTools.isFastDoubleClick()) {

                        if (countDownTimer != null) {
                            countDownTimer.cancel();

                        }

                            countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    mTimer.setText("录像倒计时：" + (millisUntilFinished / 1000));

                                }

                                @Override
                                public void onFinish() {//倒计时完成后
                                    isNormal = true;
                                    mTimer.setVisibility(View.GONE);
                                    videoButton.setChecked(false);
                                    relativeLayoutVideoConvert.setVisibility(View.VISIBLE);//显示摄像头切换
                                    relativLight.setVisibility(View.VISIBLE);//显示灯光
                                    endVideo();
                                }

                            };

                            countDownTimer.start();
                    }
                }


                takeVideo(videoPath, videoName);
                relativeLayoutVideoConvert.setVisibility(View.GONE);//隐藏摄像头切换
                relativLight.setVisibility(View.GONE);//隐藏灯光

            } else {
                relativeLayoutVideoConvert.setVisibility(View.VISIBLE);//显示摄像头切换
                relativLight.setVisibility(View.VISIBLE);//显示灯光
                endVideo();

            }
        }
//        }
    };

    /**
     * 方法名称: endVideo
     * <br/>方法详述: 结束录像
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void endVideo() {
        try {
            if (mMediaRecorder != null) {
                mMediaRecorder.setOnErrorListener(null);
                mMediaRecorder.setOnInfoListener(null);
                mMediaRecorder.setPreviewDisplay(null);
                mMediaRecorder.stop();
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        } catch (Exception e) {

        }
        mOrientationListener.rememberOrientation();
        Camera.ShutterCallback shutterCallback = null;
        Camera.PictureCallback raw = null;
        Camera.PictureCallback postView = null;
        getFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fragment_voideocontainer,
                        VideoPreviewFragment.newInstance(videoPath + videoName),
                        VideoPreviewFragment.TAG).addToBackStack(null).commit();
    }

    /**
     * 方法名称: chenckListener
     * <br/>方法详述:  聚焦和灯光的选中事件。
     * <br/>参数: null
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    private CompoundButton.OnCheckedChangeListener chenckListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView == checkAutoFocus) {
                setupCamera();
            } else if (buttonView == chenckLight) {
                isOpenLight(isChecked);
            } else {//录像


            }

        }
    };
    /**
     * 方法名称: 点击事件
     * <br/>方法详述: 设置切换摄像头的点击事件。点击可以设置当前摄像头的位置（前，后）
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //将灯光至为关闭状态
            chenckLight.setChecked(false);
//            Camera.CameraInfo info = new Camera.CameraInfo();
//            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            if (mCameraID==0) {
                mCameraID = getFrontCameraID();
                // stop the preview
                if (mCamera != null) {
                    stopCameraPreview();
                    mCamera.release();
                    mCamera = null;
                }
                getCamera(mCameraID);
                if (mCamera != null) {
                    startCameraPreview();
                }
            } else {
                mCameraID = getBackCameraID();
                // stop the preview
                if (mCamera != null) {
                    stopCameraPreview();
                    mCamera.release();
                    mCamera = null;
                }
                getCamera(mCameraID);
                if (mCamera != null) {
                    startCameraPreview();
                }
            }


        }
    };

    /**
     * 方法名称: setupCamera
     * <br/>方法详述: 设置相机的parameters参数，包括预览，照片大小，是否连续自动对焦
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    class Test {
        public int width;
        public int height;
        public Test(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
    private List<Camera.Size> videoSizeList;
    private void setupCamera() {
        // Never keep a global parameters
        Camera.Parameters parameters = mCamera.getParameters();
//        List<Camera.Size> prviewSizeList = parameters.getSupportedPreviewSizes();
        videoSizeList = parameters.getSupportedVideoSizes();
        
        bestPreviewSize = determineBestPreviewSize(parameters);
        
        parameters.setPreviewSize(640, 480);
        //设置录像
//        Camera.Size bestPictureSize = determineBestPictureSize(parameters);
//                 parameters.setPictureSize(bestPictureSize.width, bestPictureSize.height);


        //判断是否勾选中聚焦
        if (checkAutoFocus.isChecked()) {
            // Set continuous picture focus, if it's supported
            if (parameters.getSupportedFocusModes().contains(
                    Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                parameters
                        .setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);//照连续聚焦
            }
        } else {
            if (parameters.getSupportedFocusModes().contains(
                    Camera.Parameters.FOCUS_MODE_AUTO)) {
                parameters
                        .setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);//自动焦点
            }
        }
        // Lock in the changes
        mCamera.setParameters(parameters);
    }

    /**
     * 方法名称: takeVideo
     * <br/>方法详述: 开始录像
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    private void takeVideo(String videoPath, String videoName) {
        //检查sd卡是否可用
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getActivity(), "不存在SD卡！",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mRecVedioPath = new File(videoPath);
        //判断地址是否已经存在，不存在就创建。
        if (!mRecVedioPath.exists()) {
            mRecVedioPath.mkdirs();
        }
        try {
            mCamera.unlock();
        } catch (Exception e) {
            return;
        }
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        } else {
            mMediaRecorder.reset();
        }

        CamcorderProfile mProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_CIF);
        
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//音频源
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);//视频源
       
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);//输出格式 mp4
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);//音频编码
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);//视频编码  MPEG_4_SP
        File mRecAudioFile = new File(videoPath + videoName);//视频路径和格式
        mMediaRecorder.setOutputFile(mRecAudioFile.getAbsolutePath());//保存路径
        mMediaRecorder.setVideoSize(640,480);
        mMediaRecorder.setVideoFrameRate(mProfile.videoFrameRate);
//        mMediaRecorder.setVideoEncodingBitRate(mProfile.videoBitRate);
//        mMediaRecorder.setAudioEncodingBitRate(mProfile.audioBitRate);
//        mMediaRecorder.setAudioChannels(mProfile.audioChannels);
//        mMediaRecorder.setAudioSamplingRate(mProfile.audioSampleRate);
        //        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//输出格式 3GP
//        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);//视频编码  H264  ,h263
//        mMediaRecorder.setVideoFrameRate(30);//视频帧率
//        mMediaRecorder.setVideoSize(ScreenUtils.getScreenWidth(getActivity()), ScreenUtils.getScreenHeight(getActivity()));//视频尺寸
        
        mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());//预览
        
        mMediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {

            @Override
            public void onError(MediaRecorder mr, int what, int extra) {
                mMediaRecorder.stop();
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        });

        try {
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getActivity(), "开始录制！", Toast.LENGTH_SHORT)
                .show();


    }

    /**
     * 方法名称:determineBestPreviewSize
     * <br/>方法详述: 预览区域
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private Camera.Size determineBestPreviewSize(Camera.Parameters parameters) {
        return determineBestSize(parameters.getSupportedVideoSizes(),
                PREVIEW_SIZE_MAX_WIDTH);
    }

    /**
     * 方法名称:determineBestPreviewSize
     * <br/>方法详述: 预览照片
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    private Camera.Size determineBestPictureSize(Camera.Parameters parameters) {
        return determineBestSize(parameters.getSupportedPictureSizes(),
                ViDEO_SIZE_MAX_WIDTH);
    }

    /**
     * 方法名称: determineBestSize
     * <br/>方法详述: 选择最佳的照片集合
     * <br/>参数:  list 照片集合
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private Camera.Size determineBestSize(List<Camera.Size> sizes, int widthThreshold) {
        Camera.Size bestSize = null;
        Camera.Size size;
        int numOfSizes = sizes.size();
        for (int i = 0; i < numOfSizes; i++) {
            size = sizes.get(i);
            boolean isDesireRatio = (size.width / 4) == (size.height / 3);
            boolean isBetterSize = (bestSize == null)
                    || size.width > bestSize.width;

            if (isDesireRatio && isBetterSize) {
                bestSize = size;
            }
        }

        if (bestSize == null) {
            Log.d(TAG, "cannot find the best camera size");
            return sizes.get(sizes.size() - 1);
        }

        return bestSize;
    }

    /**
     * 方法名称: startCameraPreview()
     * <br/>方法详述: 开始预览拍照
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void startCameraPreview() {
//        determineDisplayOrientation();
        setupCamera();

        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Can't start camera preview due to IOException " + e);
            e.printStackTrace();
        }
    }

    /**
     * 方法名称: stopCameraPreview
     * <br/>方法详述: 停止预览
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void stopCameraPreview() {
        // Nulls out callbacks, stops face detection
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        voidSurfaceView.setCamera(null);
    }

    /**
     * 方法名称: restartPreview()
     * <br/>方法详述: 重新开是预览
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void restartPreview() {
        stopCameraPreview();
        mCamera.release();

        getCamera(mCameraID);
        if (mCamera != null) {
            startCameraPreview();
        } else {
            restartPreview();
        }
    }

    /**
     * 方法名称: getCamera
     * <br/>方法详述: 通过传入摄像头id，打开相机
     * <br/>参数: 摄像头id
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void getCamera(int cameraID) {
        Log.d(TAG, "get camera with id " + cameraID);
        try {
            mCamera = open(cameraID);
            voidSurfaceView.setCamera(mCamera);
        } catch (Exception e) {
            Log.d(TAG, "Can't open camera with id " + cameraID);
            getActivity().onBackPressed();
            getActivity().finish();
            e.printStackTrace();
        }
    }

    /**
     * 方法名称: isOpenLight()
     * <br/>方法详述: 是否开启等灯光
     * <br/>参数:  boolean
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void isOpenLight(boolean isChaeck) {
//判断手机是否支持灯光
        if (getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            if (isChaeck) {
                Camera.Parameters parameters = mCamera
                        .getParameters();
                parameters
                        .setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                mCamera.setParameters(parameters);
                // camera.startPreview();
            } else {
                Camera.Parameters parameters = mCamera
                        .getParameters();
                parameters
                        .setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(parameters);
                // camera.startPreview();
            }
        } else {
            Toast.makeText(getActivity(), "当前设备部支持闪光", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 方法名称: getBackCameraID()
     * <br/>方法详述: 得到后摄像头
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private static int getBackCameraID() {
//        isCamareBack = true;
        return Camera.CameraInfo.CAMERA_FACING_BACK;
    }

    /**
     * 方法名称: getFrontCameraID（）
     * <br/>方法详述: 得到前摄像头,如果不支持直接得后摄像头
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private int getFrontCameraID() {
        PackageManager pm = getActivity().getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
//            isCamareBack = false;
            return Camera.CameraInfo.CAMERA_FACING_FRONT;
        }

        return getBackCameraID();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceHolder = holder;
        getCamera(mCameraID);
        if (mCamera != null) {
            startCameraPreview();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    /**
     * 类名: CameraOrientationListener
     * <br/>功能描述: 相机预览界面自动旋转类，在调用相机时手机预览界面与成像成颠倒情况
     * <br/>作者: 陈渝金
     * <br/>时间: 2016/11/9
     * <br/>最后修改者:
     * <br/>最后修改内容:
     */

    private static class CameraOrientationListener extends
            OrientationEventListener {

        private int mCurrentNormalizedOrientation;
        private int mRememberedNormalOrientation;

        public CameraOrientationListener(Context context) {
            super(context, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            if (orientation != ORIENTATION_UNKNOWN) {
                mCurrentNormalizedOrientation = normalize(orientation);
            }
        }

        private int normalize(int degrees) {
            if (degrees > 315 || degrees <= 45) {
                return 0;
            }

            if (degrees > 45 && degrees <= 135) {
                return 90;
            }

            if (degrees > 135 && degrees <= 225) {
                return 180;
            }

            if (degrees > 225 && degrees <= 315) {
                return 270;
            }

            throw new RuntimeException(
                    "The physics as we know them are no more. Watch out for anomalies.");
        }

        public void rememberOrientation() {
            mRememberedNormalOrientation = mCurrentNormalizedOrientation;
        }

        public int getRememberedNormalOrientation() {
            return mRememberedNormalOrientation;
        }
    }

    //===============方法暴露区======================

    /**
     * 方法名称: getCameraVideoBack
     * <br/>方法详述: 得到拍照界面的返回按钮，你可以拿到该按钮设置自己喜欢的颜色，字体等包括事件
     * <br/>参数:
     * <br/>返回值:cameraBack返回按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static TextView getCameraVideoBack() {
        return videoBack;
    }

    /**
     * 方法名称: setCountdown
     * <br/>方法详述: 设置倒计时时间
     * <br/>参数: millisInFutureDate  时间，毫秒
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static void setCountdown(long millisInFutureDate) {
        millisInFuture = millisInFutureDate;
    }

    /**
     * 方法名称: setVideoPath
     * <br/>方法详述: 设置录像的存放地址，录像的名字
     * <br/>参数: String Path 地址,String Name名字
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static void setVideoPath(String Path, String Name) {
        if (!"".equals(Path)) {
            String str = Path.substring(Path.length() - 1, Path.length());
            if ("/".equals(str)) {
                videoPath = Path;
            } else {
                videoPath = Path + "/";
            }
        }
        if (!"".equals(Name)) {
            videoName = Name;
        }

    }

    /**
     * 方法名称: setIsVisibleCameraConvertButton
     * <br/>方法详述: 设置拍照预览界面是否显示转换摄像头按钮，此设置取决于用户。默认情况下显示
     * <br/>参数:boolean    true显示  false  隐藏
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static void setIsVisibleVideoConvertButton(boolean isVisible) {
        if (isVisible) {
            relativeLayoutVideoConvert.setVisibility(View.VISIBLE);
        } else {
            relativeLayoutVideoConvert.setVisibility(View.GONE);
        }
    }

    /**
     * 方法名称: setDefaulVideoCamera
     * <br/>方法详述: 设置拍照摄像头的位置，默认为后摄像头。
     * <br/>参数:int   0:后摄像头    1：前摄像头
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static void setDefaultVideoCamera(int cameraID) {
        mCameraID = cameraID;
    }
}
