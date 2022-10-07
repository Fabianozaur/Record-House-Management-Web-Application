package recordhouse.Artist.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import recordhouse.Artist.domain.ArtistEntityModel;

public interface ArtistEntityRepositoryInterface extends CrudRepository<ArtistEntityModel, String> {
}
