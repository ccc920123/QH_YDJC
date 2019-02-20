package com.scxd.lawqinghai.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LedgerListRespBean implements Serializable  {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"list":[{"tzlsh":"d67470f9-7ef1-44b1-9040-65c2127775ac","fwzbh":"633101000010040001","clzt":"4",
     * "hpzl":"02","hphm":"青A111C2","jccllx":"05","jcsj":"2018-08-22 15:53:05"}],"total":1}
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

    public static class MetaBean implements Serializable  {
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

    public static class DataBean implements Serializable  {
        /**
         * list : [{"tzlsh":"d67470f9-7ef1-44b1-9040-65c2127775ac","fwzbh":"633101000010040001","clzt":"4",
         * "hpzl":"02","hphm":"青A111C2","jccllx":"05","jcsj":"2018-08-22 15:53:05"}]
         * total : 1
         */

        private String total;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * tzlsh : d67470f9-7ef1-44b1-9040-65c2127775ac
             * fwzbh : 633101000010040001
             * clzt : 4
             * hpzl : 02
             * hphm : 青A111C2
             * jccllx : 05
             * jcsj : 2018-08-22 15:53:05
             */

            private String tzlsh;
            private String fwzbh;
            private String clzt;
            private String hpzl;
            private String hphm;
            private String jccllx;
            private String jcsj;

            public String getTzlsh() {
                return tzlsh;
            }

            public void setTzlsh(String tzlsh) {
                this.tzlsh = tzlsh;
            }

            public String getFwzbh() {
                return fwzbh;
            }

            public void setFwzbh(String fwzbh) {
                this.fwzbh = fwzbh;
            }

            public String getClzt() {
                return clzt;
            }

            public void setClzt(String clzt) {
                this.clzt = clzt;
            }

            public String getHpzl() {
                return hpzl;
            }

            public void setHpzl(String hpzl) {
                this.hpzl = hpzl;
            }

            public String getHphm() {
                return hphm;
            }

            public void setHphm(String hphm) {
                this.hphm = hphm;
            }

            public String getJccllx() {
                return jccllx;
            }

            public void setJccllx(String jccllx) {
                this.jccllx = jccllx;
            }

            public String getJcsj() {
                return jcsj;
            }

            public void setJcsj(String jcsj) {
                this.jcsj = jcsj;
            }
        }
    }
}
