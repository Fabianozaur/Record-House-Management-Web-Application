package recordhouse.SongArtistRelation.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import recordhouse.SongArtistRelation.domain.SongArtistRelationEntityModel;

public interface SongArtistRelationRepositoryInterface extends CrudRepository<SongArtistRelationEntityModel, SongArtistRelationEntityModel> {
}
