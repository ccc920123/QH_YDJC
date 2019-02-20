package com.scxd.common;

/**
 * 调用WEB接口返回的数据,成功,失败,以及失败
 */
public class WebResponse {
    private boolean success;
    private String message;

    public WebResponse() {
        this.success = true;

    }

    public WebResponse(boolean success) {
        this.success = success;
    }

    public WebResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
