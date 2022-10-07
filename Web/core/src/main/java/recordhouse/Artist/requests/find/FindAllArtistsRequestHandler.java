package recordhouse.Artist.requests.find;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import recordhouse.shared.repository.Repository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope("singleton")
public class FindAllArtistsRequestHandler implements RequestHandler<FindAllArtistsRequest> {

    private final Repository<String, Artist> artistRepository;

    public FindAllArtistsRequestHandler(Repository<String, Artist> artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public RequestResult<FindAllArtistsRequest> Handle(FindAllArtistsRequest command) {
        var artists = StreamSupport.stream(artistRepository.findAll().spliterator(), false);

        var response = new FindAllArtistsRequestResult();
        response.artists = artists.collect(Collectors.toList());
        return response;
    }
}
