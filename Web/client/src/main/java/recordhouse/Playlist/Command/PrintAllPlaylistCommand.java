package recordhouse.Playlist.Command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommand;
import service.Playlist.PlaylistService;
import service.Playlist.PlaylistViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PrintAllPlaylistCommand extends StringCommand {
    private PlaylistService service;

    public PrintAllPlaylistCommand(PlaylistService playlistService){
        super("PrintAllPlaylists", "Print all the playlists.");
        this.service=playlistService;
    }

    @Override
    public String Execute(String input) {
        List<PlaylistViewModel> playlists = Arrays.asList(this.service.GetAll());
        StringBuilder stringBuilder=new StringBuilder();
        playlists.forEach((s)->stringBuilder.append(s).append("\n"));
        String playlistString=stringBuilder.toString();
        return Optional.of(playlistString)
                .filter(s -> !s.isEmpty())
                .orElse("There is no playlists to present\n");
    }
}

