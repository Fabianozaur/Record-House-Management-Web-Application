package recordhouse.Playlist.requests.add;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.Playlist.requests.PlaylistRequestError;
import recordhouse.shared.repository.Repository;

@Service
public class AddPlaylistRequestHandler implements RequestHandler<AddPlaylistRequest> {

    private final Repository<String, Playlist> artistRepository;

    public AddPlaylistRequestHandler(Repository<String, Playlist> artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public RequestResult<AddPlaylistRequest> Handle(AddPlaylistRequest command) {
        Playlist playlist = new Playlist();

        playlist.setPlaylistName(command.playlistName);
        playlist.setIsPublic(command.isPublic);

        var result = artistRepository.save(playlist)
                .orElseThrow(() -> new PlaylistRequestError("Failed to create playlist!"));

        var response = new AddPlaylistRequestResult();
        response.result = result;
        return response;
    }
}
