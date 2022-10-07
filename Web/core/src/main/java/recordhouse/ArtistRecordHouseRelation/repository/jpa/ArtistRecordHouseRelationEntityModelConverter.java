package recordhouse.ArtistRecordHouseRelation.repository.jpa;

import design.domain.Composite;
import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.stereotype.Service;
import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelation;
import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelationEntityModel;

@Service
public class ArtistRecordHouseRelationEntityModelConverter implements EntityModelConverter<ArtistRecordHouseRelation> {

    @Override
    public ArtistRecordHouseRelation ConvertFrom(EntityModelOf<ArtistRecordHouseRelation> obj) {
        var model = (ArtistRecordHouseRelationEntityModel) obj;
        var relation = new ArtistRecordHouseRelation();

        relation.setId(new Composite<>(model.artistId, model.recordHouseId));
        relation.SetArtistId(model.artistId);
        relation.SetRecordHouseId(model.recordHouseId);

        return relation;
    }

    @Override
    public EntityModelOf<ArtistRecordHouseRelation> ConvertTo(ArtistRecordHouseRelation obj) {
        var model = new ArtistRecordHouseRelationEntityModel();

        model.artistId = obj.GetArtistId();
        model.recordHouseId = obj.GetRecordHouseId();

        return model;
    }
}
