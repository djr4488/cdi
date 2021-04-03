package org.djr.cdi.converter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
