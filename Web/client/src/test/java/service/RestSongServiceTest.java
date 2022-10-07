package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import rest.RESTInvocationHandler;
import rest.service.RESTSongService;
import service.Song.SongService;
import service.Song.SongViewModel;
import spring.ApplicationContext;
import spring.SpringConfiguration;

import java.lang.reflect.Array;
import java.util.Date;

class RestSongServiceTest {

    @BeforeAll
    static void Init(){
        ApplicationContext.InitializeAnnotationContext(SpringConfiguration.class);
    }


    @Test
    void Add(){

        var interfaceClass = SongService.class;
        var serviceClass = RESTSongService.class;

        var template = ApplicationContext.getInstance(RestTemplate.class);
        var mapper = ApplicationContext.getInstance(ObjectMapper.class);
        var handler = new RESTInvocationHandler("http://localhost:8080", template, mapper);

        handler.ForClass(interfaceClass, serviceClass);
        var proxy = new Object();

        var method = Assertions.assertDoesNotThrow(() -> serviceClass.getMethod("Add", SongViewModel.class));

        var parameter = new SongViewModel();
        parameter.Title = "Billy!";
        parameter.Duration = 4;
        parameter.Likes = 2;
        parameter.PublishDate = new Date();

        var result = Assertions.assertDoesNotThrow(() -> handler.invoke(proxy, method, new Object[]{
                parameter
        }));


        Assertions.assertTrue(result instanceof Array);
    }
}