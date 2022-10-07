package shared.converter.Song;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;
import service.Song.SongViewModel;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import static shared.converter.SimpleDateSerializer.dateFormat;

@Component
public class SongViewModelDeserializer extends StdDeserializer<SongViewModel> {
    public SongViewModelDeserializer(){
        this(null);
    }

    public SongViewModelDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SongViewModel deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        var Id = Optional.ofNullable(node.get("id"))
                .filter(n -> !n.isNull())
                .map(JsonNode::asText)
                .orElse(null);
        var Title = node.get("title").asText();
        Date PublishDate;
        try {
            PublishDate = dateFormat.parse(node.get("publishDate").asText());
        } catch (ParseException e) {
            throw new IOException(e);
        }
        var Likes = node.get("likes").asInt();
        var Duration = node.get("duration").asInt();

        var model = new SongViewModel();
        model.Id = Id;
        model.Title = Title;
        model.PublishDate = PublishDate;
        model.Likes = Likes;
        model.Duration = Duration;
        return model;
    }
}
