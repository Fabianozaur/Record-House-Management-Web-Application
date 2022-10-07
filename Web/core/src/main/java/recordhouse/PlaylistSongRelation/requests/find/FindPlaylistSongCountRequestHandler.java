package recordhouse.PlaylistSongRelation.requests.find;

import design.domain.Composite;
import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;
import recordhouse.shared.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class FindPlaylistSongCountRequestHandler implements RequestHandler<FindPlaylistSongCountRequest> {

    private final Repository<Composite<String, String>, PlaylistSongRelation> relationRepository;

    public FindPlaylistSongCountRequestHandler(Repository<Composite<String, String>, PlaylistSongRelation> relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public RequestResult<FindPlaylistSongCountRequest> Handle(FindPlaylistSongCountRequest command) {
        List<PlaylistSongRelation> playlistSongRelation =
                StreamSupport.stream(relationRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Map<String, Long> playlistsCount = new HashMap<>();
        playlistSongRelation.forEach(r ->
        {
            playlistsCount.putIfAbsent(r.getPlaylistId(), 0L);
            playlistsCount.put(r.getPlaylistId(), playlistsCount.get(r.getPlaylistId()) + 1);
        });
        var response = new FindPlaylistSongCountRequestResult();
        response.PlaylistCounts = playlistsCount;
        return response;
    }
}