package com.scxd.idcardlibs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
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

public class ReconCardActivity extends Activity implements SurfaceHolder.Callback, Camera.PreviewCallback {

    private TessBaseAPI baseApi;

    //    Camera.PreviewCallback mp = ;
    private CameraManager cameraManager;
    private PreviewBorderView2 mPreviewBorderView2;
    private PreviewBorderView mPreviewBorderView;
    private boolean hasSurface;
    private String type;
    private Button btn_close, light, btn_resacn;
    private boolean toggleLight = false;
    private Handler mHandler;
    private String sdPath;
    //    private ImageView iv_close;
    //    private View ErrorView;
    private int times = 0;
    private Long opentime;
    private ImageScanner scanner;
    private TextView sfz, zxbh;
    private boolean zxbhFlag = true;
    private int mScreenW, mScreenH;

    public static String phonePath = "/storage/emulated/0/Android/data/com.scxd.lawqinghai/files/Documents/";

    static {
        System.loadLibrary("iconv");
        System.loadLibrary("zbar");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opentime = System.currentTimeMillis();

        setContentView(R.layout.activity_id_camera);
        sdPath = phonePath + "ayy/";

        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        baseApi = new TessBaseAPI();
        baseApi.init(sdPath, "eng");

        mHandler = new Handler();
        initLayoutParams();
    }

    /**
     * 重置surface宽高比例为3:4，不重置的话图形会拉伸变形
     */
    private void initLayoutParams() {

        mPreviewBorderView = (PreviewBorderView) findViewById(R.id.borderview);
        mPreviewBorderView2 = (PreviewBorderView2) findViewById(R.id.borderview2);
        mPreviewBorderView.setVisibility(View.GONE);
        mPreviewBorderView2.setVisibility(View.VISIBLE);
        ViewTreeObserver vto = mPreviewBorderView2.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                mScreenH = mPreviewBorderView2.getMeasuredHeight();
                mScreenW = mPreviewBorderView2.getMeasuredWidth();
                return true;
            }
        });
        sfz = (TextView) findViewById(R.id.sfz);
        zxbh = (TextView) findViewById(R.id.zxbh);
        btn_close = (Button) findViewById(R.id.btn_close);
        light = (Button) findViewById(R.id.light);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                onBackPressed();

            }
        });
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = System.currentTimeMillis();// 摄像头 初始化 需要时间
                if (time - opentime > 2000) {
                    opentime = time;
                    if (!toggleLight) {
                        toggleLight = true;
                        cameraManager.openLight();
                        light.setBackgroundResource(R.drawable.flash_on);
                    } else {
                        toggleLight = false;
                        cameraManager.offLight();
                        light.setBackgroundResource(R.drawable.flash_off);
                    }
                }
            }
        });
        sfz.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                zxbhFlag = false;
                zxbh.setTextColor(getResources().getColor(R.color.white));
                sfz.setTextColor(getResources().getColor(R.color.text));
                mPreviewBorderView.setVisibility(View.VISIBLE);
                mPreviewBorderView2.setVisibility(View.GONE);
            }
        });
        zxbh.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                zxbhFlag = true;
                zxbh.setTextColor(getResources().getColor(R.color.text));
                sfz.setTextColor(getResources().getColor(R.color.white));
                mPreviewBorderView.setVisibility(View.GONE);
                mPreviewBorderView2.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 初始化camera
         */
        cameraManager = new CameraManager(this);
        cameraManager.setFlag(1);
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();

        if (hasSurface) {
            // activity在paused时但不会stopped,因此surface仍旧存在；
            // surfaceCreated()不会调用，因此在这里初始化camera
            initCamera(surfaceHolder);
        } else {
            // 重置callback，等待surfaceCreated()来初始化camera
            surfaceHolder.addCallback(this);
        }
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
            cameraManager.openDriver(surfaceHolder, this);
            // 创建一个handler来打开预览，并抛出一个运行时异常
            cameraManager.startPreview(this);
        } catch (Exception ioe) {
            Log.d("zk", ioe.toString());

        }
    }

    @Override
    protected void onPause() {
        /**
         * 停止camera，是否资源操作
         */
        cameraManager.stopPreview();
        cameraManager.closeDriver();
        if (!hasSurface) {
            SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            surfaceHolder.removeCallback(this);
        }
        super.onPause();
    }

    public String doOcr(Bitmap bitmap) {
        try {

            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            baseApi.setImage(bitmap);
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
    public void onBackPressed() {
        if (baseApi != null)
            baseApi.end();
        super.onBackPressed();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        ByteArrayOutputStream baos;
        byte[] rawImage;
        Bitmap bitmap;
        try {
            if (zxbhFlag){
                Camera.Parameters parameters = camera.getParameters();
                Camera.Size size = parameters.getPreviewSize();
                Image barcode = new Image(size.width, size.height, "Y800");
                barcode.setData(data);
//                YuvImage yuvimage = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
//                baos = new ByteArrayOutputStream();
//                yuvimage.compressToJpeg(new Rect(0, 0, size.width, size.height), 100, baos);// 80--JPG图片的质量[0-100],100最高
//                rawImage = baos.toByteArray();
//                //将rawImage转换成bitmap
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inPreferredConfig = Bitmap.Config.RGB_565;
//                bitmap = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options);
//                int height1 = bitmap.getHeight();
//                int width1 = bitmap.getWidth();
//                Bitmap bitmapzx1 = Bitmap.createBitmap(bitmap, width1 / 2 - height1 * 2 / 3, 
//                        height1 / 3, 
//                        height1 * 4 / 3,
//                        height1 / 3);
//                ByteArrayOutputStream baosZx = new ByteArrayOutputStream();
//                toGrayscale(bitmapzx1).compress(Bitmap.CompressFormat.PNG, 100, baosZx);
////                bitmapzx1.compress(Bitmap.CompressFormat.PNG, 100, baosZx);
//                Image barcode = new Image(bitmapzx1.getWidth(), bitmapzx1.getHeight(), "Y800");
//                barcode.setData(baosZx.toByteArray());

                if (scanner==null){
                    scanner = new ImageScanner();
                    scanner.setConfig(0, Config.X_DENSITY, 3);
                    scanner.setConfig(0, Config.Y_DENSITY, 3);
                }
                int result = scanner.scanImage(barcode);
                if (result != 0) {
                    SymbolSet syms = scanner.getResults();
                    for (Symbol sym : syms) {
                        // 将扫描后的信息返回
                        Intent intent = new Intent();
                        intent.putExtra("id", sym.getData());
                        intent.putExtra("tag", "zxbh");
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            } else {
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

                    int x, y, w, h;
                    x = (int) (bitmap1.getWidth() * 0.340);
                    y = (int) (bitmap1.getHeight() * 0.800);
                    w = (int) (bitmap1.getWidth() * 0.6 + 0.5f);
                    h = (int) (bitmap1.getHeight() * 0.12 + 0.5f);
                    Bitmap bit_hm = Bitmap.createBitmap(bitmap1, x, y, w, h);
                    String id = doOcr(bit_hm);

                    if (id.length() == 18) {
                        Intent i = new Intent();
                        i.putExtra("id", id);
                        i.putExtra("tag", "sfz");
                        setResult(RESULT_OK, i);
                        onBackPressed();
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
