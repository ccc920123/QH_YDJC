package com.scxd.lawqinghai.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseActivity;
import com.scxd.lawqinghai.bean.response.LawDetailsRsp;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;
import com.scxd.lawqinghai.mvp.presenter.LawDetailsPresenter;
import com.scxd.lawqinghai.mvp.view.LawDetailsView;
import com.scxd.lawqinghai.utils.ToastUtils;
import com.scxd.lawqinghai.widget.MyScrollerView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.OnClickListener;

/**
 * @类名: ${type_name}
 * @功能描述: 违法代码详情
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LawDetailsActivity extends BaseActivity implements LawDetailsView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.sreach_linear)
    LinearLayout sreachLinear;
    @BindView(R.id.syg)
    ImageView syg;
    @BindView(R.id.sreach_img)
    ImageView sreachImg;
    @BindView(R.id.xyg)
    ImageView xyg;
    @BindView(R.id.sreach_tools)
    LinearLayout sreachTools;
    @BindView(R.id.cancle)
    Button cancle;
    @BindView(R.id.scrollview)
    MyScrollerView scrollview;
    private String ID;

    private String contentNet = "";

    @Override
    public BasePresenter getPresenter() {
        return new LawDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_law_details;
    }

    @Override
    protected void initInjector() {
        ID = getIntent().getStringExtra("ID");
        title.setText(getIntent().getStringExtra("NAME"));
        sreachTools.setVisibility(View.GONE);
        sreachLinear.setVisibility(View.GONE);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initEventAndData() {
        try {
            ((LawDetailsPresenter) mPresenter).loadLawLaw(this, ID);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast(this, "加载失败，请重新加载");
            finish();
        }

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
    public void showCodeDatas(LawDetailsRsp.DataBean dataBean) {
        try {
            content.setText(dataBean.getContent());
            contentNet = dataBean.getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean flag = false;

    public void showDialogSreach() {
        String input = editContent.getText().toString();
        if (input.equals("")) {
            ToastUtils.showToast(LawDetailsActivity.this, "查找内容不能为空！");
        } else {
            if (!"".equals(contentNet)) {
                //                                String text = matcherSearchTitle(contentNet, input);
                scrollview.scrollTo(0, 0);
                StringBuffer resStr = new StringBuffer();
                position = new ArrayList<>();
                meau = new ArrayList<>();
                subPos = 0;
                String text = addChild(contentNet, input, resStr).toString();
                content.setText(Html.fromHtml(text.replaceAll("\\n", "<br>")));
                if (null != position && position.size() > 0) {
                    getLine(position);
                    pos = 0;
                    scrollview.scrollTo(0, meau.get(pos));
                    content.getHeight();
                    sreachTools.setVisibility(View.VISIBLE);
                    sreachLinear.setVisibility(View.GONE);
                    editContent.setText("");
                    flag = true;
                }
            }
        }
    }

    /**
     * 搜索关键字标红
     *
     * @param content
     * @param keyword
     * @return
     */
    public String matcherSearchTitle(String content, String keyword) {
        String wordReg = "(?i)" + keyword;//用(?i)来忽略大小写  
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile(wordReg).matcher(content);
        while (matcher.find()) {
            //这样保证了原文的大小写没有发生变化  
            matcher.appendReplacement(sb, "<font color=\"#ff0014\">" + matcher.group() + "</font>");
        }
        matcher.appendTail(sb);
        content = sb.toString();
        //如果匹配和替换都忽略大小写,则可以用以下方法
        //content = content.replaceAll(wordReg,"<font color=\"#ff0014\">"+keyword+"</font>");  
        Log.i("Utils", content);
        return content;
    }

    private List<Integer> position;
    private int subPos = 0;
    /**
     * 获取查找文字下标
     * @param str   文本内容
     * @param inputs    查找内容
     * @param resStr    重新拼装的文本内容
     * @return
     */
    public StringBuffer addChild(String str, String inputs, StringBuffer resStr) {
        int index = str.length();//用来做为标识,判断关键字的下标  
        int theIndex = str.indexOf(inputs);
        if (theIndex < index && theIndex > 0) {
            index = theIndex;//替换下标  
        }
        //如果条件成立,表示串中已经没有可以被替换的关键字,否则递归处理  
        if (index == str.length()) {
            resStr.append(str);
        } else {
            resStr.append(str.substring(0, index));
            resStr.append("<font color='#FF0000'>" + str.substring(index, index + inputs.length()) + "</font>");
            position.add(index + inputs.length() + subPos);
            subPos += (index + inputs.length());
            String str1 = str.substring(index + inputs.length(), str.length());
            addChild(str1, inputs, resStr);//剩余的字符串继续替换  
        }
        return resStr;
    }

    private int pos = 0;
    @OnClick({R.id.more, R.id.query, R.id.cancle, R.id.syg, R.id.sreach_img, R.id.xyg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more:
                sreachLinear.setVisibility(View.VISIBLE);
                more.setVisibility(View.INVISIBLE);
                break;
            case R.id.query:
                showDialogSreach();
                break;
            case R.id.cancle:
                sreachLinear.setVisibility(View.GONE);
                if (sreachTools.getVisibility() == View.GONE){
                    more.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.syg:
                if (null != meau && meau.size() > 0) {
                    if (pos == 0){
                        ToastUtils.showToast(this, "已到到顶了");
                    } else {
                        pos --;
                        scrollview.scrollTo(0, meau.get(pos));
                    }
                }
                break;
            case R.id.sreach_img:
                sreachLinear.setVisibility(View.VISIBLE);
                break;
            case R.id.xyg:
                if (null != meau && meau.size() > 0) {
                    if (pos == meau.size()){
                        ToastUtils.showToast(this, "已到到底了");
                    } else {
                        scrollview.scrollTo(0, meau.get(pos));
                        pos ++;
                    }
                }
                break;
        }
    }

    private List<Integer> meau;// y 坐标集合
    /**
     * 获取每一个下标对应的 y 坐标
     * @param position
     */
    public void getLine(List<Integer> position) {
        Layout layout = content.getLayout();
        int yAxisBottom = 0;
        for (int j = 0; j < position.size(); j++) {
            Rect bound = new Rect();
            int line = layout.getLineForOffset(position.get(j));
            layout.getLineBounds(line, bound);
            if (yAxisBottom != bound.bottom + content.getScrollY()) {
                yAxisBottom = bound.bottom + content.getScrollY();
                if (yAxisBottom > 150) {
                    meau.add(yAxisBottom - 150);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (flag) {
            sreachTools.setVisibility(View.GONE);
            more.setVisibility(View.VISIBLE);
            content.setText(contentNet);
            flag = false;
        } else {
            finish();
        }

    }


}
