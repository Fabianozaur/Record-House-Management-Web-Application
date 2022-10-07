package rest.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.PlaylistSong.PlaylistSongRelationService;
import service.PlaylistSong.PlaylistSongRelationViewModel;
import service.SongArtist.SongArtistRelationViewModel;

import java.util.Map;

@RequestMapping("/playlist-song")
public abstract class RESTPlaylistSongRelationService implements PlaylistSongRelationService {
    @RequestMapping(method = RequestMethod.POST)
    public abstract void Add(@RequestBody PlaylistSongRelationViewModel model);

    @RequestMapping(method = RequestMethod.GET)
    public abstract PlaylistSongRelationViewModel[] GetAll();

    @RequestMapping(value = "playlist-counts", method = RequestMethod.GET)
    public abstract Map<String, Long> GetAllPlaylistSongCounts();
}
