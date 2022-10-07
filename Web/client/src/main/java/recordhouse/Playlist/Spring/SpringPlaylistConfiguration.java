package recordhouse.Playlist.Spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recordhouse.shared.command.String.StringCommand;
import spring.ApplicationContext;
import recordhouse.Playlist.Command.*;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringPlaylistConfiguration {
    @Bean(name = "playlistCommands")
    public List<StringCommand> GetSongCommands() {
        return Arrays.asList(
                ApplicationContext.getInstance(AddPlaylistCommand.class),
                ApplicationContext.getInstance(UpdatePlaylistCommand.class),
                ApplicationContext.getInstance(RemovePlaylistCommand.class),
                ApplicationContext.getInstance(PrintAllPlaylistCommand.class),
                ApplicationContext.getInstance(FilterPlaylistByNameCommand.class),
                ApplicationContext.getInstance(FilterPlaylistByPublicityCommand.class),
                ApplicationContext.getInstance(FilterPlaylistBySongCountCommand.class),
                ApplicationContext.getInstance(FindPlaylistShortestLongestCommand.class),
                ApplicationContext.getInstance(GetPlaylistCountCommand.class)

        );
    }
}
