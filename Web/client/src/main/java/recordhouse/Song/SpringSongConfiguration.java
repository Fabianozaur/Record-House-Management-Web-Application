package recordhouse.Song;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recordhouse.Song.command.*;
import recordhouse.shared.command.String.StringCommand;
import spring.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringSongConfiguration {

    @Bean(name = "songCommands")
    public List<StringCommand> GetSongCommands() {
        return Arrays.asList(
                ApplicationContext.getInstance(AddSongCommand.class),
                ApplicationContext.getInstance(UpdateSongCommand.class),
                ApplicationContext.getInstance(DeleteSongCommand.class),
                ApplicationContext.getInstance(ShowSongsCommand.class),
                ApplicationContext.getInstance(SimpleFilterSongsCommand.class),
                ApplicationContext.getInstance(FilterSongsByArtistCountCommand.class),
                ApplicationContext.getInstance(FindSongWithCriteriaCommand.class),
                ApplicationContext.getInstance(GetSongCountCommand.class),
                ApplicationContext.getInstance(SearchSongByNameCommand.class)
        );
    }
}
