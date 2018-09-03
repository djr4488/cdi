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
package org.djr.cdi.logs;

import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoWeld
@AddBeanClasses({LoggerProducer.class})
public class LogProducerTest {
    @Inject
    @Slf4jLogger
    private Logger log;

    @Test
    public void testLoggerNotNull() {
        assertNotNull(log);
    }

    @Test
    public void testLogging() {
        log.info("Test");
    }
}
