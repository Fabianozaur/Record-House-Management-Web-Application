package recordhouse.PlaylistSongRelation.requests.find;

import design.mediator.RequestResult;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;

import java.util.List;

public class FindAllPlaylistSongRelationRequestResult implements RequestResult<FindAllPlaylistSongRelationRequest> {
    public List<PlaylistSongRelation> relations;
}
