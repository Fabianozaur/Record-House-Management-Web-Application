package recordhouse.ArtistRecordHouseRelation.requests.find;

import design.domain.Composite;
import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelation;
import recordhouse.shared.repository.Repository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FindAllArtistRecordHouseRelationsRequestHandler implements RequestHandler<FindAllArtistRecordHouseRelationsRequest> {

    private final Repository<Composite<String, String>, ArtistRecordHouseRelation> relationRepository;

    public FindAllArtistRecordHouseRelationsRequestHandler(Repository<Composite<String, String>, ArtistRecordHouseRelation> relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public RequestResult<FindAllArtistRecordHouseRelationsRequest> Handle(FindAllArtistRecordHouseRelationsRequest command) {
        var relations = relationRepository.findAll();

        var response = new FindAllArtistRecordHouseRelationsRequestResult();
        response.relations = StreamSupport.stream(relations.spliterator(), false)
                .collect(Collectors.toList());
        return response;
    }
}
