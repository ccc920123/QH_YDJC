package com.scxd.lawqinghai.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.adapter.CheckAdapter;
import com.scxd.lawqinghai.adapter.SelectedAdapter;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.request.WriteDispatchBean;
import com.scxd.lawqinghai.bean.response.DispatchResp;
import com.scxd.lawqinghai.bean.response.DispatchRespListCode;
import com.scxd.lawqinghai.bean.response.DispatchRespYjxxBean;
import com.scxd.lawqinghai.bean.response.DispatchRespcjryBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.DispatchPresenter;
import com.scxd.lawqinghai.mvp.view.DispatchView;
import com.scxd.lawqinghai.utils.ButtonTools;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.FlowTagLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @类名: ${type_name}
 * @功能描述: 布控设置
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class DispatchActivity extends BaseActivity implements DispatchView {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.sbkk_linear)
    FlowTagLayout sbkkLinear;
    @BindView(R.id.linertjlx)
    LinearLayout linearLayoutYjlx;
    @BindView(R.id.cjry_linear)
    FlowTagLayout cjryLinear;
    @BindView(R.id.ksjsyj)
    Button ksjsyj;
    @BindView(R.id.yjlx_selected)
    FlowTagLayout yjlxSelected;

    private CheckAdapter sbkkAdapter;
    private CheckAdapter yylxAdapter;
    private CheckAdapter cjrylxAdapter;

    private List<CheckAdapter> yjlxAdapterList;

    private String user;
    private String StrJszt;
    private String bmbh;
    private String sjbmbh;//上级部门编号
    private String ssbmbh;//所属部门编号
    private String stat;//0 停止接受预警 1接受

    private List<DispatchRespYjxxBean.DataBean> yjlxList;
    private SelectedAdapter mSelectedAdapter;
    private Map<String, List<DispatchRespYjxxBean.DataBean>> yjlxMap;

    @Override
    public BasePresenter getPresenter() {
        return new DispatchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dispatch;
    }

    @Override
    protected void initInjector() {
        user = SharedPreferencesUtils.getString(this, "USER", "");
        bmbh = SharedPreferencesUtils.getString(this, "BMBH", "");
        sjbmbh = SharedPreferencesUtils.getString(this, "SJBM", "");
        ssbmbh = SharedPreferencesUtils.getString(this, "SSBMBH", "");
        //        user = "admin";//test
        //        bmbh = "510000";
        title.setText("布控配置");
        if (true) {
            ksjsyj.setBackgroundResource(R.drawable.ic_button_red);
            ksjsyj.setText(R.string.tzjsyj);
        } else {
            ksjsyj.setBackgroundResource(R.drawable.ic_button_blue);
            ksjsyj.setText(R.string.ksjsyj);
        }

        yjlxList = new ArrayList<>();
    }

    @Override
    protected void initEventAndData() {
        sbkkAdapter = new CheckAdapter(this);
        sbkkLinear.setAdapter(sbkkAdapter);

        mSelectedAdapter = new SelectedAdapter(this);
        yjlxSelected.setAdapter(mSelectedAdapter);


        ((DispatchPresenter) mPresenter).getDispatchsbkk(user, sjbmbh, ssbmbh);

    }

    /**
     * 获取多选选中内容
     */

    public String getDatas(CheckAdapter flowTagLayout) {
        StringBuilder sb = new StringBuilder();

        Map<String, Boolean> selectedList = flowTagLayout.getCheckMap();
        for (String key : selectedList.keySet()) {
            if (selectedList.get(key)) {
                sb.append(key);
                sb.append(",");
            }
        }
        String sbkk = sb.toString();
        if ("".equals(sbkk)) {
            return sbkk;
        }
        sbkk = sbkk.substring(0, sbkk.length() - 1);
        return sbkk;
    }

    public String getyjlxDatas(List<CheckAdapter> flowTagLayout) {
        StringBuilder sbyjlx = new StringBuilder();
        if (flowTagLayout.size() <= 0) {
            return "";
        }
        for (int i = 0; i < flowTagLayout.size(); i++) {

            String str = getDatas(flowTagLayout.get(i));
            if (!"".equals(str)) {
                sbyjlx.append(str);
                sbyjlx.append(",");
            }
        }
        String sbkk = sbyjlx.toString();
        if ("".equals(sbkk)) {
            return sbkk;
        }
        sbkk = sbyjlx.substring(0, sbyjlx.length() - 1);

        return sbkk;


    }

    public String getYhlxInformation(){
        StringBuilder sbyjlx = new StringBuilder();
        if (yjlxList.size() > 0){
            for (int i = 0; i < yjlxList.size(); i++) {
                sbyjlx.append(yjlxList.get(i).getCode() + ",");
            }
            String yjlx = sbyjlx.toString();
            if ("".equals(yjlx)) {
                return yjlx;
            }
            yjlx = sbyjlx.substring(0, sbyjlx.length() - 1);

            return yjlx;
        }
        return "";
    }


    @OnClick({R.id.back, R.id.ksjsyj})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.back:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.ksjsyj:
                if (!ButtonTools.isFastDoubleClick(1500)) {
                    if ("1".equals(stat)) {
                        stat = "0";
                        WriteDispatchBean bean = new WriteDispatchBean();
                        bean.setUser(user);
                        bean.setJszt("0");
                        bean.setCjry(getDatas(cjrylxAdapter));
                        bean.setSbkk(getDatas(sbkkAdapter));
                        //                        bean.setYjlx(getyjlxDatas(yjlxAdapterList));
                        bean.setYjlx(getYhlxInformation());
                        bean.setBmbh(bmbh);
                        ((DispatchPresenter) mPresenter).writeDispatch(bean);
                    } else {
                        stat = "1";
                        WriteDispatchBean bean = new WriteDispatchBean();
                        bean.setUser(user);
                        bean.setJszt("1");//开启
                        bean.setCjry(getDatas(cjrylxAdapter));
                        bean.setSbkk(getDatas(sbkkAdapter));
                        //                        bean.setYjlx(getyjlxDatas(yjlxAdapterList));
                        bean.setYjlx(getYhlxInformation());
                        bean.setBmbh(bmbh);
                        ((DispatchPresenter) mPresenter).writeDispatch(bean);
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoadProgressDialog(String str) {
        showLoading(str);
    }

    @Override
    public void disDialog() {

        dissLoadDialog();
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(this, message);
    }


    @Override
    public void getSuccessbkk(Object obj) {
        if ("1".equals(((DispatchResp) obj).getJszt())) {
            stat = "1";
            //            SharedPreferencesUtils.setBoolean(this, "ISLOGIN", true);
            ksjsyj.setBackgroundResource(R.drawable.ic_button_red);
            ksjsyj.setText(R.string.tzjsyj);
        } else {
            stat = "0";
            //            SharedPreferencesUtils.setBoolean(this, "ISLOGIN", false);
            ksjsyj.setBackgroundResource(R.drawable.ic_button_blue);
            ksjsyj.setText(R.string.ksjsyj);
        }
        StrJszt = ((DispatchResp) obj).getJszt();
        List<DispatchRespListCode> sbkkBean = ((DispatchResp) obj).getSbkk();


        sbkkAdapter.onlyAddAll(sbkkBean);

        checkMap = new HashMap<String, CheckBox>();
        ((DispatchPresenter) mPresenter).getDispatchyjll(user);

    }

    private Map<String, CheckBox> checkMap;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void getSuccesyjlx(Object object) {

        List<DispatchRespYjxxBean.DataBean> bean = ((List<DispatchRespYjxxBean.DataBean>) object);
        List<String> type = new ArrayList<>();
        List<DispatchRespYjxxBean.DataBean> flList = null;


        yjlxMap = new LinkedHashMap<>();
        for (DispatchRespYjxxBean.DataBean Dispach : bean) {
            type.add(Dispach.getType());
        }

        for (String str : type) {
            flList = new ArrayList<>();
            for (DispatchRespYjxxBean.DataBean Dispach : bean) {
                if (Dispach.getType().equals(str)) {
                    flList.add(Dispach);
                }
            }
            yjlxMap.put(str, flList);
        }

        yjlxAdapterList = new ArrayList<>();
        final List<DispatchRespYjxxBean.DataBean> check = new ArrayList<>();

        final Map<String, CheckAdapter> adapterMap = new HashMap<>();
        for (final String title : yjlxMap.keySet()) {
            View view = getLayoutInflater().inflate(R.layout.yjlx_item, null);
            linearLayoutYjlx.addView(view);
            ((TextView) view.findViewById(R.id.yllx_title)).setText(title);
            final List<DispatchRespYjxxBean.DataBean> listBean = yjlxMap.get(title);

            final CheckBox childCheck = (CheckBox) view.findViewById(R.id.gaxq);
            childCheck.setTag(title);
            childCheck.setChecked(isAllCheck(listBean));
            checkMap.put(title, childCheck);

            FlowTagLayout ajFlowTagLayout = (FlowTagLayout) view.findViewById(R.id.agxq_linear);
            yylxAdapter = new CheckAdapter(this);
            adapterMap.put(title, yylxAdapter);
            ajFlowTagLayout.setAdapter(yylxAdapter);
            yjlxAdapterList.add(yylxAdapter);
            List<DispatchRespYjxxBean.DataBean> list0 = new ArrayList<>();
            for (int i = 0; i < listBean.size(); i++) {
                if (listBean.get(i).getFlag().equals("1")) {
                    yjlxList.add(listBean.get(i));
                } else {
                    list0.add(listBean.get(i));
                }
            }
            yylxAdapter.onlyAddAll(list0);

            yylxAdapter.setOnItemClickListener(new CheckAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(CompoundButton view, int position, DispatchRespYjxxBean.DataBean bean) {
                    if (view.isChecked()){
                        boolean flag = true;
                        for (int i = 0; i < yjlxList.size(); i++) {
                            if (yjlxList.get(i).getCode().equals(bean.getCode())){
                                flag = false;
                            }
                        }
                        if (flag){
                            yjlxList.add(bean);
                        }

                        for (final String title : yjlxMap.keySet()) {
                            check.clear();
                            if (title.equals(bean.getType())) {
                                for (int i = 0; i < yjlxMap.get(title).size(); i++) {
                                    if (bean.getCode().equals(yjlxMap.get(title).get(i).getCode())) {
                                        yjlxMap.get(title).get(i).setFlag("1");
                                        break;
                                    }
                                }
                            }
                            for (int i = 0; i < yjlxMap.get(title).size(); i++) {
                                if (yjlxMap.get(title).get(i).getFlag().equals("0")){
                                    check.add(yjlxMap.get(title).get(i));
                                }
                            }
                            if (bean.getType().equals(title)){
                                if (check.size() > 0){
                                    checkMap.get(title).setChecked(false);
                                } else {
                                    checkMap.get(title).setChecked(true);
                                }
                            }
                            adapterMap.get(title).clearAndAddAll(check);
                        }

                        mSelectedAdapter.onlyAddAll(yjlxList);
                    }
                }
            });
            final List<DispatchRespYjxxBean.DataBean> list = new ArrayList<>();
            final List<DispatchRespYjxxBean.DataBean> checkList = new ArrayList<>();
            childCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.clear();
                    checkList.clear();
                    checkList.addAll(yjlxMap.get(childCheck.getTag().toString()));
                    if (((CheckBox) view).isChecked()) {
                        for (int i = 0; i < checkList.size(); i++) {
                            checkList.get(i).setFlag("1");
                            if (yjlxList.size() > 0) {
                                for (int j = 0; j < yjlxList.size(); j++) {
                                    if (yjlxList.get(j).getCode().equals(checkList.get(i).getCode())) {
                                        list.add(checkList.get(i));
                                        break;
                                    }
                                }
                            }
                        }
                        yjlxList.removeAll(list);
                        yjlxList.addAll(checkList);
                        //                        yjlxMap.put(childCheck.getTag().toString(), checkList);
                        checkList.clear();
                        adapterMap.get(childCheck.getTag().toString()).clearAndAddAll(checkList);
                        //                        yylxAdapter.clearAndAddAll(checkList);
                    } else {
                        for (int i = 0; i < checkList.size(); i++) {
                            checkList.get(i).setFlag("0");
                            if (yjlxList.size() > 0) {
                                for (int j = 0; j < yjlxList.size(); j++) {
                                    if (yjlxList.get(j).getCode().equals(checkList.get(i).getCode())) {
                                        list.add(yjlxList.get(j));
                                        break;
                                    }
                                }
                            }
                        }
                        yjlxList.removeAll(list);
                        //                        yjlxMap.put(childCheck.getTag().toString(), checkList);
                        adapterMap.get(childCheck.getTag().toString()).clearAndAddAll(checkList);
                    }
                    mSelectedAdapter.onlyAddAll(yjlxList);
                }
            });

        }
        mSelectedAdapter.onlyAddAll(yjlxList);

        mSelectedAdapter.setOnItemClickListener(new SelectedAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(CompoundButton v, int position, DispatchRespYjxxBean.DataBean bean) {
                if (!v.isChecked()){
                    boolean flag = false;
                    for (int i = 0; i < yjlxList.size(); i++) {
                        if (yjlxList.get(i).getCode().equals(bean.getCode())){
                            flag = true;
                            break;
                        }
                    }
                    if (flag){
                        yjlxList.remove(bean);
                    }

                    for (final String title : yjlxMap.keySet()) {
                        check.clear();
                        if (title.equals(bean.getType())) {
                            for (int i = 0; i < yjlxMap.get(title).size(); i++) {
                                if (bean.getCode().equals(yjlxMap.get(title).get(i).getCode())) {
                                    yjlxMap.get(title).get(i).setFlag("0");
                                    break;
                                }
                            }
                        }
                        for (int i = 0; i < yjlxMap.get(title).size(); i++) {
                            if (yjlxMap.get(title).get(i).getFlag().equals("0")){
                                check.add(yjlxMap.get(title).get(i));
                            }
                        }
                        if (bean.getType().equals(title)){
                            if (check.size() > 0){
                                checkMap.get(title).setChecked(false);
                            } else {
                                checkMap.get(title).setChecked(true);
                            }
                        }
                        adapterMap.get(title).clearAndAddAll(check);
                    }

                    mSelectedAdapter.onlyAddAll(yjlxList);
                }
            }
        });

        ((DispatchPresenter) mPresenter).getDispatchcjry(user);
    }

    private boolean isAllCheck(List<DispatchRespYjxxBean.DataBean> listBean) {
        for (int i = 0; i < listBean.size(); i++) {

            if ("0".equals(listBean.get(i).getFlag())) {


                return false;

            }


        }

        return true;

    }


    @Override
    public void getSuccescjry(Object object) {

        List<DispatchRespcjryBean.DataBean> cjryBean = ((List<DispatchRespcjryBean.DataBean>) object);
        cjrylxAdapter = new CheckAdapter(this);
        cjryLinear.setAdapter(cjrylxAdapter);
        cjrylxAdapter.onlyAddAll(cjryBean);
        //出警人员
    }

    @Override
    public void writeSucces() {

        if ("0".equals(stat)) {
            //           正常操作应该在该在关闭服务 //关闭
            Common.ISJPUSH = "0";
            SharedPreferencesUtils.saveString(this, Common.user, Common.ISJPUSH);
            //            NotificationService.startService(this, true);//关闭服务

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            //            正常操作应该在该车开启服务//开启
            //            NotificationService.startService(this, false);  //开启推送服务

            Common.ISJPUSH = "1";
            SharedPreferencesUtils.saveString(this, Common.user, Common.ISJPUSH);

            Intent intent = new Intent(this, WarnTabActivity.class);
            //            Intent intent = new Intent(this, WarnListActivity.class);
            startActivity(intent);
            finish();
        }

    }

}
