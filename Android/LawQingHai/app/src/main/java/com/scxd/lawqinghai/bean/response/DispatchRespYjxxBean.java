package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * 描述：布控预警信息
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/15
 * 修改人：
 * 修改时间：
 */


public class DispatchRespYjxxBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : [{"name":"酒驾","code":"0101","flag":"1","type":"交通违法"},
     * {"name":"毒驾","code":"0102","flag":"1","type":"交通违法"},
     * {"name":"客车超载","code":"0103","flag":"0","type":"交通违法"},
     * {"name":"违法犯罪","code":"0110","flag":"0","type":"公安案情"},
     * {"name":"刑事案件","code":"0108","flag":"0","type":"公安案情"},
     * {"name":"涉牌涉证","code":"0104","flag":"0","type":"交通违法"},
     * {"name":"逆行","code":"0106","flag":"0","type":"交通违法"},
     * {"name":"治安案件","code":"0109","flag":"0","type":"公安案情"},
     * {"name":"违停","code":"0105","flag":"0","type":"交通违法"},
     * {"name":"逃逸","code":"0107","flag":"0","type":"公安案情"},
     * {"name":"治安管制","code":"0112","flag":"0","type":"公安案情"},
     * {"name":"被盗抢","code":"0111","flag":"0","type":"公安案情"}]
     */

    private MetaBean meta;
    private List<DataBean> data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
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
         * name : 酒驾
         * code : 0101
         * flag : 1
         * type : 交通违法
         */

        private String name;
        private String code;
        private String flag;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
