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

import org.djr.cdi.properties.database.model.PropertyModel;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Alternative
public class TestDatabasePropertyRetrievalService extends DatabasePropertyRetrievalService {
    @Override
    public List<PropertyModel> loadProperties(String applicationName) {
        List<PropertyModel> propertyModels = new ArrayList<>();
        propertyModels.add(new PropertyModel(1L, 1L, new Date(), "Test-App", "Test_Property", "test"));
        return propertyModels;
    }
}
