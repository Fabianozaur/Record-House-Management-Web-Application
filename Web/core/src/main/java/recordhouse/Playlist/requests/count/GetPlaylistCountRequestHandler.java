package recordhouse.Playlist.requests.count;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.shared.repository.Repository;

import java.util.stream.StreamSupport;

@Service
public class GetPlaylistCountRequestHandler implements RequestHandler<GetPlaylistCountRequest> {

    private final Repository<String, Playlist> playlistRepository;

    public GetPlaylistCountRequestHandler(Repository<String, Playlist> playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public RequestResult<GetPlaylistCountRequest> Handle(GetPlaylistCountRequest command) {

        var result = new GetPlaylistCountRequestResult();
        result.Count = (int) StreamSupport.stream(playlistRepository.findAll().spliterator(), false).count();
        return result;
    }
}