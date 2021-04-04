/**
 * Copyright 2017 - 2018 Danny Rucker

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
package org.djr.cdi.properties.environment;

import org.djr.cdi.properties.PropertyLoader;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Map;
import java.util.Properties;

@ApplicationScoped
public class EnvironmentPropertiesLoader implements PropertyLoader {
    @Inject
    private SystemEnvironment systemEnvironment;

    @Produces
    @EnvironmentProperties
    public Properties getProperties() {
        Properties prop;
        prop = new Properties();
        Map<String, String> environment = systemEnvironment.getEnvironment();
        prop.putAll(systemEnvironment.getSystemProperties());
        for (String key : environment.keySet()) {
            prop.put(key, environment.get(key));
        }
        return prop;
    }
}
