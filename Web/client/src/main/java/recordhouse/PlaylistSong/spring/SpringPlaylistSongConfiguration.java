package recordhouse.PlaylistSong.spring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recordhouse.PlaylistSong.command.AssignSongToPlaylistCommand;
import recordhouse.PlaylistSong.command.PrintAllPlaylistSongRelationCommand;
import recordhouse.shared.command.String.StringCommand;
import spring.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringPlaylistSongConfiguration{

    @Bean(name = "playlistSongCommands")
    public List<StringCommand> GetPlaylistSongCommands() {
        return Arrays.asList(
                ApplicationContext.getInstance(AssignSongToPlaylistCommand.class),
                ApplicationContext.getInstance(PrintAllPlaylistSongRelationCommand.class)
        );
    }
}