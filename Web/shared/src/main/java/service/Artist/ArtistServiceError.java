package service.Artist;

import design.exception.ApplicationException;

public class ArtistServiceError extends ApplicationException {
    public ArtistServiceError(String message) {
        super(message);
    }

    public ArtistServiceError(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtistServiceError(Throwable cause) {
        super(cause);
    }
}
