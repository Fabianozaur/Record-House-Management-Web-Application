package recordhouse.Song.repository.jpa;

import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recordhouse.Song.domain.Song;
import recordhouse.shared.repository.jpa.EntityRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository("songEntityRepository")
@Scope("singleton")
@Primary
public class SongEntityRepository extends EntityRepository<String, Song> implements AdvancedQuerySongEntityRepository {

    private final SongEntityRepositoryInterface repositoryInterface;
    public SongEntityRepository(SongEntityRepositoryInterface repository, EntityModelConverter<Song> entityModelConverter) {
        super((CrudRepository<EntityModelOf<Song>, String>) (CrudRepository<? extends EntityModelOf<Song>, String>) repository,
                entityModelConverter);
        this.repositoryInterface = repository;
    }

    public List<Song> SearchSongByName(String name){
        return StreamSupport.stream(
                repositoryInterface
                        .findByTitleContaining(name).spliterator(), false)
                .map(converter::ConvertFrom)
                .collect(Collectors.toList());
    }

    public Song FindMostLikedSong(){
        return repositoryInterface.findMostLikedSong()
                .map(converter::ConvertFrom)
                .orElse(null);
    }

    public Song FindLongestSong(){
        return repositoryInterface.findLongest()
                .map(converter::ConvertFrom)
                .orElse(null);
    }
}
