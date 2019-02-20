package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnListBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"list":[{"yjxh":"6300A00062662170","hphm":"青A1567D","yjsj":"2018-08-28 11:09:02","cllx":"H32",
     * "yjlx":"04","yjzlx":null,"status":"2"},{"yjxh":"6300A00062666025","hphm":"青AS6184","yjsj":"2018-08-28 
     * 11:09:02","cllx":"K31","yjlx":"04","yjzlx":null,"status":"2"},{"yjxh":"6300A00062653910","hphm":"青AV1579",
     * "yjsj":"2018-08-28 11:09:02","cllx":"K39","yjlx":"04","yjzlx":null,"status":"2"},{"yjxh":"6300A00062665381",
     * "hphm":"青AYY885","yjsj":"2018-08-28 11:09:02","cllx":"K33","yjlx":"04","yjzlx":null,"status":"2"},
     * {"yjxh":"6300A00062656231","hphm":"青A6A996","yjsj":"2018-08-28 11:09:02","cllx":"K31","yjlx":"04",
     * "yjzlx":null,"status":"2"},{"yjxh":"6300A00062662545","hphm":"青AS0265","yjsj":"2018-08-28 11:09:02",
     * "cllx":"H31","yjlx":"04","yjzlx":null,"status":"2"},{"yjxh":"6300A00062657427","hphm":"青AF0493",
     * "yjsj":"2018-08-28 11:09:02","cllx":"K33","yjlx":"04","yjzlx":null,"status":"2"},{"yjxh":"6300A00062663401",
     * "hphm":"青A64363","yjsj":"2018-08-28 11:09:02","cllx":"K31","yjlx":"04","yjzlx":null,"status":"2"},
     * {"yjxh":"6300A00062656683","hphm":"青BQ6762","yjsj":"2018-08-28 11:09:02","cllx":"K33","yjlx":"04",
     * "yjzlx":null,"status":"2"},{"yjxh":"6300A00062664207","hphm":"青AV2522","yjsj":"2018-08-28 11:09:02",
     * "cllx":"K33","yjlx":"04","yjzlx":null,"status":"2"}],"total":12}
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
         * list : [{"yjxh":"6300A00062662170","hphm":"青A1567D","yjsj":"2018-08-28 11:09:02","cllx":"H32","yjlx":"04",
         * "yjzlx":null,"status":"2"},{"yjxh":"6300A00062666025","hphm":"青AS6184","yjsj":"2018-08-28 11:09:02",
         * "cllx":"K31","yjlx":"04","yjzlx":null,"status":"2"},{"yjxh":"6300A00062653910","hphm":"青AV1579",
         * "yjsj":"2018-08-28 11:09:02","cllx":"K39","yjlx":"04","yjzlx":null,"status":"2"},
         * {"yjxh":"6300A00062665381","hphm":"青AYY885","yjsj":"2018-08-28 11:09:02","cllx":"K33","yjlx":"04",
         * "yjzlx":null,"status":"2"},{"yjxh":"6300A00062656231","hphm":"青A6A996","yjsj":"2018-08-28 11:09:02",
         * "cllx":"K31","yjlx":"04","yjzlx":null,"status":"2"},{"yjxh":"6300A00062662545","hphm":"青AS0265",
         * "yjsj":"2018-08-28 11:09:02","cllx":"H31","yjlx":"04","yjzlx":null,"status":"2"},
         * {"yjxh":"6300A00062657427","hphm":"青AF0493","yjsj":"2018-08-28 11:09:02","cllx":"K33","yjlx":"04",
         * "yjzlx":null,"status":"2"},{"yjxh":"6300A00062663401","hphm":"青A64363","yjsj":"2018-08-28 11:09:02",
         * "cllx":"K31","yjlx":"04","yjzlx":null,"status":"2"},{"yjxh":"6300A00062656683","hphm":"青BQ6762",
         * "yjsj":"2018-08-28 11:09:02","cllx":"K33","yjlx":"04","yjzlx":null,"status":"2"},
         * {"yjxh":"6300A00062664207","hphm":"青AV2522","yjsj":"2018-08-28 11:09:02","cllx":"K33","yjlx":"04",
         * "yjzlx":null,"status":"2"}]
         * total : 12
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
             * yjxh : 6300A00062662170
             * hphm : 青A1567D
             * yjsj : 2018-08-28 11:09:02
             * cllx : H32
             * yjlx : 04
             * yjzlx : null
             * status : 2
             */

            private String yjxh;
            private String hphm;
            private String yjsj;
            private String cllx;
            private String yjlx;
            private Object yjzlx;
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

            public Object getYjzlx() {
                return yjzlx;
            }

            public void setYjzlx(Object yjzlx) {
                this.yjzlx = yjzlx;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
