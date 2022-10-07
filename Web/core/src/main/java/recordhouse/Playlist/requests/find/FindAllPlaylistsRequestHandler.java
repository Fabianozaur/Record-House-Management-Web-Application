package recordhouse.Playlist.requests.find;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.shared.repository.Repository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope("singleton")
public class FindAllPlaylistsRequestHandler implements RequestHandler<FindAllPlaylistsRequest> {

    private final Repository<String, Playlist> playlistRepository;

    public FindAllPlaylistsRequestHandler(Repository<String, Playlist> playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public RequestResult<FindAllPlaylistsRequest> Handle(FindAllPlaylistsRequest command) {
        var playlist = StreamSupport.stream(playlistRepository.findAll().spliterator(), false);

        var response = new FindAllPlaylistsRequestResult();
        response.playlists = playlist.collect(Collectors.toList());
        return response;
    }
}
