package com.scxd.lawqinghai.bean.request;

/**
 * @类名: ${type_name}
 * @功能描述:   机动车查询条件
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class QueryCarLawListRepBean {


    private String hpzl;

    public QueryCarLawListRepBean(String hpzl, String hphm, int page, int pageSize) {
        this.hpzl = hpzl;
        this.hphm = hphm;
        this.page = page;
        this.pageSize = pageSize;
    }

    private String hphm;
    private int page;
    private int pageSize;

   
    
}
