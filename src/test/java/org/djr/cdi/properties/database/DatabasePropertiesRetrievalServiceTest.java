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

import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.djr.cdi.properties.database.model.PropertyModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DatabasePropertiesRetrievalServiceTest {
    @Mock
    @Produces
    @DatabasePropertyEM
    private EntityManager entityManager;
    @Mock
    private TypedQuery<PropertyModel> propertyModelQuery;
    @InjectMocks
    private DatabasePropertyRetrievalService databasePropertyRetrievalService = new DatabasePropertyRetrievalService();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(databasePropertyRetrievalService);
    }

    @Test
    public void testWhenDatabasePropertiesFound() {
        List<PropertyModel> propertyModels = Arrays.asList(new PropertyModel(1L,1L, new Date(), "app", "DatabasePropertyRetrievalServiceTest", "testProperty"));
        when(entityManager.createNamedQuery("propertyModel.loadAllPropertiesByApplicationName", PropertyModel.class)).thenReturn(propertyModelQuery);
        when(propertyModelQuery.getResultList()).thenReturn(propertyModels);
        List<PropertyModel> results = databasePropertyRetrievalService.loadProperties("app");
        assertEquals(1L, results.size());
        assertEquals("testProperty", results.get(0).getPropertyValue());
    }
}
