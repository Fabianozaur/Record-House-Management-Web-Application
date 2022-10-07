package recordhouse.SongArtistRelation.requests.find;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindSongArtistCountsRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindSongArtistCountsRequest.class, FindSongArtistCountsRequestHandler.class);
    }
}
