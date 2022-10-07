package recordhouse.Song.requests.findLongest;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Song.repository.jpa.AdvancedQuerySongEntityRepository;

@Service
public class FindLongestSongRequestHandler implements RequestHandler<FindLongestSongRequest> {

    private final AdvancedQuerySongEntityRepository songRepository;

    public FindLongestSongRequestHandler(AdvancedQuerySongEntityRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public RequestResult<FindLongestSongRequest> Handle(FindLongestSongRequest command) {

        var song = songRepository.FindLongestSong();

        var result = new FindLongestSongRequestResult();
        result.Song = song;

        return result;
    }
}
