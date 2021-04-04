/**
 * Copyright 2017 - 2021 Danny Rucker

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


import org.apache.commons.lang3.StringUtils;
import org.djr.cdi.logs.Slf4jLogger;
import org.djr.cdi.lookup.LookupCdi;
import org.djr.cdi.properties.decrypt.Decryptor;
import org.djr.cdi.properties.environment.EnvironmentProperties;
import org.djr.cdi.properties.file.FileProperties;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ApplicationScoped
public class PropertyResolver {
    @Inject
    @FileProperties
    private Properties fileProperties;
    @Inject
    @EnvironmentProperties
    private Properties environmentProperties;
    private static final String PROP_VALUE_UNDEFINED = "No property value defined for %1$s.";
    @Inject
    @Slf4jLogger
    private Logger log;
    private static final String pipe = Pattern.quote("|");

    public String getProperty(String defaultPropertyName, InjectionPoint injectionPoint) {
        Config config = injectionPoint.getAnnotated().getAnnotation(Config.class);
        String configPropertyName = config.propertyName();
        String defaultPropertyValue = getProperty(config.defaultValue(), config);
        if (!isConfigPropertyProvided(configPropertyName)) {
            configPropertyName = defaultPropertyName;
        }
        String propertyValue = null;
        if (fileProperties.stringPropertyNames().contains(configPropertyName)) {
            propertyValue = getProperty(fileProperties.getProperty(configPropertyName), config);
        } else if (environmentProperties.stringPropertyNames().contains(configPropertyName)) {
            propertyValue = getProperty(environmentProperties.getProperty(configPropertyName), config);
        } else {
            if (null != StringUtils.trimToNull(defaultPropertyValue)) {
                propertyValue = defaultPropertyValue;
            } else {
                throw new PropertyLoadException(PROP_VALUE_UNDEFINED, configPropertyName);
            }
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

    @Produces
    @Dependent
    @Config
    public Float injectFloat(InjectionPoint injectionPoint) {
        return Float.parseFloat(getProperty(getPropertyName(injectionPoint), injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public Double injectDouble(InjectionPoint injectionPoint) {
        return Double.parseDouble(getProperty(getPropertyName(injectionPoint), injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public List<String> injectStringList(InjectionPoint injectionPoint) {
        return splitStringByToken(getProperty(getPropertyName(injectionPoint), injectionPoint), pipe);
    }

    @Produces
    @Dependent
    @Config
    public List<Integer> injectIntegerList(InjectionPoint injectionPoint) {
        return convertStringListToListOfTypeR(Integer::parseInt, injectStringList(injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public List<Long> injectLongList(InjectionPoint injectionPoint) {
        return convertStringListToListOfTypeR(Long::parseLong, injectStringList(injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public List<Byte> injectByteList(InjectionPoint injectionPoint) {
        return convertStringListToListOfTypeR(Byte::parseByte, injectStringList(injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public List<Boolean> injectBooleanList(InjectionPoint injectionPoint) {
        return convertStringListToListOfTypeR(Boolean::parseBoolean, injectStringList(injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public List<Float> injectFloatList(InjectionPoint injectionPoint) {
        return convertStringListToListOfTypeR(Float::parseFloat, injectStringList(injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public List<Double> injectDoubleList(InjectionPoint injectionPoint) {
        return convertStringListToListOfTypeR(Double::parseDouble, injectStringList(injectionPoint));
    }

    @Produces
    @Dependent
    @Config
    public Map<String, String> injectStringToStringMap(InjectionPoint injectionPoint) {
        return convertStringListToMapOfTypeR(String::new, injectionPoint);
    }

    @Produces
    @Dependent
    @Config
    public Map<String, Boolean> injectStringToBooleanMap(InjectionPoint injectionPoint) {
        return convertStringListToMapOfTypeR(Boolean::parseBoolean, injectionPoint);
    }

    @Produces
    @Dependent
    @Config
    public Map<String, Integer> injectStringToIntegerMap(InjectionPoint injectionPoint) {
        return convertStringListToMapOfTypeR(Integer::parseInt, injectionPoint);
    }

    @Produces
    @Dependent
    @Config
    public Map<String, Long> injectStringToLongMap(InjectionPoint injectionPoint) {
        return convertStringListToMapOfTypeR(Long::parseLong, injectionPoint);
    }

    @Produces
    @Dependent
    @Config
    public Map<String, Byte> injectStringToByteMap(InjectionPoint injectionPoint) {
        return convertStringListToMapOfTypeR(Byte::parseByte, injectionPoint);
    }

    @Produces
    @Dependent
    @Config
    public Map<String, Float> injectStringToFloatMap(InjectionPoint injectionPoint) {
        return convertStringListToMapOfTypeR(Float::parseFloat, injectionPoint);
    }

    @Produces
    @Dependent
    @Config
    public Map<String, Double> injectStringToDoubleMap(InjectionPoint injectionPoint) {
        return convertStringListToMapOfTypeR(Double::parseDouble, injectionPoint);
    }

    private <R> List<R> convertStringListToListOfTypeR(Function<String, R> function, List<String> toConvert) {
        return toConvert
                .stream()
                .map(function)
                .collect(Collectors.toList());
    }

    private <R> Map<String, R> convertStringListToMapOfTypeR(Function<String, R> function, InjectionPoint injectionPoint) {
        return splitStringByToken(getProperty(getPropertyName(injectionPoint), injectionPoint), ",")
                .stream()
                .map(sl -> splitStringByToken(sl, pipe))
                .collect(Collectors.toMap(s -> s.get(0), s -> function.apply(s.get(1))));
    }

    private List<String> splitStringByToken(String toSplit, String token) {
        return Arrays.asList(toSplit.split(token));
    }

    private String getPropertyName(InjectionPoint injectionPoint) {
        String className = injectionPoint.getMember().getDeclaringClass().getSimpleName();
        String fieldName = injectionPoint.getMember().getName();
        return className + "_" + fieldName;
    }

    private boolean isConfigPropertyProvided(String configProperty) {
        boolean isProvided = true;
        if (null == StringUtils.trimToNull(configProperty)) {
            isProvided = false;
        }
        return isProvided;
    }

    private String getProperty(String propertyValue, Config config) {
        String classNameToLookup = config.defaultDecryptor().getSimpleName();
        boolean isEncrypted = config.isEncrypted();
        Decryptor decryptor;
        try {
            decryptor = LookupCdi.getBeanByNameOfClass(classNameToLookup, Decryptor.class);
        } catch (Exception ex) {
            log.error("getProperty() unable to lookup decryptor class", ex);
            throw new PropertyLoadException("Failed to get decryptor, failed to load property", ex);
        }
        return isEncrypted ? decryptor.decrypt(propertyValue) : propertyValue;
    }
}
