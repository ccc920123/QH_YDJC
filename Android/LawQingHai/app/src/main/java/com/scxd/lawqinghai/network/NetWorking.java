package com.scxd.lawqinghai.network;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.bean.request.BaseQueryJson;
import com.scxd.lawqinghai.bean.request.BaseWriteJson;
import com.scxd.lawqinghai.bean.request.PhotoReqBean;
import com.scxd.lawqinghai.common.Common;
import com.scxd.lawqinghai.utils.DESUtil;
import com.scxd.lawqinghai.utils.LogUtil;
import com.scxd.lawqinghai.utils.Md5Util;
import com.scxd.lawqinghai.utils.NetUtils;
import com.scxd.lawqinghai.utils.SharedPreferencesUtils;
import com.scxd.lawqinghai.utils.XstreamHelper;
import com.scxd.lawqinghai.widget.dialog.CustomPrograss;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringDialogCallback;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 类名: NetWorking
 * <br/>功能描述:
 * <br/>作者: 陈渝金
 * <br/>时间: 2017/3/9
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */


public class NetWorking {


    private static Map<String, String> mapData = null;
    private static String desJGBH = "";

    /**
     * @param tag        标识，可以通过tag来取消请求 OkHttpUtils.cancelTag(tag);//取消以Activity.this作为tag的请求
     * @param method     方法   queryObjectOut：查询，writeObjectOut：写入
     * @param jkid       接口id  ：18Q11
     * @param xmlDoc     请求的xml
     * @param myCallBack 回调，new DataCallback
     */

    public synchronized static void requstNetData(String tag, String method, String jkid, String xmlDoc, DataCallback
            myCallBack) {
        //拼接完整的url
        Log.e("xml", "xml===========\r" + xmlDoc);
        LogUtil.open().appendMethodB("request" + jkid + ":" + xmlDoc.toString());
        String url = "http://" + Common.IP + "/WebService/TmriOutAccess.asmx/" + method;
        try {
            desJGBH = DESUtil.encrypt(Common.JGBH, "9ce84a1f").toUpperCase();
        } catch (Exception e) {
            desJGBH = Common.JGBH;
        }
        //生成map
        //组装数据
        //     dwmc                单位名称
        //     yhbz                用户标识
        //     yhxm                用户姓名
        //      zdbs               终端标识
        //     QueryXmlDoc         查询条件
        mapData = new LinkedHashMap<String, String>();
        mapData.put("xtlb", "03");
        mapData.put("jkxlh", desJGBH);
        mapData.put("jkid", jkid);
        mapData.put("xmlDoc", xmlDoc);
        OkHttpUtils.post().url(url).tag(tag).params(mapData).build().execute(myCallBack);

    }

    /**
     * @param tClass     传入的具体类
     * @param method     方法   queryObjectOut：查询，writeObjectOut：写入
     * @param jkid       接口id  ：18Q11
     * @param bean       请求的xml的封装实体
     * @param myCallBack 回调，new DataCallback
     */
    /**
     * @param jkid       接口id  ：18Q11
     * @param method     方法   queryObjectOut：查询，writeObjectOut：写入
     * @param bean       请求的xml的封装实体
     * @param tClass     传入的具体类
     * @param myCallBack 回调，new DataCallback
     */
    public synchronized static void requstNetData(String jkid, String method, Object bean, Class tClass,
                                                  DataCallback myCallBack) {
        String xmlDoc = "";
        if (method.equals(Common.QUERY)) {
            xmlDoc = XstreamHelper.getXMLString(XstreamHelper.METHOD_QUERY, tClass, bean);
        } else {
            xmlDoc = XstreamHelper.getXMLString(XstreamHelper.METHOD_WRITE, tClass, bean);
        }
        Log.e("xml", "xml===========\r" + xmlDoc);
        LogUtil.open().appendMethodB("request" + jkid + ":" + xmlDoc.toString());
        //拼接完整的url
        String url = "http://" + Common.IP + "/Web/ICS_Service";

        try {
            desJGBH = DESUtil.encrypt(Common.JGBH, "9ce84a1f").toUpperCase();
        } catch (Exception e) {
            desJGBH = Common.JGBH;
        }
        //生成map
        //组装数据
        //     dwjgdm              单位机构代码
        //     dwmc                单位名称
        //     yhbz                用户标识
        //     yhxm                用户姓名
        //      zdbs               终端标识
        //     QueryXmlDoc         查询条件
        mapData = new LinkedHashMap<String, String>();
        mapData.put("xtlb", "03");
        mapData.put("jkxlh", desJGBH);
        mapData.put("jkid", jkid);
        mapData.put("xmlDoc", xmlDoc);

        OkHttpUtils.post().url(url).tag(jkid).params(mapData).build().execute(myCallBack);
    }


    public synchronized static void requstJsonNetData(String jkid, String method, Object bean, DataCallback myCallBack) {
        String url = "http://" + Common.IP + "/Web/ICS_Service";
        String json = new Gson().toJson(bean);
        String time = String.valueOf(Calendar.getInstance().getTimeInMillis());
        //
        LogUtil.open().appendMethodB("request" + jkid + ":" + json);
        try {
            desJGBH = Md5Util.md5(Common.PDA_IMEI + "scxdics" + time);
        } catch (Exception e) {
            desJGBH = "";
        }
        //拼接完整的url
        if (method.equals(Common.WRITE)) {
            BaseWriteJson queryJson = new BaseWriteJson();
            queryJson.setWym(Common.PDA_IMEI);
            queryJson.setSjc(time);
            queryJson.setJkid(jkid);

            queryJson.setWriteDoc(json);
            queryJson.setJkxlh(desJGBH);

            queryJson.setUser(Common.user);


            OkHttpUtils.postString().url(url).content(new Gson().toJson(queryJson))//这里要json
                    .mediaType(MediaType.parse("application/json; charset=utf-8")).build().execute(myCallBack);
        } else {
            BaseQueryJson queryJson = new BaseQueryJson();
            queryJson.setWym(Common.PDA_IMEI);
            queryJson.setSjc(time);
            queryJson.setJkid(jkid);
            queryJson.setUser(Common.user);
            queryJson.setQueryDoc(json);
            queryJson.setJkxlh(desJGBH);
            OkHttpUtils.postString().url(url).content(new Gson().toJson(queryJson))//这里要json
                    .mediaType(MediaType.parse("application/json; charset=utf-8")).build().execute(myCallBack);
        }
    }

    public synchronized static void requstJsonFileNetData(final Context context, String url, Map<String, String> 
            params, List<String> beans, StringDialogCallback myCallBack) {
        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Disposition", "form-data;filename=enctype");
        for (int i = 0; i < beans.size(); i++) {
//            if (NetUtils.isWifiConnected(context)) {
                File file = new File(beans.get(i));
                if (!file.exists()) {
                    return;
                }
                PostFormBuilder builder = OkHttpUtils.post();
                builder.url("http://" + Common.IP + "/Web/" + url);
                String filename = file.getName();
                builder.headers(headers)
                        .params(params)
                        .addFile("file" , filename, file)
                        .build()
                        .connTimeOut(50000)
                        .readTimeOut(50000)
                        .writeTimeOut(50000)
                        .execute(myCallBack);
//            }
        }
        
       
    }
}
