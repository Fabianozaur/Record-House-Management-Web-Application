package recordhouse.PlaylistSongRelation.requests.add;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class AddPlaylistSongRelationRequest implements Request {
    public String playlistId;
    public String songId;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(AddPlaylistSongRelationRequest.class, AddPlaylistSongRelationRequestHandler.class);
    }
}
