package recordhouse.Artist.requests.add;

import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class AddArtistRequest  implements Request {
    public String stageName;
    public String firstName;
    public String lastName;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(AddArtistRequest.class, AddArtistRequestHandler.class);
    }
}