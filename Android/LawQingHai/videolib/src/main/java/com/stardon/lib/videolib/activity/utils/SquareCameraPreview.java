package com.stardon.lib.videolib.activity.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名: SquareCameraPreview
 * <br/>功能描述: 预览界面放缩，通过手指间距来实现摄像头的放缩
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/8
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class SquareCameraPreview extends SurfaceView {
    /**
     * 返回的源代码中的基础类的简单名称,用它来作为TAG
     */
    public static final String TAG = SquareCameraPreview.class.getSimpleName();
    /**
     * 触摸指针
     */
    private static final int INVALID_POINTER_ID = -1;
    /**
     * 向外放缩标志
     */
    private static final int ZOOM_OUT = 0;
    /**
     * 向内放缩标志
     */
    private static final int ZOOM_IN = 1;
    /**
     * 默认放缩标志
     */
    private static final int ZOOM_DELTA = 1;
    /**
     * 放缩焦点增值
     */
    private static final int FOCUS_SQR_SIZE = 100;
    /**
     * 放缩焦点最大值
     */
    private static final int FOCUS_MAX_BOUND = 1000;
    /**
     * 放缩焦点最小值
     */
    private static final int FOCUS_MIN_BOUND = -FOCUS_MAX_BOUND;

    //    private static final double ASPECT_RATIO = 3.0 / 4.0;
    /**
     * 相机
     */
    private Camera mCamera;
    /**
     * xTouch
     */
    private float mLastTouchX;
    /**
     * yTouch
     */
    private float mLastTouchY;

    // For scaling
    /**
     * 放缩最大值
     */
    private int mMaxZoom;
    /**
     * 放缩标识
     */
    private boolean mIsZoomSupported;
    /**
     * 触摸指针
     */
    private int mActivePointerId = INVALID_POINTER_ID;
    /**
     * 尺寸比例系数
     */
    private int mScaleFactor = 1;
    /**
     * 侦测由多个触点（多点触控）引发的变形手势
     */
    private ScaleGestureDetector mScaleDetector;

    /**
     * 聚焦标识
     */
    private boolean mIsFocus;
    /**
     * 摄像区域
     */
    private Camera.Area mFocusArea;
    /**
     * 摄像区域集合
     */
    private ArrayList<Camera.Area> mFocusAreas;
    /**
     * 是否自动聚焦
     */
    private boolean isAutoFocus = false;

    public SquareCameraPreview(Context context) {
        super(context);
        init(context);
    }

    public SquareCameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SquareCameraPreview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 方法名称: init
     * <br/>方法详述: 预览界面主进入口
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    @SuppressLint("NewApi")
    private void init(Context context) {
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        mFocusArea = new Camera.Area(new Rect(), 1000);
        mFocusAreas = new ArrayList<Camera.Area>();
        mFocusAreas.add(mFocusArea);
    }

    /**
     * Measure the view and its content to determine the measured width and the
     * measured height
     */
    //    @Override
    //    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //        int height = MeasureSpec.getSize(heightMeasureSpec);
    //        int width = MeasureSpec.getSize(widthMeasureSpec);
    //
    //        if (width > height * ASPECT_RATIO) {
    //            width = (int) (height * ASPECT_RATIO + 0.5);
    //        }
    //        else {
    //            height = (int) (width / ASPECT_RATIO + 0.5);
    //        }
    //
    //        setMeasuredDimension(width, height);
    //    }

    /**
     * 方法名称: getViewWidth
     * <br/>方法详述: 预览界面的宽度
     * <br/>参数:
     * <br/>返回值: int 宽度
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public int getViewWidth() {
        return getWidth();
    }

    /**
     * 方法名称: getViewWidth
     * <br/>方法详述: 预览界面的高度
     * <br/>参数:
     * <br/>返回值: int 高度
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public int getViewHeight() {
        return getHeight();
    }

    /**
     * 方法名称: setCamera
     * <br/>方法详述: 设置摄像头
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public void setCamera(Camera camera) {
        mCamera = camera;

        if (camera != null) {
            Camera.Parameters params = camera.getParameters();
            
            mIsZoomSupported = params.isZoomSupported();
            if (mIsZoomSupported) {
                mMaxZoom = params.getMaxZoom();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);

        final int action = event.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                mIsFocus = true;

                mLastTouchX = event.getX();
                mLastTouchY = event.getY();

                mActivePointerId = event.getPointerId(0);
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (mIsFocus && isAutoFocus) {
                    handleFocus(mCamera.getParameters());
                }
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                mCamera.cancelAutoFocus();
                mIsFocus = false;
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }
        }

        return true;
    }

    /**
     * 方法名称: handleZoom
     * <br/>方法详述: 放缩
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void handleZoom(Camera.Parameters params) {
        int zoom = params.getZoom();
        if (mScaleFactor == ZOOM_IN) {
            if (zoom < mMaxZoom) zoom += ZOOM_DELTA;
        } else if (mScaleFactor == ZOOM_OUT) {
            if (zoom > 0) zoom -= ZOOM_DELTA;
        }
        params.setZoom(zoom);
        mCamera.setParameters(params);
    }

    @SuppressLint("NewApi")
    private void handleFocus(Camera.Parameters params) {
        float x = mLastTouchX;
        float y = mLastTouchY;

        if (!setFocusBound(x, y)) return;

        List<String> supportedFocusModes = params.getSupportedFocusModes();
        if (supportedFocusModes != null
                && supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            Log.d(TAG, mFocusAreas.size() + "");
            params.setFocusAreas(mFocusAreas);
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            mCamera.setParameters(params);
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    // Callback when the auto focus completes
                    if (success) {
                        isAutoFocus = true;
                    }
                }
            });
        }
    }

    @SuppressLint("NewApi")
    private boolean setFocusBound(float x, float y) {
        int left = (int) (x - FOCUS_SQR_SIZE / 2);
        int right = (int) (x + FOCUS_SQR_SIZE / 2);
        int top = (int) (y - FOCUS_SQR_SIZE / 2);
        int bottom = (int) (y + FOCUS_SQR_SIZE / 2);

        if (FOCUS_MIN_BOUND > left || left > FOCUS_MAX_BOUND) return false;
        if (FOCUS_MIN_BOUND > right || right > FOCUS_MAX_BOUND) return false;
        if (FOCUS_MIN_BOUND > top || top > FOCUS_MAX_BOUND) return false;
        if (FOCUS_MIN_BOUND > bottom || bottom > FOCUS_MAX_BOUND) return false;

        mFocusArea.rect.set(left, top, right, bottom);

        return true;
    }

    /**
     * 类名: SquareCameraPreview
     * <br/>功能描述:处理多触点放缩
     * <br/>作者: 陈渝金
     * <br/>时间: 2016/11/8
     * <br/>最后修改者:
     * <br/>最后修改内容:
     */

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor = (int) detector.getScaleFactor();
            handleZoom(mCamera.getParameters());
            return true;
        }
    }
}
