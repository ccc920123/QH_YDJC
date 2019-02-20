package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.LedgerListRespBean;
import com.scxd.lawqinghai.bean.response.WarnListBean;
import com.scxd.lawqinghai.common.Md_System_Datas;

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
public class LedgerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LedgerListRespBean.DataBean.ListBean> datas = new ArrayList<>();
    private Context mContext;

    private Map<String, String> clzt = null;
    private Map<String, String> hpzl = null;
    private Map<String, String> jccllx = null;

    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;


    public LedgerListAdapter(Context context) {
        this.mContext = context;

        clzt = new HashMap<>();
        hpzl = new HashMap<>();
        jccllx = new HashMap<>();
        for (DicationResBean bean : DictionaryManager.getInstance().getClzt()) {
            clzt.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getHpzl()) {
            hpzl.put(bean.getCode(), bean.getName());
        }
        for (DicationResBean bean : DictionaryManager.getInstance().getJccllx()) {
            jccllx.put(bean.getCode(), bean.getName());
        }
    }

    public void setDatas(List<LedgerListRespBean.DataBean.ListBean> datas) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ledger_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            final LedgerListRespBean.DataBean.ListBean bean = datas.get(position);

            viewHolder.hphm.setText(bean.getHphm());
            viewHolder.wftime.setText(bean.getJcsj());
            viewHolder.clzt.setText(clzt.get(bean.getClzt()));
            viewHolder.hpzl.setText(hpzl.get(bean.getHpzl()));
            viewHolder.fwzbh.setText(bean.getFwzbh());
            viewHolder.jcczlx.setText(jccllx.get(bean.getJccllx()));
            
            viewHolder.itemLinear.setOnClickListener(new View.OnClickListener() {
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
        void OnItemClick(View view, int position, LedgerListRespBean.DataBean.ListBean bean);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.wftime)
        TextView wftime;
        @BindView(R.id.fwzbh)
        TextView fwzbh;
        @BindView(R.id.hpzl)
        TextView hpzl;
        @BindView(R.id.hphm)
        TextView hphm;
        @BindView(R.id.clzt)
        TextView clzt;
        @BindView(R.id.jcczlx)
        TextView jcczlx;
        @BindView(R.id.item_linear)
        LinearLayout itemLinear;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
