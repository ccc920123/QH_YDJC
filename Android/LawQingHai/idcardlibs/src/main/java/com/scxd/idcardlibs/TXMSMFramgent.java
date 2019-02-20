package com.scxd.idcardlibs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import java.io.IOException;

import static android.hardware.Camera.getCameraInfo;


/**
 *
 *TXMSM_Activity.java
 * @author chenyujin
 * @Description 条形码扫描 优化扫描点击home键时出现摄像头占用现象
 * @Create Time 2015-6-30
 */
public class TXMSMFramgent extends Fragment implements SurfaceHolder.Callback, PreviewCallback {
    private Camera mCamera;
    private Handler autoFocusHandler;
    private ImageScanner scanner;
    private boolean previewing = true;
    private SurfaceView cameraPreview;
    private SurfaceHolder mHolder;
    Camera.CameraInfo cameraInfo;
    private Long opentime;
    private boolean toggleLight = false;
    private View rootView;

    static {
        System.loadLibrary("iconv");
        System.loadLibrary("zbar");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        rootView = inflater.inflate(R.layout.txmsm_activity,null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        opentime = System.currentTimeMillis();
        cameraPreview = (SurfaceView) getActivity().findViewById(R.id.cameraPreview);
        mHolder = cameraPreview.getHolder();
        mHolder.setFormat(PixelFormat.TRANSPARENT);
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
        autoFocusHandler = new Handler();
        initView();
        autoFocusHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCamera == null) {
                    autoFocusHandler.postDelayed(this, 1000);
                } else {
                    //initView();
                    previewing = true;
                }
            }
        }, 300);
    }

    private void initView() {

        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        DriverScanActivity.getLight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = System.currentTimeMillis();// 摄像头 初始化 需要时间
                if (time - opentime > 2000) {
                    opentime = time;
                    if (!toggleLight) {
                        toggleLight = true;
                        if (mCamera != null) {
                            Camera.Parameters parameters = mCamera.getParameters();
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            mCamera.setParameters(parameters);
                        }
                        DriverScanActivity.getLight().setBackgroundResource(R.drawable.flash_on);
                    } else {
                        toggleLight = false;
                        if (mCamera != null) {
                            Camera.Parameters parameters = mCamera.getParameters();
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            mCamera.setParameters(parameters);
                        }
                        DriverScanActivity.getLight().setBackgroundResource(R.drawable.flash_off);
                    }
                }
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (mCamera != null && previewing) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null) ;
            autoFocusHandler.removeCallbacks(doAutoFocus);
            previewing = false;
        }
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            releaseCameraAndPreview();
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);

            // Select a camera if no explicit camera requested
            cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(0, cameraInfo);

            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            mCamera.release();
            mCamera = null;
            Log.d("DBG", "Error setting camera preview: " + e.getMessage());
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        try {
            mCamera.stopPreview();
            if (mHolder.getSurface() == null) {
                // preview surface does not exist
                return;
            }
            // stop preview before making changes
            try {
                // Hard code camera surface rotation 90 degs to match Activity view in portrait
                determineDisplayOrientation();

                mCamera.setPreviewDisplay(mHolder);
                mCamera.setPreviewCallback(this);
                mCamera.startPreview();
                mCamera.autoFocus(autoFocusCB);
            } catch (Exception e) {
                Log.d("DBG", "Error starting camera preview: " + e.getMessage());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {


        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null) ;
            autoFocusHandler.removeCallbacks(doAutoFocus);
            mCamera.release();
            mCamera = null;

        }

    }

    /**
     * 设置旋转角度
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    private void determineDisplayOrientation() {
        getCameraInfo(0, cameraInfo);

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

    private void releaseCameraAndPreview() {
        // cameraPreview.set(null);
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        try {
            Camera.Parameters parameters = camera.getParameters();
            Size size = parameters.getPreviewSize();
            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);
            if (scanner == null) {
                scanner = new ImageScanner();
                scanner.setConfig(0, Config.X_DENSITY, 3);
                scanner.setConfig(0, Config.Y_DENSITY, 3);
            }
            int result = scanner.scanImage(barcode);
            if (result != 0) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();
                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {
                    // 将扫描后的信息返回
                    Intent intent = new Intent();
                    intent.putExtra("id", sym.getData());
                    intent.putExtra("tag", "zxbh");
                    getActivity().setResult(getActivity().RESULT_OK, intent);
                    getActivity().finish();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}