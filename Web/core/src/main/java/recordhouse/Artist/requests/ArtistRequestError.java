package recordhouse.Artist.requests;

import design.exception.ApplicationException;

public class ArtistRequestError extends ApplicationException {
    public ArtistRequestError(String message) {
        super(message);
    }

    public ArtistRequestError(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtistRequestError(Throwable cause) {
        super(cause);
    }
}
