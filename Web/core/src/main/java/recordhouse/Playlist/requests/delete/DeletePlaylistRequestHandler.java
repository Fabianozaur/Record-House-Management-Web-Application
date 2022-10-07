package recordhouse.Playlist.requests.delete;


import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.Playlist.requests.PlaylistRequestError;
import recordhouse.shared.repository.Repository;

@Service
public class DeletePlaylistRequestHandler implements RequestHandler<DeletePlaylistRequest> {

    private final Repository<String, Playlist> playlistRepository;

    public DeletePlaylistRequestHandler(Repository<String, Playlist> playlistRepository){
        this.playlistRepository = playlistRepository;
    }


    @Override
    public RequestResult<DeletePlaylistRequest> Handle(DeletePlaylistRequest command) {
        var result = playlistRepository.delete(command.playlistId)
                .orElseThrow(() -> new PlaylistRequestError("Failed to delete playlist!"));

        var response = new DeletePlaylistRequestResult();
        response.result = result;
        return response;
    }
}
