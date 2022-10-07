package recordhouse.Playlist.converter;

import design.converter.ReversibleConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.stereotype.Service;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.Playlist.domain.PlaylistEntityModel;
import service.Playlist.PlaylistViewModel;

@Service
public class PlaylistViewModelConverter implements ReversibleConverter<Playlist, PlaylistViewModel> {
    @Override
    public Playlist ConvertFrom(PlaylistViewModel obj) {
        var playlist=new Playlist();

        playlist.setId(obj.Id);
        playlist.setPlaylistName(obj.Name);
        playlist.setIsPublic(obj.isPublic);

        return playlist;
    }

    @Override
    public PlaylistViewModel ConvertTo(Playlist obj) {
        var model=new PlaylistViewModel();

        model.Id=obj.getId();
        model.Name=obj.getPlaylistName();
        model.isPublic=obj.getIsPublic();
        return model;
    }
}
