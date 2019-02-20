package com.scxd.lawqinghai.bean.response;

import java.io.Serializable;

/**
 * 描述：道路代码
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/7
 * 修改人：
 * 修改时间：
 */


public class DLRespBean implements Serializable {


    private String dldm;
    private String dlmc;
    private String xzqh;


    public String getDldm() {
        return dldm;
    }

    public void setDldm(String dldm) {
        this.dldm = dldm;
    }

    public String getDlmc() {
        return dlmc;
    }

    public void setDlmc(String dlmc) {
        this.dlmc = dlmc;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }
}
