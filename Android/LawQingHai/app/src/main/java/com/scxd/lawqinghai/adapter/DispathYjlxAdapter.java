package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.response.DispatchRespYjxxBean;
import com.scxd.lawqinghai.widget.FlowTagLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by on 15/10/19.    implements OnInitSelectedPosition
 */
public class DispathYjlxAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<String> mDataList;
    private Map<String, List<DispatchRespYjxxBean.DataBean>> mapDataList;

    public DispathYjlxAdapter(Context context, Map<String, List<DispatchRespYjxxBean.DataBean>> dataList) {
        this.mContext = context;
        this.mapDataList = dataList;
        mDataList=new ArrayList<>();
        for (String str : mapDataList.keySet()) {
            mDataList.add(str);

        }


    }

    @Override
    public int getCount() {
        return mapDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mapDataList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.yjlx_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.yllxTitle.setText(mDataList.get(position));
        CheckAdapter yylxAdapter = new CheckAdapter(mContext,mapDataList.get(mDataList.get(position)));
//        yylxAdapter.onlyAddAll(mapDataList.get(mDataList.get(position)));
        holder.agxqLinear.setAdapter(yylxAdapter);


        return convertView;
    }


//    @Override
//    public boolean isSelectedPosition(int position) {


    //        for (int i = 0; i < mDataList.size(); i++) {
//            final T t = mDataList.get(position);
//            if (t instanceof DispatchRespListCode) {
//                if (((DispatchRespListCode) t).getFlag().equals("1")) {
//                    checkMap.put(((DispatchRespListCode) t).getCode(), true);
//                    textView.setChecked(true);
//                    return true;
//                }
//            } else if (t instanceof DispatchRespYjxxBean.DataBean) {
//                if (((DispatchRespYjxxBean.DataBean) t).getFlag().equals("1")) {
//                    checkMap.put(((DispatchRespYjxxBean.DataBean) t).getCode(), true);
//                    textView.setChecked(true);
//                    return true;
//                }
//            } else if (t instanceof DispatchRespcjryBean.DataBean) {
//                if (((DispatchRespcjryBean.DataBean) t).getFlag().equals("1")) {
//                    checkMap.put(((DispatchRespcjryBean.DataBean) t).getCode(), true);
//                    textView.setChecked(true);
//                    return true;
//                }
//
//            }
//
//        }
//
//
//        return false;
//    }

    static class ViewHolder {
        @BindView(R.id.yllx_title)
        TextView yllxTitle;
        @BindView(R.id.gaxq)
        CheckBox gaxq;
        @BindView(R.id.agxq_linear)
        FlowTagLayout agxqLinear;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
