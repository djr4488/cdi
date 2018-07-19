/**
 * Copyright 11-9-2017 Danny Rucker

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
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
