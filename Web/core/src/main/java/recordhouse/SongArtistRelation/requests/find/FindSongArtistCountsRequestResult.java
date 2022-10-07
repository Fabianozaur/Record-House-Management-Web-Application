package recordhouse.SongArtistRelation.requests.find;

import design.mediator.RequestResult;

import java.util.Map;

public class FindSongArtistCountsRequestResult implements RequestResult<FindSongArtistCountsRequest> {
    public Map<String, Long> ArtistCounts;
}
