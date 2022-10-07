package recordhouse.Song.requests.add;

import design.mediator.RequestResult;
import recordhouse.Song.domain.Song;

public class AddSongRequestResult implements RequestResult<AddSongRequest> {
    public Song Result;
}
