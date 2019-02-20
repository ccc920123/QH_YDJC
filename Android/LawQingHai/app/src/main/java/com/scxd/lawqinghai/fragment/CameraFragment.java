package com.scxd.lawqinghai.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.CameraAvtivity;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.event.CameraOrientationListener;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.CameraSettings;
import com.scxd.lawqinghai.utils.Date_U;
import com.scxd.lawqinghai.utils.SensorControler;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.CameraFrameView;
import com.scxd.lawqinghai.widget.FocusImageView;
import com.scxd.lawqinghai.widget.PreviewFrameLayout;
import com.scxd.lawqinghai.widget.SquareCameraPreview;
import com.scxd.lawqinghai.widget.dialog.AlertDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.hardware.Camera.getCameraInfo;
import static android.hardware.Camera.open;

/**
 * Created by Administrator on 2017/3/16.
 */
public class CameraFragment extends BaseFragment implements SurfaceHolder.Callback, Camera.PictureCallback {
    @BindView(R.id.flash_icon)
    CheckBox flashIcon;
    @BindView(R.id.flash)
    RelativeLayout flash;
    @BindView(R.id.cameralight)
    CheckBox cameralight;
    @BindView(R.id.camerafocue)
    CheckBox camerafocue;
    @BindView(R.id.camera_preview_view)
    SquareCameraPreview cameraPreviewView;
    @BindView(R.id.cameraback)
    TextView cameraback;
    @BindView(R.id.capture_image_button)
    ImageView captureImageButton;
    @BindView(R.id.camera_tools_view)
    RelativeLayout cameraToolsView;
    @BindView(R.id.pictitle)
    TextView pictitle;
    @BindView(R.id.head)
    SurfaceView head;
    @BindView(R.id.frame)
    FrameLayout mframe;
    @BindView(R.id.frame_layout)
    PreviewFrameLayout frameLayout;
    @BindView(R.id.focusImageView)
    FocusImageView mFocusImageView;
    @BindView(R.id.change_camera)
    ImageView changeCamera;
    private CameraOrientationListener mOrientationListener;
    private SurfaceHolder surfaceHolder = null;
    private int cameraCount;
    /**
     * 相机id
     */
    private int mCameraID;

    /**
     * 相机
     */
    private Camera mCamera;

    Camera.Parameters parameters;

    /**
     * 相机模式
     */
    private String mFlashMode;
    private boolean isFlashOn;
    private boolean isLight;
    private byte[] photoByte;
    private byte[] headByte;
    private int rotationCamera;
    private long time = 0;
    String title = null;
    private String code;
    Camera.CameraInfo cameraInfo;
    private CameraFrameView myViewfinderView;
    /**
     * 敏感区域,裁剪时候用到的参数
     */
 //   private int[] regionPos = new int[4];// 敏感区域

    private float[] regionPosFloat = new float[4];// 敏感区域

    private SensorControler mSensorControler;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    boolean isTakeON = false;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_camera;
    }

    @Override
    protected void initInjector() {
        mSensorControler = SensorControler.getInstance();
//        title = ((CameraAvtivity) getActivity()).getmPhotoMessageRqBean().getPhotoName();
//        code = ((CameraAvtivity) getActivity()).getmPhotoMessageRqBean().getZpzl();
        pictitle.setText(title);
        cameraInfo = new Camera.CameraInfo();
        isTakeON = false;
        cameraCount = Camera.getNumberOfCameras();// 得到摄像头的个数
        mCameraID = SharedPreferencesUtils.getCameraId(getActivity());
        /*if ()
        mCameraID = getBackCameraID();*/
        getCamera(mCameraID);

    }


    @Override
    protected void initEventAndData() {
        //设置相机闪光灯为关闭状态
        mFlashMode = Camera.Parameters.FLASH_MODE_OFF;
        mOrientationListener = new CameraOrientationListener(getActivity());
        mOrientationListener.enable();
        surfaceHolder = cameraPreviewView.getHolder();
        surfaceHolder.addCallback(this);
        // surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);

        cameralight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setFlashModeLight(isChecked);
            }


        });
        flashIcon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // setFlashModeLight(isChecked);
                isFlashOn = isChecked;
            }


        });
        camerafocue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //    setFocusStatus(isChecked);

            }
        });
        //VIN拍照加有效区域是0101 以及 0198 两个照片

        if ("0103".equals(code)) {
            myViewfinderView = new CameraFrameView(getActivity(),1f,0.15f);

            mframe.addView(myViewfinderView);
        }else if("0101".equals(code)||"0198".equals(code)){
            myViewfinderView = new CameraFrameView(getActivity(),0.8f,0.66f);

            mframe.addView(myViewfinderView);
        }
        cameraPreviewView.setmFocusImageView(mFocusImageView);
        cameraPreviewView.setAutoFocusCallback(autoFocusCallback);

    }

    //切换摄像头
    @OnClick(R.id.change_camera)
    public void onClick() {
        if (mCameraID == 1) {
            //现在是后置，变更为前置
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置
                mCamera.stopPreview();//停掉原来摄像头的预览
                mCamera.release();//释放资源
                mCamera = null;//取消原来摄像头
                mCameraID = 0;
                SharedPreferencesUtils.saveCameraId(getActivity(), mCameraID);
                getCamera(mCameraID);//打开当前选中的摄像头
                startCameraPreview();

            }
        } else {
            //现在是前置， 变更为后置
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置
                mCamera.stopPreview();//停掉原来摄像头的预览
                mCamera.release();//释放资源
                mCamera = null;//取消原来摄像头
                mCameraID = 1;
                SharedPreferencesUtils.saveCameraId(getActivity(), mCameraID);
                getCamera(mCameraID);
                startCameraPreview();
            }
        }

//        }
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        mSensorControler.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCamera == null) {
            getCamera(mCameraID);
            startCameraPreview();
        }

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void setLight(boolean isChecked) {

        //判断手机是否支持闪光，支持就显示闪光调节，不支持隐藏闪光调节。
        if (!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            ToastUtils.showToast(getActivity(), "你的手机不支持手电筒！");
            return;
        }
        isLight = isChecked;
        if (isChecked) {
            if (parameters.getSupportedFlashModes().contains(Camera.Parameters.FLASH_MODE_ON)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            }
        }
        mCamera.setParameters(parameters);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceHolder = holder;

        if (mCamera != null) {
            startCameraPreview();

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mCameraID == getFrontCameraID() && isTakeON) {
            MyThread m = new MyThread();
            new Thread(m).start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

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
        determineDisplayOrientation();
        setupCamera();
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置旋转角度
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    private void determineDisplayOrientation() {
        getCameraInfo(mCameraID, cameraInfo);

        int rotation = getActivity().getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;

        switch (rotation) {
            case Surface.ROTATION_0: {
                degrees = 0;
                break;
            }
            case Surface.ROTATION_90: {
                degrees = 90;
                break;
            }
            case Surface.ROTATION_180: {
                degrees = 180;
                break;
            }
            case Surface.ROTATION_270: {
                degrees = 270;

                break;
            }
        }

        int displayOrientation;

        // Camera direction
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            // Orientation is angle of rotation when facing the camera for
            // the camera image to match the natural orientation of the device
            displayOrientation = (cameraInfo.orientation + degrees) % 360;
            displayOrientation = (360 - displayOrientation) % 360;
        } else {
            displayOrientation = (cameraInfo.orientation - degrees + 360) % 360;
        }

        mCamera.setDisplayOrientation(displayOrientation);

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
        try {
            mCamera = open(cameraID);
            cameraPreviewView.setCamera(mCamera);
        } catch (Exception e) {
            displayFrameworkBugMessageAndExit();

            e.printStackTrace();
        }
    }

    /**
     * 未打开相机提示信息
     */
    private void displayFrameworkBugMessageAndExit() {

        new AlertDialog(getActivity()).builder().setTitle("提示")
                .setMsg("打开相机失败，请确认权限以及相机是否可用！")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                }).show();
    }

    @Override
    public void onPause() {
        mOrientationListener.disable();
        super.onPause();
    }

    /**
     * 方法名称: setupCamera
     * <br/>方法详述: 设置相机的parameters参数，包括预览，照片大小，是否连续自动对焦
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void setupCamera() {

        parameters = mCamera.getParameters();
        setPictrueAndPreViewSize();
        // CustomWH.getInstance().getPictureAndPreViewSize(parameters, PICTURE_SIZE_MAX_WIDTH, PICTURE_SIZE_MAX_HIGHT);
     //   setFocusStatus(true);
        //   setFlashModeLight(false);
        //  mCamera.setParameters(parameters);
    }

    /**
     * 设置预览尺寸，和相片大小
     */
    private void setPictrueAndPreViewSize() {
        String pictureSize = SharedPreferencesUtils.getString(getActivity(), CameraSettings.KEY_PICTURE_SIZE, null);
        if (pictureSize == null) {
            CameraSettings.initialCameraPictureSize(getActivity(), parameters);
        } else {
            List<Camera.Size> supported = parameters.getSupportedPictureSizes();
            CameraSettings.setCameraPictureSize(pictureSize, supported, parameters);
        }
        Camera.Size size = parameters.getPictureSize();
        frameLayout.setAspectRatio((double) size.width / size.height);
        mSensorControler.setCameraFocusListener(new SensorControler.CameraFocusListener() {
            @Override
            public void onFocus() {
//                Point point = new Point(ScreenUtil.getScreenWidth(getActivity()) / 2, ScreenUtil.getScreenHeight(getActivity()) / 2);
                Point point = new Point(frameLayout.getWidth() / 2, frameLayout.getHeight() / 2);
                onCameraFocus(point);
            }
        });
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        Camera.Size optimalSize = getOptimalPreviewSize(sizes, (double) size.width / size.height);
        if (optimalSize != null) {
            Camera.Size original = parameters.getPreviewSize();
            if (!original.equals(optimalSize)) {
                parameters.setPreviewSize(optimalSize.width, optimalSize.height);

                // Zoom related settings will be changed for different preview
                // sizes, so set and read the parameters to get lastest values
                mCamera.setParameters(parameters);
                parameters = mCamera.getParameters();
            }
        }
//这里是VIN时候
        if ("0103".equals(code)||"0101".equals(code)||"0198".equals(code)) {
//            size = parameters.getPreviewSize();
//            Camera.Size photosize = parameters.getPictureSize();
          //  float bl = Float.valueOf(photosize.height) / size.height;
           // regionPos[0] = (int) (0);
           // regionPosFloat[0] = 0f;
            regionPosFloat[0] = (1-myViewfinderView.modelWidth)/2f;
         //   regionPos[1] = (int) ((1 - myViewfinderView.modelHeight) / 2 * size.height * bl);
            regionPosFloat[1] = (1 - myViewfinderView.modelHeight) / 2f;
          //  regionPos[2] = (int) (size.width);
          //  regionPosFloat[2] = 1f;
            regionPosFloat[2] = (1-(1-myViewfinderView.modelWidth)/2f);
         //   regionPos[3] = (int) ((1 + myViewfinderView.modelHeight) / 2 * size.height * bl);
            regionPosFloat[3] = (1 + myViewfinderView.modelHeight) / 2f;
        }

    }

    /**
     * 得到预览尺寸
     *
     * @param sizes
     * @param targetRatio
     * @return
     */
    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, double targetRatio) {
        final double ASPECT_TOLERANCE = 0.05;
        if (sizes == null)
            return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        // Because of bugs of overlay and layout, we sometimes will try to
        // layout the viewfinder in the portrait orientation and thus get the
        // wrong size of mSurfaceView. When we change the preview size, the
        // new overlay will be created before the old one closed, which causes
        // an exception. For now, just get the screen size

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int targetHeight = Math.min(display.getHeight(), display.getWidth());

        if (targetHeight <= 0) {
            // We don't know the size of SurefaceView, use screen height
            WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
            targetHeight = windowManager.getDefaultDisplay().getHeight();
        }

        // Try to find an size match aspect ratio and size
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }

    /**
     * 设置聚焦状态
     *
     * @param b
     */
    private void setFocusStatus(boolean b) {
        if (parameters.getSupportedFocusModes().contains(
                Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE) && b) {
            parameters
                    .setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//照连续聚焦
        } else if (parameters.getSupportedFocusModes().contains(
                Camera.Parameters.FOCUS_MODE_AUTO)) {
            parameters
                    .setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);//自动焦点
        }
        mCamera.setParameters(parameters);
    }

    /**
     * 设置闪光灯
     *
     * @param b
     */
    public void setFlashModeLight(boolean b) {
        //判断手机是否支持闪光，支持就显示闪光调节，不支持隐藏闪光调节。
        if (!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            ToastUtils.showToast(getActivity(), "你的手机不支持闪光灯！");
            return;
        }

        //得到相机闪光模式集合
        //  List<String> flashModes = parameters.getSupportedFlashModes();

        if (b || isLight) {//打开闪光灯
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

        } else {//关闭闪光灯
            parameters.setFlashMode(mFlashMode);
        }
        mCamera.setParameters(parameters);
    }

    @Override
    public void onStop() {
        mOrientationListener.disable();
        mSensorControler.onStop();
        // stop the preview
        if (mCamera != null) {
            stopCameraPreview();
            mCamera.release();
            mCamera = null;
        }
        super.onStop();
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
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        cameraPreviewView.setCamera(null);
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
            return Camera.CameraInfo.CAMERA_FACING_FRONT;
        }

        return getBackCameraID();
    }

    /**
     * 方法名称: getBackCameraID()
     * <br/>方法详述: 得到后摄像头
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private int getBackCameraID() {
        return Camera.CameraInfo.CAMERA_FACING_BACK;
    }


    @OnClick({R.id.cameraback, R.id.capture_image_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cameraback://返回
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    getActivity().finish();
                }
                break;
            case R.id.capture_image_button://拍照
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    takePicture();
                    isTakeON = true;
                }

                break;

        }
    }

    /**
     * 方法名称: takePicture
     * <br/>方法详述: 拍照，将预览界面转换成byte
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void takePicture() {
        mOrientationListener.rememberOrientation();
        time = System.currentTimeMillis();
        setFlashModeLight(isFlashOn);
        // jpeg callback occurs when the compressed image is available
        try {
            //系统返回照片数据
            mCamera.takePicture(null, null, null, this);
        } catch (Exception e) {

        }
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        setFlashModeLight(false);
        photoByte = data;

        rotationCamera = mOrientationListener.getRememberedNormalOrientation();
        if (mCameraID == 0) {
            rotationCamera = 360 - rotationCamera;
        }
        if ("98".equals(code)) {

            if (cameraCount > 1) {// T通过摄像头个数来判断是否有前摄像头
                mCameraID = getFrontCameraID();
                mCamera.stopPreview();// 停掉原来摄像头的预览
                mCamera.release();// 释放资源
                mCamera = null;// 取消原来摄像头
                surfaceHolder.removeCallback(CameraFragment.this);
                head.setVisibility(View.VISIBLE);
                head.setZOrderOnTop(true);
                surfaceHolder = head.getHolder();
                surfaceHolder.addCallback(CameraFragment.this);
                surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

            } else {
                Toast.makeText(getActivity(), "没有前摄像头，不能拍前面照", Toast.LENGTH_SHORT).show();
            }
        } else {
            gotoSavePhoto();
        }

    }

    private void gotoSavePhoto() {
        SavePhotoFragment savePhotoFragment = new SavePhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putByteArray(Common.PHOTO_KEY, photoByte);
        bundle.putByteArray(Common.HEAD_PHOTO_KEY, headByte);
        bundle.putFloatArray(Common.VIN_FRAME, regionPosFloat);
        bundle.putString(Common.TIME, Date_U.getStrTime(time));
        bundle.putString(Common.ZPCODE, code);
        // title += "\r\n" + ((CameraAvtivity) getActivity()).getmPhotoMessageRqBean().getClsbdh();
        bundle.putString(Common.TITLE_TAG, title);
        bundle.putInt(Common.ROTATION, rotationCamera);
        bundle.putInt(Common.CAMERAID, mCameraID);
        savePhotoFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fragment_container,
                        savePhotoFragment
                ).addToBackStack(null).commit();
    }


    class MyThread implements Runnable {
        public void run() {
            try {
                Thread.sleep(500);
                takePhotoCall_mall();// 自动拍小照片
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void takePhotoCall_mall() {
        mCamera.takePicture(null, null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                headByte = data;
                gotoSavePhoto();
            }
        });
    }

    /**
     * 相机对焦  默认不需要延时
     *
     * @param point
     */
    private void onCameraFocus(final Point point) {
        onCameraFocus(point, false);
    }

    /**
     * 相机对焦
     *
     * @param point
     * @param needDelay 是否需要延时
     */
    public void onCameraFocus(final Point point, boolean needDelay) {
        long delayDuration = needDelay ? 300 : 0;

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mSensorControler.isFocusLocked()) {
                    if (onFocus(point, autoFocusCallback)) {
                        mSensorControler.lockFocus();
                        cameraPreviewView.mFocusImageView.startFocus(point);


                    }
                }
            }
        }, delayDuration);
    }

    /**
     * 手动聚焦
     *
     * @param point 触屏坐标
     */
    protected boolean onFocus(Point point, Camera.AutoFocusCallback callback) {
        if (mCamera == null) {
            return false;
        }

        Camera.Parameters parameters = null;
        try {
            parameters = mCamera.getParameters();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //不支持设置自定义聚焦，则使用自动聚焦，返回

        if (Build.VERSION.SDK_INT >= 14) {

            if (parameters.getMaxNumFocusAreas() <= 0) {
                return focus(callback);
            }


            List<Camera.Area> areas = new ArrayList<Camera.Area>();
            int left = point.x - 300;
            int top = point.y - 300;
            int right = point.x + 300;
            int bottom = point.y + 300;
            left = left < -1000 ? -1000 : left;
            top = top < -1000 ? -1000 : top;
            right = right > 1000 ? 1000 : right;
            bottom = bottom > 1000 ? 1000 : bottom;
            areas.add(new Camera.Area(new Rect(left, top, right, bottom), 100));
            parameters.setFocusAreas(areas);
            try {
                //本人使用的小米手机在设置聚焦区域的时候经常会出异常，看日志发现是框架层的字符串转int的时候出错了，
                //目测是小米修改了框架层代码导致，在此try掉，对实际聚焦效果没影响
                mCamera.setParameters(parameters);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return false;
            }
        }


        return focus(callback);
    }

    private boolean focus(Camera.AutoFocusCallback callback) {
        try {
            mCamera.autoFocus(callback);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private final Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {

        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            //聚焦之后根据结果修改图片
            if (success) {
                cameraPreviewView. isAutoFocus=true;
                cameraPreviewView.mFocusImageView.onFocusSuccess();
            } else {
                //聚焦失败显示的图片，由于未找到合适的资源，这里仍显示同一张图片
                cameraPreviewView. isAutoFocus=true;
                cameraPreviewView.mFocusImageView.onFocusFailed();
            }
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //一秒之后才能再次对焦
                    mSensorControler.unlockFocus();
                }
            }, 1000);
        }
    };
}