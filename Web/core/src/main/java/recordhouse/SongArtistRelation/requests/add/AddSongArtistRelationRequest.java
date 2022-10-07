package recordhouse.SongArtistRelation.requests.add;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class AddSongArtistRelationRequest implements Request {
    public String SongId;
    public String ArtistId;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(AddSongArtistRelationRequest.class, AddSongArtistRelationRequestHandler.class);
    }
}
