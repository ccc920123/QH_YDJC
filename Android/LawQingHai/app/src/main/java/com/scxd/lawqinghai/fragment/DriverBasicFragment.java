package com.scxd.lawqinghai.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.DriverInformationActivity;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.request.QueryDriverRepBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.QueryDriverBean;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.DriverBasicPresenter;
import com.scxd.lawqinghai.mvp.view.DriverBasicView;
import com.scxd.lawqinghai.utils.RotateTransformation;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.ZoomImageViewGlide;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述: 驾驶人基本信息
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class DriverBasicFragment extends BaseFragment implements DriverBasicView {

    @BindView(R.id.xm)
    TextView xm;
    @BindView(R.id.zjmc)
    TextView zjmc;
    @BindView(R.id.fzjg)
    TextView fzjg;
    @BindView(R.id.zjcx)
    TextView zjcx;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.rotatetext)
    TextView rotateText;
    @BindView(R.id.csrq)
    TextView csrq;
    @BindView(R.id.dhhm)
    TextView dhhm;
    @BindView(R.id.dabh)
    TextView dabh;
    @BindView(R.id.sfzh)
    TextView sfzh;
    @BindView(R.id.zsdz)
    TextView zsdz;
    @BindView(R.id.lxfs)
    TextView lxfs;
    @BindView(R.id.jszzt)
    TextView jszzt;
    @BindView(R.id.clrq)
    TextView clrq;
    @BindView(R.id.zsxzqh)
    TextView zsxzqh;
    @BindView(R.id.yxqz)
    TextView yxqz;
    @BindView(R.id.zhqfrq)
    TextView zhqfrq;
    @BindView(R.id.dqjf)
    TextView dqjf;
    @BindView(R.id.zhmfqfrq)
    TextView zhmfqfrq;
    @BindView(R.id.xyqfrq)
    TextView xyqfrq;
    @BindView(R.id.xytyrq)
    TextView xytyrq;
    @BindView(R.id.xszxph)
    TextView xszxph;
    @BindView(R.id.sfcf)
    TextView sfcf;
    @BindView(R.id.sfzjbf)
    TextView sfzjbf;
    @BindView(R.id.yqwsy)
    TextView yqwsy;
    @BindView(R.id.yqwhz)
    TextView yqwhz;
    @BindView(R.id.xb)
    TextView xb;
    @BindView(R.id.yxqs)
    TextView yxqs;
    @BindView(R.id.main)
    LinearLayout main;

    private String sfzhIntent;
    private Map<String, String> jszztMap = null;
    private Map<String, String> sfzmmcMap = null;

    private String zpurl = "";

    @Override
    public BasePresenter getPresenter() {
        return new DriverBasicPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_driver_basic;
    }

    /**
     * 获取身份证号
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sfzhIntent = ((DriverInformationActivity) context).getSfzmhm();
    }

    @Override
    protected void initInjector() {
        try {
            // 驾驶证状态
            jszztMap = new HashMap<>();
            sfzmmcMap = new HashMap<>();
            for (DicationResBean bean : DictionaryManager.getInstance().getJszzt()) {
                jszztMap.put(bean.getCode(), bean.getName());
            }
            for (DicationResBean bean : DictionaryManager.getInstance().getSfzmmc()) {
                sfzmmcMap.put(bean.getCode(), bean.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initEventAndData() {
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(zpurl)) {
                    showPopuPicture(zpurl);
                } else {
                    ToastUtils.showToast(getActivity(), "暂无照片信息");
                }
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        //查询数据  请求加载
        ((DriverBasicPresenter) mPresenter).loadDriverBasic(MyApplication.appContext, new QueryDriverRepBean
                (sfzhIntent));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    /**
     * 由于驾驶证状态存在多个值，所有依次解析驾驶证状态
     *
     * @param zt
     * @return
     */
    private String getJszzt(String zt) {

        if (null != zt || !"".equals(zt)) {
            char[] zts = zt.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < zts.length; i++) {

                builder.append(jszztMap.get(String.valueOf(zts[i])));
                if (i != zts.length - 1) {
                    builder.append(",");
                }

            }
            return builder.toString();


        }


        return "";
    }

    @Override
    public void showDriverDatas(QueryDriverBean.DataBean dataBean) {
        try {
            xm.setText(dataBean.getXm());
            zjmc.setText(sfzmmcMap.get(dataBean.getSfzmc()));
            fzjg.setText(dataBean.getFzjg());
            zjcx.setText(dataBean.getZjcx());
            csrq.setText(dataBean.getCsrq());
            dhhm.setText(dataBean.getLxdh());
            dabh.setText(dataBean.getDabh());
            sfzh.setText(dataBean.getSfzmhm());
            zsdz.setText(dataBean.getDjzsxxdz());
            lxfs.setText(dataBean.getLxzsxxdz());
            if (!"A".equals(dataBean.getJszzt())) {
                jszzt.setTextColor(Color.RED);
                jszzt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            }
            jszzt.setText(getJszzt(dataBean.getJszzt()));
            clrq.setText(dataBean.getCclzrq());
            zsxzqh.setText(dataBean.getZsxzqy());
            yxqz.setText(dataBean.getYxqz());
            zhqfrq.setText(dataBean.getZhqfrq());
            xyqfrq.setText(dataBean.getXyqfrq());
            xytyrq.setText(dataBean.getXytjrq());
            xszxph.setText(dataBean.getZxbh());
            if (null == dataBean.getLjjf() || "".equals(dataBean.getLjjf())){
                dqjf.setText("0");
            } else {
                dqjf.setText(dataBean.getLjjf());
                if (Integer.valueOf(dataBean.getLjjf()) > 0){
                    dqjf.setTextColor(Color.RED);
                }
            }
            zhmfqfrq.setText(dataBean.getZhmfqfrq());

            if (null != dataBean.getSfcf()) {
                switch (dataBean.getSfcf()) {
                    case "0":
                        sfcf.setText("是");
                        break;
                    case "1":
                        sfcf.setText("否");
                        break;
                }
            }
            if (null != dataBean.getYqwhz()) {
                switch (dataBean.getYqwhz()) {
                    case "0":
                        yqwhz.setText("是");
                        break;
                    case "1":
                        yqwhz.setText("否");
                        break;
                }
            }
            if (null != dataBean.getYqwsy()) {
                switch (dataBean.getYqwsy()) {
                    case "0":
                        yqwsy.setText("是");
                        break;
                    case "1":
                        yqwsy.setText("否");
                        break;
                }
            }
            if (null != dataBean.getSfzjbf()) {
                switch (dataBean.getSfzjbf()) {
                    case "0":
                        sfzjbf.setText("是");
                        break;
                    case "1":
                        sfzjbf.setText("否");
                        break;
                }
            }
            if (null == dataBean.getSfzmhm() || "".equals(dataBean.getSfzmhm())) {
                xb.setText(""); //性别
            } else {

                xb.setText(getxb(dataBean.getSfzmhm())); //性别
            }
            yxqs.setText(dataBean.getYxqs());
            zpurl = dataBean.getZpid();
            Glide.with(this).load(dataBean.getZpid()).into(userIcon);

        } catch (Exception e) {
            e.printStackTrace();
        }

        rotateText.setOnClickListener(click);
    }

    int ronum = 1;
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ronum++;
            if (ronum > 4) {
                ronum = 1;
            }


            RequestOptions options = new RequestOptions().centerCrop().priority(Priority.HIGH).transform(new 
                    RotateTransformation(getActivity(), 90f * ronum));

            Glide.with(getActivity()).load(zpurl).apply(options).into(userIcon);
        }
    };
    
    /**
     * 判断信息
     */
    private String getxb(String sfzh) {

        String xbstr = sfzh.substring(sfzh.length() - 2, sfzh.length() - 1);
        int xb = Integer.parseInt(xbstr);

        //        LogUtil.open().appendMethodB("性别:" + xb + "\n");
        return xb % 2 == 0 ? "女" : "男";
    }

    /**
     * 显示图片弹窗
     */
    PopupWindow window = null;

    private void showPopuPicture(String url) {

        View popupView = getActivity().getLayoutInflater().inflate(R.layout.warn_photo_single, null);
        final ZoomImageViewGlide zoomImage = (ZoomImageViewGlide) popupView.findViewById(R.id.image);
        TextView btn_cancle = (TextView) popupView.findViewById(R.id.btn_cancle);
        Glide.with(this).load(url).into(zoomImage);
        window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.update();
        window.showAtLocation(main, Gravity.CENTER, 0, 0);
        window.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 如果点击了popupwindow的外部，popupwindow也会消失
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    window.dismiss();
                    return true;
                }
                return false;
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                window.dismiss();
            }
        });

    }
}
