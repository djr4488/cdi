package org.djr.cdi.properties;

public class PropertyLoadException extends RuntimeException {
    public PropertyLoadException() {
    }

    public PropertyLoadException(String message) {
        super(message);
    }

    public PropertyLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyLoadException(Throwable cause) {
        super(cause);
    }

    public PropertyLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
