package shared.converter.Artist;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import service.Artist.ArtistViewModel;

import java.io.IOException;

import static shared.converter.SimpleDateSerializer.dateFormat;

public class ArtistViewModelSerializer extends StdSerializer<ArtistViewModel> {

    public ArtistViewModelSerializer() {
        super(ArtistViewModel.class);
    }

    @Override
    public void serialize(ArtistViewModel model, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", model.artistId);
        jsonGenerator.writeStringField("stageName", model.stageName);
        jsonGenerator.writeStringField("firstName", model.firstName);
        jsonGenerator.writeStringField("lastName", model.lastName);
        jsonGenerator.writeEndObject();
    }
}
