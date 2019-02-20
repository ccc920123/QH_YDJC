package com.zhy.http.okhttp.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class FileDialogCallback extends FileCallBack {


    private ProgressDialog dialog;
    
    
    public FileDialogCallback(Activity activity, String message, String destFileDir, String destFileName) {
        super(activity, message, destFileDir, destFileName);

        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage(message);
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void inProgress(float progress, long total, int id) {
        super.inProgress(progress, total, id);
        dialog.setProgress((int) (100 * progress));
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onResponse(File response, int id) {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    
}
