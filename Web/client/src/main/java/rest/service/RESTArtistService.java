package rest.service;

import org.springframework.web.bind.annotation.*;
import service.Artist.ArtistService;
import service.Artist.ArtistServiceError;
import service.Artist.ArtistViewModel;

@RequestMapping(value="/artists")
public abstract class RESTArtistService implements ArtistService {

    @RequestMapping(method = RequestMethod.POST, headers = {"content-type=application/json", "accept=application/json"})
    public abstract @ResponseBody ArtistViewModel
    Add(@RequestBody ArtistViewModel model) throws ArtistServiceError;

    @RequestMapping(method = RequestMethod.DELETE)
    public abstract @ResponseBody ArtistViewModel
    Delete(@RequestParam(name = "id") String id) throws ArtistServiceError;

    @RequestMapping(method = RequestMethod.PUT)
    public abstract @ResponseBody ArtistViewModel
    Update(@RequestBody ArtistViewModel model) throws ArtistServiceError;

    @RequestMapping(method = RequestMethod.GET)
    public abstract @ResponseBody ArtistViewModel[]
    GetAll() throws ArtistServiceError;

    @RequestMapping(value="/count", method = RequestMethod.GET)
    public abstract @ResponseBody Integer
    GetArtistCount() throws ArtistServiceError;
}
