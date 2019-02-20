package com.scxd.lawqinghai.bean.response;

/**
 * 描述：升级对像
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/13
 * 修改人：
 * {"meta":{"success":true,"message":"ok"},
 * "data":{"bmbh":"630100000000",
 * "version":"1.3.2","url":"http://localhost:8087/Web/upload/apks/1532338117386xdjdccy_1.3.3.4_TEST.apk","description":"啊实打实大师","dictionary":"1.0.0"}}
 *
 *
 *
 *
 * 修改时间：
 */


public class UpDataDownloadBeanRes {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"bmbh":"630100000000","version":"1.3.2","url":"http://localhost:8087/Web/upload/apks/1532338117386xdjdccy_1.3.3.4_TEST.apk","description":"啊实打实大师","dictionary":"1.0.0"}
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
         * bmbh : 630100000000
         * version : 1.3.2
         * url : http://localhost:8087/Web/upload/apks/1532338117386xdjdccy_1.3.3.4_TEST.apk
         * description : 啊实打实大师
         * dictionary : 1.0.0
         */

        private String bmbh;
        private String version;
        private String url;
        private String description;
        private String dictionary;

        public String getBmbh() {
            return bmbh;
        }

        public void setBmbh(String bmbh) {
            this.bmbh = bmbh;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDictionary() {
            return dictionary;
        }

        public void setDictionary(String dictionary) {
            this.dictionary = dictionary;
        }
    }
}
