package com.scxd.lawqinghai.bean.request;

/**
 * 描述：驾驶人信息
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/20
 * 修改人：
 * 修改时间：
 */


public class LedgerDriverBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"xm":"胡漪鶨","lxdh":"18000000000","jszzt":"1","cclzrq":1514736000000,"sfcf":null,"yqwsy":null,"yqwhz":null,"fzjg":"四川省成都市交管局","zjcx":"c1","lxzsxxdz":"无","sfzmc":"A","csrq":1514736000000,"dabh":"5100000 ","sfzmhm":"11111","zsxzqy":"无","jzqx":"2019-01-01 00:00:00","zhqfrq":null,"ljjf":12,"zhmfqfrq":null,"xyqfrq":null,"xytjrq":1527782400000,"djzsxxdz":"无","zxbh":"999999999"}
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
         * xm : 胡漪鶨
         * lxdh : 18000000000
         * jszzt : 1
         * cclzrq : 1514736000000
         * sfcf : null
         * yqwsy : null
         * yqwhz : null
         * fzjg : 四川省成都市交管局
         * zjcx : c1
         * lxzsxxdz : 无
         * sfzmc : A
         * csrq : 1514736000000
         * dabh : 5100000 
         * sfzmhm : 11111
         * zsxzqy : 无
         * jzqx : 2019-01-01 00:00:00
         * zhqfrq : null
         * ljjf : 12
         * zhmfqfrq : null
         * xyqfrq : null
         * xytjrq : 1527782400000
         * djzsxxdz : 无
         * zxbh : 999999999
         */

        private String xm;
        private String lxdh;
        private String jszzt;
        private String cclzrq;
        private String sfcf;
        private String yqwsy;
        private String yqwhz;
        private String fzjg;
        private String zjcx;
        private String lxzsxxdz;
        private String sfzmc;
        private String csrq;
        private String dabh;
        private String sfzmhm;
        private String zsxzqy;
        private String xzqh;
        private String jzqx;
        private String zhqfrq;
        private String ljjf;
        private String zhmfqfrq;
        private String xyqfrq;
        private String xytjrq;
        private String djzsxxdz;
        private String zxbh;

        public String getXzqh() {
            return xzqh;
        }

        public void setXzqh(String xzqh) {
            this.xzqh = xzqh;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getJszzt() {
            return jszzt;
        }

        public void setJszzt(String jszzt) {
            this.jszzt = jszzt;
        }

        public String getCclzrq() {
            return cclzrq;
        }

        public void setCclzrq(String cclzrq) {
            this.cclzrq = cclzrq;
        }

        public String getSfcf() {
            return sfcf;
        }

        public void setSfcf(String sfcf) {
            this.sfcf = sfcf;
        }

        public String getYqwsy() {
            return yqwsy;
        }

        public void setYqwsy(String yqwsy) {
            this.yqwsy = yqwsy;
        }

        public String getYqwhz() {
            return yqwhz;
        }

        public void setYqwhz(String yqwhz) {
            this.yqwhz = yqwhz;
        }

        public String getFzjg() {
            return fzjg;
        }

        public void setFzjg(String fzjg) {
            this.fzjg = fzjg;
        }

        public String getZjcx() {
            return zjcx;
        }

        public void setZjcx(String zjcx) {
            this.zjcx = zjcx;
        }

        public String getLxzsxxdz() {
            return lxzsxxdz;
        }

        public void setLxzsxxdz(String lxzsxxdz) {
            this.lxzsxxdz = lxzsxxdz;
        }

        public String getSfzmc() {
            return sfzmc;
        }

        public void setSfzmc(String sfzmc) {
            this.sfzmc = sfzmc;
        }

        public String getCsrq() {
            return csrq;
        }

        public void setCsrq(String csrq) {
            this.csrq = csrq;
        }

        public String getDabh() {
            return dabh;
        }

        public void setDabh(String dabh) {
            this.dabh = dabh;
        }

        public String getSfzmhm() {
            return sfzmhm;
        }

        public void setSfzmhm(String sfzmhm) {
            this.sfzmhm = sfzmhm;
        }

        public String getZsxzqy() {
            return zsxzqy;
        }

        public void setZsxzqy(String zsxzqy) {
            this.zsxzqy = zsxzqy;
        }

        public String getJzqx() {
            return jzqx;
        }

        public void setJzqx(String jzqx) {
            this.jzqx = jzqx;
        }

        public String getZhqfrq() {
            return zhqfrq;
        }

        public void setZhqfrq(String zhqfrq) {
            this.zhqfrq = zhqfrq;
        }

        public String getLjjf() {
            return ljjf;
        }

        public void setLjjf(String ljjf) {
            this.ljjf = ljjf;
        }

        public String getZhmfqfrq() {
            return zhmfqfrq;
        }

        public void setZhmfqfrq(String zhmfqfrq) {
            this.zhmfqfrq = zhmfqfrq;
        }

        public String getXyqfrq() {
            return xyqfrq;
        }

        public void setXyqfrq(String xyqfrq) {
            this.xyqfrq = xyqfrq;
        }

        public String getXytjrq() {
            return xytjrq;
        }

        public void setXytjrq(String xytjrq) {
            this.xytjrq = xytjrq;
        }

        public String getDjzsxxdz() {
            return djzsxxdz;
        }

        public void setDjzsxxdz(String djzsxxdz) {
            this.djzsxxdz = djzsxxdz;
        }

        public String getZxbh() {
            return zxbh;
        }

        public void setZxbh(String zxbh) {
            this.zxbh = zxbh;
        }
    }
}
