package org.djr.cdi.converter.xml;

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
@AddBeanClasses({ LoggerProducer.class, XmlObjectConverter.class })
public class XmlObjectConverterTest {
    @Inject
    private XmlObjectConverter converter;
    private static final String FILE = "/conversion/xml.xml";

    @Test
    public void testToXml() {
        String expected = getResourceFromFile(FILE);
        try {
            LocalDate localDate = LocalDate.now().withYear(2021).withMonth(4).withDayOfMonth(1);
            LocalDateTime localDateTime = localDate.atStartOfDay();
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("America/Chicago"));
            TestChild testChild = new TestChild(localDate, localDateTime, zonedDateTime, zonedDateTime);
            String xml = converter.toXml(new TestRequest("testProperty1", "testProperty2", "testProperty3", testChild));
            assertEquals(expected, xml);
        } catch (Exception ex) {
            fail("unexpected exception", ex);
        }

    }

    @Test
    public void testFromXml() {
        try {
            String xml = getResourceFromFile(FILE);
            TestRequest test = converter.fromXml(xml, TestRequest.class);
            assertNotNull(test);
            assertEquals("testProperty1", test.getTestProperty());
            assertEquals("testProperty2", test.getTestProperty2());
            assertEquals("testProperty3", test.getTestProperty3());
            TestRequest test2 = converter.fromXml(xml, TestRequest.class);
            assertNotNull(test2);
            assertEquals("testProperty1", test2.getTestProperty());
            assertEquals("testProperty2", test2.getTestProperty2());
            assertEquals("testProperty3", test2.getTestProperty3());
        } catch (Exception ex) {
            fail("unexpected exception", ex);
        }
    }
}
