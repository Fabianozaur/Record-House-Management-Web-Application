package controller;

import design.converter.Converter;
import design.domain.Composite;
import design.domain.RelationConverter;
import design.mediator.Mediator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;
import recordhouse.SongArtistRelation.requests.add.AddSongArtistRelationRequest;
import recordhouse.SongArtistRelation.requests.find.*;
import recordhouse.shared.mediator.RequestErrorResult;
import recordhouse.shared.repository.Repository;
import service.SongArtist.SongArtistRelationService;
import service.SongArtist.SongArtistRelationServiceError;
import service.SongArtist.SongArtistRelationViewModel;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/song-artist")
public class SongArtistController implements SongArtistRelationService {

    private final Mediator mediator;
    private final Repository<Composite<String, String>, SongArtistRelation> relationRepository;
    private final Converter<SongArtistRelation, SongArtistRelationViewModel> converter;
    private final RelationConverter<SongArtistRelation> relationConverter;

    public SongArtistController(Mediator mediator, Repository<Composite<String, String>, SongArtistRelation> relationRepository, Converter<SongArtistRelation, SongArtistRelationViewModel> converter, RelationConverter<SongArtistRelation> relationConverter) {
        this.mediator = mediator;
        this.relationRepository = relationRepository;
        this.converter = converter;
        this.relationConverter = relationConverter;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void Add(@RequestBody SongArtistRelationViewModel model) {
        var request = new AddSongArtistRelationRequest();
        request.SongId = model.songId;
        request.ArtistId = model.artistId;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<AddSongArtistRelationRequest>) result;
                    throw new SongArtistRelationServiceError(errorResult.Exception);
                });
    }

    @RequestMapping(method = RequestMethod.GET)
    public SongArtistRelationViewModel[] GetAll() {
        var request = new FindAllSongArtistRelationsRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindAllSongArtistRelationsRequest>) result;
                    throw new SongArtistRelationServiceError(errorResult.Exception);
                });

        var relations = (Optional.of(result)
                .filter(r -> r instanceof FindAllSongArtistRelationsRequestResult)
                .map(r -> (FindAllSongArtistRelationsRequestResult) r))
                .get()
                .Relations;

        return relations.stream()
                .map(converter::ConvertTo)
                .toArray(SongArtistRelationViewModel[]::new);
    }

    @RequestMapping(value = "artist-counts", method = RequestMethod.GET)
    public Map<String, Long> GetAllSongArtistCounts() {
        var request = new FindSongArtistCountsRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindSongArtistCountsRequest>) result;
                    throw new SongArtistRelationServiceError(errorResult.Exception);
                });

        return (Optional.of(result)
                .filter(r -> r instanceof FindSongArtistCountsRequestResult)
                .map(r -> (FindSongArtistCountsRequestResult) r))
                .get()
                .ArtistCounts;
    }

    @RequestMapping(value = "song-counts", method = RequestMethod.GET)
    public Map<String, Long> GetAllArtistSongCounts() {
        var request = new FindArtistSongCountsRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindArtistSongCountsRequest>) result;
                    throw new SongArtistRelationServiceError(errorResult.Exception);
                });

        return (Optional.of(result)
                .filter(r -> r instanceof FindArtistSongCountsRequestResult)
                .map(r -> (FindArtistSongCountsRequestResult) r))
                .get()
                .songCounts;
    }
}
