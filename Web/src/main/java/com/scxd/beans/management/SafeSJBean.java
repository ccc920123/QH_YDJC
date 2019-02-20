package com.scxd.beans.management;

import com.szdt.security.des.DESUtil;

public class SafeSJBean {

    private String id;
    private String safetype;
    private String safechildtype;
    private String userid;
    private String ctime;
    private String cip;
    private String safecontent;
    private String checkdigit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSafetype() {
        return safetype;
    }

    public void setSafetype(String safetype) {
        this.safetype = safetype;
    }

    public String getSafechildtype() {
        return safechildtype;
    }

    public void setSafechildtype(String safechildtype) {
        this.safechildtype = safechildtype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getSafecontent() {
        try {
            DESUtil desUtil = new DESUtil("ics");
            return desUtil.decrypt(safecontent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setSafecontent(String safecontent) {
        this.safecontent = safecontent;
    }

    public String getCheckdigit() {
        return checkdigit;
    }

    public void setCheckdigit(String checkdigit) {
        this.checkdigit = checkdigit;
    }
}
