package rest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;

@Service
@Scope("prototype")
public class RESTProxyProvider {

    private final RESTInvocationHandler handler;

    public RESTProxyProvider(RESTInvocationHandler handler) {
        this.handler = handler;
    }

    public <I, C> I GetProxy(Class<I> serviceInterface, Class<C> serviceConfiguration){
        return (I) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class[] { serviceInterface },
                handler.ForClass(serviceInterface, serviceConfiguration));
    }
}
