package recordhouse.PlaylistSongRelation.requests;


import design.exception.ApplicationException;

public class PlaylistSongRelationRequestError extends ApplicationException {
    public PlaylistSongRelationRequestError(String message) {
        super(message);
    }

    public PlaylistSongRelationRequestError(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaylistSongRelationRequestError(Throwable cause) {
        super(cause);
    }
}