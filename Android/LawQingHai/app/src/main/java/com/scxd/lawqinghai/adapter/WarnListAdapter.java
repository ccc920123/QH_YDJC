package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.TestBean;
import com.scxd.lawqinghai.bean.response.WarnListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class WarnListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WarnListBean.DataBean.ListBean> datas = new ArrayList<>();
    private Context mContext;

    private Map<String, String> yjlx = null;
    private Map<String, String> hpys = null;
    
    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;


    public WarnListAdapter(Context context) {
        this.mContext = context;

        yjlx = new HashMap<>();
        hpys = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getYjlx()){
            yjlx.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getHpys()){
            hpys.put(bean.getCode(), bean.getName());
        }
    }

    public void setDatas(List<WarnListBean.DataBean.ListBean> datas) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.warn_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            final WarnListBean.DataBean.ListBean bean = datas.get(position);
            
            viewHolder.hphm.setText(bean.getHphm());
            
            viewHolder.content.setText(yjlx.get(bean.getYjlx()));
            viewHolder.yjsj.setText("预警时间：" + bean.getYjsj());
            
            switch (bean.getStatus()){
                case "0":
                    viewHolder.type.setText(R.string.dqs);
                    viewHolder.type.setTextColor(mContext.getResources().getColor(R.color.red));
                    viewHolder.linear.setBackgroundResource(R.drawable.text_orange);
                    break;
                case "1":
                    viewHolder.type.setText(R.string.yqswcl);
                    viewHolder.type.setTextColor(mContext.getResources().getColor(R.color.red));
                    viewHolder.linear.setBackgroundResource(R.drawable.text_blue);
                    break;
                case "2":
                    viewHolder.type.setText(R.string.ycl);
                    viewHolder.type.setTextColor(mContext.getResources().getColor(R.color.text_color));
                    viewHolder.linear.setBackgroundResource(R.drawable.text_qing);
                    break;
            }
            
            viewHolder.linear.setOnClickListener(new View.OnClickListener() {
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

    public void setFooterOn() {
        mSwitch = 1;
        notifyDataSetChanged();
    }

    public void setFooterOff() {
        mSwitch = 0;
        notifyDataSetChanged();
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, WarnListBean.DataBean.ListBean bean);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hphm)
        TextView hphm;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.yjsj)
        TextView yjsj;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.linear)
        LinearLayout linear;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
