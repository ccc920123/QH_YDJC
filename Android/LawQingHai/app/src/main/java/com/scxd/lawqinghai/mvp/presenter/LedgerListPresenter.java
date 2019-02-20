package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.response.LedgerListRespBean;
import com.scxd.lawqinghai.bean.response.WarnListBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.LedgerListModel;
import com.scxd.lawqinghai.mvp.model.LedgerListModelImpl;
import com.scxd.lawqinghai.mvp.model.WarnListModel;
import com.scxd.lawqinghai.mvp.model.WarnListModelImpl;
import com.scxd.lawqinghai.mvp.view.LedgerListView;
import com.scxd.lawqinghai.mvp.view.WarnListView;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:   预警清单列表请求
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LedgerListPresenter extends BasePresenter<LedgerListView> {

    private int nextpage = 1;
    private boolean isFirst = true;

    LedgerListModel model = new LedgerListModelImpl();
    
    public void load(Context context, String user, String ksrq, String jsrq, int page){
        if (mView != null && isFirst) {
            mView.showLoadProgressDialog("正在加载...");
            isFirst = false;
        }
        if (page == 1){
            nextpage = page;
            page = 0;
        }
        model.loadDatas(context, user, ksrq, jsrq, nextpage, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showDatas((LedgerListRespBean.DataBean) bean);

                } else {
                    nextpage --;
                    mView.showToast("获取数据失败");
                }
            }

            @Override
            public void failed(String message) {
                nextpage --;
                mView.disDialog();
                mView.showToast(message);
            }
        });
        nextpage ++;
    }
    
}
