package recordhouse.ArtistRecordHouseRelation.repository.jpa;

import design.domain.RelationConverter;
import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recordhouse.ArtistRecordHouseRelation.domain.ArtistRecordHouseRelation;
import recordhouse.shared.repository.jpa.RelationRepository;

@Repository("artistRecordHouseRelationRepository")
@Scope("singleton")
@Primary
public class ArtistRecordHouseRelationEntityRepository extends RelationRepository<ArtistRecordHouseRelation> {
    public ArtistRecordHouseRelationEntityRepository(ArtistRecordHouseRelationRepositoryInterface repository, EntityModelConverter<ArtistRecordHouseRelation> entityModelConverter, RelationConverter<ArtistRecordHouseRelation> relationConverter) {
        super((CrudRepository<EntityModelOf<ArtistRecordHouseRelation>, EntityModelOf<ArtistRecordHouseRelation>>) (CrudRepository<? extends EntityModelOf<ArtistRecordHouseRelation>, ? extends EntityModelOf<ArtistRecordHouseRelation>>) repository,
                entityModelConverter,
                relationConverter);
    }
}
