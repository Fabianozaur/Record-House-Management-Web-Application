package recordhouse.PlaylistSongRelation.service;//package recordhouse.PlaylistSongRelation.service;
//
//import design.domain.Composite;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//import recordhouse.Playlist.domain.Playlist;
//import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;
//import recordhouse.Song.domain.Song;
//import recordhouse.shared.repository.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Stream;
//import java.util.stream.StreamSupport;
//
//@Service
//@Scope("singleton")
//public class PlaylistSongRelationService {
//    Repository<Composite<String,String>, PlaylistSongRelation> relationRepository;
//    Repository<String, Playlist> playlistRepository;
//    Repository<String, Song> songRepository;
//
//    @Autowired
//    public PlaylistSongRelationService(
//            @Qualifier("playlistXmlRepository") Repository<String,Playlist> playlistRepository,
//            @Qualifier("songXmlRepository") Repository<String, Song> songRepository,
//            @Qualifier("playlistSongRelationXmlRepository")Repository<Composite<String,String> ,PlaylistSongRelation> relationRepository)
//    {
//        this.playlistRepository=playlistRepository;
//        this.songRepository=songRepository;
//        this.relationRepository=relationRepository;
//    }
//    public void addPlaylistSongRelation(PlaylistSongRelation relation)
//    {
//        this.playlistRepository.findOne(relation.getPlaylistId()).ifPresentOrElse(
//                r->this.songRepository.findOne(relation.getSongId()).ifPresentOrElse(
//                        p->this.relationRepository.save(relation),
//                        ()->{throw new PlaylistServiceException("There is no Song with the id :"+relation.getSongId());
//                        }),
//                ()->{ throw new PlaylistServiceException("There is no Playlists with the id : "+relation.getPlaylistId());});
//        relationRepository.save(relation);
//    }
//
//    public Stream<PlaylistSongRelation> getAllTheRelations() {
//        Iterable<PlaylistSongRelation> relations= relationRepository.findAll();
//        return StreamSupport.stream(relations.spliterator(), false);
//    }
//
//    public Map<String ,Integer> getPlaylistSongCount(){
//        List<PlaylistSongRelation> playlistSongRelations= new ArrayList<>();
//        relationRepository.findAll().forEach(playlistSongRelations::add);
//        Iterable<Playlist> playlists = playlistRepository.findAll();
//        Map<String, Integer> playlistIdCount = new HashMap<>();
//        playlists.forEach((p) ->
//                playlistIdCount.put(p.getId(),
//                        (int) playlistSongRelations.stream()
//                                .filter(r -> r.getPlaylistId().equals(p.getId()))
//                                .count())
//        );
//        return playlistIdCount;
//    }
//
//
//
//}
