package recordhouse.Song.requests.searchByName;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Song.repository.jpa.AdvancedQuerySongEntityRepository;
import spring.ApplicationContext;

@Service
public class SearchSongByNameRequestHandler implements RequestHandler<SearchSongByNameRequest> {

    private final AdvancedQuerySongEntityRepository songRepository;

    public SearchSongByNameRequestHandler() {
        this.songRepository = ApplicationContext.getInstance(AdvancedQuerySongEntityRepository.class);
    }

    @Override
    public RequestResult<SearchSongByNameRequest> Handle(SearchSongByNameRequest command) {

        var songs = songRepository.SearchSongByName(command.Name);

        var result = new SearchSongByNameRequestResult();
        result.Songs = songs;

        return result;
    }
}
