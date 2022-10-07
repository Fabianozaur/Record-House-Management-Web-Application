package recordhouse.Artist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Artist.ArtistService;

@Service
public class GetArtistCountCommand extends StringCommand {

    private final ArtistService service;

    public GetArtistCountCommand(ArtistService service) {
        super("getArtistCount", "This command gets the number of artists.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^getArtistCount$"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        return service.GetArtistCount().toString();
    }
}