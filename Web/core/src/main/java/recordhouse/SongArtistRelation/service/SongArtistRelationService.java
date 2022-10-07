//package recordhouse.SongArtistRelation.service;
//
//import design.domain.Composite;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import recordhouse.Artist.domain.Artist;
//import recordhouse.Song.domain.Song;
//import recordhouse.SongArtistRelation.domain.SongArtistRelation;
//import recordhouse.shared.repository.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Stream;
//import java.util.stream.StreamSupport;
//
//public class SongArtistRelationService {
//    Repository<Composite<String, String>, SongArtistRelation> relationRepository;
//    Repository<String, Artist> artistRepository;
//    Repository<String, Song> songRepository;
//
//    @Autowired
//    public SongArtistRelationService(
//            @Qualifier("artistXmlRepository") Repository<String, Artist> artistRepository,
//            @Qualifier("songXmlRepository") Repository<String, Song> songRepository,
//            @Qualifier("songArtistRelationXmlRepository") Repository<Composite<String, String>, SongArtistRelation> relationRepository) {
//        this.songRepository = songRepository;
//        this.artistRepository = artistRepository;
//        this.relationRepository = relationRepository;
//    }
//
//    public void addSongArtistRelation(SongArtistRelation relation) {
//        this.songRepository
//                .findOne(relation.getSongId())
//                .orElseThrow(() -> {
//                    throw new SongArtistRelationServiceException("There is no song with ID " + relation.getSongId() + "!");
//                });
//        this.artistRepository
//                .findOne(relation.getArtistId())
//                .orElseThrow(() -> {
//                    throw new SongArtistRelationServiceException("There is no artist with ID " + relation.getArtistId() + "!");
//                });
//        this.relationRepository.save(relation);
//    }
//
//    public Stream<SongArtistRelation> getAllRelations() {
//        Iterable<SongArtistRelation> relations = relationRepository.findAll();
//        return StreamSupport.stream(relations.spliterator(), false);
//    }
//
//    public Map<String, Long> GetSongArtistCount() {
//        List<SongArtistRelation> songArtistRelations = new ArrayList<SongArtistRelation>();
//        relationRepository.findAll().forEach(songArtistRelations::add);
//        Iterable<Song> songs = songRepository.findAll();
//        Map<String, Long> songIdArtistCount = new HashMap<>();
//        songs.forEach((s) ->
//                songIdArtistCount.put(s.getId(),
//                        songArtistRelations.stream()
//                                .filter(r -> r.getSongId().equals(s.getId()))
//                                .count())
//        );
//        return songIdArtistCount;
//    }
//
//    public Map<String, Long> getArtistSongCount() {
//        List<SongArtistRelation> songArtistRelations = new ArrayList<SongArtistRelation>();
//        relationRepository.findAll().forEach(songArtistRelations::add);
//        Iterable<Artist> artists = artistRepository.findAll();
//        Map<String, Long> artistIdSongCount = new HashMap<>();
//        artists.forEach((s) ->
//                artistIdSongCount.put(s.getId(),
//                        songArtistRelations.stream()
//                                .filter(r -> r.getArtistId().equals(s.getId()))
//                                .count())
//        );
//        return artistIdSongCount;
//    }
//}
