package org.djr.cdi.converter.xml.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public LocalDate unmarshal(String date) {
        return date != null ? LocalDate.parse(date, formatter) : null;
    }

    public String marshal(LocalDate date) {
        return date != null ? formatter.format(date) : null;
    }
}
