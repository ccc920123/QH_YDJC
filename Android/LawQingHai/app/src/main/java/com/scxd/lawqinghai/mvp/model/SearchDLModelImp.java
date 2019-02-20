package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.bean.request.SearchDLReqBean;
import com.scxd.lawqinghai.bean.request.SearchLDReqBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;
import com.scxd.lawqinghai.network.DataCallback;

/**
 * 描述： 查询道路/路段imp
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/7 15:17
 * 修改人：
 * 修改时间：
 */
public interface SearchDLModelImp {

    void searchDL(SearchDLReqBean bean,
                  MVPCallBack mvpCallBack);


    void searchLD(SearchLDReqBean bean,
                  MVPCallBack mvpCallBack);
}
