package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.response.WarnListBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.LoginModel;
import com.scxd.lawqinghai.mvp.model.LoginModelImpl;
import com.scxd.lawqinghai.mvp.model.WarnListModel;
import com.scxd.lawqinghai.mvp.model.WarnListModelImpl;
import com.scxd.lawqinghai.mvp.view.WarnListView;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:   预警清单列表请求
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class WarnListPresenter extends BasePresenter<WarnListView> {

    private int nextpage = 1;
    private boolean isFirst = true;
    
    WarnListModel model = new WarnListModelImpl();
    
    public void load(Context context, String user, String ksrq, String jsrq, String zt, int page){
        if (mView != null && isFirst) {
            mView.showLoadProgressDialog("正在加载...");
            isFirst = false;
        }
        if (page == 1){
            nextpage = page;
            page = 0;
        }
        model.loadDatas(context, user, ksrq, jsrq, zt, nextpage, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showDatas((WarnListBean.DataBean) bean);

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
