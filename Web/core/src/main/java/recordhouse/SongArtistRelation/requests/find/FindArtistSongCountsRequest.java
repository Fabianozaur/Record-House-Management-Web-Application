package recordhouse.SongArtistRelation.requests.find;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindArtistSongCountsRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindArtistSongCountsRequest.class, FindArtistSongCountsRequestHandler.class);
    }
}
