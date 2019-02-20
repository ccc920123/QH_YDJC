package com.scxd.beans.common;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.*;

public class ParseXml {
    /**
     * 给返回的xml添加头<web-app...></web-app>以便dom4j解析
     * @param xmlResult
     * @return
     */
    public synchronized String addhead(String xmlResult){
        String web = "<web-app version=\"2.5\" xmlns=\"http://java.sun.com/xml/ns/javaee\"  \n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  \n" +
                "    xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee   \n" +
                "    http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\">  \n";
        StringBuffer xml = new StringBuffer(xmlResult);
        xml.insert(xmlResult.indexOf('>') + 1,web);
        xml.append("</web-app>  ");
        return xml.toString();
    }

    /**
     * 返回list<map>便于mybatis批量插入
     */
    public synchronized List<Map<String,Object>> parseElement(Element node){
        List<Map<String,Object>> listMap = new LinkedList<>();
        try{
            //迭代node下的所有子节点
            Iterator<Element> iterator = node.elementIterator();
            //遍历node下的子节点
            while (iterator.hasNext()){
                Element eleNext = iterator.next();
                Map<String,Object> station = parseElementToMap(eleNext);
                listMap.add(station);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  listMap;
    }

    /**
     * 将element存入map
     * @param element
     * @return
     */
    private synchronized Map<String,Object> parseElementToMap(Element element){
          Map<String,Object> map = new HashMap<>();
       try{
           //遍历节点属性
          /* Iterator<Attribute> iterator = element.attributeIterator();
           while (iterator.hasNext()){
               Attribute att = iterator.next();
               map.put(att.getQName().getName(),att.getValue());
           }*/
          List<Element> elements = element.elements();
           for (Object obj : elements) {
               element = (Element) obj;
               map.put(element.getName(), element.getTextTrim());
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return map;
    }
}
