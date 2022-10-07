package recordhouse.SongArtistRelation.requests.find;

import design.mediator.RequestResult;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;

import java.util.List;

public class FindAllSongArtistRelationsRequestResult implements RequestResult<FindAllSongArtistRelationsRequest> {
    public List<SongArtistRelation> Relations;
}
