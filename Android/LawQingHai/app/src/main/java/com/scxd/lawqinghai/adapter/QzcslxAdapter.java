package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.NotificationOfCompulsoryMeasuresDetailActivity;
import com.scxd.lawqinghai.bean.response.DicationResBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/10
 * 修改人：
 * 修改时间：
 */


public class QzcslxAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<DicationResBean> mList;


    public QzcslxAdapter(Context mContext) {
        this.mContext = mContext;

        mInflater = LayoutInflater.from(mContext);


    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.czjg_itam, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ppjy.setChecked(mList.get(position).isFlg());
        holder.ppjy.setText(mList.get(position).getName());
//        holder.ppjy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                mList.get(position).setFlg(b);
//            }
//        });

        holder.ppjy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mList.get(position).setFlg(((CheckBox)view).isChecked());


                ((NotificationOfCompulsoryMeasuresDetailActivity)mContext).Visibility(mList);

            }
        });

        return convertView;
    }

    public List<DicationResBean> getQzcslxList()
    {

        return mList;
    }

    public void setQzcslxList( List<DicationResBean> qzcslxList)
    {
        this.mList = qzcslxList;
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.ppjy)
        CheckBox ppjy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
