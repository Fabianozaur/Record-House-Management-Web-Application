package recordhouse.Playlist.requests.update;

import design.mediator.RequestResult;
import recordhouse.Playlist.domain.Playlist;

public class UpdatePlaylistRequestResult implements RequestResult<UpdatePlaylistRequest> {
    public Playlist result;
}
