package shared.properties;

import design.exception.ApplicationException;

public class PropertiesLoaderError extends ApplicationException {
    public PropertiesLoaderError(String message) {
        super(message);
    }

    public PropertiesLoaderError(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesLoaderError(Throwable cause) {
        super(cause);
    }
}
