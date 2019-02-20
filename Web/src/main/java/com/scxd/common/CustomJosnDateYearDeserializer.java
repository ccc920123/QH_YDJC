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
 * @Auther:陈攀
 * @Description:
 * @Date:Created in 16:34 2018/9/26
 * @Modified By:
 * json反序列化
 * 用法：且在字段的setter上加上注解
 *  @JsonDeserialize(using = CustomJosnDateYearDeserializer.class)
 */
public class CustomJosnDateYearDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
