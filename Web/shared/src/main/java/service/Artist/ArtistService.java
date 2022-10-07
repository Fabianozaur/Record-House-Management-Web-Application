package service.Artist;

public interface ArtistService {

    ArtistViewModel Add(ArtistViewModel model) throws ArtistServiceError;
    ArtistViewModel Delete(String id) throws ArtistServiceError;
    ArtistViewModel Update(ArtistViewModel model) throws ArtistServiceError;
    ArtistViewModel[] GetAll() throws ArtistServiceError;
    Integer GetArtistCount() throws ArtistServiceError;
}
