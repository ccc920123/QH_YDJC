package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * 描述：道路
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/7
 * 修改人：
 * 修改时间：
 */


public class DLRespJson {
    private static final String OK = "ok";
    private static final String ERROR = "error";

    private DLRespJson.Meta meta;     // 元数据
    private List<DLRespBean> data;   // 响应内容

    public DLRespJson success() {
        this.meta = new DLRespJson.Meta(true, OK);
        return this;
    }

    public DLRespJson success(List<DLRespBean> data) {
        this.meta = new DLRespJson.Meta(true, OK);
        this.data = data;
        return this;
    }

    public DLRespJson failure() {
        this.meta = new DLRespJson.Meta(false, ERROR);
        return this;
    }

    public DLRespJson failure(String message) {
        this.meta = new DLRespJson.Meta(false, message);
        return this;
    }

    public DLRespJson.Meta getMeta() {
        return meta;
    }

    public List<DLRespBean> getData() {
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
