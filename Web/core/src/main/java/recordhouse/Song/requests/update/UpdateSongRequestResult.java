package recordhouse.Song.requests.update;

import design.mediator.RequestResult;
import recordhouse.Song.domain.Song;

public class UpdateSongRequestResult implements RequestResult<UpdateSongRequest> {
    public Song Result;
}
