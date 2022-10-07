package recordhouse.ArtistRecordHouseRelation.repository.jpa;

import design.domain.Composite;
import design.domain.RelationConverter;
import org.springframework.stereotype.Service;
import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelation;

@Service
public class ArtistRecordHouseRelationConverter implements RelationConverter<ArtistRecordHouseRelation> {

    @Override
    public Composite<String, String> ConvertFrom(ArtistRecordHouseRelation obj) {
        return new Composite<>(obj.GetArtistId(), obj.GetRecordHouseId());
    }

    @Override
    public ArtistRecordHouseRelation ConvertTo(Composite<String, String> obj) {
        var relation = new ArtistRecordHouseRelation();

        relation.SetArtistId(obj.first);
        relation.SetRecordHouseId(obj.second);

        return relation;
    }

}
