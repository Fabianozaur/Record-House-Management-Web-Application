package recordhouse.Playlist.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import recordhouse.Playlist.domain.PlaylistEntityModel;

public interface PlaylistEntityRepositoryInterface extends CrudRepository<PlaylistEntityModel,String> {

}
