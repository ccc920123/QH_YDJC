package com.scxd.beans.pojo;

import java.util.Date;

public class SysUserSafeauditsetBean {
    private String id;

    private String safetype;

    private String safechildtype;

    private String safechildunite;

    private String safechildvalue;

    private String safevalue;

    private String remarks;

    private int state;

    private String userid;

    private Date ctime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSafetype() {
        return safetype;
    }

    public void setSafetype(String safetype) {
        this.safetype = safetype == null ? null : safetype.trim();
    }

    public String getSafechildtype() {
        return safechildtype;
    }

    public void setSafechildtype(String safechildtype) {
        this.safechildtype = safechildtype == null ? null : safechildtype.trim();
    }

    public String getSafechildunite() {
        return safechildunite;
    }

    public void setSafechildunite(String safechildunite) {
        this.safechildunite = safechildunite == null ? null : safechildunite.trim();
    }

    public String getSafechildvalue() {
        return safechildvalue;
    }

    public void setSafechildvalue(String safechildvalue) {
        this.safechildvalue = safechildvalue == null ? null : safechildvalue.trim();
    }

    public String getSafevalue() {
        return safevalue;
    }

    public void setSafevalue(String safevalue) {
        this.safevalue = safevalue == null ? null : safevalue.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}