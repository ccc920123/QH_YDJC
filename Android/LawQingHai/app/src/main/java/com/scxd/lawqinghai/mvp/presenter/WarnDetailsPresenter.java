package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.bean.response.WarnDetailsRspBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.WarnDetailsModel;
import com.scxd.lawqinghai.mvp.model.WarnDetailsModelImpl;
import com.scxd.lawqinghai.mvp.view.WarnDetailsView;

/**
 * @类名: ${type_name}
 * @功能描述:   预警详情内容
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnDetailsPresenter extends BasePresenter<WarnDetailsView> {


    WarnDetailsModel mModel = new WarnDetailsModelImpl();

    public void load(Context context, String yjxh){
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }

        mModel.loadDatas(context, yjxh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showDatas((WarnDetailsRspBean.DataBean) bean);

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
    
    public void loadCar(Context context, String hpzl, String hphm){
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }

        mModel.loadCarDatas(context, hpzl, hphm, new MVPCallBack() {
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
    
    public void commitDatas(Context context, Object object){
        
        if (mView != null) {
            mView.showLoadProgressDialog("正在提交...");
        }
        
        mModel.committ(context, object, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.commitDatas((String) bean);

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
