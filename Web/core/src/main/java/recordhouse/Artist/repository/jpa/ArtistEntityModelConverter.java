package recordhouse.Artist.repository.jpa;

import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import recordhouse.Artist.domain.ArtistEntityModel;

@Service
public class ArtistEntityModelConverter implements EntityModelConverter<Artist> {
    @Override
    public Artist ConvertFrom(EntityModelOf<Artist> obj) {
        ArtistEntityModel model = (ArtistEntityModel) obj;
        Artist artist = new Artist();

        artist.setId(model.artistId);
        artist.setStageName(model.stageName);
        artist.setFirstName(model.firstName);
        artist.setLastName(model.lastName);

        return artist;
    }

    @Override
    public EntityModelOf<Artist> ConvertTo(Artist obj) {
        ArtistEntityModel model = new ArtistEntityModel();

        model.artistId = obj.getId();
        model.stageName = obj.getStageName();
        model.firstName = obj.getFirstName();
        model.lastName = obj.getLastName();

        return model;
    }
}
