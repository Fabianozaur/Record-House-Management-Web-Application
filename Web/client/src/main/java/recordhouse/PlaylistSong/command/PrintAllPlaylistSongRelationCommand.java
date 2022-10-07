package recordhouse.PlaylistSong.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommand;
import service.PlaylistSong.PlaylistSongRelationService;
import service.PlaylistSong.PlaylistSongRelationViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PrintAllPlaylistSongRelationCommand extends StringCommand {
    private final PlaylistSongRelationService service;

    public PrintAllPlaylistSongRelationCommand(PlaylistSongRelationService service){
        super("printAllPlaylistSongRelations", "This command prints all  relations between playlist and song");
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        List<PlaylistSongRelationViewModel> playlists =  Arrays.asList(service.GetAll());
        StringBuilder stringBuilder = new StringBuilder();
        playlists.forEach((artist) -> stringBuilder.append(artist).append("\n"));
        String playlistsString = stringBuilder.toString();

        return Optional.of(playlistsString)
                .filter(s->!s.isEmpty())
                .orElse("There are no playlist-song relations\n");


    }
}
