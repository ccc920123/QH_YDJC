package com.scxd.lawqinghai.utils;

import com.scxd.lawqinghai.bean.Md_cartype;
import com.scxd.lawqinghai.bean.other.DictionaryManager;
import com.scxd.lawqinghai.bean.response.DicationResBean;

import java.util.List;

/**
 * 描述：通过code 来设置 spinner 的值
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/7/10
 * 修改人：
 * 修改时间：
 */


public class SpinnerValue {

    /**
     * 设置 List<DicationResBean> 格式的spinner值
     * @param code
     * @param list
     * @return
     */
    public static int getSpinnerValueSelected(String code, List<DicationResBean> list) {

        if (null==code|| "".equals(code))
        {
            return 0;
        }
        for (int i = 0; i < list.size(); i++) {

            if (code.equals(list.get(i).getCode())) {
                return i;

            }


        }
        return 0;

    }

    /**
     * 设置List<Md_cartype> list  格式的spinner值
     * @param code
     * @param list
     * @return
     */
    public static int getSpinnerValueSelectedMd(String code, List<Md_cartype> list) {

        for (int i = 0; i < list.size(); i++) {

            if (code.equals(list.get(i).getName())) {
                return i;

            }


        }
        return 0;

    }
}
