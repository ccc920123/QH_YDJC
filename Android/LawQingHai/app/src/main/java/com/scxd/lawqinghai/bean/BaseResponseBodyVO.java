package com.scxd.lawqinghai.bean;


import com.scxd.lawqinghai.bean.response.TestBean;

import java.io.Serializable;
import java.util.List;

/**
 * 类名: BaseResponseBodyVO
 * <br/>功能描述: f返回数据的Body节点数据（一般为类）
 * <br/>作者: 陈渝金
 * <br/>时间: 2017/5/22
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class BaseResponseBodyVO implements Serializable {

    private List<TestBean> testBean;

    public List<TestBean> getTestBean() {
        return testBean;
    }

    public void setTestBean(List<TestBean> testBean) {
        testBean = testBean;
    }
}
