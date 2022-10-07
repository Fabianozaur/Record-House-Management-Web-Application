package recordhouse.Song.requests.update;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

import java.util.Date;

public class UpdateSongRequest implements Request {
    public String Id;
    public String Title;
    public Integer Duration;
    public Integer Likes;
    public Date PublishDate;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(UpdateSongRequest.class, UpdateSongRequestHandler.class);
    }
}
