package shared.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateSerializer extends StdSerializer<Date> {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public SimpleDateSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Date model, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("date", dateFormat.format(model));
        jsonGenerator.writeEndObject();
    }
}
