package recordhouse.Artist.requests.update;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class UpdateArtistRequest implements Request {
    public String artistId;
    public String stageName;
    public String firstName;
    public String lastName;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(UpdateArtistRequest.class, UpdateArtistRequestHandler.class);
    }
}
