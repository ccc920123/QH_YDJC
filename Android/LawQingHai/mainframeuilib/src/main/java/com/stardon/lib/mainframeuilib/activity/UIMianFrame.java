package com.stardon.lib.mainframeuilib.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stardon.lib.mainframeuilib.activity.adapter.UIFragmentPagerAdapter;
import com.stardon.lib.mainframeuilib.activity.impl.IBase;
import com.stardon.lib.mainframeuilib.activity.utils.LayoutInflaterUtil;

import java.util.List;


/**
 * 类名: UIMianFrame
 * <br/>功能描述: 主界面类库，通过几次UIMianFrame来实现功能，几次后getContentLayout（）表示
 * <br/>设置的layout。可以设置你喜欢的layout。
 * <br/>本类的逻辑为：实现Ibase接口，通过oncreate来加载界面，界面中通过setContentView（）设置view
 * <br/>在设置view前通过了createView来加载了布局。子类通过bindView来实现界面的逻辑
 * <br/>主要是由 Fragment 来构成，该功能实现了自动添加子界面
 * <br/>通过方法来实现子界面的个数，该小功能的设计元素采用viewpager+RadioGroup（RadioGroup包含RadioButton）模式
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/7
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */


public abstract class UIMianFrame extends FragmentActivity implements IBase,
        ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    //view  整个布局文件
    protected View mRootView;
    //viewpage的适配器
    protected UIFragmentPagerAdapter mAdapter;
    //RadioButton集合，用于实现viewpager互动
    protected List<RadioButton> mRadioButtonList;
    //继承类传过来的ViewPager
    protected ViewPager mViewPager;
    //继承类传过来的RadioGroup
    protected RadioGroup mRadioGroup;
    //Fragment数据组，需要从继承界面传过来
    List<Fragment> mFragmentData;

    protected TextView viewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
        bindView(savedInstanceState);

    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflaterUtil.inflate(this, getContentLayout());
        return view;
    }


    @Override
    public View getView() {
        return mRootView;
    }

    /**
     * 方法名称: processLogic() 处理逻辑
     * <br/>方法详述: 处理viewpager，设置viewpager的适配器，
     * <br/>参数: viewPager，mFragment数据集合
     * <br/>返回值:
     * <br/>异常抛出
     * <br/>异常抛出
     */

    protected void processLogic(ViewPager viewPager, List<Fragment> mFragments) {
        this.mViewPager = viewPager;
        this.mFragmentData = mFragments;
        mAdapter = new UIFragmentPagerAdapter(this.getSupportFragmentManager(), mFragmentData);
        mViewPager.setAdapter(mAdapter);


    }

    /**
     * 方法名称: setViewPagerOnPageChangeListener() 给ViewPager添加OnPageChangeListener事件
     * <br/>方法详述:给ViewPager添加OnPageChangeListener事件
     * <br/>参数:RadioButton数据集合，通过继承界面将RadioGroup里面的RadioButton封装到list数组中
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    protected void setViewPagerOnPageChangeListener(List<RadioButton> radioButton) {
        this.mRadioButtonList = radioButton;
        mViewPager.addOnPageChangeListener(this);

    }

    /**
     * 方法名称: setRadioGroupOnCheckedChangeListener() 给RadioGroup添加OnCheckedChangeListener事件
     * <br/>方法详述:给RadioGroup添加OnCheckedChangeListener事件
     * <br/>参数:RadioGroup
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    protected void setRadioGroupOnCheckedChangeListener(RadioGroup radioGroup) {
        this.mRadioGroup = radioGroup;
        mRadioGroup.setOnCheckedChangeListener(this);


    }

    /**
     * 方法名称: 设置标题
     * <br/>方法详述: 保证你标题实在主界面设置，而不是在子fragment中设置，次方发只保证能在主界面设置
     * <br/>标题，如果你在子fragment中写标题就可以不用调用该方法
     * <br/>参数:view
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    protected void setUiFrameTitle(TextView view) {
        this.viewText = view;

    }

    /**
     * 方法名称: 设置ViewPager选中的item
     * <br/>方法详述: 该方法应该在设置Adapter，
     * <br/>setViewPagerOnPageChangeListener（），
     * <br/>setRadioGroupOnCheckedChangeListener（）之后调用，这样才能确保不会报错
     * <br/>参数:item 设置选中的item
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    protected void setCurrentItem(int item) {
        try {
            mViewPager.setCurrentItem(item);
            mRadioButtonList.get(item).setChecked(true);
            if (viewText != null) {
                viewText.setText(mRadioButtonList.get(item).getText());
            }
        } catch (Exception e) {
            Toast.makeText(this, "该方法应在设置Adapter，setViewPagerOnPageChangeListener，setRadioGroupOnCheckedChangeListener之后调用", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

        switch (mViewPager.getCurrentItem()) {
            default:
                if (viewText != null) {
                    viewText.setText(mRadioButtonList.get(mViewPager.getCurrentItem()).getText());
                }
                mRadioButtonList.get(mViewPager.getCurrentItem()).setChecked(true);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            default:
                mViewPager.setCurrentItem(group.indexOfChild(findViewById(group.getCheckedRadioButtonId())));
                break;
        }

    }

}
