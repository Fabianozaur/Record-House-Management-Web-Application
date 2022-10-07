package service.ArtistRecordHouse;

public interface ArtistRecordHouseRelationService {

    void Add(ArtistRecordHouseRelationViewModel relation) throws ArtistRecordHouseRelationServiceError;
    ArtistRecordHouseRelationViewModel[] GetAll() throws ArtistRecordHouseRelationServiceError;
}
