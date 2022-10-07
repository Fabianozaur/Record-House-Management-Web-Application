package shared.converter.Song;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.stereotype.Component;
import service.Song.SongViewModel;

import java.io.IOException;

import static shared.converter.SimpleDateSerializer.dateFormat;

@Component
public class SongViewModelSerializer extends StdSerializer<SongViewModel> {

    public SongViewModelSerializer() {
        super(SongViewModel.class);
    }

    @Override
    public void serialize(SongViewModel model, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", model.Id);
        jsonGenerator.writeStringField("title", model.Title);
        jsonGenerator.writeObjectField("publishDate", dateFormat.format(model.PublishDate));
        jsonGenerator.writeNumberField("likes", model.Likes);
        jsonGenerator.writeNumberField("duration", model.Duration);
        jsonGenerator.writeEndObject();
    }
}