package com.scxd.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.szdt.security.des.DESUtil;

import java.io.IOException;
/**
 * json序列化des解密
 * 用法：然后在你的POJO上找到日期的get方法
 *
 * @JsonSerialize(using = CustomDesSerializer.class)
 * public Date getCreateAt() {
 * return createAt;
 * }
 */
public class CustomDesSerializer extends JsonSerializer<String> {


    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (value == null) {
            jsonGenerator.writeString("");
        } else {

            try {
                DESUtil desUtil =new DESUtil("ics");
                value=desUtil.decrypt(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonGenerator.writeString(value);
        }
    }
}
