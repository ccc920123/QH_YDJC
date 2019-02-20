package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * 描述：布控出警人员
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/15
 * 修改人：
 * 修改时间：
 */


public class DispatchRespcjryBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : [{"name":"罗警官","code":"X002","flag":"0"},{"name":"黄警官","code":"X006","flag":"0"},{"name":"李警官","code":"X004","flag":"0"},{"name":"赵警官","code":"X007","flag":"0"},{"name":"王警官","code":"X003","flag":"0"},{"name":"张警官","code":"X001","flag":"0"},{"name":"陈警官","code":"X005","flag":"0"}]
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
         * name : 罗警官
         * code : X002
         * flag : 0
         */

        private String name;
        private String code;
        private String flag;

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
    }
}
