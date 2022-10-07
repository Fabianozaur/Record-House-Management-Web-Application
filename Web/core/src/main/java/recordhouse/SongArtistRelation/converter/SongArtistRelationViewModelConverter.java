package recordhouse.SongArtistRelation.converter;

import design.converter.ReversibleConverter;
import org.springframework.stereotype.Service;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;
import service.SongArtist.SongArtistRelationViewModel;

@Service
public class SongArtistRelationViewModelConverter implements ReversibleConverter<SongArtistRelation, SongArtistRelationViewModel> {

    @Override
    public SongArtistRelation ConvertFrom(SongArtistRelationViewModel obj) {
        var relation = new SongArtistRelation();
        relation.setArtistId(obj.artistId);
        relation.setSongId(obj.songId);
        return relation;
    }

    @Override
    public SongArtistRelationViewModel ConvertTo(SongArtistRelation obj) {
        var model = new SongArtistRelationViewModel();
        model.artistId = obj.getArtistId();
        model.songId = obj.getSongId();
        return model;
    }
}
