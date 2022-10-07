package recordhouse.PlaylistSongRelation.requests.find;

import design.domain.Composite;
import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;
import recordhouse.shared.repository.Repository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FindAllPlaylistSongRelationRequestHandler implements RequestHandler<FindAllPlaylistSongRelationRequest> {

    private final Repository<Composite<String, String>, PlaylistSongRelation> relationRepository;

    public FindAllPlaylistSongRelationRequestHandler(Repository<Composite<String, String>, PlaylistSongRelation> relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public RequestResult<FindAllPlaylistSongRelationRequest> Handle(FindAllPlaylistSongRelationRequest command) {
        var relations = relationRepository.findAll();

        var response = new FindAllPlaylistSongRelationRequestResult();
        response.relations = StreamSupport.stream(relations.spliterator(), false)
                .collect(Collectors.toList());
        return response;
    }
}
