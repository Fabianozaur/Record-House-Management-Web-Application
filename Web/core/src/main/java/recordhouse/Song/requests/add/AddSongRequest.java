package recordhouse.Song.requests.add;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

import java.util.Date;

public class AddSongRequest implements Request {
    public String Title;
    public Integer Duration;
    public Integer Likes;
    public Date PublishDate;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(AddSongRequest.class, AddSongRequestHandler.class);
    }
}
