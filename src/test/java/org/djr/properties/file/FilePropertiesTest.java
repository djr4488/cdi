package org.djr.properties.file;

import org.djr.cdi.properties.PropertyLoader;
import org.djr.cdi.properties.PropertyUtils;
import org.djr.cdi.properties.file.FileProperties;
import org.djr.cdi.properties.file.FilePropertiesLoader;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses({ FileProperties.class, PropertyLoader.class, FilePropertiesLoader.class, PropertyUtils.class })
public class FilePropertiesTest {
    @Inject
    @FileProperties
    private Properties properties;

    @Test
    public void testNotNull() {
        assertNotNull(properties);
    }

    @Test
    public void testReadProperties() {
        assertEquals("test", properties.getProperty("Test_Property"));
    }
}
