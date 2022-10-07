package recordhouse.Playlist.Command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommand;
import service.Playlist.PlaylistService;

@Service
public class GetPlaylistCountCommand extends StringCommand {
    private final PlaylistService playlistService;
    public GetPlaylistCountCommand(PlaylistService playlistService) {
        super("get playlist:count", "Get the number of playlists.");
        this.playlistService = playlistService;
    }

    @Override
    public String Execute(String input) {
        return playlistService.GetPlaylistCount().toString();
    }
}
