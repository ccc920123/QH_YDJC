package com.scxd.lawqinghai.bean.response;

/**
 * 描述：   文书编号解析
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/8 19:29
 * 修改人：
 * 修改时间：
 */


public class WsbhResJson {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;     // 元数据
    private WsbhBeanRes data;   // 响应内容

    public WsbhResJson success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public WsbhResJson success(WsbhBeanRes data) {
        this.meta = new Meta(true, OK);
        this. data= data;
        return this;
    }

    public WsbhResJson failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public WsbhResJson failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public WsbhBeanRes getData() {
        return data;
    }

    /**
     * 请求元数据
     */
    public class Meta {

        private boolean success;
        private String message;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }


}
