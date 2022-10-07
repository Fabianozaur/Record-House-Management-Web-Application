package recordhouse.PlaylistSongRelation.repository.jpa;

import design.domain.RelationConverter;
import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelation;
import recordhouse.shared.repository.jpa.RelationRepository;

@Repository("playlistSongRepository")
@Scope("singleton")
@Primary
public class PlaylistSongRelationEntityRepository extends RelationRepository<PlaylistSongRelation> {
    public PlaylistSongRelationEntityRepository(PlaylistSongRelationRepositoryInterface repository, EntityModelConverter<PlaylistSongRelation> entityModelConverter, RelationConverter<PlaylistSongRelation> relationConverter) {

        super((CrudRepository<EntityModelOf<PlaylistSongRelation>, EntityModelOf<PlaylistSongRelation>>) (CrudRepository<? extends EntityModelOf<PlaylistSongRelation>, ? extends EntityModelOf<PlaylistSongRelation>>) repository,
                entityModelConverter,
                relationConverter);
    }
}
