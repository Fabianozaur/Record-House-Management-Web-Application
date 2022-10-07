package design.mediator;

public interface RequestHandler<R extends Request> {
    RequestResult<R> Handle(R command);
}
