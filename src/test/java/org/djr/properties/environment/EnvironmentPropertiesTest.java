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
