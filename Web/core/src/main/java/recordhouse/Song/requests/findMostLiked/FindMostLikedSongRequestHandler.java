package recordhouse.Song.requests.findMostLiked;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Song.repository.jpa.AdvancedQuerySongEntityRepository;

@Service
public class FindMostLikedSongRequestHandler implements RequestHandler<FindMostLikedSongRequest> {

    private final AdvancedQuerySongEntityRepository songRepository;

    public FindMostLikedSongRequestHandler(AdvancedQuerySongEntityRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public RequestResult<FindMostLikedSongRequest> Handle(FindMostLikedSongRequest command) {

        var song = songRepository.FindMostLikedSong();

        var result = new FindMostLikedSongRequestResult();
        result.Song = song;

        return result;
    }
}
