package shared.converter.Playlist;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;
import service.Playlist.PlaylistViewModel;

import java.io.IOException;
import java.util.Optional;

@Component
public class PlaylistViewModelDeserializer extends StdDeserializer<PlaylistViewModel> {
    public PlaylistViewModelDeserializer(){this(null);}
    public PlaylistViewModelDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PlaylistViewModel deserialize(JsonParser p, DeserializationContext context) throws IOException{
        JsonNode node=p.getCodec().readTree(p);
        var Id= Optional.ofNullable(node.get("Id"))
                .filter(n->!n.isNull())
                .map(JsonNode::asText)
                .orElse(null);
        var Name=node.get("PlaylistName").asText();
        var IsPublic=node.get("isPublic").asBoolean();
        var model=new PlaylistViewModel();
        model.Id=Id;
        model.Name=Name;
        model.isPublic=IsPublic;
        return model;
    }
}
