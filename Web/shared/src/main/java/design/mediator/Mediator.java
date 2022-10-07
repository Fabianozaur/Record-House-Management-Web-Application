package design.mediator;

public interface Mediator {
    <R extends Request> RequestResult<R> Execute(R command);

    <R extends Request, RH extends RequestHandler<R>> void RegisterHandler(Class<R> command, Class<RH> handler);
}
