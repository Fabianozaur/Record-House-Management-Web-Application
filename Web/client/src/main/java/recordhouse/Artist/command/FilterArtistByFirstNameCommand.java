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
public class FilterArtistByFirstNameCommand extends StringCommand {
    private final ArtistService service;

    public FilterArtistByFirstNameCommand(ArtistService service){
        super("filterArtistByFirstName", "This command filters an artist by first name.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^filterArtistByFirstName [a-zA-Z]+$"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String firstName = splitInput[1];

        List<ArtistViewModel> artists = Arrays.stream(service.GetAll())
                .filter((s) -> Pattern.matches(firstName, s.firstName))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        artists.forEach((artist) -> stringBuilder.append(artist).append("\n"));
        String artistsString = stringBuilder.toString();

        return Optional.of(artistsString)
                .filter(s -> !s.isEmpty())
                .orElse("There are no artists with first name " + firstName + "!\n");
    }
}
