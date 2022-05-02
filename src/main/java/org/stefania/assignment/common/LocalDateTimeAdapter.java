package org.stefania.assignment.common;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String s) {
        return LocalDateTime.parse(s, ISO_DATE_TIME);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) {
        return localDateTime.format(ISO_DATE_TIME);
    }
}
