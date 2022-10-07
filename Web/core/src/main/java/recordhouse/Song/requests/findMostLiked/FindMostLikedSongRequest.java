package recordhouse.Song.requests.findMostLiked;

import design.mediator.Request;
import recordhouse.Song.requests.findLongest.FindLongestSongRequest;
import recordhouse.Song.requests.findLongest.FindLongestSongRequestHandler;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class FindMostLikedSongRequest implements Request {
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(FindMostLikedSongRequest.class, FindMostLikedSongRequestHandler.class);
    }
}
