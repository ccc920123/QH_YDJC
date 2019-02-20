package com.scxd.beans.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;

public class SYS_BLACK_LIST {
    private String id;

    private String value;

    private int type;

    private Date xrsj;
    private String ms;

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getXrsj() {
        return xrsj;
    }

    public void setXrsj(Date xrsj) {
        this.xrsj = xrsj;
    }
}