package recordhouse.SongArtistRelation.domain;

import design.persistance.jpa.EntityModelOf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(SongArtistRelationEntityModel.class)
public class SongArtistRelationEntityModel implements EntityModelOf<SongArtistRelation> {

    @Id
    @Column
    public String SongId;

    @Id
    @Column
    public String ArtistId;

    @Override
    public int hashCode() {
        int result = SongId.hashCode();
        result = 31 * result + ArtistId.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongArtistRelationEntityModel model = (SongArtistRelationEntityModel) o;

        if (!SongId.equals(model.SongId)) return false;
        return ArtistId.equals(model.ArtistId);
    }
}
