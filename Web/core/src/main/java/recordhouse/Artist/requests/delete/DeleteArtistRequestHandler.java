package recordhouse.Artist.requests.delete;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import recordhouse.Artist.requests.ArtistRequestError;
import recordhouse.shared.repository.Repository;

@Service
public class DeleteArtistRequestHandler implements RequestHandler<DeleteArtistRequest> {

    private final Repository<String, Artist> artistRepository;

    public DeleteArtistRequestHandler(Repository<String, Artist> artistRepository){
        this.artistRepository = artistRepository;
    }


    @Override
    public RequestResult<DeleteArtistRequest> Handle(DeleteArtistRequest command) {
        var result = artistRepository.delete(command.artistId)
                .orElseThrow(() -> new ArtistRequestError("Failed to delete artist!"));

        var response = new DeleteArtistRequestResult();
        response.result = result;
        return response;
    }
}