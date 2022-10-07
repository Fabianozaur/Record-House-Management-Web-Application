package service.ArtistRecordHouse;

import design.exception.ApplicationException;

public class ArtistRecordHouseRelationServiceError extends ApplicationException {
    public ArtistRecordHouseRelationServiceError(String message) {
        super(message);
    }

    public ArtistRecordHouseRelationServiceError(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtistRecordHouseRelationServiceError(Throwable cause) {
        super(cause);
    }
}
