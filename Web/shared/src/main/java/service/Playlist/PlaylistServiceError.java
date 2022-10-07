package service.Playlist;

import design.exception.ApplicationException;

public class PlaylistServiceError extends ApplicationException {
    public PlaylistServiceError(String message) {
        super(message);
    }

    public PlaylistServiceError(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaylistServiceError(Throwable cause) {
        super(cause);
    }
}
