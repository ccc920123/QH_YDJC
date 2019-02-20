package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.QueryCarRepBean;
import com.scxd.lawqinghai.bean.response.CarLawListBean;
import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.CarBasicModel;
import com.scxd.lawqinghai.mvp.model.CarBasicModelImpl;
import com.scxd.lawqinghai.mvp.model.CarLawListModel;
import com.scxd.lawqinghai.mvp.model.CarLawListModelImpl;
import com.scxd.lawqinghai.mvp.view.CarBasicView;
import com.scxd.lawqinghai.mvp.view.CarLawListView;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:   Query Car Basic Information
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarLawListPresenter extends BasePresenter<CarLawListView> {

    private int nextpage = 1;
    private boolean isFirst = true;
    
    CarLawListModel mBasicModel = new CarLawListModelImpl();

    public void loadCarLawList(Context context, String hpzl, String hphm, int page){
        if (mView != null && isFirst) {
            mView.showLoadProgressDialog("正在加载...");
            isFirst = false;
        }
        if (page == 1){
            nextpage = page;
            page = 0;
        }

        mBasicModel.loadCarDatas(context, hpzl, hphm, nextpage, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showCarDatas((List<CarLawListBean>) bean);

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
