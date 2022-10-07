package recordhouse.Song.requests.findLongest;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindLongestSongRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindLongestSongRequest.class, FindLongestSongRequestHandler.class);
    }
}
