package recordhouse.Playlist.requests.update;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.Playlist.requests.PlaylistRequestError;
import recordhouse.shared.repository.Repository;

@Service
public class UpdatePlaylistRequestHandler implements RequestHandler<UpdatePlaylistRequest> {

    private final Repository<String, Playlist> playlistRepository;

    public UpdatePlaylistRequestHandler(Repository<String, Playlist> playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public RequestResult<UpdatePlaylistRequest> Handle(UpdatePlaylistRequest command) {
        Playlist playlist = new Playlist();

        playlist.setId(command.playlistId);
        playlist.setPlaylistName(command.name);
        playlist.setIsPublic(command.isPublic);

        var result = playlistRepository.save(playlist)
                .orElseThrow(() -> new PlaylistRequestError("Failed to update playlist!"));

        var response = new UpdatePlaylistRequestResult();
        response.result = result;
        return response;
    }
}
