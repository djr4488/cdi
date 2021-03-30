package org.djr.cdi.converter.json;

import org.djr.cdi.logs.LoggerProducer;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableAutoWeld
@AddBeanClasses({ LoggerProducer.class, JsonObjectConverter.class })
public class JsonObjectConverterTest {
    @Inject
    private JsonObjectConverter converter;

    @Test
    public void testToJson() {
        String json = converter.toJson(new TestRequest("test property1", "test_property2", "testProperty3"));
        assertEquals("", json);
    }
}
