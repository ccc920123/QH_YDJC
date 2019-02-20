package com.scxd.lawqinghai.bean.request;

/**
 * 描述：写入台账数据
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/19
 * 修改人：
 * 修改时间：
 */


public class WriteLegerBean {


    private String hphm="";
    //
    private String hpzl="";

    private String fwzbh="";
    private String clzt="";
    private String jccllx="";
    //
    private String jcclzlx="";
    private String sfd="";
    private String mdd="";
    private String zks="";
    private String sjzzl="";
    //
    private String gps="";
    //安全设备情况
    private String aqsb="";
    //车辆轮胎花纹情况
    private String cllthw="";
    private String wfyy="";
    //驾驶人是否系安全带
    private String jaqd="";
    //是否疲劳驾驶
    private String pljs="";
    //是否非法改装
    private String ffgz="";
    //是否安装侧后防护装置
    private String azfhzz="";
    //是否按规定粘贴反光标识
    private String ztfgbs="";
    //是否悬挂或喷涂警示标志
    private String xgjsbz="";
    //是否按指定时间行驶
    private String azdsj="";
    //是否按指定时间行驶
    private String azdlx="";
    private String sfwzjs="";
    private String sfyfjsy="";
    private String qdystxz="";
    //是否逾期未检验
    private String sfyqwjy="";
    //是否逾期未报废
    private String sfyqwbf="";
    //是否违法未处理
    private String sfwfwcl="";
    private String jcjg="";
    private String jcqkms="";
    private String jcsj="";
    private String zjszh="";
    private String zlxdh="";
    //主驾驶是否超分
    private String zjssfcf="";
    //主驾驶是否逾期未审验
    private String zjssfyqwsy="";
    //主驾驶是否逾期未换证
    private String zjssfyqwhz="";
    //主驾驶准驾不符
    private String zjszjbf="";
    //副驾驶证号
    private String fjszh="";
    //副驾驶联系电话
    private String flxdh="";
    //副驾驶是否超分
    private String fjssfcf="";
    //副驾驶是否逾期未审验
    private String fjssfyqwsy="";
    //副驾驶是否逾期未换证
    private String fjssfyqwhz="";
    //副驾驶准驾不符
    private String fjszjbf="";
    //灯光是否正常
    private String dgzc="";
    //制动是否正常
    private String zdzc="";
    //转向是否正常
    private String zxzc="";
    private String sfjbc="";
    private String bxpc="";
    private String sftbjqx="";
    //驶入站口
    private String srzk="";
    //驶出站口
    private String sczk="";
    //运行区域
    private String yxqy="";
    //押运员姓名
    private String yyxm="";
    //押运员电话
    private String yydh="";
    private String yysfz="";
    //whpzl危化品种类
    private String whpzl="";
    //危化品名称
    private String whpmc="";
    //是否嫌疑车辆
    private String chjg="";
    private String cljg="";
    //法律文书编号
    private String wsbh="";
    //文书校验位
    private String jyw="";
    //文书类别
    private String wslb="";
    //处罚嫌疑类型
    private String cfxylx="";
    //处罚嫌疑子类型
    private String cfxyzlx="";
    //移交部门
    private String yjbm="";
    //联系人
    private String lxr="";
    //联系电话
    private String lxdh="";
    //处置情况描述
    private String czqkms="";
    /**
     * 所属部门编号
     */
    private String ssbmbh="";



    private String jcmj="";
    /**
     *  登录账号
     */
    private String user="";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getJcmj() {
        return jcmj;
    }

    public void setJcmj(String jcmj) {
        this.jcmj = jcmj;
    }


/*

    private String jszh;
    private String sfzjbf;
    private String gpswd;
    private String gpsjd;
    private String hdzks;
    private String jcycnr;

    private String sfxycl;
*/

    public String getYysfz() {
        return yysfz;
    }

    public void setYysfz(String yysfz) {
        this.yysfz = yysfz;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getJccllx() {
        return jccllx;
    }

    public void setJccllx(String jccllx) {
        this.jccllx = jccllx;
    }

    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
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


    public String getJcqkms() {
        return jcqkms;
    }

    public void setJcqkms(String jcqkms) {
        this.jcqkms = jcqkms;
    }

    public String getWfyy() {
        return wfyy;
    }

    public void setWfyy(String wfyy) {
        this.wfyy = wfyy;
    }

    public String getSjzzl() {
        return sjzzl;
    }

    public void setSjzzl(String sjzzl) {
        this.sjzzl = sjzzl;
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

    public String getJcsj() {
        return jcsj;
    }

    public void setJcsj(String jcsj) {
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

    public String getCljg() {
        return cljg;
    }

    public void setCljg(String cljg) {
        this.cljg = cljg;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
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

    public String getSfyqwjy() {
        return sfyqwjy;
    }

    public void setSfyqwjy(String sfyqwjy) {
        this.sfyqwjy = sfyqwjy;
    }

    public String getSfyqwbf() {
        return sfyqwbf;
    }

    public void setSfyqwbf(String sfyqwbf) {
        this.sfyqwbf = sfyqwbf;
    }

    public String getSfwfwcl() {
        return sfwfwcl;
    }

    public void setSfwfwcl(String sfwfwcl) {
        this.sfwfwcl = sfwfwcl;
    }

    public String getZjssfcf() {
        return zjssfcf;
    }

    public void setZjssfcf(String zjssfcf) {
        this.zjssfcf = zjssfcf;
    }

    public String getZjssfyqwsy() {
        return zjssfyqwsy;
    }

    public void setZjssfyqwsy(String zjssfyqwsy) {
        this.zjssfyqwsy = zjssfyqwsy;
    }

    public String getZjssfyqwhz() {
        return zjssfyqwhz;
    }

    public void setZjssfyqwhz(String zjssfyqwhz) {
        this.zjssfyqwhz = zjssfyqwhz;
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

    public String getChjg() {
        return chjg;
    }

    public void setChjg(String chjg) {
        this.chjg = chjg;
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

    public String getAzfhzz() {
        return azfhzz;
    }

    public void setAzfhzz(String azfhzz) {
        this.azfhzz = azfhzz;
    }

    public String getSsbmbh() {
        return ssbmbh;
    }

    public void setSsbmbh(String ssbmbh) {
        this.ssbmbh = ssbmbh;
    }
}
