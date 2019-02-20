package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.ResponseRootVo;
import com.scxd.lawqinghai.bean.request.TestRequestBean;
import com.scxd.lawqinghai.bean.response.TestBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.TestModel;
import com.scxd.lawqinghai.mvp.model.TestModelImpl;
import com.scxd.lawqinghai.mvp.view.TestView;
import com.scxd.lawqinghai.utils.ToastUtils;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class TestPresenter extends BasePresenter<TestView> {

    TestModel mTestModel = new TestModelImpl();

    public void getDetailsDatas(final Context context, String id){
        if (mView != null) {
            mView.showLoadProgressDialog("正在提交数据...");
        }
        mTestModel.loadDatas(context, id, new MVPCallBack() {
            @Override
            public void succeed(Object be) {
                mView.disDialog();
                if (be != null){
                    TestBean bean = (TestBean) be; 
                    if (null != mView) {
                        mView.showView(bean.getData());
                    } else {
                        ToastUtils.showToast(context, "未查询到数据");
                    }
                } else {
                    mView.disDialog();
                    ToastUtils.showToast(context, "获取数据失败");
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
//jhfghfh