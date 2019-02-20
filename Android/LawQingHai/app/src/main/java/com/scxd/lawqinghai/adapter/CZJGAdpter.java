package com.scxd.lawqinghai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.bean.response.DicationResBean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/6/26
 * 修改人：
 * 修改时间：
 */


public class CZJGAdpter extends BaseAdapter {

    private List<DicationResBean> data;
    private Context con;
    private LayoutInflater mflater;
    private Map<Integer, String> checkMap = new LinkedHashMap<Integer, String>();
    PopupWindow popView;
    TextView editText;

    public CZJGAdpter(List<DicationResBean> data, Context con,
                        PopupWindow popView, TextView edit) {
        super();
        this.data = data;
        this.con = con;
        mflater = LayoutInflater.from(con);
        this.popView = popView;
        editText = edit;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHorld viewHorld;
        if (convertView == null) {
            convertView = mflater.inflate(R.layout.llgg_itme, null);
            viewHorld = new ViewHorld();
            viewHorld.auto = (TextView) convertView.findViewById(R.id.text);

            viewHorld.check = (CheckBox) convertView
                    .findViewById(R.id.checkBox1);
            convertView.setTag(viewHorld);
        } else {
            viewHorld = (ViewHorld) convertView.getTag();
            // resetViewHolder(viewHorld);
        }
        viewHorld.auto.setText(((DicationResBean) data.get(position)).getCode());
        viewHorld.check.setChecked(checkMap.get(position) == null ? false
                : true);
		/*
		 * viewHorld.check.setOnCheckedChangeListener(new
		 * OnCheckedChangeListener() {
		 *
		 * @Override public void onCheckedChanged(CompoundButton buttonView,
		 * boolean isChecked) { if(isChecked) { checkMap.put(position,
		 * viewHorld.auto.getText().toString()); }else{ try{
		 * checkMap.remove(position);}catch(Exception e){
		 *
		 * } }
		 *
		 * } });
		 */
      /*  viewHorld.check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                if (cb.isChecked()) {
                    if ("其它".equals(viewHorld.auto.getText().toString())) {
                        checkMap.clear();
                        checkMap.put(position, viewHorld.auto.getText()
                                .toString());
                        editText.setText(viewHorld.auto.getText()
                                .toString());
                        popView.dismiss();


                    } else {
                        if (checkMap.size() <= 2) {
                            checkMap.put(position, viewHorld.auto.getText()
                                    .toString());
                        }else{
                            cb.setChecked(false);
                            Toast.makeText(con, "最多只能选择3种颜色", 1).show();
                        }


                    }
                } else {
                    try {
                        checkMap.remove(position);
                    } catch (Exception e) {

                    }
                }
            }
        });*/
        return convertView;
    }

    protected void resetViewHolder(ViewHorld p_ViewHolder) {
        p_ViewHolder.auto.setText(null);
        p_ViewHolder.check.setChecked(false);

    }

    class ViewHorld {
        TextView auto;
        CheckBox check;
    }

    public Map<Integer, String> getCheckDate() {

        return checkMap;

    }

    public void clearMap() {

        checkMap.clear();
    }
}
