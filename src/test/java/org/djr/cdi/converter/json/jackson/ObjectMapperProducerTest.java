package org.djr.cdi.converter.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(WeldJunit5AutoExtension.class)
@AddBeanClasses({ ObjectMapperProducer.class })
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
