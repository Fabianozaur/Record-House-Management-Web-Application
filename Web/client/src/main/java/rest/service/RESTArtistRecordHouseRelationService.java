package rest.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ArtistRecordHouse.ArtistRecordHouseRelationService;
import service.ArtistRecordHouse.ArtistRecordHouseRelationViewModel;

@RequestMapping("/artist-recordhouse")
public abstract class RESTArtistRecordHouseRelationService implements ArtistRecordHouseRelationService {
    @RequestMapping(method = RequestMethod.POST)
    public abstract void Add(@RequestBody ArtistRecordHouseRelationViewModel model);

    @RequestMapping(method = RequestMethod.GET)
    public abstract ArtistRecordHouseRelationViewModel[] GetAll();
}
