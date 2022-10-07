package recordhouse.PlaylistSongRelation.requests.find;

import design.mediator.Request;
import recordhouse.Playlist.requests.find.FindAllPlaylistsRequest;
import recordhouse.Playlist.requests.find.FindAllPlaylistsRequestHandler;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindAllPlaylistSongRelationRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindAllPlaylistSongRelationRequest.class, FindAllPlaylistSongRelationRequestHandler.class);
    }
}
