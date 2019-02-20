package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.response.LedgerCarReqBean;
import com.scxd.lawqinghai.bean.response.LedgerDetailsRespBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.LedgerModel;
import com.scxd.lawqinghai.mvp.model.LedgerModelImpl;
import com.scxd.lawqinghai.mvp.view.LedgerView;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述: 台账录入
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LedgerPersenter extends BasePresenter<LedgerView> {

    LedgerModel mModel = new LedgerModelImpl();

    /**
     * 车辆查询
     */
    public void loadCarDatas(Context context, String hpzl, String hphm) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }
        mModel.queryCar(context, hpzl, hphm, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null) {
                    mView.queryCar((LedgerQueyCarReqBean.DataBean) bean);

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

    /**
     * 驾驶人查询
     */
    public void loadDriverDatas(Context context, String sfzh) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }
        mModel.queryDriver(context, sfzh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null) {
                    mView.queryDriver(bean);

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

    /**
     * 数据查询
     */
    public void commitDatas(Context context, Object object) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在提交...");
        }
        mModel.loadCommit(context, object, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null) {
                    mView.loadCommit((String) bean);

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

    public void commitPhotos(Context context, String url, String user, String yjxh, String zpzl, List<String> object) {
//        if (mView != null) {
//            mView.showLoadProgressDialog("正在提交照片...");
//        }

        mModel.commitPhoto(context, url, user, yjxh, zpzl, object, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
//                mView.disDialog();
                if (bean != null) {
                    mView.commitPhotos((String) bean);

                } else {
                    mView.showToast("照片上传失败");
                }
            }

            @Override
            public void failed(String message) {
//                mView.disDialog();
                mView.showToast(message);
            }
        });

    }


    /**
     * 详情查询
     */
    public void queryDatas(Context context, String tzlsh) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }
        mModel.queryDetails(context, tzlsh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null) {
                    mView.queryDatas((LedgerDetailsRespBean.DataBean) bean);

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
