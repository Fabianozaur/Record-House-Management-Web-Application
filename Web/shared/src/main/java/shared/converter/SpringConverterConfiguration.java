package shared.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import service.Song.SongViewModel;
import shared.converter.Song.SongViewModelDeserializer;
import shared.converter.Song.SongViewModelSerializer;

import java.util.Date;

@Configuration
public class SpringConverterConfiguration {

    @Bean
    @Primary
    public ObjectMapper GetObjectMapper(@Qualifier("customSimpleModule") SimpleModule module){
        var mapper = new ObjectMapper();
        mapper.registerModule(module);
        return mapper;
    }

    @Bean("customSimpleModule")
    public SimpleModule GetMapperSimpleModule(){
        var module = new SimpleModule();

        module.addSerializer(new SimpleDateSerializer(Date.class));
        module.addDeserializer(Date.class, new SimpleDateDeserializer());
        module.addSerializer(new SongViewModelSerializer());
        module.addDeserializer(SongViewModel.class, new SongViewModelDeserializer());

        return module;
    }
}
