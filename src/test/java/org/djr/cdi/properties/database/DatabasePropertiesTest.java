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
package org.djr.cdi.properties.database;

import org.djr.cdi.properties.PropertyUtils;
import org.djr.cdi.properties.database.model.PropertyModel;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Date;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnableAutoWeld
@AddBeanClasses({DatabaseProperties.class, DatabasePropertiesLoader.class, PropertyUtils.class, EntityManagerProducer.class,
                 TestDatabasePropertyRetrievalService.class})
@EnableAlternatives({TestDatabasePropertyRetrievalService.class})
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

    @Test
    public void testPropertyModel() {
        PropertyModel propertyModel = new PropertyModel();
        Date testing = DateTime.now()
                           .withTimeAtStartOfDay()
                           .withMonthOfYear(DateTimeConstants.JANUARY)
                           .withDayOfMonth(1).withYear(2018)
                           .withZone(DateTimeZone.UTC)
                           .toDate();
        propertyModel.setId(1L);
        propertyModel.setApplicationName("app");
        propertyModel.setLastUpdated(testing);
        propertyModel.setPropertyName("propName");
        propertyModel.setPropertyValue("propValue");
        propertyModel.setVersion(1L);
        assertEquals(1L, propertyModel.getId().longValue());
        assertEquals(1L, propertyModel.getVersion().longValue());
        assertEquals("app", propertyModel.getApplicationName());
        assertEquals("propName", propertyModel.getPropertyName());
        assertEquals("propValue", propertyModel.getPropertyValue());
        assertEquals(testing.getTime(), propertyModel.getLastUpdated().getTime());
        assertTrue(propertyModel.toString().contains("PropertyModel[id=1,version=1,"));
    }
}
