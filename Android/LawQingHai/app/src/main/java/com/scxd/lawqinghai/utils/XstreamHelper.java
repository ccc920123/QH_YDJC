/**
 * @�ļ�����XstreamHelper.java
 * @author Administrator
 * @�������� 2016-8-3
 * @����������
 */
package com.scxd.lawqinghai.utils;

import com.scxd.lawqinghai.bean.BaseRequestMsgVO;
import com.scxd.lawqinghai.bean.BaseRequestMsgVoWrite;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.util.Iterator;
import java.util.Map;

/**类名: XstreamHelper
 * <br/>功能描述:帮助生成xml
 * <br/>作者: 陈渝金
 * <br/>时间: 2017/5/22
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class XstreamHelper {
    /**
     * 查讯
     */
    public static final String METHOD_QUERY = "QueryCondition";
    /**
     * 写入
     */
    public static final String METHOD_WRITE = "vioSurveil";

    /**
     * @方法名称: getXMLString
     * @方法详述: 得到xml字符串
     * @参数: null
     * @返回值: String
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public static <T> String getXMLString(String method, Class<T> cls, Object object) {
        XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
        if (method.equals(METHOD_QUERY)) {
            BaseRequestMsgVO root = new BaseRequestMsgVO((T) object);

            xstream.alias("root", BaseRequestMsgVO.class);// 更改节点明
            xstream.aliasSystemAttribute(null, "class");// 去掉class属性
            xstream.alias(method, cls);
            String xmlhead = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

            return xmlhead + xstream.toXML(root);
        } else {
            BaseRequestMsgVoWrite root = new BaseRequestMsgVoWrite((T) object);

            xstream.alias("root", BaseRequestMsgVoWrite.class);// 更改节点明
            xstream.aliasSystemAttribute(null, "class");// 去掉class属性
            xstream.alias(method, cls);
            String xmlhead = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

            return xmlhead + xstream.toXML(root);
        }

    }

    /**
     * @方法名称: getNOTXml
     * @方法详述:xml的头一行
     * @参数: null
     * @返回值: String
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public static String getNOTXml() {

        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><root><QueryCondition></QueryCondition></root>";

        return xml;
    }

    /**
     * @param
     * @return
     * @throws NullPointerException
     * @功能描述： map转xml.将map的key作为标签，value作为值转换成xml
     */
    public static String encodeToXml(Map<String, Object> values, String tag) throws Exception {

        StringBuilder mapXmlStrBuilder = new StringBuilder();
        mapXmlStrBuilder.append("<?xml version=\"1.0\" encoding=\"GBK\"?><root>" + "<" + tag + ">");
        encodeMapToXML(mapXmlStrBuilder, values);
        mapXmlStrBuilder.append("</" + tag + ">" + "</root>\n");
        return mapXmlStrBuilder.toString();
    }


    /**
     * 组装xml
     *
     * @param values
     */
    private static void encodeMapToXML(StringBuilder xmlStrBulder, Map<String, Object> values) throws Exception {
        Iterator<Map.Entry<String, Object>> it = values.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();

            String node = entry.getKey();
            Object value = entry.getValue();
            xmlStrBulder.append("<").append(node).append(">");

            if (value instanceof Map) {
                encodeMapToXML(xmlStrBulder, (Map<String, Object>) value);
            } else {
                xmlStrBulder.append(value);
            }

            xmlStrBulder.append("</").append(node).append(">").append("\n");
        }
    }


}
