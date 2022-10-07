package design.exception;

public class FatalError extends ApplicationException {

    public FatalError(String message) {
        super(message);
    }

    public FatalError(String message, Throwable cause) {
        super(message, cause);
    }

    public FatalError(Throwable cause) {
        super(cause);
    }
}
