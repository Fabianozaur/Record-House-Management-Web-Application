package recordhouse.PlaylistSongRelation.requests.find;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindPlaylistSongCountRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindPlaylistSongCountRequest.class,FindPlaylistSongCountRequestHandler.class);
    }
}
