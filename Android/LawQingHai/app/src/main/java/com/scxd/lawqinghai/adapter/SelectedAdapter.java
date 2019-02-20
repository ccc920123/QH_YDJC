package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.response.DispatchRespListCode;
import com.scxd.lawqinghai.bean.response.DispatchRespYjxxBean;
import com.scxd.lawqinghai.bean.response.DispatchRespcjryBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by on 15/10/19.    implements OnInitSelectedPosition
 */
public class SelectedAdapter extends BaseAdapter {

    private final Context mContext;
    private List<DispatchRespYjxxBean.DataBean> mDataList;

    public SelectedAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }
   
    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.check_item, null);
            holder = new SelectedAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SelectedAdapter.ViewHolder) convertView.getTag();
        }

        final DispatchRespYjxxBean.DataBean t = mDataList.get(position);
        holder.textView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClick(compoundButton, position, mDataList.get(position));
                }
            }
        });

        holder.textView.setText(t.getName());
        boolean b = t.getFlag().equals("1") ? true : false;
        holder.textView.setChecked(b);

        return convertView;
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(CompoundButton view, int position, DispatchRespYjxxBean.DataBean bean);
    }

    public void onlyAddAll(List<DispatchRespYjxxBean.DataBean> datas) {
        mDataList = datas;
        notifyDataSetChanged();
    }

    /**
     * 清除数据
     *
     * @param datas
     */
    public void clearAndAddAll(List<DispatchRespYjxxBean.DataBean> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    static class ViewHolder {
        @BindView(R.id.checkbox)
        CheckBox textView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
