package recordhouse.PlaylistSongRelation.converter;

import design.converter.ReversibleConverter;
import org.springframework.stereotype.Service;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;
import service.PlaylistSong.PlaylistSongRelationViewModel;

@Service
public class PlaylistSongRelationViewModelConverter implements ReversibleConverter<PlaylistSongRelation, PlaylistSongRelationViewModel> {

@Override
public PlaylistSongRelation ConvertFrom(PlaylistSongRelationViewModel obj) {
        var relation = new PlaylistSongRelation();

        relation.setSongId(obj.songId);
        relation.setPlaylistId(obj.playlistId);

        return relation;
        }

@Override
public PlaylistSongRelationViewModel ConvertTo(PlaylistSongRelation obj) {
        var model = new PlaylistSongRelationViewModel();

        model.playlistId = obj.getPlaylistId();
        model.songId = obj.getSongId();

        return model;
        }
}