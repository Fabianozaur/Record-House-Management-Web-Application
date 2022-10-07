package recordhouse.Song.requests.searchByName;

import design.mediator.RequestResult;
import recordhouse.Song.domain.Song;

import java.util.List;

public class SearchSongByNameRequestResult implements RequestResult<SearchSongByNameRequest> {
    public List<Song> Songs;
}
