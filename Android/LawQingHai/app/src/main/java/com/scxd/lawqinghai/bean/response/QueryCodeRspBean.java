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
public class QueryCodeRspBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"xxts":595,"list":[{"wfdm":"1075","wfnr":"在车门、车厢没有关好时行车的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},
     * {"wfdm":"1076","wfnr":"机动车在没有划分机动车道、非机动车道和人行道的道路上，不在道路中间通行的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},
     * {"wfdm":"1077","wfnr":"驾驶机动车下陡坡时熄火、空档滑行的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"1081",
     * "wfnr":"除客运、危险物品运输车辆外，连续驾驶机动车超过4小时未停车休息或停车休息时间少于20分钟的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},
     * {"wfdm":"1082","wfnr":"使用他人机动车驾驶证驾驶机动车的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"1610",
     * "wfnr":"在驾驶证暂扣期间仍驾驶机动车的","wfjf":"6","flyj":"《中华人民共和国道路交通安全法》第九十九条第一款"},{"wfdm":"4010",
     * "wfnr":"在高速公路上骑、轧车行道分界线的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"4011","wfnr":"在高速公路上试车或学习驾驶机动车的",
     * "wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"1088","wfnr":"以欺骗、贿赂等不正当手段办理补、换领机动车登记证书、号牌、行驶证和检查合格标志等业务的",
     * "wfjf":"0","flyj":"《机动车登记规定》第四十九条第二款"},{"wfdm":"1057","wfnr":"机动车在单位院内居民居住区内不低速行驶的","wfjf":"0",
     * "flyj":"《中华人民共和国道路交通安全法》第九十条"}]}
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
         * xxts : 595
         * list : [{"wfdm":"1075","wfnr":"在车门、车厢没有关好时行车的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"1076",
         * "wfnr":"机动车在没有划分机动车道、非机动车道和人行道的道路上，不在道路中间通行的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"1077",
         * "wfnr":"驾驶机动车下陡坡时熄火、空档滑行的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"1081",
         * "wfnr":"除客运、危险物品运输车辆外，连续驾驶机动车超过4小时未停车休息或停车休息时间少于20分钟的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},
         * {"wfdm":"1082","wfnr":"使用他人机动车驾驶证驾驶机动车的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"1610",
         * "wfnr":"在驾驶证暂扣期间仍驾驶机动车的","wfjf":"6","flyj":"《中华人民共和国道路交通安全法》第九十九条第一款"},{"wfdm":"4010",
         * "wfnr":"在高速公路上骑、轧车行道分界线的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"4011",
         * "wfnr":"在高速公路上试车或学习驾驶机动车的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"},{"wfdm":"1088",
         * "wfnr":"以欺骗、贿赂等不正当手段办理补、换领机动车登记证书、号牌、行驶证和检查合格标志等业务的","wfjf":"0","flyj":"《机动车登记规定》第四十九条第二款"},
         * {"wfdm":"1057","wfnr":"机动车在单位院内居民居住区内不低速行驶的","wfjf":"0","flyj":"《中华人民共和国道路交通安全法》第九十条"}]
         */

        private String xxts;
        private List<ListBean> list;

        public String getXxts() {
            return xxts;
        }

        public void setXxts(String xxts) {
            this.xxts = xxts;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * wfdm : 1075
             * wfnr : 在车门、车厢没有关好时行车的
             * wfjf : 0
             * flyj : 《中华人民共和国道路交通安全法》第九十条
             */

            private String wfdm;
            private String wfnr;
            private String wfjf;
            private String flyj;

            public String getWfdm() {
                return wfdm;
            }

            public void setWfdm(String wfdm) {
                this.wfdm = wfdm;
            }

            public String getWfnr() {
                return wfnr;
            }

            public void setWfnr(String wfnr) {
                this.wfnr = wfnr;
            }

            public String getWfjf() {
                return wfjf;
            }

            public void setWfjf(String wfjf) {
                this.wfjf = wfjf;
            }

            public String getFlyj() {
                return flyj;
            }

            public void setFlyj(String flyj) {
                this.flyj = flyj;
            }
        }
    }
}
