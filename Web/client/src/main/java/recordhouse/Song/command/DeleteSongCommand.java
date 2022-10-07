package recordhouse.Song.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Song.SongService;

@Service
public class DeleteSongCommand extends StringCommand {

    private SongService service;
    public DeleteSongCommand(SongService service){
        super("delete song", "Delete a song.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("delete song ([a-zA-Z0-9]|-)+"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String id = splitInput[2];
        var result = service.Delete(id);
        return String.format("Deleted song with id: %s", result.Id);
    }
}
