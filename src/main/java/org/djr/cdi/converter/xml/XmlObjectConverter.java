package org.djr.cdi.converter.xml;

import org.djr.cdi.logs.Slf4jLogger;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class XmlObjectConverter {
    @Inject
    @Slf4jLogger
    private Logger log;
    private Map<String, JAXBContext> contexts;

    @PostConstruct
    private void postConstruct() {
        contexts = new ConcurrentHashMap<>();
    }

    public <T> String toXml(T from)
    throws JAXBException, IOException {
        JAXBContext context = getContext(from.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        m.marshal(from, sw);
        String xml = sw.toString();
        log.trace("toXml() from:{}, to:{}", from, xml);
        return xml;
    }

    public <T> T fromXml(String from, Class<T> toClass)
    throws JAXBException, IOException {
        JAXBContext context = getContext(toClass);
        T to = toClass.cast(context.createUnmarshaller().unmarshal((new StringReader(from))));
        log.trace("fromXml() from:{}, to:{}", from, to);
        return to;
    }

    private <T> JAXBContext getContext(Class<T> contextObject)
    throws JAXBException {
        JAXBContext context;
        if (contexts.containsKey(contextObject.getCanonicalName())) {
            context = contexts.get(contextObject.getCanonicalName());
        } else {
            context = JAXBContext.newInstance(contextObject);
            contexts.put(contextObject.getCanonicalName(), context);
        }
        return context;
    }
}
