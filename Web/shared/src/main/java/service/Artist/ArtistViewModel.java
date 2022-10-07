package service.Artist;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import shared.converter.Artist.ArtistViewModelDeserializer;
import shared.converter.Artist.ArtistViewModelSerializer;

import java.io.Serializable;

@JsonSerialize(using = ArtistViewModelSerializer.class)
@JsonDeserialize(using = ArtistViewModelDeserializer.class)
public class ArtistViewModel implements Serializable {
    public String artistId;
    public String stageName;
    public String firstName;
    public String lastName;

    public String toString() {
        return "Artist{" + "\n" +
                "   Id = " + artistId + ",\n" +
                "   Stage Name = " + stageName + ",\n" +
                "   First Name = " + firstName + ",\n" +
                "   Last Name = " + lastName + "\n" +
                "} ";
    }
}
