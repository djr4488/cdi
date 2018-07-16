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
        for (String key : environment.keySet()) {
            prop.put(key, environment.get(key));
        }
        return prop;
    }
}
