package recordhouse.Playlist.requests;

import design.exception.ApplicationException;

public class PlaylistRequestError extends ApplicationException {
    public PlaylistRequestError(String message) {
        super(message);
    }

    public PlaylistRequestError(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaylistRequestError(Throwable cause) {
        super(cause);
    }
}
