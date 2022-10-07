package recordhouse.Song.requests.find;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import recordhouse.Song.domain.Song;
import recordhouse.shared.repository.Repository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope("singleton")
public class FindAllSongsRequestHandler implements RequestHandler<FindAllSongsRequest> {

    private final Repository<String, Song> songRepository;

    public FindAllSongsRequestHandler(Repository<String, Song> songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public RequestResult<FindAllSongsRequest> Handle(FindAllSongsRequest command) {
        var songs = StreamSupport.stream(songRepository.findAll().spliterator(), false);

        var response = new FindAllSongsRequestResult();
        response.Songs = songs.collect(Collectors.toList());
        return response;
    }
}
