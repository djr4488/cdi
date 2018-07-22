package org.djr.cdi.converter.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses({ ObjectMapperProducer.class })
public class ObjectMapperProducerTest {
    @JacksonModule(jacksonModules = {com.fasterxml.jackson.datatype.jsr310.JavaTimeModule.class})
    @JacksonMapperFeature(features = {
            @MapperFeatureConfig(feature = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, value = false),
            @MapperFeatureConfig(feature = MapperFeature.AUTO_DETECT_GETTERS, value = true)})
    @JacksonSerializationFeature(features = {
            @SerializationFeatureConfig(feature = SerializationFeature.INDENT_OUTPUT, value = true)})
    @JacksonDeserializationFeature(features = {
            @DeserializationFeatureConfig(feature = DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, value = false)})
    @Inject
    @JacksonObjectMapper
    private ObjectMapper objectMapper;
    @JacksonJsonParserFeature(features = {
            @JsonParserConfig(feature = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, value = true)})
    @JacksonJsonGeneratorFeature(features = {
            @JsonGeneratorConfig(feature = JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, value = true)})
    @Inject
    @JacksonObjectMapper
    private ObjectMapper objectMapper2;
    @Inject
    @JacksonObjectMapper
    private ObjectMapper objectMapper3;

    @Test
    public void testObjectMappersNotNull() {
        //assertNotNull(objectMapper);
        //assertNotNull(objectMapper2);
        assertNotNull(objectMapper3);
    }
}
