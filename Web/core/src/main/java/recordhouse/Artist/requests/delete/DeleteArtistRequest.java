package recordhouse.Artist.requests.delete;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class DeleteArtistRequest implements Request {
    public String artistId;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(DeleteArtistRequest.class, DeleteArtistRequestHandler.class);
    }
}
