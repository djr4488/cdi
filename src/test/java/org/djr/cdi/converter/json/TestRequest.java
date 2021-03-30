package org.djr.cdi.converter.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.bind.annotation.JsonbProperty;

public class TestRequest {
    @JsonProperty("test property")
    @JsonbProperty("test property1")
    private String testProperty;
    @JsonProperty("test_property2")
    @JsonbProperty("test_property2")
    private String testProperty2;
    @JsonbProperty("testProperty3")
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
}
