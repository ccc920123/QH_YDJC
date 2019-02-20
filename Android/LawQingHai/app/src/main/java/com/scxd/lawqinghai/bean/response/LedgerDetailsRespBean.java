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
public class LedgerDetailsRespBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"id":"adc28d89-ef28-4156-a91a-292edb731200","ssbmbh":null,"hphm":"青A111C2","jszh":null,"sfd":"成都",
     * "mdd":"西宁","fwzbh":"633101000010040001","sfyfjsy":null,"gpswd":null,"gpsjd":null,"hdzks":null,"zks":22,
     * "sfjbc":"1","bxpc":"7","sftbjqx":"1","jcycnr":null,"jcqkms":"通缉","sfzjbf":null,"clzt":4,"jccllx":"01",
     * "sjzzl":null,"wfyy":"1","sfwzjs":null,"qdystxz":null,"jcjg":"20","jcmj":"莫国良","jcsj":1535100316000,
     * "zjszh":"511303199201230919","zlxdh":null,"sfxycl":null,"cljg":null,"jcclzlx":"0101","gps":null,"aqsb":0,
     * "cllthw":0,"jaqd":1,"pljs":1,"ffgz":null,"ztfgbs":null,"azfhzz":null,"xgjsbz":null,"azdsj":null,"azdlx":null,
     * "zjszjbf":null,"fjszh":null,"flxdh":null,"fjssfcf":null,"fjssfyqwsy":null,"fjssfyqwhz":null,"fjszjbf":null,
     * "dgzc":null,"zdzc":null,"zxzc":null,"srzk":"成都","sczk":"西宁","yxqy":"四川","yyxm":null,"yydh":null,"yysfz":null,
     * "whpzl":null,"whpmc":null,"wsbh":null,"jyw":null,"wslb":null,"cfxylx":null,"cfxyzlx":null,"yjbm":null,
     * "lxr":null,"lxdh":null,"czqkms":null,"zjssfyqwhz":null,"zjssfyqwsy":null,"chjg":null,"sfwfwcl":null,
     * "sfyqwbf":null,"sfyqwjy":null,"zjssfcf":null,"hpzl":"02","user":null,
     * "photos":["http://11.101.2.16:9995/Web/getPhotos?id=06a0e207-7fe3-46d5-8169-38da331ed758"]}
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
         * id : adc28d89-ef28-4156-a91a-292edb731200
         * ssbmbh : null
         * hphm : 青A111C2
         * jszh : null
         * sfd : 成都
         * mdd : 西宁
         * fwzbh : 633101000010040001
         * sfyfjsy : null
         * gpswd : null
         * gpsjd : null
         * hdzks : null
         * zks : 22
         * sfjbc : 1
         * bxpc : 7
         * sftbjqx : 1
         * jcycnr : null
         * jcqkms : 通缉
         * sfzjbf : null
         * clzt : 4
         * jccllx : 01
         * sjzzl : null
         * wfyy : 1
         * sfwzjs : null
         * qdystxz : null
         * jcjg : 20
         * jcmj : 莫国良
         * jcsj : 1535100316000
         * zjszh : 511303199201230919
         * zlxdh : null
         * sfxycl : null
         * cljg : null
         * jcclzlx : 0101
         * gps : null
         * aqsb : 0
         * cllthw : 0
         * jaqd : 1
         * pljs : 1
         * ffgz : null
         * ztfgbs : null
         * azfhzz : null
         * xgjsbz : null
         * azdsj : null
         * azdlx : null
         * zjszjbf : null
         * fjszh : null
         * flxdh : null
         * fjssfcf : null
         * fjssfyqwsy : null
         * fjssfyqwhz : null
         * fjszjbf : null
         * dgzc : null
         * zdzc : null
         * zxzc : null
         * srzk : 成都
         * sczk : 西宁
         * yxqy : 四川
         * yyxm : null
         * yydh : null
         * yysfz : null
         * whpzl : null
         * whpmc : null
         * wsbh : null
         * jyw : null
         * wslb : null
         * cfxylx : null
         * cfxyzlx : null
         * yjbm : null
         * lxr : null
         * lxdh : null
         * czqkms : null
         * zjssfyqwhz : null
         * zjssfyqwsy : null
         * chjg : null
         * sfwfwcl : null
         * sfyqwbf : null
         * sfyqwjy : null
         * zjssfcf : null
         * hpzl : 02
         * user : null
         * photos : ["http://11.101.2.16:9995/Web/getPhotos?id=06a0e207-7fe3-46d5-8169-38da331ed758"]
         */

        private String id;
        private String ssbmbh;
        private String hphm;
        private String jszh;
        private String sfd;
        private String mdd;
        private String fwzbh;
        private String sfyfjsy;
        private String gpswd;
        private String gpsjd;
        private String hdzks;
        private String zks;
        private String sfjbc;
        private String bxpc;
        private String sftbjqx;
        private String jcycnr;
        private String jcqkms;
        private String sfzjbf;
        private String clzt;
        private String jccllx;
        private String sjzzl;
        private String wfyy;
        private String sfwzjs;
        private String qdystxz;
        private String jcjg;
        private String jcmj;
        private long jcsj;
        private String zjszh;
        private String zlxdh;
        private String sfxycl;
        private String cljg;
        private String jcclzlx;
        private String gps;
        private String aqsb;
        private String cllthw;
        private String jaqd;
        private String pljs;
        private String ffgz;
        private String ztfgbs;
        private String azfhzz;
        private String xgjsbz;
        private String azdsj;
        private String azdlx;
        private String zjszjbf;
        private String fjszh;
        private String flxdh;
        private String fjssfcf;
        private String fjssfyqwsy;
        private String fjssfyqwhz;
        private String fjszjbf;
        private String dgzc;
        private String zdzc;
        private String zxzc;
        private String srzk;
        private String sczk;
        private String yxqy;
        private String yyxm;
        private String yydh;
        private String yysfz;
        private String whpzl;
        private String whpmc;
        private String wsbh;
        private String jyw;
        private String wslb;
        private String cfxylx;
        private String cfxyzlx;
        private String yjbm;
        private String lxr;
        private String lxdh;
        private String czqkms;
        private String zjssfyqwhz;
        private String zjssfyqwsy;
        private String chjg;
        private String sfwfwcl;
        private String sfyqwbf;
        private String sfyqwjy;
        private String zjssfcf;
        private String hpzl;
        private String user;
        private List<String> photos;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSsbmbh() {
            return ssbmbh;
        }

        public void setSsbmbh(String ssbmbh) {
            this.ssbmbh = ssbmbh;
        }

        public String getHphm() {
            return hphm;
        }

        public void setHphm(String hphm) {
            this.hphm = hphm;
        }

        public String getJszh() {
            return jszh;
        }

        public void setJszh(String jszh) {
            this.jszh = jszh;
        }

        public String getSfd() {
            return sfd;
        }

        public void setSfd(String sfd) {
            this.sfd = sfd;
        }

        public String getMdd() {
            return mdd;
        }

        public void setMdd(String mdd) {
            this.mdd = mdd;
        }

        public String getFwzbh() {
            return fwzbh;
        }

        public void setFwzbh(String fwzbh) {
            this.fwzbh = fwzbh;
        }

        public String getSfyfjsy() {
            return sfyfjsy;
        }

        public void setSfyfjsy(String sfyfjsy) {
            this.sfyfjsy = sfyfjsy;
        }

        public String getGpswd() {
            return gpswd;
        }

        public void setGpswd(String gpswd) {
            this.gpswd = gpswd;
        }

        public String getGpsjd() {
            return gpsjd;
        }

        public void setGpsjd(String gpsjd) {
            this.gpsjd = gpsjd;
        }

        public String getHdzks() {
            return hdzks;
        }

        public void setHdzks(String hdzks) {
            this.hdzks = hdzks;
        }

        public String getZks() {
            return zks;
        }

        public void setZks(String zks) {
            this.zks = zks;
        }

        public String getSfjbc() {
            return sfjbc;
        }

        public void setSfjbc(String sfjbc) {
            this.sfjbc = sfjbc;
        }

        public String getBxpc() {
            return bxpc;
        }

        public void setBxpc(String bxpc) {
            this.bxpc = bxpc;
        }

        public String getSftbjqx() {
            return sftbjqx;
        }

        public void setSftbjqx(String sftbjqx) {
            this.sftbjqx = sftbjqx;
        }

        public String getJcycnr() {
            return jcycnr;
        }

        public void setJcycnr(String jcycnr) {
            this.jcycnr = jcycnr;
        }

        public String getJcqkms() {
            return jcqkms;
        }

        public void setJcqkms(String jcqkms) {
            this.jcqkms = jcqkms;
        }

        public String getSfzjbf() {
            return sfzjbf;
        }

        public void setSfzjbf(String sfzjbf) {
            this.sfzjbf = sfzjbf;
        }

        public String getClzt() {
            return clzt;
        }

        public void setClzt(String clzt) {
            this.clzt = clzt;
        }

        public String getJccllx() {
            return jccllx;
        }

        public void setJccllx(String jccllx) {
            this.jccllx = jccllx;
        }

        public String getSjzzl() {
            return sjzzl;
        }

        public void setSjzzl(String sjzzl) {
            this.sjzzl = sjzzl;
        }

        public String getWfyy() {
            return wfyy;
        }

        public void setWfyy(String wfyy) {
            this.wfyy = wfyy;
        }

        public String getSfwzjs() {
            return sfwzjs;
        }

        public void setSfwzjs(String sfwzjs) {
            this.sfwzjs = sfwzjs;
        }

        public String getQdystxz() {
            return qdystxz;
        }

        public void setQdystxz(String qdystxz) {
            this.qdystxz = qdystxz;
        }

        public String getJcjg() {
            return jcjg;
        }

        public void setJcjg(String jcjg) {
            this.jcjg = jcjg;
        }

        public String getJcmj() {
            return jcmj;
        }

        public void setJcmj(String jcmj) {
            this.jcmj = jcmj;
        }

        public long getJcsj() {
            return jcsj;
        }

        public void setJcsj(long jcsj) {
            this.jcsj = jcsj;
        }

        public String getZjszh() {
            return zjszh;
        }

        public void setZjszh(String zjszh) {
            this.zjszh = zjszh;
        }

        public String getZlxdh() {
            return zlxdh;
        }

        public void setZlxdh(String zlxdh) {
            this.zlxdh = zlxdh;
        }

        public String getSfxycl() {
            return sfxycl;
        }

        public void setSfxycl(String sfxycl) {
            this.sfxycl = sfxycl;
        }

        public String getCljg() {
            return cljg;
        }

        public void setCljg(String cljg) {
            this.cljg = cljg;
        }

        public String getJcclzlx() {
            return jcclzlx;
        }

        public void setJcclzlx(String jcclzlx) {
            this.jcclzlx = jcclzlx;
        }

        public String getGps() {
            return gps;
        }

        public void setGps(String gps) {
            this.gps = gps;
        }

        public String getAqsb() {
            return aqsb;
        }

        public void setAqsb(String aqsb) {
            this.aqsb = aqsb;
        }

        public String getCllthw() {
            return cllthw;
        }

        public void setCllthw(String cllthw) {
            this.cllthw = cllthw;
        }

        public String getJaqd() {
            return jaqd;
        }

        public void setJaqd(String jaqd) {
            this.jaqd = jaqd;
        }

        public String getPljs() {
            return pljs;
        }

        public void setPljs(String pljs) {
            this.pljs = pljs;
        }

        public String getFfgz() {
            return ffgz;
        }

        public void setFfgz(String ffgz) {
            this.ffgz = ffgz;
        }

        public String getZtfgbs() {
            return ztfgbs;
        }

        public void setZtfgbs(String ztfgbs) {
            this.ztfgbs = ztfgbs;
        }

        public String getAzfhzz() {
            return azfhzz;
        }

        public void setAzfhzz(String azfhzz) {
            this.azfhzz = azfhzz;
        }

        public String getXgjsbz() {
            return xgjsbz;
        }

        public void setXgjsbz(String xgjsbz) {
            this.xgjsbz = xgjsbz;
        }

        public String getAzdsj() {
            return azdsj;
        }

        public void setAzdsj(String azdsj) {
            this.azdsj = azdsj;
        }

        public String getAzdlx() {
            return azdlx;
        }

        public void setAzdlx(String azdlx) {
            this.azdlx = azdlx;
        }

        public String getZjszjbf() {
            return zjszjbf;
        }

        public void setZjszjbf(String zjszjbf) {
            this.zjszjbf = zjszjbf;
        }

        public String getFjszh() {
            return fjszh;
        }

        public void setFjszh(String fjszh) {
            this.fjszh = fjszh;
        }

        public String getFlxdh() {
            return flxdh;
        }

        public void setFlxdh(String flxdh) {
            this.flxdh = flxdh;
        }

        public String getFjssfcf() {
            return fjssfcf;
        }

        public void setFjssfcf(String fjssfcf) {
            this.fjssfcf = fjssfcf;
        }

        public String getFjssfyqwsy() {
            return fjssfyqwsy;
        }

        public void setFjssfyqwsy(String fjssfyqwsy) {
            this.fjssfyqwsy = fjssfyqwsy;
        }

        public String getFjssfyqwhz() {
            return fjssfyqwhz;
        }

        public void setFjssfyqwhz(String fjssfyqwhz) {
            this.fjssfyqwhz = fjssfyqwhz;
        }

        public String getFjszjbf() {
            return fjszjbf;
        }

        public void setFjszjbf(String fjszjbf) {
            this.fjszjbf = fjszjbf;
        }

        public String getDgzc() {
            return dgzc;
        }

        public void setDgzc(String dgzc) {
            this.dgzc = dgzc;
        }

        public String getZdzc() {
            return zdzc;
        }

        public void setZdzc(String zdzc) {
            this.zdzc = zdzc;
        }

        public String getZxzc() {
            return zxzc;
        }

        public void setZxzc(String zxzc) {
            this.zxzc = zxzc;
        }

        public String getSrzk() {
            return srzk;
        }

        public void setSrzk(String srzk) {
            this.srzk = srzk;
        }

        public String getSczk() {
            return sczk;
        }

        public void setSczk(String sczk) {
            this.sczk = sczk;
        }

        public String getYxqy() {
            return yxqy;
        }

        public void setYxqy(String yxqy) {
            this.yxqy = yxqy;
        }

        public String getYyxm() {
            return yyxm;
        }

        public void setYyxm(String yyxm) {
            this.yyxm = yyxm;
        }

        public String getYydh() {
            return yydh;
        }

        public void setYydh(String yydh) {
            this.yydh = yydh;
        }

        public String getYysfz() {
            return yysfz;
        }

        public void setYysfz(String yysfz) {
            this.yysfz = yysfz;
        }

        public String getWhpzl() {
            return whpzl;
        }

        public void setWhpzl(String whpzl) {
            this.whpzl = whpzl;
        }

        public String getWhpmc() {
            return whpmc;
        }

        public void setWhpmc(String whpmc) {
            this.whpmc = whpmc;
        }

        public String getWsbh() {
            return wsbh;
        }

        public void setWsbh(String wsbh) {
            this.wsbh = wsbh;
        }

        public String getJyw() {
            return jyw;
        }

        public void setJyw(String jyw) {
            this.jyw = jyw;
        }

        public String getWslb() {
            return wslb;
        }

        public void setWslb(String wslb) {
            this.wslb = wslb;
        }

        public String getCfxylx() {
            return cfxylx;
        }

        public void setCfxylx(String cfxylx) {
            this.cfxylx = cfxylx;
        }

        public String getCfxyzlx() {
            return cfxyzlx;
        }

        public void setCfxyzlx(String cfxyzlx) {
            this.cfxyzlx = cfxyzlx;
        }

        public String getYjbm() {
            return yjbm;
        }

        public void setYjbm(String yjbm) {
            this.yjbm = yjbm;
        }

        public String getLxr() {
            return lxr;
        }

        public void setLxr(String lxr) {
            this.lxr = lxr;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getCzqkms() {
            return czqkms;
        }

        public void setCzqkms(String czqkms) {
            this.czqkms = czqkms;
        }

        public String getZjssfyqwhz() {
            return zjssfyqwhz;
        }

        public void setZjssfyqwhz(String zjssfyqwhz) {
            this.zjssfyqwhz = zjssfyqwhz;
        }

        public String getZjssfyqwsy() {
            return zjssfyqwsy;
        }

        public void setZjssfyqwsy(String zjssfyqwsy) {
            this.zjssfyqwsy = zjssfyqwsy;
        }

        public String getChjg() {
            return chjg;
        }

        public void setChjg(String chjg) {
            this.chjg = chjg;
        }

        public String getSfwfwcl() {
            return sfwfwcl;
        }

        public void setSfwfwcl(String sfwfwcl) {
            this.sfwfwcl = sfwfwcl;
        }

        public String getSfyqwbf() {
            return sfyqwbf;
        }

        public void setSfyqwbf(String sfyqwbf) {
            this.sfyqwbf = sfyqwbf;
        }

        public String getSfyqwjy() {
            return sfyqwjy;
        }

        public void setSfyqwjy(String sfyqwjy) {
            this.sfyqwjy = sfyqwjy;
        }

        public String getZjssfcf() {
            return zjssfcf;
        }

        public void setZjssfcf(String zjssfcf) {
            this.zjssfcf = zjssfcf;
        }

        public String getHpzl() {
            return hpzl;
        }

        public void setHpzl(String hpzl) {
            this.hpzl = hpzl;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<String> getPhotos() {
            return photos;
        }

        public void setPhotos(List<String> photos) {
            this.photos = photos;
        }
    }
}
