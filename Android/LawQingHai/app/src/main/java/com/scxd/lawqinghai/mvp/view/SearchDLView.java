package com.scxd.lawqinghai.mvp.view;

import com.scxd.lawqinghai.bean.response.DLRespBean;
import com.scxd.lawqinghai.bean.response.LDRespBean;

import java.util.List;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/7
 * 修改人：
 * 修改时间：
 */


public interface SearchDLView extends BaseView {


    void backDL(List<DLRespBean> bean);

    void backLD(List<LDRespBean> bean ,DLRespBean dl);


}
