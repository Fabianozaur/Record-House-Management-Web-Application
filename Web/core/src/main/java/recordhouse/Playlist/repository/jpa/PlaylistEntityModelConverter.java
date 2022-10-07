package recordhouse.Playlist.repository.jpa;

import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.stereotype.Service;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.Playlist.domain.PlaylistEntityModel;

@Service
public class PlaylistEntityModelConverter implements EntityModelConverter<Playlist> {
    @Override
    public Playlist ConvertFrom(EntityModelOf<Playlist> obj) {
        PlaylistEntityModel model=(PlaylistEntityModel) obj;
        Playlist playlist=new Playlist();

        playlist.setId(model.PlaylistId);
        playlist.setPlaylistName(model.playlistName);
        playlist.setIsPublic(model.isPublic);

        return playlist;
    }

    @Override
    public EntityModelOf<Playlist> ConvertTo(Playlist obj) {
        PlaylistEntityModel model=new PlaylistEntityModel();

        model.PlaylistId=obj.getId();
        model.playlistName=obj.getPlaylistName();
        model.isPublic=obj.getIsPublic();
        return model;
    }
}

