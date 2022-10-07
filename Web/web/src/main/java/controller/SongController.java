package controller;

import com.jcabi.aspects.Loggable;
import design.converter.ReversibleConverter;
import design.mediator.Mediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import recordhouse.Song.domain.Song;
import recordhouse.Song.requests.add.AddSongRequest;
import recordhouse.Song.requests.add.AddSongRequestResult;
import recordhouse.Song.requests.delete.DeleteSongRequest;
import recordhouse.Song.requests.delete.DeleteSongRequestResult;
import recordhouse.Song.requests.find.FindAllSongsRequest;
import recordhouse.Song.requests.find.FindAllSongsRequestResult;
import recordhouse.Song.requests.findLongest.FindLongestSongRequest;
import recordhouse.Song.requests.findLongest.FindLongestSongRequestResult;
import recordhouse.Song.requests.findMostLiked.FindMostLikedSongRequest;
import recordhouse.Song.requests.findMostLiked.FindMostLikedSongRequestResult;
import recordhouse.Song.requests.getSongCount.GetSongCountRequest;
import recordhouse.Song.requests.getSongCount.GetSongCountRequestResult;
import recordhouse.Song.requests.searchByName.SearchSongByNameRequest;
import recordhouse.Song.requests.searchByName.SearchSongByNameRequestResult;
import recordhouse.Song.requests.update.UpdateSongRequest;
import recordhouse.Song.requests.update.UpdateSongRequestResult;
import recordhouse.shared.mediator.RequestErrorResult;
import service.Song.SongService;
import service.Song.SongServiceError;
import service.Song.SongViewModel;

import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController implements SongService {

    private final Mediator mediator;
    private final ReversibleConverter<Song, SongViewModel> converter;
    private final Logger logger = LoggerFactory.getLogger(SongController.class);

    public SongController(Mediator mediator, ReversibleConverter<Song, SongViewModel> converter) {
        this.mediator = mediator;
        this.converter = converter;
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.POST, headers = {"content-type=application/json", "accept=application/json"})
    public @ResponseBody SongViewModel
    Add(@RequestBody SongViewModel model) throws SongServiceError {
        var request = new AddSongRequest();
        request.Title = model.Title;
        request.Duration = model.Duration;
        request.Likes = model.Likes;
        request.PublishDate = model.PublishDate;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<AddSongRequest>) result;
                    throw new SongServiceError(errorResult.Exception);
                });

        var song = (Optional.of(result)
                .filter(r -> r instanceof AddSongRequestResult)
                .map(r -> (AddSongRequestResult) r))
                .get()
                .Result;

        return converter.ConvertTo(song);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody SongViewModel
    Delete(@RequestParam(name = "id") String id) throws SongServiceError {
        var request = new DeleteSongRequest();
        request.Id = id;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<DeleteSongRequest>) result;
                    throw new SongServiceError(errorResult.Exception);
                });

        var song = (Optional.of(result)
                .filter(r -> r instanceof DeleteSongRequestResult)
                .map(r -> (DeleteSongRequestResult) r))
                .get()
                .Result;

        return converter.ConvertTo(song);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody SongViewModel
    Update(@RequestBody SongViewModel model) throws SongServiceError {
        var request = new UpdateSongRequest();
        request.Id = model.Id;
        request.Title = model.Title;
        request.Duration = model.Duration;
        request.Likes = model.Likes;
        request.PublishDate = model.PublishDate;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<UpdateSongRequest>) result;
                    throw new SongServiceError(errorResult.Exception);
                });

        var song = (Optional.of(result)
                .filter(r -> r instanceof UpdateSongRequestResult)
                .map(r -> (UpdateSongRequestResult) r))
                .get()
                .Result;

        return converter.ConvertTo(song);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody SongViewModel[]
    GetAll() throws SongServiceError {
        var request = new FindAllSongsRequest();
        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindAllSongsRequest>) result;
                    throw new SongServiceError(errorResult.Exception);
                });

        var songs = (Optional.of(result)
                .filter(r -> r instanceof FindAllSongsRequestResult)
                .map(r -> (FindAllSongsRequestResult) r))
                .get()
                .Songs;

        return songs.stream()
                .map(converter::ConvertTo).toArray(SongViewModel[]::new);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public @ResponseBody Integer
    GetSongCount() throws SongServiceError {
        var request = new GetSongCountRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<GetSongCountRequest>) result;
                    throw new SongServiceError(errorResult.Exception);
                });

        return Optional.of(result)
                .filter(r -> r instanceof GetSongCountRequestResult)
                .map(r -> (GetSongCountRequestResult) r)
                .get()
                .Count;
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(value = "/longest", method = RequestMethod.GET)
    public @ResponseBody SongViewModel
    GetLongestSong() throws SongServiceError {
        var request = new FindLongestSongRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindLongestSongRequest>) result;
                    throw new SongServiceError(errorResult.Exception);
                });

        return converter.ConvertTo(Optional.of(result)
                .filter(r -> r instanceof FindLongestSongRequestResult)
                .map(r -> (FindLongestSongRequestResult) r)
                .get()
                .Song);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(value = "/most-liked", method = RequestMethod.GET)
    public @ResponseBody SongViewModel
    GetMostLikedSong() throws SongServiceError {
        var request = new FindMostLikedSongRequest();

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindMostLikedSongRequest>) result;
                    throw new SongServiceError(errorResult.Exception);
                });

        return converter.ConvertTo(Optional.of(result)
                .filter(r -> r instanceof FindMostLikedSongRequestResult)
                .map(r -> (FindMostLikedSongRequestResult) r)
                .get()
                .Song);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody SongViewModel[]
    GetByName(@RequestParam(name = "name") String name) throws SongServiceError {
        var request = new SearchSongByNameRequest();
        request.Name = name;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<SearchSongByNameRequest>) result;
                    throw new SongServiceError(errorResult.Exception);
                });

        var songs = Optional.of(result)
                .filter(r -> r instanceof SearchSongByNameRequestResult)
                .map(r -> (SearchSongByNameRequestResult) r)
                .get()
                .Songs;

        return songs
                .stream()
                .map(converter::ConvertTo)
                .toArray(SongViewModel[]::new);
    }
}
