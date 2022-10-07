package recordhouse.SongArtistRelation.requests.find;

import design.mediator.RequestResult;

import java.util.Map;

public class FindArtistSongCountsRequestResult implements RequestResult<FindArtistSongCountsRequest> {
    public Map<String, Long> songCounts;
}
