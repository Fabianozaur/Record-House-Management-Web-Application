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
public class FindSongArtistCountsRequestHandler implements RequestHandler<FindSongArtistCountsRequest> {

    private final Repository<Composite<String, String>, SongArtistRelation> relationRepository;

    public FindSongArtistCountsRequestHandler(Repository<Composite<String, String>, SongArtistRelation> relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public RequestResult<FindSongArtistCountsRequest> Handle(FindSongArtistCountsRequest command) {
        List<SongArtistRelation> songArtistRelations =
                StreamSupport.stream(relationRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Map<String, Long> artistCounts = new HashMap<>();
        songArtistRelations.forEach(r ->
                {
                    artistCounts.putIfAbsent(r.getSongId(), 0L);
                    artistCounts.put(r.getSongId(), artistCounts.get(r.getSongId()) + 1);
                });
        var response = new FindSongArtistCountsRequestResult();
        response.ArtistCounts = artistCounts;
        return response;
    }
}
