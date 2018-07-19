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

import org.djr.cdi.properties.database.DatabaseProperties;
import org.djr.cdi.properties.environment.EnvironmentProperties;
import org.djr.cdi.properties.file.FileProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.Properties;

@ApplicationScoped
public class PropertyResolver {
    @Inject
    @FileProperties
    private Properties fileProperties;
    @Inject
    @EnvironmentProperties
    private Properties environmentProperties;
    @Inject
    @DatabaseProperties
    private Properties databaseProperties;

    public String getProperty(String defaultPropertyName, InjectionPoint injectionPoint) {
        Config config = injectionPoint.getAnnotated().getAnnotation(Config.class);
        String configPropertyName = config.propertyName();
        String defaultPropertyValue = config.defaultValue();
        if (!isConfigPropertyProvided(configPropertyName)) {
            configPropertyName = defaultPropertyName;
        }
        String propertyValue = null;
        if (fileProperties.stringPropertyNames().contains(configPropertyName)) {
            propertyValue = fileProperties.getProperty(configPropertyName);
        } else if (environmentProperties.stringPropertyNames().contains(configPropertyName)) {
            propertyValue = environmentProperties.getProperty(configPropertyName);
        } else if (databaseProperties.stringPropertyNames().contains(configPropertyName)) {
            propertyValue = databaseProperties.getProperty(configPropertyName);
        } else {
            propertyValue = defaultPropertyValue;
        }
        return propertyValue;
    }

    @Produces
    @Dependent
    @Config
    public String injectString(InjectionPoint injectionPoint) {
        return getProperty(getPropertyName(injectionPoint), injectionPoint);
    }

    @Produces
    @Dependent
    @Config
    public Boolean injectBoolean(InjectionPoint injectionPoint) {
        return Boolean.parseBoolean(getProperty(getPropertyName(injectionPoint), injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public Integer injectInteger(InjectionPoint injectionPoint) {
        return Integer.parseInt(getProperty(getPropertyName(injectionPoint), injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public Long injectLong(InjectionPoint injectionPoint) {
        return Long.parseLong(getProperty(getPropertyName(injectionPoint), injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public Byte injectByte(InjectionPoint injectionPoint) {
        return Byte.parseByte(getProperty(getPropertyName(injectionPoint), injectionPoint));
    }

    private String getPropertyName(InjectionPoint injectionPoint) {
        String className = injectionPoint.getMember().getDeclaringClass().getSimpleName();
        String fieldName = injectionPoint.getMember().getName();
        return className + "_" + fieldName;
    }

    private boolean isConfigPropertyProvided(String configProperty) {
        boolean isProvided = true;
        if (null == configProperty || !(configProperty.trim().length() > 0)) {
            isProvided = false;
        }
        return isProvided;
    }
}
