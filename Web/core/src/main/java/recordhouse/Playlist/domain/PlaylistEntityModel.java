package recordhouse.Playlist.domain;

import design.persistance.jpa.EntityModelOf;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Playlist")
public class PlaylistEntityModel implements EntityModelOf<Playlist> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "org.hibernate.id.UUIDGenerator")
    public String PlaylistId;

    @Column
    public String playlistName;

    @Column
    public boolean isPublic;
    @Override
    public String toString() {
        return "Playlist{" +
                "id='" + PlaylistId + '\'' +
                ", name='" + playlistName + '\'' +
                ", isPublic=" + isPublic +
                '}';
    }


}

