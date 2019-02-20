package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.scxd.lawqinghai.activity.NotificationOfCompulsoryMeasuresDetailActivity;
import com.scxd.lawqinghai.bean.request.LedgerQueyCarReqBean;
import com.scxd.lawqinghai.bean.request.NotificationOfCompulsoryMeasuresDetailReqBan;
import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.bean.request.SummaryPunishmentDetailReqBan;
import com.scxd.lawqinghai.bean.request.WSBHReqBean;
import com.scxd.lawqinghai.bean.request.WarnBackReqBean;
import com.scxd.lawqinghai.bean.response.DLRespBean;
import com.scxd.lawqinghai.bean.response.LDRespBean;
import com.scxd.lawqinghai.bean.response.NotificationOfCompulsoryMeasuresDetailResBean;
import com.scxd.lawqinghai.bean.response.PrintImageResBean;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.bean.response.SummaryPunishmentDetailResBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.NotificationOfCompulsoryMeasuresDetailModel;
import com.scxd.lawqinghai.mvp.model.NotificationOfCompulsoryMeasuresDetailModelImp;
import com.scxd.lawqinghai.mvp.model.SummaryPunishmentDetailModel;
import com.scxd.lawqinghai.mvp.model.SummaryPunishmentDetailModelImp;
import com.scxd.lawqinghai.mvp.view.NotificationOfCompulsoryMeasuresDetailView;
import com.scxd.lawqinghai.mvp.view.SummaryPunishmentDetailView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：强制决定书Persenter
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/5
 * 修改人：
 * 修改时间：
 */


public class NotificationOfCompulsoryMeasuresDetailPersenter extends BasePresenter<NotificationOfCompulsoryMeasuresDetailView> {


    NotificationOfCompulsoryMeasuresDetailModelImp mModel=new NotificationOfCompulsoryMeasuresDetailModel();

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
                    mView.backQueryDetail((NotificationOfCompulsoryMeasuresDetailResBean) bean);

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
     * @param bean
     */

    public void comitData(Context context, NotificationOfCompulsoryMeasuresDetailReqBan bean) {

        if (mView != null) {
            mView.showLoadProgressDialog("正在提交...");
        }
        mModel.loadCommit(context, bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null) {
                    mView.backComitData((PrintImageResBean) bean);

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
     * 根据违法代码查询违法内容
     * @param context
     * @param wfdm
     */
    public void loadCodeLaw(Context context, String wfdm){
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }

        mModel.loadCodeDatas(context, wfdm, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showCodeDatas((QueryCodeListBean) bean);

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
     * 根据违法代码查询违法内容
     * @param context
     * @param wfdm
     */
    public void loadItemCodeLaw(Context context, String wfdm, final NotificationOfCompulsoryMeasuresDetailActivity.ViewHolder holder){
        if (mView != null) {
            mView.showLoadProgressDialog("正在加载...");
        }

        mModel.loadCodeDatas(context, wfdm, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.showCodeItemDatas((QueryCodeListBean) bean,holder);

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



    public void commitBackData(Context context, WarnBackReqBean object){

        if (mView != null) {
            mView.showLoadProgressDialog("正在提交...");
        }

        mModel.commitBackDetails(context, object, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.disDialog();
                if (bean != null){
                    mView.commitBackDatas((String) bean);

                } else {
                    mView.showToast("提交反馈信息失败");
                }
            }

            @Override
            public void failed(String message) {
                mView.disDialog();
                mView.showToast(message);
            }
        });
    }

    public void commitPhotos(Context context, String url, String user, String yjxh, String zpzl, List<String> object){
        //        if (mView != null) {
        //            mView.showLoadProgressDialog("正在提交照片...");
        //        }

        mModel.commitBackPhoto(context, url, user, yjxh, zpzl, object, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                //                mView.disDialog();
                if (bean != null){
                    mView.commitBackPhotos((String) bean);

                } else {
                    mView.showToast("反馈照片信息上传失败");
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
     * 请求道路
     *
     * @param bean
     */
    public void searchDL(SearchDLReqBean bean) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在查询道路...");
        }
        mModel.searchDL(bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                ;
                mView.disDialog();
                if (bean != null) {
                    mView.backDL(((List<DLRespBean>) bean));
                } else {
                    mView.showToast("获取道路失败");
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
     * 请求路段
     *
     * @param bean
     */
    public void searchLD(SearchLDReqBean bean) {
        if (mView != null) {
            mView.showLoadProgressDialog("正在查询路段...");
        }
        mModel.searchLD(bean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                ;
                mView.disDialog();
                if ( null!= bean) {
                    mView.backLD(((List<LDRespBean>) bean));
                } else {
                    List<LDRespBean> ld=new ArrayList<>();
                    LDRespBean ldBean=new LDRespBean();
                    ldBean.setLddm("0000");
                    ldBean.setLdmc("国道/高速");
                    ld.add(ldBean);
                    mView.backLD(ld);

//                    mView.showToast("获取数据失败");
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
            mView.showLoadProgressDialog("正在文书编号...");
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

}
