package design.validation;

import design.exception.ApplicationException;

/**
 * @author radu.
 */

public class ValidatorException extends ApplicationException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
