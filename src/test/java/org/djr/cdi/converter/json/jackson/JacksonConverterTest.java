package org.djr.cdi.converter.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.djr.cdi.logs.LoggerProducer;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.inject.Inject;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(CdiRunner.class)
@AdditionalClasses({ JsonConverter.class, ObjectMapperProducer.class, LoggerProducer.class })
public class JacksonConverterTest {
    @Inject
    private Logger log;
    @Inject
    private JsonConverter jsonConverter;

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
}
