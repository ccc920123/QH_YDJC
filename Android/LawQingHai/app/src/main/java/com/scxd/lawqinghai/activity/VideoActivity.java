package com.scxd.lawqinghai.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.utils.CustomFileCipherUtil;
import com.scxd.lawqinghai.utils.NetUtils;
import com.scxd.lawqinghai.widget.dialog.CustomPrograss;
import com.stardon.lib.videolib.activity.VideoActivityLib;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.callback.StringDialogCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class VideoActivity extends VideoActivityLib {
    
    public String filePath = MyApplication.getFileLocation() + "/video";
    public String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    @Override
    public void bindView() {
        setVideoPath(filePath, fileName + ".mp4");
    }

    @Override
    public void getVideoLeftBackButton(View leftButton) {
        
    }

    @Override
    public void getVideoRightBackText(TextView right) {
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onLoad onLoad = new onLoad();
//                onLoad.execute();
                finish();
            }
        });
    }

    @Override
    public void getFinshText(final TextView finsh) {
        finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetUtils.isWifiConnected(VideoActivity.this)) {
                    Map<String, String> params = new HashMap<>();
                    File file = new File(filePath + "/" + fileName + ".mp4");
                    if (!file.exists()) {
                        return;
                    }
                    String filename = file.getName();
                    OkHttpUtils.post().url("http://11.101.4.57:8082/movie/uploadBigFile")
                            .params(params)
                            .addFile("file", filename, file)
                            .build()
                            .connTimeOut(20000)
                            .readTimeOut(20000)
                            .writeTimeOut(20000)
                            .execute(new StringDialogCallback(VideoActivity.this, "正在上传......") {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            super.onError(call, e, id);
                            Log.d("MyFragment", "failed");
                            Toast.makeText(VideoActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            super.onResponse(response, id);
                            Log.d("MyFragment", response);
                            finish();
                            Toast.makeText(VideoActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
            
                
    }

    @Override
    public void getRetakeText(TextView retake) {
        
    }

    @Override
    public void getVideoData(String videoPath) { 
        Toast.makeText(this, "文件已保存：" + videoPath, Toast.LENGTH_SHORT).show();
    }
    
}
