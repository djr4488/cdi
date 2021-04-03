package org.djr.cdi.converter.json;

import org.djr.cdi.converter.TestChild;
import org.djr.cdi.converter.TestRequest;
import org.djr.cdi.logs.LoggerProducer;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.djr.cdi.converter.LoadResourceUtil.getResourceFromFile;
import static org.junit.jupiter.api.Assertions.*;

@EnableAutoWeld
@AddBeanClasses({ LoggerProducer.class, JsonObjectConverter.class })
public class JsonObjectConverterTest {
    @Inject
    private JsonObjectConverter converter;
    private static final String EXPECTED_JSON = "{\"test property1\":\"test property1\",\"testChild\":{\"localDate\":\"2021-04-01\",\"localDateTime\":\"2021-04-01T00:00:00\",\"offsetDateTime\":\"2021-04-01T00:00:00-05:00[America/Chicago]\",\"zonedDateTime\":\"2021-04-01T00:00:00-05:00[America/Chicago]\"},\"testProperty3\":\"testProperty3\",\"test_property2\":\"test_property2\"}";
    private static final String JSON_FILE = "/conversion/json.json";

    @Test
    public void testToJson() {
        LocalDate localDate = LocalDate.now().withYear(2021).withMonth(4).withDayOfMonth(1);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("America/Chicago"));
        TestChild testChild = new TestChild(localDate, localDateTime, zonedDateTime, zonedDateTime);
        String json = converter.toJson(new TestRequest("test property1", "test_property2", "testProperty3", testChild));
        assertEquals(EXPECTED_JSON, json);
    }

    @Test
    public void testFromJson() {
        final TestRequest testRequest = converter.fromJson(getResourceFromFile(JSON_FILE), TestRequest.class);
        assertNotNull(testRequest);
        assertEquals("test property1", testRequest.getTestProperty());
        assertEquals("test_property2", testRequest.getTestProperty2());
        assertEquals("testProperty3", testRequest.getTestProperty3());
        assertNotNull(testRequest.getTestChild());
        LocalDate localDateExpected = LocalDate.now().withYear(2021).withMonth(4).withDayOfMonth(1);
        assertTrue(localDateExpected.isEqual(testRequest.getTestChild().getLocalDate()));
        LocalDateTime localDateTimeExpected = localDateExpected.atStartOfDay();
        assertTrue(localDateTimeExpected.isEqual(testRequest.getTestChild().getLocalDateTime()));
    }
}
