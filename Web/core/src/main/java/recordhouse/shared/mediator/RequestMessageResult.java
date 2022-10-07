package recordhouse.shared.mediator;

import design.mediator.Request;
import design.mediator.RequestResult;

public class RequestMessageResult<R extends Request> implements RequestResult<R> {
    public String Message;

    public RequestMessageResult(String message) {
        this.Message = message;
    }
}
