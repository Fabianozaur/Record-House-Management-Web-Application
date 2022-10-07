package recordhouse.Song.requests;

import design.exception.ApplicationException;

public class SongRequestError extends ApplicationException {
    public SongRequestError(String message) {
        super(message);
    }

    public SongRequestError(String message, Throwable cause) {
        super(message, cause);
    }

    public SongRequestError(Throwable cause) {
        super(cause);
    }
}
