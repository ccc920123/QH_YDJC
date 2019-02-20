package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * 描述： 路段
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/7
 * 修改人：
 * 修改时间：
 */


public class LDRespJson {
    private static final String OK = "ok";
    private static final String ERROR = "error";

    private LDRespJson.Meta meta;     // 元数据
    private List<LDRespBean> data;   // 响应内容

    public LDRespJson success() {
        this.meta = new LDRespJson.Meta(true, OK);
        return this;
    }

    public LDRespJson success(List<LDRespBean> data) {
        this.meta = new LDRespJson.Meta(true, OK);
        this.data = data;
        return this;
    }

    public LDRespJson failure() {
        this.meta = new LDRespJson.Meta(false, ERROR);
        return this;
    }

    public LDRespJson failure(String message) {
        this.meta = new LDRespJson.Meta(false, message);
        return this;
    }

    public LDRespJson.Meta getMeta() {
        return meta;
    }

    public List<LDRespBean> getData() {
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
