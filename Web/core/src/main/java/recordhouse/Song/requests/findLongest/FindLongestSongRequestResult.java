package recordhouse.Song.requests.findLongest;

import design.mediator.RequestResult;
import recordhouse.Song.domain.Song;

public class FindLongestSongRequestResult implements RequestResult<FindLongestSongRequest> {
    public Song Song;
}
