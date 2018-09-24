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

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Inject
    @Config
    private String noDefaults;

    @Inject
    @Config(propertyName = "PropertyResolverTest_stringMap", defaultValue = "test1|value1,test2|value2")
    private Map<String, String> stringMap;

    @Inject
    @Config(propertyName = "PropertyResolverTest_intMap", defaultValue = "test1|1,test2|2")
    private Map<String, Integer> intMap;

    @Inject
    @Config(propertyName = "PropertyResolverTest_longMap", defaultValue = "test1|1,test2|2")
    private Map<String, Long> longMap;

    @Inject
    @Config(propertyName = "PropertyResolverTest_byteMap", defaultValue = "test1|1,test2|2")
    private Map<String, Byte> byteMap;

    @Inject
    @Config(propertyName = "PropertyResolverTest_booleanMap", defaultValue = "test1|true,test2|false")
    private Map<String, Boolean> booleanMap;

    @Inject
    @Config(propertyName = "PropertyResolverTest_floatMap", defaultValue = "test1|1.0,test2|2.0")
    private Map<String, Float> floatMap;

    @Inject
    @Config(propertyName = "PropertyResolverTest_doubleMap", defaultValue = "test1|1.0,test2|2.0")
    private Map<String, Double> doubleMap;

    @Inject
    @Config(propertyName = "PropertyResolverTest_stringList", defaultValue = "test1|test2")
    private List<String> stringList;

    @Inject
    @Config(propertyName = "PropertyResolverTest_intList", defaultValue = "1|2")
    private List<Integer> intList;

    @Inject
    @Config(propertyName = "PropertyResolverTest_longList", defaultValue = "1|2")
    private List<Long> longList;

    @Inject
    @Config(propertyName = "PropertyResolverTest_byteList", defaultValue = "1|2")
    private List<Byte> byteList;

    @Inject
    @Config(propertyName = "PropertyResolverTest_booleanList", defaultValue = "true|false")
    private List<Boolean> booleanList;

    @Inject
    @Config(propertyName = "PropertyResolverTest_floatList", defaultValue = "1.0|2")
    private List<Float> floatList;

    @Inject
    @Config(propertyName = "PropertyResolverTest_doubleList", defaultValue = "1.1|2.0")
    private List<Double> doubleList;

    @Inject
    @Config(propertyName = "PropertyResolverTest_floatVal", defaultValue = "1.1")
    private Float floatTest;

    @Inject
    @Config(propertyName = "PropertyResolverTest_doubleVal", defaultValue = "1.2")
    private Double doubleTest;

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
        assertEquals("noDefaults", noDefaults);
        assertNotNull(stringMap);
        assertEquals("value1", stringMap.get("test1"));
        assertEquals("value2", stringMap.get("test2"));
        assertNotNull(intMap);
        assertEquals(1, intMap.get("test1").intValue());
        assertNotNull(longMap);
        assertEquals(1L, longMap.get("test1").longValue());
        assertNotNull(byteMap);
        assertEquals(1, byteMap.get("test1").byteValue());
        assertNotNull(booleanMap);
        assertTrue(booleanMap.get("test1"));
        assertFalse(booleanMap.get("test2"));
        assertNotNull(floatMap);
        assertEquals(1.0f, floatMap.get("test1").floatValue());
        assertNotNull(doubleMap);
        assertEquals(1.0d, doubleMap.get("test1").doubleValue());
        assertNotNull(stringList);
        assertEquals("test1", stringList.get(0));
        assertEquals("test2", stringList.get(1));
        assertNotNull(intList);
        assertEquals(1, intList.get(0).intValue());
        assertEquals(2, intList.get(1).intValue());
        assertNotNull(longList);
        assertEquals(1L, longList.get(0).longValue());
        assertNotNull(byteList);
        assertEquals(1, byteList.get(0).byteValue());
        assertNotNull(booleanList);
        assertEquals(true, booleanList.get(0));
        assertNotNull(floatList);
        assertEquals(1.0f, floatList.get(0).floatValue());
        assertNotNull(doubleList);
        assertEquals(1.1d, doubleList.get(0).doubleValue());
        assertEquals(1.1f, floatTest.floatValue());
        assertEquals(1.2d, doubleTest.doubleValue());
    }
}
