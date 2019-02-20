package com.scxd.idcardlibs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.List;

import static android.hardware.Camera.getCameraInfo;

public class CameraManager {
    private static final String TAG = CameraManager.class.getName();
    private Camera camera;
    private Camera.Parameters parameters;
    private AutoFocusManager autoFocusManager;
    private int requestedCameraId = -1;

    private boolean initialized;
    private boolean previewing;

    private Size mPreviewSize;
    private List<Size> mSupportedPreviewSizes;

    public void setFlag(int flag) {
        this.flag = flag;
    }

    Camera.CameraInfo cameraInfo;

    private int flag = 0;

    private Activity mContext;
    public CameraManager(Activity context) {
        this.mContext = context;
    }

    /**
     * 打开摄像头
     *
     * @param cameraId 摄像头id
     * @return Camera
     */
    public Camera open(int cameraId, Context mContext) {
        int nucameras = Camera.getNumberOfCameras();
        if (nucameras == 0) {
            Log.e(TAG, "No cameras!");
            return null;
        }
        boolean explicitRequest = cameraId >= 0;
        if (!explicitRequest) {
            // Select a camera if no explicit camera requested
            int index = 0;
            while (index < nucameras) {
                cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(index, cameraInfo);
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    break;
                }
                index++;
            }
            cameraId = index;
        }
        if (cameraId < nucameras) {
            Log.e(TAG, "Opening camera #" + cameraId);
            camera = Camera.open(cameraId);
        } else {
            if (explicitRequest) {
                Log.e(TAG, "Requested camera does not exist: " + cameraId);
                camera = null;
            } else {
                Log.e(TAG, "No camera facing back; returning camera #0");
                camera = Camera.open(0);
            }
        }
        mDefaultPreviewSize = camera.getParameters().getPreviewSize();
        setSize(flag);
        return camera;
    }

    private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.2;
        double targetRatio = (double) w / h;

        if (sizes == null) return null;

        Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }


        return optimalSize;
    }

    /**
     * 打开camera
     *
     * @param holder SurfaceHolder
     * @throws IOException IOException
     */
    private Size mDefaultPreviewSize ;
    public synchronized void openDriver(SurfaceHolder holder, Context mContext)
            throws IOException {
        Log.e(TAG, "openDriver");
        Camera theCamera = camera;
        if (theCamera == null) {
            theCamera = open(requestedCameraId, mContext);
            if (theCamera == null) {
                throw new IOException();
            }
            camera = theCamera;
        }
        determineDisplayOrientation();
        theCamera.setPreviewDisplay(holder);

        //        if (!initialized) {
        //            initialized = true;
        //            parameters = camera.getParameters();
        //            parameters.setPreviewSize(800, 600);
        //            parameters.setPictureFormat(ImageFormat.JPEG);
        //            parameters.setJpegQuality(100);
        //            parameters.setPictureSize(800, 600);
        ////            theCamera.setParameters(parameters);
        //        }
    }
    /**
     * 设置旋转角度
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    private void determineDisplayOrientation() {
        getCameraInfo(0, cameraInfo);

        int rotation = mContext.getWindowManager().getDefaultDisplay()
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

        camera.setDisplayOrientation(displayOrientation);

    }
    /**
     * camera是否打开
     *
     * @return camera是否打开
     */
    public synchronized boolean isOpen() {
        return camera != null;
    }

    /**
     * 关闭camera
     */
    public synchronized void closeDriver() {
        Log.e(TAG, "closeDriver");
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    /**
     * 开始预览
     */
    public synchronized void startPreview(Camera.PreviewCallback mp) {
        Log.e(TAG, "startPreview");
        Camera theCamera = camera;
        if (theCamera != null && !previewing) {
            //            theCamera.setOneShotPreviewCallback(mp);
            theCamera.startPreview();
            previewing = true;
            autoFocusManager = new AutoFocusManager(camera, mp);
        }
        //        camera.setPreviewCallback(new Camera.PreviewCallback() {
        //            @Override
        //            public void onPreviewFrame(byte[] data, Camera camera) {
        //                Log.d("zka","onPreviewFrame");
        //            }
        //        });
    }

    /**
     * 关闭预览
     */
    public synchronized void stopPreview() {
        Log.e(TAG, "stopPreview");
        if (autoFocusManager != null) {
            autoFocusManager.stop();
            autoFocusManager = null;
        }
        if (camera != null && previewing) {
            camera.stopPreview();
            previewing = false;
        }
    }
    
    public void setSize(int flag){
        Camera.Parameters parameters = camera.getParameters();
        if (flag == 0) {
            mSupportedPreviewSizes = camera.getParameters()
                    .getSupportedPreviewSizes();
            if (mSupportedPreviewSizes != null) {
                mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, getScreenWidth(mContext), getScreenHeight(mContext));

            }
            parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
        } else {
            parameters.setPreviewSize(mDefaultPreviewSize.width, mDefaultPreviewSize.height);
        }
        camera.setParameters(parameters);
    }
    
    /**
     * 打开闪光灯
     */
    public synchronized void openLight() {
        Log.e(TAG, "openLight");
        if (camera != null) {
            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
        }
    }

    /**
     * 关闭闪光灯
     */
    public synchronized void offLight() {
        Log.e(TAG, "offLight");
        if (camera != null) {
            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
        }
    }

    /**
     * 拍照
     *
     * @param shutter ShutterCallback
     * @param raw     PictureCallback
     * @param jpeg    PictureCallback
     */
    public synchronized void takePicture(final Camera.ShutterCallback shutter, final Camera.PictureCallback raw,
                                         final Camera.PictureCallback jpeg) {

        camera.takePicture(shutter, raw, jpeg);


    }

    public int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context 上下文
     * @return 屏幕除去通知栏的高度
     */
    public int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
