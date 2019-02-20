package com.scxd.lawqinghai.mvp.presenter;

import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresDetailResBean;
import com.scxd.lawqinghai.bean.response.PrintImageResBean;
import com.scxd.lawqinghai.bean.response.WarnbackRspBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.PrintImageModel;
import com.scxd.lawqinghai.mvp.model.PrintImageModelImpl;
import com.scxd.lawqinghai.mvp.view.PrintImageView;

/**
 * 描述：请求打印数据
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class PrintImagePersenter extends BasePresenter<PrintImageView> {
    PrintImageModelImpl model = new PrintImageModel();

    public void queryPrintImage(String xh) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在查询数据...");
        }
        model.queryPrintImage(xh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {


                mView.disDialog();
                if (bean != null) {
                    mView.queryPrintImageUrl("");//没有打印照片的url，修改节点了

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

    public void callBackPrintImage(String xh, String type) {


        model.callBackPrintImage(xh,type, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {

                mView.backPrintImageSucced();
            }

            @Override
            public void failed(String message) {
                //失败。
            }
        });


    }


    /**
     * 查询详情
     */
    public void LoadDetail(String jdsbh, final String jkid) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在查询...");
        }
        try {


        model.queryDetail(jdsbh,jkid, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null) {
                    mView.backQueryDetail(bean,jkid);

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
        }catch (Exception e)
        {
            mView.showToast("获取数据失败");

        }
    }

}
