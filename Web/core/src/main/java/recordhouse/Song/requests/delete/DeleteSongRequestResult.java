package recordhouse.Song.requests.delete;

import design.mediator.RequestResult;
import recordhouse.Song.domain.Song;

public class DeleteSongRequestResult implements RequestResult<DeleteSongRequest> {
    public Song Result;
}
