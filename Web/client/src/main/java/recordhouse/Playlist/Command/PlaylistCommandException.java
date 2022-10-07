package recordhouse.Playlist.Command;

import design.exception.ApplicationException;

public class PlaylistCommandException extends ApplicationException {
    public PlaylistCommandException(String message) {
        super(message);
    }

    public PlaylistCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaylistCommandException(Throwable cause) {
        super(cause);
    }
}
