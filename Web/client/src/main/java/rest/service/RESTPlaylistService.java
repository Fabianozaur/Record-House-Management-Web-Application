package rest.service;

import org.springframework.web.bind.annotation.*;
import service.Playlist.PlaylistService;
import service.Playlist.PlaylistServiceError;
import service.Playlist.PlaylistViewModel;
import service.Song.SongServiceError;
import service.Song.SongViewModel;

@RequestMapping(value = "/playlist")
public abstract class RESTPlaylistService implements PlaylistService {
    @RequestMapping(method = RequestMethod.POST, headers = {"content-type=application/json", "accept=application/json"})
    public abstract @ResponseBody PlaylistViewModel
    Add(@RequestBody PlaylistViewModel model) throws PlaylistServiceError;

    @RequestMapping(method = RequestMethod.DELETE)
    public abstract @ResponseBody PlaylistViewModel
    Delete(@RequestParam(name = "id") String id) throws PlaylistServiceError;

    @RequestMapping(method = RequestMethod.PUT)
    public abstract @ResponseBody PlaylistViewModel
    Update(@RequestBody PlaylistViewModel model) throws PlaylistServiceError;

    @RequestMapping(method = RequestMethod.GET)
    public abstract @ResponseBody PlaylistViewModel[]
    GetAll() throws PlaylistServiceError;

    @RequestMapping(value="/count", method = RequestMethod.GET)
    public abstract @ResponseBody Integer
    GetPlaylistCount() throws PlaylistServiceError;
}
