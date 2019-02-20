package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.WarnBackActivity;
import com.scxd.lawqinghai.bean.Md_cartype;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.DicationResBean;
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
public class CzjgAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<DicationResBean> mList;


    private Map<String, Boolean> isSelected;
    private int flag = 0;
    public void setIsSelected(Map<String, Boolean> isSelected) {
        this.isSelected = isSelected;
        notifyDataSetChanged();
    }

    public CzjgAdapter(Context context, int flag) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = DictionaryManager.getInstance().getCzjg();

        isSelected = new HashMap<>();
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.czjg_itam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.ppjy.setText(mList.get(position).getName());

        holder.ppjy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if ("2".equals(mList.get(position).getCode()))
                    {
                        ((WarnBackActivity)mContext).setwslb(true);
                    }else if("3".equals(mList.get(position).getCode()))
                    {
                        ((WarnBackActivity)mContext).setYjbm(true);
                    }
                    isSelected.put(mList.get(position).getCode(), true);

                } else {
                    if ("2".equals(mList.get(position).getCode()))
                    {
                        ((WarnBackActivity)mContext).setwslb(false);
                    }else if("3".equals(mList.get(position).getCode()))
                    {
                        ((WarnBackActivity)mContext).setYjbm(false);
                    }
                    isSelected.put(mList.get(position).getCode(), false);
                }
            }
        });

       /* final ViewHolder finalHolder = holder;
        holder.ppjy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalHolder.ppjy.isChecked()){
                    if ("2".equals(mList.get(position).getCode()))
                    {
                        ((WarnBackActivity)mContext).setwslb(true);
                    }else if("3".equals(mList.get(position).getCode()))
                    {
                        ((WarnBackActivity)mContext).setYjbm(true);
                    }
                    isSelected.put(mList.get(position).getCode(), true);

                } else {
                    if ("2".equals(mList.get(position).getCode()))
                    {
                        ((WarnBackActivity)mContext).setwslb(false);
                    }else if("3".equals(mList.get(position).getCode()))
                    {
                        ((WarnBackActivity)mContext).setYjbm(false);
                    }
                    isSelected.put(mList.get(position).getCode(), false);
                }
            }
        });*/
        holder.ppjy.setChecked(isSelected.get(mList.get(position).getCode()));



        
        return convertView;
    }

    public Map<String, Boolean> getIsSelected() {
        return isSelected;
    }

    static class ViewHolder {
        @BindView(R.id.ppjy)
        CheckBox ppjy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
