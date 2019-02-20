package com.scxd.lawqinghai.mvp.view;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public interface PrintImageView extends BaseView {

    void backPrintImageSucced();

    void queryPrintImageUrl(String url);

    void backQueryDetail(Object obj, String jkid);
}
