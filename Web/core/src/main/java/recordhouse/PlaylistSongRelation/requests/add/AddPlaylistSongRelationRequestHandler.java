package recordhouse.PlaylistSongRelation.requests.add;

import design.domain.Composite;
import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;
import recordhouse.PlaylistSongRelation.requests.PlaylistSongRelationRequestError;
import recordhouse.Song.domain.Song;
import recordhouse.shared.mediator.RequestSuccessResult;
import recordhouse.shared.repository.Repository;

@Service
public class AddPlaylistSongRelationRequestHandler implements RequestHandler<AddPlaylistSongRelationRequest> {

    private final Repository<Composite<String, String>, PlaylistSongRelation> relationRepository;
    private final Repository<String, Playlist> playlistRepository;
    private final Repository<String, Song> songRepository;

    public AddPlaylistSongRelationRequestHandler(Repository<Composite<String, String>, PlaylistSongRelation> relationRepository, Repository<String, Playlist> playlistRepository, Repository<String, Song> songRepository) {
        this.relationRepository = relationRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public RequestResult<AddPlaylistSongRelationRequest> Handle(AddPlaylistSongRelationRequest command) {
        var relation = new PlaylistSongRelation();
        relation.setPlaylistId(command.playlistId);
        relation.setSongId(command.songId);

        playlistRepository
                .findOne(relation.getPlaylistId())
                .orElseThrow(() -> {
                    throw new PlaylistSongRelationRequestError("There is no playlist with ID " + relation.getPlaylistId() + "!");
                });
        songRepository
                .findOne(relation.getSongId())
                .orElseThrow(() -> {
                    throw new PlaylistSongRelationRequestError("There is no song with ID " + relation.getSongId() + "!");
                });

        relationRepository.save(relation);
        return new RequestSuccessResult<>();
    }
}