package org.djr.cdi.properties.file;

import org.djr.cdi.properties.PropertyLoadException;
import org.djr.cdi.properties.PropertyLoader;
import org.djr.cdi.properties.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class FilePropertiesLoader implements PropertyLoader {
    private static final Logger log = LoggerFactory.getLogger(FilePropertiesLoader.class);
    @Inject
    private PropertyUtils propertyUtils;

    @Produces
    @FileProperties
    public Properties getProperties() {
        String propertyFileName = propertyUtils.applicationName() + ".properties";
        Properties prop;
        prop = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream(propertyFileName);
        try {
            prop.load(in);
            in.close();
        } catch (IOException ioEx) {
            throw new PropertyLoadException("Failed to load properties", ioEx);
        }
        return prop;
    }
}
