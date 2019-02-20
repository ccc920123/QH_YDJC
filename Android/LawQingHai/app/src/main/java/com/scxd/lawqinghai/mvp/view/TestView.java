package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.TestBean;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface TestView extends BaseView {
    
    void showView(List<TestBean.DataBean> test);
}
