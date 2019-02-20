package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.bean.response.DLRespBean;
import com.scxd.lawqinghai.bean.response.LDRespBean;
import com.scxd.lawqinghai.bean.response.RSPVersionBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.LoginModel;
import com.scxd.lawqinghai.mvp.model.LoginModelImpl;
import com.scxd.lawqinghai.mvp.model.SearchDLModel;
import com.scxd.lawqinghai.mvp.model.SearchDLModelImp;
import com.scxd.lawqinghai.mvp.view.LoginView;
import com.scxd.lawqinghai.mvp.view.SearchDLView;
import com.scxd.lawqinghai.network.DataCallback;
import com.scxd.lawqinghai.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class SearchDLPresenter extends BasePresenter<SearchDLView> {

    SearchDLModelImp mModel = new SearchDLModel();


    /**
     * 请求道路
     *
     * @param bean
     */
    public void searchDL(SearchDLReqBean bean) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在查询道路...");
        }
        mModel.searchDL(bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                ;
                mView.disDialog();
                if (bean != null) {
                    mView.backDL(((List<DLRespBean>) bean));
                } else {
                    mView.showToast("获取道路失败");
                }
            }

            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);
            }
        });

    }

    /**
     * 请求路段
     *
     * @param bean
     */
    public void searchLD(SearchLDReqBean bean, final DLRespBean dl) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在查询路段...");
        }
        mModel.searchLD(bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                ;
                mView.disDialog();
                if ( null!= bean) {
                    mView.backLD(((List<LDRespBean>) bean),dl);
                } else {
                    List<LDRespBean> ld=new ArrayList<>();
                    LDRespBean ldBean=new LDRespBean();
                    ldBean.setLddm("0000");
                    ldBean.setLdmc("国道/高速");
                    ld.add(ldBean);

                    mView.backLD(ld,dl);
//                    mView.showToast("获取数据失败");
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
