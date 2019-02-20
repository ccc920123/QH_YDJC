package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * 描述： 简易处决书
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/4
 * 修改人：
 * 修改时间：
 */


public class SummaryPunishmentDecisionResBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"list":[{"jdsbh":"123456789","hpzl":"02","hphm":"青A111C2","dsr":"陈渝金","jszh":"511303199201230919"}],
     * "total":1}
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
         * list : [{"jdsbh":"123456789","hpzl":"02","hphm":"青A111C2","dsr":"陈渝金","jszh":"511303199201230919"}]
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

        public static class ListBean {
            /**
             * jdsbh : 123456789
             * hpzl : 02
             * hphm : 青A111C2
             * dsr : 陈渝金
             * jszh : 511303199201230919
             */

            private String jdsbh;
            private String hpzl;
            private String hphm;
            private String dsr;
            private String jszh;

            public String getJdsbh() {
                return jdsbh;
            }

            public void setJdsbh(String jdsbh) {
                this.jdsbh = jdsbh;
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

            public String getDsr() {
                return dsr;
            }

            public void setDsr(String dsr) {
                this.dsr = dsr;
            }

            public String getJszh() {
                return jszh;
            }

            public void setJszh(String jszh) {
                this.jszh = jszh;
            }
        }
    }
}
