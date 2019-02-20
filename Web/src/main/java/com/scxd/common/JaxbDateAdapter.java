package com.scxd.common;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @XmlJavaTypeAdapter(JaxbDateAdapter.class) public Date getCsrq() {
 * return csrq;
 * }
 */
public class JaxbDateAdapter extends XmlAdapter<String, Date> {
    static final String STANDARM_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static final String STANDARM_DATE_FORMAT_DAY = "yyyy-MM-dd";

    @Override
    public Date unmarshal(String v) throws Exception {
        Date date = null;
        if (StringUtil.isEmpty(v) || "-".equals(v)) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(STANDARM_DATE_FORMAT);
        try {
            date = format.parse(v);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (date == null) {
            format = new SimpleDateFormat(STANDARM_DATE_FORMAT_DAY);
            try {
                date = format.parse(v);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return date;
    }

    @Override
    public String marshal(Date v) throws Exception {
        DateFormat format = new SimpleDateFormat(STANDARM_DATE_FORMAT);
        return format.format(v);
    }
}
