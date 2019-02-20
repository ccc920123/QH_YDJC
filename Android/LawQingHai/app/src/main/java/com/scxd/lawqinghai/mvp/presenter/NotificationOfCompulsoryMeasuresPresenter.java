package com.scxd.lawqinghai.mvp.presenter;

import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresResBean;
import com.scxd.lawqinghai.bean.response.SummaryPunishmentDecisionResBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.NotificationOfCompulsoryMeasuresModel;
import com.scxd.lawqinghai.mvp.model.NotificationOfCompulsoryMeasuresModelImp;
import com.scxd.lawqinghai.mvp.model.SummaryPunishmentDecisionModel;
import com.scxd.lawqinghai.mvp.model.SummaryPunishmentDecisionModelImp;
import com.scxd.lawqinghai.mvp.view.NotificationOfCompulsoryMeasuresView;
import com.scxd.lawqinghai.mvp.view.SummaryPunishmentDecisionView;

import java.util.List;

/**
 * 描述： 强制处决书，包括时间查询，刷新，加载更多
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/3
 * 修改人：
 * 修改时间：
 */


public class NotificationOfCompulsoryMeasuresPresenter extends BasePresenter<NotificationOfCompulsoryMeasuresView> {

    private int nextpage = 1;
    private boolean isFirst = true;
    NotificationOfCompulsoryMeasuresModelImp model = new NotificationOfCompulsoryMeasuresModel();

    /**
     * 查询强制措施处决车辆列表
     *
     * @param starTime 可以根据时间查询 开始时间
     * @param endTime  结束时间
     * @param page     页数，永远判断是刷新还是下拉，具体页数添加在本界面++
     */
    public void querNotificationOfCompulsoryMeasures(String user, String bmbh, String starTime, String endTime, int page) {
        if (mView != null && isFirst) {
            mView.showLoadProgressDialog("正在获取列表...");
            isFirst = false;
        }
        if (page == 1) {
            nextpage = page;
            page = 0;
        }
        model.backNotificationOfCompulsoryMeasures(user, bmbh, starTime, endTime, nextpage, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null) {
                    mView.backNotificationOfCompulsoryMeasures((NotificationOfCompulsoryMeasuresResBean.DataBean) bean);
                } else {
                    nextpage--;
                    mView.showToast("获取数据失败");
                }
            }

            @Override
            public void failed(String message) {
                nextpage--;
                if ( null!= mView) {
                    mView.disDialog();
                }
                mView.showToast(message);

            }
        });

        nextpage++;


    }


}
