package recordhouse.PlaylistSongRelation.repository.jpa;


import design.domain.Composite;
import design.domain.RelationConverter;
import org.springframework.stereotype.Service;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;

@Service
public class PlaylistSongRelationConverter implements RelationConverter<PlaylistSongRelation> {

    @Override
    public Composite<String, String> ConvertFrom(PlaylistSongRelation obj) {
        return new Composite<>(obj.getPlaylistId(), obj.getSongId());
    }

    @Override
    public PlaylistSongRelation ConvertTo(Composite<String, String> obj) {
        var relation = new PlaylistSongRelation();

        relation.setPlaylistId(obj.first);
        relation.setSongId(obj.second);

        return relation;
    }

}
