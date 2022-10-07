package design.persistance.db.query;

import design.exception.ApplicationException;

public class QueryError extends ApplicationException {
    public QueryError(String message) {
        super(message);
    }

    public QueryError(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryError(Throwable cause) {
        super(cause);
    }
}
