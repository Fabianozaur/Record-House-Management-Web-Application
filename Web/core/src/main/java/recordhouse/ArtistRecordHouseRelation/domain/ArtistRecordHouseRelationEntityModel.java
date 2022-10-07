package recordhouse.ArtistRecordHouseRelation.domain;

import design.persistance.jpa.EntityModelOf;
import recordhouse.SongArtistRelation.domain.SongArtistRelationEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ArtistRecordHouseRelationEntityModel.class)
public class ArtistRecordHouseRelationEntityModel implements EntityModelOf<ArtistRecordHouseRelation> {

    @Id
    @Column
    public String artistId;

    @Id
    @Column
    public String recordHouseId;

    @Override
    public int hashCode() {
        int result = artistId.hashCode();
        result = 31 * result + recordHouseId.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistRecordHouseRelationEntityModel model = (ArtistRecordHouseRelationEntityModel) o;

        if (!artistId.equals(model.artistId)) return false;
        return recordHouseId.equals(model.recordHouseId);
    }
}
