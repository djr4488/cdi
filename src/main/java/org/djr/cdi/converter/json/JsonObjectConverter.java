package org.djr.cdi.converter.json;

import org.djr.cdi.logs.Slf4jLogger;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class JsonObjectConverter {
    private Jsonb jsonb;
    @Inject
    @Slf4jLogger
    private Logger log;

    @PostConstruct
    public void postConstruct() {
        jsonb = JsonbBuilder.create();
    }

    public <T> String toJson(T from) {
        String to = jsonb.toJson(from);
        log.trace("toJson() from:{}. to:{}", from, to);
        return to;
    }

    public <T> T fromJson(String from, Class<T> toClass) {
        T to = jsonb.fromJson(from, toClass);
        return to;
    }
}
