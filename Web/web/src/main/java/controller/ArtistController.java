package controller;

import com.jcabi.aspects.Loggable;
import design.converter.ReversibleConverter;
import design.mediator.Mediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import recordhouse.Artist.domain.Artist;
import recordhouse.Artist.requests.add.AddArtistRequest;
import recordhouse.Artist.requests.add.AddArtistRequestResult;
import recordhouse.Artist.requests.count.GetArtistCountRequest;
import recordhouse.Artist.requests.count.GetArtistCountRequestResult;
import recordhouse.Artist.requests.delete.DeleteArtistRequest;
import recordhouse.Artist.requests.delete.DeleteArtistRequestResult;
import recordhouse.Artist.requests.find.FindAllArtistsRequest;
import recordhouse.Artist.requests.find.FindAllArtistsRequestResult;
import recordhouse.Artist.requests.update.UpdateArtistRequest;
import recordhouse.Artist.requests.update.UpdateArtistRequestResult;
import recordhouse.shared.mediator.RequestErrorResult;
import service.Artist.ArtistService;
import service.Artist.ArtistServiceError;
import service.Artist.ArtistViewModel;

import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController implements ArtistService {

    private final Mediator mediator;
    private final ReversibleConverter<Artist, ArtistViewModel> converter;
    private final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    public ArtistController(Mediator mediator, ReversibleConverter<Artist, ArtistViewModel> converter) {
        this.mediator = mediator;
        this.converter = converter;
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.POST, headers = {"content-type=application/json", "accept=application/json"})
    public @ResponseBody ArtistViewModel
    Add(@RequestBody ArtistViewModel model) throws ArtistServiceError {

        var request = new AddArtistRequest();
        request.stageName = model.stageName;
        request.firstName = model.firstName;
        request.lastName = model.lastName;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<AddArtistRequest>) result;
                    throw new ArtistServiceError(errorResult.Exception);
                });

        var artist = (Optional.of(result)
                .filter(r -> r instanceof AddArtistRequestResult)
                .map(r -> (AddArtistRequestResult) r))
                .get()
                .result;

        return converter.ConvertTo(artist);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody ArtistViewModel
    Delete(@RequestParam(name = "id") String id) throws ArtistServiceError {

        var request = new DeleteArtistRequest();
        request.artistId = id;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<DeleteArtistRequest>) result;
                    throw new ArtistServiceError(errorResult.Exception);
                });

        var artist = (Optional.of(result)
                .filter(r -> r instanceof DeleteArtistRequestResult)
                .map(r -> (DeleteArtistRequestResult) r))
                .get()
                .result;

        return converter.ConvertTo(artist);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody ArtistViewModel
    Update(@RequestBody ArtistViewModel model) throws ArtistServiceError {

        var request = new UpdateArtistRequest();
        request.artistId = model.artistId;
        request.stageName = model.stageName;
        request.firstName = model.firstName;
        request.lastName = model.lastName;

        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<UpdateArtistRequest>) result;
                    throw new ArtistServiceError(errorResult.Exception);
                });

        var artist = (Optional.of(result)
                .filter(r -> r instanceof UpdateArtistRequestResult)
                .map(r -> (UpdateArtistRequestResult) r))
                .get()
                .result;

        return converter.ConvertTo(artist);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ArtistViewModel[]
    GetAll() throws ArtistServiceError {
        logger.trace("Getall method");

        var request = new FindAllArtistsRequest();
        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<FindAllArtistsRequest>) result;
                    throw new ArtistServiceError(errorResult.Exception);
                });

        var artists = (Optional.of(result)
                .filter(r -> r instanceof FindAllArtistsRequestResult)
                .map(r -> (FindAllArtistsRequestResult) r))
                .get()
                .artists;

        return artists.stream()
                .map(converter::ConvertTo).toArray(ArtistViewModel[]::new);
    }

    @Loggable(Loggable.TRACE)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public @ResponseBody Integer
    GetArtistCount() throws ArtistServiceError{
        logger.trace("GetArtistCount method");

        var request = new GetArtistCountRequest();
        var result = mediator.Execute(request);

        Optional.of(result)
                .filter(r -> r instanceof RequestErrorResult)
                .ifPresent((r) -> {
                    var errorResult = (RequestErrorResult<GetArtistCountRequest>) result;
                    throw new ArtistServiceError(errorResult.Exception);
                });

        return Optional.of(result)
                .filter(r -> r instanceof GetArtistCountRequestResult)
                .map(r -> (GetArtistCountRequestResult) r)
                .get()
                .count;
    }
}
