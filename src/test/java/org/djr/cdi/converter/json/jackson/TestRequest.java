package org.djr.cdi.converter.json.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TestRequest {
    @JsonProperty("test property")
    private String testProperty;
    @JsonProperty("test_property2")
    private String testProperty2;
    private String testProperty3;

    public TestRequest() {
    }

    public TestRequest(String testProperty, String testProperty2, String testProperty3) {
        this.testProperty = testProperty;
        this.testProperty2 = testProperty2;
        this.testProperty3 = testProperty3;
    }

    public String getTestProperty() {
        return testProperty;
    }

    public void setTestProperty(String testProperty) {
        this.testProperty = testProperty;
    }

    public String getTestProperty2() {
        return testProperty2;
    }

    public void setTestProperty2(String testProperty2) {
        this.testProperty2 = testProperty2;
    }

    public String getTestProperty3() {
        return testProperty3;
    }

    public void setTestProperty3(String testProperty3) {
        this.testProperty3 = testProperty3;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
