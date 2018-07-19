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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DatabasePropertyRetrievalService {
    public static final Logger log = LoggerFactory.getLogger(DatabasePropertyRetrievalService.class);
    @Inject
    @DatabasePropertyEM
    private EntityManager em;

    public List<PropertyModel> loadProperties(String applicationName) {
        if (null != em) {
            TypedQuery<PropertyModel> propertyModelQuery = em.createNamedQuery("propertyModel.loadAllPropertiesByApplicationName", PropertyModel.class);
            propertyModelQuery.setParameter("applicationName", applicationName);
            return propertyModelQuery.getResultList();
        } else {
            log.debug("loadProperties() database property loader not setup for property management");
            return null;
        }
    }
}
