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

import org.djr.cdi.properties.environment.EnvironmentProperties;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import java.util.Properties;

@Alternative
public class TestEnvironmentProducer implements PropertyLoader {
    private Properties properties = new Properties();

    @Produces
    @EnvironmentProperties
    public Properties getProperties() {
        properties.put("env_prop", "env_test");
        return properties;
    }
}
