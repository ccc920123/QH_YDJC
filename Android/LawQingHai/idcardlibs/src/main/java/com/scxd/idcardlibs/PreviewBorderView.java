package com.scxd.idcardlibs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class PreviewBorderView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String DEFAULT_TIPS_TEXT = "请将身份证正面置于框内扫描，并尽量对齐边框";
    private static final int DEFAULT_TIPS_TEXT_SIZE = 18;
    private static final int DEFAULT_TIPS_TEXT_COLOR = Color.GREEN;
    private int mScreenH;
    private int mScreenW;
    private Canvas mCanvas;
    private Paint mPaint;
    private Paint mPaintLine;
    private Paint mPanitRed;
    private SurfaceHolder mHolder;
    private Thread mThread;
    /**
     * 自定义属性
     */
    private float tipTextSize;
    private int tipTextColor;
    private String tipText;

    public PreviewBorderView(Context context) {
        this(context, null);
    }

    public PreviewBorderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PreviewBorderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    /**
     * 初始化自定义属性
     *
     * @param context Context
     * @param attrs   AttributeSet
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PreviewBorderView);
        try {
            tipTextSize = a.getDimension(R.styleable.PreviewBorderView_tipTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TIPS_TEXT_SIZE, getResources().getDisplayMetrics()));
            tipTextColor = a.getColor(R.styleable.PreviewBorderView_tipTextColor, DEFAULT_TIPS_TEXT_COLOR);
            tipText = a.getString(R.styleable.PreviewBorderView_tipText);
            if (tipText == null) {
                tipText = DEFAULT_TIPS_TEXT;
            }
        } finally {
            a.recycle();
        }


    }

    /**
     * 初始化绘图变量
     */
    private void init() {
        this.mHolder = getHolder();
        this.mHolder.addCallback(this);
        this.mHolder.setFormat(PixelFormat.TRANSPARENT);
        setZOrderOnTop(true);
        setZOrderMediaOverlay(true);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(Color.WHITE);
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.mPaintLine = new Paint();
        this.mPaintLine.setColor(Color.WHITE);
        this.mPaintLine.setStrokeWidth(3.0F);
//        this.mPanitRed = new Paint();
//        this.mPanitRed.setColor(Color.RED);
//        this.mPanitRed.setStyle(Paint.Style.STROKE);
//        this.mPanitRed.setStrokeWidth(4.0F);
        setKeepScreenOn(true);
    }

    /**
     * 绘制取景框
     */
    private void draw() {
        try {
            this.mCanvas = this.mHolder.lockCanvas();
            this.mCanvas.drawARGB(100, 0, 0, 0);
            Log.e("TAG", "mScreenW:" + mScreenW + " mScreenH:" + mScreenH);
            int left = this.mScreenW / 2 - this.mScreenH * 2 / 3 + this.mScreenH * 1 / 6;
            int top = this.mScreenH * 1 / 6;
            int right = this.mScreenW / 2 + this.mScreenH * 2 / 3 - this.mScreenH * 1 / 6;
            int bottom = this.mScreenH - this.mScreenH * 1 / 6;
            this.mCanvas.drawRect(new RectF(left, top, right, bottom), this.mPaint);
            this.mCanvas.drawLine(left, top, left, top + 150, this.mPaintLine);
            this.mCanvas.drawLine(left, top, left + 150, top, this.mPaintLine);
            this.mCanvas.drawLine(right, top, right, top + 150, this.mPaintLine);
            this.mCanvas.drawLine(right, top, right - 150, top, this.mPaintLine);
            this.mCanvas.drawLine(left, bottom, left, bottom - 150, this.mPaintLine);
            this.mCanvas.drawLine(left, bottom, left + 150, bottom, this.mPaintLine);
            this.mCanvas.drawLine(right, bottom, right, bottom - 150, this.mPaintLine);
            this.mCanvas.drawLine(right, bottom, right - 150, bottom, this.mPaintLine);

            mPaintLine.setTextSize(tipTextSize);
            mPaintLine.setAntiAlias(true);
            mPaintLine.setDither(true);
            float length = mPaintLine.measureText(tipText);
            
            this.mCanvas.drawText(tipText, left + this.mScreenH / 2 - length / 2, this.mScreenH * 1 / 2 - tipTextSize, mPaintLine);
            
            scaleLeft = left / (double)mScreenW;
            scaleTop = top / (double)mScreenH;
            scaleRight = right / (double)mScreenW;
            scaleBottom = bottom / (double)mScreenH;
//            
//            this.mCanvas.drawRect(new Rect(right / 2 + 20, bottom - 130, right - 50, bottom - 50), mPanitRed);
            
            Log.e("TAG", "left:" + (left));
            Log.e("TAG", "top:" + (top));
            Log.e("TAG", "right:" + (right));
            Log.e("TAG", "bottom:" + (bottom));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (this.mCanvas != null) {
                this.mHolder.unlockCanvasAndPost(this.mCanvas);
            }
        }
    }

    private double scaleLeft, scaleTop, scaleRight, scaleBottom;

    public double getScaleLeft() {
        return scaleLeft;
    }

    public double getScaleTop() {
        return scaleTop;
    }

    public double getScaleRight() {
        return scaleRight;
    }

    public double getScaleBottom() {
        return scaleBottom;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //获得宽高，开启子线程绘图
        this.mScreenW = getWidth();
        this.mScreenH = getHeight();
        this.mThread = new Thread(this);
        this.mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //停止线程
        try {
            mThread.interrupt();
            mThread = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //子线程绘图
        draw();
    }
}
