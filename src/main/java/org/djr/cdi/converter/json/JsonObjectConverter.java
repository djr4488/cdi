/**
 * Copyright 2017 - 2021 Danny Rucker

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
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
        log.trace("fromJson() from:{}, to:{}", from, to);
        return to;
    }
}
