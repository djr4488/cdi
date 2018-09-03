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
package org.djr.properties;

import org.djr.cdi.properties.PropertyLoadException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertyLoadExceptionTest {
    @Test
    public void testConstructors() {
        PropertyLoadException plEx = new PropertyLoadException();
        assertNull(plEx.getMessage());
        plEx = new PropertyLoadException("Some message");
        assertEquals("Some message", plEx.getMessage());
        plEx = new PropertyLoadException(new IOException("IO Ex"));
        assertTrue(plEx.getCause() instanceof IOException);
        plEx = new PropertyLoadException("Some message", new IOException("IO Ex"));
        assertTrue(plEx.getCause() instanceof IOException);
        assertEquals("Some message", plEx.getMessage());
        plEx = new PropertyLoadException("Some message", new IOException("IO Ex"), true, true);
        assertTrue(plEx.getCause() instanceof IOException);
        assertEquals("Some message", plEx.getMessage());
    }
}
