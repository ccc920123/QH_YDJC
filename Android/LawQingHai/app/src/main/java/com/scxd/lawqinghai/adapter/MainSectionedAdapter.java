package com.scxd.lawqinghai.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.activity.SearchDLActivity;
import com.scxd.lawqinghai.activity.SummaryPunishmentDetailActivity;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.response.DLRespBean;
import com.scxd.lawqinghai.bean.response.DicationResBean;
import com.scxd.lawqinghai.bean.response.LDRespBean;
import com.scxd.lawqinghai.bean.response.WarnListBean;
import com.scxd.lawqinghai.widget.adapter.SectionedBaseAdapter;

import java.util.List;


/**
 * 描述：右侧Adapter
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/7 15:56
 * 修改人：
 * 修改时间：
 */
public class MainSectionedAdapter extends SectionedBaseAdapter {

    private Context mContext;
    private List<DLRespBean> dlList;  //道路
    private List<LDRespBean> ldList;  //路段



    public MainSectionedAdapter(Context context) {
        this.mContext = context;
    }

    public void setDataList(List<DLRespBean> dlList, List<LDRespBean> ldList) {
        this.dlList = dlList;
        this.ldList = ldList;
        notifyDataSetChanged();

    }


    @Override
    public Object getItem(int section, int position) {
        return ldList.get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    /**
     * 左边数据的个数
     * @return
     */
    @Override
    public int getSectionCount() {
        return  dlList == null ? 0 : dlList.size();
    }

    /**
     * 右边list的数据个数
     * @param section
     * @return
     */
    @Override
    public int getCountForSection(int section) {
        return ldList == null ? 0 : ldList.size();
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (RelativeLayout) inflator.inflate(R.layout.right_list_item, null);
        } else {
            layout = (RelativeLayout) convertView;
        }
        ((TextView) layout.findViewById(R.id.textItem)).setText(ldList.get(position).getLdmc());
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ((SearchDLActivity)mContext).getDLData(dlList.get(0),ldList.get(position));
            }
        });
        return layout;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText("路段名称");//dlList.get(section).getDlmc()
        return layout;
    }


}
