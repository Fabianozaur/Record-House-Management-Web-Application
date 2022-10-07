package recordhouse.Playlist.repository.jpa;

import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recordhouse.Playlist.domain.Playlist;
import recordhouse.shared.repository.jpa.EntityRepository;

@Repository("playlistEntityRepository")
@Scope("singleton")
public class PlaylistEntityRepository extends EntityRepository<String, Playlist> {
    public PlaylistEntityRepository(PlaylistEntityRepositoryInterface repository, EntityModelConverter<Playlist> entityModelConverter){
        super((CrudRepository<EntityModelOf<Playlist>, String>) (CrudRepository<? extends EntityModelOf<Playlist>, String>) repository,
                entityModelConverter);
    }

}
