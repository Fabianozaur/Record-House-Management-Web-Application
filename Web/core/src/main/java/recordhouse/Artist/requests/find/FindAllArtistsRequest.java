package recordhouse.Artist.requests.find;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindAllArtistsRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindAllArtistsRequest.class, FindAllArtistsRequestHandler.class);
    }
}
