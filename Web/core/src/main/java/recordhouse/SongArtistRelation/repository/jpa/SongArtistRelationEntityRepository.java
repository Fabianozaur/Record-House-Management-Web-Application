package recordhouse.SongArtistRelation.repository.jpa;


import design.domain.RelationConverter;
import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recordhouse.SongArtistRelation.domain.SongArtistRelation;
import recordhouse.shared.repository.jpa.RelationRepository;

@Repository("songArtistRelationRepository")
@Scope("singleton")
@Primary
public class SongArtistRelationEntityRepository extends RelationRepository<SongArtistRelation> {
    public SongArtistRelationEntityRepository(SongArtistRelationRepositoryInterface repository, EntityModelConverter<SongArtistRelation> entityModelConverter, RelationConverter<SongArtistRelation> relationConverter) {
        super((CrudRepository<EntityModelOf<SongArtistRelation>, EntityModelOf<SongArtistRelation>>) (CrudRepository<? extends EntityModelOf<SongArtistRelation>, ? extends EntityModelOf<SongArtistRelation>>) repository,
                entityModelConverter,
                relationConverter);
    }
}