package org.djr.cdi.properties.database;

import org.djr.cdi.properties.database.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DatabasePropertyRetrievalService {
    public static final Logger log = LoggerFactory.getLogger(DatabasePropertyRetrievalService.class);
    @PersistenceContext(unitName = "property_model_persistence_unit")
    private EntityManager em;

    public List<PropertyModel> loadProperties(String applicationName) {
        TypedQuery<PropertyModel> propertyModelQuery = em.createNamedQuery("propertyModel.loadAllPropertiesByApplicationName", PropertyModel.class);
        propertyModelQuery.setParameter("applicationName", applicationName);
        return propertyModelQuery.getResultList();
    }
}
