package recordhouse.Artist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Artist.ArtistService;
import service.Artist.ArtistViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class FilterArtistByStageNameCommand extends StringCommand{
    private final ArtistService service;

    public FilterArtistByStageNameCommand(ArtistService service){
        super("filterArtistByStageName", "This command filters an artist by stage name.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^filterArtistByStageName [a-zA-Z0-9]+$"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String stageName = splitInput[1];

        List<ArtistViewModel> artists = Arrays.stream(service.GetAll())
                .filter((s) -> Pattern.matches(stageName, s.stageName))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        artists.forEach((artist) -> stringBuilder.append(artist).append("\n"));
        String artistsString = stringBuilder.toString();

        return Optional.of(artistsString)
                .filter(s -> !s.isEmpty())
                .orElse("There are no artists with stage name " + stageName + "!\n");
    }
}

