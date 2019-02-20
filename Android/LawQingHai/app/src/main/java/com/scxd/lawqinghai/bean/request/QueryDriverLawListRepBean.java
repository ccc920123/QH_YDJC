package com.scxd.lawqinghai.bean.request;

/**
 * @类名: ${type_name}
 * @功能描述:   驾驶人查询条件
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class QueryDriverLawListRepBean {


    private String sfzmhm;
    private int page;
    private int pageSize;

    public QueryDriverLawListRepBean(String sfzmhm, int page, int pageSize) {
        this.sfzmhm = sfzmhm;
        this.page = page;
        this.pageSize = pageSize;
    }
}
