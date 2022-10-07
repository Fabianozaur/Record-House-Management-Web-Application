package recordhouse.PlaylistSongRelation.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import recordhouse.PlaylistSongRelation.domain.PlaylistSongRelationEntityModel;

public interface PlaylistSongRelationRepositoryInterface extends CrudRepository<PlaylistSongRelationEntityModel, PlaylistSongRelationEntityModel> {
}