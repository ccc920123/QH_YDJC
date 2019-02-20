package com.scxd.lawqinghai.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.CameraAvtivity;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.event.RxSchedulers;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.view.SavePhotoView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.ImageUtility;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.ZoomImageView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/16.
 */
public class SavePhotoFragment extends BaseFragment implements SavePhotoView {
    @BindView(R.id.photo)
    ZoomImageView photo;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.save_photo)
    TextView savePhoto;
    @BindView(R.id.cancel)
    TextView cancel;
    private String title;
    Bitmap bitmap = null;
    private String time;
    /**
     * 敏感区域,裁剪时候用到的参数
     */
    private float[] regionPos = new float[4];// 敏感区域
    private String code;
    private int rotation;
    private String from;
    private int mCameraId;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_edit_save_photo;
    }

    @Override
    protected void initInjector() {
        title = getArguments().getString(Common.TITLE_TAG);
        final byte[] photodata = getArguments().getByteArray(Common.PHOTO_KEY);//得到拍照界面的照片byte
        final byte[] headdata = getArguments().getByteArray(Common.HEAD_PHOTO_KEY);//得到拍照界面的照片byte
        code = getArguments().getString(Common.ZPCODE);
        regionPos = getArguments().getFloatArray(Common.VIN_FRAME);
        rotation = getArguments().getInt(Common.ROTATION);
        mCameraId = getArguments().getInt(Common.CAMERAID);
        time = getArguments().getString(Common.TIME);
        from = ((CameraAvtivity) getActivity()).getForm();
        // title += "\r\n" + time;
        String buttomTitle = time;
        final String finalButtomTitle = buttomTitle;
        showLoadProgressDialog("处理图片中");
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = setPicture(photodata, headdata, title, finalButtomTitle);
                subscriber.onNext(bitmap);
            }
        }).compose(RxSchedulers.schedulersTransformer).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap beans) {
                bitmap = beans;
                photo.setImageBitmap(bitmap);
                disDialog();
            }
        });
        if ("TEST".equals(from)) {
            savePhoto.setVisibility(View.GONE);
        }
        // setPicture(photodata, headdata, title, buttomTitle);
        // photo.setImageBitmap(bitmap);
    }

    /**
     * 生成图片
     *
     * @param photodata
     * @param headdata
     * @param title
     */
    private Bitmap setPicture(byte[] photodata, byte[] headdata, String title, String buttomStr) {
        Bitmap bitmaps = null;
        if (photodata == null) {
        } else {
            if (headdata != null) {
                Bitmap head = ImageUtility.decodeSampledBitmapFromByte(getActivity(), headdata, rotation);
                Bitmap photo = ImageUtility.decodeSampledBitmapFromByte(getActivity(), photodata, rotation);
                head = ImageUtility.zoomImage(head, 100, 100);
                bitmap = doodle(photo, head);
                bitmaps = ImageUtility.waterMarkPicture(getActivity(), bitmap, title, buttomStr, 0);
            } else {
                if ("0103".equals(code)||"0101".equals(code)||"0198".equals(code)) {
                    bitmaps = ImageUtility.waterMarkPictureAndCrop(getActivity(), photodata, title, buttomStr, regionPos, rotation);//得到bitmap
                } else {
                    bitmaps = ImageUtility.waterMarkPicture(getActivity(), photodata, title, buttomStr, rotation, mCameraId);//得到bitmap
                }
            }

        }
        return bitmaps;
    }

    @Override
    protected void initEventAndData() {
        savePhoto.setClickable(true);

    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @OnClick({R.id.back, R.id.save_photo, R.id.cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back://返回
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    getFragmentManager().popBackStack();
                }
                break;
            case R.id.save_photo:
                if ("Violatins".equals(from)) {
                    finishThisActivity();


                } else {

                    if (!ButtonTools.isFastDoubleClick(1500)) {
                        cancel.setClickable(false);
                        if ("DoorPhotoItemFragment".equals(((CameraAvtivity) getActivity()).getForm())) {
                            savePhotoToDabase();
                        } else {
                            upDataPhotoMessage();
                        }
                    }
                }
                break;
            case R.id.cancel://重拍
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    savePhoto.setClickable(false);
                    getFragmentManager().popBackStack();
                }

                break;
        }
    }

    /**
     * 保存数据到数据库
     */

    private void savePhotoToDabase() {
        cancel.setClickable(true);
        showLoadProgressDialog("正在保存图片");
//        final PhotoMessageRqBean mPhotoMessageRqBean = ((CameraAvtivity) getActivity()).getmPhotoMessageRqBean();
        Observable.just(bitmap).map(new Func1<Bitmap, String>() {
            @Override
            public String call(Bitmap bitmap) {
                return ImageUtility.bitmapToBaseString(bitmap);
            }
        }).compose(RxSchedulers.schedulersTransformer).subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
//                mPhotoMessageRqBean.setZp(str);
//                mPhotoMessageRqBean.setPhotoName(null);
//                mPhotoMessageRqBean.setPssj(time);
//                ((SavePhotoPresenter) mPresenter).savePhotoData(getActivity(), mPhotoMessageRqBean);
            }
        });

    }

    /**
     * 上传数据
     */
    private void upDataPhotoMessage() {
        cancel.setClickable(true);
        showLoadProgressDialog("正在上传图片");
//        final PhotoMessageRqBean mPhotoMessageRqBean = ((CameraAvtivity) getActivity()).getmPhotoMessageRqBean();
        Observable.just(bitmap).map(new Func1<Bitmap, String>() {
            @Override
            public String call(Bitmap bitmap) {
                return ImageUtility.bitmapToBaseString(bitmap);
            }
        }).compose(RxSchedulers.schedulersTransformer).subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
//                mPhotoMessageRqBean.setZp(str);
//                mPhotoMessageRqBean.setPhotoName(null);
//                mPhotoMessageRqBean.setPssj(time);
//                ((SavePhotoPresenter) mPresenter).upLoadPhotoData(getActivity(), mPhotoMessageRqBean);
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bitmap!=null){
            bitmap.recycle();
        }
    }

    /**
     * 压缩图片
     *
     * @param data 图片数据
     */
    private Bitmap processPhoto(byte[] data) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap imagebitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        return imagebitmap;
    }

    /**
     * 位图处理 将两张照片叠加在一起
     *
     * @param src       原图
     * @param watermark 添加的图
     * @return
     */
    public Bitmap doodle(Bitmap src, Bitmap watermark)

    {

        // 另外创建一张图片
        Bitmap newb = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);// 
        // 创建一个新的和SRC长度宽度一样的位图
        Canvas canvas = new Canvas(newb);
        canvas.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入原图片src
        canvas.drawBitmap(watermark, (src.getWidth() - watermark.getWidth()), (src.getHeight() - watermark.getHeight
                ()) / 4, null); // 涂鸦图片画到原图片中间位置
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        watermark.recycle();
        watermark = null;
        return newb;

    }

    @Override
    public void showLoadProgressDialog(String str) {
        showLoading(str);
    }

    @Override
    public void disDialog() {
        dissLoadDialog();
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(getActivity(), message);
    }

    @Override
    public void finishThisActivity() {
        if ("PROJECT".equals(from)) {
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
        } else if ("Violatins".equals(from)) {

            Observable.just(bitmap).map(new Func1<Bitmap, String>() {
                @Override
                public String call(Bitmap bitmap) {
                    return ImageUtility.bitmapToBaseString(bitmap);
                }
            }).compose(RxSchedulers.schedulersTransformer).subscribe(new Action1<String>() {
                @Override
                public void call(String str) {

                    Intent itt = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("bitmap", str);
                    itt.putExtras(bundle);
                    getActivity().setResult(Activity.RESULT_OK, itt);
                    getActivity().finish();
                }}
            );




        }else{
            getActivity().finish();
        }



    }
}
