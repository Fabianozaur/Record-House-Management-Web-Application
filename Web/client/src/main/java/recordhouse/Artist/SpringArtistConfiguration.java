package recordhouse.Artist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recordhouse.Artist.command.*;
import recordhouse.shared.command.String.StringCommand;
import spring.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringArtistConfiguration {

    @Bean(name = "artistCommands")
    public List<StringCommand> GetSongCommands() {
        return Arrays.asList(
                ApplicationContext.getInstance(AddArtistCommand.class),
                ApplicationContext.getInstance(UpdateArtistCommand.class),
                ApplicationContext.getInstance(DeleteArtistCommand.class),
                ApplicationContext.getInstance(PrintAllArtistCommand.class),
                ApplicationContext.getInstance(FilterArtistByStageNameCommand.class),
                ApplicationContext.getInstance(FilterArtistByFirstNameCommand.class),
                ApplicationContext.getInstance(FilterArtistByLastNameCommand.class),
                ApplicationContext.getInstance(FilterArtistBySongCountCommand.class),
                ApplicationContext.getInstance(FindArtistWithMaxMinSongCount.class),
                ApplicationContext.getInstance(GetArtistCountCommand.class)
        );
    }
}
