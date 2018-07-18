package org.djr.cdi.properties.database.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

@Entity
@Table(name = "property_models",
        indexes = { @Index(name = "application_name_idx", columnList = "application_name"),
                    @Index(name = "property_name_idx", columnList = "property_name")})
@NamedQueries({
        @NamedQuery(name = "propertyModel.loadAllPropertiesByApplicationName",
                    query = "select propertyModel from PropertyModel propertyModel where propertyModel.applicationName = :applicationName")
})
public class PropertyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "meta__version")
    private Long version;
    @Column(name = "meta__last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    @Column(name = "application_name")
    private String applicationName;
    @Column(name = "property_name")
    private String propertyName;
    @Column(name = "propertyValue")
    private String propertyValue;

    public PropertyModel() {
    }

    public PropertyModel(Long version, Date lastUpdated, String applicationName, String propertyName, String propertyValue) {
        this.version = version;
        this.lastUpdated = lastUpdated;
        this.applicationName = applicationName;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
