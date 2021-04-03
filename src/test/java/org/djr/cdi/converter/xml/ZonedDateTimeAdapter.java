package org.djr.cdi.converter.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    public ZonedDateTime unmarshal(String datetime) {
        return datetime != null ? ZonedDateTime.parse(datetime, formatter) : null;
    }

    public String marshal(ZonedDateTime datetime) {
        return datetime != null ? formatter.format(datetime) : null;
    }
}
