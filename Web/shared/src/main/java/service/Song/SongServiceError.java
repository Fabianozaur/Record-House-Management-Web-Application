package service.Song;

import design.exception.ApplicationException;

public class SongServiceError extends ApplicationException {
    public SongServiceError(String message) {
        super(message);
    }

    public SongServiceError(String message, Throwable cause) {
        super(message, cause);
    }

    public SongServiceError(Throwable cause) {
        super(cause);
    }
}
