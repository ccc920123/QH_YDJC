package com.scxd.common;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxbStringAdapter extends XmlAdapter<String, String> {
    @Override
    public String unmarshal(String v) throws Exception {
        if (StringUtil.isEmpty(v) || "-".equals(v.trim())) {
            return "";
        }

        return v;
    }

    @Override
    public String marshal(String v) throws Exception {
        return v;
    }
}
