package org.djr.properties.environment;

import org.djr.cdi.properties.environment.SystemEnvironment;

import javax.enterprise.inject.Alternative;
import java.util.HashMap;
import java.util.Map;

@Alternative
public class TestSystemEnvironment extends SystemEnvironment {
    public Map<String,String> getEnvironment() {
        Map<String,String> envMap = new HashMap<>();
        envMap.put("Test_Environment", "test");
        return envMap;
    }
}
