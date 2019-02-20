package com.scxd.beans.biz;

import com.scxd.beans.pojo.BizVioForce;

import java.util.List;

/**
 *  强制措施平台返回实体类
 */
public class ForceExtend extends BizVioForce {

    private String syxzname = "";//车辆使用性质名称

    private String clflname = "";//车辆分类名称

    private List<WfdmExtend> wf;//违法

    private String qzcsname ;//强制措施名称

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

    public List<WfdmExtend> getWf() {
        return wf;
    }

    public void setWf(List<WfdmExtend> wf) {
        this.wf = wf;
    }

    public String getQzcsname() {
        return qzcsname;
    }

    public void setQzcsname(String qzcsname) {
        this.qzcsname = qzcsname;
    }


}
