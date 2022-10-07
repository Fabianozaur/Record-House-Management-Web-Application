package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Scope("prototype")
public class RESTInvocationHandler implements InvocationHandler {

    private final String remoteUrl;
    private final RestTemplate rest;
    private final ObjectMapper mapper;


    public RESTInvocationHandler(@Qualifier("remoteUrl") String remoteUrl, RestTemplate rest, ObjectMapper mapper) {
        this.remoteUrl = remoteUrl;
        this.rest = rest;
        this.mapper = mapper;
    }

    private Class<?> serviceInterface;
    private Class<?> serviceConfiguration;

    public RESTInvocationHandler ForClass(Class<?> serviceInterface, Class<?> serviceConfiguration){
        this.serviceInterface = serviceInterface;
        this.serviceConfiguration = serviceConfiguration;
        return this;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
        if (method.getName().equals("toString"))
            return serviceInterface.getSimpleName();

        Method configurationMethod = null;
        try{
            configurationMethod = serviceConfiguration.getMethod(method.getName(), method.getParameterTypes());
        }catch(NoSuchMethodException e){
            throw new RESTInvocationHandlerException(String.format("Unable to find methd: %s on configuration class", method.getName()));
        }
        var uri = GetUri(configurationMethod);
        var httpMethod = GetHttpMethod(configurationMethod);
        var argumentMap = GetArgumentMap(configurationMethod, arguments);
        var headers = new LinkedMultiValueMap<>(Arrays.stream(configurationMethod.getAnnotation(RequestMapping.class)
                .headers())
                .collect(Collectors.toMap(this::ExtractKey, this::ExtractValues)));

        uri = ExpandUri(uri, argumentMap);

        var configurationMethodParameters = configurationMethod.getParameters();
        var argIndex = Optional.ofNullable(arguments)
                .map((a) -> IntStream.range(0, a.length)
                        .filter((i) -> configurationMethodParameters[i].isAnnotationPresent(RequestBody.class))
                        .findFirst()
                        .orElse(-1));

        var bodyResult = argIndex
                .filter(a -> a != -1)
                .map((i) -> arguments[i]);

        if(httpMethod.equals(HttpMethod.POST)){
            var body = bodyResult
                    .orElseThrow(() ->
                new RESTInvocationHandlerException("Body cannot be null on a post!"));

            return rest.postForObject(uri, body, configurationMethod.getReturnType());
        }

        if(httpMethod.equals(HttpMethod.PUT)){
            var body = bodyResult
                    .orElseThrow(() ->
                            new RESTInvocationHandlerException("Body cannot be null on a put!"));

            var entity = new HttpEntity<>(body);
            return rest.exchange(uri, httpMethod, entity, configurationMethod.getReturnType()).getBody();
        }


        Method finalConfigurationMethod = configurationMethod;

        var voidReturnType = configurationMethod.getReturnType().equals(Void.TYPE);
        var returnType = configurationMethod.getReturnType();

        var exceptionType = Arrays.stream(configurationMethod.getExceptionTypes()).findFirst()
                .orElse(Exception.class);

        var exceptionConstructor = exceptionType.getConstructor(String.class);

        try{
            return rest.execute(
                    uri,
                    httpMethod,
                    null,
                    (response) -> Optional.of(response)
                            .filter((r) -> !voidReturnType)
                            .map((r) -> {
                                try {
                                    return mapper.readValue(r.getBody(), returnType);
                                } catch (IOException e) {
                                    throw new RESTInvocationHandlerException(e);
                                }
                            })
                            .orElse(null)
            );
        }catch(HttpServerErrorException e){
            var exception = exceptionConstructor.newInstance(e.getMessage());
            throw (Exception) exception;
        }
    }

    private String ExtractKey(String header){
        var splits = header.split("=");
        return splits[0];
    }
    private List<String> ExtractValues(String header){
        var splits = header.split("=");
        var values = splits[1];
        return Arrays.asList(values.split(",").clone());
    }
    private HashMap<String, Object> GetArgumentMap(Method method, Object[] arguments) {
        var argumentMap = new HashMap<String, Object>();
        if(arguments == null || arguments.length == 0)
            return argumentMap;

        var methodParameterAnnotations = method.getParameterAnnotations();
        IntStream.range(0, arguments.length)
                .forEach((i) -> {
                    var annotations = methodParameterAnnotations[i];
                    Arrays.stream(annotations).filter((a) -> a.annotationType().equals(RequestParam.class))
                            .findFirst()
                            .map(a -> (RequestParam) a)
                            .ifPresent((r) -> argumentMap.put(r.name(), arguments[i]));

                });

        return argumentMap;
    }

    private URI GetUri(Method method) {
        var classRequestMapping = serviceConfiguration.getAnnotation(RequestMapping.class);
        var baseUrl = Arrays.stream(classRequestMapping.value())
                .findFirst()
                .orElse("");

        var requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
        var methodUrl = Arrays.stream(requestMapping.value()).findFirst()
                .orElse("");

        var url = remoteUrl + baseUrl + methodUrl;
        try{
            var uri = new URI(url);
            return uri;
        }catch(URISyntaxException e){
            throw new RESTInvocationHandlerException(e);
        }
    }

    private HttpMethod GetHttpMethod(Method method) {
        var requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
        var requestMethod = Arrays.stream(requestMapping.method()).findFirst()
                .orElseThrow(() -> new RESTInvocationHandlerException("Unable to find request method on annotation."));

        return HttpMethod.resolve(requestMethod.name());
    }

    private URI ExpandUri(URI uri, Map<String, Object> values){
        var uriBuilder = UriComponentsBuilder.fromUri(uri);
        values.forEach(uriBuilder::queryParam);

        return uriBuilder.build().toUri();
    }
}
