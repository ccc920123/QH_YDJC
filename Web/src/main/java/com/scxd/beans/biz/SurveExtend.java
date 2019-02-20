package com.scxd.beans.biz;

import java.util.List;

/**
 *  平台电子监控信息返回实体类，主要是将代码值转为代码名称
 */
public class SurveExtend extends Q22Return{

    private String id = "";//车辆使用性质名称
    private String syxzname = "";//车辆使用性质名称

    private String csysname = "";//车身颜色名称

    private String clflname = "";//车辆分类名称

    private String cjfsname = "";//采集方式名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSyxzname() {
        return syxzname;
    }

    public void setSyxzname(String syxzname) {
        this.syxzname = syxzname;
    }

    public String getCsysname() {
        return csysname;
    }

    public void setCsysname(String csysname) {
        this.csysname = csysname;
    }

    public String getClflname() {
        return clflname;
    }

    public void setClflname(String clflname) {
        this.clflname = clflname;
    }

    public String getCjfsname() {
        return cjfsname;
    }

    public void setCjfsname(String cjfsname) {
        this.cjfsname = cjfsname;
    }

}
