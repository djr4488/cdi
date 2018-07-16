package org.djr.cdi.properties;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class PropertyUtils {
    @Resource(lookup="java:app/AppName")
    private String appName;

    public String applicationName() {
        String applicationName = "app";
        if (null != appName) {
            Pattern appNamePattern = Pattern.compile("\\-\\d+");
            Matcher appNameMatcher = appNamePattern.matcher(appName);
            appNameMatcher.find();
            int indexOfFirstDash = appNameMatcher.start();
            applicationName = appName;
            if (indexOfFirstDash != -1) {
                applicationName = appName.substring(0, indexOfFirstDash);
            }
        }
        return applicationName;
    }
}
