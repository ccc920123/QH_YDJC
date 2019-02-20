/**
 * @文件名：AssestToString.java
 * @author Administrator user=chenpan
 * @创建日期 2015-10-22
 * @功能描述：
 */
package com.scxd.lawqinghai.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.IOException;
import java.io.InputStream;

/**
 * @类名 :AssestToString.java
 * @author Administrator
 * @功能描述:
 * @创建时间:2015-10-22
 */
public class AssestToString {

    public static String xmltoString(Context context, String stringName) {
        InputStream xml = null;
        String result = null;
        try {
            xml = context.getResources().getAssets().open(stringName);

            int length = xml.available();// 获取文字字数
            byte[] buffer = new byte[length];
            xml.read(buffer);// 读到数组中
            // 设置编码
            result = new String(buffer, "UTF-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
  /**  读取Assets文件夹中的图片资源
 * @param context
 * @param fileName 图片名称
 * @return
         */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
