package shared.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public final class PropertiesLoader {

    public static Properties Load(String fileName){
        Properties properties = new Properties();

        InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName);

        Optional.ofNullable(inputStream)
                .ifPresentOrElse((i) -> LoadInto(properties, i),
                        () -> {throw new PropertiesLoaderError("Unable to find file " + fileName); }
                );

        return properties;
    }

    private static void LoadInto(Properties properties, InputStream inputStream){
        try{
            TryLoadInto(properties, inputStream);
        }catch(IOException e){
            throw new PropertiesLoaderError(e);
        }
    }

    private static void TryLoadInto(Properties properties, InputStream inputStream) throws IOException {
        properties.load(inputStream);
    }
}
