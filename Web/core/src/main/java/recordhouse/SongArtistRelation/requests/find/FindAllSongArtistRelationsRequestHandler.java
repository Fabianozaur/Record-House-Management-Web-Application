package recordhouse.SongArtistRelation.requests.find;

import design.domain.Composite;
import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;
import recordhouse.shared.repository.Repository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FindAllSongArtistRelationsRequestHandler implements RequestHandler<FindAllSongArtistRelationsRequest> {

    private final Repository<Composite<String, String>, SongArtistRelation> relationRepository;

    public FindAllSongArtistRelationsRequestHandler(Repository<Composite<String, String>, SongArtistRelation> relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public RequestResult<FindAllSongArtistRelationsRequest> Handle(FindAllSongArtistRelationsRequest command) {
        var relations = relationRepository.findAll();

        var response = new FindAllSongArtistRelationsRequestResult();
        response.Relations = StreamSupport.stream(relations.spliterator(), false)
                .collect(Collectors.toList());
        return response;
    }
}
