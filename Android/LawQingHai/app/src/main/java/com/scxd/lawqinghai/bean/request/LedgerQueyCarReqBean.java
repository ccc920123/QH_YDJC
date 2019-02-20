package com.scxd.lawqinghai.bean.request;

/**
 * 描述：查询的车辆信息
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/20
 * 修改人：
 * 修改时间：
 */


public class LedgerQueyCarReqBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"zdcllx":null,"cllx":"未知","wfcs":0,"yqwjy":null,"yqwbf":null,"lxdh":null,"lxdz":null,"hphm":"川A00012","hpzl":"2","clpp":"梅赛德斯","sfdqc":null,"csys":"黑色","zsxzqh":null,"yjdcbgyy":null,"hpqyrq":null,"ccdjrq":"2018-01-01","jdcsyr":"王思聪","syxz":"非运营","clsbdh":"MSDGDKASK21938","gcjk":null,"bxzzrq":null,"hgbzbh":null,"fdjh":"MGKDAK2403929","xszbh":null,"clzt":"1","pl":"8.0","gl":"大","dybj":null,"wkc":0,"jyyxqz":null,"wkk":0,"wkg":0,"fzjg":null}
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
         * zdcllx : null
         * cllx : 未知
         * wfcs : 0
         * yqwjy : null
         * yqwbf : null
         * lxdh : null
         * lxdz : null
         * hphm : 川A00012
         * hpzl : 2
         * clpp : 梅赛德斯
         * sfdqc : null
         * csys : 黑色
         * zsxzqh : null
         * yjdcbgyy : null
         * hpqyrq : null
         * ccdjrq : 2018-01-01
         * jdcsyr : 王思聪
         * syxz : 非运营
         * clsbdh : MSDGDKASK21938
         * gcjk : null
         * bxzzrq : null
         * hgbzbh : null
         * fdjh : MGKDAK2403929
         * xszbh : null
         * clzt : 1
         * pl : 8.0
         * gl : 大
         * dybj : null
         * wkc : 0
         * jyyxqz : null
         * wkk : 0
         * wkg : 0
         * fzjg : null
         */

        private String zdcllx;
        private String cllx;
        private String wfcs;
        private String yqwjy;
        private String yqwbf;
        private String lxdh;
        private String lxdz;
        private String hphm;
        private String hpzl;
        private String clpp;
        private String sfdqc;
        private String csys;
        private String zsxzqh;
        private String yjdcbgyy;
        private String hpqyrq;
        private String ccdjrq;
        private String jdcsyr;
        private String syxz;
        private String clsbdh;
        private String gcjk;
        private String bxzzrq;
        private String hgbzbh;
        private String fdjh;
        private String xszbh;
        private String clzt;
        private String pl;
        private String gl;
        private String dybj;
        private String wkc;
        private String jyyxqz;
        private String wkk;
        private String wkg;
        private String fzjg;

        public String getZdcllx() {
            return zdcllx;
        }

        public void setZdcllx(String zdcllx) {
            this.zdcllx = zdcllx;
        }

        public String getCllx() {
            return cllx;
        }

        public void setCllx(String cllx) {
            this.cllx = cllx;
        }

        public String getWfcs() {
            return wfcs;
        }

        public void setWfcs(String wfcs) {
            this.wfcs = wfcs;
        }

        public String getYqwjy() {
            return yqwjy;
        }

        public void setYqwjy(String yqwjy) {
            this.yqwjy = yqwjy;
        }

        public String getYqwbf() {
            return yqwbf;
        }

        public void setYqwbf(String yqwbf) {
            this.yqwbf = yqwbf;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getLxdz() {
            return lxdz;
        }

        public void setLxdz(String lxdz) {
            this.lxdz = lxdz;
        }

        public String getHphm() {
            return hphm;
        }

        public void setHphm(String hphm) {
            this.hphm = hphm;
        }

        public String getHpzl() {
            return hpzl;
        }

        public void setHpzl(String hpzl) {
            this.hpzl = hpzl;
        }

        public String getClpp() {
            return clpp;
        }

        public void setClpp(String clpp) {
            this.clpp = clpp;
        }

        public String getSfdqc() {
            return sfdqc;
        }

        public void setSfdqc(String sfdqc) {
            this.sfdqc = sfdqc;
        }

        public String getCsys() {
            return csys;
        }

        public void setCsys(String csys) {
            this.csys = csys;
        }

        public String getZsxzqh() {
            return zsxzqh;
        }

        public void setZsxzqh(String zsxzqh) {
            this.zsxzqh = zsxzqh;
        }

        public String getYjdcbgyy() {
            return yjdcbgyy;
        }

        public void setYjdcbgyy(String yjdcbgyy) {
            this.yjdcbgyy = yjdcbgyy;
        }

        public String getHpqyrq() {
            return hpqyrq;
        }

        public void setHpqyrq(String hpqyrq) {
            this.hpqyrq = hpqyrq;
        }

        public String getCcdjrq() {
            return ccdjrq;
        }

        public void setCcdjrq(String ccdjrq) {
            this.ccdjrq = ccdjrq;
        }

        public String getJdcsyr() {
            return jdcsyr;
        }

        public void setJdcsyr(String jdcsyr) {
            this.jdcsyr = jdcsyr;
        }

        public String getSyxz() {
            return syxz;
        }

        public void setSyxz(String syxz) {
            this.syxz = syxz;
        }

        public String getClsbdh() {
            return clsbdh;
        }

        public void setClsbdh(String clsbdh) {
            this.clsbdh = clsbdh;
        }

        public String getGcjk() {
            return gcjk;
        }

        public void setGcjk(String gcjk) {
            this.gcjk = gcjk;
        }

        public String getBxzzrq() {
            return bxzzrq;
        }

        public void setBxzzrq(String bxzzrq) {
            this.bxzzrq = bxzzrq;
        }

        public String getHgbzbh() {
            return hgbzbh;
        }

        public void setHgbzbh(String hgbzbh) {
            this.hgbzbh = hgbzbh;
        }

        public String getFdjh() {
            return fdjh;
        }

        public void setFdjh(String fdjh) {
            this.fdjh = fdjh;
        }

        public String getXszbh() {
            return xszbh;
        }

        public void setXszbh(String xszbh) {
            this.xszbh = xszbh;
        }

        public String getClzt() {
            return clzt;
        }

        public void setClzt(String clzt) {
            this.clzt = clzt;
        }

        public String getPl() {
            return pl;
        }

        public void setPl(String pl) {
            this.pl = pl;
        }

        public String getGl() {
            return gl;
        }

        public void setGl(String gl) {
            this.gl = gl;
        }

        public String getDybj() {
            return dybj;
        }

        public void setDybj(String dybj) {
            this.dybj = dybj;
        }

        public String getWkc() {
            return wkc;
        }

        public void setWkc(String wkc) {
            this.wkc = wkc;
        }

        public String getJyyxqz() {
            return jyyxqz;
        }

        public void setJyyxqz(String jyyxqz) {
            this.jyyxqz = jyyxqz;
        }

        public String getWkk() {
            return wkk;
        }

        public void setWkk(String wkk) {
            this.wkk = wkk;
        }

        public String getWkg() {
            return wkg;
        }

        public void setWkg(String wkg) {
            this.wkg = wkg;
        }

        public String getFzjg() {
            return fzjg;
        }

        public void setFzjg(String fzjg) {
            this.fzjg = fzjg;
        }
    }
}
