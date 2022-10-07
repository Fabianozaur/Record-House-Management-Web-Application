package recordhouse.SongArtistRelation.requests;

import design.exception.ApplicationException;

public class SongArtistRelationRequestError extends ApplicationException {
    public SongArtistRelationRequestError(String message) {
        super(message);
    }

    public SongArtistRelationRequestError(String message, Throwable cause) {
        super(message, cause);
    }

    public SongArtistRelationRequestError(Throwable cause) {
        super(cause);
    }
}
