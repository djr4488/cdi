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
package org.djr.cdi.properties.file;

import org.djr.cdi.properties.PropertyLoader;
import org.djr.cdi.properties.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class FilePropertiesLoader implements PropertyLoader {
    private static final Logger log = LoggerFactory.getLogger(FilePropertiesLoader.class);
    @Inject
    private PropertyUtils propertyUtils;

    @Produces
    @FileProperties
    public Properties getProperties() {
        String propertyFileName = propertyUtils.applicationName() + ".properties";
        Properties prop;
        prop = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream(propertyFileName);
        try {
            prop.load(in);
            in.close();
        } catch (IOException | NullPointerException ex) {
            log.warn("getProperties() failed to load properties for propertyFileName:{}", propertyFileName, ex);
        }
        return prop;
    }
}
