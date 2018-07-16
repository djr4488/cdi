package org.djr.properties.database;

import org.djr.cdi.properties.PropertyUtils;
import org.djr.cdi.properties.database.DatabaseProperties;
import org.djr.cdi.properties.database.DatabasePropertiesLoader;
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
@AdditionalClasses({DatabaseProperties.class, DatabasePropertiesLoader.class, PropertyUtils.class})
@ActivatedAlternatives({TestDatabasePropertyRetrievalSerice.class})
public class DatabasePropertiesTest {
    @Inject
    @DatabaseProperties
    private Properties properties;

    @Test
    public void testNotNull() {
        assertNotNull(properties);
    }

    @Test
    public void testRetrieveProperty() {
        assertEquals("test", properties.getProperty("Test_Property"));
    }
}
