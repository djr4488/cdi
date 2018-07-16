package org.djr.cdi.properties.environment;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class SystemEnvironment {
    public Map<String,String> getEnvironment() {
        return System.getenv();
    }
}
