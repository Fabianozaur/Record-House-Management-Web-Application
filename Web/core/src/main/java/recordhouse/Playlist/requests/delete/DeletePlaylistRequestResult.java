package recordhouse.Playlist.requests.delete;

import design.mediator.RequestResult;
import recordhouse.Playlist.domain.Playlist;

public class DeletePlaylistRequestResult implements RequestResult<DeletePlaylistRequest> {
    public Playlist result;
}
