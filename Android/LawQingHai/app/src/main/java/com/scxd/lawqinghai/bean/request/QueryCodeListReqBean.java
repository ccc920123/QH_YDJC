package com.scxd.lawqinghai.bean.request;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class QueryCodeListReqBean {
    
    private String wfdm;
    private String wfnr;
    private int page;
    private int pageSize;


    public QueryCodeListReqBean(String wfdm, String wfnr, int page, int pageSize) {
        this.wfdm = wfdm;
        this.wfnr = wfnr;
        this.page = page;
        this.pageSize = pageSize;
    }


}
