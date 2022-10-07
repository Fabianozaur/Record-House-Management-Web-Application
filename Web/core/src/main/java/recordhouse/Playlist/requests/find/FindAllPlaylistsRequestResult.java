package recordhouse.Playlist.requests.find;

import design.mediator.RequestResult;
import recordhouse.Playlist.domain.Playlist;

import java.util.List;

public class FindAllPlaylistsRequestResult implements RequestResult<FindAllPlaylistsRequest> {
    public List<Playlist> playlists;
}
