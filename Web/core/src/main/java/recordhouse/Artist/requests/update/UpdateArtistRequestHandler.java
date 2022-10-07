package recordhouse.Artist.requests.update;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import recordhouse.Artist.requests.ArtistRequestError;
import recordhouse.shared.repository.Repository;

@Service
public class UpdateArtistRequestHandler implements RequestHandler<UpdateArtistRequest> {

    private final Repository<String, Artist> artistRepository;

    public UpdateArtistRequestHandler(Repository<String, Artist> artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public RequestResult<UpdateArtistRequest> Handle(UpdateArtistRequest command) {
        Artist artist = new Artist();

        artist.setId(command.artistId);
        artist.setStageName(command.stageName);
        artist.setFirstName(command.firstName);
        artist.setLastName(command.lastName);

        var result = artistRepository.save(artist)
                .orElseThrow(() -> new ArtistRequestError("Failed to update artist!"));

        var response = new UpdateArtistRequestResult();
        response.result = result;
        return response;
    }
}
