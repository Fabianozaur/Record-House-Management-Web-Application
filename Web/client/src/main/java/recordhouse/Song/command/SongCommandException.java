package recordhouse.Song.command;

import design.exception.ApplicationException;

public class SongCommandException extends ApplicationException {
    public SongCommandException(String message) {
        super(message);
    }

    public SongCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public SongCommandException(Throwable cause) {
        super(cause);
    }
}
