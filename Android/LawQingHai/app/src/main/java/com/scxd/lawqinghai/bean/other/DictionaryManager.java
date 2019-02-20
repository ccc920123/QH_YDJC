package com.scxd.lawqinghai.bean.other;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典储存基类
 */
public class DictionaryManager {
    private static DictionaryManager mDicManager = null;
    /**
     * 1005 号牌种类
     */
    private List<DicationResBean> hpzl;
    /**
     * 1006 号牌颜色
     */
    private List<DicationResBean> hpys;
    /**
     * 1003 车身颜色
     */
    private List<DicationResBean> csys;
    /**
     * 1002车辆类型
     */
    private List<DicationResBean> cllx;
    /**
     * 10010预警类型
     */
    private List<DicationResBean> yjlx;
    /**
     * 10011车辆状态
     */
    private List<DicationResBean> clzt;
    /**
     * 10012使用性质
     */
    private List<DicationResBean> syxz;
    /**
     * 10013驾驶证状态
     */
    private List<DicationResBean> jszzt;
    /**
     * 1007照片种类
     */
    private List<DicationResBean> zpzl;
    /**
     * 1008处置结果
     */
    private List<DicationResBean> czjg;
    /**
     * 1009非嫌疑车辆原因
     */
    private List<DicationResBean> fxyclyy;
    /**
     * 1014 检查车辆类型
     */
    private List<DicationResBean> jccllx;
    /**
     * 1015 未拦截到原因
     */
    private List<DicationResBean> wljdyy;
    /**
     * 1016 文书类别
     */
    private List<DicationResBean> wslb;
    /**
     * 1017检查车辆子类型
     */
    private List<DicationResBean> jcclzlx;
    /**
     * 1018 GPS装备情况
     */
    private List<DicationResBean> gpszbqk;
    /**
     * 1019 班线频次
     */
    private List<DicationResBean> bxpc;
    /**
     * 1020 危化品种类
     */
    private List<DicationResBean> whpzl;
    /**
     * 1021 车辆用途
     */
    private List<DicationResBean> clyt;
    /**
     * 1022 用途属性
     */
    private List<DicationResBean> ytsx;

    /**
     * 1023 人员分类
     */
    private List<DicationResBean> ryfl;

    /**
     * 1024 车辆分类
     */
    private List<DicationResBean> clfl;

    /**
     * 1025 交通方式
     */
    private List<DicationResBean> jtfs;

    /**
     * 1026 处罚种类
     */
    private List<DicationResBean> cfzl;

    /**
     * 1027 缴款方式
     */
    private List<DicationResBean> jkfs;

    /**
     * 1028 缴款标记
     */
    private List<DicationResBean> jkbj;

    /**
     * 1029 事故等级
     */
    private List<DicationResBean> sgdj;

    /**
     * 1030 强制措施类型
     */
    private List<DicationResBean> qzcslx;

    /**
     * 1031 收缴项目
     */
    private List<DicationResBean> sjxm;

    /**
     * 1032 采集方式
     */
    private List<DicationResBean> cjfs;
    /**
     * 1033  身份证明名称
     */
    private List<DicationResBean> sfzmmc;
    /**
     * 1034 违法状态
     */
    private List<DicationResBean> wfzt;

    /**
     * 1035 操作类型  暂时不要
     */


    /**
     * 1036 拒收拒签
     */
    private List<DicationResBean> jsjq;
    /**
     * 1037 预警子类型
     */
    private List<DicationResBean> yjzlx;




    private Context mContext;

    private DictionaryManager() {
    }

    public void init(Context mContext) {
        this.mContext = mContext;
        String jsonhpzl = SharedPreferencesUtils.getString(mContext, "jsonhpzl", "");
        String jsonhpys = SharedPreferencesUtils.getString(mContext, "jsonhpys", "");
        String jsoncsys = SharedPreferencesUtils.getString(mContext, "jsoncsys", "");
        String jsoncllx = SharedPreferencesUtils.getString(mContext, "jsoncllx", "");
        String jsonyjlx = SharedPreferencesUtils.getString(mContext, "jsonyjlx", "");
        String jsonclzt = SharedPreferencesUtils.getString(mContext, "jsonclzt", "");
        String jsonsyxz = SharedPreferencesUtils.getString(mContext, "jsonsyxz", "");
        String jsonjszzt = SharedPreferencesUtils.getString(mContext, "jsonjszzt", "");
        String jsonzpzl = SharedPreferencesUtils.getString(mContext, "jsonzpzl", "");
        String jsonczjg = SharedPreferencesUtils.getString(mContext, "jsonczjg", "");
        String jsonfxyclyy = SharedPreferencesUtils.getString(mContext, "jsonfxyclyy", "");

        String jsonjccllx = SharedPreferencesUtils.getString(mContext, "jsonjccllx", "");
        String jsonwljdyy = SharedPreferencesUtils.getString(mContext, "jsonwljdyy", "");
        String jsonwslb = SharedPreferencesUtils.getString(mContext, "jsonwslb", "");
        String jsonjcclzlx = SharedPreferencesUtils.getString(mContext, "jsonjcclzlx", "");
        String jsongpszbqk = SharedPreferencesUtils.getString(mContext, "jsongpszbqk", "");
        String jsonbxpc = SharedPreferencesUtils.getString(mContext, "jsonbxpc", "");
        String jsonwhpzl = SharedPreferencesUtils.getString(mContext, "jsonwhpzl", "");

        String jsonclyt = SharedPreferencesUtils.getString(mContext, "jsonclyt", "");
        String jsonytsx = SharedPreferencesUtils.getString(mContext, "jsonytsx", "");
        String jsonryfl = SharedPreferencesUtils.getString(mContext, "jsonryfl", "");
        String jsonclfl = SharedPreferencesUtils.getString(mContext, "jsonclfl", "");
        String jsonjtfs = SharedPreferencesUtils.getString(mContext, "jsonjtfs", "");
        String jsoncfzl = SharedPreferencesUtils.getString(mContext, "jsoncfzl", "");
        String jsonjkfs = SharedPreferencesUtils.getString(mContext, "jsonjkfs", "");
        String jsonjkbj = SharedPreferencesUtils.getString(mContext, "jsonjkbj", "");
        String jsonsgdj = SharedPreferencesUtils.getString(mContext, "jsonsgdj", "");
        String jsonqzcslx = SharedPreferencesUtils.getString(mContext, "jsonqzcslx", "");
        String jsonsjxm = SharedPreferencesUtils.getString(mContext, "jsonsjxm", "");
        String jsoncjfs = SharedPreferencesUtils.getString(mContext, "jsoncjfs", "");
        String jsonsfzmmc = SharedPreferencesUtils.getString(mContext, "jsonsfzmmc", "");
        String jsonwfzt = SharedPreferencesUtils.getString(mContext, "jsonwfzt", "");
        String jsonjsjq = SharedPreferencesUtils.getString(mContext, "jsonjsjq", "");
        String jsonyjzlx = SharedPreferencesUtils.getString(mContext, "jsonyjzlx", "");

        if (!TextUtils.isEmpty(jsonhpzl)) {
            Gson gson = new Gson();
            hpzl = gson.fromJson(jsonhpzl, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonhpys)) {
            Gson gson = new Gson();
            hpys = gson.fromJson(jsonhpys, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsoncsys)) {
            Gson gson = new Gson();
            csys = gson.fromJson(jsoncsys, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsoncllx)) {
            Gson gson = new Gson();
            cllx = gson.fromJson(jsoncllx, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonyjlx)) {
            Gson gson = new Gson();
            yjlx = gson.fromJson(jsonyjlx, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonclzt)) {
            Gson gson = new Gson();
            clzt = gson.fromJson(jsonclzt, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonsyxz)) {
            Gson gson = new Gson();
            syxz = gson.fromJson(jsonsyxz, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonjszzt)) {
            Gson gson = new Gson();
            jszzt = gson.fromJson(jsonjszzt, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonzpzl)) {
            Gson gson = new Gson();
            zpzl = gson.fromJson(jsonzpzl, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonczjg)) {
            Gson gson = new Gson();
            czjg = gson.fromJson(jsonczjg, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonfxyclyy)) {
            Gson gson = new Gson();
            fxyclyy = gson.fromJson(jsonfxyclyy, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }

        if (!TextUtils.isEmpty(jsonjccllx)) {
            Gson gson = new Gson();
            jccllx = gson.fromJson(jsonjccllx, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonwljdyy)) {
            Gson gson = new Gson();
            wljdyy = gson.fromJson(jsonwljdyy, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonwslb)) {
            Gson gson = new Gson();
            wslb = gson.fromJson(jsonwslb, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonjcclzlx)) {
            Gson gson = new Gson();
            jcclzlx = gson.fromJson(jsonjcclzlx, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsongpszbqk)) {
            Gson gson = new Gson();
            gpszbqk = gson.fromJson(jsongpszbqk, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonbxpc)) {
            Gson gson = new Gson();
            bxpc = gson.fromJson(jsonbxpc, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonwhpzl)) {
            Gson gson = new Gson();
            whpzl = gson.fromJson(jsonwhpzl, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonclyt)) {
            Gson gson = new Gson();
            clyt = gson.fromJson(jsonclyt, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonytsx)) {
            Gson gson = new Gson();
            ytsx = gson.fromJson(jsonytsx, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonryfl)) {
            Gson gson = new Gson();
            ryfl = gson.fromJson(jsonryfl, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonclfl)) {
            Gson gson = new Gson();
            clfl = gson.fromJson(jsonclfl, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonjtfs)) {
            Gson gson = new Gson();
            jtfs = gson.fromJson(jsonjtfs, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsoncfzl)) {
            Gson gson = new Gson();
            cfzl = gson.fromJson(jsoncfzl, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonjkfs)) {
            Gson gson = new Gson();
            jkfs = gson.fromJson(jsonjkfs, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonjkbj)) {
            Gson gson = new Gson();
            jkbj = gson.fromJson(jsonjkbj, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonsgdj)) {
            Gson gson = new Gson();
            sgdj = gson.fromJson(jsonsgdj, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonqzcslx)) {
            Gson gson = new Gson();
            qzcslx = gson.fromJson(jsonqzcslx, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonsjxm)) {
            Gson gson = new Gson();
            sjxm = gson.fromJson(jsonsjxm, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsoncjfs)) {
            Gson gson = new Gson();
            cjfs = gson.fromJson(jsoncjfs, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonsfzmmc)) {
            Gson gson = new Gson();
            sfzmmc = gson.fromJson(jsonsfzmmc, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonwfzt)) {
            Gson gson = new Gson();
            wfzt = gson.fromJson(jsonwfzt, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonjsjq)) {
            Gson gson = new Gson();
            jsjq = gson.fromJson(jsonjsjq, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }
        if (!TextUtils.isEmpty(jsonyjzlx)) {
            Gson gson = new Gson();
            yjzlx = gson.fromJson(jsonyjzlx, new TypeToken<List<DicationResBean>>() {
            }.getType());
        }

    }


    public static DictionaryManager getInstance() {
        if (mDicManager == null) {
            synchronized (DictionaryManager.class) {
                mDicManager = new DictionaryManager();
            }
        }
        return mDicManager;
    }


    public List<DicationResBean> getHpzl() {
        return hpzl;
    }

    public void setHpzl(List<DicationResBean> hpzl) {
        this.hpzl = hpzl;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonhpzl", gson.toJson(hpzl));
    }

    public List<DicationResBean> getHpys() {
        return hpys;
    }

    public void setHpys(List<DicationResBean> hpys) {
        this.hpys = hpys;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonhpys", gson.toJson(hpys));
    }

    public List<DicationResBean> getCsys() {
        return csys;
    }

    public void setCsys(List<DicationResBean> csys) {
        this.csys = csys;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsoncsys", gson.toJson(csys));
    }

    public List<DicationResBean> getCllx() {
        return cllx;
    }

    public void setCllx(List<DicationResBean> cllx) {
        this.cllx = cllx;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsoncllx", gson.toJson(cllx));
    }

    public List<DicationResBean> getYjlx() {
        return yjlx;
    }

    public void setYjlx(List<DicationResBean> yjlx) {
        this.yjlx = yjlx;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonyjlx", gson.toJson(yjlx));
    }

    public List<DicationResBean> getClzt() {
        return clzt;
    }

    public void setClzt(List<DicationResBean> clzt) {
        this.clzt = clzt;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonclzt", gson.toJson(clzt));
    }

    public List<DicationResBean> getSyxz() {
        return syxz;
    }

    public void setSyxz(List<DicationResBean> syxz) {
        this.syxz = syxz;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonsyxz", gson.toJson(syxz));
    }

    public List<DicationResBean> getJszzt() {
        return jszzt;
    }

    public void setJszzt(List<DicationResBean> jszzt) {
        this.jszzt = jszzt;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonjszzt", gson.toJson(jszzt));
    }

    public List<DicationResBean> getZpzl() {
        return zpzl;
    }

    public void setZpzl(List<DicationResBean> zpzl) {
        this.zpzl = zpzl;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonzpzl", gson.toJson(zpzl));
    }

    public List<DicationResBean> getCzjg() {
        return czjg;
    }

    public void setCzjg(List<DicationResBean> czjg) {
        this.czjg = czjg;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonczjg", gson.toJson(czjg));
    }

    public List<DicationResBean> getFxyclyy() {
        return fxyclyy;
    }

    public void setFxyclyy(List<DicationResBean> fxyclyy) {
        this.fxyclyy = fxyclyy;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonfxyclyy", gson.toJson(fxyclyy));
    }

    public List<DicationResBean> getJccllx() {
        return jccllx;
    }

    public void setJccllx(List<DicationResBean> jccllx) {
        this.jccllx = jccllx;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonjccllx", gson.toJson(jccllx));

    }

    public List<DicationResBean> getWljdyy() {
        return wljdyy;
    }

    public void setWljdyy(List<DicationResBean> wljdyy) {
        this.wljdyy = wljdyy;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonwljdyy", gson.toJson(wljdyy));

    }

    public List<DicationResBean> getWslb() {
        return wslb;
    }

    public void setWslb(List<DicationResBean> wslb) {
        this.wslb = wslb;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonwslb", gson.toJson(wslb));

    }

    public List<DicationResBean> getJcclzlx() {
        return jcclzlx;
    }

    public void setJcclzlx(List<DicationResBean> jcclzlx) {
        this.jcclzlx = jcclzlx;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonjcclzlx", gson.toJson(jcclzlx));

    }

    public List<DicationResBean> getGpszbqk() {
        return gpszbqk;
    }

    public void setGpszbqk(List<DicationResBean> gpszbqk) {
        this.gpszbqk = gpszbqk;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsongpszbqk", gson.toJson(gpszbqk));

    }

    public List<DicationResBean> getBxpc() {
        return bxpc;
    }

    public void setBxpc(List<DicationResBean> bxpc) {
        this.bxpc = bxpc;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonbxpc", gson.toJson(bxpc));

    }

    public List<DicationResBean> getWhpzl() {
        return whpzl;
    }

    public void setWhpzl(List<DicationResBean> whpzl) {
        this.whpzl = whpzl;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonwhpzl", gson.toJson(whpzl));

    }

    public List<DicationResBean> getClyt() {
        return clyt;
    }

    public void setClyt(List<DicationResBean> clyt) {
        this.clyt = clyt;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonclyt", gson.toJson(clyt));

    }

    public List<DicationResBean> getYtsx() {
        return ytsx;
    }

    public void setYtsx(List<DicationResBean> ytsx) {
        this.ytsx = ytsx;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonytsx", gson.toJson(ytsx));

    }

    public List<DicationResBean> getRyfl() {
        return ryfl;
    }

    public void setRyfl(List<DicationResBean> ryfl) {
        this.ryfl = ryfl;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonryfl", gson.toJson(ryfl));

    }

    public List<DicationResBean> getClfl() {
        return clfl;
    }

    public void setClfl(List<DicationResBean> clfl) {
        this.clfl = clfl;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonclfl", gson.toJson(clfl));

    }

    public List<DicationResBean> getJtfs() {
        return jtfs;
    }

    public void setJtfs(List<DicationResBean> jtfs) {
        this.jtfs = jtfs;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonjtfs", gson.toJson(jtfs));

    }

    public List<DicationResBean> getCfzl() {
        return cfzl;
    }

    public void setCfzl(List<DicationResBean> cfzl) {
        this.cfzl = cfzl;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsoncfzl", gson.toJson(cfzl));

    }

    public List<DicationResBean> getJkfs() {
        return jkfs;
    }

    public void setJkfs(List<DicationResBean> jkfs) {
        this.jkfs = jkfs;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonjkfs", gson.toJson(jkfs));

    }

    public List<DicationResBean> getJkbj() {
        return jkbj;
    }

    public void setJkbj(List<DicationResBean> jkbj) {
        this.jkbj = jkbj;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonjkbj", gson.toJson(jkbj));

    }

    public List<DicationResBean> getSgdj() {
        return sgdj;
    }

    public void setSgdj(List<DicationResBean> sgdj) {
        this.sgdj = sgdj;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonsgdj", gson.toJson(sgdj));

    }

    public List<DicationResBean> getQzcslx() {
        return qzcslx;
    }

    public void setQzcslx(List<DicationResBean> qzcslx) {
        this.qzcslx = qzcslx;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonqzcslx", gson.toJson(qzcslx));

    }

    public List<DicationResBean> getSjxm() {
        return sjxm;
    }

    public void setSjxm(List<DicationResBean> sjxm) {
        this.sjxm = sjxm;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonsjxm", gson.toJson(sjxm));

    }

    public List<DicationResBean> getCjfs() {
        return cjfs;
    }

    public void setCjfs(List<DicationResBean> cjfs) {
        this.cjfs = cjfs;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsoncjfs", gson.toJson(cjfs));

    }
    public List<DicationResBean> getSfzmmc() {
        return sfzmmc;
    }

    public void setSfzmmc(List<DicationResBean> sfzmmc) {
        this.sfzmmc = sfzmmc;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonsfzmmc", gson.toJson(sfzmmc));

    }
    public List<DicationResBean> getWfzt() {
        return wfzt;
    }

    public void setWfzt(List<DicationResBean> wfzt) {
        this.wfzt = wfzt;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonwfzt", gson.toJson(wfzt));

    }
    public List<DicationResBean> getJsjq() {
        return jsjq;
    }

    public void setJsjq(List<DicationResBean> jsjq) {
        this.jsjq = jsjq;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonjsjq", gson.toJson(jsjq));

    }
    public List<DicationResBean> getYjzlx() {
        return yjzlx;
    }

    public void setYjzlx(List<DicationResBean> yjzlx) {
        this.yjzlx = yjzlx;
        Gson gson = new Gson();
        SharedPreferencesUtils.saveString(mContext, "jsonyjzlx", gson.toJson(yjzlx));

    }
}
