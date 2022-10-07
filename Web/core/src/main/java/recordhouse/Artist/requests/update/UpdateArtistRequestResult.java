package recordhouse.Artist.requests.update;

import design.mediator.RequestResult;
import recordhouse.Artist.domain.Artist;

public class UpdateArtistRequestResult implements RequestResult<UpdateArtistRequest> {
    public Artist result;
}
