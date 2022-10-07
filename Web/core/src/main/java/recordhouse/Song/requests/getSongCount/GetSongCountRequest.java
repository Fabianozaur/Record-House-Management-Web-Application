package recordhouse.Song.requests.getSongCount;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class GetSongCountRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(GetSongCountRequest.class, GetSongCountRequestHandler.class);
    }
}
