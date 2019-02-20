package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.PhotoReqBean;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.bean.response.WarnbackRspBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.WarnBackModel;
import com.scxd.lawqinghai.mvp.model.WarnBackModelImpl;
import com.scxd.lawqinghai.mvp.model.WarnDetailsModel;
import com.scxd.lawqinghai.mvp.model.WarnDetailsModelImpl;
import com.scxd.lawqinghai.mvp.view.WarnBackView;
import com.scxd.lawqinghai.mvp.view.WarnDetailsView;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:   预警详情内容
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnBackPresenter extends BasePresenter<WarnBackView> {


    WarnBackModel mModel = new WarnBackModelImpl();

    public void load(Context context, String yjxh, String sjbmbh){
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }

        mModel.loadDatas(context, yjxh, sjbmbh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showDatas((WarnbackRspBean.DataBean) bean);

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

    public void commitDatas(Context context, WarnBackReqBean object){

        if (mView != null) {
            mView.showLoadProgressDialog("正在提交...");
        }

        mModel.commit(context, object, new MVPCallBack() {
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

    public void commitPhotos(Context context, String url, String user, String yjxh, String zpzl, List<String> object){
//        if (mView != null) {
//            mView.showLoadProgressDialog("正在提交照片...");
//        }

        mModel.commitPhoto(context, url, user, yjxh, zpzl, object, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
//                mView.disDialog();
                if (bean != null){
                    mView.commitPhotos((String) bean);

                } else {
                    mView.showToast("照片上传失败");
                }
            }

            @Override
            public void failed(String message) {
//                mView.disDialog();
                mView.showToast(message);
            }
        });

    }
    
    
}
