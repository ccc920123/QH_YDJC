package com.scxd.lawqinghai.mvp.presenter;

import com.scxd.lawqinghai.bean.request.WriteDispatchBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.DispatchModel;
import com.scxd.lawqinghai.mvp.model.DispatchModelImpl;
import com.scxd.lawqinghai.mvp.view.DispatchView;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public class DispatchPresenter extends BasePresenter<DispatchView> {

    DispatchModel model = new DispatchModelImpl();

    public void getDispatchsbkk(String user,String sjbmbh,String ssbmbh) {

        if (null != mView) {
            mView.showLoadProgressDialog("正在获取布控数据");

        }
        model.getDispatchsbkk(user,sjbmbh,ssbmbh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {

                mView.getSuccessbkk(bean);
            }

            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);
            }
        });

    }

    public void getDispatchyjll(String user) {

        model.getDispatchyjlx(user, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {

                mView.getSuccesyjlx(bean);
            }

            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);

            }
        });

    }

    public void getDispatchcjry(String user) {

        model.getDispatchcjry(user, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                mView.getSuccescjry(bean);
            }

            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);
            }
        });

    }

    public void writeDispatch(WriteDispatchBean bean) {
        if (null != mView) {
            mView.showLoadProgressDialog("正在设置布控，请稍等...");

        }
        model.getWirteDispatch(bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {

                mView.disDialog();
                mView.writeSucces();

            }

            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);
            }
        });

    }


}
