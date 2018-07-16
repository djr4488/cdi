package org.djr.cdi.properties.database;


import org.djr.cdi.properties.PropertyLoader;
import org.djr.cdi.properties.PropertyUtils;
import org.djr.cdi.properties.database.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.List;
import java.util.Properties;

@ApplicationScoped
public class DatabasePropertiesLoader implements PropertyLoader {
    private static final Logger log = LoggerFactory.getLogger(DatabasePropertiesLoader.class);
    @Inject
    private DatabasePropertyRetrievalService databasePropertyRetrievalService;
    @Inject
    private PropertyUtils propertyUtils;

    @Produces
    @DatabaseProperties
    public Properties getProperties() {
        List<PropertyModel> propertyModels = databasePropertyRetrievalService.loadProperties(propertyUtils.applicationName());
        Properties prop = null;
        if (null != propertyModels && 0 < propertyModels.size()) {
            prop = new Properties();
            for (PropertyModel propertyModel : propertyModels) {
                prop.setProperty(propertyModel.getPropertyName(), propertyModel.getPropertyValue());
            }
        }
        return prop;
    }
}

