package recordhouse.SongArtist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recordhouse.SongArtist.command.AssignSongToArtistCommand;
import recordhouse.SongArtist.command.PrintAllSongArtistRelationCommand;
import recordhouse.shared.command.String.StringCommand;
import spring.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringSongArtistConfiguration {

    @Bean(name = "songArtistCommands")
    public List<StringCommand> GetSongCommands() {
        return Arrays.asList(
                ApplicationContext.getInstance(AssignSongToArtistCommand.class),
                ApplicationContext.getInstance(PrintAllSongArtistRelationCommand.class)
        );
    }
}
