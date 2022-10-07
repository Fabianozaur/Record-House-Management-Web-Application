package recordhouse.PlaylistSongRelation.repository.jpa;


import design.domain.Composite;
import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.stereotype.Service;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelationEntityModel;

@Service
public class PlaylistSongRelationEntityModelConverter implements EntityModelConverter<PlaylistSongRelation> {

    @Override
    public PlaylistSongRelation ConvertFrom(EntityModelOf<PlaylistSongRelation> obj) {
        var model = (PlaylistSongRelationEntityModel) obj;
        var relation = new PlaylistSongRelation();

        relation.setId(new Composite<>(model.playlistId, model.songId));
        relation.setPlaylistId(model.playlistId);
        relation.setSongId(model.songId);

        return relation;
    }

    @Override
    public EntityModelOf<PlaylistSongRelation> ConvertTo(PlaylistSongRelation obj) {
        var model = new PlaylistSongRelationEntityModel();

        model.playlistId = obj.getPlaylistId();
        model.songId = obj.getSongId();

        return model;
    }
}