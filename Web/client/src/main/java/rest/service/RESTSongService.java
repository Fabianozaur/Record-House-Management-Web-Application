package rest.service;

import org.springframework.web.bind.annotation.*;
import service.Song.SongService;
import service.Song.SongServiceError;
import service.Song.SongViewModel;

@RequestMapping(value="/songs")
public abstract class RESTSongService implements SongService {

    @RequestMapping(method = RequestMethod.POST, headers = {"content-type=application/json", "accept=application/json"})
    public abstract @ResponseBody SongViewModel
    Add(@RequestBody SongViewModel model) throws SongServiceError;

    @RequestMapping(method = RequestMethod.DELETE)
    public abstract @ResponseBody SongViewModel
    Delete(@RequestParam(name = "id") String id) throws SongServiceError;

    @RequestMapping(method = RequestMethod.PUT)
    public abstract @ResponseBody SongViewModel
    Update(@RequestBody SongViewModel model) throws SongServiceError;

    @RequestMapping(method = RequestMethod.GET)
    public abstract @ResponseBody SongViewModel[]
    GetAll() throws SongServiceError;

    @RequestMapping(value="/count", method = RequestMethod.GET)
    public abstract @ResponseBody Integer
    GetSongCount() throws SongServiceError;

    @RequestMapping(value = "/longest", method = RequestMethod.GET)
    public abstract @ResponseBody SongViewModel
    GetLongestSong() throws SongServiceError;

    @RequestMapping(value = "/most-liked", method = RequestMethod.GET)
    public abstract @ResponseBody SongViewModel
    GetMostLikedSong() throws SongServiceError;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public abstract @ResponseBody SongViewModel[]
    GetByName(@RequestParam(name = "name") String name) throws SongServiceError;
}
