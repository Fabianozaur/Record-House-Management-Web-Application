package recordhouse.PlaylistSongRelation.requests.find;

import design.mediator.RequestResult;

import java.util.Map;

public class FindPlaylistSongCountRequestResult implements RequestResult<FindPlaylistSongCountRequest> {
    public Map<String,Long> PlaylistCounts;
}
