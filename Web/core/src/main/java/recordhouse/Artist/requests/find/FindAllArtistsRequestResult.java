package recordhouse.Artist.requests.find;

import design.mediator.RequestResult;
import recordhouse.Artist.domain.Artist;

import java.util.List;

public class FindAllArtistsRequestResult implements RequestResult<FindAllArtistsRequest> {
    public List<Artist> artists;
}
