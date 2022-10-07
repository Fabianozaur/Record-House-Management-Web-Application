package recordhouse.SongArtistRelation.requests.add;

import design.domain.Composite;
import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Artist.domain.Artist;
import recordhouse.Song.domain.Song;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;
import recordhouse.SongArtistRelation.requests.SongArtistRelationRequestError;
import recordhouse.shared.mediator.RequestSuccessResult;
import recordhouse.shared.repository.Repository;

@Service
public class AddSongArtistRelationRequestHandler implements RequestHandler<AddSongArtistRelationRequest> {

    private final Repository<Composite<String, String>, SongArtistRelation> relationRepository;
    private final Repository<String, Song> songRepository;
    private final Repository<String, Artist> artistRepository;

    public AddSongArtistRelationRequestHandler(Repository<Composite<String, String>, SongArtistRelation> relationRepository, Repository<String, Song> songRepository, Repository<String, Artist> artistRepository) {
        this.relationRepository = relationRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public RequestResult<AddSongArtistRelationRequest> Handle(AddSongArtistRelationRequest command) {
        var relation = new SongArtistRelation();
        relation.setSongId(command.SongId);
        relation.setArtistId(command.ArtistId);

        songRepository
                .findOne(relation.getSongId())
                .orElseThrow(() -> {
                    throw new SongArtistRelationRequestError("There is no song with ID " + relation.getSongId() + "!");
                });
        artistRepository
                .findOne(relation.getArtistId())
                .orElseThrow(() -> {
                    throw new SongArtistRelationRequestError("There is no artist with ID " + relation.getArtistId() + "!");
                });

        relationRepository.save(relation);
        return new RequestSuccessResult<>();
    }
}
