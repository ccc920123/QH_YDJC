package com.scxd.lawqinghai.bean.response;

/**
 * 描述：查询简易处罚书的详细数据
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/4
 * 修改人：
 * 修改时间：
 */


public class BaseNotifcationOfCompulsoryMeasuresDetailJson {

    private MetaBean meta;
    private NotificationOfCompulsoryMeasuresDetailResBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public NotificationOfCompulsoryMeasuresDetailResBean getData() {
        return data;
    }

    public void setData(NotificationOfCompulsoryMeasuresDetailResBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * success : true
         * message : ok
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
