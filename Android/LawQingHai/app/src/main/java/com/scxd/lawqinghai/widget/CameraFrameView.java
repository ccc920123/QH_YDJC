package com.scxd.lawqinghai.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by chenpan on 2017/6/6.
 */
public class CameraFrameView extends View {

    /**
     * 刷新界面的时间
     */
    private static final long ANIMATION_DELAY = 25L;

    /**
     * 四周边框的宽度
     */
    private static final int FRAME_LINE_WIDTH = 4;
    private Rect frame;
    private int width;
    private int height;
    private Paint paint;
    private Context context;
  //  private Bitmap bitmap;
    public float leftPointX;
    public float leftPointY;
    /**
     *VIN码拍摄比例8;1.5
     */
    public float modelWidth=Float.valueOf("0.8").floatValue();
    public float modelHeight=Float.valueOf("0.15").floatValue();
    public CameraFrameView(Context context, float modelWidth, float modelHeight) {
        super(context);
        paint = new Paint();
        this.context = context;
        this.modelWidth=modelWidth;
        this.modelHeight=modelHeight;
//        this.bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.scanline);
    }

    @Override
    public void onDraw(Canvas canvas) {
        width = canvas.getWidth();
        height = canvas.getHeight();
        // System.out.println("刷新:"+fieldsPosition);
//        if (configParamsModel.leftPointX == 0.0
//                && configParamsModel.leftPointY == 0.0) {
            /**
             * 这个矩形就是中间显示的那个框框
             */
            frame = new Rect(
                    (int) ((1-modelWidth)/2 * width),
               //     (int) (0),
//                    (int) (height * 0.4),
                    (int) ((1-modelHeight )/ 2*height),
                   (int) ((1-(1-modelWidth)/2) * width),
               //     (int) ( width),
                    (int) ((1 + modelHeight)/2*height));
                    //(int) (height * (0.4 + modelHeight)));

//        } else {
//            /**
//             * 这个矩形就是中间显示的那个框框
//             */
//            frame = new Rect(
//                    (int) (configParamsModel.leftPointX * width),
//                    (int) (height * configParamsModel.leftPointY),
//                    (int) ((configParamsModel.leftPointX + configParamsModel
//                            .width) * width),
//                    (int) (height * (configParamsModel.leftPointY + configParamsModel
//                            .height)));
//
//
//        }
        if (frame == null) {
            return;
        }
        // 画出扫描框外面的阴影部分，共四个部分，扫描框的上面到屏幕上面，扫描框的下面到屏幕下面
        // 扫描框的左边面到屏幕左边，扫描框的右边到屏幕右边
        paint.setColor(Color.argb(48, 0, 0, 0));
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
                paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);
        //255_159_242_74
        // 绘制两个像素边宽的绿色线框
        paint.setColor(Color.argb(255,159,242,74));
        canvas.drawRect(frame.left + FRAME_LINE_WIDTH - 2, frame.top,
                frame.right - FRAME_LINE_WIDTH + 2, frame.top
                        + FRAME_LINE_WIDTH, paint);// 上边
        canvas.drawRect(frame.left + FRAME_LINE_WIDTH - 2, frame.top,
                frame.left + FRAME_LINE_WIDTH + 2, frame.bottom
                        + FRAME_LINE_WIDTH, paint);// 左边
        canvas.drawRect(frame.right - FRAME_LINE_WIDTH - 2, frame.top,
                frame.right - FRAME_LINE_WIDTH + 2, frame.bottom
                        + FRAME_LINE_WIDTH, paint);// 右边
        canvas.drawRect(frame.left + FRAME_LINE_WIDTH - 2, frame.bottom,
                frame.right - FRAME_LINE_WIDTH + 2, frame.bottom
                        + FRAME_LINE_WIDTH, paint);// 底边
        fresh();

    }

    public void fresh() {
        /**
         * 当我们获得结果的时候，我们更新整个屏幕的内容
         */

        postInvalidateDelayed(ANIMATION_DELAY, 0, 0, (int) (width * 0.8),
                height);

//		postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right,
//				frame.bottom);
    }
}
