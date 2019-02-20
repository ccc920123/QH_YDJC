package com.scxd.lawqinghai.bean.response;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnJpushRspBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"yjxh":"9651875","hphm":"川A66666","yjsj":"2018-06-20","cllx":null,"yjlx":"0201","status":null}
     */

    private MetaBean meta;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * yjxh : 9651875
         * hphm : 川A66666
         * yjsj : 2018-06-20
         * cllx : null
         * yjlx : 0201
         * status : null
         */

        private String yjxh;
        private String hphm;
        private String yjsj;
        private String cllx;
        private String yjlx;

        public String getYjzlx() {
            return yjzlx;
        }

        public void setYjzlx(String yjzlx) {
            this.yjzlx = yjzlx;
        }

        private String yjzlx;
        private String status;

        public String getYjxh() {
            return yjxh;
        }

        public void setYjxh(String yjxh) {
            this.yjxh = yjxh;
        }

        public String getHphm() {
            return hphm;
        }

        public void setHphm(String hphm) {
            this.hphm = hphm;
        }

        public String getYjsj() {
            return yjsj;
        }

        public void setYjsj(String yjsj) {
            this.yjsj = yjsj;
        }

        public String getCllx() {
            return cllx;
        }

        public void setCllx(String cllx) {
            this.cllx = cllx;
        }

        public String getYjlx() {
            return yjlx;
        }

        public void setYjlx(String yjlx) {
            this.yjlx = yjlx;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
