package service.ArtistRecordHouse;

import java.io.Serializable;

public class ArtistRecordHouseRelationViewModel implements Serializable {
    public String artistId;
    public String recordHouseId;

    public String toString() {
        return "ArtistRecordHouseRelation{\n" +
                "   Artist Id = " + artistId + ",\n" +
                "   Record House Id = " + recordHouseId + "\n" +
                '}';
    }
}
