package service.SongArtist;

import java.util.Map;

public interface SongArtistRelationService {

    Map<String, Long> GetAllArtistSongCounts() throws SongArtistRelationServiceError;
    Map<String, Long> GetAllSongArtistCounts() throws SongArtistRelationServiceError;
    void Add(SongArtistRelationViewModel relation) throws SongArtistRelationServiceError;
    SongArtistRelationViewModel[] GetAll() throws SongArtistRelationServiceError;

}
