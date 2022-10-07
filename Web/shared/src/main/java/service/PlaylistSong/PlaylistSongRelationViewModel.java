package service.PlaylistSong;

import java.io.Serializable;

public class PlaylistSongRelationViewModel implements Serializable {
    public String playlistId;
    public String songId;

    public String toString() {
        return "PlaylistSongRelation{\n" +
                "   Playlist Id = " + playlistId + ",\n" +
                "   Song Id = " + songId + "\n" +
                '}';
    }
}
