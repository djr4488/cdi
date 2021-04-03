package org.djr.cdi.converter.xml;

import org.djr.cdi.converter.TestRequest;
import org.djr.cdi.logs.LoggerProducer;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

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
            String xml = converter.toXml(new TestRequest("testProperty1", "testProperty2", "testProperty3"));
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
