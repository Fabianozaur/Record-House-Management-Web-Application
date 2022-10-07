package recordhouse.shared.mediator;

import design.mediator.Request;
import design.mediator.RequestResult;

public class RequestErrorResult<R extends Request> implements RequestResult<R> {
    public Exception Exception;

    public RequestErrorResult(Exception exception) {
        this.Exception = exception;
    }
}
