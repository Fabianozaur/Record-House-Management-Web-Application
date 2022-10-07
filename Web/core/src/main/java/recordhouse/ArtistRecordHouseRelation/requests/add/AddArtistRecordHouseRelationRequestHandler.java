package recordhouse.ArtistRecordHouseRelation.requests.add;

import design.domain.Composite;
import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelation;
import recordhouse.RecordHouse.Domain.RecordHouse;
import recordhouse.SongArtistRelation.requests.SongArtistRelationRequestError;
import recordhouse.shared.mediator.RequestSuccessResult;
import recordhouse.shared.repository.Repository;

@Service
public class AddArtistRecordHouseRelationRequestHandler implements RequestHandler<AddArtistRecordHouseRelationRequest> {

    private final Repository<Composite<String, String>, ArtistRecordHouseRelation> relationRepository;
    private final Repository<String, RecordHouse> recordHouseRepository;
    private final Repository<String, Artist> artistRepository;

    public AddArtistRecordHouseRelationRequestHandler(Repository<Composite<String, String>, ArtistRecordHouseRelation> relationRepository, Repository<String, Artist> artistRepository, Repository<String, RecordHouse> recordHouseRepository) {
        this.relationRepository = relationRepository;
        this.recordHouseRepository = recordHouseRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public RequestResult<AddArtistRecordHouseRelationRequest> Handle(AddArtistRecordHouseRelationRequest command) {
        var relation = new ArtistRecordHouseRelation();
        relation.SetArtistId(command.artistId);
        relation.SetRecordHouseId(command.recordHouseId);

        artistRepository
                .findOne(relation.GetArtistId())
                .orElseThrow(() -> {
                    throw new SongArtistRelationRequestError("There is no artist with ID " + relation.GetArtistId() + "!");
                });
        recordHouseRepository
                .findOne(relation.GetRecordHouseId())
                .orElseThrow(() -> {
                    throw new SongArtistRelationRequestError("There is no record house with ID " + relation.GetRecordHouseId() + "!");
                });

        relationRepository.save(relation);
        return new RequestSuccessResult<>();
    }
}
