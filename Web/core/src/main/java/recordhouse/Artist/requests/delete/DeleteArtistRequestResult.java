package recordhouse.Artist.requests.delete;

import design.mediator.RequestResult;
import recordhouse.Artist.domain.Artist;

public class DeleteArtistRequestResult implements RequestResult<DeleteArtistRequest> {
    public Artist result;
}
