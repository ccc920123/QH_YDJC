package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.response.TestBean;

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
public class Test02Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TestBean.DataBean> datas = new ArrayList<>();
    private Context mContext;

    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;

    
    public Test02Adapter(Context context) {
        this.mContext = context;
    }

    public void setDatas(List<TestBean.DataBean> datas) {
        this.datas.clear();
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount() && mSwitch == 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot, parent, false);
            return new FooterViewHolder(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_scan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            final TestBean.DataBean bean = datas.get(position);
            viewHolder.test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener !=null){
                        mOnItemClickListener.OnItemClick(view, position, bean);
                    }
                }
            });
        }
    }
    
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView test;
        public ViewHolder(View itemView) {
            super(itemView);
            test = (TextView) itemView.findViewById(R.id.test);
        }
    }
    
    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }


    private int mSwitch = 0;
    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size() + mSwitch;
    }
    
    public void setFooterOn(){
        mSwitch = 1;
        notifyDataSetChanged();
    }
    public void setFooterOff(){
        mSwitch = 0;
        notifyDataSetChanged();
    }
    
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, TestBean.DataBean testBean);
    }
}
