package recordhouse.SongArtistRelation.repository.jpa;

import design.domain.Composite;
import design.domain.RelationConverter;
import org.springframework.stereotype.Service;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;

@Service
public class SongArtistRelationConverter implements RelationConverter<SongArtistRelation> {
    @Override
    public Composite<String, String> ConvertFrom(SongArtistRelation obj) {
        return new Composite<>(obj.getSongId(), obj.getArtistId());
    }

    @Override
    public SongArtistRelation ConvertTo(Composite<String, String> obj) {
        var relation = new SongArtistRelation();
        relation.setSongId(obj.first);
        relation.setArtistId(obj.second);
        return relation;
    }
}
