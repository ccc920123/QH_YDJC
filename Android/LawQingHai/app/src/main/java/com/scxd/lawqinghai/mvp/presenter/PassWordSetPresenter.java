package com.scxd.lawqinghai.mvp.presenter;

import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.PassWordSetModel;
import com.scxd.lawqinghai.mvp.model.PassWordSetModelImpl;
import com.scxd.lawqinghai.mvp.view.PassWordSetView;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/14
 * 修改人：
 * 修改时间：
 */


public class PassWordSetPresenter extends BasePresenter<PassWordSetView> {

    PassWordSetModel model = new PassWordSetModelImpl();

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param newPwd
     * @param user
     */
    public void setPassWord(String oldPwd, String newPwd, String user, String bmbh) {


        if (mView != null) {
            mView.showLoadProgressDialog("请稍等...");
        }
        model.setPassWordSet(oldPwd, newPwd, user, bmbh,  new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                mView.changeSuccess("修改成功，请重新登录");

            }

            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);


            }
        });
    }

}
