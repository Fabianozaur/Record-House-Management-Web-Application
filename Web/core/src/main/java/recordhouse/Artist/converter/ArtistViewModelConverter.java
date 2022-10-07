package recordhouse.Artist.converter;

import design.converter.ReversibleConverter;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import service.Artist.ArtistViewModel;

@Service
public class ArtistViewModelConverter implements ReversibleConverter<Artist, ArtistViewModel> {
    @Override
    public Artist ConvertFrom(ArtistViewModel obj) {
        var artist = new Artist();

        artist.setId(obj.artistId);
        artist.setStageName(obj.stageName);
        artist.setFirstName(obj.firstName);
        artist.setLastName(obj.lastName);

        return artist;
    }

    @Override
    public ArtistViewModel ConvertTo(Artist obj) {
        var model = new ArtistViewModel();

        model.artistId = obj.getId();
        model.stageName = obj.getStageName();
        model.firstName  = obj.getFirstName();
        model.lastName = obj.getLastName();

        return model;
    }
}
