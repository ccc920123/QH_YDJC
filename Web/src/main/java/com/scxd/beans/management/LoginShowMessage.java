package com.scxd.beans.management;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scxd.beans.pojo.SysOpLogBean;
import com.scxd.beans.pojo.SysOpLogBean;
import com.scxd.common.CustomDateSerializer;

import java.util.Date;
import java.util.List;

/**
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 17:12 2018/9/28
 * @Modified By:
 * 登录后显示的提示信息bean类
 */
public class LoginShowMessage {
    private  int online;
    private Date scdlcgsj;
    private Date dqdlsj;
    private  String dqdlip;
    private  String scdlcgip;
    private  Date yhyxq;
    private  Date mmyxq;
    private List<SysOpLogBean> czrz;

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getScdlcgsj() {
        return scdlcgsj;
    }

    public void setScdlcgsj(Date scdlcgsj) {
        this.scdlcgsj = scdlcgsj;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDqdlsj() {
        return dqdlsj;
    }

    public void setDqdlsj(Date dqdlsj) {
        this.dqdlsj = dqdlsj;
    }

    public String getDqdlip() {
        return dqdlip;
    }

    public void setDqdlip(String dqdlip) {
        this.dqdlip = dqdlip;
    }

    public String getScdlcgip() {
        return scdlcgip;
    }

    public void setScdlcgip(String scdlcgip) {
        this.scdlcgip = scdlcgip;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getYhyxq() {
        return yhyxq;
    }

    public void setYhyxq(Date yhyxq) {
        this.yhyxq = yhyxq;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getMmyxq() {
        return mmyxq;
    }

    public void setMmyxq(Date mmyxq) {
        this.mmyxq = mmyxq;
    }

    public List<SysOpLogBean> getCzrz() {
        return czrz;
    }

    public void setCzrz(List<SysOpLogBean> czrz) {
        this.czrz = czrz;
    }
}
