package com.scxd.lawqinghai.bean.request;

/**
 * 描述：台账-查询车辆信息bean
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/19
 * 修改人：
 * 修改时间：
 */


public class LedgerCarBean {

    private String hpzl;
    private String hphm;

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public LedgerCarBean(String hpzl, String hphm) {
        this.hpzl = hpzl;
        this.hphm = hphm;
    }
}
