package com.scxd.idcardlibs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ReconCardFragment extends Fragment implements SurfaceHolder.Callback, Camera.PreviewCallback {

    private TessBaseAPI baseApi;

    //    Camera.PreviewCallback mp = ;
    private CameraManager cameraManager;
    private PreviewBorderView mPreviewBorderView;
    private boolean hasSurface;
    private boolean toggleLight = false;
    private Handler mHandler;
    private String sdPath;
    private Long opentime;
    private ImageScanner scanner;
    private SurfaceView surfaceView;
    public static String phonePath = "/storage/emulated/0/Android/data/com.scxd.lawqinghai/files/Documents/";
    private View rootView;
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
        rootView = inflater.inflate(R.layout.activity_id_camera,null);
        return rootView;
    }
   
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        opentime = System.currentTimeMillis();

        sdPath = phonePath + "ayy/";

        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        baseApi = new TessBaseAPI();
        baseApi.init(sdPath, "eng");

        mHandler = new Handler();
        initLayoutParams();
        /**
         * 初始化camera
         */
        cameraManager = new CameraManager(getActivity());
        cameraManager.setFlag(1);
        surfaceView = (SurfaceView) getActivity().findViewById(R.id.surfaceview);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        if (hasSurface) {
            // activity在paused时但不会stopped,因此surface仍旧存在；
            // surfaceCreated()不会调用，因此在这里初始化camera
            initCamera(surfaceHolder);
        } else {
            // 重置callback，等待surfaceCreated()来初始化camera
            surfaceHolder.addCallback(this);
        }
    }
    
    /**
     * 重置surface宽高比例为3:4，不重置的话图形会拉伸变形
     */
    private void initLayoutParams() {

        mPreviewBorderView = (PreviewBorderView) getActivity().findViewById(R.id.borderview);

        DriverScanActivity.getLight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = System.currentTimeMillis();// 摄像头 初始化 需要时间
                if (time - opentime > 2000) {
                    opentime = time;
                    if (!toggleLight) {
                        toggleLight = true;
                        cameraManager.openLight();
                        DriverScanActivity.getLight().setBackgroundResource(R.drawable.flash_on);
                    } else {
                        toggleLight = false;
                        cameraManager.offLight();
                        DriverScanActivity.getLight().setBackgroundResource(R.drawable.flash_off);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
       
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        cameraManager.stopPreview();
    }

    /**
     * 初始camera
     *
     * @param surfaceHolder SurfaceHolder
     */
    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            return;
        }
        try {
            // 打开Camera硬件设备
            cameraManager.openDriver(surfaceHolder, getActivity());
            // 创建一个handler来打开预览，并抛出一个运行时异常
            cameraManager.startPreview(this);
        } catch (Exception ioe) {
            Log.d("zk", ioe.toString());

        }
    }

    @Override
    public void onPause() {
        /**
         * 停止camera，是否资源操作
         */
        cameraManager.stopPreview();
        cameraManager.closeDriver();
        if (!hasSurface) {
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            surfaceHolder.removeCallback(this);
        }
        super.onPause();
    }

    public String doOcr(Bitmap bitmap) {
        try {

//            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            baseApi.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_BLOCK);
            baseApi.setImage(ImageFilter.gray2Binary(bitmap));
            BitmapUtil.saveBitmap(ImageFilter.gray2Binary(bitmap), Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/pic.png");
            baseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "0123456789XYxy");
            baseApi.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "!@#$%^&*()_+=-[]}{;:'\"\\|~`,./<>?");
            String text = baseApi.getUTF8Text();
            baseApi.clear();
            //        baseApi.end();
            return text;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onDestroy() {
        if (baseApi != null)
            baseApi.end();
        super.onDestroy();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        ByteArrayOutputStream baos;
        byte[] rawImage;
        Bitmap bitmap;
        try {
            Camera.Size previewSize = camera.getParameters().getPreviewSize();//获取尺寸,格式转换的时候要用到
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = true;
            YuvImage yuvimage = new YuvImage(data, ImageFormat.NV21, previewSize.width, previewSize.height, null);
            baos = new ByteArrayOutputStream();
            yuvimage.compressToJpeg(new Rect(0, 0, previewSize.width, previewSize.height), 100, baos);// 80--JPG图片的质量[0-100],100最高
            rawImage = baos.toByteArray();
            //将rawImage转换成bitmap
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            bitmap = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options);
            
            if (bitmap == null) {
                Log.d("zka", "bitmap is nlll");
                return;
            } else {

                int height = bitmap.getHeight();
                int width = bitmap.getWidth();
                final Bitmap bitmap1 = Bitmap.createBitmap(bitmap, (width - height) / 2, height / 6, height, height * 2 / 3);
//                final Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.qq);
                
                int x, y, w, h;
                x = (int) (bitmap1.getWidth() * 0.340);
                y = (int) (bitmap1.getHeight() * 0.800);
                w = (int) (bitmap1.getWidth() * 0.6 + 0.5f);
                h = (int) (bitmap1.getHeight() * 0.12 + 0.5f);
                Bitmap bit_hm = Bitmap.createBitmap(bitmap1, x, y, w, h);
                String id = doOcr(bit_hm);

                if (personIdValidation(id)) {
                    Intent i = new Intent();
                    i.putExtra("id", id);
                    i.putExtra("tag", "sfz");
                    getActivity().setResult(getActivity().RESULT_OK, i);
                    getActivity().finish();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 校验身份证号
     * @param text
     * @return
     */
    public boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

}
