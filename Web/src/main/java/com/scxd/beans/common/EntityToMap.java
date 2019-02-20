package com.scxd.beans.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *功能描述：实体类转Map，用于Mybatis动态update/select/insert
 */
public class EntityToMap {

    private Map<String, Object> map = new HashMap<>();
    // 存数据库表中的字段名
    private ArrayList<String> column = new ArrayList<>();
    // 实体对象的属性装到ArrayList中
    private ArrayList<String> attribute = new ArrayList<>();
    // 属性数据类型
    private String type;
    // 属性值
    private Object val;
    // 反射出的属性名数组
    private Field[] field;

    //将实体类转化为Map,做UPDATE操作
    public Map<String,Object> getgetMapByEntityForUpdate(Object entity){
        try {
            executionSequence(entity);
            for (int i = 0; i < attribute.size(); i++) {
                //判断是不是主键,是主键则跳过这次循环
                String name = attribute.get(i);

                if (name.substring(name.length() - 2).equals("Id")) {
                    continue;
                }else {}

                map = getMapUPDATEorINSERT(entity,i);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return map;
    }
    //将实体类转化为Map,做INSERT操作
    public Map<String,Object> getMapByEntityForInsert(Object entity){
        try {
            executionSequence(entity);
            for (int i = 0; i < attribute.size(); i++) {
                map = getMapUPDATEorINSERT(entity,i);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return map;
    }
    //将实体类转换为Map,做LIKE查询
    public Map<String, Object> getMapByEntityForLIKE(Object entity) {
        try {
            executionSequence(entity);
            for (int i = 0; i < attribute.size(); i++) {
                getAttributeValue(i, entity);
                // 组装map里的键-值
                switch (type) {
                    case "class java.lang.String":
                        if (val != null && !val.equals("")) {
                            map.put(column.get(i), "%" + val + "%");
                        } else {
                        };break;
                    case "int":
                        int valInt = (int) val;
                        if (valInt != 0) {
                            map.put(column.get(i), "%" + valInt + "%");
                        } else {
                        };break;
                    case "class java.sql.Date":
                        Date valDate = (Date) val;
                        if (valDate != null) {
                            map.put(column.get(i), "%" + valDate + "%");
                        }else {
                        }break;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return map;
    }

    // 确定方法的执行顺序
    private void executionSequence(Object entity) {
        // 获取实体类属性名字
        field = entity.getClass().getDeclaredFields();
        getAttribute(field);
        // 获取数据表字段名
        getColumn(attribute);
    }

    // 将实体类的属性名存到ArrayList中
    private void getAttribute(Field[] field) {
        for (Field f : field) {
            // 先转换为字符串
            String sf = f.toString();
            // 得到最后一次出现'.'的索引
            int index = sf.lastIndexOf('.');
            // 截取字符串得到对象的属性名
            String property = sf.substring(index + 1);
            // 将属性名装到ArrayList中即obj
            attribute.add(property);
        }
    }

    // 数据库表字段名
    private void getColumn(ArrayList<String> obj) {
        for (int i = 0; i < obj.size(); i++) {
            StringBuffer name = new StringBuffer();
            String atr = obj.get(i);
            // 遍历属性名查看有无驼峰，有则变驼峰为'_';
            for (int j = 0; j < atr.length(); j++) {
                char c = atr.charAt(j);
                if (Character.isUpperCase(c)) {
                    name.append('_');
                    name.append(Character.toLowerCase(c));
                } else {
                    name.append(c);
                }
            }
            // 驼峰转换完之后存入ArrayList
            atr = name.toString();
            name.setLength(0);
            column.add(atr);
        }
    }

    // 获得属性值
    private void getAttributeValue(int i, Object entity) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 获取get方法,首先属性首字母大写
        String meth = attribute.get(i).substring(0, 1).toUpperCase() + attribute.get(i).substring(1);
        Method method = entity.getClass().getMethod("get" + meth);
        // 得到属性的类型
        type = field[i].getGenericType().toString();
        // 调用get方法获取属性值
        val = (Object) method.invoke(entity);
    }

    //组装map为UPDATE何INSERT
    private Map<String,Object> getMapUPDATEorINSERT(Object entity,int i) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

        getAttributeValue(i, entity);
        // 组装map里的键-值
        switch (type) {
            case "class java.lang.String":
                if (val != null && !val.equals("")) {
                    map.put(column.get(i),val);
                } else {
                };break;
            case "int":
                int valInt = (int) val;
                if (valInt != 0) {
                    map.put(column.get(i),valInt);
                } else {
                };break;
            case "class java.sql.Date":
                Date valDate = (Date) val;
                if (valDate != null) {
                    map.put(column.get(i),valDate);
                }else {
                }break;
            case "byte[]":
                byte[] valByte = (byte[]) val;
                if (valByte.length != 0){
                    map.put(column.get(i),valByte);
                }break;
            default:map.put(column.get(i),val);
        }
        return map;
    }
}
