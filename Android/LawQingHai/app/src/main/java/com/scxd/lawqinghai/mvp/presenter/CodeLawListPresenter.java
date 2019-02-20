package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.response.CarLawListBean;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.bean.response.QueryCodeRspBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.CarLawListModel;
import com.scxd.lawqinghai.mvp.model.CarLawListModelImpl;
import com.scxd.lawqinghai.mvp.model.CodeLawListModel;
import com.scxd.lawqinghai.mvp.model.CodeLawListModelImpl;
import com.scxd.lawqinghai.mvp.view.CarLawListView;
import com.scxd.lawqinghai.mvp.view.CodeLawListView;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:   Query Code Law Information
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CodeLawListPresenter extends BasePresenter<CodeLawListView> {

    private int nextpage = 1;
    private boolean isFirst = true;

    CodeLawListModel mBasicModel = new CodeLawListModelImpl();

    public void loadCodeLawList(Context context, String wfdm, String wfnr, int page){
        if (mView != null && isFirst) {
            mView.showLoadProgressDialog("正在加载...");
            isFirst = false;
        }
        if (page == 1){
            nextpage = page;
            page = 0;
        }

        mBasicModel.loadLawDatas(context, wfdm, wfnr, nextpage, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showCodeDatas((QueryCodeRspBean.DataBean) bean);

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
