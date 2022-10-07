package recordhouse.Playlist.Command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Playlist.PlaylistService;

@Service
public class RemovePlaylistCommand extends StringCommand {
    private PlaylistService service;

    public RemovePlaylistCommand(PlaylistService playlistService){
        super("RemovePlaylist", "If you want to remove a playlist.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("RemovePlaylist ([a-zA-Z0-9]|-)+$"));
        this.service=playlistService;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String id = splitInput[1];

        this.service.Delete(id);
        return "Playlist removed.\n";
    }
}
