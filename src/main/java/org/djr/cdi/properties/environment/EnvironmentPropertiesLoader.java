package org.djr.cdi.properties.environment;

import org.djr.cdi.properties.PropertyLoader;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Map;
import java.util.Properties;

@ApplicationScoped
public class EnvironmentPropertiesLoader implements PropertyLoader {
    @Produces
    @EnvironmentProperties
    public Properties getProperties() {
        Properties prop;
        prop = new Properties();
        Map<String, String> environment = System.getenv();
        for (String key : environment.keySet()) {
            prop.put(key, System.getenv(key));
        }
        return prop;
    }
}
