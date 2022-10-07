package recordhouse.shared.repository.jpa;

import design.exception.ApplicationException;

public class EntityRepositoryError extends ApplicationException {
    public EntityRepositoryError(String message) {
        super(message);
    }

    public EntityRepositoryError(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityRepositoryError(Throwable cause) {
        super(cause);
    }
}
