package recordhouse.Artist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Artist.ArtistService;
import service.Artist.ArtistViewModel;

@Service
public class UpdateArtistCommand extends StringCommand {
    private final ArtistService service;

    public UpdateArtistCommand(ArtistService service){
        super("updateArtist", "This command updates an artist.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^updateArtist [a-zA-Z0-9-]+ [a-zA-Z0-9]+ [a-zA-Z]+ [a-zA-Z]+$"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String artistId = splitInput[1];
        String stageName = splitInput[2];
        String firstName = splitInput[3];
        String lastName = splitInput[4];

        var model =  new ArtistViewModel();
        model.artistId = artistId;
        model.stageName = stageName;
        model.firstName = firstName;
        model.lastName = lastName;

        this.service.Update(model);
        return "Artist updated.\n";
    }
}
