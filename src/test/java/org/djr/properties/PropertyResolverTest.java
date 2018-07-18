package org.djr.properties;

import org.djr.cdi.properties.Config;
import org.djr.cdi.properties.PropertyResolver;
import org.djr.cdi.properties.database.DatabasePropertiesLoader;
import org.djr.cdi.properties.database.DatabasePropertyRetrievalService;
import org.djr.cdi.properties.environment.EnvironmentPropertiesLoader;
import org.djr.cdi.properties.file.FilePropertiesLoader;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses({ PropertyResolver.class, Config.class, FilePropertiesLoader.class, EnvironmentPropertiesLoader.class,
        DatabasePropertiesLoader.class, DatabasePropertyRetrievalService.class })
public class PropertyResolverTest {
    @Inject
    @Config(defaultValue = "testDefaultPropertyValue")
    private String testProperty;

    @Inject
    @Config(propertyName = "PropertyResolverTest_testDefaultProperty", defaultValue = "testDefaultPropertyValue")
    private String testDefaultProperty;

    @Inject
    @Config(propertyName = "testBoolean", defaultValue = "FALSE")
    private Boolean testBoolean;

    @Inject
    @Config(propertyName = "testInteger", defaultValue = "1")
    private Integer testInteger;

    @Inject
    @Config(propertyName = "testLong", defaultValue = "2")
    private Long testLong;

    @Inject
    @Config(propertyName = "testByte", defaultValue = "3")
    private Byte testByte;

    @Test
    public void testPropertySetWhenInProperties() {
        assertNotNull(testProperty);
        assertEquals("testPropertyValue", testProperty);
    }

    @Test
    public void testDefaultPropertyWhenNotInPropertyConfigs() {
        assertNotNull(testDefaultProperty);
        assertEquals("testDefaultPropertyValue", testDefaultProperty);
        assertFalse(testBoolean);
        assertEquals(1, testInteger.intValue());
        assertEquals(2, testLong.longValue());
        assertEquals(3, testByte.byteValue());
    }
}
