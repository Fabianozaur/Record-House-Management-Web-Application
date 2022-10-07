package shared.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static shared.converter.SimpleDateSerializer.dateFormat;

@Component
public class SimpleDateDeserializer extends StdDeserializer<Date> {
    public SimpleDateDeserializer(){
        this(null);
    }

    public SimpleDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        Date date;
        try {
            date = dateFormat.parse(node.get("date").asText());
        } catch (ParseException e) {
            throw new IOException(e);
        }
        return date;
    }
}
