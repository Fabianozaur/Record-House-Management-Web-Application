package rest.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.SongArtist.SongArtistRelationService;
import service.SongArtist.SongArtistRelationViewModel;

import java.util.Map;

@RequestMapping("/song-artist")
public abstract class RESTSongArtistRelationService implements SongArtistRelationService {

    @RequestMapping(method = RequestMethod.POST)
    public abstract void Add(@RequestBody SongArtistRelationViewModel model);

    @RequestMapping(method = RequestMethod.GET)
    public abstract SongArtistRelationViewModel[] GetAll();

    @RequestMapping(value = "artist-counts", method = RequestMethod.GET)
    public abstract Map<String, Long> GetAllSongArtistCounts();

    @RequestMapping(value = "song-counts", method = RequestMethod.GET)
    public abstract Map<String, Long> GetAllArtistSongCounts();
}
