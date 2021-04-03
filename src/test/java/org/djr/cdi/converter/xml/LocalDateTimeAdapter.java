package org.djr.cdi.converter.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public LocalDateTime unmarshal(String datetime) {
        return datetime != null ? LocalDateTime.parse(datetime, formatter) : null;
    }

    public String marshal(LocalDateTime datetime) {
        return datetime != null ? formatter.format(datetime) : null;
    }
}
