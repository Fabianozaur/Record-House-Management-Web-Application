package controller;

import design.converter.Converter;
import design.domain.Composite;
import design.domain.RelationConverter;
import design.mediator.Mediator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelation;
import recordhouse.ArtistRecordHouseRelation.requests.add.AddArtistRecordHouseRelationRequest;
import recordhouse.ArtistRecordHouseRelation.requests.find.FindAllArtistRecordHouseRelationsRequest;
import recordhouse.ArtistRecordHouseRelation.requests.find.FindAllArtistRecordHouseRelationsRequestResult;
import recordhouse.shared.mediator.RequestErrorResult;
import recordhouse.shared.repository.Repository;
import service.ArtistRecordHouse.ArtistRecordHouseRelationService;
import service.ArtistRecordHouse.ArtistRecordHouseRelationServiceError;
import service.ArtistRecordHouse.ArtistRecordHouseRelationViewModel;

import java.util.Optional;

@RestController
@RequestMapping("/artist-recordhouse")
public class ArtistRecordHouseController implements ArtistRecordHouseRelationService {

    private final Mediator mediator;
    private final Repository<Composite<String, String>, ArtistRecordHouseRelation> relationRepository;
    private final Converter<ArtistRecordHouseRelation, ArtistRecordHouseRelationViewModel> converter;
    private final RelationConverter<ArtistRecordHouseRelation> relationConverter;

    public ArtistRecordHouseController(Mediator mediator, Repository<Composite<String, String>, ArtistRecordHouseRelation> relationRepository, Converter<ArtistRecordHouseRelation, ArtistRecordHouseRelationViewModel> converter, RelationConverter<ArtistRecordHouseRelation> relationConverter) {
        this.mediator = mediator;
        this.relationRepository = relationRepository;
        this.converter = converter;
        this.relationConverter = relationConverter;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void Add(@RequestBody ArtistRecordHouseRelationViewModel model) {
        var request = new AddArtistRecordHouseRelationRequest();
        request.artistId = model.artistId;
        request.recordHouseId = model.recordHouseId;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<AddArtistRecordHouseRelationRequest>) result;
                    throw new ArtistRecordHouseRelationServiceError(errorResult.Exception);
                });
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArtistRecordHouseRelationViewModel[] GetAll() {
        var request = new FindAllArtistRecordHouseRelationsRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindAllArtistRecordHouseRelationsRequest>) result;
                    throw new ArtistRecordHouseRelationServiceError(errorResult.Exception);
                });

        var relations = (Optional.of(result)
                .filter(r -> r instanceof FindAllArtistRecordHouseRelationsRequestResult)
                .map(r -> (FindAllArtistRecordHouseRelationsRequestResult) r))
                .get()
                .relations;

        return relations.stream()
                .map(converter::ConvertTo)
                .toArray(ArtistRecordHouseRelationViewModel[]::new);
    }
}
