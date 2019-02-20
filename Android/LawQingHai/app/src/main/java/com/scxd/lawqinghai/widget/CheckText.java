package com.scxd.lawqinghai.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 是否
 * com.scxd.lawqinghai.widget.CheckText
 *
 * @author chenyujin
 */
@SuppressLint("AppCompatCustomView")
public class CheckText extends TextView {
    private int value = 0;
    private boolean isText;

    public CheckText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setText("否");
        setTextSize(14.0F);
        setPadding(6, 6, 6, 6);
//        getPaint().setFakeBoldText(true);
        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (CheckText.this.value >= 2) {
                    CheckText.this.value = 1;
                } else {
                    CheckText.this.value++;
                }
                CheckText.this.setFocusableInTouchMode(true);
                CheckText.this.requestFocus();
                if(isText)
                {
                    CheckText.this.setValue(CheckText.this.value,isText);
                }else {
                    CheckText.this.setValue(CheckText.this.value);
                }

            }
        });
    }

    public int getValue() {

        if (this.getText().toString().equals("是")) {
            return 1;
        } else if (this.getText().toString().equals("否")) {
            return 0;
        } else if (this.getText().toString().equals("有")) {
            return 1;
        } else if (this.getText().toString().equals("无")) {
            return 0;
        }
        return 0;
    }

    public void setValue(int paramInt) {
            switch (paramInt) {
                default:
                case 0:
                    setText("否");
                    break;
                case 1:
                    setText("是");
                    break;

        }
    }

    public void setValue(int paramInt, boolean paramBoolean) {
        this.isText = paramBoolean;
        switch (paramInt) {
            default:
            case 0:
                setText("无");
                break;
            case 1:
                setText("有");
                break;

        }
    }
}
