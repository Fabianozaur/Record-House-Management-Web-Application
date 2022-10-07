package recordhouse.Song.requests.searchByName;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class SearchSongByNameRequest implements Request {
    public String Name;
    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(SearchSongByNameRequest.class, SearchSongByNameRequestHandler.class);
    }
}
