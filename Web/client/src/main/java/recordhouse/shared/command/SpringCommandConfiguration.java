package recordhouse.shared.command;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import recordhouse.shared.command.String.StringCommand;
import recordhouse.shared.command.String.StringCommandReceiver;
import spring.ApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class SpringCommandConfiguration {
    @Bean
    @Lazy
    public StringCommandReceiver GetStringCommandReceiver(){
        var receiver = new StringCommandReceiver();

        receiver.RegisterCommands((List<StringCommand>) ApplicationContext.getInstance("songCommands"));
        receiver.RegisterCommands((List<StringCommand>) ApplicationContext.getInstance("songArtistCommands"));
        receiver.RegisterCommands((List<StringCommand>) ApplicationContext.getInstance("playlistCommands"));
        receiver.RegisterCommands((List<StringCommand>) ApplicationContext.getInstance("playlistSongCommands"));
        receiver.RegisterCommands((List<StringCommand>) ApplicationContext.getInstance("artistCommands"));
        receiver.RegisterCommands((List<StringCommand>) ApplicationContext.getInstance("artistRecordHouseCommands"));
        return receiver;
    }

    @Bean
    public ExecutorService GetExecutorService(){
        return Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }
}
