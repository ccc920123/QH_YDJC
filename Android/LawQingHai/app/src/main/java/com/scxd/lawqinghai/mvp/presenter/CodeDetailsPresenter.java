package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.CodeDetailsModel;
import com.scxd.lawqinghai.mvp.model.CodeDetailsModelImpl;
import com.scxd.lawqinghai.mvp.model.CodeLawListModel;
import com.scxd.lawqinghai.mvp.model.CodeLawListModelImpl;
import com.scxd.lawqinghai.mvp.view.CodeDetailsView;
import com.scxd.lawqinghai.mvp.view.CodeLawListView;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:   Query Code Information
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CodeDetailsPresenter extends BasePresenter<CodeDetailsView> {


    CodeDetailsModel mBasicModel = new CodeDetailsModelImpl();

    public void loadCodeLaw(Context context, String wfdm){
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }

        mBasicModel.loadCodeDatas(context, wfdm, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showCodeDatas((QueryCodeListBean) bean);

                } else {
                    mView.showToast("获取数据失败");
                }
            }

            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);
            }
        });
    } 
    
    
}
