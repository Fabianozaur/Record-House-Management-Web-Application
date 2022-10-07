package recordhouse.Song.requests.findMostLiked;

import design.mediator.RequestResult;
import recordhouse.Song.domain.Song;

public class FindMostLikedSongRequestResult implements RequestResult<FindMostLikedSongRequest> {
    public Song Song;
}
