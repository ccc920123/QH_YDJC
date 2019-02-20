package com.scxd.lawqinghai.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parkingwang.vehiclekeyboard.KeyboardInputController;
import com.parkingwang.vehiclekeyboard.PopupKeyboard;
import com.parkingwang.vehiclekeyboard.core.KeyboardType;
import com.parkingwang.vehiclekeyboard.view.InputView;
import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.base.BaseFragment;
import com.scxd.lawqinghai.mvp.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 机动车查询
 */
public class TopCarFragment extends BaseFragment {

    @BindView(R.id.carnuber)
    InputView carnuber;
    @BindView(R.id.query_car)
    Button queryCar;
    private PopupKeyboard mPopupKeyboard;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_top_car;
    }

    @Override
    protected void initInjector() {
        
        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(getActivity());
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(carnuber, getActivity());
        //直接设置新能源车牌
        mPopupKeyboard.getController().setLockCarKeyBoard(true);
//        mPopupKeyboard.getKeyboardView()
//                .setKeyboardType(KeyboardType.FULL);

        // KeyboardInputController提供一个默认实现的新能源车牌锁定按钮
//        mPopupKeyboard.getController()
//                .setDebugEnabled(true)
//                .bindLockTypeProxy(new KeyboardInputController.ButtonProxyImpl(lockTypeButton) {
//                    @Override
//                    public void onNumberTypeChanged(boolean isNewEnergyType) {
//                        super.onNumberTypeChanged(isNewEnergyType);
//                        if (isNewEnergyType) {
//                            lockTypeButton.setTextColor(getResources().getColor(android.R.color.holo_green_light));
//                        } else {
//                            lockTypeButton.setTextColor(getResources().getColor(android.R.color.black));
//                        }
//                    }
//                });
//
//
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }



    @OnClick(R.id.query_car)
    public void onViewClicked() {


    }
}
