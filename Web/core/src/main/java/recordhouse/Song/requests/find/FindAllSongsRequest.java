package recordhouse.Song.requests.find;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindAllSongsRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindAllSongsRequest.class, FindAllSongsRequestHandler.class);
    }
}
