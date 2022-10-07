package service.Playlist;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import shared.converter.Playlist.PlaylistViewModelDeserializer;
import shared.converter.Playlist.PlaylistViewModelSerializer;

import java.io.Serializable;

@JsonSerialize(using= PlaylistViewModelSerializer.class)
@JsonDeserialize(using= PlaylistViewModelDeserializer.class)
public class PlaylistViewModel implements Serializable {
    public String Id;
    public String Name;
    public boolean isPublic;
    public String toString() {
        return "Playlist{" + "\n" +
                "   Id = " + Id + ",\n" +
                "   Playlist Name = " + Name + ",\n" +
                "   isPublic = " + isPublic + "\n" +
                "} ";
    }
}
