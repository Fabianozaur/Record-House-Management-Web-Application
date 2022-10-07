package recordhouse.Playlist.requests.delete;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class DeletePlaylistRequest implements Request {
    public String playlistId;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(DeletePlaylistRequest.class, DeletePlaylistRequestHandler.class);
    }
}
