package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.response.QueryCodeListBean;
import com.scxd.lawqinghai.bean.response.QueryCodeRspBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CodeQueryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QueryCodeRspBean.DataBean.ListBean> datas = new ArrayList<>();
    private Context mContext;

    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;


    /**
     * 构造方法
     * @param
     */
    public CodeQueryAdapter(Context context) {
        this.mContext = context;
    }

    public void setDatas(List<QueryCodeRspBean.DataBean.ListBean> datas) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.code_query_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            final QueryCodeRspBean.DataBean.ListBean bean = datas.get(position);
            
            try {
                viewHolder.wfdm.setText(bean.getWfdm());
                viewHolder.wfnr.setText(bean.getWfnr());
                viewHolder.wfjf.setText(bean.getWfjf());
            } catch (Exception e){
                e.printStackTrace();
            }
            
            viewHolder.itemAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.OnItemClick(view, position, bean);
                    }
                }
            });
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

    /**
     * 开启脚
     * @param
     */
    public void setFooterOn() {
        mSwitch = 1;
        notifyDataSetChanged();
    }

    /**
     * 关闭脚
     * @param
     */
    public void setFooterOff() {
        mSwitch = 0;
        notifyDataSetChanged();
    }

    /**
     * 监听item
     * @param
     */
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, QueryCodeRspBean.DataBean.ListBean testBean);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.wfdm)
        TextView wfdm;
        @BindView(R.id.wfnr)
        TextView wfnr;
        @BindView(R.id.wfjf)
        TextView wfjf;
        @BindView(R.id.item_all)
        LinearLayout itemAll;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
