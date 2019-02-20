package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.CarPrintBean;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.request.WSBHReqBean;
import com.scxd.lawqinghai.bean.response.CarLawDetailsRspBean;
import com.scxd.lawqinghai.bean.response.PrintImageResBean;
import com.scxd.lawqinghai.bean.response.SummaryPunishmentDetailResBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.CarLawDetailsModel;
import com.scxd.lawqinghai.mvp.model.CarLawDetailsModelImpl;
import com.scxd.lawqinghai.mvp.model.CarLawPrintModel;
import com.scxd.lawqinghai.mvp.model.CarLawPrintModelImpl;
import com.scxd.lawqinghai.mvp.view.CarLawDetailsView;
import com.scxd.lawqinghai.mvp.view.CarLawPrintView;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarLawPrintPresenter extends BasePresenter<CarLawPrintView> {
    
    CarLawPrintModel mModel = new CarLawPrintModelImpl();
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
                    mView.backQueryCar((LedgerQueyCarReqBean.DataBean) bean);

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
                    mView.backQueryDriver(bean);

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
     * 查询文书编号
     *
     * @param bean
     */
    public void queryWsbh(WSBHReqBean bean) {


        if (mView != null) {
            mView.showLoadProgressDialog("正在查询文书编号...");
        }
        mModel.queryWsbh(bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                ;
                mView.disDialog();
                if (bean != null) {
                    mView.getWsbh(((String)bean));//得到文书编号
                }
            }

            @Override
            public void failed(String message) {
                mView.disDialog();

            }
        });


    }

    /**
     * 查询详情
     */
    public void LoadDetail(String jdsbh) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在提交...");
        }
        mModel.queryDetail(jdsbh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null) {
                    mView.backQueryDetail((SummaryPunishmentDetailResBean) bean);

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
     * 提交数据
     *
     * @param context
     */
    public void comitData(Context context, CarPrintBean bean){
        
        if (mView != null) {
            mView.showLoadProgressDialog("正在提交...");
        }
        
        mModel.comitData(context, bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null) {
                    mView.showDatas((PrintImageResBean) bean);

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
