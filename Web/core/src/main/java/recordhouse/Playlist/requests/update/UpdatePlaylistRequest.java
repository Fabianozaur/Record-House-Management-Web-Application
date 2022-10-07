package recordhouse.Playlist.requests.update;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class UpdatePlaylistRequest implements Request {
    public String playlistId;
    public String name;
    public boolean isPublic;
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(UpdatePlaylistRequest.class, UpdatePlaylistRequestHandler.class);
    }
}
