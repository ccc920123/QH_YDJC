package com.scxd.lawqinghai.mvp.view;

/**
 * MVP基础view
 */
public interface BaseView {
    /**
     * 启动消息等待框
     * @param str
     */
    void showLoadProgressDialog(String str);

    /**
     * 关闭
     */
    void disDialog();

    /**
     * Toast提示
     * @param message
     */
    void showToast(String message);

}