package recordhouse.PlaylistSongRelation.domain;

import design.persistance.jpa.EntityModelOf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(PlaylistSongRelationEntityModel.class)
public class PlaylistSongRelationEntityModel implements EntityModelOf<PlaylistSongRelation> {
    @Id
    @Column
    public String playlistId;

    @Id
    @Column
    public String songId;

    @Override
    public int hashCode() {
        int result = playlistId.hashCode();
        result = 31 * result + songId.hashCode();
        return result;

    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        PlaylistSongRelationEntityModel model=(PlaylistSongRelationEntityModel) o;

        if(!playlistId.equals(model.playlistId)) return false;

        return playlistId.equals(model.playlistId);
    }
}
