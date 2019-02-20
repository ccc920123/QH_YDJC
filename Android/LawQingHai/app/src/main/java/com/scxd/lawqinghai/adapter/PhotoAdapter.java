package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scxd.lawqinghai.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类名: ${type_name}
 * @功能描述: 照片adapter
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class PhotoAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mList;
    private int flag;
    public PhotoAdapter(Context context, int flag) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = new ArrayList<>();
        this.flag = flag;
    }

    public void setList(List<String> list) {
        mList.clear();
        mList.addAll(list);
//        if (flag != 2){
//            mList.add("android.resource://com.scxd.lawqinghai/" + R.drawable.add);
//        }
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
//        if (flag == 2){
            return mList.size();
//        }
//        return mList.size() + 1;
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
            convertView = mInflater.inflate(R.layout.photo_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
//        if (flag == 2){
            Glide.with(convertView.getContext()).load(mList.get(position))
                    .into(holder.imageview);
//        } else {
//            if (position == 0) {
//                holder.imageview.setImageDrawable(mContext.getResources().getDrawable(R.drawable.add));
//            } else {
//                Glide.with(convertView.getContext()).load(mList.get(position - 1))
//                        .into(holder.imageview);
//            }
//        }
        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener !=null){
                    mOnItemClickListener.OnItemClick(view, position, mList);
                }
            }
        });
        
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.imageview)
        ImageView imageview;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, Object testBean);
    }
}
