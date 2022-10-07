package controller;


import design.converter.Converter;
import design.domain.Composite;
import design.domain.RelationConverter;
import design.mediator.Mediator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;
import recordhouse.PlaylistSongRelation.requests.add.AddPlaylistSongRelationRequest;
import recordhouse.PlaylistSongRelation.requests.find.FindAllPlaylistSongRelationRequest;
import recordhouse.PlaylistSongRelation.requests.find.FindAllPlaylistSongRelationRequestResult;
import recordhouse.PlaylistSongRelation.requests.find.FindPlaylistSongCountRequest;
import recordhouse.PlaylistSongRelation.requests.find.FindPlaylistSongCountRequestResult;
import recordhouse.SongArtistRelation.requests.add.AddSongArtistRelationRequest;
import recordhouse.SongArtistRelation.requests.find.FindAllSongArtistRelationsRequestResult;
import recordhouse.shared.mediator.RequestErrorResult;
import recordhouse.shared.repository.Repository;
import service.Playlist.PlaylistServiceError;
import service.PlaylistSong.PlaylistSongRelationService;
import service.PlaylistSong.PlaylistSongRelationServiceError;
import service.PlaylistSong.PlaylistSongRelationViewModel;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/playlist-song")
public class PlaylistSongController implements PlaylistSongRelationService {

    private final Mediator mediator;
    private final Repository<Composite<String, String>, PlaylistSongRelation> relationRepository;
    private final Converter<PlaylistSongRelation, PlaylistSongRelationViewModel> converter;
    private final RelationConverter<PlaylistSongRelation> relationConverter;

    public PlaylistSongController(Mediator mediator, Repository<Composite<String, String>, PlaylistSongRelation> relationRepository, Converter<PlaylistSongRelation, PlaylistSongRelationViewModel> converter, RelationConverter<PlaylistSongRelation> relationConverter) {
        this.mediator = mediator;
        this.relationRepository = relationRepository;
        this.converter = converter;
        this.relationConverter = relationConverter;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void Add(@RequestBody PlaylistSongRelationViewModel model) {
        var request = new AddPlaylistSongRelationRequest();
        request.songId = model.songId;
        request.playlistId = model.playlistId;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<AddPlaylistSongRelationRequest>) result;
                    throw new PlaylistSongRelationServiceError(errorResult.Exception);
                });
    }

    @RequestMapping(method = RequestMethod.GET)
    public PlaylistSongRelationViewModel[] GetAll() {
        var request = new FindAllPlaylistSongRelationRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindAllPlaylistSongRelationRequest>) result;
                    throw new PlaylistSongRelationServiceError(errorResult.Exception);
                });

        var relations = (Optional.of(result)
                .filter(r -> r instanceof FindAllPlaylistSongRelationRequestResult)
                .map(r -> (FindAllPlaylistSongRelationRequestResult) r))
                .get()
                .relations;

        return relations.stream()
                .map(converter::ConvertTo)
                .toArray(PlaylistSongRelationViewModel[]::new);
    }

    @RequestMapping(value = "playlist-counts", method = RequestMethod.GET)
    public Map<String, Long> GetAllPlaylistSongCounts() {
        var request = new FindPlaylistSongCountRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindPlaylistSongCountRequest>) result;
                    throw new PlaylistSongRelationServiceError(errorResult.Exception);
                });

        return (Optional.of(result)
                .filter(r -> r instanceof FindPlaylistSongCountRequestResult)
                .map(r -> (FindPlaylistSongCountRequestResult) r))
                .get()
                .PlaylistCounts;
    }
}
