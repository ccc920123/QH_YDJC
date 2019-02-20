package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.MainModel;
import com.scxd.lawqinghai.mvp.model.MainModelImpl;
import com.scxd.lawqinghai.mvp.model.WelcomeModelImpl;
import com.scxd.lawqinghai.mvp.view.MainView;
import com.scxd.lawqinghai.mvp.view.WelcomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class MainPresenter extends BasePresenter<MainView> {
    MainModel mWelcomModel = new MainModelImpl();

    public void getServiceVersion(final Context mContext, String bmbh) {

        mWelcomModel.getSerViceVerDion(mContext, bmbh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {

                mView.upDataDownload(bean);

            }

            @Override
            public void failed(String message) {
//                mView.showToast(message);

            }
        });


    }


}
//jhfghfh