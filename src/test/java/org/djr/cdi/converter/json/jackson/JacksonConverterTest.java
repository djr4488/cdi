package org.djr.cdi.converter.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.djr.cdi.converter.json.TestRequest;
import org.djr.cdi.logs.LoggerProducer;
import org.djr.cdi.logs.Slf4jLogger;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import javax.inject.Inject;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@EnableAutoWeld
@AddBeanClasses({ JsonConverter.class, ObjectMapperProducer.class, LoggerProducer.class } )
public class JacksonConverterTest {
    @Inject
    @Slf4jLogger
    private Logger log;
    @Inject
    private JsonConverter jsonConverter;
    @Inject
    private ObjectMapperProducer objectMapperProducer;

    @Test
    public void testInjections() {
        assertNotNull(log);
        assertNotNull(jsonConverter);
        assertNotNull(objectMapperProducer);
    }

    @Test
    public void testConverter() {
        TestRequest tr = new TestRequest("test1", "test2", "test3");
        try {
            String json = jsonConverter.toJsonString(tr);
            log.info("testConverter() json:{}", json);
            assertEquals("{\"testProperty3\":\"test3\",\"test property\":\"test1\",\"test_property2\":\"test2\"}".trim(), json);
            TestRequest fromJson = jsonConverter.toObjectFromString(json, TestRequest.class);
            assertEquals(tr.getTestProperty(), fromJson.getTestProperty());
            assertEquals(tr.getTestProperty2(), fromJson.getTestProperty2());
            assertEquals(tr.getTestProperty3(), fromJson.getTestProperty3());
        } catch (JsonProcessingException jpEx) {
            log.error("Failed with JsonProcessingException: ", jpEx);
            fail("Failed with JsonProcessingException");
        } catch (IOException ioEx) {
            log.error("Failed with IOException: ", ioEx);
            fail("Failed with IOException");
        }
    }

    @Test
    public void testToObjectFromJsonNodeAndBackAgain() {
        try {
            TestRequest tr = new TestRequest("test1", "test2", "test3");
            JsonNode jsonNode = jsonConverter.toJsonNodeFromObject(tr);
            assertTrue(jsonNode.has("test property"));
            assertTrue(jsonNode.has("test_property2"));
            assertTrue(jsonNode.has("testProperty3"));
            TestRequest result = jsonConverter.toObjectFromJsonNode(jsonNode, TestRequest.class);
            assertEquals(tr.getTestProperty(), result.getTestProperty());
            assertEquals(tr.getTestProperty2(), result.getTestProperty2());
            assertEquals(tr.getTestProperty3(), result.getTestProperty3());
        } catch (IOException ioEx) {
            log.error("failed", ioEx);
            fail("Failed");
        }
    }
}
