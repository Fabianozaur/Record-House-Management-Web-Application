package recordhouse.Song.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommand;
import service.Song.SongService;

@Service
public class GetSongCountCommand extends StringCommand {

    private final SongService songService;
    public GetSongCountCommand(SongService songService) {
        super("get song:count", "Get the number of songs.");
        this.songService = songService;
    }

    @Override
    public String Execute(String input) {
        return songService.GetSongCount().toString();
    }
}
