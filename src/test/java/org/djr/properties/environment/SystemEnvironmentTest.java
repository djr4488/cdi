/**
 * Copyright 11-9-2017 Danny Rucker

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
package org.djr.properties.environment;

import org.djr.cdi.properties.environment.SystemEnvironment;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(WeldJunit5AutoExtension.class)
@AddBeanClasses(SystemEnvironment.class)
public class SystemEnvironmentTest {
    @Inject
    private SystemEnvironment systemEnvironment;

    @Test
    public void testSystemEnvironmentNotNull() {
        assertNotNull(systemEnvironment);
        assertNotNull(systemEnvironment.getEnvironment());
    }
}
