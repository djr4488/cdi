package org.djr.cdi.converter;

import javax.json.bind.annotation.JsonbProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class TestRequest {
    @JsonbProperty("test property1")
    @XmlElement(name = "testproperty1")
    private String testProperty;
    @JsonbProperty("test_property2")
    @XmlElement(name = "test_property2")
    private String testProperty2;
    @JsonbProperty("testProperty3")
    @XmlElement(name = "testProperty3")
    private String testProperty3;
    @JsonbProperty
    @XmlElement
    private TestChild testChild;

    public TestRequest() {
    }

    public TestRequest(String testProperty, String testProperty2,
                       String testProperty3, TestChild testChild) {
        this.testProperty = testProperty;
        this.testProperty2 = testProperty2;
        this.testProperty3 = testProperty3;
        this.testChild = testChild;
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

    public TestChild getTestChild() {
        return testChild;
    }

    public void setTestChild(TestChild testChild) {
        this.testChild = testChild;
    }
}
