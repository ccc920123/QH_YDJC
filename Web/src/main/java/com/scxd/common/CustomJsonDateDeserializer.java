package com.scxd.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * json反序列化
 * 用法：且在字段的setter上加上注解
 @JsonDeserialize(using = CustomJsonDateDeserializer.class)
 */
public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {
  
    @Override  
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = jp.getText();
                if (StringUtil.isEmpty(date)){
                    return null;
                }
                try {  
                    return format.parse(date);  
                } catch (ParseException e) {
                    throw new RuntimeException(e);  
                }  
    }  
  
}  