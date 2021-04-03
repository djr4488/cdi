package org.djr.cdi.converter;

import org.djr.cdi.converter.xml.LocalDateAdapter;
import org.djr.cdi.converter.xml.LocalDateTimeAdapter;
import org.djr.cdi.converter.xml.ZonedDateTimeAdapter;
import org.djr.cdi.converter.xml.ZonedOffsetDateTimeAdapter;

import javax.json.bind.annotation.JsonbProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class TestChild {
    @JsonbProperty
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class, type = LocalDate.class)
    private LocalDate localDate;
    @JsonbProperty
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class, type = LocalDateTime.class)
    private LocalDateTime localDateTime;
    @JsonbProperty
    @XmlElement
    @XmlJavaTypeAdapter(value = ZonedDateTimeAdapter.class, type = ZonedDateTime.class)
    private ZonedDateTime zonedDateTime;
    @JsonbProperty
    @XmlElement
    @XmlJavaTypeAdapter(value = ZonedOffsetDateTimeAdapter.class, type = ZonedDateTime.class)
    private ZonedDateTime offsetDateTime;

    public TestChild() {}

    public TestChild(LocalDate localDate, LocalDateTime localDateTime, ZonedDateTime zonedDateTime,
                     ZonedDateTime offsetDateTime) {
        this.localDate = localDate;
        this.localDateTime = localDateTime;
        this.zonedDateTime = zonedDateTime;
        this.offsetDateTime = offsetDateTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public ZonedDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(ZonedDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }
}
