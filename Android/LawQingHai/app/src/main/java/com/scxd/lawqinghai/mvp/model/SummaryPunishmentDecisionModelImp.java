package com.scxd.lawqinghai.mvp.model;

import com.scxd.lawqinghai.mvp.MVPCallBack;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/3
 * 修改人：
 * 修改时间：
 */


public interface SummaryPunishmentDecisionModelImp extends  BaseModel {


    void backSummaryPunishmentDecision(String user,String bmbh,String starTime,String endTime,int page, MVPCallBack mvpCallBack);


}
