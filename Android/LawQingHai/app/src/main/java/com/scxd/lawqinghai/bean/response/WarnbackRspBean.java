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
public class WarnbackRspBean implements Serializable  {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"yjsj":"2018-08-28 14:30:29","ljqk":"","wljdyy":"","sfxycl":"","fxyclyy":"","ljqkms":"","cjry":"李一川",
     * "czfs":"","wsbh":"","wslb":"","yjbm":"","cfxylx":"","jyw":"","czdw":"","lxdh":"","lxr":"","dldm":"20612",
     * "dlmc":"西和高速23公里300米","xzqh":"633102,633103,633106","lddm":"","ldmc":"","czr":"李一川","zpid":[],"fxyxlyy":""}
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

    public static class DataBean implements Serializable {
        /**
         * yjsj : 2018-08-28 14:30:29
         * ljqk : 
         * wljdyy : 
         * sfxycl : 
         * fxyclyy : 
         * ljqkms : 
         * cjry : 李一川
         * czfs : 
         * wsbh : 
         * wslb : 
         * yjbm : 
         * cfxylx : 
         * jyw : 
         * czdw : 
         * lxdh : 
         * lxr : 
         * dldm : 20612
         * dlmc : 西和高速23公里300米
         * xzqh : 633102,633103,633106
         * lddm : 
         * ldmc : 
         * czr : 李一川
         * zpid : []
         * fxyxlyy : 
         */

        private String yjsj;
        private String ljqk;
        private String wljdyy;
        private String sfxycl;
        private String fxyclyy;
        private String ljqkms;
        private String cjry;
        private String czfs;
        private String wsbh;
        private String wslb;
        private String yjbm;
        private String cfxylx;
        private String jyw;
        private String czdw;
        private String lxdh;
        private String lxr;
        private String dldm;
        private String dlmc;
        private String xzqh;
        private String lddm;
        private String ldmc;
        private String czr;
        private String fxyxlyy;
        private List<String> zpid;

        public String getYjsj() {
            return yjsj;
        }

        public void setYjsj(String yjsj) {
            this.yjsj = yjsj;
        }

        public String getLjqk() {
            return ljqk;
        }

        public void setLjqk(String ljqk) {
            this.ljqk = ljqk;
        }

        public String getWljdyy() {
            return wljdyy;
        }

        public void setWljdyy(String wljdyy) {
            this.wljdyy = wljdyy;
        }

        public String getSfxycl() {
            return sfxycl;
        }

        public void setSfxycl(String sfxycl) {
            this.sfxycl = sfxycl;
        }

        public String getFxyclyy() {
            return fxyclyy;
        }

        public void setFxyclyy(String fxyclyy) {
            this.fxyclyy = fxyclyy;
        }

        public String getLjqkms() {
            return ljqkms;
        }

        public void setLjqkms(String ljqkms) {
            this.ljqkms = ljqkms;
        }

        public String getCjry() {
            return cjry;
        }

        public void setCjry(String cjry) {
            this.cjry = cjry;
        }

        public String getCzfs() {
            return czfs;
        }

        public void setCzfs(String czfs) {
            this.czfs = czfs;
        }

        public String getWsbh() {
            return wsbh;
        }

        public void setWsbh(String wsbh) {
            this.wsbh = wsbh;
        }

        public String getWslb() {
            return wslb;
        }

        public void setWslb(String wslb) {
            this.wslb = wslb;
        }

        public String getYjbm() {
            return yjbm;
        }

        public void setYjbm(String yjbm) {
            this.yjbm = yjbm;
        }

        public String getCfxylx() {
            return cfxylx;
        }

        public void setCfxylx(String cfxylx) {
            this.cfxylx = cfxylx;
        }

        public String getJyw() {
            return jyw;
        }

        public void setJyw(String jyw) {
            this.jyw = jyw;
        }

        public String getCzdw() {
            return czdw;
        }

        public void setCzdw(String czdw) {
            this.czdw = czdw;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getLxr() {
            return lxr;
        }

        public void setLxr(String lxr) {
            this.lxr = lxr;
        }

        public String getDldm() {
            return dldm;
        }

        public void setDldm(String dldm) {
            this.dldm = dldm;
        }

        public String getDlmc() {
            return dlmc;
        }

        public void setDlmc(String dlmc) {
            this.dlmc = dlmc;
        }

        public String getXzqh() {
            return xzqh;
        }

        public void setXzqh(String xzqh) {
            this.xzqh = xzqh;
        }

        public String getLddm() {
            return lddm;
        }

        public void setLddm(String lddm) {
            this.lddm = lddm;
        }

        public String getLdmc() {
            return ldmc;
        }

        public void setLdmc(String ldmc) {
            this.ldmc = ldmc;
        }

        public String getCzr() {
            return czr;
        }

        public void setCzr(String czr) {
            this.czr = czr;
        }

        public String getFxyxlyy() {
            return fxyxlyy;
        }

        public void setFxyxlyy(String fxyxlyy) {
            this.fxyxlyy = fxyxlyy;
        }

        public List<String> getZpid() {
            return zpid;
        }

        public void setZpid(List<String> zpid) {
            this.zpid = zpid;
        }
    }
}
