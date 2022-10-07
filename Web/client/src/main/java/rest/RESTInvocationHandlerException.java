package rest;

import design.exception.ApplicationException;

public class RESTInvocationHandlerException extends ApplicationException {
    public RESTInvocationHandlerException(String message) {
        super(message);
    }

    public RESTInvocationHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RESTInvocationHandlerException(Throwable cause) {
        super(cause);
    }
}
