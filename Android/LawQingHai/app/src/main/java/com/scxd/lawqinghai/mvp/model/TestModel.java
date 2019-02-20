package com.scxd.lawqinghai.mvp.model;

import android.content.Context;

import com.scxd.lawqinghai.bean.ResponseRootVo;
import com.scxd.lawqinghai.bean.response.TestBean;
import com.scxd.lawqinghai.mvp.MVPCallBack;

import java.util.List;


/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public interface TestModel extends BaseModel {

    void loadDatas(Context context,String param, MVPCallBack mvpCallBack);

}
//jhfghfh