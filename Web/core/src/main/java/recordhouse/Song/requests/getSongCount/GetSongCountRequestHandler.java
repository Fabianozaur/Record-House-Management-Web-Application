package recordhouse.Song.requests.getSongCount;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Song.domain.Song;
import recordhouse.shared.repository.Repository;

import java.util.stream.StreamSupport;

@Service
public class GetSongCountRequestHandler implements RequestHandler<GetSongCountRequest> {

    private final Repository<String, Song> songRepository;

    public GetSongCountRequestHandler(Repository<String, Song> songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public RequestResult<GetSongCountRequest> Handle(GetSongCountRequest command) {

        var result = new GetSongCountRequestResult();
        result.Count = (int) StreamSupport.stream(songRepository.findAll().spliterator(), false).count();
        return result;
    }
}
