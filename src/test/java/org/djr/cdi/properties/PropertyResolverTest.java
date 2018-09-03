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
package org.djr.cdi.properties;

import org.djr.cdi.logs.LoggerProducer;
import org.djr.cdi.lookup.LookupCdi;
import org.djr.cdi.properties.database.DatabasePropertyEM;
import org.djr.cdi.properties.database.EntityManagerProducer;
import org.djr.cdi.properties.decrypt.Decryptor;
import org.djr.cdi.properties.decrypt.DefaultDecryptor;
import org.djr.cdi.properties.file.FilePropertiesLoader;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoWeld
@AddBeanClasses({ PropertyResolver.class, Config.class, FilePropertiesLoader.class, EntityManagerProducer.class,
                  LoggerProducer.class, LookupCdi.class, DefaultDecryptor.class, Decryptor.class, PropertyUtils.class,
                  TestEnvironmentProducer.class, TestDatabaseProducer.class })
@EnableAlternatives({TestEnvironmentProducer.class, TestDatabaseProducer.class})
public class PropertyResolverTest {
    @Mock
    @Produces
    @DatabasePropertyEM
    private EntityManagerProducer entityManagerProducer;

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

    @Inject
    @Config(propertyName = "env_prop", defaultValue = "notEnvProp")
    private String envProp;

    @Inject
    @Config(propertyName = "db_prop", defaultValue = "notDbProp")
    private String dbProp;

    @Inject
    @Config(propertyName = "PropertyResolverTest_encryptedProperty", defaultValue="some string", isEncrypted = true)
    private String decrypted;

    @Test
    public void testPropertySetWhenInProperties() {
        assertNotNull(testProperty);
        assertEquals("testPropertyValue", testProperty);
        assertEquals("env_test", envProp);
        assertEquals("db_test", dbProp);
        assertEquals("Some String", decrypted);
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
