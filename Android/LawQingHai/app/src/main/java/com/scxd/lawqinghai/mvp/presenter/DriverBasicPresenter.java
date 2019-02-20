package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.QueryDriverRepBean;
import com.scxd.lawqinghai.bean.response.QueryDriverBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.DriverBasicModel;
import com.scxd.lawqinghai.mvp.model.DriverBasicModelImpl;
import com.scxd.lawqinghai.mvp.view.DriverBasicView;

/**
 * @类名: ${type_name}
 * @功能描述:   Query Driver Basic Information
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class DriverBasicPresenter extends BasePresenter<DriverBasicView> {
    
    DriverBasicModel mBasicModel = new DriverBasicModelImpl();

    public void loadDriverBasic(Context context, QueryDriverRepBean bean){
        if (mView != null) {
            mView.showLoadProgressDialog("正在查询...");
        }

        mBasicModel.loadDriverDatas(context, bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showDriverDatas((QueryDriverBean.DataBean) bean);

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
