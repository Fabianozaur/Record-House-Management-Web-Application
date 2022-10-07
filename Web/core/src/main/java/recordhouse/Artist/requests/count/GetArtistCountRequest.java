package recordhouse.Artist.requests.count;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class GetArtistCountRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(GetArtistCountRequest.class, GetArtistCountRequestHandler.class);
    }
}
