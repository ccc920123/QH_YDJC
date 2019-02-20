package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.response.DispatchRespListCode;
import com.scxd.lawqinghai.bean.request.WarnListRequestBean;
import com.scxd.lawqinghai.bean.response.DispatchRespYjxxBean;
import com.scxd.lawqinghai.bean.response.DispatchRespcjryBean;
import com.scxd.lawqinghai.mvp.presenter.DispatchPresenter;
import com.scxd.lawqinghai.widget.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by on 15/10/19.    implements OnInitSelectedPosition
 */
public class CheckAdapter<T> extends BaseAdapter {

    private final Context mContext;
    private final List<T> mDataList;
    private Map<String, Boolean> checkMap ;

    public CheckAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
        checkMap = new HashMap<>();
    }
    public CheckAdapter(Context context,List<T> datas) {
        this.mContext = context;
        mDataList = datas;
        checkMap = new HashMap<>();
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
            holder = new CheckAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CheckAdapter.ViewHolder) convertView.getTag();
        }


        /* textView = (CheckBox) view.findViewById(R.id.checkbox);*/

        final T t = mDataList.get(position);
        holder.textView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (t instanceof DispatchRespListCode) {
                    checkMap.put(((DispatchRespListCode) t).getCode(), b);
                } else if (t instanceof DispatchRespYjxxBean.DataBean) {
                    checkMap.put(((DispatchRespYjxxBean.DataBean) t).getCode(), b);
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.OnItemClick(compoundButton, position, 
                                (DispatchRespYjxxBean.DataBean)mDataList.get(position));
                    }
                } else if (t instanceof DispatchRespcjryBean.DataBean) {
                    checkMap.put(((DispatchRespcjryBean.DataBean) t).getCode(), b);

                }

            }
        });


        if (t instanceof DispatchRespListCode) {

            holder.textView.setText(((DispatchRespListCode) t).getName());
            boolean b = ((DispatchRespListCode) t).getFlag().equals("1") ? true : false;
            holder.textView.setChecked(b);
            checkMap.put(((DispatchRespListCode) t).getCode(), b);
        } else if (t instanceof DispatchRespYjxxBean.DataBean) {
            holder.textView.setText(((DispatchRespYjxxBean.DataBean) t).getName());
            boolean b = ((DispatchRespYjxxBean.DataBean) t).getFlag().equals("1") ? true : false;
            holder.textView.setChecked(b);
            checkMap.put(((DispatchRespYjxxBean.DataBean) t).getCode(), b);
        } else if (t instanceof DispatchRespcjryBean.DataBean) {
            holder.textView.setText(((DispatchRespcjryBean.DataBean) t).getName());
            boolean b = ((DispatchRespcjryBean.DataBean) t).getFlag().equals("1") ? true : false;
            holder.textView.setChecked(b);
            checkMap.put(((DispatchRespcjryBean.DataBean) t).getCode(), b);
        }

        return convertView;
    }

    public Map<String, Boolean> getCheckMap() {
        return checkMap;
    }

    public void onlyAddAll(List<T> datas) {
        List<DispatchRespYjxxBean.DataBean> list0 = new ArrayList<>();
        for (int i = 0; i < mDataList.size(); i++) {
            T t = mDataList.get(i);
            if (t instanceof DispatchRespYjxxBean.DataBean) {
                for (int j = 0; j < datas.size(); j++) {
                    T t1 = datas.get(j);
                    if (t1 instanceof DispatchRespYjxxBean.DataBean) {
                        if (((DispatchRespYjxxBean.DataBean) t).getCode().equals(((DispatchRespYjxxBean.DataBean) t1).getCode())){
                            list0.add((DispatchRespYjxxBean.DataBean) mDataList.get(i));
                        }
                    }
                }
            }
        }
        mDataList.removeAll(list0);
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }
    
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(CompoundButton view, int position, DispatchRespYjxxBean.DataBean bean);
    }

    /**
     * 清除数据
     *
     * @param datas
     */
    public void clearAndAddAll(List<T> datas) {
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
