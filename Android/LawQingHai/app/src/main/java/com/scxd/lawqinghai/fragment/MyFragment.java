package com.scxd.lawqinghai.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.VideoActivity;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.utils.ActManager;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.NetUtils;
import com.scxd.lawqinghai.widget.dialog.CustomPrograss;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.FileDialogCallback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.callback.StringDialogCallback;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 我的界面
 */
public class MyFragment extends BaseFragment {


    @BindView(R.id.test)
    TextView test;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.video)
    VideoView video;
    @BindView(R.id.luxiang)
    Button luxiang;
    @BindView(R.id.start)
    Button start;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initInjector() {
        image.setVisibility(View.GONE);

        luxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), VideoActivity.class));
            }
        });
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);
        Glide.with(getActivity()).load("http://11.101.4.57:8082/image/testDownload?id=5").apply(options).into(image);
//        Uri uri = Uri.parse("http://11.101.4.57:8082/movie/testDownloadMovie?id=1");
//        video.setMediaController(new MediaController(getActivity()));
//        video.setVideoURI(uri);
//        //        video.start();  
//        video.requestFocus();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
                OkHttpUtils.get().url("http://11.101.4.57:8082/movie/testDownloadMovie")
                        .addParams("id", "1")
                        .build()
                        .connTimeOut(20000)
                        .readTimeOut(20000)
                        .writeTimeOut(20000)
                        .execute(new FileDialogCallback(getActivity(), "正在加载......",
                                MyApplication.getFileLocation() + "/video",
                                "downvideo.mp4") {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                super.onError(call, e, id);
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(File response, int id) {
                                super.onResponse(response, id);
                                Uri uri = Uri.parse(MyApplication.getFileLocation() + "/video/downvideo.mp4");
                                video.setMediaController(new MediaController(getActivity()));
                                video.setVideoURI(uri);
                                //        video.start();  
                                video.requestFocus();
                            }

                        });

            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPrograss.show(getActivity(), "aaaaaaaa", true, null);
                if (NetUtils.isWifiConnected(getActivity())) {
                    Map<String, String> params = new HashMap<>();
                    File file = new File(MyApplication.getFileLocation() + "/video/test.mp4");
                    if (!file.exists()) {
                        return;
                    }
                    String filename = file.getName();
                    OkHttpUtils.post().url("http://11.101.4.57:8082/image/testDownloadMovie")
                            .params(params)
                            .addFile("file", filename, file)
                            .build().connTimeOut(20000).readTimeOut(20000).writeTimeOut
                            (20000).execute(new StringDialogCallback(getActivity(), "正在上传......") {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            CustomPrograss.disMiss();
                            Log.d("MyFragment", "failed");
                            Toast.makeText(mActivity, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            CustomPrograss.disMiss();
                            Log.d("MyFragment", response);
                            Toast.makeText(mActivity, response, Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                //                String test = LogUtil.open().showInfo();
                //                try {
                //                    JSONArray jsonArray = new JSONArray("[" + test.substring(0, test.length() - 1) 
                // + "]");
                //                    String json = jsonArray.toString();
                //                } catch (JSONException e) {
                //                    e.printStackTrace();
                //                }
            }
        });
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void lazyLoadData() {

    }


    @Override
    public BasePresenter getPresenter() {
        return null;
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
}
