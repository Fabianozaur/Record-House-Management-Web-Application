package recordhouse.Artist.command;

import design.exception.ApplicationException;

public class ArtistCommandException  extends ApplicationException{
    public ArtistCommandException(String message) {
        super(message);
    }

    public ArtistCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtistCommandException(Throwable cause) {
        super(cause);
    }
}
