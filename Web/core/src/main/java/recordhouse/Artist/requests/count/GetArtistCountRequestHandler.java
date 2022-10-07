package recordhouse.Artist.requests.count;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import recordhouse.shared.repository.Repository;

import java.util.stream.StreamSupport;

@Service
public class GetArtistCountRequestHandler implements RequestHandler<GetArtistCountRequest> {

    private final Repository<String, Artist> artistRepository;

    public GetArtistCountRequestHandler(Repository<String, Artist> artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public RequestResult<GetArtistCountRequest> Handle(GetArtistCountRequest command) {

        var result = new GetArtistCountRequestResult();
        result.count = (int) StreamSupport.stream(artistRepository.findAll().spliterator(), false).count();
        return result;
    }
}