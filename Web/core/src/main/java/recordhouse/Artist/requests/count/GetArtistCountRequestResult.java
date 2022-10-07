package recordhouse.Artist.requests.count;

import design.mediator.RequestResult;

public class GetArtistCountRequestResult implements RequestResult<GetArtistCountRequest> {
    public Integer count;
}