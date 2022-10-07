package shared.converter.Playlist;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.stereotype.Component;
import service.Playlist.PlaylistViewModel;

import java.io.IOException;

import static shared.converter.SimpleDateSerializer.dateFormat;

@Component
public class PlaylistViewModelSerializer extends StdSerializer<PlaylistViewModel> {

    public PlaylistViewModelSerializer(){super(PlaylistViewModel.class);}

    @Override
    public void serialize(PlaylistViewModel model, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException{
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("Id", model.Id);
        jsonGenerator.writeStringField("PlaylistName", model.Name);
        jsonGenerator.writeBooleanField("isPublic", model.isPublic);
        jsonGenerator.writeEndObject();
    }
}
