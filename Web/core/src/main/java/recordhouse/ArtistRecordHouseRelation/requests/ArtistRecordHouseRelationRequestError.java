package recordhouse.ArtistRecordHouseRelation.requests;

import design.exception.ApplicationException;

public class ArtistRecordHouseRelationRequestError extends ApplicationException {
    public ArtistRecordHouseRelationRequestError(String message) {
        super(message);
    }

    public ArtistRecordHouseRelationRequestError(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtistRecordHouseRelationRequestError(Throwable cause) {
        super(cause);
    }
}
