package com.scxd.lawqinghai.bean.request;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LedgerListRequestBean {
    
    private String user;
    private String ksrq;
    private String jsrq;
    private int page;
    private int pageSize;

    public LedgerListRequestBean(String user, String ksrq, String jsrq,  int page, int pageSize) {
        this.user = user;
        this.ksrq = ksrq;
        this.jsrq = jsrq;
        this.page = page;
        this.pageSize = pageSize;
    }


}
