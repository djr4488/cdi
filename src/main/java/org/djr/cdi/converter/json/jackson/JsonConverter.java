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
package org.djr.cdi.converter.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.stream.JsonParsingException;
import java.io.IOException;

@ApplicationScoped
public class JsonConverter {
    @JacksonModule(jacksonModules = {JodaModule.class})
    @JacksonMapperFeature(features = {
            @MapperFeatureConfig(feature = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, value = false),
            @MapperFeatureConfig(feature = MapperFeature.AUTO_DETECT_GETTERS, value = true)})
    @JacksonDeserializationFeature(features = {
            @DeserializationFeatureConfig(feature = DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, value = false)})
    @Inject
    @JacksonObjectMapper
    private ObjectMapper objectMapper;

    public <T> String toJsonString(T objectToConvertToJsonString)
    throws JsonProcessingException {
        return objectMapper.writeValueAsString(objectToConvertToJsonString);
    }

    public <R> R toObjectFromString(String jsonToConvertFrom, Class<R> classToConvertTo)
    throws IOException, JsonParsingException{
        return objectMapper.readValue(jsonToConvertFrom, classToConvertTo);
    }
}
