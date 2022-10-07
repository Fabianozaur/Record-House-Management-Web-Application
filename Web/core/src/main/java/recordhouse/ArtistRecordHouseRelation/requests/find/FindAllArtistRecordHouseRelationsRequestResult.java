package recordhouse.ArtistRecordHouseRelation.requests.find;

import design.mediator.RequestResult;
import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelation;

import java.util.List;

public class FindAllArtistRecordHouseRelationsRequestResult implements RequestResult<FindAllArtistRecordHouseRelationsRequest> {
    public List<ArtistRecordHouseRelation> relations;
}
