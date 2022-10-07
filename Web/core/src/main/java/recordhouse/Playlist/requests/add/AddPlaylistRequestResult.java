package recordhouse.Playlist.requests.add;

import design.mediator.RequestResult;
import recordhouse.Playlist.domain.Playlist;

public class AddPlaylistRequestResult implements RequestResult<AddPlaylistRequest> {
    public Playlist result;
}
