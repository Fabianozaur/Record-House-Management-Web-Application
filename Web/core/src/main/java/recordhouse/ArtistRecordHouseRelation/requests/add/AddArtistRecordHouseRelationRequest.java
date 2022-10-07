package recordhouse.ArtistRecordHouseRelation.requests.add;


import design.mediator.Request;
import recordhouse.shared.mediator.SpringMediator;
import spring.ApplicationContext;

public class AddArtistRecordHouseRelationRequest implements Request {
    public String artistId;
    public String recordHouseId;

    static {
        ApplicationContext.getInstance(SpringMediator.class)
                .RegisterHandler(AddArtistRecordHouseRelationRequest.class, AddArtistRecordHouseRelationRequestHandler.class);
    }
}