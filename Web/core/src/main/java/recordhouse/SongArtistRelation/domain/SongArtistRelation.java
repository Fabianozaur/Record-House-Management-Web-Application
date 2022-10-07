package recordhouse.SongArtistRelation.domain;

import design.domain.Relation;

public class SongArtistRelation extends Relation<String, String> {
    private String songId;
    private String artistId;

    public SongArtistRelation() {
    }

    public SongArtistRelation(String songId, String artistId) {
        this.songId = songId;
        this.artistId = artistId;
    }

    public String getSongId() {
        return this.songId;
    }

    public String getArtistId() {
        return this.artistId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        return "SongArtistRelation{\n" +
                "   Song Id = " + songId + ",\n" +
                "   Artist Id = " + artistId + "\n" +
                '}';
    }
}
