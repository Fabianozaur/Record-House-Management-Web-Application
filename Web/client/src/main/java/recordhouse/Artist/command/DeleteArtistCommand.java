package recordhouse.Artist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Artist.ArtistService;

@Service
public class DeleteArtistCommand extends StringCommand {
    private final ArtistService service;

    public DeleteArtistCommand(ArtistService service){
        super("removeArtist", "This command removes an artist.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("removeArtist [a-zA-Z0-9-]+$"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String artistId = splitInput[1];

        var result = this.service.Delete(artistId);
        return String.format("Deleted song with id: %s.\n", result.artistId);
    }
}
