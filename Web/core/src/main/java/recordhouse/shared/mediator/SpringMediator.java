package recordhouse.shared.mediator;

import design.exception.ApplicationException;
import design.mediator.Mediator;
import design.mediator.Request;
import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import spring.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("springMediator")
@Scope("singleton")
public class SpringMediator implements Mediator {

    private Map<Class<?>, Class<? extends RequestHandler<?>>> entries = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(SpringMediator.class);

    public SpringMediator() {
    }

    @Override
    public <R extends Request> RequestResult<R> Execute(R request) {
        logger.trace("Executing request: {}", request.getClass());

        var handler = Optional.of(entries.get(request.getClass()))
                .orElseThrow(() -> new SpringMediatorError(String.format("Request %s does not have a handler registered.", request.getClass().getSimpleName())));
        var instance = (RequestHandler<R>) ApplicationContext.getInstance(handler);
        try {
            return instance.Handle(request);
        } catch (ApplicationException e) {
            logger.error("Failed to execute request: {}\n" +
                    "Reason: {} - Message: {}", request.getClass(), e.getClass(), e.getMessage());
            return new RequestErrorResult<>(e);
        }
    }

    @Override
    public <R extends Request, RH extends RequestHandler<R>> void RegisterHandler(Class<R> command, Class<RH> handler) {
        logger.info("Registering handler for command: {} - {}", command, handler);
        entries.put(command, handler);
    }
}
