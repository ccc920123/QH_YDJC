package com.scxd.lawqinghai.common;

import android.os.Environment;

import com.scxd.lawqinghai.application.MyApplication;

import java.io.File;
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
public class Common {

    /**
     * 预警序号
     */
    public static String YJXH = "";

    /**
     * 是否登录成功
     */
    public static boolean ISLOGIN = false;
    public static String ISJPUSH = "0";

    /**
     * 查询
     */
    public static final String QUERY = "querydoc";
    /**
     * 写入
     */
    public static final String WRITE = "writedoc";
    
    public static  String IP = "";
    public static  String PDA_IMEI = "";
    public static String JKXLH = "";
    public static String VERSION = "";
    /**
     * 机构编号
     */
    public static   String JGBH = "";
    /**
     * 登录账号
     */
    public static   String user = "";

    public static String serviceTime;
    
    /**
     * FROM标示符
     */
    public static String FROM = "FROM";
    public static final int PREMISSIONS_CAMERA = 1;
    public static final int WRITE_EXTERNAL_STORAGE = 2;//写的权限
    public static final int ACCESS_COARSE_LOCATION = 3;
    public static boolean TAKEOVER = true;
    public static String PHOTO_KEY = "photo_key";
    public static String HEAD_PHOTO_KEY = "head_photo_key";
    public static String TITLE_TAG = "title_tag";
    public static final String VIN_FRAME = "frame_vin";
    public static String TIME = "TIME_TAG";
    public static String ROTATION = "ROTATION";
    public static final String ZPCODE = "CODE_PHOTO";
    //    public static int TIMEOUT_LOGIN = -1;
    private static Common mCommon = null;
    public static final String CAMERAID ="CAMERAID" ;
    
    
    public static String PHONE_PATH = MyApplication.getApplication().getFileLocation() + File.separator;
    public static String PIC_PATH = MyApplication.getApplication().getPicFileLocation() + File.separator;

    //是否启用测试数据，true启用，false 不启用
    public static  boolean isDataTest=false;

    //离线语音
    public static final String appKey = "wio74rij224vpdb3te2usb5b2tmxgujj6ahr2bqb";
    public static final String secret = "5c9f69a07403a3ee8c910f448307ceaf";

}
