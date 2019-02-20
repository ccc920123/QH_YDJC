package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.CarLawListBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.TestBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述: 机动车违法列表
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class CarLawAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CarLawListBean> datas = new ArrayList<>();
    private Context mContext;

    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;
    private Map<String, String> wfztMap = null;

    /**
     * 构造方法
     *
     * @param context
     */
    public CarLawAdapter(Context context,Map<String, String> wfztMap) {
        this.mContext = context;

//违法状态
        this.wfztMap = wfztMap;

    }

    /**
     * 设置数据
     *
     * @param
     */
    public void setDatas(List<CarLawListBean> data) {
//        this.datas.clear();
        this.datas = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount() && mSwitch == 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    /**
     * 渲染布局
     *
     * @param
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot, parent, false);
            return new FooterViewHolder(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_law_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * 适配数据
     *
     * @param
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            final CarLawListBean bean = datas.get(position);
            try {
                viewHolder.dsrxm.setText("当  事  人：" + bean.getDsr());
                if (null == bean.getWfsj()) {
                    viewHolder.wfsj.setText("违法时间：");
                } else {
                    viewHolder.wfsj.setText("违法时间：" + bean.getWfsj());
                }
                viewHolder.wfdz.setText("违法地址：" + bean.getWfdz());
                viewHolder.wfxw.setText(bean.getWfxw());
                viewHolder.fkje.setText("罚款金额：" + bean.getFkje());
                viewHolder.wfjf.setText("违法记分：" + bean.getWfjf());

                switch (bean.getWfzt()) {
                    case "0":
                        viewHolder.wfzt.setText(Html.fromHtml("违法状态：<font color=\"#ff0014\">" + wfztMap.get
                                (bean.getWfzt())+ "</font>"));
                        viewHolder.item_all.setBackgroundResource(R.drawable.text_orange);
                        break;
                    case "1":
                        viewHolder.wfzt.setText("违法状态：" + wfztMap.get(bean.getWfzt()));
                        viewHolder.item_all.setBackgroundResource(R.drawable.text_blue);
                        break;
                    case "2":
                        viewHolder.wfzt.setText("违法状态：" + wfztMap.get(bean.getWfzt()));
                        viewHolder.item_all.setBackgroundResource(R.drawable.text_qing);
                        break;
                    default:
                        viewHolder.wfzt.setText("违法状态：" + wfztMap.get(bean.getWfzt()));
                        viewHolder.item_all.setBackgroundResource(R.drawable.text_item_bg_blue);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            viewHolder.item_all.setOnClickListener(new View.OnClickListener() {
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
     *
     * @param
     */
    public void setFooterOn() {
        mSwitch = 1;
        notifyDataSetChanged();
    }

    /**
     * 关闭脚
     *
     * @param
     */
    public void setFooterOff() {
        mSwitch = 0;
        notifyDataSetChanged();
    }

    /**
     * 监听item
     *
     * @param
     */
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, CarLawListBean testBean);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dsrxm)
        TextView dsrxm;
        @BindView(R.id.wfsj)
        TextView wfsj;
        @BindView(R.id.wfdz)
        TextView wfdz;
        @BindView(R.id.wfxw)
        TextView wfxw;
        @BindView(R.id.item_all)
        LinearLayout item_all;
        @BindView(R.id.wfzt)
        TextView wfzt;
        @BindView(R.id.fkje)
        TextView fkje;
        @BindView(R.id.wfjf)
        TextView wfjf;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
