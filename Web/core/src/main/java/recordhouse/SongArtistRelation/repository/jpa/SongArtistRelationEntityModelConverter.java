package recordhouse.SongArtistRelation.repository.jpa;

import design.domain.Composite;
import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.stereotype.Service;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;
import recordhouse.SongArtistRelation.domain.SongArtistRelationEntityModel;

@Service
public class SongArtistRelationEntityModelConverter implements EntityModelConverter<SongArtistRelation> {

    @Override
    public SongArtistRelation ConvertFrom(EntityModelOf<SongArtistRelation> obj) {
        var model = (SongArtistRelationEntityModel) obj;
        var relation = new SongArtistRelation();
        relation.setId(new Composite<>(model.SongId, model.ArtistId));
        relation.setSongId(model.SongId);
        relation.setArtistId(model.ArtistId);
        return relation;
    }

    @Override
    public EntityModelOf<SongArtistRelation> ConvertTo(SongArtistRelation obj) {
        var model = new SongArtistRelationEntityModel();
        model.SongId = obj.getSongId();
        model.ArtistId = obj.getArtistId();
        return model;
    }
}
