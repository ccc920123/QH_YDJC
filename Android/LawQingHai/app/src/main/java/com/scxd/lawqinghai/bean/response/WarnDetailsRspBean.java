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
public class WarnDetailsRspBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"hpzl":"02","hphm":"青A7C053","yjlx":"34","yjzlx":null,"yjsj":"2018-09-14 17:46:58","cllx":"K33",
     * "gcsj":"2018-09-14 17:46:58","xssd":null,"xsqy":null,"xsdd":null,"syxz":"A","sbzl":null,"sbmc":null,
     * "jszzt":null,"qsjg":null,"sfcj":null,"qszt":0,"kkmc":"西和高速34公里810米[湟源方向]","jszh":"511303199201230919",
     * "zjcx":"C1","cclzrq":"2016-04-19 00:00:00","jszxbh":"","dabh":"511304018023","ljjf":"0","jszyxqz":"2022-04-19 
     * 00:00:00","zpid":[]}
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
         * hpzl : 02
         * hphm : 青A7C053
         * yjlx : 34
         * yjzlx : null
         * yjsj : 2018-09-14 17:46:58
         * cllx : K33
         * gcsj : 2018-09-14 17:46:58
         * xssd : null
         * xsqy : null
         * xsdd : null
         * syxz : A
         * sbzl : null
         * sbmc : null
         * jszzt : null
         * qsjg : null
         * sfcj : null
         * qszt : 0
         * kkmc : 西和高速34公里810米[湟源方向]
         * jszh : 511303199201230919
         * zjcx : C1
         * cclzrq : 2016-04-19 00:00:00
         * jszxbh : 
         * dabh : 511304018023
         * ljjf : 0
         * jszyxqz : 2022-04-19 00:00:00
         * zpid : []
         */

        private String hpzl;
        private String hphm;
        private String yjlx;
        private String yjzlx;
        private String yjsj;
        private String cllx;
        private String gcsj;
        private String xssd;
        private String xsqy;
        private String xsdd;
        private String syxz;
        private String sbzl;
        private String sbmc;
        private String jszzt;
        private String qsjg;
        private String sfcj;
        private String qszt;
        private String kkmc;
        private String jszh;
        private String zjcx;
        private String cclzrq;
        private String jszxbh;
        private String dabh;
        private String ljjf;
        private String jszyxqz;
        private List<String> zpid;

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

        public String getYjlx() {
            return yjlx;
        }

        public void setYjlx(String yjlx) {
            this.yjlx = yjlx;
        }

        public String getYjzlx() {
            return yjzlx;
        }

        public void setYjzlx(String yjzlx) {
            this.yjzlx = yjzlx;
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

        public String getGcsj() {
            return gcsj;
        }

        public void setGcsj(String gcsj) {
            this.gcsj = gcsj;
        }

        public String getXssd() {
            return xssd;
        }

        public void setXssd(String xssd) {
            this.xssd = xssd;
        }

        public String getXsqy() {
            return xsqy;
        }

        public void setXsqy(String xsqy) {
            this.xsqy = xsqy;
        }

        public String getXsdd() {
            return xsdd;
        }

        public void setXsdd(String xsdd) {
            this.xsdd = xsdd;
        }

        public String getSyxz() {
            return syxz;
        }

        public void setSyxz(String syxz) {
            this.syxz = syxz;
        }

        public String getSbzl() {
            return sbzl;
        }

        public void setSbzl(String sbzl) {
            this.sbzl = sbzl;
        }

        public String getSbmc() {
            return sbmc;
        }

        public void setSbmc(String sbmc) {
            this.sbmc = sbmc;
        }

        public String getJszzt() {
            return jszzt;
        }

        public void setJszzt(String jszzt) {
            this.jszzt = jszzt;
        }

        public String getQsjg() {
            return qsjg;
        }

        public void setQsjg(String qsjg) {
            this.qsjg = qsjg;
        }

        public String getSfcj() {
            return sfcj;
        }

        public void setSfcj(String sfcj) {
            this.sfcj = sfcj;
        }

        public String getQszt() {
            return qszt;
        }

        public void setQszt(String qszt) {
            this.qszt = qszt;
        }

        public String getKkmc() {
            return kkmc;
        }

        public void setKkmc(String kkmc) {
            this.kkmc = kkmc;
        }

        public String getJszh() {
            return jszh;
        }

        public void setJszh(String jszh) {
            this.jszh = jszh;
        }

        public String getZjcx() {
            return zjcx;
        }

        public void setZjcx(String zjcx) {
            this.zjcx = zjcx;
        }

        public String getCclzrq() {
            return cclzrq;
        }

        public void setCclzrq(String cclzrq) {
            this.cclzrq = cclzrq;
        }

        public String getJszxbh() {
            return jszxbh;
        }

        public void setJszxbh(String jszxbh) {
            this.jszxbh = jszxbh;
        }

        public String getDabh() {
            return dabh;
        }

        public void setDabh(String dabh) {
            this.dabh = dabh;
        }

        public String getLjjf() {
            return ljjf;
        }

        public void setLjjf(String ljjf) {
            this.ljjf = ljjf;
        }

        public String getJszyxqz() {
            return jszyxqz;
        }

        public void setJszyxqz(String jszyxqz) {
            this.jszyxqz = jszyxqz;
        }

        public List<String> getZpid() {
            return zpid;
        }

        public void setZpid(List<String> zpid) {
            this.zpid = zpid;
        }
    }
}
