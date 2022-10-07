package service.PlaylistSong;

import java.util.Map;

public interface PlaylistSongRelationService {
    Map<String, Long> GetAllPlaylistSongCounts() throws PlaylistSongRelationServiceError;
    void Add(PlaylistSongRelationViewModel relation) throws PlaylistSongRelationServiceError;
    PlaylistSongRelationViewModel[] GetAll() throws PlaylistSongRelationServiceError;

}
