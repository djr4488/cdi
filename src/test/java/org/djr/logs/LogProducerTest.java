package org.djr.logs;

import org.djr.cdi.logs.LoggerProducer;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses({LoggerProducer.class})
public class LogProducerTest {
    @Inject
    private Logger log;

    @Test
    public void testLoggerNotNull() {
        assertNotNull(log);
    }

    @Test
    public void testLogging() {
        log.info("Test");
        assertEquals("LogProducerTest", log.getName());
    }
}
