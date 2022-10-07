package recordhouse.ArtistRecordHouseRelation.domain;

import design.domain.Relation;

import java.util.Optional;

public class ArtistRecordHouseRelation extends Relation<String, String> {
    private String artistId;
    private String recordHouseId;

    public ArtistRecordHouseRelation(){
    }

    public ArtistRecordHouseRelation(String artistId, String recordHouseId) {
        this.recordHouseId = recordHouseId;
        this.artistId = artistId;
    }

    public String GetArtistId(){
        return Optional.ofNullable(artistId).orElse("");
    }
    public void SetArtistId(String value){
        artistId = value;
    }

    public String GetRecordHouseId(){
        return Optional.ofNullable(recordHouseId).orElse("");
    }
    public void SetRecordHouseId(String value){
        recordHouseId = value;
    }

    @Override
    public String toString() {
        return "ArtistRecordHouseRelation{\n" +
                "   Artist Id = " + artistId + ",\n" +
                "   Record House Id = " + recordHouseId + "\n" +
                '}';
    }
}