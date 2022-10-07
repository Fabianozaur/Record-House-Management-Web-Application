package recordhouse.ArtistRecordHouse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recordhouse.ArtistRecordHouse.command.AssignArtistToRecordHouseCommand;
import recordhouse.ArtistRecordHouse.command.PrintAllArtistRecordHouseRelationsCommand;
import recordhouse.shared.command.String.StringCommand;
import spring.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringArtistRecordHouseConfiguration{

    @Bean(name = "artistRecordHouseCommands")
    public List<StringCommand> GetArtistCommands() {
        return Arrays.asList(
                ApplicationContext.getInstance(AssignArtistToRecordHouseCommand.class),
                ApplicationContext.getInstance(PrintAllArtistRecordHouseRelationsCommand.class)
        );
    }
}
