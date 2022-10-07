package service.SongArtist;

import design.exception.ApplicationException;

public class SongArtistRelationServiceError extends ApplicationException {
    public SongArtistRelationServiceError(String message) {
        super(message);
    }

    public SongArtistRelationServiceError(String message, Throwable cause) {
        super(message, cause);
    }

    public SongArtistRelationServiceError(Throwable cause) {
        super(cause);
    }
}
