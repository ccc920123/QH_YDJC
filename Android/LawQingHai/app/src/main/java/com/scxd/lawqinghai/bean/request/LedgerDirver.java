package com.scxd.lawqinghai.bean.request;

/**
 * 描述：查询驾驶人信息的请求类
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/19
 * 修改人：
 * 修改时间：
 */


public class LedgerDirver {

    private String jszh;

    public String getJszh() {
        return jszh;
    }

    public void setJszh(String jszh) {
        this.jszh = jszh;
    }

    public LedgerDirver(String jszh) {
        this.jszh = jszh;
    }

    public LedgerDirver() {
    }
}
