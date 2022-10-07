package recordhouse.SongArtist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommand;
import service.SongArtist.SongArtistRelationService;
import service.SongArtist.SongArtistRelationViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PrintAllSongArtistRelationCommand extends StringCommand {
    private final SongArtistRelationService service;

    public PrintAllSongArtistRelationCommand(SongArtistRelationService service) {
        super("printAllSongArtistRelations", "This command prints all song-artist relations.");
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        List<SongArtistRelationViewModel> artists = Arrays.asList(service.GetAll());
        StringBuilder stringBuilder = new StringBuilder();
        artists.forEach((artist) -> stringBuilder.append(artist).append("\n"));
        String artistsString = stringBuilder.toString();

        return Optional.of(artistsString)
                .filter(s -> !s.isEmpty())
                .orElse("There are no song-artist relations present!");
    }
}
