package org.djr.cdi.converter.json;

import org.djr.cdi.converter.TestRequest;
import org.djr.cdi.logs.LoggerProducer;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.djr.cdi.converter.LoadResourceUtil.getResourceFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoWeld
@AddBeanClasses({ LoggerProducer.class, JsonObjectConverter.class })
public class JsonObjectConverterTest {
    @Inject
    private JsonObjectConverter converter;
    private static final String EXPECTED_JSON = "{\"test property1\":\"test property1\",\"testProperty3\":\"testProperty3\",\"test_property2\":\"test_property2\"}";
    private static final String JSON_FILE = "/conversion/json.json";

    @Test
    public void testToJson() {
        String json = converter.toJson(new TestRequest("test property1", "test_property2", "testProperty3"));
        assertEquals(EXPECTED_JSON, json);
    }

    @Test
    public void testFromJson() {
        final TestRequest testRequest = converter.fromJson(getResourceFromFile(JSON_FILE), TestRequest.class);
        assertNotNull(testRequest);
        assertEquals("testProperty1", testRequest.getTestProperty());
        assertEquals("testProperty2", testRequest.getTestProperty2());
        assertEquals("testProperty3", testRequest.getTestProperty3());
    }
}
