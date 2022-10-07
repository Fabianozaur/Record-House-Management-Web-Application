package recordhouse.Artist.repository.jpa;

import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recordhouse.Artist.domain.Artist;
import recordhouse.shared.repository.jpa.EntityRepository;

@Repository("artistEntityRepository")
@Scope("singleton")
@Primary
public class ArtistEntityRepository extends EntityRepository<String, Artist> {
    public ArtistEntityRepository(ArtistEntityRepositoryInterface repository, EntityModelConverter<Artist> entityModelConverter){
        super((CrudRepository<EntityModelOf<Artist>, String>) (CrudRepository<? extends EntityModelOf<Artist>, String>) repository,
                entityModelConverter);
    }
}
