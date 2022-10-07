package recordhouse.Song.domain;

import design.persistance.jpa.EntityModelOf;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

// This is PostgreSQL NOT JPQL
//@NamedNativeQuery(name = "Song.findLongest",
//            query = "SELECT * FROM Songs WHERE Id = (SELECT Id FROM Songs ORDER BY Duration LIMIT 1)")
@Entity(name="Songs")
public class SongEntityModel implements EntityModelOf<Song> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    public String Id;

    @Column
    public String Title;

    @Column
    public Date PublishDate;

    @Column
    public int Duration;

    @Column
    public int Likes;
}
