package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.response.DriverLawListBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.DriverLawListModel;
import com.scxd.lawqinghai.mvp.model.DriverLawListModelImpl;
import com.scxd.lawqinghai.mvp.view.DriverLawListView;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:   Query Driver Law Information
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class DriverLawListPresenter extends BasePresenter<DriverLawListView> {

    private int nextpage = 1;
    private boolean isFirst = true;
    
    DriverLawListModel mBasicModel = new DriverLawListModelImpl();

    public void loadDriverLawList(Context context, String xh, int page){
        if (mView != null && isFirst) {
            mView.showLoadProgressDialog("正在加载...");
            isFirst = false;
        }
        if (page == 1){
            nextpage = page;
            page = 0;
        }

        mBasicModel.loadDriverDatas(context, xh, nextpage, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showDriverDatas((List<DriverLawListBean>) bean);

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
