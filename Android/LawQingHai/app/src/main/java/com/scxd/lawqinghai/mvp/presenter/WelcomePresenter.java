package com.scxd.lawqinghai.mvp.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.BaseDicationResponseJson;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.mvp.model.WelcomeModelImpl;
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
public class WelcomePresenter extends BasePresenter<WelcomeView> {
    WelcomeModelImpl mWelcomModel = new WelcomeModelImpl();

    /**
     * <br/> 方法名称: getSystemvesion
     * <br/> 方法详述: 获取版本号
     * <br/> 参数:context
     */
    public void getSystemVersion(Context context) {
        mWelcomModel.getSystemVersion(context, new MVPCallBack<String>() {
            @Override
            public void succeed(String version) {
                if (mView != null) {
                    mView.setVersionNumToView(version);
                }
            }

            @Override
            public void failed(String message) {

            }
        });
    }

    /**
     * <br/> 方法名称: startLocationService
     * <br/> 方法详述: 启动GPS定位服务
     * <br/> 参数:
     * <br/> &nbsp &nbsp &nbsp &nbsp context:上下文
     * <br/> &nbsp &nbsp &nbsp &nbsp mvpCallBack:回调接口
     */
    public void startLocationService(Context context) {
        mWelcomModel.openGPS(context);
        mWelcomModel.startLocationService(context, new MVPCallBack<String>() {
            @Override
            public void succeed(String message) {
                if (mView != null) {
                    mView.startGPSServiceSuccess(message);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    if ("-1".equals(message)) {
                        mView.openSystemGPS();
                    }
                }
            }
        });
    }

    public void getServiceVersion(final Context mContext, String bmbh) {

        if (null != mView) {

            mView.showLoadProgressDialog("正在检查最新版本...");
        }
        mWelcomModel.getSerViceVerDion(mContext, bmbh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {

                mView.upDataDownload(bean);


            }

            @Override
            public void failed(String message) {
                mView.showToast(message);

            }
        });


    }


    public void comitLog(String wym, String loginname, String indata, final String pathFile) {

        mWelcomModel.comitLog(wym, loginname, indata, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                mView.comitLogSueed(pathFile);
            }

            @Override
            public void failed(String message) {
                mView.comitLogError();
            }
        });


    }

    /**
     * 获取字典
     *
     * @param bmbh
     */
    public void getDictiona(String bmbh, final String zdbb) {
        if (null != mView) {

            mView.showLoadProgressDialog("正在检查字典数据...");
        }
        mWelcomModel.getDictiona(bmbh, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                //保存到shared中

                List<DicationResBean> jsonhpzl = new ArrayList<>();
                List<DicationResBean> jsonhpys = new ArrayList<>();
                List<DicationResBean> jsoncsys = new ArrayList<>();
                List<DicationResBean> jsoncllx = new ArrayList<>();
                List<DicationResBean> jsonyjlx = new ArrayList<>();
                List<DicationResBean> jsonyjzlx = new ArrayList<>();
                List<DicationResBean> jsonclzt = new ArrayList<>();
                List<DicationResBean> jsonsyxz = new ArrayList<>();
                List<DicationResBean> jsonjszzt = new ArrayList<>();
                List<DicationResBean> jsonzpzl = new ArrayList<>();
                List<DicationResBean> jsonczjg = new ArrayList<>();
                List<DicationResBean> jsonfxyclyy = new ArrayList<>();

                List<DicationResBean> jsonjccllx = new ArrayList<>();
                List<DicationResBean> jsonwljdyy = new ArrayList<>();
                List<DicationResBean> jsonwslb = new ArrayList<>();
                List<DicationResBean> jsonjcclzlx = new ArrayList<>();
                List<DicationResBean> jsongpszbqk = new ArrayList<>();
                List<DicationResBean> jsonbxpc = new ArrayList<>();
                List<DicationResBean> jsonwhpzl = new ArrayList<>();

                List<DicationResBean> jsonclyt = new ArrayList<>();
                List<DicationResBean> jsonytsx = new ArrayList<>();
                List<DicationResBean> jsonryfl = new ArrayList<>();
                List<DicationResBean> jsonclfl = new ArrayList<>();
                List<DicationResBean> jsonjtfs = new ArrayList<>();
                List<DicationResBean> jsoncfzl = new ArrayList<>();
                List<DicationResBean> jsonjkfs = new ArrayList<>();
                List<DicationResBean> jsonjkbj = new ArrayList<>();
                List<DicationResBean> jsonsgdj = new ArrayList<>();
                List<DicationResBean> jsonqzcslx = new ArrayList<>();
                List<DicationResBean> jsonsjxm = new ArrayList<>();
                List<DicationResBean> jsoncjfs = new ArrayList<>();
                List<DicationResBean> jsonsfzmmc = new ArrayList<>();
                List<DicationResBean> jsonwfzt = new ArrayList<>();
                List<DicationResBean> jsonjsjq = new ArrayList<>();


                List<DicationResBean> dicationList = (List<DicationResBean>) bean;
                for (DicationResBean dicbean : dicationList) {
                    if ("1005".equals(dicbean.getType())) {
                        jsonhpzl.add(dicbean);
                        continue;
                    }
                    if ("1006".equals(dicbean.getType())) {
                        jsonhpys.add(dicbean);
                        continue;
                    }
                    if ("1003".equals(dicbean.getType())) {
                        jsoncsys.add(dicbean);
                        continue;
                    }
                    if ("1002".equals(dicbean.getType())) {
                        jsoncllx.add(dicbean);
                        continue;
                    }
                    if ("1010".equals(dicbean.getType())) {
                        jsonyjlx.add(dicbean);
                        continue;
                    }
                    if ("1011".equals(dicbean.getType())) {
                        jsonclzt.add(dicbean);
                        continue;
                    }
                    if ("1012".equals(dicbean.getType())) {
                        jsonsyxz.add(dicbean);
                        continue;
                    }
                    if ("1013".equals(dicbean.getType())) {
                        jsonjszzt.add(dicbean);
                        continue;
                    }
                    if ("1007".equals(dicbean.getType())) {
                        jsonzpzl.add(dicbean);
                        continue;
                    }
                    if ("1008".equals(dicbean.getType())) {
                        jsonczjg.add(dicbean);
                        continue;
                    }
                    if ("1009".equals(dicbean.getType())) {
                        jsonfxyclyy.add(dicbean);
                        continue;
                    }

                    if ("1014".equals(dicbean.getType())) {
                        jsonjccllx.add(dicbean);
                        continue;
                    }
                    if ("1015".equals(dicbean.getType())) {
                        jsonwljdyy.add(dicbean);
                        continue;
                    }
                    if ("1016".equals(dicbean.getType())) {
                        jsonwslb.add(dicbean);
                        continue;
                    }
                    if ("1017".equals(dicbean.getType())) {
                        jsonjcclzlx.add(dicbean);
                        continue;
                    }
                    if ("1018".equals(dicbean.getType())) {
                        jsongpszbqk.add(dicbean);
                        continue;
                    }
                    if ("1019".equals(dicbean.getType())) {
                        jsonbxpc.add(dicbean);
                        continue;
                    }
                    if ("1020".equals(dicbean.getType())) {
                        jsonwhpzl.add(dicbean);
                        continue;
                    }

                    if ("1021".equals(dicbean.getType())) {
                        jsonclyt.add(dicbean);
                        continue;
                    }
                    if ("1022".equals(dicbean.getType())) {
                        jsonytsx.add(dicbean);
                        continue;
                    }
                    if ("1023".equals(dicbean.getType())) {
                        jsonryfl.add(dicbean);
                        continue;
                    }
                    if ("1024".equals(dicbean.getType())) {
                        jsonclfl.add(dicbean);
                        continue;
                    }
                    if ("1025".equals(dicbean.getType())) {
                        jsonjtfs.add(dicbean);
                        continue;
                    }
                    if ("1026".equals(dicbean.getType())) {
                        jsoncfzl.add(dicbean);
                        continue;
                    }
                    if ("1027".equals(dicbean.getType())) {
                        jsonjkfs.add(dicbean);
                        continue;
                    }
                    if ("1028".equals(dicbean.getType())) {
                        jsonjkbj.add(dicbean);
                        continue;
                    }
                    if ("1029".equals(dicbean.getType())) {
                        jsonsgdj.add(dicbean);
                        continue;
                    }
                    if ("1030".equals(dicbean.getType())) {
                        jsonqzcslx.add(dicbean);
                        continue;
                    }
                    if ("1031".equals(dicbean.getType())) {
                        jsonsjxm.add(dicbean);
                        continue;
                    }
                    if ("1032".equals(dicbean.getType())) {
                        jsoncjfs.add(dicbean);
                        continue;
                    }
                    if ("1033".equals(dicbean.getType())) {
                        jsonsfzmmc.add(dicbean);
                        continue;
                    }
                    if ("1034".equals(dicbean.getType())) {
                        jsonwfzt.add(dicbean);
                        continue;
                    }
                    if("1036".equals(dicbean.getType()))
                    {
                        jsonjsjq.add(dicbean);
                        continue;
                    }
                    if("1037".equals(dicbean.getType()))
                    {
                        jsonyjzlx.add(dicbean);
                        continue;
                    }


                }
                DictionaryManager.getInstance().setYjlx(jsonyjlx);
                DictionaryManager.getInstance().setYjzlx(jsonyjzlx);
                DictionaryManager.getInstance().setHpzl(jsonhpzl);
                DictionaryManager.getInstance().setHpys(jsonhpys);
                DictionaryManager.getInstance().setCsys(jsoncsys);
                DictionaryManager.getInstance().setCllx(jsoncllx);
                DictionaryManager.getInstance().setClzt(jsonclzt);
                DictionaryManager.getInstance().setSyxz(jsonsyxz);
                DictionaryManager.getInstance().setJszzt(jsonjszzt);
                DictionaryManager.getInstance().setZpzl(jsonzpzl);
                DictionaryManager.getInstance().setCzjg(jsonczjg);
                DictionaryManager.getInstance().setFxyclyy(jsonfxyclyy);

                DictionaryManager.getInstance().setJccllx(jsonjccllx);
                DictionaryManager.getInstance().setWljdyy(jsonwljdyy);
                DictionaryManager.getInstance().setWslb(jsonwslb);
                DictionaryManager.getInstance().setJcclzlx(jsonjcclzlx);
                DictionaryManager.getInstance().setGpszbqk(jsongpszbqk);
                DictionaryManager.getInstance().setBxpc(jsonbxpc);
                DictionaryManager.getInstance().setWhpzl(jsonwhpzl);

                DictionaryManager.getInstance().setClyt(jsonclyt);
                DictionaryManager.getInstance().setYtsx(jsonytsx);
                DictionaryManager.getInstance().setRyfl(jsonryfl);
                DictionaryManager.getInstance().setClfl(jsonclfl);
                DictionaryManager.getInstance().setJtfs(jsonjtfs);
                DictionaryManager.getInstance().setCfzl(jsoncfzl);
                DictionaryManager.getInstance().setJkfs(jsonjkfs);
                DictionaryManager.getInstance().setJkbj(jsonjkbj);
                DictionaryManager.getInstance().setSgdj(jsonsgdj);
                DictionaryManager.getInstance().setQzcslx(jsonqzcslx);
                DictionaryManager.getInstance().setSjxm(jsonsjxm);
                DictionaryManager.getInstance().setCjfs(jsoncjfs);
                DictionaryManager.getInstance().setSfzmmc(jsonsfzmmc);
                DictionaryManager.getInstance().setWfzt(jsonwfzt);
                DictionaryManager.getInstance().setJsjq(jsonjsjq);
                mView.downLoadDictionaSuccess(zdbb);
            }

            @Override
            public void failed(String message) {
                mView.showToast(message);
            }
        });


    }

}
//jhfghfh