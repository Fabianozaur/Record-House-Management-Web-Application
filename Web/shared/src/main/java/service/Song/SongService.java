package service.Song;

public interface SongService {

    SongViewModel Add(SongViewModel model) throws SongServiceError;
    SongViewModel Delete(String id) throws SongServiceError;
    SongViewModel Update(SongViewModel model) throws SongServiceError;
    SongViewModel[] GetAll() throws SongServiceError;
    Integer GetSongCount() throws SongServiceError;

    SongViewModel GetLongestSong();
    SongViewModel GetMostLikedSong();
    SongViewModel[] GetByName(String name);
}
