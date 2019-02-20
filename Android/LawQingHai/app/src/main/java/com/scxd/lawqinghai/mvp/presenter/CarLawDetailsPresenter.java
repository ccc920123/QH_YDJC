package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.response.CarLawDetailsRspBean;
import com.scxd.lawqinghai.bean.response.QueryCarBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.CarLawDetailsModel;
import com.scxd.lawqinghai.mvp.model.CarLawDetailsModelImpl;
import com.scxd.lawqinghai.mvp.view.CarLawDetailsView;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarLawDetailsPresenter extends BasePresenter<CarLawDetailsView> {
    
    CarLawDetailsModel mModel = new CarLawDetailsModelImpl();

    /**
     * 机动车违法记录查询
     */
    public void loadDatas(Context context, String xh, String cxly){
        
        if (mView != null) {
            mView.showLoadProgressDialog("正在查询...");
        }
        
        mModel.load(context, xh, cxly, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showDatas((CarLawDetailsRspBean.DataBean) bean);

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
