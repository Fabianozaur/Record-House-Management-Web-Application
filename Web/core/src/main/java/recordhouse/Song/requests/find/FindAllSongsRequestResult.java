package recordhouse.Song.requests.find;

import design.mediator.RequestResult;
import recordhouse.Song.domain.Song;

import java.util.List;

public class FindAllSongsRequestResult implements RequestResult<FindAllSongsRequest> {
    public List<Song> Songs;
}
