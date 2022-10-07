package service.PlaylistSong;


import design.exception.ApplicationException;

public class PlaylistSongRelationServiceError extends ApplicationException {
    public PlaylistSongRelationServiceError(String message) {
        super(message);
    }

    public PlaylistSongRelationServiceError(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaylistSongRelationServiceError(Throwable cause) {
        super(cause);
    }
}