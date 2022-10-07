package recordhouse.ArtistRecordHouseRelation.converter;

import design.converter.ReversibleConverter;
import org.springframework.stereotype.Service;
import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelation;
import service.ArtistRecordHouse.ArtistRecordHouseRelationViewModel;

@Service
public class ArtistRecordHouseRelationViewModelConverter implements ReversibleConverter<ArtistRecordHouseRelation, ArtistRecordHouseRelationViewModel> {

    @Override
    public ArtistRecordHouseRelation ConvertFrom(ArtistRecordHouseRelationViewModel obj) {
        var relation = new ArtistRecordHouseRelation();

        relation.SetArtistId(obj.artistId);
        relation.SetRecordHouseId(obj.recordHouseId);

        return relation;
    }

    @Override
    public ArtistRecordHouseRelationViewModel ConvertTo(ArtistRecordHouseRelation obj) {
        var model = new ArtistRecordHouseRelationViewModel();

        model.artistId = obj.GetArtistId();
        model.recordHouseId = obj.GetRecordHouseId();

        return model;
    }
}
