package recordhouse.SongArtistRelation.requests.find;

import design.domain.Composite;
import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;
import recordhouse.shared.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FindArtistSongCountsRequestHandler implements RequestHandler<FindArtistSongCountsRequest> {

    private final Repository<Composite<String, String>, SongArtistRelation> relationRepository;

    public FindArtistSongCountsRequestHandler(Repository<Composite<String, String>, SongArtistRelation> relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public RequestResult<FindArtistSongCountsRequest> Handle(FindArtistSongCountsRequest command) {
        List<SongArtistRelation> songArtistRelations =
                StreamSupport.stream(relationRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Map<String, Long> songCounts = new HashMap<>();
        songArtistRelations.forEach(r ->
        {
            songCounts.putIfAbsent(r.getArtistId(), 0L);
            songCounts.put(r.getArtistId(), songCounts.get(r.getArtistId()) + 1);
        });
        var response = new FindArtistSongCountsRequestResult();
        response.songCounts = songCounts;
        return response;
    }
}
