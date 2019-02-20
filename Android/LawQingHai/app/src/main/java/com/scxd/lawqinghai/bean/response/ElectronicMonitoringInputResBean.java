package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * 描述：电子录入信息
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/4
 * 修改人：
 * 修改时间：
 */


public class ElectronicMonitoringInputResBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"list":[{"jdsbh":"633101700000031","hpzl":"02","hphm":"青A111C2","wfxw":"不按车道行驶","wfsj":"2018-08-22 
     * 16:41:45"}],"total":1}
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
         * list : [{"jdsbh":"633101700000031","hpzl":"02","hphm":"青A111C2","wfxw":"不按车道行驶","wfsj":"2018-08-22 
         * 16:41:45"}]
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
             * jdsbh : 633101700000031
             * hpzl : 02
             * hphm : 青A111C2
             * wfxw : 不按车道行驶
             * wfsj : 2018-08-22 16:41:45
             */

            private String jdsbh;
            private String hpzl;
            private String hphm;
            private String wfxw;
            private String wfsj;

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

            public String getWfxw() {
                return wfxw;
            }

            public void setWfxw(String wfxw) {
                this.wfxw = wfxw;
            }

            public String getWfsj() {
                return wfsj;
            }

            public void setWfsj(String wfsj) {
                this.wfsj = wfsj;
            }
        }
    }
}
