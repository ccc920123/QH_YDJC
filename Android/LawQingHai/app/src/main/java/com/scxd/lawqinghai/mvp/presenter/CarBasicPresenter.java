package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.QueryCarRepBean;
import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.CarBasicModel;
import com.scxd.lawqinghai.mvp.model.CarBasicModelImpl;
import com.scxd.lawqinghai.mvp.view.CarBasicView;

/**
 * @类名: ${type_name}
 * @功能描述:   Query Car Basic Information
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarBasicPresenter extends BasePresenter<CarBasicView> {
    
    CarBasicModel mBasicModel = new CarBasicModelImpl();

    public void loadCarBasic(Context context, QueryCarRepBean bean){
        if (mView != null) {
            mView.showLoadProgressDialog("正在查询...");
        }

        mBasicModel.loadCarDatas(context, bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showCarDatas((QueryCarBean.DataBean) bean);

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
