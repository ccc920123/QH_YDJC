package com.scxd.beans.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;

public class SysOperateLog {
    private String id;

    private String opuser;

    private int optype;

    private Date optime;

    private String opvalues;

    private String opdevice;

    private int opsource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpuser() {
        return opuser;
    }

    public void setOpuser(String opuser) {
        this.opuser = opuser == null ? null : opuser.trim();
    }

    public int getOptype() {
        return optype;
    }

    public void setOptype(int optype) {
        this.optype = optype;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String getOpvalues() {
        return opvalues;
    }

    public void setOpvalues(String opvalues) {
        this.opvalues = opvalues == null ? null : opvalues.trim();
    }

    public String getOpdevice() {
        return opdevice;
    }

    public void setOpdevice(String opdevice) {
        this.opdevice = opdevice == null ? null : opdevice.trim();
    }

    public int getOpsource() {
        return opsource;
    }

    public void setOpsource(int opsource) {
        this.opsource = opsource;
    }
}