package recordhouse.Artist.requests.add;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import recordhouse.Artist.requests.ArtistRequestError;
import recordhouse.shared.repository.Repository;

@Service
public class AddArtistRequestHandler implements RequestHandler<AddArtistRequest> {

    private final Repository<String, Artist> artistRepository;

    public AddArtistRequestHandler(Repository<String, Artist> artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public RequestResult<AddArtistRequest> Handle(AddArtistRequest command) {
        Artist artist = new Artist();

        artist.setStageName(command.stageName);
        artist.setFirstName(command.firstName);
        artist.setLastName(command.lastName);

        var result = artistRepository.save(artist)
                .orElseThrow(() -> new ArtistRequestError("Failed to create artist!"));

        var response = new AddArtistRequestResult();
        response.result = result;
        return response;
    }
}
