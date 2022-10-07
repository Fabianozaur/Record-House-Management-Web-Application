package controller;

import com.jcabi.aspects.Loggable;
import design.converter.ReversibleConverter;
import design.mediator.Mediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.Playlist.requests.add.AddPlaylistRequest;
import recordhouse.Playlist.requests.add.AddPlaylistRequestResult;
import recordhouse.Playlist.requests.count.GetPlaylistCountRequest;
import recordhouse.Playlist.requests.count.GetPlaylistCountRequestResult;
import recordhouse.Playlist.requests.delete.DeletePlaylistRequest;
import recordhouse.Playlist.requests.delete.DeletePlaylistRequestResult;
import recordhouse.Playlist.requests.find.FindAllPlaylistsRequest;
import recordhouse.Playlist.requests.find.FindAllPlaylistsRequestResult;
import recordhouse.Playlist.requests.update.UpdatePlaylistRequest;
import recordhouse.Playlist.requests.update.UpdatePlaylistRequestResult;
import recordhouse.shared.mediator.RequestErrorResult;
import service.Playlist.PlaylistService;
import service.Playlist.PlaylistServiceError;
import service.Playlist.PlaylistViewModel;

import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController implements PlaylistService {
    private final Mediator mediator;
    private final ReversibleConverter<Playlist, PlaylistViewModel> converter;
    private final Logger logger= LoggerFactory.getLogger(PlaylistController.class);

    public PlaylistController(Mediator mediator,ReversibleConverter<Playlist,PlaylistViewModel> converter){
        this.mediator=mediator;
        this.converter=converter;
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.POST, headers = {"content-type=application/json", "accept=application/json"})
    public @ResponseBody PlaylistViewModel
    Add(@RequestBody PlaylistViewModel model) throws PlaylistServiceError {
        var request = new AddPlaylistRequest();
        request.playlistName=model.Name;
        request.isPublic=model.isPublic;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<AddPlaylistRequest>) result;
                    throw new PlaylistServiceError(errorResult.Exception);
                });

        var playlist = (Optional.of(result)
                .filter(r -> r instanceof AddPlaylistRequestResult)
                .map(r -> (AddPlaylistRequestResult) r))
                .get()
                .result;

        return converter.ConvertTo(playlist);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody PlaylistViewModel
    Delete(@RequestParam(name = "id") String id) throws PlaylistServiceError {
        var request = new DeletePlaylistRequest();
        request.playlistId = id;
        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<DeletePlaylistRequest>) result;
                    throw new PlaylistServiceError(errorResult.Exception);
                });

        var playlist = (Optional.of(result)
                .filter(r -> r instanceof DeletePlaylistRequestResult)
                .map(r -> (DeletePlaylistRequestResult) r))
                .get()
                .result;

        return converter.ConvertTo(playlist);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.PUT)
    //@Transactional
    public @ResponseBody PlaylistViewModel
    Update(@RequestBody PlaylistViewModel model) throws PlaylistServiceError {
        var request = new UpdatePlaylistRequest();
        request.playlistId = model.Id;
        request.name = model.Name;
        request.isPublic = model.isPublic;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<UpdatePlaylistRequest>) result;
                    throw new PlaylistServiceError(errorResult.Exception);
                });

        var playlist = (Optional.of(result)
                .filter(r -> r instanceof UpdatePlaylistRequestResult)
                .map(r -> (UpdatePlaylistRequestResult) r))
                .get()
                .result;

        return converter.ConvertTo(playlist);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody PlaylistViewModel[]
    GetAll() throws PlaylistServiceError {
        var request = new FindAllPlaylistsRequest();
        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindAllPlaylistsRequest>) result;
                    throw new PlaylistServiceError(errorResult.Exception);
                });

        var playlists = (Optional.of(result)
                .filter(r -> r instanceof FindAllPlaylistsRequestResult)
                .map(r -> (FindAllPlaylistsRequestResult) r))
                .get()
                .playlists;

        return playlists.stream()
                .map(converter::ConvertTo).toArray(PlaylistViewModel[]::new);
    }
    @Loggable(Loggable.TRACE)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public @ResponseBody Integer
    GetPlaylistCount() throws PlaylistServiceError {
        var request = new GetPlaylistCountRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<GetPlaylistCountRequest>) result;
                    throw new PlaylistServiceError(errorResult.Exception);
                });

        return Optional.of(result)
                .filter(r -> r instanceof GetPlaylistCountRequestResult)
                .map(r -> (GetPlaylistCountRequestResult) r)
                .get()
                .Count;
    }

}
