package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.response.LawDetailsRsp;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.CodeDetailsModel;
import com.scxd.lawqinghai.mvp.model.CodeDetailsModelImpl;
import com.scxd.lawqinghai.mvp.model.LawDetailsModel;
import com.scxd.lawqinghai.mvp.model.LawDetailsModelImpl;
import com.scxd.lawqinghai.mvp.view.CodeDetailsView;
import com.scxd.lawqinghai.mvp.view.LawDetailsView;

/**
 * @类名: ${type_name}
 * @功能描述:   Query Code Information
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LawDetailsPresenter extends BasePresenter<LawDetailsView> {


    LawDetailsModel mBasicModel = new LawDetailsModelImpl();

    public void loadLawLaw(Context context, String id){
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }

        mBasicModel.loadCodeDatas(context, id, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showCodeDatas((LawDetailsRsp.DataBean) bean);

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
