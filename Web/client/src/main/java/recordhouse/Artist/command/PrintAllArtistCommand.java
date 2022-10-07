package recordhouse.Artist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommand;
import service.Artist.ArtistService;
import service.Artist.ArtistViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PrintAllArtistCommand extends StringCommand {
    private final ArtistService service;

    public PrintAllArtistCommand(ArtistService service){
        super("printAllArtists", "This command prints all artists.");
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        List<ArtistViewModel> artists = Arrays.asList(this.service.GetAll());
        StringBuilder stringBuilder = new StringBuilder();
        artists.forEach((artist) -> stringBuilder.append(artist).append("\n"));
        String artistsString = stringBuilder.toString();

        return Optional.of(artistsString)
                .filter(s -> !s.isEmpty())
                .orElse("There are no artists present!\n");
    }
}