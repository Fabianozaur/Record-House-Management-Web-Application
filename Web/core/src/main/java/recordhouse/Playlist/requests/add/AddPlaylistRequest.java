package recordhouse.Playlist.requests.add;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class AddPlaylistRequest implements Request {
    public String playlistName;
    public boolean isPublic;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(AddPlaylistRequest.class,AddPlaylistRequestHandler.class);
    }
}
