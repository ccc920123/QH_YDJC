package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.response.LawListResp;
import com.scxd.lawqinghai.bean.response.QueryCodeRspBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.CodeLawListModel;
import com.scxd.lawqinghai.mvp.model.CodeLawListModelImpl;
import com.scxd.lawqinghai.mvp.model.LawListModel;
import com.scxd.lawqinghai.mvp.model.LawListModelImpl;
import com.scxd.lawqinghai.mvp.view.CodeLawListView;
import com.scxd.lawqinghai.mvp.view.LawListView;

/**
 * @类名: ${type_name}
 * @功能描述:   Query Law Information
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LawListPresenter extends BasePresenter<LawListView> {

    private int nextpage = 1;
    private boolean isFirst = true;

    LawListModel mBasicModel = new LawListModelImpl();

    public void loadLawList(Context context, String name, int page){
        if (mView != null && isFirst) {
            mView.showLoadProgressDialog("正在加载...");
            isFirst = false;
        }
        if (page == 1){
            nextpage = page;
            page = 0;
        }

        mBasicModel.loadLawDatas(context, name, nextpage, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showCodeDatas((LawListResp.DataBean) bean);

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
