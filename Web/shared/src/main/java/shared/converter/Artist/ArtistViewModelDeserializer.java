package shared.converter.Artist;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import service.Artist.ArtistViewModel;

import java.io.IOException;
import java.util.Optional;

public class ArtistViewModelDeserializer extends StdDeserializer<ArtistViewModel> {

    public ArtistViewModelDeserializer(){
        this(null);
    }

    public ArtistViewModelDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ArtistViewModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        var id = Optional.ofNullable(node.get("id"))
                .filter(n -> !n.isNull())
                .map(JsonNode::asText)
                .orElse(null);
        var stageName = node.get("stageName").asText();
        var firstName = node.get("firstName").asText();
        var lastName = node.get("lastName").asText();

        var model = new ArtistViewModel();
        model.artistId = id;
        model.stageName = stageName;
        model.firstName = firstName;
        model.lastName = lastName;
        return model;
    }
}
