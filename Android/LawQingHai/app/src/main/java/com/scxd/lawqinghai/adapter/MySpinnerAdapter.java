package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.response.DicationResBean;

import java.util.List;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/19
 * 修改人：
 * 修改时间：
 */


public class MySpinnerAdapter extends BaseAdapter {

    protected Context mContext;
    protected LayoutInflater mInflater;
    private List<DicationResBean> lists;
    private int resource;

    public MySpinnerAdapter(Context context, List<DicationResBean> datas) {
        mContext = context;
        this.resource=resource;
        mInflater = LayoutInflater.from(context);
        lists=datas;
    }
    @Override
    public int getCount() {
        return lists==null?0:lists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_dialog, null);
        }
        ((TextView) convertView.findViewById(R.id.listitem_dialog_text)).setText(lists.get(position).getCode()+":"+lists.get(position).getName());
        return convertView;
    }
}
