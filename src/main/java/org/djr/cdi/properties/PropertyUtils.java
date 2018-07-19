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

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class PropertyUtils {
    @Resource(lookup="java:app/AppName")
    private String appName;

    public PropertyUtils() {

    }

    public PropertyUtils(String appName) {
        this.appName = appName;
    }

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
