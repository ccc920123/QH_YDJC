package com.scxd.lawqinghai.adapter;

import android.content.Context;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseViewHolder;
import com.scxd.lawqinghai.base.SimpleAdapter;
import com.scxd.lawqinghai.bean.response.TestBean;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:    无逻辑操作的adapter
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class TestAdapter extends SimpleAdapter<TestBean> {
    
    
    public TestAdapter(Context context, List<TestBean> beans) {
        super(context, R.layout.activity_scan, beans);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, TestBean item) {

        viewHoder.getTextView(R.id.test).setText(item.getContent());
        
    }
}
