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
package org.djr.properties.environment;

import org.djr.cdi.properties.PropertyUtils;
import org.djr.cdi.properties.environment.EnvironmentProperties;
import org.djr.cdi.properties.environment.EnvironmentPropertiesLoader;
import org.jglue.cdiunit.ActivatedAlternatives;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses({EnvironmentProperties.class, EnvironmentPropertiesLoader.class, PropertyUtils.class})
@ActivatedAlternatives({TestSystemEnvironment.class})
public class EnvironmentPropertiesTest {
    @Inject
    @EnvironmentProperties
    private Properties properties;

    @Test
    public void testPropertiesNotNull() {
        assertNotNull(properties);
    }

    @Test
    public void testRetrieveProperty() {
        assertEquals("test", properties.getProperty("Test_Environment"));
    }
}
