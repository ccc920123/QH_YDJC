package com.scxd.beans.biz;

import java.util.List;

/**
 * 违法代码查询返回的集合实体类，外加一个信息总条数
 */
public class Q23ReturnExtend {
    private int xxts;
    private List<Q23Return> list;

    public int getXxts() {
        return xxts;
    }

    public void setXxts(int xxts) {
        this.xxts = xxts;
    }

    public List<Q23Return> getList() {
        return list;
    }

    public void setList(List<Q23Return> list) {
        this.list = list;
    }
}
