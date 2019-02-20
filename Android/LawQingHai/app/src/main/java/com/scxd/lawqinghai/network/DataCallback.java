package com.scxd.lawqinghai.network;

import com.google.gson.Gson;
import com.scxd.lawqinghai.bean.response.BaseResponseJson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**类名: DataCallback
 * <br/>功能描述:  回调callback
 * <br/>作者: 陈渝金
 * <br/>时间: 2017/3/9
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */



public abstract class DataCallback extends Callback<String> {


        @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        String dataJson=getResXml(response.body().string());
//        return URLDecoder.decode(xml,"UTF-8");
        return dataJson;
    }
    /**
     * 返回数据处理
     */
    private  String getResXml(String reponseData) {
        if (!reponseData.equals("")) {
            try {
//                String temp = reponseData.replace("&lt;", "<").replace("&gt;",
//                        ">");
//                int startIndex = temp.indexOf("<root>");
//                int endIndex = temp.indexOf("</root>");
//                String Resxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
//                        + temp.substring(startIndex, endIndex + 7);
//                return Resxml;
                //该处处理成json

//                BaseResponseJson jsonObj = new Gson().fromJson(reponseData, BaseResponseJson.class);
                return reponseData;


            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
