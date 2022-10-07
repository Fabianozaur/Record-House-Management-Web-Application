package recordhouse.Artist.domain;

import design.persistance.jpa.EntityModelOf;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ArtistEntityModel implements EntityModelOf<Artist> {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    public String artistId;

    @Column
    public String stageName;

    @Column
    public String firstName;

    @Column
    public String lastName;
}
