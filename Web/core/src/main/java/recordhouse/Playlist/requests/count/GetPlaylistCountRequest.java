package recordhouse.Playlist.requests.count;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class GetPlaylistCountRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(GetPlaylistCountRequest.class, GetPlaylistCountRequestHandler.class);
    }
}
