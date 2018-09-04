/**
 * Copyright 2017-2018 Danny Rucker

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
package org.djr.cdi.converter.json.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class ObjectMapperProducer {
    @Produces
    @JacksonObjectMapper
    public ObjectMapper configureJackson(InjectionPoint injectionPoint)
    throws InstantiationException, IllegalAccessException {
        ObjectMapper objectMapper = getObjectMapper(null);
        JacksonModule jacksonModule = injectionPoint.getAnnotated().getAnnotation(JacksonModule.class);
        if (null != jacksonModule) {
            objectMapper = getObjectMapper(objectMapper);
            registerJacksonModulesWithObjectMapper(jacksonModule, objectMapper);
        }
        JacksonMapperFeature jacksonMapperFeature = injectionPoint.getAnnotated().getAnnotation(JacksonMapperFeature.class);
        if (null != jacksonMapperFeature) {
            objectMapper = getObjectMapper(objectMapper);
            configureMapperFeaturesForObjectMapper(jacksonMapperFeature, objectMapper);
        }
        JacksonDeserializationFeature deserializationFeature = injectionPoint.getAnnotated().getAnnotation(JacksonDeserializationFeature.class);
        if (null != deserializationFeature) {
            objectMapper = getObjectMapper(objectMapper);
            configureDeserializationFeaturesForObjectMapper(deserializationFeature, objectMapper);
        }
        JacksonSerializationFeature serializationFeature = injectionPoint.getAnnotated().getAnnotation(JacksonSerializationFeature.class);
        if (null != serializationFeature) {
            objectMapper = getObjectMapper(objectMapper);
            configureSerializationFeaturesForObjectMapper(serializationFeature, objectMapper);
        }
        JacksonJsonParserFeature jsonParserFeature = injectionPoint.getAnnotated().getAnnotation(JacksonJsonParserFeature.class);
        if (null != jsonParserFeature) {
            objectMapper = getObjectMapper(objectMapper);
            configureJsonParserFeaturesForObjectMapper(jsonParserFeature, objectMapper);
        }
        JacksonJsonGeneratorFeature jsonGeneratorFeature = injectionPoint.getAnnotated().getAnnotation(JacksonJsonGeneratorFeature.class);
        if (null != jsonGeneratorFeature) {
            objectMapper = getObjectMapper(objectMapper);
            configureJsonGeneratorFeaturesForObjectMapper(jsonGeneratorFeature, objectMapper);
        }
        return objectMapper;
    }

    private ObjectMapper getObjectMapper(ObjectMapper objectMapper) {
        if (null == objectMapper) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    private void registerJacksonModulesWithObjectMapper(JacksonModule jacksonModule, ObjectMapper objectMapper)
            throws InstantiationException, IllegalAccessException {
        for (Class module : jacksonModule.jacksonModules()) {
            objectMapper.registerModule((Module)module.newInstance());
        }
    }

    private void configureMapperFeaturesForObjectMapper(JacksonMapperFeature jacksonMapperFeature, ObjectMapper objectMapper) {
        for (MapperFeatureConfig config : jacksonMapperFeature.features()) {
            objectMapper.configure(config.feature(), config.value());
        }
    }

    private void configureDeserializationFeaturesForObjectMapper(JacksonDeserializationFeature deserializationFeature, ObjectMapper objectMapper) {
        for (DeserializationFeatureConfig config : deserializationFeature.features()) {
            objectMapper.configure(config.feature(), config.value());
        }
    }

    private void configureSerializationFeaturesForObjectMapper(JacksonSerializationFeature serializationFeature, ObjectMapper objectMapper) {
        for (SerializationFeatureConfig config : serializationFeature.features()) {
            objectMapper.configure(config.feature(), config.value());
        }
    }

    private void configureJsonParserFeaturesForObjectMapper(JacksonJsonParserFeature parserFeature, ObjectMapper objectMapper) {
        for (JsonParserConfig config : parserFeature.features()) {
            objectMapper.configure(config.feature(), config.value());
        }
    }

    private void configureJsonGeneratorFeaturesForObjectMapper(JacksonJsonGeneratorFeature parserFeature, ObjectMapper objectMapper) {
        for (JsonGeneratorConfig config : parserFeature.features()) {
            objectMapper.configure(config.feature(), config.value());
        }
    }
}
