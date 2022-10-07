package recordhouse.Artist.requests.add;

import design.mediator.RequestResult;
import recordhouse.Artist.domain.Artist;

public class AddArtistRequestResult implements RequestResult<AddArtistRequest> {
    public Artist result;
}
