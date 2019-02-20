package com.scxd.beans.pojo;

import com.szdt.security.des.DESUtil;

public class SCY_LOG_C {

    private String rn = "";
    private String id = "";

    private String value = "";

    private String event = "";

    private String ly = "";

    private String type = "";

    private String module = "";

    private String sfhxgn = "";

    private String sffcgyw = "";

    private String jg = "";

    private String ms = "";

    private String xrsj;
    private String jyw = "";
    private String loginname = "";
    private String name = "";
    private String ssbmbh = "";

    public DESUtil initDESUtil() {

        DESUtil desUtil = null;
        try {
            desUtil = new DESUtil("ics");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desUtil;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsbmbh() {
        return ssbmbh;
    }

    public void setSsbmbh(String ssbmbh) {
        this.ssbmbh = ssbmbh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getEvent() {

        String s = null;
        try {
            s = initDESUtil().decrypt(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (s.equals("3")) {
            return "移除黑名单";

        } else if (s.equals("2")) {
            return "退出";
        } else {

            return "登录";
        }


    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModule() {
        String s = null;
        try {
            s = initDESUtil().decrypt(module);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (s.equals("1")) {
            return "登录";
        } else {
            return "黑名单";
        }


    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSfhxgn() {
        return sfhxgn;
    }

    public void setSfhxgn(String sfhxgn) {
        this.sfhxgn = sfhxgn;
    }

    public String getSffcgyw() {
        return sffcgyw;
    }

    public void setSffcgyw(String sffcgyw) {
        this.sffcgyw = sffcgyw;
    }

    public String getJg() {
        String s = null;
        try {
            s = initDESUtil().decrypt(jg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (s.equals("1")) {
            return "成功";
        } else {
            return "失败";

        }

    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getMs() {
        String s = null;
        try {
            s = initDESUtil().decrypt(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void setMs(String ms) {
        this.ms = ms == null ? null : ms.trim();
    }

    public String getXrsj() {
        return xrsj;
    }

    public void setXrsj(String xrsj) {
        this.xrsj = xrsj;
    }

    public String getJyw() {
        return jyw;
    }

    public void setJyw(String jyw) {
        this.jyw = jyw;
    }

    @Override
    public String toString() {
        String str = "";
        try {
            DESUtil desUtil = new DESUtil("ics");
            str = id + value + desUtil.decrypt(event) + desUtil.decrypt(ly) + desUtil.decrypt(type) + desUtil.decrypt(module) +
                    desUtil.decrypt(sfhxgn) + desUtil.decrypt(sffcgyw) + desUtil.decrypt(jg) + desUtil.decrypt(ms) + xrsj;

        } catch (Exception e) {

        }
        return str;
    }
}