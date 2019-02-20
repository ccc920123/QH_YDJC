package com.scxd.beans.pojo;

import com.szdt.security.des.DESUtil;

import java.util.Date;

public class SCY_LOG {
    private String id;

    private String value;

    private String event;

    private String ly;

    private String type;

    private String module;

    private String sfhxgn;

    private String sffcgyw;

    private String jg;

    private String ms;

    private Date xrsj;
    private  String jyw;

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
        return event;
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
        return module;
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
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms == null ? null : ms.trim();
    }

    public Date getXrsj() {
        return xrsj;
    }

    public void setXrsj(Date xrsj) {
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
        String str="";
        try {
            DESUtil desUtil=new DESUtil("ics");
            str=id+value  + desUtil.decrypt(event) + desUtil.decrypt(ly) +  desUtil.decrypt(type) + desUtil.decrypt(module) +
                    desUtil.decrypt(sfhxgn) + desUtil.decrypt(sffcgyw) +desUtil.decrypt(jg) +  desUtil.decrypt(ms) + xrsj;

        }catch (Exception e)
        {

        }
        return str;
    }
}