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
public class RSPVersionBean {


    /**
     * result : 1
     * message : AJSD
     * resultvalue : [{"version":"123456789","bmbh":"user","url":"password","description":"123456"}]
     */

    private String result;
    private String message;
    private List<ResultvalueBean> resultvalue;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultvalueBean> getResultvalue() {
        return resultvalue;
    }

    public void setResultvalue(List<ResultvalueBean> resultvalue) {
        this.resultvalue = resultvalue;
    }

    public static class ResultvalueBean {
        /**
         * version : 123456789
         * bmbh : user
         * url : password
         * description : 123456
         */

        private String version;
        private String bmbh;
        private String url;
        private String description;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getBmbh() {
            return bmbh;
        }

        public void setBmbh(String bmbh) {
            this.bmbh = bmbh;
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
    }
}
