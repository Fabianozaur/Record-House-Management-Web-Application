package recordhouse.Song.requests.delete;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class DeleteSongRequest implements Request {
    public String Id;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(DeleteSongRequest.class, DeleteSongRequestHandler.class);
    }
}
