package com.scxd.lawqinghai.mvp.view;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public interface DispatchView extends BaseView {

    /**
     * 返回设备卡口
     * @param obj
     */
    void getSuccessbkk(Object obj);

    /**
     * 返回预警类型
     * @param object
     */
    void getSuccesyjlx(Object object);

    /**
     * 返回出警人员
     * @param object
     */
    void getSuccescjry(Object object);

    /**
     * 写入预警信息
     */
    void writeSucces();


}
