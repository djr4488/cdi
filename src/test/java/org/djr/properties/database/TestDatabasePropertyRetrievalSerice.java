package org.djr.properties.database;

import org.djr.cdi.properties.database.DatabasePropertyRetrievalService;
import org.djr.cdi.properties.database.model.PropertyModel;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Alternative
public class TestDatabasePropertyRetrievalSerice extends DatabasePropertyRetrievalService {
    @Override
    public List<PropertyModel> loadProperties(String applicationName) {
        List<PropertyModel> propertyModels = new ArrayList<>();
        propertyModels.add(new PropertyModel(1L, new Date(), "Test-App", "Test_Property", "test"));
        return propertyModels;
    }
}
