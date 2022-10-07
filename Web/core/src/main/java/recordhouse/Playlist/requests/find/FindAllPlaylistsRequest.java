package recordhouse.Playlist.requests.find;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindAllPlaylistsRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindAllPlaylistsRequest.class, FindAllPlaylistsRequestHandler.class);
    }
}
