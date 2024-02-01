package com.solvd.university.parsers;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyAdapter extends XmlAdapter<String, Date> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Date unmarshal(String s) throws Exception {
        if (s == null || s.isEmpty()) {
            return null;
        }
        java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.parse(s, formatter));
        return sqlDate;
    }


    @Override
    public String marshal(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        return formatter.format(date.toLocalDate());
    }

}


