package com.scxd.idcardlibs;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class DriverScanActivity extends AppCompatActivity {


    private FrameLayout mainFragment;
    private Button btnClose;
    private static Button light;
    private TextView sfz;
    private TextView zxbh;

    private List<Fragment> fragments;
    private TextView[] textViews;
    private FragmentManager fm;
    private FragmentTransaction ft;

    static {
        System.loadLibrary("iconv");
        System.loadLibrary("zbar");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mainFragment = (FrameLayout) findViewById(R.id.main_fragment);
        btnClose = (Button) findViewById(R.id.btn_close);
        light = (Button) findViewById(R.id.light);
        sfz = (TextView) findViewById(R.id.sfz);
        zxbh = (TextView) findViewById(R.id.zxbh);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        sfz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(0);
            }
        });
        zxbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(1);
            }
        });
        
        fm = getFragmentManager();
        fragments = getFragments();
        textViews = new TextView[]{sfz,zxbh};
        setCurrentFragment(1);
    }

    private List<Fragment> getFragments() {
        this.fragments = new ArrayList<>();
        fragments.add(new ReconCardFragment());
        fragments.add(new TXMSMFramgent());
        return fragments;
    }


    public static Button getLight() {
        return light;
    }

    private Fragment currentFrag = null;
    private int currentIndex = -1;
    private ReconCardFragment mCardFragment;
    private TXMSMFramgent mTXMSMFramgent;

    @SuppressLint("ResourceType")
    private void setCurrentFragment(int index) {
        ft = fm.beginTransaction();
        if (currentIndex != -1){
            textViews[currentIndex].setTextColor(Color.rgb(155,155,155));
        }
        textViews[index].setTextColor(Color.rgb(0,160,81));
        if (currentIndex == index) {
            return;
        } else {
            currentIndex = index;
        }
        if (currentFrag != null) {
            ft.hide(currentFrag);
        }
        ft.setCustomAnimations(
                R.anim.slide_right_in, R.anim.fragment_slide_left_out,
                R.anim.fragment_slide_left_in, R.anim.fragment_slide_right_out);
        switch (index) {
            case 0:
                if (mCardFragment == null) {
                    mCardFragment = new ReconCardFragment();
//                    ft.add(R.id.main_fragment, fragments.get(0));
//                } else {
//                    ft.show(fragments.get(0));
                }
                ft.replace(R.id.main_fragment, fragments.get(0));
                currentFrag = fragments.get(0);
                break;
            case 1:
                if (mTXMSMFramgent == null) {
                    mTXMSMFramgent = new TXMSMFramgent();
//                    ft.add(R.id.main_fragment, fragments.get(1));
//                } else {
//                    ft.show(fragments.get(1));
                }
                ft.replace(R.id.main_fragment, fragments.get(1));
                currentFrag = fragments.get(1);
                break;
        }
        ft.commit();
    }


}
