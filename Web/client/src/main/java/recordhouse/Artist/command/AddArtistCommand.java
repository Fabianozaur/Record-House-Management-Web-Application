package recordhouse.Artist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Artist.ArtistService;
import service.Artist.ArtistViewModel;

@Service
public class AddArtistCommand extends StringCommand {

    private final ArtistService service;

//    attendance
    public AddArtistCommand(ArtistService service){
        super("addArtist", "This command adds an artist.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^addArtist [a-zA-Z0-9]+ [a-zA-Z]+ [a-zA-Z]+$"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String stageName = splitInput[1];
        String firstName = splitInput[2];
        String lastName = splitInput[3];

        ArtistViewModel model =  new ArtistViewModel();
        model.stageName = stageName;
        model.firstName = firstName;
        model.lastName = lastName;

        var result = this.service.Add(model);
        return String.format("Artist created with id: %s.\n", result.artistId);
    }
}
