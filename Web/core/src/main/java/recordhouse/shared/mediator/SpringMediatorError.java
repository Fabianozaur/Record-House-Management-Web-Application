package recordhouse.shared.mediator;

import design.exception.ApplicationException;

public class SpringMediatorError extends ApplicationException {
    public SpringMediatorError(String message) {
        super(message);
    }

    public SpringMediatorError(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringMediatorError(Throwable cause) {
        super(cause);
    }
}
