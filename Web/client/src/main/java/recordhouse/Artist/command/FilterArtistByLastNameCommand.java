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

@Service
public class FilterArtistByLastNameCommand extends StringCommand {
    private final ArtistService service;

    public FilterArtistByLastNameCommand(ArtistService service){
        super("filterArtistByLastName", "This command filters an artist by last name.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^filterArtistByLastName [a-zA-Z]+$"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String lastName = splitInput[1];

        List<ArtistViewModel> artists = Arrays.stream(service.GetAll())
                .filter((s) -> Pattern.matches(lastName, s.lastName))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        artists.forEach((artist) -> stringBuilder.append(artist).append("\n"));
        String artistsString = stringBuilder.toString();

        return Optional.of(artistsString)
                .filter(s -> !s.isEmpty())
                .orElse("There are no artists with last name " + lastName + "!\n");
    }
}
