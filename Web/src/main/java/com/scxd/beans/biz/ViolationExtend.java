package com.scxd.beans.biz;

import com.scxd.beans.pojo.BizVioViolation;

/**
 *  平台返回简易处罚决定书实体类
 */
public class ViolationExtend extends BizVioViolation {

    private String syxzname = "";//车辆使用性质名称

    private String clflname = "";//车辆分类名称

    private String cfzlname = "";//处罚种类名称

    private String jkfsname = "";//交款方式名称

    private String jkbjname = "";//交款标记名称

    private String jsjqbjname = "";//拒收拒签标记

    public String getSyxzname() {
        return syxzname;
    }

    public void setSyxzname(String syxzname) {
        this.syxzname = syxzname;
    }

    public String getClflname() {
        return clflname;
    }

    public void setClflname(String clflname) {
        this.clflname = clflname;
    }

    public String getCfzlname() {
        return cfzlname;
    }

    public void setCfzlname(String cfzlname) {
        this.cfzlname = cfzlname;
    }

    public String getJkfsname() {
        return jkfsname;
    }

    public void setJkfsname(String jkfsname) {
        this.jkfsname = jkfsname;
    }

    public String getJkbjname() {
        return jkbjname;
    }

    public void setJkbjname(String jkbjname) {
        this.jkbjname = jkbjname;
    }

    public String getJsjqbjname() {
        return jsjqbjname;
    }

    public void setJsjqbjname(String jsjqbjname) {
        this.jsjqbjname = jsjqbjname;
    }
}
