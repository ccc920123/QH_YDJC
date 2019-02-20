package com.scxd.lawqinghai.bean.response;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class QueryDriverBean {


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

        private String xm="";
        private String lxdh="";
        private String jszzt="";
        private String cclzrq="";
        private String sfcf="";
        private String yqwsy="";
        private String yqwhz="";
        private String fzjg="";
        private String zjcx="";
        private String lxzsxxdz="";
        private String sfzjbf="";
        private String sfzmc="";
        private String csrq="";
        private String dabh="";
        private String sfzmhm="";
        private String sfzmmc="";
        private String zsxzqy="";
        private String jzqx="";
        private String zhqfrq="";
        private String ljjf="";
        private String zhmfqfrq="";
        private String xyqfrq="";
        private String xytjrq="";
        private String zxbh="";
        private String djzsxxdz="";
        private String yxqs="";
        private String yxqz="";
        private String xb="";

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public String getYxqs() {
            return yxqs;
        }

        public void setYxqs(String yxqs) {
            this.yxqs = yxqs;
        }

        public String getYxqz() {
            return yxqz;
        }

        public void setYxqz(String yxqz) {
            this.yxqz = yxqz;
        }

        public String getSfzmmc() {
            return sfzmmc;
        }

        public void setSfzmmc(String sfzmmc) {
            this.sfzmmc = sfzmmc;
        }

        public String getZpid() {
            return zpid;
        }

        public void setZpid(String zpid) {
            this.zpid = zpid;
        }

        private String zpid;

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

        public String getSfzjbf() {
            return sfzjbf;
        }

        public void setSfzjbf(String sfzjbf) {
            this.sfzjbf = sfzjbf;
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

        public String getZxbh() {
            return zxbh;
        }

        public void setZxbh(String zxbh) {
            this.zxbh = zxbh;
        }

        public String getDjzsxxdz() {
            return djzsxxdz;
        }

        public void setDjzsxxdz(String djzsxxdz) {
            this.djzsxxdz = djzsxxdz;
        }
    }
}                        
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       