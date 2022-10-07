package service.Playlist;

public interface PlaylistService {
    PlaylistViewModel Add(PlaylistViewModel model) throws PlaylistServiceError;
    PlaylistViewModel Delete(String id) throws PlaylistServiceError;
    PlaylistViewModel Update(PlaylistViewModel model) throws PlaylistServiceError;
    PlaylistViewModel[] GetAll() throws PlaylistServiceError;
    Integer GetPlaylistCount() throws PlaylistServiceError;

}
