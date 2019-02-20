package com.scxd.beans.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;
import com.scxd.common.CustomDesSerializer;

import java.util.Date;

public class SysUserSafeauditstrategylog {
    private String id;

    private String safetype;

    private String safechildtype;

    private String userid;

    private Date ctime;

    private String cip;

    private String safecontent;

    private String checkdigit;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip == null ? null : cip.trim();
    }

    @JsonSerialize(using = CustomDesSerializer.class)
    public String getSafecontent() {
        return safecontent;
    }

    public void setSafecontent(String safecontent) {
        this.safecontent = safecontent == null ? null : safecontent.trim();
    }

    public String getCheckdigit() {
        return checkdigit;
    }

    public void setCheckdigit(String checkdigit) {
        this.checkdigit = checkdigit == null ? null : checkdigit.trim();
    }
}