package recordhouse.SongArtistRelation.requests.find;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindAllSongArtistRelationsRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindAllSongArtistRelationsRequest.class, FindAllSongArtistRelationsRequestHandler.class);
    }
}
